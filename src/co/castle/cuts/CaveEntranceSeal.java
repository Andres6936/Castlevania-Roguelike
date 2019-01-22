package co.castle.cuts;

import co.castle.game.Game;
import co.castle.level.Level;

public class CaveEntranceSeal extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		// level.removeExit("MUMMIES_LAIR");
		enabled = false;
	}

}