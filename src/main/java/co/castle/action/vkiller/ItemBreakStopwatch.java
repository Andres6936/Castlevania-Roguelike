package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Player;
import sz.util.Debug;

public class ItemBreakStopwatch extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Debug.doAssert( performer instanceof Player, "At action.Stopwatch" );
		Player aPlayer = (Player) performer;
		Level x = performer.getLevel( );
		x.addMessage( "You open the stopwatch! Time stops!" );
		x.stopTime( 2 * ( 5 + aPlayer.getShotLevel( ) * 2 + aPlayer.getSoulPower( ) ) );
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
		return "Stopwatch";
	}

	public String getSFX( )
	{
		return "wav/clockbel.wav";
	}
}