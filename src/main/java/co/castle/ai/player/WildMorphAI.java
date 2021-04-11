package co.castle.ai.player;

import co.castle.action.Action;
import co.castle.action.Attack;
import co.castle.action.Walk;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.player.Player;
import sz.util.Position;
import sz.util.Util;

public class WildMorphAI implements ActionSelector
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
		return "WILD_MORPH_AI";
	}

	public Action selectAction( Actor who )
	{
		Player aPlayer = (Player) who;
		if ( aPlayer.getEnemy( ) != null )
		{
			if ( !aPlayer.getLevel( ).getMonsters( ).contains( aPlayer.getEnemy( ) ) )
			{
				aPlayer.setEnemy( null );
			}
		}

		int directionToMonster = -1;
		if ( aPlayer.getEnemy( ) != null )
		{
			directionToMonster = aPlayer.stareMonster( aPlayer.getEnemy( ) );
		}
		else
		{
			directionToMonster = aPlayer.stareMonster( );
		}

		if ( directionToMonster == -1 )
		{
			return null;
		}
		else
		{
			Position destination = Position.add( who.getPosition( ),
					Action.directionToVariation( directionToMonster ) );
			if ( aPlayer.getLevel( ).getMonsterAt( destination ) != null )
				aPlayer.setEnemy( aPlayer.getLevel( ).getMonsterAt( destination ) );
			if ( aPlayer.getEnemy( ) != null
					&& destination.equals( aPlayer.getEnemy( ).getPosition( ) ) )
			{
				Action ret = new Attack( );
				ret.setPerformer( aPlayer );
				ret.setDirection( directionToMonster );
				return ret;
			}
			else
			{
				Action ret = new Walk( );
				if ( !who.getLevel( ).isWalkable( Position.add( who.getPosition( ),
						Action.directionToVariation( directionToMonster ) ) ) )
				{
					directionToMonster = Util.rand( 0, 7 );
					ret.setDirection( directionToMonster );
				}
				else
				{
					ret.setDirection( directionToMonster );
				}
				return ret;
			}
		}

	}
}