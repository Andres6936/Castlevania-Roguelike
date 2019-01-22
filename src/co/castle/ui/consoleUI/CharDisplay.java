package co.castle.ui.consoleUI;

import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.Main;
import co.castle.conf.console.data.CharCuts;
import co.castle.game.Game;
import co.castle.game.MonsterRecord;
import co.castle.main.Service;
import co.castle.monster.Monster;
import co.castle.npc.Hostage;
import co.castle.player.GameSessionInfo;
import co.castle.player.HiScore;
import co.castle.player.Player;
import co.castle.player.advancements.Advancement;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import co.castle.ui.consoleUI.cuts.CharChat;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.csi.textcomponents.TextBox;
import sz.util.Position;
import sz.util.ScriptUtil;

public class CharDisplay extends Display
{
	private Hashtable locationKeys;

	private ConsoleSystemInterface si;

	String[ ] mapImage = new String[ ]
	{	"                                                                                ",
		" ''`.--..,''`_,''`.-''----.----..'     '`''''..,'''-'    `_,,'''`--- ./  ,'-.   ",
		" '                                                 /\\                   |,. `.  ",
		"  |                                               /  \\                    `.... ",
		"  |                                              | /-\\|      /'\\              | ",
		"  |                                               \\| |\\    .'   |             | ",
		"  |                            O    /\\             \\-/ \\   . /-\\`             | ",
		"  |                                |  |            `.===``/==| | \\           ,  ",
		" .\"                                |/-\\_              `===== \\-/  `.          \\ ",
		" |                                 `| |==.../-\\       .'      =    |          | ",
		" |                                 /\\-/ ====| ||  ,-.'.       ==   /         /  ",
		" \\.                                | =/-\\   \\-/| |   | '|     /-\\ /           \\ ",
		"  |                               .' =| |=   = | |   '..'     | | |           / ",
		"  |            ,-.     ..--.      |.  \\-/=   = |  |           \\-/  \\         |  ",
		"  |          _/-\\|.  ,/-\\  `./-\\   /-\\  /-\\  =  \\ |            =   |.        <. ",
		"  :|      ,,' | |=====| |====| |===| |==| |  ==  \\ `'\\.        =    `.        | ",
		"  |    _,'    \\-/     \\-/    \\-/   \\-/  \\-/   =   |/-\\|        =     |        | ",
		"   |.-'                                      /-\\ ==| |===  /-\\ =     `.      |  ",
		" .-' ''`''-.    ,'`..,''''''`.               | |== \\-/  ===| |==       ``.   `. ",
		" |.         ---'             `._,..,_        \\-/  ,..      \\-/   _  __   ___. | ",
		"  '                                  `.     .-''''   \\.   _....'' `'  \\''    ./ ",
		"  |                                    `_,.-          '`--'                  `. ",
		"  | ..           _                      .                                     | ",
		" |../'....,'-.../ ``...,''`--....''`..,' `\"-..,''`....,`...-'..''......'`...,-' ",
		"                                                                                " };

	{
		locationKeys = new Hashtable( );
		locationKeys.put( "TOWN", new Position( 15, 15 ) );
		locationKeys.put( "FOREST", new Position( 23, 15 ) );
		locationKeys.put( "BRIDGE", new Position( 30, 15 ) );
		locationKeys.put( "ENTRANCE", new Position( 36, 15 ) );
		locationKeys.put( "HALL", new Position( 41, 15 ) );
		locationKeys.put( "LAB", new Position( 39, 12 ) );
		locationKeys.put( "CHAPEL", new Position( 37, 9 ) );
		locationKeys.put( "RUINS", new Position( 45, 10 ) );
		locationKeys.put( "CAVES", new Position( 46, 18 ) );
		locationKeys.put( "VILLA", new Position( 52, 17 ) );
		locationKeys.put( "COURTYARD", new Position( 52, 17 ) );
		locationKeys.put( "DUNGEON", new Position( 60, 18 ) );
		locationKeys.put( "STORAGE", new Position( 63, 12 ) );
		locationKeys.put( "CLOCKTOWER", new Position( 62, 7 ) );
		locationKeys.put( "KEEP", new Position( 52, 5 ) );
	}

	public CharDisplay( ConsoleSystemInterface si )
	{
		this.si = si;
	}

	public void init( ConsoleSystemInterface syst )
	{
		si = syst;
	}

