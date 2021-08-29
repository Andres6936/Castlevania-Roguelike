package co.castle.cuts.entrance;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;
import co.castle.main.Service;
import sz.midi.STMidiPlayer;

public class PreludeSound extends Unleasher
{
	public void unleash( Level level, Game game ) {
		MusicManager.stopMusic();
		MusicManager.playKeyOnce("PRELUDE");
		enabled = false;
	}

}
