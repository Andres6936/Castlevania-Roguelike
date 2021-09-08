package co.castle;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

import co.castle.game.*;
import co.castle.main.Service;
import co.castle.scene.SceneManager;
import co.castle.system.FileLoader;
import co.castle.ui.Display;

public final class Main
{
	private static Game currentGame;

	private static Hashtable <?, ?> monsterRecord;

	/**
	 * Provide functionality to the application, loading and starting modules
	 */
	private static Service app;

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

    public static void main( String[] args) {
		var sceneManager = new SceneManager();
		while (sceneManager.isRunning()) {
			sceneManager.update();
			sceneManager.draw();
			sceneManager.process();
		}

		// Lazy evaluation of Service
		app = new Service();
//
//		try {
//			title();
//		} catch (Exception e) {
//			System.out.println("Unrecoverable Exception, Game cann't start.\n");
//		}
	}

    private static void setMonsterRecord( Hashtable< ?, ? > monsterRecord )
	{
		Main.monsterRecord = monsterRecord;
	}

	private static void arena( )
	{
		if (currentGame != null) {
			app.userInterface.removeCommandListener(currentGame);
		}
		currentGame = new Game();
		currentGame.setCanSave(false);
		currentGame.setInterfaces(app.userInterface, app.uiSelector);
		setMonsterRecord(GameFiles.getMonsterRecord());
		// si.cls();
		currentGame.arena();
		title();
	}

	private static void loadGame( )
	{
		File saveDirectory = FileLoader.getResourceFile("savegame");
		File[] saves = saveDirectory.listFiles((dir, name) -> name.endsWith(".sav"));

		int index = Display.thus.showSavedGames(saves);
		if (index == -1)
			title();
		try {
			assert saves != null;
			ObjectInputStream ois = new ObjectInputStream(
					FileLoader.getFileInputStream(saves[index]));
			currentGame = (Game) ois.readObject();
			ois.close( );
		} catch (IOException ioe) {

			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			crash("Invalid savefile or wrong version",
					new CRLException("Invalid savefile or wrong version"));
		}
		currentGame.setInterfaces(app.userInterface, app.uiSelector);
		if (currentGame.getPlayer().getLevel() == null) {
			crash("Player wasnt loaded", new Exception("Player wasnt loaded"));
		}
		currentGame.setPlayer(currentGame.getPlayer());
		app.userInterface.setPlayer(currentGame.getPlayer());
		app.uiSelector.setPlayer(currentGame.getPlayer());
		setMonsterRecord(GameFiles.getMonsterRecord());
		currentGame.resume();

		title();
	}

	private static void newGame( ) {
		if (currentGame != null) {
			app.userInterface.removeCommandListener(currentGame);
		}
		currentGame = new Game();
		currentGame.setCanSave(true);
		currentGame.setInterfaces(app.userInterface, app.uiSelector);
		setMonsterRecord(GameFiles.getMonsterRecord());
		currentGame.newGame();

		title();
	}

	private static void prologue( ) {
		if (currentGame != null) {
			app.userInterface.removeCommandListener(currentGame);
		}
		currentGame = new Game();
		currentGame.setCanSave(false);
		currentGame.setInterfaces(app.userInterface, app.uiSelector);
		// si.cls();
		setMonsterRecord(GameFiles.getMonsterRecord());
		currentGame.prologue();
		title();
	}

	private static void title( ) {
		MusicManager.playKey("TITLE");

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
			// HighScoreScene
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

	private static void training( ) {
		if (currentGame != null) {
			app.userInterface.removeCommandListener(currentGame);
		}

		currentGame = new Game();
		currentGame.setCanSave(false);
		currentGame.setInterfaces(app.userInterface, app.uiSelector);
		// si.cls();
		setMonsterRecord(GameFiles.getMonsterRecord());
		currentGame.training();
		title();
	}

}