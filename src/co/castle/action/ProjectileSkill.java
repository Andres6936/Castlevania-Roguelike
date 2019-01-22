package co.castle.action;

import java.util.Vector;

import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;

public abstract class ProjectileSkill extends HeartAction
{
	private Vector hitMonsters = new Vector( 10 );

	protected Position finalPoint;

	public static final int PATH_DIRECT = 0, PATH_CURVED = 1, PATH_LINEAR = 2,
			PATH_HOVER = 3;

	public boolean allowsSelfTarget( )
	{
		return true;
	}

	public void execute( )
	{
		super.execute( );
		hitMonsters.clear( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		int attackHeight = aLevel.getMapCell( aPlayer.getPosition( ) ).getHeight( );
		if ( targetPosition.equals( performer.getPosition( ) ) && allowsSelfTarget( ) )
		{
			aLevel.addMessage( getSelfTargettedMessage( ) );
			// if (getPathType() == PATH_CURVED){
			Feature destinationFeature = aLevel
					.getFeatureAt( getPlayer( ).getPosition( ) );
			if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
			{
				String message = "The " + getSpellAttackDesc( ) + " hits the "
						+ destinationFeature.getDescription( );
				Feature prize = destinationFeature.damage( aPlayer, getDamage( ) );
				if ( prize != null )
				{
					message += " and destroys it.";
				}
				aLevel.addMessage( message );
			}

			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( getPlayer( ).getPosition( ) );

			if ( targetMonster != null )
			{
				if ( targetMonster.tryMagicHit( aPlayer, getDamage( ), getHit( ),
						targetMonster.wasSeen( ), getSpellAttackDesc( ),
						isWeaponAttack( ), performer.getPosition( ) ) )
				{
					hitMonsters.add( targetMonster );
				}
				;
			}
			// }
			return;
		}
		if ( showThrowMessage( ) )
			aLevel.addMessage( getShootMessage( ) );
		boolean hit = false;
		Line fireLine = new Line( performer.getPosition( ), targetPosition );

		boolean curved = false;
		int flyStart = 0, flyEnd = 0;
		if ( getPathType( ) == PATH_CURVED )
		{
			curved = true;
			flyStart = (int) Math.ceil( getRange( ) / 3.0D );
			flyEnd = (int) Math.ceil( 2 * ( getRange( ) / 3.0D ) );
		}
		int projectileHeight = attackHeight;
		for ( int i = 0; i <= getRange( ); i++ )
		{
			Position destinationPoint = fireLine.next( );
			finalPoint = destinationPoint;
			if ( !aLevel.isValidCoordinate( destinationPoint ) )
				continue;
			if ( curved )
			{
				if ( i > flyStart && i < flyEnd )
					projectileHeight = attackHeight + 1;
				else
					projectileHeight = attackHeight;
			}
			if ( aLevel.isSolid( destinationPoint ) || projectileHeight < -1
					+ aLevel.getMapCell( destinationPoint ).getHeight( ) )
			{
				// if (!piercesThru()){
				endProjectile( i - 1 );
				return;
				// }
			}

			String message = "";

			if ( aLevel.getMapCell( destinationPoint ) == null )
			{
				if ( getPathType( ) == PATH_HOVER )
				{
					// if (destinationPoint.z() < aLevel.getDepth()-1){
					destinationPoint = aLevel.getDeepPosition( destinationPoint );
					if ( destinationPoint == null )
					{
						endProjectile( i );
						return;
					}
					projectileHeight = aLevel.getMapCell( destinationPoint ).getHeight( );
					/*
					 * } else { endProjectile(i); return; }
					 */
				}
			}

			int destinationHeight = 0;

			if ( aLevel.getMapCell( destinationPoint ) != null )
			{
				destinationHeight = aLevel.getMapCell( destinationPoint ).getHeight( );
			}

			if ( getPathType( ) == PATH_HOVER )
			{
				if ( destinationHeight < projectileHeight )
					projectileHeight = destinationHeight;
				else if ( destinationHeight > projectileHeight )
				{
					if ( getSFXID( ) != null )
						drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
								performer.getPosition( ), targetPosition, getSFXID( ),
								i ) );
					return;
				}
			}

			if ( destinationHeight == projectileHeight )
			{
				Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
				if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
				{
					message = "The " + getSpellAttackDesc( ) + " hits the "
							+ destinationFeature.getDescription( );
					if ( !piercesThru( ) )
						if ( getSFXID( ) != null )
							drawEffect( EffectFactory.getSingleton( )
									.createDirectedEffect( performer.getPosition( ),
											targetPosition, getSFXID( ), i ) );
					Feature prize = destinationFeature.damage( aPlayer, getDamage( ) );
					if ( prize != null )
					{
						message += " and destroys it.";
					}
					aLevel.addMessage( message );
					if ( !piercesThru( ) )
						return;
				}
			}
			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( destinationPoint );

			if ( targetMonster != null )
			{
				// int monsterHeight = destinationHeight + (targetMonster.isFlying() ? 1 :
				// 0);
				int monsterHeight = destinationHeight + targetMonster.getHoverHeight( );

				if ( projectileHeight == monsterHeight || getPathType( ) == PATH_DIRECT )
				{
					if ( targetMonster.tryMagicHit( aPlayer, getDamage( ), getHit( ),
							targetMonster.wasSeen( ), getSpellAttackDesc( ),
							isWeaponAttack( ), performer.getPosition( ) ) )
					{
						hit = true;
						hitMonsters.add( targetMonster );
						if ( !piercesThru( ) )
						{
							if ( getSFXID( ) != null )
								drawEffect( EffectFactory.getSingleton( )
										.createDirectedEffect( aPlayer.getPosition( ),
												targetPosition, getSFXID( ), i ) );
							return;
						}

					}
					;
				}
				else if ( projectileHeight < monsterHeight )
				{
					aLevel.addMessage( "The " + getSpellAttackDesc( )
							+ " flies under the " + targetMonster.getDescription( ) );
				}
				else
				{
					aLevel.addMessage( "The " + getSpellAttackDesc( ) + " flies over the "
							+ targetMonster.getDescription( ) );
				}
			}
		}
		if ( !hit || piercesThru( ) )
			if ( getSFXID( ) != null )
				drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
						aPlayer.getPosition( ), targetPosition, getSFXID( ),
						getRange( ) - 1 ) );

	}

	public abstract int getDamage( );

	public abstract int getHit( );
	public Vector getHitMonsters( )
	{
		return hitMonsters;
	}

	public abstract int getPathType( );

	public abstract String getPromptPosition( );

	public abstract int getRange( );

	public abstract String getSelfTargettedMessage( );

	public abstract String getSFXID( );

	public abstract String getShootMessage( );

	public abstract String getSpellAttackDesc( );

	public boolean isWeaponAttack( )
	{
		return false;
	}

	public boolean needsPosition( )
	{
		return true;
	}

	public boolean piercesThru( )
	{
		return false;
	}

	public boolean showThrowMessage( )
	{
		return true;
	}

	private void endProjectile( int depth )
	{
		if ( getSFXID( ) != null )
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					performer.getPosition( ), targetPosition, getSFXID( ), depth ) );
	}
}