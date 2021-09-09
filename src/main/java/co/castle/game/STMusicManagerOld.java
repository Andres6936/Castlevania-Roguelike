package co.castle.game;

import sz.midi.MidisLoader;
import sz.mp3.JLayerMP3Player;

import java.util.Hashtable;

public class STMusicManagerOld {
	// private Thread currentMidiThread;
	private final Thread currentMP3Thread;
	private boolean enabled;
	private Hashtable<String, String> musics = new Hashtable<>();
	private String playing = "__nuthin";

	public static STMusicManagerOld thus;

	private static MidisLoader midiPlayer;

	public STMusicManagerOld() {
		midiPlayer = new MidisLoader();
		JLayerMP3Player mp3Player = new JLayerMP3Player();
		// currentMidiThread = new Thread(midiPlayer);
		currentMP3Thread = new Thread( mp3Player );
		currentMP3Thread.start( );
	}

	public static void initManager( )
	{
		thus = new STMusicManagerOld( );
	}

	public void die( )
	{
		midiPlayer.close( );
		JLayerMP3Player.setInstruction( JLayerMP3Player.INS_DIE );
		if ( currentMP3Thread != null )
		{
			currentMP3Thread.interrupt( );
		}
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
				midiPlayer.playFile( fileName, true );
			}
		}
		catch ( Exception e )
		{
			System.err.println("Error trying to play " + fileName);
		}
	}

	public void playKey( String key ) {
		String bgMusic = musics.get(key);
		if (bgMusic != null) {
			play(bgMusic);
		} else {
			stopMusic();
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
			midiPlayer.stop( );
		}
	}

	/*
	 * public void playNight (){ String niteMusic = (String) musics.get(NITE_MUSIC);
	 * if (niteMusic != null) play(niteMusic); } private final static String
	 * NITE_MUSIC = "NITE_MUSIC";
	 */
}
