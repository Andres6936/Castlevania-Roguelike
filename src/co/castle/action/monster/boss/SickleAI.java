package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.action.monster.MonsterWalk;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.monster.MonsterAI;
import co.castle.level.Cell;
import co.castle.monster.Monster;
import sz.util.Position;

public class SickleAI extends MonsterAI
{
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
		return "WANDER";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		int directionToPlayer = aMonster.starePlayer( );
		if ( directionToPlayer == -1 )
		{
			return null;
		}
		else
		{
			int distanceToPlayer = Position.flatDistance( aMonster.getPosition( ),
					aMonster.getLevel( ).getPlayer( ).getPosition( ) );
			if ( distanceToPlayer > 20 )
				return null;
			Action ret = new MonsterWalk( );
			ret.setDirection( directionToPlayer );
			Cell currentCell = aMonster.getLevel( ).getMapCell( aMonster.getPosition( ) );
			Cell destinationCell = aMonster.getLevel( )
					.getMapCell( Position.add( aMonster.getPosition( ),
							Action.directionToVariation( directionToPlayer ) ) );

			if ( destinationCell == null || currentCell == null )
			{
				return null;
			}
			return ret;
		}

	}

}