package co.castle.feature.action;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.feature.SmartFeature;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.Effect;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;
import sz.util.Util;

public class CrossBack extends Action
{
	public boolean canPerform( Actor a )
	{
		if ( a instanceof Player )
		{
			Player aPlayer = (Player) a;
			if ( aPlayer.getHearts( ) < 1 )
			{
				invalidationMessage = "You don't have enough hearts";
				return false;
			}
		}
		return true;
	}

	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "The cross comes back!" );
		int damage = 3 + aLevel.getPlayer( ).getShotLevel( )
				+ aLevel.getPlayer( ).getSoulPower( );

		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			if ( Util.chance( 50 ) )
			{
				aLevel.addMessage( "The cross falls heads! You catch the cross." );
			}
			else
			{
				aLevel.addMessage( "The cross falls tails! You catch the cross." );
			}
			return;
		}

		Line crossLine = new Line( performer.getPosition( ), targetPosition );

		int startHeight = ( (SmartFeature) performer ).getHeight( );

		crossLine.next( );
		Position destinationPoint = null;
		int i = 0;
		for ( i = 0; i < 20; i++ )
		{
			destinationPoint = crossLine.next( );
			if ( destinationPoint.equals( aLevel.getPlayer( ).getPosition( ) ) )
			{
				aLevel.addMessage( "You catch the cross" );
				break;
			}

			Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
			if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
			{
				aLevel.addMessage(
						"The cross cuts the " + destinationFeature.getDescription( ) );
				destinationFeature.damage( aLevel.getPlayer( ), damage );
			}

			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( destinationPoint );
			Cell destinationCell = performer.getLevel( ).getMapCell( destinationPoint );

			if ( targetMonster != null )
			{
				if ( ( targetMonster.isInWater( ) && targetMonster.canSwim( ) )
						|| destinationCell.getHeight( ) < startHeight - 1 )
				{
					if ( targetMonster.wasSeen( ) )
						aLevel.addMessage( "The cross flies over the "
								+ targetMonster.getDescription( ) );
				}
				else if ( destinationCell.getHeight( ) > startHeight + 1 )
				{
					if ( targetMonster.wasSeen( ) )
						aLevel.addMessage( "The cross flies under the "
								+ targetMonster.getDescription( ) );
				}
				else
				{
					StringBuffer buff = new StringBuffer( );
					if ( targetMonster.wasSeen( ) )
						buff.append( "The cross slashes the "
								+ targetMonster.getDescription( ) );
					targetMonster.damage( buff, damage );
					aLevel.addMessage( buff.toString( ) );
					if ( targetMonster.isDead( ) )
					{
						performer.getLevel( ).removeMonster( targetMonster );
					}
				}
			}
		}

		Effect crossEffect = EffectFactory.getSingleton( ).createDirectedEffect(
				performer.getPosition( ), targetPosition, "SFX_CROSS", i );
		crossEffect.setPosition( performer.getLevel( ).getPlayer( ).getPosition( ) );
		drawEffect( crossEffect );

	}

	public int getCost( )
	{
		if ( performer instanceof Player )
		{
			Player p = (Player) performer;
			return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
		}
		else
			return 40;
	}

	public String getID( )
	{
		return "Cross";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the Cross?";
	}

	public String getSFX( )
	{
		return "wav/misswipe.wav";
	}

	public boolean needsPosition( )
	{
		return true;
	}
}
