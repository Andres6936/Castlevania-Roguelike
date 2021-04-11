package co.castle.action.vkiller;

import co.castle.action.Action;
import co.castle.action.HeartAction;
import co.castle.feature.Feature;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;
import sz.util.Util;

public class Holy extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "You throw a vial of holy water!" );

		int damage = getDamage( );

		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			aLevel.addMessage( "The vial washes over you" );
			if ( Util.chance( 30 ) )
			{
				aLevel.addMessage( "You feel healed." );
				aPlayer.recoverHits( 2 );
			}
			else
			{
				aLevel.addMessage( "The bottle hits your head and breaks." );
			}
			return;
		}

		Line holyLine = new Line( performer.getPosition( ), targetPosition );
		Position flameOrigin = null;
		Cell destinationCell = null;
		int i = 0;
		for ( ; i < 3; i++ )
		{
			flameOrigin = holyLine.next( );
			destinationCell = aLevel.getMapCell( flameOrigin );
			if ( !aLevel.isValidCoordinate( flameOrigin )
					|| ( destinationCell != null && destinationCell.isSolid( ) ) )
			{
				break;
			}
		}

		aLevel.addEffect( EffectFactory.getSingleton( ).createDirectedEffect(
				performer.getPosition( ), targetPosition, "SFX_HOLY", i ) );

		if ( destinationCell == null )
		{
			flameOrigin = aLevel.getDeepPosition( flameOrigin );
			if ( flameOrigin == null )
			{
				aLevel.addMessage( "The vial falls to a pit!" );
				return;
			}
		}

		aLevel.addEffect( EffectFactory.getSingleton( ).createLocatedEffect( flameOrigin,
				"SFX_HOLY_FLAME" ) );
		aLevel.addSmartFeature( "BURNING_FLAME", flameOrigin );
		aLevel.addSmartFeature( "BURNING_FLAME", Position.add( flameOrigin,
				Action.directionToVariation( Action.UPLEFT ) ) );
		aLevel.addSmartFeature( "BURNING_FLAME", Position.add( flameOrigin,
				Action.directionToVariation( Action.UPRIGHT ) ) );
		aLevel.addSmartFeature( "BURNING_FLAME", Position.add( flameOrigin,
				Action.directionToVariation( Action.DOWNLEFT ) ) );
		aLevel.addSmartFeature( "BURNING_FLAME", Position.add( flameOrigin,
				Action.directionToVariation( Action.DOWNRIGHT ) ) );

		StringBuffer message = new StringBuffer( );
		for ( int x = flameOrigin.x - 1; x <= flameOrigin.x + 1; x++ )
			for ( int y = flameOrigin.y - 1; y <= flameOrigin.y + 1; y++ )
			{
				Position destinationPoint = new Position( x, y, flameOrigin.z );
				Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
				if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
				{
					message.append(
							"The " + destinationFeature.getDescription( ) + " burns" );
					Feature prize = destinationFeature.damage( aPlayer, damage );
					if ( prize != null )
					{
						message.append( " until consumption!" );

					}
					message.append( "." );
				}
				Monster targetMonster = performer.getLevel( )
						.getMonsterAt( destinationPoint );
				if ( targetMonster != null )
				{

					if ( targetMonster.wasSeen( ) )
						message.append( "The " + targetMonster.getDescription( )
								+ " catches in flame" );
					// targetMonster.damage(player.getWhipLevel());
					targetMonster.damage( message, damage );
					if ( targetMonster.isDead( ) )
					{
						if ( targetMonster.wasSeen( ) )
							message.append( " and is roasted!" );
						performer.getLevel( ).removeMonster( targetMonster );
					}
				}
			}
		aLevel.addMessage( message.toString( ) );

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 1;
	}

	public String getID( )
	{
		return "Holy";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the vial?";
	}

	public String getSFX( )
	{
		return "wav/breakpot.wav";
	}

	public boolean needsPosition( )
	{
		return true;
	}

	private int getDamage( )
	{
		return 6 + getPlayer( ).getShotLevel( ) + getPlayer( ).getSoulPower( );
	}

}