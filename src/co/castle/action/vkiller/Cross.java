package co.castle.action.vkiller;

import co.castle.action.ProjectileSkill;
import co.castle.feature.SmartFeature;
import co.castle.feature.SmartFeatureFactory;
import co.castle.feature.ai.CrossAI;
import co.castle.player.Player;
import sz.util.Util;

public class Cross extends ProjectileSkill
{
	public void execute( )
	{
		super.execute( );
		if ( targetPosition.equals( performer.getPosition( ) ) )
		{
			if ( Util.chance( 50 ) )
			{
				performer.getLevel( )
						.addMessage( "The cross falls heads! You catch the cross." );
			}
			else
			{
				performer.getLevel( )
						.addMessage( "The cross falls tails! You catch the cross." );
			}
			return;
		}
		if ( performer instanceof Player )
		{
			SmartFeature cross = SmartFeatureFactory.getFactory( )
					.buildFeature( "CROSS" );
			( (CrossAI) cross.getSelector( ) )
					.setTargetPosition( getPlayer( ).getPosition( ) );
			cross.setPosition( finalPoint );
			getPlayer( ).getLevel( ).addSmartFeature( cross );
		}
		else
		{
			/*
			 * Effect crossEffect
			 * =EffectFactory.getSingleton().createDirectedEffect(performer.getPosition(),
			 * targetPosition, "SFX_CROSS", i);
			 * crossEffect.setPosition(performer.getLevel().getPlayer().getPosition());
			 * drawEffect(crossEffect);
			 */
		}
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

	public int getDamage( )
	{
		return 5 + getPlayer( ).getShotLevel( ) + 2 * getPlayer( ).getSoulPower( );
	}

	public int getHeartCost( )
	{
		return 3;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "Cross";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the Cross?";
	}

	public int getRange( )
	{
		return 7;
	}

	public String getSelfTargettedMessage( )
	{
		return "You throw the holy cross!";
	}

	public String getSFX( )
	{
		return "wav/misswipe.wav";
	}

	public String getSFXID( )
	{
		return "SFX_CROSS";
	}

	public String getShootMessage( )
	{
		return "You throw the holy cross!";
	}

	public String getSpellAttackDesc( )
	{
		return "cross";
	}

	public boolean piercesThru( )
	{
		return true;
	}
}
