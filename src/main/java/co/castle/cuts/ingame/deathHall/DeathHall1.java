package co.castle.cuts.ingame.deathHall;

import co.castle.ai.SelectorFactory;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;

public class DeathHall1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster death = level.getMonsterByID( "DEATH" );
		death.setSelector(
				SelectorFactory.getSelectorFactory( ).getSelector( "NULL_SELECTOR" ) );
		level.addCounter( "COUNTBACK_DEATHHALL", 2 );
		level.setFlag( "DEATH_HALL", true );
		enabled = false;
	}

}
