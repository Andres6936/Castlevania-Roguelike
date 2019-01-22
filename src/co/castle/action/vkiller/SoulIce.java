package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.player.Player;

public class SoulIce extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		aPlayer.recoverHitsP( 20 + aPlayer.getSoulPower( ) );
		aPlayer.getLevel( ).addMessage( "You feel relieved!" );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getHeartCost( )
	{
		return 20;
	}

	public String getID( )
	{
		return "Soul Ice";
	}

	public String getSFX( )
	{
		return null;
	}
}