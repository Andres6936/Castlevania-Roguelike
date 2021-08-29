package co.castle.cuts.dracula;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.main.Service;
import co.castle.monster.Monster;
import co.castle.ui.Display;
import sz.midi.STMidiPlayer;

public class Dracula3 extends Unleasher
{

	public void unleash( Level level, Game game ) {
		Monster dracula = level.getMonsterByID("DEMON_DRACULA");
		if (dracula != null)
			return;
		STMidiPlayer.stopMusic();
		Display.thus.showChat("DRACULA3", game);
		STMidiPlayer.playKey("VICTORY");
		game.wonGame();
		enabled = false;
	}
}