package co.castle.action.renegade;

import co.castle.action.BeamProjectileSkill;
import co.castle.player.Player;

public class FlamesShoot extends BeamProjectileSkill
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
		return 15 + getPlayer( ).getSoulPower( ) * 2;
	}

	public int getHeartCost( )
	{
		return 10;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "FlamesShoot";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the flames?";
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
		return "SFX_FLAMESSHOOT";
	}

	public String getShootMessage( )
	{
		return "Three flame missiles emerge from your cape";
	}

	public String getSpellAttackDesc( )
	{
		return "flames";
	}

}