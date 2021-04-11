package co.castle.action.renegade;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class SoulsStrike extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		aLevel.addMessage( "Three souls come from under your cape" );

		for ( int i = 0; i < 3; i++ )
		{
			Monster nearestMonster = aPlayer.getNearestMonster( );
			if ( nearestMonster == null
					|| Position.flatDistance( nearestMonster.getPosition( ),
							aPlayer.getPosition( ) ) > 15 )
			{
			}
			else
			{
				StringBuffer buff = new StringBuffer( );
				if ( nearestMonster.wasSeen( ) )
					buff.append( "The soul impacts the "
							+ nearestMonster.getDescription( ) + "!" );
				nearestMonster.damage( buff, 10 + aPlayer.getSoulPower( ) * 2 );
				drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
						aPlayer.getPosition( ), nearestMonster.getPosition( ),
						"SFX_SOULSSTRIKE",
						Position.flatDistance( performer.getPosition( ),
								nearestMonster.getPosition( ) ) ) );
				aLevel.addMessage( buff.toString( ) );
			}
		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.5 );
	}

	public int getHeartCost( )
	{
		return 8;
	}

	public String getID( )
	{
		return "Souls Strike";
	}

}