	public void printBars( )
	{
		si.print( 0, 0,
				"[==============================================================================]",
				ConsoleSystemInterface.WHITE );
		si.print( 0, 1,
				"  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]",
				ConsoleSystemInterface.WHITE );

		si.print( 0, 23,
				"  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]  [==]",
				ConsoleSystemInterface.WHITE );
		si.print( 0, 24,
				"[==============================================================================]",
				ConsoleSystemInterface.WHITE );
	}

	public void showBoxedMessage( String title, String msg, int x, int y, int w, int h )
	{
		TextBox tb = new TextBox( si );
		tb.setBounds( x, y, w, h );
		tb.setBorder( true );
		tb.setText( msg );
		tb.setTitle( title );
		tb.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	public void showChat( String chatID, Game game )
	{
		si.saveBuffer( );
		CharChat chat = CharCuts.thus.get( chatID );
		TextBox tb = new TextBox( si );
		tb.setBounds( 3, 4, 40, 10 );
		tb.setBorder( true );
		String[ ] marks = new String[ ]
		{ "%NAME", "%%INTRO_1", "%%CLARA1" };
		String[ ] replacements = new String[ ]
		{	game.getPlayer( ).getName( ), game.getPlayer( ).getCustomMessage( "INTRO_1" ),
			game.getPlayer( ).getCustomMessage( "CLARA1" ) };
		for ( int i = 0; i < chat.getConversations( ); i++ )
		{
			tb.clear( );
			tb.setText( ScriptUtil.replace( marks, replacements,
					chat.getConversation( i ) ) );
			tb.setTitle( ScriptUtil.replace( marks, replacements, chat.getName( i ) ) );
			tb.draw( );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
		}
		si.restore( );
	}

	public void showDraculaSequence( )
	{
		TextBox tb = new TextBox( si );
		tb.setBounds( 3, 4, 40, 10 );
		tb.setBorder( true );
		tb.setText(
				"Ahhh... a human... the first one to get this far. HAHAHAHA! Now it is time to die!" );
		tb.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	public void showEndgame( Player player )
	{
		si.cls( );
		printBars( );
		String heshe = ( player.getSex( ) == Player.MALE ? "he" : "she" );
		String hisher = ( player.getSex( ) == Player.MALE ? "his" : "her" );

		si.print( 2, 3, "                           ", ConsoleSystemInterface.RED );

		TextBox tb = new TextBox( si );
		tb.setPosition( 2, 5 );
		tb.setHeight( 8 );
		tb.setWidth( 76 );
		tb.setForeColor( ConsoleSystemInterface.RED );

		tb.setText( player.getName( )
				+ " made many sacrifices, but now the long fight is over. Dracula is dead "
				+ "and all other spirits are asleep. In the shadows, a person watches the castle fall. "
				+ player.getName( ) + " must go for now but " + heshe + " hopes someday "
				+ heshe + " will get the " + "respect that " + heshe
				+ " deserves.    After this fight the new Belmont name shall be honored "
				+ "by all people." );
		tb.draw( );
		si.print( 2, 15, "You played the greatest role in this history... ",
				ConsoleSystemInterface.RED );
		si.print( 2, 16, "Thank you for playing.", ConsoleSystemInterface.RED );

		si.print( 2, 17, "CastlevaniaRL: v" + Game.getVersion( ),
				ConsoleSystemInterface.RED );
		si.print( 2, 18, "Santiago Zapata 2005-2007", ConsoleSystemInterface.RED );

		si.print( 2, 20, "[Press Space]", ConsoleSystemInterface.BLUE );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.cls( );

	}

	public void showHelp( )
	{
		si.cls( );
		// printBars();
		si.print( 1, 1,
				"                              * - HELP - *                                      ",
				ConsoleSystemInterface.RED );

		si.print( 3, 3,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "WEAPON_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 4,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "ATTACK1_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 5,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "DROP_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 6,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "EQUIP_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 7,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "TARGET_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 8,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "GET_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 9,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "JUMP_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 10,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "DIVE_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 11,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "RELOAD_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 12,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SHOW_SKILLS_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 13,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "THROW_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 14,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "USE_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 15,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "UNEQUIP_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 3, 16,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SWITCH_WEAPONS_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );

		si.print( 6, 3, "Action: Action: Aim special weapon",
				ConsoleSystemInterface.WHITE );
		si.print( 6, 4, "Attack: Uses a weapon", ConsoleSystemInterface.WHITE );
		si.print( 6, 5, "Drop: Drops an item", ConsoleSystemInterface.WHITE );
		si.print( 6, 6, "Equip: Wears equipment", ConsoleSystemInterface.WHITE );
		si.print( 6, 7, "Fire: Aims a ranged weapon", ConsoleSystemInterface.WHITE );
		si.print( 6, 8, "Get: Picks up an item", ConsoleSystemInterface.WHITE );
		si.print( 6, 9, "Jump: Jumps in a direction", ConsoleSystemInterface.WHITE );
		si.print( 6, 10, "Plunge: Dive into the water", ConsoleSystemInterface.WHITE );
		si.print( 6, 11, "Reload: Reloads a given weapon", ConsoleSystemInterface.WHITE );
		si.print( 6, 12, "Skills: Use character skills", ConsoleSystemInterface.WHITE );
		si.print( 6, 13, "Throw: Throws an Item", ConsoleSystemInterface.WHITE );
		si.print( 6, 14, "Use: Uses an Item", ConsoleSystemInterface.WHITE );
		si.print( 6, 15, "Unequip: Take off an item", ConsoleSystemInterface.WHITE );
		si.print( 6, 16, "Switch: Exchange primary weapon",
				ConsoleSystemInterface.WHITE );

		si.print( 41, 3,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SHOW_STATS_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 4,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SHOW_INVENTORY_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 5,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "LOOK_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 6,
				"(" + CharKey.getString( Display.getKeyBindings( )
						.getProperty( "SHOW_MESSAGE_HISTORY_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 7,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SHOW_MAP_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 8,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "EXAMINE_LEVEL_MAP_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 9,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "QUIT_KEY" ) ) + ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 10,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "PROMPT_SAVE_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );
		si.print( 41, 11,
				"(" + CharKey.getString(
						Display.getKeyBindings( ).getProperty( "SWITCH_MUSIC_KEY" ) )
						+ ")",
				ConsoleSystemInterface.RED );

		si.print( 44, 3, "Character: Skills and attributes",
				ConsoleSystemInterface.WHITE );
		si.print( 44, 4, "Inventory: Shows the inventory", ConsoleSystemInterface.WHITE );
		si.print( 44, 5, "Look: Identifies map symbols", ConsoleSystemInterface.WHITE );
		si.print( 44, 6, "Messages: Shows the latest messages",
				ConsoleSystemInterface.WHITE );
		si.print( 44, 7, "Castle Map: Shows the castle map",
				ConsoleSystemInterface.WHITE );
		si.print( 44, 8, "Area Map: Show the current area map",
				ConsoleSystemInterface.WHITE );
		si.print( 44, 9, "Quit: Exits game", ConsoleSystemInterface.WHITE );
		si.print( 44, 10, "Save: Saves game", ConsoleSystemInterface.WHITE );
		si.print( 44, 11, "Switch Music: Turns music on/off",
				ConsoleSystemInterface.WHITE );

		si.print( 6, 18, "[ Press space to exit ]", ConsoleSystemInterface.WHITE );

		/*
		 * si.print(0,1,
		 * "                                                                                "
		 * ); si.print(0,2,
		 * "                                                                                "
		 * ); si.print(0,3,
		 * "                              * - HELP - *                                      "
		 * , ConsoleSystemInterface.RED); si.print(0,4,
		 * "                                                                                "
		 * ); si.print(0,5,
		 * "                                                                                "
		 * ); si.print(0,6,
		 * "        -- ACTIONS --                             -- MOVEMENT --                "
		 * , ConsoleSystemInterface.RED); si.print(0,7,
		 * "                                                                      7 8 9     "
		 * ); si.print(0,8,
		 * "  ( ) Action: Aim mystic or ranged weapon                              \\|/      "
		 * ); si.print(0,9,
		 * "  (a) Attack: Uses a weapon                     Arrow Keys or Numpad  4-@-6     "
		 * ); si.print(0,10,
		 * "  (d) Drop: Drops an item                                              /|\\      "
		 * ); si.print(0,11,
		 * "  (D) Dive: Dives into deep water                                     1 2 3     "
		 * ); si.print(0,12,
		 * "                                                  -- COMMANDS --                "
		 * , ConsoleSystemInterface.RED); si.print(0,12,
		 * "  (e) Equip: Wears equipment               "); si.print(0,13,
		 * "  (f) Fire: Aims a ranged weapon                                                "
		 * ); si.print(0,14,
		 * "  (g) Get: Picks up an item                (c) Character info: Player attributes"
		 * ); si.print(0,15,
		 * "  (j) Jump: Jumps, emerge, fly             (i) Inventory: Shows the inventory   "
		 * ); si.print(0,16,
		 * "  (r) Reload: Reloads a given weapon       (l) Look: Identifies map symbols 	"
		 * ); si.print(0,17,
		 * "  (s) Skills: Use character skills         (m) Messages: Latest messages        "
		 * ); si.print(0,18,
		 * "  (t) Throw: Throws an Item                (M) Map: Shows the castle map        "
		 * ); si.print(0,19,
		 * "  (u) Use: Uses an Item                    (Q) Quit: Exits game                 "
		 * ); si.print(0,19,
		 * "  (U) Unequip: Take off an item            (S) Save: Saves game                 "
		 * ); si.print(0,20,
		 * "  (x) Switch: Primary for secondary weapon (T) Switch music: Turns on/off music "
		 * ); si.print(0,21,
		 * "                                                                                "
		 * ); si.print(0,22,
		 * "                                                                                "
		 * ); si.print(0,23,
		 * "                                            [ Press Space to Continue ]         "
		 * , ConsoleSystemInterface.RED); si.print(0,24,
		 * "                                                                                "
		 * );
		 */
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	public void showHiscores( HiScore[ ] scores )
	{
		si.cls( );

		si.print( 2, 1, "                      Castlevania RL " + Game.getVersion( ),
				ConsoleSystemInterface.RED );
		si.print( 2, 2, "                  ~ The most brave of Belmonts ~",
				ConsoleSystemInterface.RED );

		si.print( 13, 4, "Score" );
		// si.print(21,4, "Score");
		si.print( 25, 4, "Date" );
		si.print( 36, 4, "Turns" );
		si.print( 43, 4, "Death" );

		for ( int i = 0; i < scores.length; i++ )
		{
			si.print( 2, 5 + i, scores[ i ].getName( ), ConsoleSystemInterface.BLUE );
			si.print( 21, 5 + i, "" + scores[ i ].getPlayerClass( ) );
			si.print( 13, 5 + i, "" + scores[ i ].getScore( ) );
			si.print( 25, 5 + i, "" + scores[ i ].getDate( ) );
			si.print( 36, 5 + i, "" + scores[ i ].getTurns( ) );
			si.print( 43, 5 + i, ( "" + scores[ i ].getDeathString( ) + " on level "
					+ scores[ i ].getDeathLevel( ) ) );

		}
		si.print( 2, 23, "[Press Space]" );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.cls( );
	}

	public void showHostageRescue( Hostage h )
	{
		TextBox tb = new TextBox( si );
		tb.setBounds( 3, 4, 30, 10 );
		tb.setBorder( true );

		String text = "Thanks for rescuing me! I will give you " + h.getReward( )
				+ " gold, it is all I have!";
		if ( h.getItemReward( ) != null )
			text += "\n\n\nTake this, the " + h.getItemReward( ).getDescription( )
					+ ", I found it inside the castle ";
		tb.setText( text );
		tb.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	public void showIntro( Player player )
	{
		si.cls( );
		printBars( );
		si.print( 32, 2, "Prologue", ConsoleSystemInterface.DARK_RED );

		TextBox tb1 = new TextBox( si );
		tb1.setPosition( 2, 4 );
		tb1.setHeight( 3 );
		tb1.setWidth( 76 );
		tb1.setForeColor( ConsoleSystemInterface.RED );
		tb1.setText(
				"In the year of 1691, a dark castle emerges from the cursed soils of the plains of Transylvannia."
						+ " Chaos and death spread along the land, as the evil count Dracula unleases his powers, turning it into a pool of blood" );

		TextBox tb2 = new TextBox( si );
		tb2.setPosition( 2, 8 );
		tb2.setHeight( 4 );
		tb2.setWidth( 76 );
		tb2.setForeColor( ConsoleSystemInterface.RED );
		tb2.setText(
				"The trip to the castle was long and harsh, after enduring many challenges through all Transylvannia, "
						+ "you are close to the castle of chaos. You are almost at Castlevania, and you are here on business: "
						+ "To destroy forever the Curse of the Evil Count." );

		TextBox tb = new TextBox( si );
		tb.setPosition( 2, 13 );
		tb.setHeight( 4 );
		tb.setWidth( 76 );
		tb.setForeColor( ConsoleSystemInterface.RED );
		tb.setText( player.getPlot( ) + ", " + player.getDescription( )
				+ " journeys to the cursed castle." );
		tb1.draw( );
		tb2.draw( );
		tb.draw( );
		si.print( 2, 18, "[Press Space]", ConsoleSystemInterface.BLUE );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.cls( );
	}

	public Advancement showLevelUp( Vector advancements )
	{

		si.saveBuffer( );
		si.cls( );
		si.print( 1, 1, "You have gained a chance to pick an advancement!",
				ConsoleSystemInterface.BLUE );

		for ( int i = 0; i < advancements.size( ); i++ )
		{
			si.print( 1, 3 + i * 2, ( (char) ( 'a' + i ) ) + ". "
					+ ( (Advancement) advancements.elementAt( i ) ).getName( ) );
			si.print( 1, 4 + i * 2,
					( (Advancement) advancements.elementAt( i ) ).getDescription( ) );
		}
		si.refresh( );
		int choice = readAlphaToNumber( advancements.size( ) );
		si.restore( );
		return (Advancement) advancements.elementAt( choice );
		/*
		 * ItemDefinition[] defs = new ItemDefinition[soulIds.size()]; for (int i = 0; i
		 * < defs.length; i++){ defs[i] =
		 * ItemFactory.getItemFactory().getDefinition((String)soulIds.elementAt(i)); }
		 * si.cls(); printBars(); si.print(2,3, " - - Level Up - -",
		 * ConsoleSystemInterface.RED); si.print(2,5,
		 * "Please pick a spiritual memento"); for (int i = 0; i < defs.length; i++){
		 * si.print(2,7+i, (char)('a'+i) + ") ", ConsoleSystemInterface.WHITE);
		 * si.print(5,7+i, ((CharAppearance)defs[i].getAppearance()).getChar(),
		 * ((CharAppearance)defs[i].getAppearance()).getColor()); si.print(7,7+i,
		 * defs[i].getDescription() + ": " + defs[i].getMenuDescription()); }
		 * si.refresh(); int choice = readAlphaToNumber(defs.length); si.cls(); return
		 * choice;
		 */
	}

	public void showMap( String locationKey, String locationDescription )
	{
		si.saveBuffer( );
		for ( int i = 0; i < 25; i++ )
		{
			si.print( 0, i, mapImage[ i ], CharAppearance.BROWN );
		}
		si.print( 15, 11, locationDescription );
		if ( locationKey != null )
		{
			Position location = (Position) locationKeys.get( locationKey );
			if ( location != null )
				si.print( location.x, location.y, "X", CharAppearance.RED );
		}
		si.waitKey( CharKey.SPACE );
		si.restore( );
	}

	public void showMonsterScreen( Monster who, Player player )
	{
		CharAppearance app = (CharAppearance) who.getAppearance( );
		si.cls( );
		si.print( 6, 3, who.getDescription( ), ConsoleSystemInterface.RED );
		si.print( 4, 3, app.getChar( ), app.getColor( ) );

		TextBox tb = new TextBox( si );
		tb.setPosition( 3, 5 );
		tb.setHeight( 8 );
		tb.setWidth( 70 );
		tb.setForeColor( ConsoleSystemInterface.WHITE );
		if ( who.getLongDescription( ) != null )
			tb.setText( who.getLongDescription( ) );
		tb.draw( );

		MonsterRecord record = Main.getMonsterRecordFor( who.getID( ) );
		long baseKilled = 0;
		long baseKillers = 0;
		if ( record != null )
		{
			baseKilled = record.getKilled( );
			baseKillers = record.getKillers( );
		}
		si.print( 2, 17, "You have killed "
				+ ( baseKilled + player.getGameSessionInfo( ).getDeathCountFor( who ) )
				+ " " + who.getDescription( ) + "s", ConsoleSystemInterface.WHITE );
		if ( baseKillers == 0 )
		{
			si.print( 2, 18, "No " + who.getDescription( ) + "s have killed you",
					ConsoleSystemInterface.WHITE );
		}
		else
		{
			si.print( 2, 18, "You have been killed by " + baseKillers + " "
					+ who.getDescription( ) + "s", ConsoleSystemInterface.WHITE );
		}
		si.print( 2, 20, "[Press Space]", ConsoleSystemInterface.WHITE );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.restore( );
		si.refresh( );
	}

	public boolean showResumeScreen( Player player )
	{
		GameSessionInfo gsi = player.getGameSessionInfo( );
		si.cls( );
		printBars( );
		String heshe = ( player.getSex( ) == Player.MALE ? "He" : "She" );

		si.print( 2, 3, "The chronicles of " + player.getName( ),
				ConsoleSystemInterface.RED );

		TextBox tb = new TextBox( si );
		tb.setPosition( 2, 5 );
		tb.setHeight( 3 );
		tb.setWidth( 70 );
		tb.setForeColor( ConsoleSystemInterface.RED );
		tb.setText( "  ...And so it was that " + player.getDescription( ) + ", "
				+ gsi.getDeathString( ) + " on the "
				+ player.getLevel( ).getDescription( ) + "..." );
		tb.draw( );

		si.print( 2, 9, heshe + " scored " + player.getScore( ) + " points and earned "
				+ player.getGold( ) + " gold", ConsoleSystemInterface.RED );
		si.print( 2, 10, heshe + " survived for " + gsi.getTurns( ) + " turns ",
				ConsoleSystemInterface.RED );

		si.print( 2, 11, heshe + " took " + gsi.getTotalDeathCount( )
				+ " monsters to the other world", ConsoleSystemInterface.RED );
		/*
		 * int i = 0; Enumeration monsters = gsi.getDeathCount().elements(); while
		 * (monsters.hasMoreElements()){ MonsterDeath mons = (MonsterDeath)
		 * monsters.nextElement(); si.print(5,11+i, mons.getTimes()
		 * +" "+mons.getMonsterDescription(), ConsoleSystemInterface.RED); i++; }
		 */ si.print( 2, 14, "Do you want to save your character memorial? [Y/N]" );
		si.refresh( );
		return UserInterface.getUI( ).prompt( );
	}

	public int showSavedGames( File[ ] saveFiles )
	{
		si.cls( );
		printBars( );
		if ( saveFiles == null || saveFiles.length == 0 )
		{
			si.print( 3, 6, "No adventurers available" );
			si.print( 4, 8, "[Space to Cancel]" );
			si.refresh( );
			si.waitKey( CharKey.SPACE );
			return -1;
		}

		si.print( 3, 6, "Pick an adventurer" );
		for ( int i = 0; i < saveFiles.length; i++ )
		{
			String saveFileName = saveFiles[ i ].getName( );
			si.print( 5, 7 + i, (char) ( CharKey.a + i + 1 ) + " - "
					+ saveFileName.substring( 0, saveFileName.indexOf( ".sav" ) ) );
		}
		si.print( 3, 9 + saveFiles.length, "[Space to Cancel]" );
		si.refresh( );
		CharKey x = si.inkey( );
		while ( ( x.code < CharKey.a || x.code > CharKey.a + saveFiles.length )
				&& x.code != CharKey.SPACE )
		{
			x = si.inkey( );
		}
		si.cls( );
		if ( x.code == CharKey.SPACE )
			return -1;
		else
			return x.code - CharKey.a;
	}
	public void showScreen( Object pScreen )
	{
		si.saveBuffer( );
		String screenText = (String) pScreen;
		TextBox tb = new TextBox( si );
		tb.setBounds( 3, 4, 50, 18 );
		tb.setBorder( true );
		tb.setText( screenText );
		tb.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
		si.restore( );
	}

	public void showTimeChange(	boolean day, boolean fog, boolean rain,
								boolean thunderstorm, boolean sunnyDay )
	{
		String baseMessage = getTimeChangeMessage( day, fog, rain, thunderstorm,
				sunnyDay );
		TextBox tb = new TextBox( si );
		tb.setBounds( 3, 4, 30, 10 );
		tb.setBorder( true );
		tb.setText( baseMessage );
		tb.draw( );
		si.refresh( );
		si.waitKey( CharKey.SPACE );
	}

	public int showTitleScreen( )
	{
		( (ConsoleUserInterface) UserInterface
				.getUI( ) ).showPersistantMessageBox = false;
		si.cls( );
		printBars( );
		// Brahms Castle
		int castlex = 35;
		int castley = 5;
		si.print( castlex, castley + 0, "                   /\\",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 1, "                  |  |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 2, "                  |  |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 3, "                  \\  / .",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 4, "                   || / \\",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 5, "                  / , | |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 6, "                  | \\/' |.:",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 7, "                  ',      |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 8, "                   |      |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 9, "                  /      <",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 10, "                  |       |",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 11, "                 ,'       `\\",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 12, "               _.|          \\__",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 13, "__....__     ,'                `'--.",
				ConsoleSystemInterface.BROWN );
		si.print( castlex, castley + 14, "        `''''                       `''",
				ConsoleSystemInterface.BROWN );
		si.print( 20, 12, "a. New Game", ConsoleSystemInterface.WHITE );
		si.print( 20, 13, "b. Journey Onward", ConsoleSystemInterface.WHITE );
		si.print( 20, 14, "c. View Prologue", ConsoleSystemInterface.WHITE );
		si.print( 20, 15, "d. Training", ConsoleSystemInterface.WHITE );
		si.print( 20, 16, "e. Prelude Arena", ConsoleSystemInterface.WHITE );
		si.print( 20, 17, "f. Show HiScores", ConsoleSystemInterface.WHITE );
		si.print( 20, 18, "g. Quit", ConsoleSystemInterface.WHITE );

		// CRL Logo
		int logox = 20;
		int logoy = 4;
		si.print( logox + 2, logoy, " /-  -----------\\", ConsoleSystemInterface.RED );
		si.print( logox + 2, logoy + 1, "<                >",
				ConsoleSystemInterface.RED );
		si.print( logox + 2, logoy + 2, " \\-  -----------/",
				ConsoleSystemInterface.RED );
		si.print( logox + 5, logoy, "/\\", ConsoleSystemInterface.YELLOW );
		si.print( logox + 5, logoy + 1, "|astlevaniaRL", ConsoleSystemInterface.YELLOW );
		si.print( logox + 5, logoy + 2, "\\/", ConsoleSystemInterface.YELLOW );
		String messageX = "'CastleVania' is a trademark of Konami Corporation.";
		si.print( ( 80 - messageX.length( ) ) / 2, 20, messageX,
				ConsoleSystemInterface.DARK_RED );
		messageX = "CastlevaniaRL v" + Game.getVersion( )
				+ ", Developed by Santiago Zapata 2005-2010";
		si.print( ( 80 - messageX.length( ) ) / 2, 21, messageX,
				ConsoleSystemInterface.WHITE );
		messageX = "Midi Tracks by Jorge E. Fuentes, JiLost, Nicholas and Tom Kim";
		si.print( ( 80 - messageX.length( ) ) / 2, 22, messageX,
				ConsoleSystemInterface.WHITE );

		si.refresh( );
		Service.musicManager.playKey( "TITLE" );
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != CharKey.A && x.code != CharKey.a && x.code != CharKey.B
				&& x.code != CharKey.b && x.code != CharKey.C && x.code != CharKey.c
				&& x.code != CharKey.E && x.code != CharKey.e && x.code != CharKey.D
				&& x.code != CharKey.d && x.code != CharKey.G && x.code != CharKey.g
				&& x.code != CharKey.F && x.code != CharKey.f )
			x = si.inkey( );
		si.cls( );
		switch ( x.code )
		{
		case CharKey.A:
		case CharKey.a:
			return 0;
		case CharKey.B:
		case CharKey.b:
			return 1;
		case CharKey.C:
		case CharKey.c:
			return 2;
		case CharKey.D:
		case CharKey.d:
			return 3;
		case CharKey.E:
		case CharKey.e:
			return 4;
		case CharKey.F:
		case CharKey.f:
			return 5;
		case CharKey.G:
		case CharKey.g:
			return 6;
		}
		return 0;

	}

	private int readAlphaToNumber( int numbers )
	{
		while ( true )
		{
			CharKey key = si.inkey( );
			if ( key.code >= CharKey.A && key.code <= CharKey.A + numbers - 1 )
			{
				return key.code - CharKey.A;
			}
			if ( key.code >= CharKey.a && key.code <= CharKey.a + numbers - 1 )
			{
				return key.code - CharKey.a;
			}
		}
	}
}
