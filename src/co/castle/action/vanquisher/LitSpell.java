package co.castle.action.vanquisher;

import co.castle.action.ProjectileSkill;
import co.castle.player.Player;

public class LitSpell extends ProjectileSkill
{
	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.3 );
	}

	public int getDamage( )
	{
		return 5 + 4 * getPlayer( ).getSoulPower( );
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
		return "LitSpell";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to invoke the lighting?";
	}

	public int getRange( )
	{
		return 7;
	}

	public String getSelfTargettedMessage( )
	{
		return "You feel shocked";
	}

	public String getSFXID( )
	{
		return "SFX_LIT_SPELL";
	}

	public String getShootMessage( )
	{
		return "You invoke the spell of Lighting!";
	}

	public String getSpellAttackDesc( )
	{
		return "lighting bolt";
	}

	public boolean piercesThru( )
	{
		return false;
	}
}