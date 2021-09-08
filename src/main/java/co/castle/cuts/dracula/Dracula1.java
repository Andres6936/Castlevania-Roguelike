package co.castle.cuts.dracula;

import co.castle.ai.monster.boss.DraculaAI;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.Display;
import sz.util.Position;

public class Dracula1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster dracula = level.getMonsterByID( "DRACULA" );
		int distance = Position.distance( dracula.getPosition( ),
				game.getPlayer().getPosition());
		if (distance > 3 && !((DraculaAI) dracula.getSelector()).isOnBattle())
			return;
		Display.thus.showChat("DRACULA1", game);
		level.getMapCell(level.getExitFor("#DRACPOS"))
				.setAppearance(AppearanceFactory.getAppearanceFactory()
						.getAppearance("DRACULA_THRONE2_X"));
		((DraculaAI) dracula.getSelector()).setOnBattle(true);
		dracula.setVisible(true);
		level.setMusicKeyMorning("DRACULA");
		MusicManager.playKey("DRACULA");
		enabled = false;
	}

}
