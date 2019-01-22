package co.castle.action;

import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Consts;
import co.castle.player.Player;
import co.castle.ui.effects.Effect;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;
import sz.util.Util;

public class Attack extends Action
{
	static class WhipFireball extends ProjectileSkill
	{
		public int getDamage( )
		{
			return 4;
		}

		public int getHeartCost( )
		{
			return 0;
		}

		public int getHit( )
		{
			return 100;
		}

		public String getID( )
		{
			return null;
		}

		public int getPathType( )
		{
			return PATH_LINEAR;
		}

		public String getPromptPosition( )
		{
			return null;
		}

		public int getRange( )
		{
			return 15;
		}

		public String getSelfTargettedMessage( )
		{
			return null;
		}

		public String getSFXID( )
		{
			return "SFX_WHIP_FIREBALL";
		}

		public String getShootMessage( )
		{
			return "A fireball flies out from your whip";
		}

		public String getSpellAttackDesc( )
		{
			return "fireball";
		}

		public boolean needsPosition( )
		{
			return false;
		}

	}

	private int reloadTime;

	private Item weapon;
	public boolean canPerform( Actor a )
	{
		Player player = getPlayer( a );
		if ( !player.canAttack( ) )
		{
			invalidationMessage = "You can't attack";
			return false;
		}
		if ( player.getWeapon( ) != null
				&& player.getWeapon( ).getWeaponCategory( ) == ItemDefinition.CAT_BOWS )
		{
			Monster nearest = player.getNearestMonster( );
			if ( nearest != null )
			{
				if ( Position.distance( nearest.getPosition( ),
						player.getPosition( ) ) < 2 )
				{
					invalidationMessage = "You can't aim your "
							+ player.getWeapon( ).getDescription( )
							+ " this close to the enemy, get away!";
					return false;
				}
			}
		}
		return true;

	}

