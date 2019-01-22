package co.castle.cuts.ingame.clara1;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;

public class Clara1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Display.thus.showChat( "VINDELITH3", game );
		level.removeMonster( level.getNPCByID( "VINDELITH" ) );
		level.removeMonster( level.getNPCByID( "UNIDED_CLARA" ) );
		UserInterface.getUI( ).refresh( );
		enabled = false;
	}

}
