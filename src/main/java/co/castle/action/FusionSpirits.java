package co.castle.action;

import java.util.Iterator;

import co.castle.item.Item;
import co.castle.player.Player;

public class FusionSpirits extends Action
{
	public void execute( )
	{
		Player aPlayer = (Player) performer;
		if ( targetMultiItems.size( ) != 2 )
		{
			performer.getLevel( ).addMessage( "You can only fusion two spirits" );
			return;
		}
		/* Checks all the items to be spirits */
		for ( Iterator item = targetMultiItems.iterator( ); item.hasNext( ); )
		{
			Item element = (Item) item.next( );
			if ( !element.getDefinition( ).getID( ).endsWith( "SPIRIT" ) )
			{
				performer.getLevel( )
						.addMessage( element.getDescription( ) + " is not an spirit" );
				return;
			}
		}

		String principal = "";
		String secondary = "";
		String principalDs = "";
		String secondaryDs = "";

		for ( Iterator item = targetMultiItems.iterator( ); item.hasNext( ); )
		{
			Item element = (Item) item.next( );
			String fx = element.getDefinition( ).getID( );
			/* Looks for attribute spirits */
			if ( fx.equals( "VENUS_SPIRIT" ) || fx.equals( "MERCURY_SPIRIT" )
					|| fx.equals( "MARS_SPIRIT" ) )
			{
				principal = fx;
				principalDs = element.getDescription( );
			}
			else
			{
				secondary = fx;
				secondaryDs = element.getDescription( );
			}
		}
		if ( principal.equals( "" ) || secondary.equals( "" ) )
		{
			performer.getLevel( ).addMessage( "That fusion won't work" );
			return;
		}

		performer.getLevel( ).addMessage(
				"You try to fusion " + principalDs + " with " + secondaryDs + "..." );
		aPlayer.addHistoricEvent( "Fusioned " + principalDs + " with " + secondaryDs );

		if ( principal.equals( "VENUS_SPIRIT" ) )
		{
			if ( secondary.equals( "URANUS_SPIRIT" ) )
			{
				aPlayer.reduceCastCost( 5 );
				performer.getLevel( )
						.addMessage( "Your spellcasting ability increases!" );
			}
			else if ( secondary.equals( "NEPTUNE_SPIRIT" ) )
			{
				aPlayer.increaseHeartMax( 7 );
				performer.getLevel( ).addMessage( "You are able to hold more hearts!" );
			}
			else if ( secondary.equals( "JUPITER_SPIRIT" ) )
			{
				aPlayer.increaseSoulPower( 1 );
				performer.getLevel( ).addMessage( "Your soul power increases!" );
			}
		}
		else if ( principal.equals( "MERCURY_SPIRIT" ) )
		{
			if ( secondary.equals( "URANUS_SPIRIT" ) )
			{
				aPlayer.reduceWalkCost( 5 );
				performer.getLevel( ).addMessage( "You feel quicker!" );
			}
			else if ( secondary.equals( "NEPTUNE_SPIRIT" ) )
			{
				aPlayer.increaseCarryMax( 5 );
				performer.getLevel( ).addMessage( "You feel able to carry more!" );
			}
			else if ( secondary.equals( "JUPITER_SPIRIT" ) )
			{
				aPlayer.increaseEvadeChance( 5 );
				performer.getLevel( ).addMessage( "You feel more nimble!" );
			}
		}
		else if ( principal.equals( "MARS_SPIRIT" ) )
		{
			if ( secondary.equals( "URANUS_SPIRIT" ) )
			{
				aPlayer.reduceAttackCost( 5 );
				performer.getLevel( ).addMessage( "You feel more able on combat!" );
			}
			else if ( secondary.equals( "NEPTUNE_SPIRIT" ) )
			{
				aPlayer.increaseHitsMax( 3 );
				performer.getLevel( ).addMessage( "You feel hardy!" );
			}
			else if ( secondary.equals( "JUPITER_SPIRIT" ) )
			{
				aPlayer.increaseAttack( 1 );
				performer.getLevel( ).addMessage( "You feel stronger!" );
			}
		}

		for ( Iterator item = targetMultiItems.iterator( ); item.hasNext( ); )
		{
			Item element = (Item) item.next( );
			aPlayer.reduceQuantityOf( element );
		}
	}

	public String getID( )
	{
		return "Fusion Spirits";
	}

	public boolean needsSpirits( )
	{
		return true;
	}
}