	public void execute( )
	{
		Position var = directionToVariation( targetDirection );
		Player player = null;
		reloadTime = 0;
		try
		{
			player = (Player) performer;
		}
		catch ( ClassCastException cce )
		{
			return;
		}

		weapon = player.getWeapon( );
		Level aLevel = performer.getLevel( );
		if ( !player.canAttack( ) )
		{
			aLevel.addMessage( "You can't attack!" );
			return;
		}

		/*
		 * if (player.hasCounter(Consts.C_WOLFMORPH) ||
		 * player.hasCounter(Consts.C_WOLFMORPH2)) weapon = null;
		 */

		if ( weapon == null || weapon.getDefinition( ).getWeaponCategory( )
				.equals( ItemDefinition.CAT_UNARMED ) )
		{
			if ( targetDirection == Action.SELF
					&& aLevel.getMonsterAt( player.getPosition( ) ) == null )
			{
				aLevel.addMessage( "Don't hit yourself" );
				return;
			}
			Position targetPosition = Position.add( player.getPosition( ),
					Action.directionToVariation( targetDirection ) );
			Monster targetMonster = aLevel.getMonsterAt( targetPosition );
			String attackDescription = player.getPunchDescription( );
			int punchDamage = player.getPunchDamage( );
			int push = player.getPunchPush( );

			if ( targetMonster != null
					&& targetMonster.getStandingHeight( ) == player.getStandingHeight( ) )
			{
				StringBuffer buff = new StringBuffer( "You " + attackDescription + " the "
						+ targetMonster.getDescription( ) + "!" );
				targetMonster.damageWithWeapon( buff, punchDamage );
				aLevel.addMessage( buff.toString( ) );
				if ( push != 0 )
					pushMonster( targetMonster, aLevel, push );

			}
			Feature targetFeature = aLevel.getFeatureAt( targetPosition );
			if ( targetFeature != null && targetFeature.isDestroyable( ) )
			{
				aLevel.addMessage( "You " + attackDescription + " the "
						+ targetFeature.getDescription( ) );
				targetFeature.damage( player, punchDamage );
			}

			Cell targetMapCell = aLevel.getMapCell( targetPosition );
			if ( targetMapCell != null && targetMapCell.isSolid( ) )
			{
				aLevel.addMessage( "You " + attackDescription + " the "
						+ targetMapCell.getShortDescription( ) );
			}
			return;
		}

		if ( targetDirection == Action.SELF
				&& aLevel.getMonsterAt( player.getPosition( ) ) == null )
		{
			aLevel.addMessage( "That's a coward way to give up!" );
			return;
		}

		targetPosition = Position.add( performer.getPosition( ),
				Action.directionToVariation( targetDirection ) );
		int startHeight = aLevel.getMapCell( player.getPosition( ) ).getHeight( )
				+ player.getHoverHeight( );
		ItemDefinition weaponDef = weapon.getDefinition( );

		if ( weapon.getReloadTurns( ) > 0 )
			if ( weapon.getRemainingTurnsToReload( ) == 0 )
			{
				if ( !reload( weapon, player ) )
					return;
			}

		String[ ] sfx = weaponDef.getAttackSFX( ).split( " " );
		if ( sfx.length > 0 )
			if ( sfx[ 0 ].equals( "MELEE" ) )
			{
				Effect me = EffectFactory.getSingleton( ).createDirectionalEffect(
						performer.getPosition( ), targetDirection, weapon.getRange( ),
						"SFX_WP_" + weaponDef.getID( ) );
				aLevel.addEffect( me );
			}
			else if ( sfx[ 0 ].equals( "BEAM" ) )
			{
				Effect me = EffectFactory.getSingleton( ).createDirectedEffect(
						performer.getPosition( ), targetPosition,
						"SFX_WP_" + weaponDef.getID( ), weapon.getRange( ) );
				aLevel.addEffect( me );
			}
			else if ( sfx[ 0 ].equals( "MISSILE" ) )
			{
				Effect me = EffectFactory.getSingleton( ).createDirectedEffect(
						performer.getPosition( ), targetPosition,
						"SFX_WP_" + weaponDef.getID( ), weapon.getRange( ) );
				if ( !weapon.isSlicesThrough( ) )
				{
					int i = 0;
					for ( i = 0; i < weapon.getRange( ); i++ )
					{
						Position destinationPoint = Position.add(
								performer.getPosition( ), Position.mul( var, i + 1 ) );
						Cell destinationCell = aLevel.getMapCell( destinationPoint );
						Feature destinationFeature = aLevel
								.getFeatureAt( destinationPoint );
						if ( destinationFeature != null
								&& destinationFeature.isDestroyable( ) )
							break;
						Monster targetMonster = performer.getLevel( )
								.getMonsterAt( destinationPoint );
						if ( targetMonster != null
								&& !( targetMonster.isInWater( )
										&& targetMonster.canSwim( ) )
								&& ( destinationCell.getHeight( ) == aLevel
										.getMapCell( player.getPosition( ) ).getHeight( )
										|| destinationCell.getHeight( ) - 1 == aLevel
												.getMapCell( player.getPosition( ) )
												.getHeight( )
										|| destinationCell.getHeight( ) == aLevel
												.getMapCell( player.getPosition( ) )
												.getHeight( ) - 1 ) )
							break;
					}
					me = EffectFactory.getSingleton( ).createDirectedEffect(
							performer.getPosition( ), targetPosition,
							"SFX_WP_" + weaponDef.getID( ), i );
				}
				aLevel.addEffect( me );
			}

		boolean hitsSomebody = false;
		boolean hits = false;
		for ( int i = 0; i < weapon.getRange( ); i++ )
		{
			Position destinationPoint = Position.add( performer.getPosition( ),
					Position.mul( var, i + 1 ) );
			Cell destinationCell = aLevel.getMapCell( destinationPoint );
			/* aLevel.addMessage("You hit the "+destinationCell.getID()); */

			String message = "";

			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( destinationPoint );
			message = "";

			if ( targetMonster != null )
			{
				if ( ( targetMonster.isInWater( ) && targetMonster.canSwim( ) )
						|| destinationCell.getHeight( ) < startHeight - 1 )
				{
					if ( targetMonster.wasSeen( ) )
						aLevel.addMessage( "The attack passes over the "
								+ targetMonster.getDescription( ) );
				}
				else
				{
					if ( destinationCell.getHeight( ) > startHeight + 1 )
					{
						if ( weapon.getVerticalRange( ) > 0 )
						{
							hits = true;
						}
						else
						{
							if ( targetMonster.wasSeen( ) )
							{
								aLevel.addMessage( "The attack passes under the "
										+ targetMonster.getDescription( ) );
							}
						}
					}
					else
					{
						hits = true;
					}
				}
			}

			if ( hits )
			{
				hits = false;
				hitsSomebody = true;
				int penalty = (int) ( Position.distance( targetMonster.getPosition( ),
						player.getPosition( ) ) / 4 );
				int attack = player.getWeaponAttack( ) + Util.rand( 0, 2 );
				attack -= penalty;
				if ( attack < 1 )
					attack = 1;
				StringBuffer hitMsg = new StringBuffer( );
				if ( weapon.isHarmsUndead( ) && targetMonster.isUndead( ) )
				{
					attack *= 2;
					if ( targetMonster.wasSeen( ) )
						hitMsg.append( "You critically damage the "
								+ targetMonster.getDescription( ) + "!" );
					else
						hitMsg.append( "You hit something!" );
				}
				else
				{
					if ( targetMonster.wasSeen( ) )
						hitMsg.append( "You hit the " + targetMonster.getDescription( ) );
					else
						hitMsg.append( "You hit something!" );
				}

				// targetMonster.damage(player.getWhipLevel());

				targetMonster.damageWithWeapon( hitMsg, attack );
				aLevel.addMessage( hitMsg.toString( ) );
			}

			Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
			if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
			{
				hitsSomebody = true;
				if ( player.sees( destinationPoint ) )
					message = "You hit the " + destinationFeature.getDescription( );
				else
					message = "You hit something";
				destinationFeature.damage( player, weapon.getAttack( ) );
				aLevel.addMessage( message );
			}

			Cell targetMapCell = aLevel.getMapCell( destinationPoint );
			if ( targetMapCell != null && targetMapCell.isSolid( ) )
			{
				hitsSomebody = true;
				// aLevel.addMessage("You hit the "+targetMapCell.getShortDescription());
			}

			if ( hitsSomebody && !weapon.isSlicesThrough( ) )
				break;
		}
		/*
		 * if (!hitsSomebody) aLevel.addMessage("You swing at the air!");
		 */
		if ( !hitsSomebody && player.hasCounter( Consts.C_FIREBALL_WHIP ) )
		{
			Action fireball = new WhipFireball( );
			fireball.setPerformer( performer );
			fireball.setPosition( targetPosition );
			fireball.execute( );
		}
		if ( weapon.getReloadTurns( ) > 0 && weapon.getRemainingTurnsToReload( ) > 0 )
			weapon.setRemainingTurnsToReload( weapon.getRemainingTurnsToReload( ) - 1 );
		if ( weaponDef.isSingleUse( ) )
		{
			if ( weapon.getReloadTurns( ) > 0 )
			{
				if ( weapon.getRemainingTurnsToReload( ) == 0 )
				{
					player.setWeapon( null );
				}
			}
			else
			{
				if ( player.hasItem( weapon ) )
					player.reduceQuantityOf( weapon );
				else
					player.setWeapon( null );
			}
		}
	}

