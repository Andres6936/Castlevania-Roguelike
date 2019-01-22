package co.castle.action.vanquisher;

import co.castle.action.ProjectileSkill;
import co.castle.player.Player;

public class ChargeBall extends ProjectileSkill
{
	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.5 );
	}

	public int getDamage( )
	{
		return 8 + (int) Math.round( getPlayer( ).getSoulPower( ) * 2.5d );
	}

	public int getHeartCost( )
	{
		return 6;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "ChargeBall";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the charge Ball?";
	}

	public int getRange( )
	{
		return 10;
	}

	public String getSelfTargettedMessage( )
	{
		return "The ball of energy explodes";
	}

	public String getSFXID( )
	{
		return "SFX_CHARGE_BALL";
	}

	public String getShootMessage( )
	{
		return "You concentrate and launch a charge ball!";
	}

	public String getSpellAttackDesc( )
	{
		return "charge ball";
	}
}