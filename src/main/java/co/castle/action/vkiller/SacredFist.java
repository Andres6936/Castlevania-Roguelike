package co.castle.action.vkiller;

import co.castle.action.BeamProjectileSkill;
import co.castle.player.Player;

public class SacredFist extends BeamProjectileSkill
{
	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getDamage( )
	{
		return 15 + 2 * getPlayer( ).getSoulPower( );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "SacredFist";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to punch?";
	}

	public int getRange( )
	{
		return 2;
	}

	public String getSelfTargettedMessage( )
	{
		return null;
	}

	public String getSFXID( )
	{
		return "NONE";
	}

	public String getShootMessage( )
	{
		return "You punch with all your mystic power!";
	}

	public String getSpellAttackDesc( )
	{
		return "holy blow";
	}

	public String plottedLocatedEffect( )
	{
		return "SFX_WHITE_HIT";
	}

}