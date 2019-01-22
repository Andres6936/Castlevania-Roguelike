package co.castle.cuts.ingame.badBelmont;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.Display;

public class BadBelmont2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		if ( level.getPlayer( ).getFlag( "SAVED_SOLIEYU" ) )
		{
			enabled = false;
			return;
		}
		Monster belmont = level.getMonsterByID( "BADBELMONT" );
		if ( belmont != null )
			return;
		Display.thus.showChat( "BADSOLIEYU2", game );
		enabled = false;
	}
}