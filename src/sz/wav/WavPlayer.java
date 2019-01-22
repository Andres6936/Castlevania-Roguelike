package sz.wav;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import co.castle.game.Game;

public class WavPlayer implements Runnable
{
	private String wavFile;

	public WavPlayer( String wavFile )
	{
		this.wavFile = wavFile;
	}

	public void play( String file )
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream( new File( file ) );

			AudioFormat af = ais.getFormat( );
			DataLine.Info info = new DataLine.Info( SourceDataLine.class, af );
			if ( !AudioSystem.isLineSupported( info ) )
			{
				System.out.println( "Unsupported line" );
				System.exit( -1 );
			}
			int frameRate = (int) af.getFrameRate( );
			int frameSize = af.getFrameSize( );
			int bufSize = frameRate * frameSize / 10;
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine( info );
			line.open( af, bufSize );
			line.start( );

			byte[ ] data = new byte[ bufSize ];
			int bytesRead;
			while ( ( bytesRead = ais.read( data, 0, data.length ) ) != -1 )
			{
				Thread.yield( );
				line.write( data, 0, bytesRead );
			}
			line.drain( );
			line.stop( );
			line.close( );
		}
		catch ( Exception e )
		{
			Game.addReport( "Error playing... " + e.toString( ) );
		}
	}

	public void run( )
	{
		play( wavFile );
	}
}