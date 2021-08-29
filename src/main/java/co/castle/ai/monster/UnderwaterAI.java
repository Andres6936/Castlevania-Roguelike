package co.castle.ai.monster;

import co.castle.action.Action;
import co.castle.action.ActionFactory;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.monster.Swim;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.monster.Monster;
import co.castle.player.Player;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public class UnderwaterAI extends MonsterAI
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
		return "UNDERWATER";
	}

	/**
	 * Dwells in the water swimming until he spots the player, swims to him and
	 * Jumps out of the water. walks to it and fires
	 */
	public Action selectAction( Actor who )
	{
		Debug.doAssert( who instanceof Monster, "Underwater AI selectAction" );
		Monster aMonster = (Monster) who;
		int directionToPlayer = aMonster.starePlayer( );
		if ( directionToPlayer == -1 )
		{
			// The player is somewhere
			if ( !aMonster.waitsPlayer( ) )
			{
				int direction = Util.rand( 0, 7 );
				if ( aMonster.isInWater( ) )
				{
					Action ret = new Swim( );
					ret.setDirection( direction );
					return ret;
				}
				else
				{
					Action ret = new MonsterWalk( );
					ret.setDirection( direction );
					return ret;
				}
			}
			else
				return null;
		}
		else
		{
			/*
			 * if (aMonster.isInWater() && !aPlayer.isSwimming()){ Action ret = new
			 * Swim(); ret.setDirection(directionToPlayer); return ret; }
			 */
			if ( rangedAttacks != null )
			{
				int distanceToPlayer = Position.flatDistance( aMonster.getPosition( ),
						aMonster.getLevel( ).getPlayer( ).getPosition( ) );
				// Try to attack
				for ( int i = 0; i < rangedAttacks.size( ); i++ )
				{
					RangedAttack ra = rangedAttacks.elementAt(i);
					if (distanceToPlayer <= ra.getRange())
						if (Util.chance(ra.getFrequency())) {
							Action ret = actionFactory.getAction(ra.getAttackId());
							ret.setDirection(directionToPlayer);
							return ret;
						}
				}
			}
			// COuldnt attack... walk or swim to the player
			if ( aMonster.isInWater( ) )
			{
				Action ret = new Swim( );
				ret.setDirection( directionToPlayer );
				return ret;
			}
			else
			{
				Action ret = new MonsterWalk( );
				ret.setDirection( directionToPlayer );
				return ret;
			}
		}
	}
}