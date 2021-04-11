package co.castle.action;

import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.OutParameter;
import sz.util.Position;

public abstract class BeamProjectileSkill extends ProjectileSkill
{
	private boolean[ ] deadLines = new boolean[ 3 ];

	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public void execute( )
	{
		if ( targetPosition.equals( performer.getPosition( ) ) )
			return;
		reduceHearts( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		int attackHeight = aLevel.getMapCell( aPlayer.getPosition( ) ).getHeight( );
		if ( showThrowMessage( ) )
			aLevel.addMessage( getShootMessage( ) );
		targetDirection = getGeneralDirection( performer.getPosition( ), targetPosition );

		OutParameter outPosition1 = new OutParameter( );
		OutParameter outPosition2 = new OutParameter( );
		Action.fillNormalPositions( performer.getPosition( ), targetDirection,
				outPosition1, outPosition2 );

		Position start1 = (Position) outPosition1.getObject( );
		Position start2 = (Position) outPosition2.getObject( );

		Action.fillNormalPositions( targetPosition, targetDirection, outPosition1,
				outPosition2 );
		Position end1 = (Position) outPosition1.getObject( );
		Position end2 = (Position) outPosition2.getObject( );

		/*
		 * aLevel.addFeature("SMALLHEART", start1); aLevel.addFeature("SMALLHEART",
		 * start2);
		 */

		Line fireLine = new Line( performer.getPosition( ), targetPosition );
		Line fireLine1 = new Line( start1, end1 );
		Line fireLine2 = new Line( start2, end2 );
		deadLines[ 0 ] = false;
		deadLines[ 1 ] = false;
		deadLines[ 2 ] = false;
		fireLine.next( );
		int projectileHeight = attackHeight;
		for ( int i = 0; i < getRange( ); i++ )
		{
			for ( int hits = 0; hits < 3; hits++ )
			{
				if ( deadLines[ hits ] )
					continue;
				Position destinationPoint = null;
				Position originPoint = null;
				Position finalPoint = null;
				switch ( hits )
				{
				case 0:
					destinationPoint = fireLine.next( );
					originPoint = performer.getPosition( );
					finalPoint = targetPosition;
					break;
				case 1:
					destinationPoint = fireLine1.next( );
					originPoint = start1;
					finalPoint = end1;
					break;
				case 2:
					destinationPoint = fireLine2.next( );
					originPoint = start2;
					finalPoint = end2;
					break;
				}
				if ( !aLevel.isValidCoordinate( destinationPoint ) )
					continue;
				if ( plottedLocatedEffect( ) != null )
				{
					drawEffect( EffectFactory.getSingleton( ).createLocatedEffect(
							destinationPoint, plottedLocatedEffect( ) ) );
				}
				if ( aLevel.isSolid( destinationPoint ) )
				{
					if ( !piercesThru( ) )
					{
						drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
								originPoint, finalPoint, getSFXID( ), i ) );
						deadLines[ hits ] = true;
						continue;
					}
				}
				// aLevel.addBlood(destinationPoint,1);
				String message = "";

				int destinationHeight = aLevel.getMapCell( destinationPoint )
						.getHeight( );

				if ( destinationHeight == projectileHeight )
				{
					Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
					if ( destinationFeature != null
							&& destinationFeature.isDestroyable( ) )
					{
						message = "The " + getSpellAttackDesc( ) + " hits the "
								+ destinationFeature.getDescription( );
						if ( !piercesThru( ) )
							drawEffect(
									EffectFactory.getSingleton( ).createDirectedEffect(
											originPoint, finalPoint, getSFXID( ), i ) );
						Feature prize = destinationFeature.damage( aPlayer,
								getDamage( ) );
						if ( prize != null )
						{
							message += " and destroys it.";
						}
						aLevel.addMessage( message );
						if ( !piercesThru( ) )
						{
							deadLines[ hits ] = true;
							continue;
						}
					}
				}
				Monster targetMonster = performer.getLevel( )
						.getMonsterAt( destinationPoint );

				if ( targetMonster != null )
				{
					// int monsterHeight = destinationHeight + (targetMonster.isFlying() ?
					// 1 : 0);
					int monsterHeight = destinationHeight
							+ targetMonster.getHoverHeight( );

					if ( projectileHeight == monsterHeight )
					{
						if ( targetMonster.tryMagicHit( aPlayer, getDamage( ), getHit( ),
								targetMonster.wasSeen( ), getSpellAttackDesc( ),
								isWeaponAttack( ), performer.getPosition( ) ) )
						{
							if ( !piercesThru( ) )
							{
								drawEffect( EffectFactory.getSingleton( )
										.createDirectedEffect( originPoint, finalPoint,
												getSFXID( ), i ) );
								deadLines[ hits ] = true;
								continue;
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
						aLevel.addMessage( "The " + getSpellAttackDesc( )
								+ " flies over the " + targetMonster.getDescription( ) );
					}
				}
			}
		}
		for ( int hits = 0; hits < 3; hits++ )
		{
			Position originPoint = null;
			Position finalPoint = null;
			switch ( hits )
			{
			case 0:
				originPoint = performer.getPosition( );
				finalPoint = targetPosition;
				break;
			case 1:
				originPoint = start1;
				finalPoint = end1;
				break;
			case 2:
				originPoint = start2;
				finalPoint = end2;
				break;
			}
			if ( !deadLines[ hits ] )
				drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
						originPoint, finalPoint, getSFXID( ), getRange( ) ) );
		}
	}

	public String plottedLocatedEffect( )
	{
		return null;
	}
}