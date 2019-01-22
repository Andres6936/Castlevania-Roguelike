package co.castle.action.manbeast;

import co.castle.action.ProjectileSkill;

public class ClawAssault extends ProjectileSkill
{
	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public void execute( )
	{
		super.execute( );
		getPlayer( ).setPosition( finalPoint );
		getPlayer( ).land( );
	}

	public int getDamage( )
	{
		return 15 + getPlayer( ).getPunchDamage( ) * 2;
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
		return "ClawAssault";
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
		return 5;
	}

	public String getSelfTargettedMessage( )
	{
		return null;
	}

	public String getSFXID( )
	{
		return null;
	}

	public String getShootMessage( )
	{
		return "You jump into the enemy!";
	}

	public String getSpellAttackDesc( )
	{
		return "attack";
	}

	public boolean piercesThru( )
	{
		return true;
	}
}
