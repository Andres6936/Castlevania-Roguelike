package co.castle.ui.consoleUI;

import java.util.Hashtable;
import java.util.Vector;

import co.castle.action.Action;
import co.castle.action.Drop;
import co.castle.action.Equip;
import co.castle.action.Throw;
import co.castle.action.Unequip;
import co.castle.action.Use;
import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.feature.Feature;
import co.castle.feature.SmartFeature;
import co.castle.game.GameFiles;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.item.Merchant;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.main.Service;
import co.castle.monster.Monster;
import co.castle.monster.VMonster;
import co.castle.npc.NPC;
import co.castle.player.Consts;
import co.castle.player.Equipment;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.Skill;
import co.castle.player.advancements.Advancement;
import co.castle.ui.ActionCancelException;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.CommandListener;
import co.castle.ui.Display;
import co.castle.ui.UserCommand;
import co.castle.ui.UserInterface;
import co.castle.ui.consoleUI.effects.CharEffect;
import co.castle.ui.effects.Effect;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.csi.textcomponents.BasicListItem;
import sz.csi.textcomponents.ListBox;
import sz.csi.textcomponents.MenuBox;
import sz.csi.textcomponents.SimpleMenuItem;
import sz.csi.textcomponents.TextBox;
import sz.csi.textcomponents.TextInformBox;
import sz.util.Debug;
import sz.util.Line;
import sz.util.Position;
import sz.util.Util;

/**
 * Shows the level using characters. Informs the Actions and Commands of the
 * player. Must be listening to a System Interface
 */

