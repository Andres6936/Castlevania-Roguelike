package co.castle.action.vanquisher;

import co.castle.action.HeartAction;
import co.castle.player.Player;

public class Recover extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		aPlayer.recoverHitsP( 10 + aPlayer.getSoulPower( ) );
		aPlayer.getLevel( ).addMessage( "You feel relieved!" );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getHeartCost( )
	{
		return 15;
	}

	public String getID( )
	{
		return "RECOVER";
	}

	public String getSFX( )
	{
		return null;
	}
}