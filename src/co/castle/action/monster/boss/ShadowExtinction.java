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

public class ShadowExtinction extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Dracula Yells: 'Shadow Extinction!'" );
		int sickles = Util.rand( 2, 5 );
		for ( int i = 0; i < sickles; i++ )
		{
			int xvar = Util.rand( -10, 10 );
			int yvar = Util.rand( -10, 10 );
			int xgo = performer.getPosition( ).x + xvar - 3;
			int ygo = performer.getPosition( ).y + yvar - 3;
			// UserInterface.getUI().drawEffect(new SplashEffect(new
			// Position(xvar+performer.getPosition().x, yvar+performer.getPosition().y),
			// "o*'", Appearance.RED));
			drawEffect(
					EffectFactory.getSingleton( ).createLocatedEffect(
							new Position( xvar + performer.getPosition( ).x,
									yvar + performer.getPosition( ).y,
									performer.getPosition( ).z ),
							"SFX_SHADOW_EXTINCTION" ) );
			for ( int jx = xgo; jx <= xgo + 3; jx++ )
				for ( int jy = ygo; jy <= ygo + 3; jy++ )
					hit( jx, jy, performer.getPosition( ).z );
		}
	}

	public int getCost( )
	{
		return 20;
	}

	public String getID( )
	{
		return "SHADOW_EXTINCTION";
	}

	private void hit( int x, int y, int z )
	{
		String message = "";
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		Position destinationPoint = new Position( x, y, z );
		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
		{
			message = "The " + destinationFeature.getDescription( )
					+ " is hit by dark energy!";
			Feature prize = destinationFeature.damage( aPlayer, 4 );
			if ( prize != null )
			{
				message += " and turned into dust!";
			}
			aLevel.addMessage( message );
		}
		if ( destinationPoint.equals( aPlayer.getPosition( ) ) )
		{
			StringBuffer buff = new StringBuffer( "You are hit by the dark energy!" );
			aPlayer.damage( buff, (Monster) performer, new Damage( 2, false ) );
			aLevel.addMessage( buff.toString( ) );
		}
	}
}