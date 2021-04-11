package co.castle.cuts.ingame.deathHall;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;

public class DeathHall2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		if ( level.getFlag( "DEATH_HALL" )
				&& level.getCounter( "COUNTBACK_DEATHHALL" ).isOver( ) )
		{
			Display.thus.showChat( "DEATH_HALL", game );
			level.removeMonster( level.getMonsterByID( "DEATH" ) );
			enabled = false;
		}
	}

}
