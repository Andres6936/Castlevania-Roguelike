package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.monster.VMonster;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;
import sz.util.Util;

public class ItemBreakHoly extends HeartAction
{
	public void execute( )
	{
		Player aPlayer = (Player) performer;
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "You jump! You unleash a rain of holy water!" );
		UserInterface.getUI( ).drawEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( aPlayer.getPosition( ), "SFX_HOLY_RAINSPLASH" ) );
		int damage = 6 + getPlayer( ).getShotLevel( ) + getPlayer( ).getSoulPower( );

		VMonster monsters = aLevel.getMonsters( );
		for ( int i = 0; i < monsters.size( ); i++ )
		{

			if ( monsters.elementAt( i ).getPosition( ).z == performer.getPosition( ).z
					&& Position.distance( monsters.elementAt( i ).getPosition( ),
							performer.getPosition( ) ) < 4 )
			{
				StringBuffer buff = new StringBuffer( );
				if ( monsters.elementAt( i ).wasSeen( ) )
				{
					buff.append( "The " + monsters.elementAt( i ).getDescription( )
							+ " is splashed with holy water!" );
				}
				monsters.elementAt( i ).damage( buff, damage );
				aLevel.addMessage( buff.toString( ) );
			}
		}
		UserInterface.getUI( ).refresh( );

		int drops = Util.rand( 20, 40 );

		for ( int i = 0; i < drops; i++ )
		{
			int xdif = 5 - Util.rand( 0, 10 );
			int ydif = 5 - Util.rand( 0, 10 );
			Position destinationPoint = Position.add( aPlayer.getPosition( ),
					new Position( xdif, ydif ) );
			if ( aLevel.isValidCoordinate( destinationPoint ) )
			{
				aLevel.addEffect( EffectFactory.getSingleton( )
						.createLocatedEffect( destinationPoint, "SFX_HOLY_RAINDROP" ) );
				UserInterface.getUI( ).refresh( );
				Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
				if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
				{
					Feature prize = destinationFeature.damage( aPlayer, damage );
					if ( prize != null )
					{
						aLevel.addMessage( "The " + destinationFeature.getDescription( )
								+ " burns until consumption!" );
					}
					else
					{
						aLevel.addMessage( "The " + destinationFeature.getDescription( )
								+ " burns." );
					}
				}
				Monster targetMonster = performer.getLevel( )
						.getMonsterAt( destinationPoint );
				if ( targetMonster != null )
				{
					StringBuffer buff = new StringBuffer( );
					buff.append( "The " + monsters.elementAt( i ).getDescription( )
							+ " is splashed with holy rain!" );
					targetMonster.damage( buff, damage );
					aLevel.addMessage( buff.toString( ) );
					if ( targetMonster.isDead( ) )
					{
						if ( targetMonster.wasSeen( ) )
							aLevel.addMessage( "The " + targetMonster.getDescription( )
									+ " catches in flame and is roasted!" );
						performer.getLevel( ).removeMonster( targetMonster );
					}
					else
					{
						if ( targetMonster.wasSeen( ) )
							aLevel.addMessage( "The " + targetMonster.getDescription( )
									+ " catches in flame." );
					}
				}
			}
		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "Holy";
	}

	public String getSFX( )
	{
		return "wav/breakpot.wav";
	}
}