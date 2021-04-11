package co.castle.action.invoker;

import co.castle.action.ProjectileSkill;
import co.castle.player.Player;

public class Cat extends ProjectileSkill
{
	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.4 );
	}

	public int getDamage( )
	{
		return 10 + getPlayer( ).getSoulPower( ) * 2;
	}

	public int getHeartCost( )
	{
		return 3;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "Cat";
	}

	public int getPathType( )
	{
		return PATH_HOVER;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to invoke the kitty?";
	}

	public int getRange( )
	{
		return 20;
	}

	public String getSelfTargettedMessage( )
	{
		return "You summon a jumping cat! The cat roars like mad!! The cat falls, scratching all around!";
	}

	public String getSFX( )
	{
		return "wav/kitty.wav";
	}

	public String getSFXID( )
	{
		return "SFX_CAT";
	}

	public String getShootMessage( )
	{
		return "You summon a quick running cat!";
	}

	public String getSpellAttackDesc( )
	{
		return "cat soul";
	}

	public boolean piercesThru( )
	{
		return true;
	}
}