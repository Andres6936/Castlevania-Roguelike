package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;
import sz.util.Util;

public class ShadowApocalypse extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "A voice thunders! 'SHADOW APOCALYPSE!!'" );
		int sickles = Util.rand( 4, 8 );
		for ( int i = 0; i < sickles; i++ )
		{
			int xvar = Util.rand( -10, 10 );
			int yvar = Util.rand( -10, 10 );
			int xgo = performer.getPosition( ).x + xvar - 4;
			int ygo = performer.getPosition( ).y + yvar - 4;
			// UserInterface.getUI().drawEffect(new SplashEffect(new
			// Position(xvar+performer.getPosition().x, yvar+performer.getPosition().y),
			// "Oo*'.", Appearance.CYAN));
			drawEffect(
					EffectFactory.getSingleton( ).createLocatedEffect(
							new Position( xvar + performer.getPosition( ).x,
									yvar + performer.getPosition( ).y,
									performer.getPosition( ).z ),
							"SFX_SHADOW_APOCALYPSE" ) );
			for ( int jx = xgo; jx <= xgo + 4; jx++ )
				for ( int jy = ygo; jy <= ygo + 4; jy++ )
					hit( jx, jy, performer.getPosition( ).z );
		}
	}

	public int getCost( )
	{
		return 40;
	}

	public String getID( )
	{
		return "SHADOW_APOCALYPSE";
	}

	private void hit( int x, int y, int z )
	{
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		Position destinationPoint = new Position( x, y, z );
		if ( destinationPoint.equals( aPlayer.getPosition( ) ) )
		{
			StringBuffer buff = new StringBuffer( "You feel pain all over your body!!" );
			aPlayer.damage( buff, (Monster) performer, new Damage( 4, false ) );
			aLevel.addMessage( buff.toString( ) );
		}
	}
}