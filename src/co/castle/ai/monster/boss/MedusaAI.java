package co.castle.ai.monster.boss;

import co.castle.action.Action;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.monster.boss.SummonSnakes;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.monster.MonsterAI;
import co.castle.monster.Monster;
import sz.util.Position;
import sz.util.Util;

public class MedusaAI extends MonsterAI
{
	private boolean powerActive;
	private int powerCounter = 5;

	public ActionSelector derive( )
	{
		try
		{
			return (ActionSelector) clone( );
		}
		catch ( CloneNotSupportedException cnse )
		{
			return null;
		}
	}

	public String getID( )
	{
		return "MEDUSA_AI";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		int playerDistance = Position.flatDistance( aMonster.getPosition( ),
				aMonster.getLevel( ).getPlayer( ).getPosition( ) );
		if ( playerDistance > 20 )
		{
			powerActive = false;
			powerCounter = 15;
		}
		else
			powerActive = true;

		if ( powerActive )
		{
			if ( powerCounter < 0 )
			{
				powerCounter = 15;
				return new SummonSnakes( );
			}
			else
				powerCounter--;
		}
		int directionToPlayer = aMonster.starePlayer( );

		if ( directionToPlayer == -1 )
		{
			// A stationary here would do nothing
			int direction = Util.rand( 0, 7 );
			Action ret = new MonsterWalk( );
			ret.setDirection( direction );
			return ret;
		}
		if ( powerCounter > 3 )
		{
			// walk randomly
			Action ret = new MonsterWalk( );
			ret.setDirection( Util.rand( 0, 7 ) );
			return ret;
		}
		else
		{
			// Walk to the player
			Action ret = new MonsterWalk( );
			ret.setDirection( directionToPlayer );
			return ret;
		}
	}

}