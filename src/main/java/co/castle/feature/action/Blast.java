package co.castle.feature.action;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class Blast extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		int damage = 20 + aLevel.getPlayer( ).getShotLevel( )
				+ aLevel.getPlayer( ).getSoulPower( );

		aLevel.addMessage( "The crystal emits a holy blast!" );

		Position blastPosition = performer.getPosition( );

		// aLevel.addEffect(new SplashEffect(blastPosition, "Oo,.", Appearance.CYAN));
		aLevel.addEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( blastPosition, "SFX_CRYSTAL_BLAST" ) );
		Position destinationPoint = new Position( 0, 0, performer.getPosition( ).z );
		for ( int x = blastPosition.x - 3; x <= blastPosition.x + 3; x++ )
			for ( int y = blastPosition.y - 3; y <= blastPosition.y + 3; y++ )
			{
				destinationPoint.x = x;
				destinationPoint.y = y;
				Monster targetMonster = performer.getLevel( )
						.getMonsterAt( destinationPoint );
				if ( targetMonster != null )
				{
					if ( targetMonster.wasSeen( ) )
						aLevel.addMessage( "The " + targetMonster.getDescription( )
								+ " is hit by the holy wave!" );
					targetMonster.damage( new StringBuffer( ), damage );
				}
			}
	}

	public int getCost( )
	{
		return 50;
	}

	public String getID( )
	{
		return "Blast";
	}

	public String getSFX( )
	{
		return "wav/lazrshot.wav";
	}
}
