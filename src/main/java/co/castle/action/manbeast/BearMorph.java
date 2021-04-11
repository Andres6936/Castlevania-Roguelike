package co.castle.action.manbeast;

import co.castle.action.MorphAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class BearMorph extends MorphAction
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
		return "BearMorph";
	}

	public int getMadChance( )
	{
		return 60 - getPlayer( ).getSoulPower( ) * 2;
	}

	public String getMorphID( )
	{
		return Consts.C_BEARMORPH;
	}

	public String getMorphMessage( )
	{
		return "You turn into a huge bear!";
	}

	public int getMorphStrength( )
	{
		return 10;
	}

	public int getMorphTime( )
	{
		return 50 + getPlayer( ).getSoulPower( ) * 4
				+ ( !getPlayer( ).getLevel( ).isDay( ) ? 50 : 0 );
	}

	public String getSFX( )
	{
		return "wav/growll.wav";
	}

	public boolean isBigMorph( )
	{
		return true;
	}

	public boolean isSmallMorph( )
	{
		return false;
	}
}