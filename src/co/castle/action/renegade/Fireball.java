package co.castle.action.renegade;

import co.castle.action.ProjectileSkill;
import co.castle.player.Player;

public class Fireball extends ProjectileSkill
{

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getDamage( )
	{
		return 3 + getPlayer( ).getSoulPower( ) * 2;
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
		return "Fireball";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the fireball?";
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
		return "SFX_FIREBALL";
	}

	public String getShootMessage( )
	{
		return "You launch a fireball!";
	}

	public String getSpellAttackDesc( )
	{
		return "fireball";
	}
}