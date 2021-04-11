package co.castle.cuts.ingame.death;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;

public class Death2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		if ( level.getFlag( "DEATH_LEVEL" )
				&& level.getCounter( "COUNTBACK_DEATH" ).isOver( ) )
		{
			Display.thus.showChat( "DEATH1", game );
			enabled = false;
		}
	}

}
