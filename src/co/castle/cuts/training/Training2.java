package co.castle.cuts.training;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import sz.util.Position;

public class Training2 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Position x = level.getExitFor( "#END" );
		if ( level.getPlayer( ).getPosition( ).equals( x ) )
		{
			game.exitGame( );
			enabled = false;
		}
	}
}
