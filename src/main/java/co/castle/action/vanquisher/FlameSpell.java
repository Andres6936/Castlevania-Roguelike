package co.castle.action.vanquisher;

import co.castle.action.ProjectileSkill;
import co.castle.player.Player;

public class FlameSpell extends ProjectileSkill
{
	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.3 );
	}

	public int getDamage( )
	{
		return 15 + getPlayer( ).getSoulPower( ) * 2;
	}

	public int getHeartCost( )
	{
		return 8;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "FlameSpell";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to invoke the flame?";
	}

	public int getRange( )
	{
		return 5;
	}

	public String getSelfTargettedMessage( )
	{
		return "You make a flame pillar!";
	}

	public String getSFXID( )
	{
		return "SFX_FLAME_SPELL";
	}

	public String getShootMessage( )
	{
		return "You invoke the spell of Flame!";
	}

	public String getSpellAttackDesc( )
	{
		return "flame";
	}

	public boolean piercesThru( )
	{
		return true;
	}

}