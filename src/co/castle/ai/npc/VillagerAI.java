package co.castle.ai.npc;

import co.castle.action.Action;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.npc.PeaceWalk;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.level.Cell;
import co.castle.npc.NPC;
import sz.util.Position;
import sz.util.Util;

public class VillagerAI implements ActionSelector
{
	protected boolean attackPlayer;
	protected boolean onDanger;

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
		return "VILLAGER";
	}

	public boolean isHostile( )
	{
		return attackPlayer || onDanger;
	}

	public Action selectAction( Actor who )
	{
		NPC aNPC = (NPC) who;
		if ( attackPlayer && aNPC.getHits( ) > 1 )
		{
			if ( Util.chance( 10 ) )
			{
				who.getLevel( ).addMessage( "The " + who.getDescription( ) + " yells: '"
						+ aNPC.getAngryMessage( ) + "'", who.getPosition( ) );
			}
			who.getLevel( ).signal( who.getPosition( ), 5, "ATTACK_PLAYER" );
			int directionToPlayer = aNPC.starePlayer( );
			if ( directionToPlayer != -1 )
			{
				Action ret = new MonsterWalk( );
				ret.setDirection( directionToPlayer );
				return ret;
			}
		}
		else if ( onDanger )
		{
			who.getLevel( ).addMessage( "The " + who.getDescription( ) + " yells: '"
					+ aNPC.getDangerMessage( ) + "'", who.getPosition( ) );
			who.getLevel( ).signal( who.getPosition( ), 7, "ATTACK_PLAYER" );
			int directionToPlayer = aNPC.starePlayer( );
			if ( directionToPlayer != -1 )
			{
				Action ret = new MonsterWalk( );
				ret.setDirection( Action.toIntDirection( Position
						.mul( Action.directionToVariation( directionToPlayer ), -1 ) ) );
				return ret;
			}
		}
		else
		{
			if ( Util.chance( 30 ) )
			{
				Action ret = new PeaceWalk( );
				int tries = 100;
				while ( tries > 0 )
				{
					int direction = Util.rand( 0, 7 );
					Position destinationPoint = Position.add( aNPC.getPosition( ),
							Action.directionToVariation( direction ) );
					Cell cell = who.getLevel( ).getMapCell( destinationPoint );
					if ( cell == null )
					{
						tries--;
						continue;
					}
					if ( cell.isSolid( ) || cell.isStair( ) )
					{
						tries--;
						continue;
					}
					if ( cell.getID( ).startsWith( "TOWN_DOOR" ) )
					{
						tries--;
						continue;
					}
					if ( cell.getID( ).startsWith( "AIR" ) )
					{
						tries--;
						continue;
					}
					ret.setDirection( direction );
					return ret;
				}
				return null;

			}
			else
				return null;
		}
		return null;
	}

	public void setAttackPlayer( boolean value )
	{
		attackPlayer = value;
	}

	public void setOnDanger( boolean value )
	{
		onDanger = value;
	}
}