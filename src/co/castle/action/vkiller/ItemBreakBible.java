package co.castle.action.vkiller;

import co.castle.action.BeamProjectileSkill;
import co.castle.player.Player;

public class ItemBreakBible extends BeamProjectileSkill
{

	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getDamage( )
	{
		return 7 + getPlayer( ).getShotLevel( ) + getPlayer( ).getSoulPower( ) * 2;
	}

	public int getHeartCost( )
	{
		return 2;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "ItemBreakBible";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where?";
	}

	public int getRange( )
	{
		return 15;
	}

	public String getSelfTargettedMessage( )
	{
		return "The fireball flies to the heavens";
	}

	public String getSFX( )
	{
		return "wav/fire.wav";
	}

	public String getSFXID( )
	{
		return "SFX_ITEMBREAKBIBLE";
	}

	public String getShootMessage( )
	{
		return "The bible opens and shreds a beam of light!";
	}

	public String getSpellAttackDesc( )
	{
		return "beam of light";
	}

	public boolean piercesThru( )
	{
		return true;
	}

}