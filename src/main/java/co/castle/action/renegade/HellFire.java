package co.castle.action.renegade;

import co.castle.action.BeamProjectileSkill;
import co.castle.player.Player;

public class HellFire extends BeamProjectileSkill
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
		return 25 + getPlayer( ).getSoulPower( ) * 3;
	}

	public int getHeartCost( )
	{
		return 15;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "HellFire";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to summon Hellfire?";
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
		return "SFX_HELLFIRE";
	}

	public String getShootMessage( )
	{
		return "Three balls of lava emerge from your cape!";
	}

	public String getSpellAttackDesc( )
	{
		return "hellfire";
	}

}