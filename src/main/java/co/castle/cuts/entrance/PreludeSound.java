package co.castle.cuts.entrance;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;

public class PreludeSound extends Unleasher
{
	public void unleash( Level level, Game game ) {
		MusicManager.stopMusic();
		MusicManager.playKeyOnce("PRELUDE");
		enabled = false;
	}

}
