package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;
import sz.util.Util;

public class ShadowFlare extends Action
{
	public void execute( )
	{
		Monster aMonster = (Monster) performer;
		Level aLevel = aMonster.getLevel( );
		if ( targetDirection == -1 )
			return;
		aLevel.addMessage( "Dracula invokes shadow flares!" );
		Position runner1 = new Position( performer.getPosition( ) );
		Position runner2 = null;
		Position runner3 = null;
		int randy = Util.rand( 1, 2 );
		if ( targetDirection == Action.UP || targetDirection == Action.DOWN )
		{
			runner2 = Position.add( performer.getPosition( ),
					new Position( -1 * randy, 0 ) );
			runner3 = Position.add( performer.getPosition( ),
					new Position( 1 * randy, 0 ) );
		}
		else
		{
			runner2 = Position.add( performer.getPosition( ),
					new Position( 0, -1 * randy ) );
			runner3 = Position.add( performer.getPosition( ),
					new Position( 0, 1 * randy ) );
		}

		Position directionVar = Action.directionToVariation( targetDirection );

		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect( runner1,
				Position.add( runner1, directionVar ), "SFX_SHADOW_FLARE", 10 ) );
		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect( runner2,
				Position.add( runner2, directionVar ), "SFX_SHADOW_FLARE", 10 ) );
		drawEffect( EffectFactory.getSingleton( ).createDirectedEffect( runner3,
				Position.add( runner3, directionVar ), "SFX_SHADOW_FLARE", 10 ) );
		for ( int i = 0; i < 10; i++ )
		{
			hit( runner1 );
			hit( runner2 );
			hit( runner3 );
			runner1.add( directionVar );
			runner2.add( directionVar );
			runner3.add( directionVar );
		}
	}

	public int getCost( )
	{
		return 50;
	}

	public String getID( )
	{
		return "SHADOWFLARE";
	}

	private void hit( Position destinationPoint )
	{
		String message = "";
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
		{
			message = "The flares crush the " + destinationFeature.getDescription( );
			Feature prize = destinationFeature.damage( aPlayer, 4 );
			if ( prize != null )
			{
				message += ", consuming it!";
			}
			aLevel.addMessage( message );
		}
		if ( destinationPoint.equals( aPlayer.getPosition( ) ) )
		{
			StringBuffer buff = new StringBuffer( "You are burned by the shadow flare!" );
			aPlayer.damage( buff, (Monster) performer, new Damage( 3, false ) );
			aLevel.addMessage( buff.toString( ) );
		}
		// drawEffect(EffectFactory.getSingleton().createLocatedEffect(destinationPoint,
		// "SFX_SHADOW_FLARE"));
	}
}
