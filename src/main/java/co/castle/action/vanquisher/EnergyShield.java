package co.castle.action.vanquisher;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Consts;
import co.castle.player.Player;

public class EnergyShield extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		aLevel.addMessage( "You are covered with a shimmering shield!!" );
		aPlayer.setCounter( Consts.C_ENERGYSHIELD, 50 + aPlayer.getSoulPower( ) * 2 );
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
		return "EnergyShield";
	}

}