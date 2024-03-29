package co.castle.cuts.dracula;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.Display;

public class Dracula3 extends Unleasher
{

	public void unleash( Level level, Game game ) {
		Monster dracula = level.getMonsterByID("DEMON_DRACULA");
		if (dracula != null)
			return;
		MusicManager.stopMusic();
		Display.thus.showChat("DRACULA3", game);
		MusicManager.playKey("VICTORY");
		game.wonGame();
		enabled = false;
	}
}