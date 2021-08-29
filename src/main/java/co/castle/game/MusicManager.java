package co.castle.game;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import sz.midi.STMidiPlayer;
import sz.mp3.JLayerMP3Player;

/**
 * Role: Play tracks of format mp3 and waw.
 */
public class MusicManager {

	// Field Final

	/**
	 * Needed for play waw files.
	 */
	private static final Thread currentMidiThread = new Thread(new STMidiPlayer());

	/**
	 * Needed for play mp3 files.
	 */
	private static final Thread currentMP3Thread = new Thread(new JLayerMP3Player());

	/**
	 * Save the path to tracks.
	 */
	private static final Hashtable<String, String> musics = new Hashtable<>();

	/**
	 * For default to false, false if exist errors that not allow play tracks,
	 * true if not errors has been produced. If this variable is false, the
	 * music manager cannot play tracks (effects, ambient sound, etc ... ).
	 */
	private static boolean enabled = false;

	/**
	 * Current path or name of file playing.
	 */
	private static String playing = "__nuthin";

	// Construct

	public MusicManager() {
		currentMP3Thread.start();
		currentMidiThread.start();
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
	public static void addTracks(final Properties properties) {
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

	public static boolean isEnabled() {
		return enabled;
	}

	public static void play(String fileName) {
		if (!enabled || playing.equals(fileName))
			return;
		stopMusic();
		try {
			playing = fileName;
			if (fileName.endsWith("mp3")) {
				JLayerMP3Player.setMP3(fileName);
				JLayerMP3Player.setInstruction(JLayerMP3Player.INS_LOAD);
				currentMP3Thread.interrupt();
			} else {
				STMidiPlayer.setMidi(fileName);
				STMidiPlayer.setInstruction(STMidiPlayer.INS_LOAD);
				currentMidiThread.interrupt();
			}

		} catch (Exception e) {
			System.out.println("Error trying to play " + fileName);
		}
	}

	public static void playKey(String key) {
		String bgMusic = musics.get(key);

		if (bgMusic != null) {
			play(bgMusic);
		} else {
			stopMusic();
		}
	}

	public static void playKeyOnce(String key) {
		String bgMusic = musics.get(key);
		if (bgMusic != null) {
			playOnce(bgMusic);
		} else {
			stopMusic();
		}
	}

	public static void playOnce(String fileName) {
		if (!enabled || playing.equals(fileName))
			return;
		stopMusic();
		try {
			playing = fileName;
			if (fileName.endsWith("mp3")) {
				JLayerMP3Player.setMP3(fileName);
				JLayerMP3Player.setInstruction(JLayerMP3Player.INS_LOAD);
				currentMP3Thread.interrupt();
			} else {
				STMidiPlayer.setMidi(fileName);
				STMidiPlayer.setInstruction(STMidiPlayer.INS_LOAD_ONCE);
				currentMidiThread.interrupt();
			}

		} catch (Exception e) {
			Game.crash("Error trying to play " + fileName, e);
		}
	}

	public static void setEnabledMusicManager(boolean value) {
		enabled = value;
	}

	public static void stopMusic() {
		if (playing.endsWith("mp3")) {
			JLayerMP3Player.setInstruction(JLayerMP3Player.INS_STOP);
			currentMP3Thread.interrupt();
		} else {
			STMidiPlayer.setInstruction(STMidiPlayer.INS_STOP);
			currentMidiThread.interrupt();
		}
		playing = "__nuthin";
	}

	// Method Debug

	@Override
	public String toString() {
		return String.format("Total tracks: %d", musics.size());
	}
}
