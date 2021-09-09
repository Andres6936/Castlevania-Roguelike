package co.castle.game;

import co.castle.item.Item;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RepositoryLevelMetadata;
import co.castle.levelgen.LevelMaster;
import co.castle.npc.Hostage;
import co.castle.player.Consts;
import co.castle.player.GameSessionInfo;
import co.castle.player.Player;
import co.castle.player.PlayerEventListener;
import co.castle.ui.CommandListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import sz.fov.FOV;
import sz.util.Debug;
import sz.util.Util;

import java.util.Hashtable;
import java.util.Vector;

public class Game implements CommandListener, PlayerEventListener, java.io.Serializable {
	private boolean canSave;
	private Level currentLevel;

	private final String[] DEATHMESSAGES = new String[]
			{"You are dead... and Dracula is still alive", "All hopes are lost.",
					"It's the end.",
					"Let us enjoy this evening for pleasure, the night is still young...",
					"Game Over", "Better luck next time, Son of a Belmont"};
	private Dispatcher dispatcher;
	private boolean endGame;
	private boolean isDay = true;

	private final RepositoryLevelMetadata levelMetadata = new RepositoryLevelMetadata();
	// private String[] levelPath;

	private Player player;

	private Hashtable<String, Level> /* Level */ storedLevels = new Hashtable<>();
	private int timeSwitch;
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

		endGame = true;
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

	private void checkTimeSwitch( )
	{
		if ( endGame )
			return;
		timeSwitch--;
		currentLevel.setTimecounter( timeSwitch );
		if ( timeSwitch <= 0 )
		{
			// Environmental Effects, random
			boolean rain = Util.chance( 20 );
			boolean thunderstorm = !rain && Util.chance( 10 );
			boolean fog = !rain && !thunderstorm && Util.chance( 10 );
			boolean sunnyDay = !isDay && !fog && !rain && !thunderstorm
					&& Util.chance( 20 );

			player.setFlag( Consts.ENV_RAIN, rain );
			player.setFlag( Consts.ENV_THUNDERSTORM, thunderstorm );
			player.setFlag( Consts.ENV_FOG, fog );
			player.setFlag( Consts.ENV_SUNNY, sunnyDay );

			if ( isDay )
			{
				if ( currentLevel.hasNoonMusic( ) ) {
					MusicManager.stopMusic();
					Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm, false);
					MusicManager.playKey(currentLevel.getMusicKeyNoon());
				}
				else
				{
					Display.thus.showTimeChange( !isDay, fog, rain, thunderstorm, false );
				}
			}
			else
			{

				if ( currentLevel.hasNoonMusic( ) ) {
					MusicManager.stopMusic();
					Display.thus.showTimeChange(!isDay, fog, rain, thunderstorm,
							sunnyDay);
					MusicManager.playKey(currentLevel.getMusicKeyMorning());
				}
				else
				{
					Display.thus.showTimeChange( !isDay, fog, rain, thunderstorm,
							sunnyDay );
				}
			}
			isDay = !isDay;
			currentLevel.setIsDay( isDay );
			timeSwitch = DAY_LENGTH;
			currentLevel.setTimecounter( timeSwitch );
		}
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

	private void forwardTime( )
	{
		timeSwitch = 0;
		checkTimeSwitch( );
	}


	private void loadLevel( String levelID )
	{
		loadLevel( levelID, -1 );
	}

	private void loadLevel( String levelID, int targetLevelNumber ) {
		Debug.enterMethod(this, "loadLevel", levelID + "," + targetLevelNumber);
		String formerLevelID;
		if (currentLevel != null) {
			if (currentLevel.getBoss() != null && !currentLevel.getBoss().isDead())
				return;
			formerLevelID = currentLevel.getID();
			Level storedLevel = storedLevels.get(formerLevelID);
			if (storedLevel == null) {
				storedLevels.put(formerLevelID, currentLevel);
			}
		} else {
			formerLevelID = "_BACK";
		}
		Level storedLevel = storedLevels.get(levelID);
		if (storedLevel != null) {
			currentLevel = storedLevel;
			player.setPosition(currentLevel.getExitFor(formerLevelID));
			currentLevel.setIsDay(isDay);
			currentLevel.setTimecounter(timeSwitch);
			if (currentLevel.isCandled()) {
				currentLevel.destroyCandles();
				LevelMaster.lightCandles(currentLevel);
			}
		}
		else
		{
			try
			{
				currentLevel = LevelMaster.createLevel(
						levelMetadata.get(levelID), player);
				currentLevel.setPlayer(player);
				ui.setPlayer( player );
				uiSelector.setPlayer( player );
				currentLevel.setIsDay( isDay );
				currentLevel.setTimecounter( timeSwitch );
				if ( currentLevel.getPlayer( ) != null )
					currentLevel.getPlayer( ).addHistoricEvent(
							"got to the " + currentLevel.getDescription( ) );
			}
			catch ( CRLException crle )
			{
				System.err.println("Error while creating level " + levelID);
			}
		}
		// currentLevel.setLevelNumber(targetLevelNumber);
		player.setLevel( currentLevel );

		if ( currentLevel.getExitFor( formerLevelID ) != null )
		{
			player.setPosition( currentLevel.getExitFor( formerLevelID ) );
		}
		else if ( currentLevel.getExitFor( "_START" ) != null )
		{
			player.setPosition( currentLevel.getExitFor( "_START" ) );
		}

		if ( currentLevel.isHostageSafe( ) )
		{
			if ( player.hasHostage( ) )
			{
				// player.setPosition(currentLevel.getExitFor("_NEXT"));
				Hostage h = player.getHostage( );
				player.setHostage( null );
				h.setPosition( player.getPosition( ).x - 3, player.getPosition( ).y,
						player.getPosition( ).z );
				h.setRescued( true );
				currentLevel.addMonster( h );
				player.addHistoricEvent(
						"brought " + h.getDescription( ) + " to safety" );
				Display.thus.showHostageRescue( h );
				player.addGold( h.getReward( ) );
				Item reward = h.getItemReward( );
				if ( reward != null )
					if ( player.canCarry( ) )
						player.addItem( reward );
					else
						player.getLevel( ).addItem( player.getPosition( ), reward );
			}
		}
		dispatcher = currentLevel.getDispatcher( );
		if ( currentLevel.hasNoonMusic( ) && !currentLevel.isDay( ) ) {
			MusicManager.playKey(currentLevel.getMusicKeyNoon());
		}
		else {
			MusicManager.playKey(currentLevel.getMusicKeyMorning());
		}
		if ( currentLevel.isRutinary( ) )
		{
			currentLevel.anihilateMonsters( );
			currentLevel.populate( );
		}

		if ( currentLevel.getBoss( ) != null )
		{
			currentLevel.getBoss( ).recoverHits( );
		}
		if ( !dispatcher.contains( player ) )
		{
			dispatcher.addActor( player );
		}
		ui.levelChange( );
		Debug.exitMethod( );
	}

	private void resumeScreen( ) {
		MusicManager.playKey("GAME_OVER");
		UserInterface.getUI().showMessageHistory();
		if (Display.thus.showResumeScreen(player)) {
			GameFiles.saveMemorialFile(player);
		}
	}

}