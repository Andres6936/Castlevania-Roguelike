package co.castle.cuts.intro;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;

public class Intro1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Display.thus.showChat( "INTRO_1", game );
		level.addCounter( "COUNTBACK_INTRO_1", 5 );
		level.setFlag( "INTRO1", true );
		// level.removeExit("_BACK");
		enabled = false;
	}

}
