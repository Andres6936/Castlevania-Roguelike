package co.castle.game;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import sz.midi.STMidiPlayer;
import sz.mp3.JLayerMP3Player;

public class MusicManager {

	// Field Final

	private final Thread currentMidiThread;

	private final Thread currentMP3Thread;

	private final Hashtable<String, String> musics = new Hashtable<>();

	private boolean enabled = false;

	private String playing = "__nuthin";

	// Field Static

	/**
	 * Class type Singleton, reference to only object
	 */
	private static MusicManager instance;

	// Construct

	// We make the constructor private to prevent the use of 'new'
	private MusicManager() {
		STMidiPlayer midiPlayer = new STMidiPlayer();
		JLayerMP3Player mp3Player = new JLayerMP3Player();

		currentMidiThread = new Thread(midiPlayer);
		currentMP3Thread = new Thread(mp3Player);

		currentMP3Thread.start();
		currentMidiThread.start();
	}

	// Method Static

	/**
	 * @return Instance of MusicManager
	 */
	public static MusicManager getInstance() {
		if (instance == null) {
			instance = new MusicManager();
		}

		return instance;
	}

	// Method

	/**
	 * Load the list of track and path to it.
	 * <p>
	 * Post-condition: The Hash music has been filled with list of tracks
	 * and paths to it.
	 *
	 * @param properties File with the key and path to tracks.
	 */
	public void addMusic(final Properties properties) {
		Enumeration<?> keys = properties.keys();

		while (keys.hasMoreElements()) {
			final String key = (String) keys.nextElement();
			// The properties file save the path of track with the prefix
			// (aka. key) "mus_<KEY_TRACK>".
			// Example of possibles keys:
			// 	- mus_TITLE = "tracks/title.mp3"
			// 	- mus_HIT = "effect/hit.mp3"
			// Filter this keys.
			if (key.startsWith("mus_")) {
				// Deleted the part of "mus_" and use the rest of key name
				// like key.
				// Example: mus_TITLE will be TITLE
				musics.put(key.substring(4), properties.getProperty(key));
			}
		}

		// The hash with the tracks has been filled, enable the music manager.
		enabled = true;
	}

	public void die() {
		STMidiPlayer.setInstruction(STMidiPlayer.INS_DIE);
		if (currentMidiThread != null) {
			currentMidiThread.interrupt();
		}
		JLayerMP3Player.setInstruction( JLayerMP3Player.INS_DIE );
		if ( currentMP3Thread != null )
		{
			currentMP3Thread.interrupt( );
		}
	}

	public boolean isEnabled( )
	{
		return enabled;
	}

	public void play( String fileName )
	{
		if ( !enabled || playing.equals( fileName ) )
			return;
		stopMusic( );
		try
		{
			playing = fileName;
			if ( fileName.endsWith( "mp3" ) )
			{
				JLayerMP3Player.setMP3( fileName );
				JLayerMP3Player.setInstruction( JLayerMP3Player.INS_LOAD );
				if ( currentMP3Thread != null )
				{
					currentMP3Thread.interrupt( );
				}
			}
			else
			{
				STMidiPlayer.setMidi( fileName );
				STMidiPlayer.setInstruction( STMidiPlayer.INS_LOAD );
				if ( currentMidiThread != null )
				{
					currentMidiThread.interrupt( );
				}
			}

		}
		catch ( Exception e )
		{
			System.out.println( "Error trying to play " + fileName );
		}
	}

	public void playKey( String key) {
		String bgMusic = musics.get(key);

		if (bgMusic != null) {
			play(bgMusic);
		} else {
			stopMusic();
		}
	}

	public void playKeyOnce( String key) {
		String bgMusic = musics.get(key);
		if (bgMusic != null) {
			playOnce(bgMusic);
		} else {
			stopMusic();
		}
	}

	public void playOnce( String fileName )
	{
		if ( !enabled || playing.equals( fileName ) )
			return;
		stopMusic( );
		try
		{
			playing = fileName;
			if ( fileName.endsWith( "mp3" ) )
			{
				JLayerMP3Player.setMP3( fileName );
				JLayerMP3Player.setInstruction( JLayerMP3Player.INS_LOAD );
				if ( currentMP3Thread != null )
				{
					currentMP3Thread.interrupt( );
				}
			}
			else
			{
				STMidiPlayer.setMidi( fileName );
				STMidiPlayer.setInstruction( STMidiPlayer.INS_LOAD_ONCE );
				if ( currentMidiThread != null )
				{
					currentMidiThread.interrupt( );
				}
			}

		}
		catch ( Exception e )
		{
			Game.crash( "Error trying to play " + fileName, e );
		}
	}

	public void setEnabled( boolean value )
	{
		enabled = value;
	}

	public void stopMusic( )
	{
		if ( playing.endsWith( "mp3" ) )
		{
			JLayerMP3Player.setInstruction( JLayerMP3Player.INS_STOP );
			if ( currentMP3Thread != null )
			{
				currentMP3Thread.interrupt( );
			}
		}
		else
		{
			STMidiPlayer.setInstruction( STMidiPlayer.INS_STOP );
			if ( currentMidiThread != null )
				currentMidiThread.interrupt( );
		}
		playing = "__nuthin";
	}
}
