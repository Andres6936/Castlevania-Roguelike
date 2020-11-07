package co.castle.ai.monster.boss;

import co.castle.action.Action;
import co.castle.action.ActionFactory;
import co.castle.action.monster.MonsterCharge;
import co.castle.action.monster.MonsterMissile;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.monster.SummonMonster;
import co.castle.action.monster.boss.ShadowApocalypse;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.monster.MonsterAI;
import co.castle.ai.monster.RangedAttack;
import co.castle.monster.Monster;
import sz.util.Position;
import sz.util.Util;

public class DemonDraculaAI extends MonsterAI
{
	private int chargeCounter = 99;

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
		return "DEMON_DRACULA_AI";
	}

	public Action selectAction( Actor who )
	{
		Monster aMonster = (Monster) who;
		int directionToPlayer = aMonster.starePlayer( );
		int playerDistance = Position.flatDistance( aMonster.getPosition( ),
				aMonster.getLevel( ).getPlayer( ).getPosition( ) );
		if ( directionToPlayer == -1 )
		{
			int direction = Util.rand( 0, 7 );
			Action ret = new MonsterWalk( );
			ret.setDirection( direction );
			return ret;
		}
		else
		{
			// Randomly decide if will approach the player or attack
			if ( Util.chance( 70 ) )
			{
				if ( playerDistance < 5 )
				{
					if ( Util.chance( 70 ) )
						return new ShadowApocalypse( );
					else
					{
						Action ret = new MonsterWalk( );
						int direction = Action.toIntDirection( Position.mul(
								Action.directionToVariation( directionToPlayer ), -1 ) );
						ret.setDirection( direction );
						return ret;
					}
				}

			}
			else if ( rangedAttacks != null && Util.chance( 80 ) )
			{
				// Try to attack the player
				for (RangedAttack element : rangedAttacks) {
					if (element.getRange() >= playerDistance
							&& Util.chance(element.getFrequency())) {
						// Perform the attack
						Action ret = ActionFactory.getActionFactory()
								.getAction(element.getAttackId());
						if (element.getChargeCounter() > 0) {
							if (chargeCounter == 0) {
								chargeCounter = element.getChargeCounter();
							} else {
								chargeCounter--;
								continue;
							}
						}

						if (ret instanceof MonsterMissile) {
							((MonsterMissile) ret).set(element.getAttackType(),
									element.getStatusEffect(), element.getRange(),
									element.getAttackMessage(), element.getEffectType(),
									element.getEffectID(), element.getDamage(),
									element.getEffectWav());
						} else if (ret instanceof MonsterCharge) {
							((MonsterCharge) ret).set(element.getRange(),
									element.getAttackMessage(), element.getDamage(),
									element.getEffectWav());
						} else if (ret instanceof SummonMonster) {
							((SummonMonster) ret).set(element.getSummonMonsterId(),
									element.getAttackMessage());
						}
						ret.setPosition(who.getLevel().getPlayer().getPosition());
						return ret;
					}
				}
			}
			// Couldnt attack the player, so walk to him
			Action ret = new MonsterWalk( );
			ret.setDirection( directionToPlayer );
			return ret;
		}
	}
}