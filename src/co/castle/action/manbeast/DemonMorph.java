package co.castle.action.manbeast;

import co.castle.action.MorphAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class DemonMorph extends MorphAction
{
	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getAttackCost( ) * 1.5 );
	}

	public int getHeartCost( )
	{
		return 15;
	}

	public String getID( )
	{
		return "DemonMorph";
	}

	public int getMadChance( )
	{
		return 30 - getPlayer( ).getSoulPower( );
	}

	public String getMorphID( )
	{
		return Consts.C_DEMONMORPH;
	}

	public String getMorphMessage( )
	{
		return "You turn into a demon!";
	}

	public int getMorphStrength( )
	{
		return 10;
	}

	public int getMorphTime( )
	{
		return 60 + getPlayer( ).getSoulPower( ) * 3
				+ ( !getPlayer( ).getLevel( ).isDay( ) ? 50 : 0 );
	}

	public String getSFX( )
	{
		return "wav/growll.wav";
	}

	public boolean isBigMorph( )
	{
		return false;
	}

	public boolean isSmallMorph( )
	{
		return false;
	}
}