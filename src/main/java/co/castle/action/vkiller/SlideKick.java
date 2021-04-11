package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Line;
import sz.util.Position;

public class SlideKick extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );

		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			aLevel.addMessage( "You fall back." );
			return;
		}
		aLevel.addMessage( "You slide!" );
		int damage = 5 + aPlayer.getAttack( );

		Line fireLine = new Line( performer.getPosition( ), targetPosition );
		Position previousPoint = aPlayer.getPosition( );
		int projectileHeight = aLevel.getMapCell( aPlayer.getPosition( ) ).getHeight( );
		for ( int i = 0; i < 3; i++ )
		{
			Position destinationPoint = fireLine.next( );
			if ( aLevel.isSolid( destinationPoint ) )
			{
				drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
						performer.getPosition( ), targetPosition, "SFX_SLIDEKICK", i ) );
				aPlayer.landOn( previousPoint );
				return;
			}

			String message = "";

			int destinationHeight = aLevel.getMapCell( destinationPoint ).getHeight( );

			if ( destinationHeight == projectileHeight )
			{
				Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
				if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
				{
					message = "You hit the " + destinationFeature.getDescription( );
					drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
							performer.getPosition( ), targetPosition, "SFX_SLIDEKICK",
							i ) );
					Feature prize = destinationFeature.damage( aPlayer, damage );
					if ( prize != null )
					{
						message += " and destroys it.";
					}
					aLevel.addMessage( message );
					aPlayer.landOn( previousPoint );
					return;
				}
			}
			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( destinationPoint );

			if ( targetMonster != null )
			{
				int monsterHeight = destinationHeight + targetMonster.getHoverHeight( );
				if ( projectileHeight == monsterHeight )
				{
					if ( targetMonster.tryMagicHit( aPlayer, damage, 100,
							targetMonster.wasSeen( ), "dash", false,
							performer.getPosition( ) ) )
					{
						drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
								aPlayer.getPosition( ), targetPosition, "SFX_SLIDEKICK",
								i ) );
						aPlayer.landOn( previousPoint );
						return;
					}
					;
				}
				else if ( projectileHeight < monsterHeight )
				{
					aLevel.addMessage(
							"You slide under the " + targetMonster.getDescription( ) );
				}
				else
				{
					aLevel.addMessage(
							"You slide over the " + targetMonster.getDescription( ) );
				}
			}
			previousPoint = destinationPoint;
		}

		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
				aPlayer.getPosition( ), targetPosition, "SFX_SLIDEKICK", 4 ) );
		aPlayer.landOn( previousPoint );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getWalkCost( ) * 1.4 );
	}

	public int getHeartCost( )
	{
		return 2;
	}

	public String getID( )
	{
		return "SlideKick";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to slide?";
	}

	public String getSFX( )
	{
		return "wav/scrch.wav";
	}

	public boolean needsPosition( )
	{
		return true;
	}
}