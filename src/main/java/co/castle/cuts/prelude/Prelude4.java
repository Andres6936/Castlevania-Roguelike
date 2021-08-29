package co.castle.cuts.prelude;

import co.castle.ai.monster.boss.DraculaAI;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;
import co.castle.main.Service;
import co.castle.ui.Display;
import sz.midi.STMidiPlayer;
import sz.util.Position;

public class Prelude4 extends Unleasher
{

	public void unleash( Level level, Game game ) {
		if (!level.getFlag("CHRIS_DEAD") || Position.distance(
				level.getMonsterByID("PRELUDE_DRACULA").getPosition(),
				game.getPlayer().getPosition()) > 5)
			return;
		Display.thus.showChat("PRELUDE_DRACULA2", game);
		MusicManager.playKey("CHRIS_DEAD");
		level.setMusicKeyMorning("CHRIS_DEAD");
		((DraculaAI) level.getMonsterByID("PRELUDE_DRACULA").getSelector())
				.setOnBattle(true);
		enabled = false;
	}

}
