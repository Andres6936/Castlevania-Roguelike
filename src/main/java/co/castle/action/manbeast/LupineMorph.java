package co.castle.action.manbeast;

import co.castle.action.MorphAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class LupineMorph extends MorphAction
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
		return "LupineMorph";
	}

	public int getMadChance( )
	{
		return 20 - getPlayer( ).getSoulPower( );
	}

	public String getMorphID( )
	{
		return Consts.C_LUPINEMORPH;
	}

	public String getMorphMessage( )
	{
		return "You turn into a wolf-life creature!";
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