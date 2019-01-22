package co.castle.ai.monster.boss;

import co.castle.action.Action;
import co.castle.action.monster.MonsterWalk;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.monster.MonsterAI;
import co.castle.monster.Monster;
import sz.util.Util;

public class FrankAI extends MonsterAI
{
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
		return "FRANK_AI";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		if ( powerCounter < 0 )
		{
			who.getLevel( ).addMessage( "Frank calms down" );
			powerCounter = 25;
		}
		else
			powerCounter--;

		int directionToPlayer = aMonster.starePlayer( );

		if ( powerCounter > 4 || directionToPlayer == -1 )
		{
			int direction = Util.rand( 0, 7 );
			Action ret = new MonsterWalk( );
			ret.setDirection( direction );
			return ret;
		}
		else
		{
			// Walk to the player
			who.getLevel( ).addMessage( "Frank is angry!," );
			Action ret = new MonsterWalk( );
			ret.setDirection( directionToPlayer );
			return ret;
		}
	}

}