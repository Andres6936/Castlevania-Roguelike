package co.castle.game;

import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.CommandListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import sz.fov.FOV;

import java.util.Vector;

public class Game implements CommandListener, PlayerEventListener, java.io.Serializable {
	private boolean canSave;
	private Level currentLevel;

	private Dispatcher dispatcher;

	private Player player;

	private long turns;
	// Configuration
	private transient UserInterface ui;
	private transient UISelector uiSelector;

	public final static int DAY_LENGTH = 500;

	private static Vector<String> uniqueRegister = new Vector<>();

	public static String getVersion() {
		return "0.73";
	}

	public static void registerUniqueGenerated(String itemID)
	{
		uniqueRegister.add( itemID );
	}

	public static boolean wasUniqueGenerated( String itemID )
	{
		return uniqueRegister.contains( itemID );
	}

	public boolean canSave( )
	{
		return canSave;
	}

	public void commandSelected( int commandCode )
	{

	}

	public void exitGame( )
	{
		// levelNumber = -1;
		currentLevel.disableTriggers( );
		currentLevel = null;
		ui.removeCommandListener( this );
		ui.setGameOver( true );
		player.setPlayerEventListener( null );

	}

	public Player getPlayer( )
	{
		return player;
	}

	public void informEvent( int code )
	{
		informEvent( code, null );
	}

	public void informEvent( int code, Object param )
	{

	}

	public void setLevel( Level level )
	{
		currentLevel = level;
		player.setLevel( level );
		dispatcher = currentLevel.getDispatcher( );
		if ( currentLevel.hasNoonMusic( ) && !currentLevel.isDay( ) ) {
			MusicManager.playKey(currentLevel.getMusicKeyNoon());
		}
		else {
			MusicManager.playKey(currentLevel.getMusicKeyMorning());
		}

		// STMusicManager.thus.playForLevel(levelNumber, levelPath[levelNumber],
		// currentLevel.isDay());
		ui.levelChange( );

	}

	public void setPlayer( Player p )
	{
		player = p;
		player.setLevel( currentLevel );
		player.setFOV( new FOV( ) );
		currentLevel.setPlayer( player );
		if ( player.getGameSessionInfo( ) == null )
			player.setGameSessionInfo( new GameSessionInfo( ) );
		player.setSelector( uiSelector );
		ui.setPlayer( player );
		uiSelector.setPlayer( player );
		player.setPlayerEventListener( this );
		player.setGame( this );
	}

	public void wonGame( )
	{
		Display.thus.showEndgame( player );
		player.getGameSessionInfo( ).setDeathCause( GameSessionInfo.ASCENDED );
		finishGame( );
	}

	private void finishGame( )
	{
		if ( !player.isDoNotRecordScore( ) )
		{
//			GameFiles.updateGraveyard( Main.getMonsterRecord( ),
//					player.getGameSessionInfo( ) );
			GameFiles.saveHiScore(player,
					player.getFlag("ARENA_FIGHTER") ? "arena.tbl" : "hiscore.tbl");
			resumeScreen();
			Display.thus.showHiscores(GameFiles.loadScores(
					player.getFlag("ARENA_FIGHTER") ? "arena.tbl" : "hiscore.tbl"));
		}
		GameFiles.permadeath( player );
		exitGame( );
	}


	private void resumeScreen( ) {
		MusicManager.playKey("GAME_OVER");
		UserInterface.getUI().showMessageHistory();
		if (Display.thus.showResumeScreen(player)) {
			GameFiles.saveMemorialFile(player);
		}
	}

}