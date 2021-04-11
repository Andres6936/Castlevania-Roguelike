package co.castle.ui;

import java.util.Hashtable;
import java.util.Vector;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.actor.Message;
import co.castle.item.Merchant;
import co.castle.level.Level;
import co.castle.npc.NPC;
import co.castle.player.Player;
import co.castle.ui.effects.Effect;
import sz.util.Debug;

/**
 * Shows the level Informs the Actions and Commands of the player. Must be
 * listening to a System Interface
 */
public abstract class UserInterface implements CommandListener
{
	private Vector <CommandListener> commandListeners = new Vector <CommandListener>( 5 );

	private boolean[ ][ ] FOVMask;
	private boolean gameOver;
	protected Action actionSelectedByCommand;
	protected boolean eraseOnArrival; // Erase the buffer upon the arrival of a new msg

	// Components

	protected Vector featuresOnSight = new Vector( );

	protected Hashtable gameCommands = new Hashtable( );
	protected Vector itemsOnSight = new Vector( );
	protected String lastMessage;

	protected Level level;

	// Smart Getters

	// Final attributes

	// Status
	protected Vector monstersOnSight = new Vector( );

	// Relations
	protected Player player;

	// Attributes
	// private String[] quitMessages;
	protected String[ ] quitMessages = new String[ ]
	{	"Do you really want to abandon Transylvania?",
		"Quit now, and let the evil count roam the world free?",
		"Leave now, and lose this unique chance to fight for freedom?",
		"Abandon the people of Transylvania?", "Deceive everybody who trusted you?",
		"Throw your weapons away and live a 'peaceful' life?"

	};

	public final static String verboseSkills[] = new String[ ]
	{	"Unskilled", "Mediocre(1)", "Mediocre(2)", "Mediocre(3)", "Trained(1)",
		"Trained(2)", "Trained(3)", "Skilled(1)", "Skilled(2)", "Skilled(3)", "Master" };

	// Singleton
	private static UserInterface singleton;

	public static UserInterface getUI( )
	{
		return singleton;
	}

	public static void setSingleton( UserInterface ui )
	{
		singleton = ui;
	}

	public void addCommandListener( CommandListener pCl )
	{
		commandListeners.add( pCl );
	}

	public abstract void addMessage( Message message );

	public abstract void chat( NPC who );

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
			Display.thus.showHelp( );
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
				}
			}
			catch ( ActionCancelException ace )
			{

			}
			break;
		}
	}

	// Interactive Methods
	public abstract void doLook( );

	// Drawing Methods
	public abstract void drawEffect( Effect what );

	public boolean gameOver( )
	{
		return gameOver;
	}

	public abstract Vector getMessageBuffer( );

	// Setters
	/**
	 * Sets the object which will be informed of the player commands. this
	 * corresponds to the Game object
	 */

	// Getters
	public Player getPlayer( )
	{
		return player;
	}

	public void init( UserCommand[ ] gameCommands )
	{
		// uiSelector = selector;
		FOVMask = new boolean[ 80 ][ 25 ];
		for ( int i = 0; i < gameCommands.length; i++ )
			this.gameCommands.put( gameCommands[ i ].getKeyCode( ) + "",
					gameCommands[ i ] );
		addCommandListener( this );
	}

	public abstract boolean isDisplaying( Actor who );

	public boolean isOnFOVMask( int x, int y )
	{
		return FOVMask[ x ][ y ];
	}
	public abstract void launchMerchant( Merchant who );

	public void levelChange( )
	{
		level = player.getLevel( );
	}

	/* Shows a level was won, lets pick a random spirit */
	public abstract void levelUp( );

	public abstract void processQuit( );

	public abstract void processSave( );

	/**
	 * Prompts for Yes or NO
	 */
	public abstract boolean prompt( );

	public abstract boolean promptChat( NPC who );

	public abstract void refresh( );

	public void removeCommandListener( CommandListener pCl )
	{
		commandListeners.remove( pCl );
	}

	public void setGameOver( boolean bal )
	{

		gameOver = bal;
	}

	public abstract void setPersistantMessage( String description );

	public void setPlayer( Player pPlayer )
	{
		player = pPlayer;
		level = player.getLevel( );
	}

	public abstract void setTargets( Action a ) throws ActionCancelException;

	public abstract void showImportantMessage( String x );

	public abstract Action showInventory( ) throws ActionCancelException;

	/**
	 * Shows a message inmediately; useful for system messages.
	 * 
	 * @param x
	 *            the message to be shown
	 */
	public abstract void showMessage( String x );

	public abstract void showMessageHistory( );

	public abstract void showPlayerStats( );

	public abstract Action showSkills( ) throws ActionCancelException;

	/**
	 * Shows a message inmediately; useful for system messages. Waits for a key
	 * press or something.
	 * 
	 * @param x
	 *            the message to be shown
	 */
	public abstract void showSystemMessage( String x );

	protected int getRelatedCommand( int keyCode )
	{
		Debug.enterMethod( this, "getRelatedCommand", keyCode + "" );
		UserCommand uc = (UserCommand) gameCommands.get( keyCode + "" );
		if ( uc == null )
		{
			Debug.exitMethod( CommandListener.NONE );
			return CommandListener.NONE;
		}

		int ret = uc.getCommand( );
		Debug.exitMethod( ret + "" );
		return ret;
	}

	protected void informPlayerCommand( int command )
	{
		Debug.enterMethod( this, "informPlayerCommand", command + "" );
		for ( int i = 0; i < commandListeners.size( ); i++ )
		{
			( (CommandListener) commandListeners.elementAt( i ) )
					.commandSelected( command );
		}
		Debug.exitMethod( );
	}
}