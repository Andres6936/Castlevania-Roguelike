package co.castle.ai.monster;

import co.castle.action.Action;
import co.castle.action.ActionFactory;
import co.castle.action.monster.MonsterWalk;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.level.Cell;
import co.castle.monster.Monster;
import co.castle.player.Consts;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public class WanderToPlayerAI extends MonsterAI
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

	/**
	 * This AI is used by those enemies that just walk until they find the player,
	 * optionally performing a ranged attack him when he is in range
	 */

	public Action selectAction( Actor who )
	{
		Debug.doAssert( who instanceof Monster, "WanderToPlayerAI selectAction" );
		Monster aMonster = (Monster) who;

		if ( aMonster.getEnemy( ) != null )
		{
			if ( !aMonster.getLevel( ).getMonsters( )
					.contains( (Monster) aMonster.getEnemy( ) ) )
			{
				aMonster.setEnemy( null );
			}
		}

		if ( aMonster.getEnemy( ) != null
				|| aMonster.hasCounter( Consts.C_MONSTER_CHARM ) )
		{

			int directionToMonster = -1;
			if ( aMonster.getEnemy( ) != null )
			{
				directionToMonster = aMonster
						.stareMonster( (Monster) aMonster.getEnemy( ) );
			}
			else
			{
				directionToMonster = aMonster.stareMonster( );
			}

			if ( directionToMonster == -1 )
			{
				// Walk TO player except if will bump him
				directionToMonster = aMonster.starePlayer( );
				if ( directionToMonster == -1 )
				{
					return null;
				}
				else
				{
					Position targetPositionX = Position.add( who.getPosition( ),
							Action.directionToVariation( directionToMonster ) );
					if ( !who.getLevel( ).isWalkable( targetPositionX ) )
					{
						return null;
					}
					else
					{
						if ( who.getLevel( ).getPlayer( ).getPosition( )
								.equals( targetPositionX ) )
						{
							return null;
						}
						else
						{
							Action ret = new MonsterWalk( );
							ret.setDirection( directionToMonster );
							return ret;
						}
					}
				}
			}
			else
			{
				Action ret = new MonsterWalk( );
				if ( !who.getLevel( ).isWalkable( Position.add( who.getPosition( ),
						Action.directionToVariation( directionToMonster ) ) ) )
				{
					directionToMonster = Util.rand( 0, 7 );
					while ( true )
					{
						if ( !Position
								.add( who.getPosition( ),
										Action.directionToVariation(
												directionToMonster ) )
								.equals( who.getLevel( ).getPlayer( ).getPosition( ) ) )
							break;
					}
					ret.setDirection( directionToMonster );
				}
				else
				{
					ret.setDirection( directionToMonster );
				}
				return ret;
			}
		}

		int directionToPlayer = aMonster.starePlayer( );
		if ( directionToPlayer == -1 )
		{
			// Wander aimlessly
			int direction = Util.rand( 0, 7 );
			Action ret = new MonsterWalk( );
			ret.setDirection( direction );
			return ret;
		}
		else
		{
			int distanceToPlayer = Position.flatDistance( aMonster.getPosition( ),
					aMonster.getLevel( ).getPlayer( ).getPosition( ) );
			// Decide if will attack the player or walk to him
			if ( Util.chance( 50 ) )
			{
				// Will try to attack the player
				if ( rangedAttacks != null )
				{
					// Try
					for ( int i = 0; i < rangedAttacks.size( ); i++ )
					{
						RangedAttack ra = (RangedAttack) rangedAttacks.elementAt( i );
						if ( distanceToPlayer <= ra.getRange( ) )
							if ( Util.chance( ra.getFrequency( ) ) )
							{
								Action ret = ActionFactory.getActionFactory( )
										.getAction( ra.getAttackId( ) );
								ret.setDirection( directionToPlayer );
								return ret;
							}
					}
				}
			}
			// Couldnt attack the player, move to him
			Action ret = new MonsterWalk( );
			ret.setDirection( directionToPlayer );
			Cell currentCell = aMonster.getLevel( ).getMapCell( aMonster.getPosition( ) );
			Cell destinationCell = aMonster.getLevel( )
					.getMapCell( Position.add( aMonster.getPosition( ),
							Action.directionToVariation( directionToPlayer ) ) );

			if ( destinationCell == null || currentCell == null )
			{
				ret.setDirection( Util.rand( 0, 7 ) );
				return ret;
			}
			if ( ( destinationCell.isSolid( ) && !aMonster.isEthereal( ) )
					|| destinationCell.getHeight( ) > currentCell.getHeight( )
							+ aMonster.getLeaping( ) + 1 )
				ret.setDirection( Util.rand( 0, 7 ) );
			return ret;
		}

	}

}