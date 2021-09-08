package sz.wav;

import co.castle.system.FileLoader;

import javax.sound.sampled.*;

public class WavPlayer implements Runnable
{
	private String wavFile;

	public WavPlayer( String wavFile )
	{
		this.wavFile = wavFile;
	}

	public void play( String file )
	{
		try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(FileLoader.getResourceFile(file));

            AudioFormat af = ais.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Unsupported line");
                System.exit(-1);
            }
            int frameRate = (int) af.getFrameRate();
            int frameSize = af.getFrameSize();
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
			System.err.println("Error playing... " + e.toString());
		}
	}

	public void run( )
	{
		play( wavFile );
	}
}