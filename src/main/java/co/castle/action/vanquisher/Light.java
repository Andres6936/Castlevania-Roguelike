package co.castle.action.vanquisher;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Consts;
import co.castle.player.Player;

public class Light extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		aLevel.addMessage( "Magical light illuminates the place." );
		aPlayer.setCounter( Consts.C_MAGICLIGHT, 70 + aPlayer.getSoulPower( ) * 3 );
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
		return "Light";
	}

	public String getSFX( )
	{
		return null;
	}

}