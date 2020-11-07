package co.castle.ai.monster;

import co.castle.action.Action;
import co.castle.action.ActionFactory;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.monster.Monster;
import sz.util.Position;
import sz.util.Util;

public class StationaryAI extends MonsterAI
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
		return "STATIONARY_AI";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		int directionToPlayer = aMonster.starePlayer( );
		int playerDistance = Position.flatDistance( aMonster.getPosition( ),
				aMonster.getLevel( ).getPlayer( ).getPosition( ) );
		if ( directionToPlayer == -1 )
		{
			return null;
		}
		else
		{
			// Randomly decide if will approach the player or attack
			if ( rangedAttacks != null && Util.chance( 80 ) )
			{
				// Try to attack the player
				for (RangedAttack element : rangedAttacks) {
					if (element.getRange() >= playerDistance
							&& Util.chance(element.getFrequency())) {
						// Perform the attack
						Action ret = ActionFactory.getActionFactory()
								.getAction(element.getAttackId());
						ret.setDirection(directionToPlayer);
						return ret;
					}
				}
			}
			// Couldnt attack the player, so do nothing
			return null;
		}
	}
}