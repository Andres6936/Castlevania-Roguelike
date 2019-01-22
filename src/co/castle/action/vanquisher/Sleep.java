package co.castle.action.vanquisher;

import java.util.Vector;

import co.castle.action.ProjectileSkill;
import co.castle.monster.Monster;
import co.castle.player.Consts;
import co.castle.player.Player;

public class Sleep extends ProjectileSkill
{
	public void execute( )
	{
		super.execute( );
		Vector hitMonsters = getHitMonsters( );
		for ( int i = 0; i < hitMonsters.size( ); i++ )
		{
			Monster targetMonster = (Monster) hitMonsters.elementAt( i );
			if ( targetMonster.wasSeen( ) )
				targetMonster.getLevel( ).addMessage(
						"The " + targetMonster.getDescription( ) + " is frozen!" );
			targetMonster.setCounter( Consts.C_MONSTER_SLEEP, 10 );
		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.3 );
	}

	public int getDamage( )
	{
		return 0;
	}

	public int getHeartCost( )
	{
		return 7;
	}

	public int getHit( )
	{
		return 100;
	}

	public String getID( )
	{
		return "SpellSpell";
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
		return 7;
	}

	public String getSelfTargettedMessage( )
	{
		return "You fall asleep!";
	}

	public String getSFXID( )
	{
		return "SFX_SLEEP_SPELL";
	}

	public String getShootMessage( )
	{
		return "You invoke the spell of Sleep!";
	}

	public String getSpellAttackDesc( )
	{
		return "sleep beam";
	}

	public boolean piercesThru( )
	{
		return true;
	}

}