package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class SoulSaint extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		aPlayer.setCounter( Consts.C_FIREBALL_WHIP, 50 + 3 * aPlayer.getSoulPower( ) );
		aPlayer.getLevel( ).addMessage( "Your whip glows!" );
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
		return "Soul Saint";
	}

	public String getSFX( )
	{
		return null;
	}
}