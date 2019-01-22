package co.castle.game;

import java.util.Hashtable;

import sz.midi.MidisLoader;
import sz.mp3.JLayerMP3Player;

public class STMusicManagerOld
{
	// private Thread currentMidiThread;
	private Thread currentMP3Thread;
	private boolean enabled;
	private Hashtable musics = new Hashtable( );
	private String playing = "__nuthin";

	public static STMusicManagerOld thus;

	private static MidisLoader midiPlayer;

	public STMusicManagerOld( )
	{
		midiPlayer = new MidisLoader( );
		JLayerMP3Player mp3Player = new JLayerMP3Player( );
		// currentMidiThread = new Thread(midiPlayer);
		currentMP3Thread = new Thread( mp3Player );
		currentMP3Thread.start( );
	}

	public static void initManager( )
	{
		thus = new STMusicManagerOld( );
	}

	public void addMusic( String levelType, String fileName )
	{
		musics.put( levelType, fileName );
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
			Game.crash( "Error trying to play " + fileName, e );
		}
	}

	public void playForLevel( int levelNo, String levelType )
	{
		String bgMusic = (String) musics.get( levelType );
		if ( bgMusic != null )
		{
			play( bgMusic );
		}
		else
		{
			stopMusic( );
		}
	}

	public void playKey( String key )
	{
		String bgMusic = (String) musics.get( key );
		if ( bgMusic != null )
		{
			play( bgMusic );
		}
		else
		{
			stopMusic( );
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
