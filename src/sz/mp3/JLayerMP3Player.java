package sz.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class JLayerMP3Player implements Runnable {
	// Fields Static Finals

	public static final int INS_DIE = 2;

	public static final int INS_LOAD = 1;

	public static final int INS_PLAYING = 3;

	public static final int INS_STOP = 0;

	// Fields Static

	private static int currentInstruction;

	private static String currentMP3File = "__noneYet";

	public static void main(String[] args) {
		new Thread(new JLayerMP3Player()).start();
		JLayerMP3Player.setMP3("music/Wyvern - upbeat.mp3");

		// JLayerMP3Player.setMP3("music/Craig Stern - Exploring the Depths.mp3");
		JLayerMP3Player.setInstruction( INS_LOAD );

		try {
			Thread.sleep(10000);
			JLayerMP3Player.setInstruction(INS_DIE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Method Static

	public static void setInstruction(int instruction) {
		currentInstruction = instruction;
	}

	public static void setMP3(String pMP3File) {
		currentMP3File = pMP3File;
	}

	// Method Synchronized

	public synchronized void run() {
		boolean leave;
		out:
		while (true) {
			if (currentInstruction == INS_DIE) {
				break;
			}
			if (currentInstruction == INS_STOP) {
				currentMP3File = "__noneYet";
			}
			if ( currentInstruction == INS_LOAD )
			{
				currentInstruction = INS_PLAYING;
			}
			if ( currentMP3File.equals( "__noneYet" ) )
			{
				try
				{
					this.wait( );
				}
				catch ( InterruptedException ie )
				{
					continue;
				}
			}
			File soundFile = new File( currentMP3File );
			if ( !soundFile.exists( ) || soundFile.isDirectory( )
					|| !soundFile.canRead( ) )
			{
				System.out.println( "Invalid MP3 file: " + currentMP3File );
				try
				{
					this.wait( );
				}
				catch ( InterruptedException ie )
				{
					continue;
				}
			}
			leave = false;
			Player player = null;
			while ( !leave )
			{
				try {
					player = new Player(new FileInputStream(soundFile));
					while (true) {
						player.play(20);
						if (currentInstruction == INS_STOP) {
							currentMP3File = "__noneYet";
							player.close();
							leave = true;
							continue out;
						}
						if (currentInstruction == INS_DIE) {
							break out;
						}
						if (currentInstruction == INS_LOAD) {
							leave = true;
							continue out;
						}
						if (player.isComplete()) {
							player.close();
							break;
						}
					}
				}
				catch ( IOException ioe )
				{
					System.out.println( "I/O Error for " + currentMP3File );
					leave = true;
				}
				catch ( JavaLayerException uafe )
				{
					System.out.println(
							"UnsupportedAudioFileException for " + currentMP3File );
					leave = true;
				}
			}
			player.close( );
		}
	}
}