public class ConsoleUserInterface extends UserInterface
		implements CommandListener, Runnable
{
	public boolean showPersistantMessageBox = false;
	public final Position VP_START = new Position( 1, 3 ),
			VP_END = new Position( 51, 21 ), PC_POS = new Position( 25, 12 );
	private int[ ] additionalKeys = new int[ ]
	{ CharKey.N1, CharKey.N2, CharKey.N3, CharKey.N4, };

	private boolean eraseOnArrival; // Erase the buffer upon the arrival of a new msg
	private boolean[ ][ ] FOVMask;
	private Hashtable hashSpaces = new Hashtable( );
	private ListBox idList;

	private int[ ] itemUsageAdditionalKeys = new int[ ]
	{ CharKey.u, CharKey.e, CharKey.d, CharKey.t, };

	private Monster lockedMonster;

	// Components
	private TextInformBox messageBox;

	private Vector messageHistory = new Vector( 20, 10 );

	private TextBox persistantMessageBox;

	private transient ConsoleSystemInterface si;

	private Hashtable /* BasicListItem */ sightListItems = new Hashtable( );
	// Relations

	private Action target;

	private Vector vecItemUsageChoices = new Vector( );

	// Attributes
	private int xrange = 25;

	private int yrange = 9;

	{
		vecItemUsageChoices.add( new SimpleMenuItem( '*', "(u)se", 1 ) );
		vecItemUsageChoices.add( new SimpleMenuItem( '*', "(e)quip", 2 ) );
		vecItemUsageChoices.add( new SimpleMenuItem( '*', "(d)rop", 3 ) );
		vecItemUsageChoices.add( new SimpleMenuItem( '*', "(t)hrow", 4 ) );
		vecItemUsageChoices.add( new SimpleMenuItem( '*', "( ) Cancel", 5 ) );

	}

	public void addMessage( Message message )
	{
		Debug.enterMethod( this, "addMessage", message );
		if ( eraseOnArrival )
		{
			messageBox.clear( );
			messageBox.setForeColor( ConsoleSystemInterface.RED );
			eraseOnArrival = false;
		}
		if ( ( player != null && player.getPosition( ) != null
				&& message.getLocation( ).z != player.getPosition( ).z )
				|| ( message.getLocation( ) != null && !insideViewPort(
						getAbsolutePosition( message.getLocation( ) ) ) ) )
		{
			Debug.exitMethod( );
			return;
		}
		messageHistory.add( message.getText( ) );
		if ( messageHistory.size( ) > 100 )
			messageHistory.removeElementAt( 0 );
		messageBox.addText( message.getText( ) );

		messageBox.draw( );
		Debug.exitMethod( );

	}

	public void chat( NPC who )
	{
		si.saveBuffer( );
		Debug.enterMethod( this, "chat", who );
		TextBox chatBox = new TextBox( si );
		chatBox.setHeight( 7 );
		chatBox.setWidth( 33 );
		chatBox.setPosition( 28, 3 );
		chatBox.setBorder( true );
		chatBox.setForeColor( ConsoleSystemInterface.WHITE );
		chatBox.setBorderColor( ConsoleSystemInterface.WHITE );
		chatBox.setText( who.getTalkMessage( ) );
		chatBox.setTitle( who.getDescription( ) );
		chatBox.draw( );
		si.refresh( );
		waitKey( );
		si.restore( );
		Debug.exitMethod( );
	}

	public void commandSelected( int commandCode )
	{
		switch ( commandCode )
		{
		case CommandListener.PROMPTQUIT:
			processQuit( );
			break;
		case CommandListener.PROMPTSAVE:
			processSave( );
			break;
		case CommandListener.HELP:
			si.saveBuffer( );
			Display.thus.showHelp( );
			si.restore( );
			break;
		case CommandListener.LOOK:
			doLook( );
			break;
		case CommandListener.SHOWSTATS:
			showPlayerStats( );
			break;
		case CommandListener.SHOWINVEN:
			try
			{
				actionSelectedByCommand = showInventory( );
			}
			catch ( ActionCancelException ace )
			{

			}
			break;
		case CommandListener.SHOWSKILLS:
			try
			{
				if ( !player.isSwimming( ) )
				{
					actionSelectedByCommand = showSkills( );
				}
				else
				{
					player.getLevel( ).addMessage( "You can't do that!" );
					throw new ActionCancelException( );
				}
			}
			catch ( ActionCancelException ace )
			{
				addMessage( new Message( "- Cancelled", player.getPosition( ) ) );
				eraseOnArrival = true;
				si.refresh( );
				actionSelectedByCommand = null;
			}
			break;
			case CommandListener.SHOWMESSAGEHISTORY:
				showMessageHistory();
				break;
			case CommandListener.SHOWMAP:
				Display.thus.showMap(level.getMapLocationKey(), level.getDescription());
				break;
			case CommandListener.SWITCHMUSIC:
				boolean enabled = Service.isEnabled();
				if (enabled) {
					showMessage("Turn off music");
					Service.stopMusic();
					Service.setEnabledMusicManager(false);
				} else {
					showMessage("Turn on music");
					Service.setEnabledMusicManager(true);
					if (!level.isDay() && level.hasNoonMusic())
						Service.playKey(level.getMusicKeyNoon());
					else
						Service.playKey(level.getMusicKeyMorning());
				}
			break;
		case EXAMINELEVELMAP:
			examineLevelMap( );
			break;
		case CommandListener.CHARDUMP:
			GameFiles.saveChardump( player );
			showMessage( "Character File Dumped." );
			break;
		}
	}

	// Interactive Methods
	public void doLook( )
	{
		Position offset = new Position( 0, 0 );
		messageBox.setForeColor( ConsoleSystemInterface.RED );
		si.saveBuffer( );
		Monster lookedMonster = null;
		while ( true )
		{
			Position browser = Position.add( player.getPosition( ), offset );
			String looked = "";
			si.restore( );
			if ( FOVMask[ PC_POS.x + offset.x ][ PC_POS.y + offset.y ] )
			{
				Cell choosen = level.getMapCell( browser );
				Feature feat = level.getFeatureAt( browser );
				Vector items = level.getItemsAt( browser );
				Item item = null;
				if ( items != null )
				{
					item = (Item) items.elementAt( 0 );
				}
				Actor actor = level.getActorAt( browser );
				if ( choosen != null )
					looked += choosen.getDescription( );
				if ( level.getBloodAt( browser ) != null )
					looked += "{bloody}";
				if ( feat != null )
					looked += ", " + feat.getDescription( );
				if ( item != null )
					if ( items.size( ) == 1 )
						looked += ", " + item.getDescription( );
					else
						looked += ", " + item.getDescription( ) + " and some items";
				if ( actor != null )
				{
					if ( actor instanceof Monster )
					{
						looked += ", " + actor.getDescription( )
								+ " ['m' for extended info]";
						lookedMonster = (Monster) actor;
					}
					else
					{
						looked += ", " + actor.getDescription( );
					}
				}
			}
			messageBox.setText( looked );
			messageBox.draw( );
			si.print( PC_POS.x + offset.x, PC_POS.y + offset.y, '_',
					ConsoleSystemInterface.RED );
			si.refresh( );
			CharKey x = new CharKey( CharKey.NONE );
			while ( x.code != CharKey.SPACE && x.code != CharKey.m
					&& x.code != CharKey.ESC && !x.isArrow( ) )
				x = si.inkey( );
			if ( x.code == CharKey.SPACE || x.code == CharKey.ESC )
			{
				si.restore( );
				break;
			}
			if ( x.code == CharKey.m )
			{
				if ( lookedMonster != null )
					Display.thus.showMonsterScreen( lookedMonster, player );
			}
			else
			{
				offset.add( Action.directionToVariation( Action.toIntDirection( x ) ) );

				if ( offset.x >= xrange )
					offset.x = xrange;
				if ( offset.x <= -xrange )
					offset.x = -xrange;
				if ( offset.y >= yrange )
					offset.y = yrange;
				if ( offset.y <= -yrange )
					offset.y = -yrange;
			}
		}
		messageBox.setText( "Look mode off" );
		refresh( );
	}

	// Drawing Methods
	public void drawEffect( Effect what )
	{
		// Debug.enterMethod(this, "drawEffect", what);
		if ( what == null )
			return;
		// drawLevel();
		if ( insideViewPort( getAbsolutePosition( what.getPosition( ) ) ) )
		{
			si.refresh( );
			si.setAutoRefresh( true );
			( (CharEffect) what ).drawEffect( this, si );
			si.setAutoRefresh( false );
		}
		// Debug.exitMethod();
	}

	// Setters
	/**
	 * Sets the object which will be informed of the player commands. this
	 * corresponds to the Game object
	 */

	// Getters

	// Smart Getters
	public Position getAbsolutePosition( Position insideLevel )
	{
		Position relative = Position.subs( insideLevel, player.getPosition( ) );
		return Position.add( PC_POS, relative );
	}

	public Vector getMessageBuffer( )
	{
		if ( messageHistory.size( ) > 20 )
			return new Vector( messageHistory.subList( messageHistory.size( ) - 21,
					messageHistory.size( ) ) );
		else
			return messageHistory;
	}

	public void init(	ConsoleSystemInterface psi, UserCommand[ ] gameCommands,
						Action target )
	{
		Debug.enterMethod( this, "init" );
		this.target = target;
		super.init( gameCommands );
		messageBox = new TextInformBox( psi );
		idList = new ListBox( psi );

		messageBox.setPosition( 1, 22 );
		messageBox.setWidth( 78 );
		messageBox.setHeight( 2 );
		messageBox.setForeColor( ConsoleSystemInterface.RED );

		persistantMessageBox = new TextBox( psi );
		persistantMessageBox.setBounds( 40, 5, 38, 14 );
		persistantMessageBox.setBorder( true );
		persistantMessageBox.setBorderColor( ConsoleSystemInterface.RED );

		persistantMessageBox.setForeColor( ConsoleSystemInterface.WHITE );
		persistantMessageBox.setTitle( "Tutorial" );

		/*
		 * monstersList.setPosition(2, 4); monstersList.setWidth(27);
		 * monstersList.setHeight(10);
		 */

		idList.setPosition( 52, 4 );
		idList.setWidth( 27 );
		idList.setHeight( 18 );
		si = psi;
		FOVMask = new boolean[ 80 ][ 25 ];
		Debug.exitMethod( );
	}

	/**
	 * Checks if the point, relative to the console coordinates, is inside the
	 * ViewPort
	 */
	public boolean insideViewPort( int x, int y )
	{
		// return (x>=VP_START.x && x <= VP_END.x && y >= VP_START.y && y <= VP_END.y);
		return ( x >= 0 && x < FOVMask.length && y >= 0 && y < FOVMask[ 0 ].length )
				&& FOVMask[ x ][ y ];
	}

	public boolean insideViewPort( Position what )
	{
		return insideViewPort( what.x, what.y );
	}

	public boolean isDisplaying( Actor who )
	{
		return insideViewPort( getAbsolutePosition( who.getPosition( ) ) );
	}

	public boolean isOnFOVMask( int x, int y )
	{
		return FOVMask[ x ][ y ];
	}

	public void launchMerchant( Merchant who )
	{
		Debug.enterMethod( this, "launchMerchant", who );
		si.saveBuffer( );

		Vector merchandise = who.getMerchandiseFor( player );
		if ( merchandise == null || merchandise.size( ) == 0 )
		{
			chat( who );
			return;
		}
		Equipment.eqMode = true;
		Item.shopMode = true;
		MenuBox menuBox = new MenuBox( si );
		menuBox.setHeight( 24 );
		menuBox.setWidth( 79 );
		menuBox.setPosition( 0, 0 );
		menuBox.setMenuItems( merchandise );
		menuBox.setPromptSize( 5 );
		menuBox.setPrompt( "Greetings " + player.getName( ) + "... I am " + who.getName( )
				+ ", the " + who.getMerchandiseTypeDesc( )
				+ " merchant. May I interest you in an item?" );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );
		while ( true )
		{
			menuBox.setTitle( who.getName( ) + " (Gold:" + player.getGold( ) + ")" );
			Item choice = (Item) menuBox.getSelection( );
			if ( choice == null )
				break;

			menuBox.setPrompt( "The " + choice.getDescription( ) + ", "
					+ choice.getShopDescription( ) + "; it costs "
					+ choice.getGoldPrice( ) + ", Do you want to buy it? (Y/n)" );
			menuBox.draw( );
			if ( prompt( ) )
				if ( player.getGold( ) >= choice.getGoldPrice( ) )
				{
					player.reduceGold( choice.getGoldPrice( ) );
					if ( player.canCarry( ) )
						player.addItem( choice );
					else
						level.addItem( player.getPosition( ), choice );
					menuBox.setPrompt( "Thanks!, May I interest you in something else?" );
				}
				else
				{
					menuBox.setPrompt( "I am afraid you don\'t have enough gold" );
				}
			else
			{
				menuBox.setPrompt( "Too bad... May I interest you in something else?" );
			}
			// menuBox.draw();
		}
		Equipment.eqMode = false;
		Item.shopMode = false;
		si.restore( );
		Debug.exitMethod( );
	}

	public void levelUp( )
	{
		showMessage( "You gained a level!, [Press Space to continue]" );
		si.waitKey( CharKey.SPACE );
		if ( player.deservesAdvancement( player.getPlayerLevel( ) ) )
		{
			Vector advancements = player.getAvailableAdvancements( );
			if ( advancements.size( ) != 0 )
			{
				Advancement playerChoice = Display.thus.showLevelUp( advancements );
				playerChoice.advance( player );
				player.getGameSessionInfo( )
						.addHistoryItem( "went for " + playerChoice.getName( ) );
			}
		}
		if ( player.deservesStatAdvancement( player.getPlayerLevel( ) ) )
		{
			Vector advancements = player.getAvailableStatAdvancements( );
			if ( advancements.size( ) != 0 )
			{
				Advancement playerChoice = Display.thus.showLevelUp( advancements );
				playerChoice.advance( player );
				player.getGameSessionInfo( )
						.addHistoryItem( "went for " + playerChoice.getName( ) );
			}
		}
		si.saveBuffer( );
		player.resetLastIncrements( );
		si.restore( );
		si.refresh( );

		/*
		 * showMessage("You gained a level!, [Press Space to continue]");
		 * si.waitKey(CharKey.SPACE); int soulOptions = 5; Vector soulIds =
		 * getLevelUpSouls(); int playerChoice = Display.thus.showLevelUp(soulIds); Item
		 * soul = ItemFactory.getItemFactory().createItem((String)soulIds.elementAt(
		 * playerChoice)); if (player.canCarry()){ player.addItem(soul); } else {
		 * player.getLevel().addItem(player.getPosition(), soul); }
		 * showMessage("You acquired a "+soul.getDescription());
		 */
	}

	public void processQuit( )
	{
		messageBox.setForeColor( ConsoleSystemInterface.RED );
		messageBox.setText(
				quitMessages[ Util.rand( 0, quitMessages.length - 1 ) ] + " (y/n)" );
		messageBox.draw( );
		si.refresh( );
		if ( prompt( ) )
		{
			messageBox.setText(
					"Go away, and let the world flood in darkness... [Press Space to continue]" );
			messageBox.draw( );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
			player.getGameSessionInfo( ).setDeathCause( GameSessionInfo.QUIT );
			player.getGameSessionInfo( ).setDeathLevel( level.getLevelNumber( ) );
			informPlayerCommand( CommandListener.QUIT );
		}
		messageBox.draw( );
		messageBox.clear( );
		si.refresh( );
	}

	public void processSave( )
	{
		if ( !player.getGame( ).canSave( ) )
		{
			level.addMessage( "You cannot save your game here!" );
			return;
		}
		messageBox.setForeColor( ConsoleSystemInterface.RED );
		messageBox.setText( "Save your game? (y/n)" );
		messageBox.draw( );
		si.refresh( );
		if ( prompt( ) )
		{
			messageBox.setText(
					"Saving... I will await your return.. [Press Space to continue]" );
			messageBox.draw( );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
			informPlayerCommand( CommandListener.SAVE );
		}
		messageBox.draw( );
		messageBox.clear( );
		si.refresh( );
	}

	public boolean prompt( )
	{
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != CharKey.Y && x.code != CharKey.y && x.code != CharKey.N
				&& x.code != CharKey.n )
			x = si.inkey( );
		return ( x.code == CharKey.Y || x.code == CharKey.y );
	}

	public boolean promptChat( NPC who )
	{
		si.saveBuffer( );
		Debug.enterMethod( this, "chat", who );
		TextBox chatBox = new TextBox( si );
		chatBox.setHeight( 7 );
		chatBox.setWidth( 33 );
		chatBox.setPosition( 28, 3 );
		chatBox.setBorder( true );
		chatBox.setForeColor( ConsoleSystemInterface.WHITE );
		chatBox.setBorderColor( ConsoleSystemInterface.WHITE );
		chatBox.setText( who.getTalkMessage( ) );
		chatBox.draw( );
		si.refresh( );
		boolean ret = prompt( );
		si.restore( );
		Debug.exitMethod( );
		return ret;

	}

	public void refresh( )
	{
		// cleanViewPort();
		drawPlayerStatus( );
		drawLevel( );
		if ( showPersistantMessageBox )
		{
			persistantMessageBox.draw( );
		}
		else
		{
			idList.draw( );
		}

		si.refresh( );
		messageBox.draw( );
		messageBox.setForeColor( ConsoleSystemInterface.DARK_RED );
		if ( !player.getFlag( "KEEPMESSAGES" ) )
			eraseOnArrival = true;

	}

	// Runnable interface
	public void run( )
	{
	}

	public Action selectCommand( CharKey input )
	{
		Debug.enterMethod( this, "selectCommand", input );
		int com = getRelatedCommand( input.code );
		informPlayerCommand( com );
		Action ret = actionSelectedByCommand;
		actionSelectedByCommand = null;
		Debug.exitMethod( ret );
		return ret;
	}

	@Override
	public void setPersistantMessage( String description )
	{
		persistantMessageBox.setText( description );
		showPersistantMessageBox = true;
	}

	public void setTargets( Action a ) throws ActionCancelException
	{
		if ( a.needsItem( ) )
			a.setItem( pickItem( a.getPromptItem( ) ) );
		if ( a.needsDirection( ) )
		{
			a.setDirection( pickDirection( a.getPromptDirection( ) ) );
		}
		if ( a.needsPosition( ) )
		{
			if ( a == target )
			{
				a.setPosition( pickPosition( a.getPromptPosition( ), CharKey.f ) );
			}
			else
			{
				a.setPosition( pickPosition( a.getPromptPosition( ), CharKey.SPACE ) );
			}
		}
		if ( a.needsEquipedItem( ) )
			a.setEquipedItem( pickEquipedItem( a.getPromptEquipedItem( ) ) );
		if ( a.needsMultiItems( ) )
		{
			a.setMultiItems( pickMultiItems( a.getPromptMultiItems( ) ) );
		}
		if ( a.needsSpirits( ) )
		{
			a.setMultiItems( pickSpirits( ) );
		}
		if ( a.needsUnderlyingItem( ) )
		{
			a.setItem( pickUnderlyingItem( a.getPrompUnderlyingItem( ) ) );
		}
	}

	public void showImportantMessage( String x )
	{
		showMessage( x );
		si.waitKey( CharKey.SPACE );
	}

	public Action showInventory( ) throws ActionCancelException
	{
		Equipment.eqMode = true;
		Vector inventory = player.getInventory( );
		int xpos = 1, ypos = 0;
		MenuBox menuBox = new MenuBox( si );
		menuBox.setHeight( 11 );
		menuBox.setWidth( 50 );
		menuBox.setPosition( 1, 8 );
		menuBox.setBorder( false );
		menuBox.setMenuItems( inventory );

		MenuBox itemUsageChoices = new MenuBox( si );
		itemUsageChoices.setHeight( 9 );
		itemUsageChoices.setWidth( 20 );
		itemUsageChoices.setPosition( 52, 15 );
		itemUsageChoices.setBorder( false );
		itemUsageChoices.setMenuItems( vecItemUsageChoices );
		itemUsageChoices.clearBox( );

		TextBox itemDescription = new TextBox( si );
		itemDescription.setBounds( 52, 9, 25, 5 );
		si.saveBuffer( );
		si.cls( );
		si.print( xpos, ypos,
				"------------------------------------------------------------------------",
				ConsoleSystemInterface.DARK_RED );
		si.print( xpos, ypos + 1, "Inventory", ConsoleSystemInterface.RED );
		si.print( xpos, ypos + 2,
				"------------------------------------------------------------------------",
				ConsoleSystemInterface.DARK_RED );
		si.print( xpos + 2, ypos + 3,
				"1. Weapon:    " + player.getEquipedWeaponDescription( ) );
		si.print( xpos + 2, ypos + 4,
				"2. Readied:   " + player.getSecondaryWeaponDescription( ) );
		si.print( xpos + 2, ypos + 5, "3. Armor:     " + player.getArmorDescription( ) );
		si.print( xpos + 2, ypos + 6, "4. Shield:    " + player.getAccDescription( ) );
		si.print( xpos, ypos + 7,
				"------------------------------------------------------------------------",
				ConsoleSystemInterface.DARK_RED );
		menuBox.draw( );
		si.print( xpos, 24, "[Space] to continue, Up and Down to browse" );
		si.refresh( );
		Item selected = null;

		Action selectedAction = null;
		do
		{
			try
			{
				Equipment eqs = (Equipment) menuBox.getSelectionAKS( additionalKeys );
				if ( eqs == null )
					break;
				selected = eqs.getItem( );
			}
			catch ( AdditionalKeysSignal aks )
			{
				switch ( aks.getKeyCode( ) )
				{
				case CharKey.N1:
					// Unequip Weapon
					if ( player.getWeapon( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getWeapon( ) );
						si.restore( );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N2:
					// Unequip Secondary Weapon
					if ( player.getSecondaryWeapon( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getSecondaryWeapon( ) );
						si.restore( );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N3:
					// Unequip Armor
					if ( player.getArmor( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getArmor( ) );
						si.restore( );
						return selectedAction;
					}
					else
					{
						continue;
					}
				case CharKey.N4:
					// Unequip Shield
					if ( player.getShield( ) != null )
					{
						selectedAction = new Unequip( );
						selectedAction.setPerformer( player );
						selectedAction.setEquipedItem( player.getShield( ) );
						si.restore( );
						return selectedAction;
					}
					else
					{
						continue;
					}
				}
			}
			if ( selected == null )
			{
				break;
			}
			si.print( 52, 8, fill( selected.getDescription( ), 25 ),
					ConsoleSystemInterface.RED );
			itemDescription.clear( );
			itemDescription.setText( selected.getDefinition( ).getMenuDescription( ) );

			itemUsageChoices.draw( );
			itemDescription.draw( );
			SimpleMenuItem choice = null;
			try
			{
				choice = (SimpleMenuItem) itemUsageChoices
						.getNullifiedSelection( itemUsageAdditionalKeys );
			}
			catch ( AdditionalKeysSignal aks )
			{
				switch ( aks.getKeyCode( ) )
				{
				case CharKey.u:
					choice = (SimpleMenuItem) vecItemUsageChoices.elementAt( 0 );
					break;
				case CharKey.e:
					choice = (SimpleMenuItem) vecItemUsageChoices.elementAt( 1 );
					break;
				case CharKey.d:
					choice = (SimpleMenuItem) vecItemUsageChoices.elementAt( 2 );
					break;
				case CharKey.t:
					choice = (SimpleMenuItem) vecItemUsageChoices.elementAt( 3 );
					break;
				}
			}

			if ( choice != null )
			{
				switch ( choice.getValue( ) )
				{
				case 1: // Use
					Use use = new Use( );
					use.setPerformer( player );
					use.setItem( selected );
					si.restore( );
					return use;
				case 2: // Equip
					Equip equip = new Equip( );
					equip.setPerformer( player );
					equip.setItem( selected );
					si.restore( );
					return equip;
				case 3: // Drop
					Drop drop = new Drop( );
					drop.setPerformer( player );
					drop.setItem( selected );
					si.restore( );
					return drop;
				case 4: // Throw
					Throw throwx = new Throw( );
					throwx.setPerformer( player );
					throwx.setItem( selected );
					si.restore( );
					throwx.setPosition( pickPosition( "Throw where?", CharKey.SPACE ) );
					return throwx;
				case 5: // Cancel

					break;
				}
			}
			si.print( 52, 8, fill( "", 25 ) );
			itemUsageChoices.clearBox( );
			itemDescription.clearBox( );

		}
		while ( selected != null );
		// si.waitKey(CharKey.SPACE);
		si.restore( );
		Equipment.eqMode = false;
		return null;
	}

	/**
	 * Shows a message inmediately; useful for system messages.
	 * 
	 * @param x
	 *            the message to be shown
	 */
	public void showMessage( String x )
	{
		messageBox.setForeColor( ConsoleSystemInterface.RED );
		messageBox.addText( x );
		messageBox.draw( );
		si.refresh( );
	}
	public void showMessageHistory( )
	{
		si.saveBuffer( );
		si.cls( );
		si.print( 1, 0, "Message Buffer", CharAppearance.DARK_RED );
		for ( int i = 0; i < 22; i++ )
		{
			if ( i >= messageHistory.size( ) )
				break;
			si.print( 1, i + 2,
					(String) messageHistory.elementAt( messageHistory.size( ) - 1 - i ),
					CharAppearance.RED );
		}

		si.print( 55, 24, "[ Space to Continue ]" );
		si.waitKey( CharKey.SPACE );
		si.restore( );
	}

	public void showPlayerStats( )
	{
		si.saveBuffer( );
		si.cls( );
		si.print( 1, 0,
				player.getName( ) + " the level " + player.getPlayerLevel( ) + " "
						+ player.getClassString( ) + " " + player.getStatusString( ),
				ConsoleSystemInterface.RED );
		si.print( 1, 1, "Sex: " + ( player.getSex( ) == Player.MALE ? "M" : "F" ) );
		si.print( 1, 2,
				"Hits: " + player.getHits( ) + "/" + player.getHitsMax( ) + " Hearts: "
						+ player.getHearts( ) + "/" + player.getHeartsMax( ) + " Gold: "
						+ player.getGold( ) + " Keys: " + player.getKeys( ) );
		si.print( 1, 3,
				"Carrying: " + player.getItemCount( ) + "/" + player.getCarryMax( ) );
		si.print( 1, 5, "Attack      +" + player.getAttack( ) );
		si.print( 1, 6, "Soul Power  +" + player.getSoulPower( ) );
		si.print( 1, 7, "Evade       " + player.getEvadeChance( ) + "%" );
		si.print( 1, 8, "Combat      " + ( 50 - player.getAttackCost( ) ) );
		si.print( 1, 9, "Invocation  " + ( 50 - player.getCastCost( ) ) );
		si.print( 1, 10, "Movement    " + ( 50 - player.getWalkCost( ) ) );

		si.print( 1, 11, "Experience  " + player.getXp( ) + "/" + player.getNextXP( ) );

		/*
		 * si.print(1,2, "Skills", ConsoleSystemInterface.RED); Vector skills =
		 * player.getAvailableSkills(); int cont = 0; for (int i = 0; i < skills.size();
		 * i++){ if (i % 10 == 0) cont++; si.print((cont-1) * 25 + 1, 3 + i - ((cont-1)
		 * * 10), ((Skill)skills.elementAt(i)).getMenuDescription()); }
		 */

		si.print( 1, 13, "Weapon Profficiences", ConsoleSystemInterface.RED );
		si.print( 1, 14, "Hand to hand             Whips                    Projectiles",
				ConsoleSystemInterface.RED );
		si.print( 1, 15, "Daggers                  Maces                    Bows/Xbows",
				ConsoleSystemInterface.RED );
		si.print( 1, 16, "Swords                   Pole                     Machinery",
				ConsoleSystemInterface.RED );
		si.print( 1, 17, "Spears                   Rings                    Shields",
				ConsoleSystemInterface.RED );

		String[ ] wskills = ItemDefinition.CATS;
		int cont = 0;
		for ( int i = 0; i < wskills.length; i++ )
		{
			if ( i % 4 == 0 )
				cont++;
			si.print( ( cont - 1 ) * 25 + 14, 14 + i - ( ( cont - 1 ) * 4 ),
					verboseSkills[ player.weaponSkill( wskills[ i ] ) ] );
		}

		si.print( 1, 19, "Attack Damage  ", ConsoleSystemInterface.RED );
		si.print( 1, 20, "Actual Defense ", ConsoleSystemInterface.RED );
		si.print( 1, 21, "Shield Rates   ", ConsoleSystemInterface.RED );

		si.print( 16, 19, "" + player.getWeaponAttack( ), ConsoleSystemInterface.WHITE );
		si.print( 16, 20,
				player.getArmorDefense( ) + ( player.getDefenseBonus( ) != 0 ? "+"
						+ player.getDefenseBonus( ) : "" ),
				ConsoleSystemInterface.WHITE );
		si.print( 16, 21,
				"Block " + player.getShieldBlockChance( ) + "% Coverage "
						+ player.getShieldCoverageChance( ) + "%",
				ConsoleSystemInterface.WHITE );

		si.print( 1, 23, "[ Press Space to continue ]" );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.restore( );
	}

	public Action showSkills( ) throws ActionCancelException
	{
		Debug.enterMethod( this, "showSkills" );
		si.saveBuffer( );
		Vector skills = player.getAvailableSkills( );
		MenuBox menuBox = new MenuBox( si );
		menuBox.setHeight( 14 );
		menuBox.setWidth( 33 );
		menuBox.setBorder( true );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setPosition( 24, 4 );
		menuBox.setMenuItems( skills );
		menuBox.setTitle( "Skills" );
		menuBox.setPromptSize( 0 );
		menuBox.draw( );
		si.refresh( );
		Skill selectedSkill = (Skill) menuBox.getSelection( );
		if ( selectedSkill == null )
		{
			si.restore( );
			Debug.exitMethod( "null" );
			return null;
		}
		si.restore( );
		if ( selectedSkill.isSymbolic( ) )
		{
			Debug.exitMethod( "null" );
			return null;
		}

		Action selectedAction = selectedSkill.getAction( );
		selectedAction.setPerformer( player );
		if ( selectedAction.canPerform( player ) )
			setTargets( selectedAction );
		else
			level.addMessage( selectedAction.getInvalidationMessage( ) );

		// si.refresh();
		Debug.exitMethod( selectedAction );
		return selectedAction;
	}

	public void showSystemMessage( String x )
	{
		messageBox.setForeColor( ConsoleSystemInterface.RED );
		messageBox.setText( x );
		messageBox.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	// IO Utility
	public void waitKey( )
	{
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code == CharKey.NONE )
			x = si.inkey( );
	}

	private void cleanViewPort( )
	{
		Debug.enterMethod( this, "cleanViewPort" );
		String spaces = "";
		for ( int i = 0; i <= VP_END.x - VP_START.x; i++ )
		{
			spaces += " ";
		}
		for ( int i = VP_START.y; i <= VP_END.y; i++ )
		{
			si.print( VP_START.x, i, spaces );
		}
		Debug.exitMethod( );
	}

	private void drawLevel( )
	{
		Debug.enterMethod( this, "drawLevel" );
		// Cell[] [] cells =
		// level.getCellsAround(player.getPosition().x,player.getPosition().y,
		// player.getPosition().z, range);
		Cell[ ][ ] rcells = level.getMemoryCellsAround( player.getPosition( ).x,
				player.getPosition( ).y, player.getPosition( ).z, xrange, yrange );
		Cell[ ][ ] vcells = level.getVisibleCellsAround( player.getPosition( ).x,
				player.getPosition( ).y, player.getPosition( ).z, xrange, yrange );

		Position runner = new Position( player.getPosition( ).x - xrange,
				player.getPosition( ).y - yrange, player.getPosition( ).z );

		for ( int x = 0; x < rcells.length; x++ )
		{
			for ( int y = 0; y < rcells[ 0 ].length; y++ )
			{
				if ( rcells[ x ][ y ] != null && !rcells[ x ][ y ].getAppearance( )
						.getID( ).equals( "NOTHING" ) )
				{
					CharAppearance app = (CharAppearance) rcells[ x ][ y ]
							.getAppearance( );
					char cellChar = app.getChar( );
					if ( level.getFrostAt( runner ) != 0 )
					{
						cellChar = '#';
					}
					// if (!level.isVisible(runner.x, runner.y))
					if ( vcells[ x ][ y ] == null )
						si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y, cellChar,
								ConsoleSystemInterface.GRAY );
				}
				else if ( vcells[ x ][ y ] == null
						|| vcells[ x ][ y ].getID( ).equals( "AIR" ) )
					si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
							CharAppearance.getVoidAppearance( ).getChar( ),
							CharAppearance.BLACK );
				runner.y++;
			}
			runner.y = player.getPosition( ).y - yrange;
			runner.x++;
		}

		runner.x = player.getPosition( ).x - xrange;
		runner.y = player.getPosition( ).y - yrange;

		monstersOnSight.removeAllElements( );
		featuresOnSight.removeAllElements( );
		itemsOnSight.removeAllElements( );

		for ( int x = 0; x < vcells.length; x++ )
		{
			for ( int y = 0; y < vcells[ 0 ].length; y++ )
			{
				FOVMask[ PC_POS.x - xrange + x ][ PC_POS.y - yrange + y ] = false;
				if ( vcells[ x ][ y ] != null )
				{
					FOVMask[ PC_POS.x - xrange + x ][ PC_POS.y - yrange + y ] = true;
					String bloodLevel = level.getBloodAt( runner );
					CharAppearance cellApp = (CharAppearance) vcells[ x ][ y ]
							.getAppearance( );
					int cellColor = cellApp.getColor( );
					if ( !level.isDay( ) )
					{
						cellColor = ConsoleSystemInterface.DARK_BLUE;
					}
					if ( bloodLevel != null )
					{
						switch ( Integer.parseInt( bloodLevel ) )
						{
						case 0:
							if ( !level.isDay( ) )
							{
								cellColor = ConsoleSystemInterface.DARK_RED;
							}
							else
							{
								cellColor = ConsoleSystemInterface.RED;
							}
							break;
						case 1:
							if ( !level.isDay( ) )
							{
								cellColor = ConsoleSystemInterface.PURPLE;
							}
							else
							{
								cellColor = ConsoleSystemInterface.DARK_RED;
							}
							break;
						case 8:
							cellColor = ConsoleSystemInterface.LEMON;
							break;
						}
					}
					if ( vcells[ x ][ y ].isWater( ) )
					{
						if ( level.canFloatUpward( runner ) )
						{
							cellColor = ConsoleSystemInterface.BLUE;
						}
						else
						{
							cellColor = ConsoleSystemInterface.DARK_BLUE;
						}
					}

					char cellChar = cellApp.getChar( );
					if ( level.getFrostAt( runner ) != 0 )
					{
						cellChar = '#';
						cellColor = ConsoleSystemInterface.CYAN;
					}
					if ( level.getDepthFromPlayer( player.getPosition( ).x - xrange + x,
							player.getPosition( ).y - yrange + y ) != 0 )
					{
						cellColor = ConsoleSystemInterface.TEAL;
					}

					if ( player.isInvisible( ) || x != xrange || y != yrange )
						si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y, cellChar,
								cellColor );
					Feature feat = level.getFeatureAt( runner );
					if ( feat != null )
					{
						if ( feat.isVisible( ) )
						{
							BasicListItem li = (BasicListItem) sightListItems
									.get( feat.getID( ) );
							if ( li == null )
							{
								Debug.say(
										"Adding " + feat.getID( ) + " to the hashtable" );
								sightListItems.put( feat.getID( ), new BasicListItem(
										( (CharAppearance) feat.getAppearance( ) )
												.getChar( ),
										( (CharAppearance) feat.getAppearance( ) )
												.getColor( ),
										feat.getDescription( ) ) );
								li = (BasicListItem) sightListItems.get( feat.getID( ) );
							}
							if ( feat.isRelevant( ) && !featuresOnSight.contains( li ) )
								featuresOnSight.add( li );
							CharAppearance featApp = (CharAppearance) feat
									.getAppearance( );
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
									featApp.getChar( ), featApp.getColor( ) );
						}
					}

					SmartFeature sfeat = level.getSmartFeature( runner );
					if ( sfeat != null )
					{
						if ( sfeat.isVisible( ) )
						{
							CharAppearance featApp = (CharAppearance) sfeat
									.getAppearance( );
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
									featApp.getChar( ), featApp.getColor( ) );
						}
					}

					Vector items = level.getItemsAt( runner );
					Item item = null;
					if ( items != null )
					{
						item = (Item) items.elementAt( 0 );
					}
					if ( item != null )
					{
						if ( item.isVisible( ) )
						{
							CharAppearance itemApp = (CharAppearance) item
									.getAppearance( );
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
									itemApp.getChar( ), itemApp.getColor( ) );
							BasicListItem li = (BasicListItem) sightListItems
									.get( item.getDefinition( ).getID( ) );
							if ( li == null )
							{
								// Debug.say("Adding "+item.getDefinition().getID()+" to
								// the hashtable");
								sightListItems.put( item.getDefinition( ).getID( ),
										new BasicListItem(
												( (CharAppearance) item.getAppearance( ) )
														.getChar( ),
												( (CharAppearance) item.getAppearance( ) )
														.getColor( ),
												item.getDefinition( )
														.getDescription( ) ) );
								li = (BasicListItem) sightListItems
										.get( item.getDefinition( ).getID( ) );
							}
							if ( !itemsOnSight.contains( li ) )
								itemsOnSight.add( li );
						}
					}

					Monster monster = level.getMonsterAt( runner );
					if ( monster != null && monster.isVisible( ) )
					{
						BasicListItem li = null;
						if ( monster instanceof NPC )
						{
							li = (BasicListItem) sightListItems
									.get( ( (NPC) monster ).getDescription( ) );
							if ( li == null )
							{
								CharAppearance monsterApp = (CharAppearance) monster
										.getAppearance( );
								Debug.say( "Adding " + monster.getID( )
										+ " to the hashtable" );
								sightListItems.put( ( (NPC) monster ).getDescription( ),
										new BasicListItem( monsterApp.getChar( ),
												monsterApp.getColor( ),
												monster.getDescription( ) ) );
								li = (BasicListItem) sightListItems
										.get( ( (NPC) monster ).getDescription( ) );
							}
						}
						else
						{
							li = (BasicListItem) sightListItems.get( monster.getID( ) );
							if ( li == null )
							{
								CharAppearance monsterApp = (CharAppearance) monster
										.getAppearance( );
								Debug.say( "Adding " + monster.getID( )
										+ " to the hashtable" );
								sightListItems.put( monster.getID( ),
										new BasicListItem( monsterApp.getChar( ),
												monsterApp.getColor( ),
												monster.getDescription( ) ) );
								li = (BasicListItem) sightListItems
										.get( monster.getID( ) );
							}
						}
						if ( !monstersOnSight.contains( li ) )
							monstersOnSight.add( li );

						CharAppearance monsterApp = (CharAppearance) monster
								.getAppearance( );
						if ( monster.canSwim( ) && level.getMapCell( runner ) != null
								&& level.getMapCell( runner ).isShallowWater( ) )
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y, '~',
									monsterApp.getColor( ) );
						else if ( monster.hasCounter( Consts.C_MONSTER_FREEZE ) )
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
									monsterApp.getChar( ), ConsoleSystemInterface.CYAN );
						else
							si.print( PC_POS.x - xrange + x, PC_POS.y - yrange + y,
									monsterApp.getChar( ), monsterApp.getColor( ) );
					}

					if ( !player.isInvisible( ) )
					{
						si.print( PC_POS.x, PC_POS.y,
								( (CharAppearance) player.getAppearance( ) ).getChar( ),
								( (CharAppearance) player.getAppearance( ) )
										.getColor( ) );
					}
					else
					{

						si.print( PC_POS.x, PC_POS.y,
								( (CharAppearance) AppearanceFactory
										.getAppearanceFactory( )
										.getAppearance( "SHADOW" ) ).getChar( ),
								( (CharAppearance) AppearanceFactory
										.getAppearanceFactory( )
										.getAppearance( "SHADOW" ) ).getColor( ) );
					}

				}
				runner.y++;
			}
			runner.y = player.getPosition( ).y - yrange;
			runner.x++;
		}

		/*
		 * monstersList.clear(); itemsList.clear();
		 */
		idList.clear( );
		if ( player.hasHostage( ) )
		{
			BasicListItem li = (BasicListItem) sightListItems
					.get( ( (NPC) player.getHostage( ) ).getDescription( ) );
			if ( li == null )
			{
				CharAppearance hostageApp = (CharAppearance) player.getHostage( )
						.getAppearance( );
				Debug.say( "Adding " + hostageApp.getID( ) + " to the hashtable" );
				sightListItems.put( player.getHostage( ).getDescription( ),
						new BasicListItem( hostageApp.getChar( ), hostageApp.getColor( ),
								player.getHostage( ).getDescription( ) ) );
				li = (BasicListItem) sightListItems
						.get( ( (NPC) player.getHostage( ) ).getDescription( ) );
			}
			idList.addElement( li );
		}
		idList.addElements( monstersOnSight );
		idList.addElements( itemsOnSight );
		idList.addElements( featuresOnSight );

		Debug.exitMethod( );
	}

	private void drawLineTo( int x, int y, char icon, int color )
	{
		Position target = new Position( x, y );
		Line line = new Line( PC_POS, target );
		Position tmp = line.next( );
		while ( !tmp.equals( target ) )
		{
			tmp = line.next( );
			si.print( tmp.x, tmp.y, icon, color );
		}

	}

	private void drawPlayerStatus( )
	{
		Debug.enterMethod( this, "drawPlayerStatus" );
		int foreColor;
		int backColor;
		switch ( ( ( player.getHits( ) - 1 ) / 20 ) + 1 )
		{
		case 1:
			foreColor = ConsoleSystemInterface.RED;
			backColor = ConsoleSystemInterface.WHITE;
			break;
		case 2:
			foreColor = ConsoleSystemInterface.DARK_RED;
			backColor = ConsoleSystemInterface.RED;
			break;
		default:
			foreColor = ConsoleSystemInterface.MAGENTA;
			backColor = ConsoleSystemInterface.DARK_RED;
			break;
		}
		String timeTile = "";
		int timeColor = ConsoleSystemInterface.YELLOW;
		switch ( level.getDayTime( ) )
		{
		case Level.MORNING:
			timeTile = "O__";
			timeColor = ConsoleSystemInterface.BROWN;
			break;
		case Level.NOON:
			timeTile = "_O_";
			timeColor = ConsoleSystemInterface.YELLOW;
			break;
		case Level.AFTERNOON:
			timeTile = "__O";
			timeColor = ConsoleSystemInterface.RED;
			break;
		case Level.DUSK:
			timeTile = "(__";
			timeColor = ConsoleSystemInterface.BLUE;
			break;
		case Level.NIGHT:
			timeTile = "_O_";
			timeColor = ConsoleSystemInterface.BLUE;
			break;
		case Level.DAWN:
			timeTile = "__)";
			timeColor = ConsoleSystemInterface.BLUE;
			break;
		}

		String shot = "   ";
		if ( player.getShotLevel( ) == 1 )
			shot = "II ";
		if ( player.getShotLevel( ) == 2 )
			shot = "III";
		int rest = ( ( player.getHits( ) - 1 ) % 20 ) + 1;

		si.print( 0, 0, "SCORE    " + player.getScore( ) );
		si.print( 0, 1, "PLAYER   " );

		for ( int i = 0; i < 20; i++ )
			if ( i + 1 <= rest )
				si.print( i + 9, 1, 'I', foreColor );
			else
				si.print( i + 9, 1, 'I', backColor );

		si.print( 0, 2, "ENEMY    " );
		if ( player.getLevel( ).getBoss( ) != null )
		{
			int sixthiedBossHits = (int) Math
					.ceil( ( player.getLevel( ).getBoss( ).getHits( ) * 60.0 )
							/ (double) player.getLevel( ).getBoss( ).getMaxHits( ) );
			int foreColorB = 0;
			int backColorB = 0;
			switch ( ( ( sixthiedBossHits - 1 ) / 20 ) + 1 )
			{
			case 1:
				foreColorB = ConsoleSystemInterface.YELLOW;
				backColorB = ConsoleSystemInterface.WHITE;
				break;
			case 2:
				foreColorB = ConsoleSystemInterface.BROWN;
				backColorB = ConsoleSystemInterface.YELLOW;
				break;
			default:
				foreColorB = ConsoleSystemInterface.PURPLE;
				backColorB = ConsoleSystemInterface.BROWN;
				break;
			}

			int restB = ( ( sixthiedBossHits - 1 ) % 20 ) + 1;

			for ( int i = 0; i < 20; i++ )
				if ( i + 1 <= restB )
					si.print( i + 9, 2, 'I', foreColorB );
				else
					si.print( i + 9, 2, 'I', backColorB );
		}
		else
			si.print( 9, 2, "IIIIIIIIIIIIIIIIIIII", ConsoleSystemInterface.WHITE );

		si.print( 31, 2, fill( player.getWeaponDescription( ) + " " + shot, 40 ) );

		if ( player.getLevel( ).getLevelNumber( ) == -1 )
			si.print( 43, 0, fill( player.getLevel( ).getDescription( ), 35 ) );
		else
			si.print( 43, 0, fill( "STAGE   " + player.getLevel( ).getLevelNumber( ) + " "
					+ player.getLevel( ).getDescription( ), 35 ) );

		// si.print(43+"STAGE ".length(),0);

		si.print( 31, 1, "v       ", ConsoleSystemInterface.RED );
		si.print( 33, 1, "- " + player.getHearts( ) );
		si.print( 39, 1, "k     ", ConsoleSystemInterface.YELLOW );
		si.print( 41, 1, "- " + player.getKeys( ) );

		si.print( 47, 1, "$            ", ConsoleSystemInterface.YELLOW );
		si.print( 49, 1, "- " + player.getGold( ) );

		si.print( 60, 1, "TIME - " );
		si.print( 67, 1, timeTile, timeColor );

		si.print( 71, 1, "     ", ConsoleSystemInterface.WHITE );
		if ( player.getFlag( Consts.ENV_FOG ) )
			si.print( 71, 1, "FOG", ConsoleSystemInterface.TEAL );
		if ( player.getFlag( Consts.ENV_RAIN ) )
			si.print( 71, 1, "RAIN", ConsoleSystemInterface.BLUE );
		if ( player.getFlag( Consts.ENV_SUNNY ) )
			si.print( 71, 1, "SUNNY", ConsoleSystemInterface.YELLOW );
		if ( player.getFlag( Consts.ENV_THUNDERSTORM ) )
			si.print( 71, 1, "STORM", ConsoleSystemInterface.WHITE );
		// si.print (71,2,"P - 0");

		// si.print(1,24, " ");
		si.print( 1, 24,
				fill( player.getName( ) + ", the Lv" + player.getPlayerLevel( ) + " "
						+ player.getClassString( ) + " " + player.getStatusString( ),
						70 ) );

		Debug.exitMethod( );
	}

	private void examineLevelMap( )
	{
		si.saveBuffer( );
		si.cls( );
		int lw = level.getWidth( );
		int lh = level.getHeight( );
		int remnantx = (int) ( ( 80 - ( lw ) ) / 2.0d );
		// int remnanty = (int)((25 - (lh))/2.0d);
		int pages = (int) ( ( lh - 1 ) / 23 ) + 1;
		int cellColor = 0;
		Position runner = new Position( 0, 0, player.getPosition( ).z );
		for ( int i = 1; i <= pages; i++ )
		{
			si.cls( );
			for ( int ii = 0; ii < 23; ii++ )
			{
				int y = ( i - 1 ) * 23 + ii;
				if ( y >= level.getHeight( ) )
					break;
				runner.y = y;
				runner.x = 0;
				for ( int x = 0; x < level.getWidth( ); x++, runner.x++ )
				{
					if ( !level.remembers( x, y ) )
						cellColor = ConsoleSystemInterface.BLACK;
					else
					{
						Cell current = level.getMapCell( x, y, player.getPosition( ).z );
						Feature currentF = level.getFeatureAt( x, y,
								player.getPosition( ).z );
						if ( level.isVisible( x, y ) )
						{
							if ( current == null )
								cellColor = ConsoleSystemInterface.BLACK;
							else if ( level.getExitOn( runner ) != null )
								cellColor = ConsoleSystemInterface.RED;
							else if ( current.isSolid( )
									|| ( currentF != null && currentF.isSolid( ) ) )
								cellColor = ConsoleSystemInterface.BROWN;
							else
								cellColor = ConsoleSystemInterface.LIGHT_GRAY;

						}
						else
						{
							if ( current == null )
								cellColor = ConsoleSystemInterface.BLACK;
							else if ( level.getExitOn( runner ) != null )
								cellColor = ConsoleSystemInterface.RED;
							else if ( current.isSolid( )
									|| ( currentF != null && currentF.isSolid( ) ) )
								cellColor = ConsoleSystemInterface.BROWN;
							else
								cellColor = ConsoleSystemInterface.GRAY;
						}
						if ( player.getPosition( ).x == x
								&& player.getPosition( ).y == y )
							cellColor = ConsoleSystemInterface.RED;
					}
					si.safeprint( remnantx + x, ii, '.', cellColor );

				}

			}
			si.print( 5, 24, "Page " + i, ConsoleSystemInterface.RED );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
		}

		si.restore( );
		si.refresh( );

	}

	private String fill( String str, int limit )
	{
		if ( str.length( ) > limit )
			return str.substring( 0, limit );
		else
			return str + spaces( limit - str.length( ) );
	}

	private Position getNearestMonsterPosition( )
	{
		VMonster monsters = level.getMonsters( );
		Monster nearMonster = null;
		int minDist = 150;
		int maxDist = 15;
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			Monster monster = (Monster) monsters.elementAt( i );
			if ( monster.getPosition( ).z( ) != level.getPlayer( ).getPosition( ).z( ) )
				continue;
			int distance = Position.flatDistance( level.getPlayer( ).getPosition( ),
					monster.getPosition( ) );
			if ( distance < maxDist && distance < minDist && player.sees( monster ) )
			{
				minDist = distance;
				nearMonster = monster;
			}
		}
		if ( nearMonster != null )
			return nearMonster.getPosition( );
		else
			return null;
	}

	private int pickDirection( String prompt ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickDirection" );
		// refresh();
		messageBox.setText( prompt );
		messageBox.draw( );
		si.refresh( );
		// refresh();

		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code == CharKey.NONE )
			x = si.inkey( );
		if ( x.isArrow( ) || x.code == CharKey.N5 )
		{
			int ret = Action.toIntDirection( x );
			Debug.exitMethod( ret );
			return ret;
		}
		else
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			si.refresh( );
			throw ret;
		}
	}

	/*
	 * private boolean cheatConsole(CharKey input){ switch (input.code){ case
	 * CharKey.F2: player.addHearts(5);
	 * player.informPlayerEvent(Player.EVT_LEVELUP); break; case CharKey.F3:
	 * player.setInvincible(250); player.setInvisible(20); break; case CharKey.F4:
	 * String nextLevel = level.getMetaData().getExit("_NEXT");
	 * player.informPlayerEvent(Player.EVT_GOTO_LEVEL, nextLevel); break; case
	 * CharKey.F5: player.heal(); break; case CharKey.F6: if
	 * (player.getLevel().getBoss() != null) player.getLevel().getBoss().damage(15);
	 * break; case CharKey.F7:
	 * player.getLevel().setIsDay(!player.getLevel().isDay()); break; case
	 * CharKey.F8: //player.informPlayerEvent(Player.EVT_BACK_LEVEL); break;
	 * default: return false; } return true; }
	 */

	private Item pickEquipedItem( String prompt ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickEquipedItem" );
		Vector equipped = new Vector( );
		if ( player.getArmor( ) != null )
			equipped.add( player.getArmor( ) );
		if ( player.getWeapon( ) != null )
			equipped.add( player.getWeapon( ) );
		if ( player.getShield( ) != null )
			equipped.add( player.getShield( ) );
		MenuBox menuBox = new MenuBox( si );
		// menuBox.setBounds(26,6,30,11);
		menuBox.setBounds( 10, 3, 60, 18 );
		menuBox.setPromptSize( 2 );
		menuBox.setMenuItems( equipped );
		menuBox.setPrompt( prompt );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );
		si.saveBuffer( );
		menuBox.draw( );
		Item equiped = (Item) menuBox.getSelection( );
		si.restore( );
		if ( equiped == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			throw ret;
		}
		return equiped;
	}

	private Item pickItem( String prompt ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickItem" );
		Vector inventory = player.getInventory( );
		MenuBox menuBox = new MenuBox( si );
		menuBox.setBounds( 10, 3, 60, 18 );
		menuBox.setPromptSize( 2 );
		menuBox.setMenuItems( inventory );
		menuBox.setPrompt( prompt );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );
		si.saveBuffer( );
		menuBox.draw( );
		Equipment equipment = (Equipment) menuBox.getSelection( );
		si.restore( );
		if ( equipment == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			throw ret;
		}
		return equipment.getItem( );
	}

	private Vector pickMultiItems( String prompt )
	{
		Equipment.eqMode = true;
		Vector inventory = player.getInventory( );
		MenuBox menuBox = new MenuBox( si );
		menuBox.setBounds( 25, 3, 40, 18 );
		menuBox.setPromptSize( 2 );
		menuBox.setMenuItems( inventory );
		menuBox.setPrompt( prompt );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );
		Vector ret = new Vector( );
		MenuBox selectedBox = new MenuBox( si );
		selectedBox.setBounds( 5, 3, 20, 18 );
		selectedBox.setPromptSize( 2 );
		selectedBox.setPrompt( "Selected Items" );
		selectedBox.setMenuItems( ret );
		selectedBox.setForeColor( ConsoleSystemInterface.RED );
		selectedBox.setBorder( true );

		si.saveBuffer( );

		while ( true )
		{
			selectedBox.draw( );
			menuBox.draw( );

			Equipment equipment = (Equipment) menuBox.getSelection( );
			if ( equipment == null )
				break;
			if ( !ret.contains( equipment.getItem( ) ) )
				ret.add( equipment.getItem( ) );
		}
		si.restore( );
		Equipment.eqMode = false;
		return ret;
	}

	private Position pickPosition(	String prompt,
									int fireKeyCode ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickPosition" );
		messageBox.setForeColor( ConsoleSystemInterface.BLUE );
		messageBox.setText( prompt );
		messageBox.draw( );
		si.refresh( );
		si.saveBuffer( );

		Position defaultTarget = null;
		Position nearest = getNearestMonsterPosition( );
		if ( nearest != null )
		{
			defaultTarget = nearest;
		}
		else
		{
			defaultTarget = null;
		}

		Position browser = null;
		Position offset = new Position( 0, 0 );
		if ( lockedMonster != null )
		{
			if ( !player.sees( lockedMonster ) || lockedMonster.isDead( ) )
				lockedMonster = null;
			else
				defaultTarget = new Position( lockedMonster.getPosition( ) );
		}
		if ( !insideViewPort( PC_POS.x + offset.x, PC_POS.y + offset.y ) )
		{
			offset = new Position( 0, 0 );
		}

		if ( defaultTarget == null )
		{
			offset = new Position( 0, 0 );
		}
		else
		{
			offset = new Position( defaultTarget.x - player.getPosition( ).x,
					defaultTarget.y - player.getPosition( ).y );
		}
		while ( true )
		{
			si.restore( );
			String looked = "";
			browser = Position.add( player.getPosition( ), offset );

			/*
			 * if (PC_POS.x + offset.x < 0 || PC_POS.x + offset.x >= FOVMask.length ||
			 * PC_POS.y + offset.y < 0 || PC_POS.y + offset.y >=FOVMask[0].length){ offset
			 * = new Position (0,0); browser = Position.add(player.getPosition(), offset);
			 * }
			 */

			if ( FOVMask[ PC_POS.x + offset.x ][ PC_POS.y + offset.y ] )
			{
				Cell choosen = level.getMapCell( browser );
				Feature feat = level.getFeatureAt( browser );
				Vector items = level.getItemsAt( browser );
				Item item = null;
				if ( items != null )
				{
					item = (Item) items.elementAt( 0 );
				}
				Actor actor = level.getActorAt( browser );
				si.restore( );

				if ( choosen != null )
					looked += choosen.getDescription( );
				if ( level.getBloodAt( browser ) != null )
					looked += "{bloody}";
				if ( feat != null )
					looked += ", " + feat.getDescription( );
				if ( actor != null )
					looked += ", " + actor.getDescription( );
				if ( item != null )
					looked += ", " + item.getDescription( );
			}
			messageBox.setText( prompt + " " + looked );
			messageBox.draw( );
			// si.print(PC_POS.x + offset.x, PC_POS.y + offset.y, '_',
			// ConsoleSystemInterface.BLUE);
			drawLineTo( PC_POS.x + offset.x, PC_POS.y + offset.y, '*',
					ConsoleSystemInterface.DARK_BLUE );
			si.print( PC_POS.x + offset.x, PC_POS.y + offset.y, 'X',
					ConsoleSystemInterface.BLUE );
			si.refresh( );
			CharKey x = new CharKey( CharKey.NONE );
			while ( x.code != CharKey.SPACE && x.code != CharKey.ESC
					&& x.code != fireKeyCode && !x.isArrow( ) )
				x = si.inkey( );
			if ( x.code == CharKey.ESC )
			{
				si.restore( );
				throw new ActionCancelException( );
			}
			if ( x.code == CharKey.SPACE || x.code == fireKeyCode )
			{
				si.restore( );
				if ( level.getMonsterAt( browser ) != null )
					lockedMonster = level.getMonsterAt( browser );
				return browser;
			}
			offset.add( Action.directionToVariation( Action.toIntDirection( x ) ) );

			if ( offset.x >= xrange )
				offset.x = xrange;
			if ( offset.x <= -xrange )
				offset.x = -xrange;
			if ( offset.y >= yrange )
				offset.y = yrange;
			if ( offset.y <= -yrange )
				offset.y = -yrange;
		}

	}

	private Vector pickSpirits( )
	{
		Vector originalInventory = player.getInventory( );
		Vector inventory = new Vector( );
		for ( int i = 0; i < originalInventory.size( ); i++ )
		{
			Equipment testEq = (Equipment) originalInventory.elementAt( i );
			if ( testEq.getItem( ).getDefinition( ).getID( ).endsWith( "_SPIRIT" ) )
			{
				inventory.add( testEq );
			}
		}

		MenuBox menuBox = new MenuBox( si );
		menuBox.setBounds( 25, 3, 40, 18 );
		menuBox.setPromptSize( 2 );
		menuBox.setMenuItems( inventory );
		menuBox.setPrompt( "Select the spirits to fusion" );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );

		Vector ret = new Vector( );
		MenuBox selectedBox = new MenuBox( si );
		selectedBox.setBounds( 5, 3, 20, 18 );
		selectedBox.setPromptSize( 2 );
		selectedBox.setPrompt( "Selected Spirits" );
		selectedBox.setMenuItems( ret );
		selectedBox.setForeColor( ConsoleSystemInterface.RED );
		selectedBox.setBorder( true );

		si.saveBuffer( );

		while ( true )
		{
			selectedBox.draw( );
			menuBox.draw( );

			Equipment equipment = (Equipment) menuBox.getSelection( );
			if ( equipment == null )
				break;
			if ( !ret.contains( equipment.getItem( ) ) )
				ret.add( equipment.getItem( ) );
		}
		si.restore( );
		return ret;
	}

	private Item pickUnderlyingItem( String prompt ) throws ActionCancelException
	{
		Debug.enterMethod( this, "pickUnderlyingItem" );
		Vector items = level.getItemsAt( player.getPosition( ) );
		if ( items == null )
			return null;
		if ( items.size( ) == 1 )
			return (Item) items.elementAt( 0 );
		MenuBox menuBox = new MenuBox( si );
		menuBox.setBounds( 10, 3, 60, 18 );
		menuBox.setPromptSize( 2 );
		menuBox.setMenuItems( items );
		menuBox.setPrompt( prompt );
		menuBox.setForeColor( ConsoleSystemInterface.RED );
		menuBox.setBorder( true );
		si.saveBuffer( );
		menuBox.draw( );
		Item item = (Item) menuBox.getSelection( );
		si.restore( );
		if ( item == null )
		{
			ActionCancelException ret = new ActionCancelException( );
			Debug.exitExceptionally( ret );
			throw ret;
		}
		return item;
	}

	private String spaces( int n )
	{
		String ret = (String) hashSpaces.get( n + "" );
		if ( ret != null )
			return ret;
		ret = "";
		for ( int i = 0; i < n; i++ )
			ret += " ";
		hashSpaces.put( n + "", ret );
		return ret;
	}
}