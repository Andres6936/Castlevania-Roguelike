package co.castle.ai.monster.boss;

import co.castle.action.Action;
import co.castle.action.monster.MonsterMissile;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.monster.boss.UnleashSickles;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.monster.MonsterAI;
import co.castle.monster.Monster;
import co.castle.player.Player;
import sz.util.Position;
import sz.util.Util;

public class DeathAI extends MonsterAI
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
		return "DEATH_AI";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		Player aPlayer = aMonster.getLevel().getPlayer();
		int playerDistance = Position.flatDistance(aMonster.getPosition(),
				aPlayer.getPosition());
		boolean powerActive;
		if (playerDistance > 20) {
			powerActive = false;
			powerCounter = 15;
		} else
			powerActive = true;

		if (powerActive) {
			if (powerCounter < 0) {
				powerCounter = 15;
				return new UnleashSickles();
			} else
				powerCounter--;
		}
		int directionToPlayer = aMonster.starePlayer();

		if ( directionToPlayer == -1 )
		{
			// A stationary here would do nothing
			int direction = Util.rand( 0, 7 );
			Action ret = new MonsterWalk( );
			ret.setDirection( direction );
			return ret;
		}
		if ( playerDistance < 5 )
		{
			// get away from player
			Action ret = new MonsterWalk( );
			int direction = Action.toIntDirection( Position
					.mul( Action.directionToVariation( directionToPlayer ), -1 ) );
			Position destPosition = Position.add( who.getPosition( ),
					Action.directionToVariation( direction ) );
			if ( who.getLevel( ).getMonsterAt( destPosition ) == null )
				ret.setDirection( direction );
			else
				ret.setDirection( Util.rand( 0, 7 ) );
			return ret;
		}
		else
		{
			if ( playerDistance < 10 && Util.chance( 70 ) )
			{
				// Walk to the player
				Action ret = new MonsterWalk( );
				ret.setDirection( directionToPlayer );
				return ret;
			}
			else
			{
				MonsterMissile ret = new MonsterMissile( );
				ret.setPosition( aPlayer.getPosition( ) );
				ret.set( "DIRECT", null, 20, "throws a shade ball", "missile",
						"SFX_FIREBALL", 5, "wav/fire.wav" );
				ret.setPerformer( aMonster );
				return ret;
			}

		}
	}

}