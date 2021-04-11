package co.castle.cuts.ingame.vindelith1;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;

public class Vindelith1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		level.addCounter( "COUNTBACK_VINDELITHMEETING", 0 );
		level.setFlag( "VINDELITHMEETING", true );
		enabled = false;
	}

}