	public int getCost( )
	{
		Player player = (Player) performer;
		if ( weapon != null )
		{
			return player.getAttackCost( ) + weapon.getAttackCost( ) + reloadTime;
		}
		else
		{
			return (int) ( player.getAttackCost( ) * 1.5 );
		}
	}

	public int getDirection( )
	{
		return targetDirection;
	}

	public String getID( )
	{
		return "Attack";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to attack?";
	}

	public String getSFX( )
	{
		Player p = (Player) performer;
		weapon = p.getWeapon( );
		if ( weapon != null && !weapon.getAttackSound( ).equals( "DEFAULT" ) )
		{
			return weapon.getAttackSound( );
		}
		else
		{
			if ( ( (Player) performer ).getSex( ) == Player.MALE )
				return "wav/punch_male.wav";
			else
				return "wav/punch_female.wav";
		}
	}

	public boolean needsDirection( )
	{
		return true;
	}

	private void pushMonster( Monster targetMonster, Level aLevel, int spaces )
	{
		Position varP = Action.directionToVariation( targetDirection );
		Position runner = Position.add( targetMonster.getPosition( ), varP );
		for ( int i = 0; i < spaces; i++ )
		{
			Cell fly = aLevel.getMapCell( runner );
			if ( fly == null )
				return;
			if ( !fly.isSolid( ) )
			{
				if ( fly.isWater( ) || fly.isShallowWater( ) )
				{
					if ( targetMonster.canSwim( ) )
					{
						if ( i == spaces - 1 )
							aLevel.addMessage(
									"You throw the " + targetMonster.getDescription( )
											+ " into the water!" );
						targetMonster.setPosition( runner );

					}
					else if ( targetMonster.isEthereal( ) )
					{
						targetMonster.setPosition( runner );
					}
					else
					{
						aLevel.addMessage( "You throw the "
								+ targetMonster.getDescription( ) + " into the water!" );
						aLevel.addMessage(
								"The " + targetMonster.getDescription( ) + " drowns!" );
						targetMonster.die( );
						aLevel.removeMonster( targetMonster );
						return;
					}
				}
				else
				{
					targetMonster.setPosition( runner );
				}
			}
			else
			{
				StringBuffer buff = new StringBuffer(
						"You smash the " + targetMonster.getDescription( )
								+ " against the " + fly.getDescription( ) + "!" );
				targetMonster.damage( buff, 2 );
				aLevel.addMessage( buff.toString( ) );
			}
			runner.add( varP );
		}

	}

	private boolean reload( Item weapon, Player aPlayer )
	{
		if ( weapon != null )
		{
			if ( aPlayer.getGold( ) < weapon.getDefinition( ).getReloadCostGold( ) )
			{
				aPlayer.getLevel( )
						.addMessage( "You can't reload the " + weapon.getDescription( ) );
				return false;
			}
			else if ( aPlayer.getHearts( ) < 2 )
			{
				aPlayer.getLevel( )
						.addMessage( "You can't reload the " + weapon.getDescription( ) );
				return false;
			}
			else
			{
				weapon.reload( );
				aPlayer.reduceGold( weapon.getDefinition( ).getReloadCostGold( ) );
				aPlayer.reduceHearts( 2 );
				aPlayer.getLevel( )
						.addMessage( "You reload the " + weapon.getDescription( ) + " ("
								+ weapon.getDefinition( ).getReloadCostGold( )
								+ " gold)" );
				reloadTime = 10 * weapon.getDefinition( ).getReloadTurns( );
				return true;
			}
		}
		else
			aPlayer.getLevel( ).addMessage( "You can't reload yourself" );
		return false;
	}
}