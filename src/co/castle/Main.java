package co.castle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

import co.castle.game.CRLException;
import co.castle.game.Game;
import co.castle.game.GameFiles;
import co.castle.game.MonsterRecord;
import co.castle.main.Service;
import co.castle.ui.Display;

public final class Main
{
	private static Game currentGame;

	private final static byte JCURSES_CONSOLE = 0;
	private final static byte SWING_GFX = 1;
	private final static byte SWING_CONSOLE = 2;

	private static Hashtable <?, ?> monsterRecord;

	/**
	 * Provide functionality to the application, loading and starting modules
	 */
	private static final Service app = Service.getInstance();

	public static void crash( String message, Throwable exception )
	{
		System.out.println( "CastlevaniaRL " + Game.getVersion( ) + ": Error" );
        System.out.println( );
		System.out.println( "Unrecoverable error: " + message );
		exception.printStackTrace( );
		if ( currentGame != null )
		{
			System.out.println( "Trying to save game" );
			GameFiles.saveGame( currentGame, currentGame.getPlayer( ) );
		}
		System.exit( -1 );
	}

	public static Hashtable <?, ?> getMonsterRecord( )
	{
		return monsterRecord;
	}

	public static MonsterRecord getMonsterRecordFor( String monsterID )
	{
		return (MonsterRecord) monsterRecord.get( monsterID );
	}

    public static void main( String[] args )
	{
		// ¿Exist arguments?
		if ( args.length > 0 )
		{
			if ( args[ 0 ].equalsIgnoreCase( "sgfx" ) )
			{
				app.start( SWING_GFX );
			}
			else if ( args[ 0 ].equalsIgnoreCase( "jc" ) )
			{
				app.start( JCURSES_CONSOLE );
			}
			else if ( args[ 0 ].equalsIgnoreCase( "sc" ) )
			{
				app.start( SWING_CONSOLE );
			}
		}
		else
		{
			// Initiation for defect in mode graphics
			app.start( SWING_GFX );
		}

		System.out.println( "Launching game" );

		try
		{
			title( );
		}
		catch ( Exception e )
		{
			System.out.println( "Unrecoverable Exception, Game cann't start.\n" );
		}
	}

    private static void setMonsterRecord( Hashtable< ?, ? > monsterRecord )
	{
		Main.monsterRecord = monsterRecord;
	}

	private static void arena( )
	{
		if ( currentGame != null )
		{
			app.ui.removeCommandListener( currentGame );
		}
		currentGame = new Game( );
		currentGame.setCanSave( false );
		currentGame.setInterfaces( app.ui, app.uiSelector );
		setMonsterRecord( GameFiles.getMonsterRecord( ) );
		// si.cls();
		currentGame.arena( );
		title( );
	}

	private static void loadGame( )
	{
		File saveDirectory = new File( "savegame" );
		File[ ] saves = saveDirectory.listFiles( new SaveGameFilenameFilter( ) );

		int index = Display.thus.showSavedGames( saves );
		if ( index == -1 )
			title( );
		try
		{
            assert saves != null;
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream( saves[ index ] ) );
			currentGame = (Game) ois.readObject( );
			ois.close( );
		}
		catch ( IOException ioe )
		{

			ioe.printStackTrace( );
		}
		catch ( ClassNotFoundException cnfe )
		{
			crash( "Invalid savefile or wrong version",
					new CRLException( "Invalid savefile or wrong version" ) );
		}
		currentGame.setInterfaces( app.ui, app.uiSelector );
		if ( currentGame.getPlayer( ).getLevel( ) == null )
		{
			crash( "Player wasnt loaded", new Exception( "Player wasnt loaded" ) );
		}
		currentGame.setPlayer( currentGame.getPlayer( ) );
		app.ui.setPlayer( currentGame.getPlayer( ) );
		app.uiSelector.setPlayer( currentGame.getPlayer( ) );
		setMonsterRecord( GameFiles.getMonsterRecord( ) );
		currentGame.resume( );

		title( );
	}

	private static void newGame( )
	{
		if ( currentGame != null )
		{
			app.ui.removeCommandListener( currentGame );
		}
		currentGame = new Game( );
		currentGame.setCanSave( true );
		currentGame.setInterfaces( app.ui, app.uiSelector );
		setMonsterRecord( GameFiles.getMonsterRecord( ) );
		currentGame.newGame( );

		title( );
	}

	private static void prologue( )
	{
		if ( currentGame != null )
		{
			app.ui.removeCommandListener( currentGame );
		}
		currentGame = new Game( );
		currentGame.setCanSave( false );
		currentGame.setInterfaces( app.ui, app.uiSelector );
		// si.cls();
		setMonsterRecord( GameFiles.getMonsterRecord( ) );
		currentGame.prologue( );
		title( );
	}

	private static void title( )
	{
		app.playKey("TITLE");

		int choice = Display.thus.showTitleScreen();

		switch (choice) {
			case 0:
				newGame();
				break;
			case 1:
				loadGame();
			break;
		case 2:
			prologue( );
			break;
		case 3:
			training( );
			break;
		case 4:
			arena( );
			break;
		case 5:
			Display.thus.showHiscores( GameFiles.loadScores( "hiscore.tbl" ) );
			Display.thus.showHiscores( GameFiles.loadScores( "arena.tbl" ) );
			title( );
			break;

		case 6:
			System.out.println( "CastlevaniaRL v" + Game.getVersion( ) + ", clean Exit" );
			System.out.println( "Thank you for playing!" );
			System.exit( 0 );
			break;

		}

	}

	private static void training( )
	{
		if ( currentGame != null )
		{
			app.ui.removeCommandListener( currentGame );
		}

		currentGame = new Game( );
		currentGame.setCanSave( false );
		currentGame.setInterfaces( app.ui, app.uiSelector );
		// si.cls();
		setMonsterRecord( GameFiles.getMonsterRecord( ) );
		currentGame.training( );
		title( );
	}

}

class SaveGameFilenameFilter implements FilenameFilter
{

	public boolean accept( File arg0, String arg1 )
	{
		// if (arg0.getName().endsWith(".sav"))
        return arg1.endsWith( ".sav" );
	}

}