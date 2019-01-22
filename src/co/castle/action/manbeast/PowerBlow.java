package co.castle.action.manbeast;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Consts;
import co.castle.player.Player;

public class PowerBlow extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		aLevel.addMessage( "WAARGH!!! You build up your power!" );
		aPlayer.setCounter( Consts.C_POWERBLOW, 5 );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getAttackCost( ) * 1.1 );
	}

	public int getHeartCost( )
	{
		return 3;
	}

	public String getID( )
	{
		return "PowerBlow";
	}
}