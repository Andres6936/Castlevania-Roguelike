package sz.midi;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.sound.midi.*;

import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.game.SFXManager;
import co.castle.system.FileLoader;

public class STMidiPlayer implements Runnable {
	public static final int INS_STOP = 0;
	public static final int INS_LOAD = 1;
	public static final int INS_DIE = 2;
	public static final int INS_LOAD_ONCE = 3;

	public static Sequencer sequencer;

	private static int currentInstruction;

	private static String currentMidiFile = "__noneYet";

	private static boolean loop = true;

	static {
		// Load the file of configuration, this properties file have the path
		// to tracks of app.
		final Properties configurationFile = new Properties();

		try (var in = FileLoader.getInputStream("properties/configuration.properties")) {
			configurationFile.load(in);
		} catch (IOException exception) {
			System.err.println("Configuration file not found or error while loading the file.\n");
			exception.printStackTrace();
		}

		// NOTE: Move and Clear
		if (configurationFile.getProperty("enableSound", "false").equals("true")) {
			System.out.println("Initializing Midi Sequencer");
			try {
				STMidiPlayer.sequencer = MidiSystem.getSequencer();
				// STMidiPlayer.setVolume(0.1d);
				STMidiPlayer.sequencer.open();

			} catch (MidiUnavailableException mue) {
				System.err.println("Midi Device Unavailable");
			}
		}
	}

	public static void setInstruction(int instruction) {
		currentInstruction = instruction;
	}

	public static void setMidi(String pMidiFile) {
		currentMidiFile = pMidiFile;
	}

	public static void setVolume( double gain )
	{
		Synthesizer synthesizer = (Synthesizer) sequencer;
		MidiChannel[] channels = synthesizer.getChannels();
		// gain is a value between 0 and 1 (loudest)
		for (MidiChannel channel : channels) {
			channel.controlChange(7, (int) (gain * 127.0d));
		}
	}

	public synchronized void run( )
	{
		boolean leave;
		while (currentInstruction != INS_DIE) {
			if (currentInstruction == INS_STOP) {
				currentMidiFile = "__noneYet";
			}
			if (currentMidiFile.equals("__noneYet")) {
				try {
					this.wait();
				} catch (InterruptedException ie) {
					continue;
				}
			}

			File midiFile = FileLoader.getResourceFile(currentMidiFile);

			if (currentInstruction == INS_LOAD) {
				if (!midiFile.exists() || midiFile.isDirectory()
						|| !midiFile.canRead()) {
					Game.addReport("Invalid Midi file: " + currentMidiFile);
					try {
						this.wait();
					} catch (InterruptedException ie) {
						continue;
					}
				}
				loop = true;
			}
			if (currentInstruction == INS_LOAD_ONCE) {
				if (!midiFile.exists() || midiFile.isDirectory()
						|| !midiFile.canRead()) {
					Game.addReport("Invalid Midi file: " + currentMidiFile);
					try {
						this.wait();
					} catch (InterruptedException ie) {
						continue;
					}
				}
				loop = false;
			}

			leave = false;
			while (!leave) {
				try {
					sequencer.setSequence(MidiSystem.getSequence(midiFile));
					sequencer.start();
					while (true) {
						if (sequencer.isRunning()) {
							try {
								Thread.sleep(1000); // Check every second
							} catch (InterruptedException ignore) {
								leave = true;
								break;
							}
						} else {
							break;
						}
					}
					// Close the MidiDevice & free resources
					sequencer.stop();
					if (!loop) {
						try {
							this.wait();
						} catch (InterruptedException ie) {
							leave = true;
						}
					}
				} catch (InvalidMidiDataException imde) {
					Game.addReport("Invalid Midi data for " + currentMidiFile);
				} catch (IOException ioe) {
					Game.addReport("I/O Error for " + currentMidiFile);
					ioe.printStackTrace();
				}
			}
		}
		sequencer.close( );
	}

}
