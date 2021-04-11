package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;

public class SoulWind extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level x = performer.getLevel( );
		x.addEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( aPlayer.getPosition( ), "SFX_SOUL_WIND" ) );
		x.addMessage( "Soul Wind!" );
		x.stopTime( 20 + aPlayer.getShotLevel( ) * 5 + 2 * aPlayer.getSoulPower( ) );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 10;
	}

	public String getID( )
	{
		return "Soul Wind";
	}

	public String getSFX( )
	{
		return "wav/clockbel.wav";
	}

}