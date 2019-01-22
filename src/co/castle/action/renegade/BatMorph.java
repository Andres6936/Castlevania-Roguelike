package co.castle.action.renegade;

import co.castle.action.MorphAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class BatMorph extends MorphAction
{
	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.5 );
	}

	public int getHeartCost( )
	{
		return 10;
	}

	public String getID( )
	{
		return "BatMorph";
	}

	public int getMadChance( )
	{
		return 0;
	}

	public String getMorphID( )
	{
		return Consts.C_BATMORPH;
	}

	public String getMorphMessage( )
	{
		return "You turn into a bat!";
	}

	public int getMorphStrength( )
	{
		return 10;
	}

	public int getMorphTime( )
	{
		return 30 + getPlayer( ).getSoulPower( ) * 4
				+ ( !getPlayer( ).getLevel( ).isDay( ) ? 40 : 0 );
	}

	public String getSFX( )
	{
		return "wav/swaashll.wav";
	}

	public boolean isBigMorph( )
	{
		return false;
	}

	public boolean isSmallMorph( )
	{
		return true;
	}
}