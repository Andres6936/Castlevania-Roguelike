package co.castle.action.vanquisher;

import java.util.Vector;

import co.castle.action.ProjectileSkill;
import co.castle.monster.Monster;
import co.castle.player.Player;

public class IceSpell extends ProjectileSkill
{
	public void execute( )
	{
		super.execute( );
		Vector hitMonsters = getHitMonsters( );
		for ( int i = 0; i < hitMonsters.size( ); i++ )
		{
			Monster targetMonster = (Monster) hitMonsters.elementAt( i );
			int friz = 10 + getPlayer( ).getSoulPower( )
					- targetMonster.getFreezeResistance( );
			if ( friz > 0 )
			{
				if ( targetMonster.wasSeen( ) )
					targetMonster.getLevel( ).addMessage(
							"The " + targetMonster.getDescription( ) + " is frozen!" );
				targetMonster.freeze( friz );
			}
			else if ( targetMonster.wasSeen( ) )
				targetMonster.getLevel( ).addMessage(
						"The " + targetMonster.getDescription( ) + " is too hot!" );

		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.3 );
	}

	public int getDamage( )
	{
		return 15 + 2 * getPlayer( ).getSoulPower( );
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
		return "IceSpell";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to invoke the frost?";
	}

	public int getRange( )
	{
		return 5;
	}

	public String getSelfTargettedMessage( )
	{
		return "You feel chilly";
	}

	public String getSFXID( )
	{
		return "SFX_ICE_SPELL";
	}

	public String getShootMessage( )
	{
		return "You invoke the spell of Ice!";
	}

	public String getSpellAttackDesc( )
	{
		return "chilly wind";
	}

	public boolean piercesThru( )
	{
		return true;
	}

}