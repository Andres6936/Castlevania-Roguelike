package co.castle.action.renegade;

import java.util.Vector;

import co.castle.action.ProjectileSkill;
import co.castle.monster.Monster;
import co.castle.player.Player;
import sz.util.Util;

public class SoulSteal extends ProjectileSkill
{

	public boolean allowsSelfTarget( )
	{
		return false;
	}

	public void execute( )
	{
		super.execute( );
		Vector monsters = getHitMonsters( );
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			Monster m = (Monster) monsters.elementAt( i );
			// TODO: Make this relative to the monster's soul or something
			if ( Util.chance( 70 ) )
			{
				getPlayer( ).getLevel( )
						.addMessage( "You steal the " + m.getDescription( ) + " soul!" );
				getPlayer( ).recoverHitsP( 5 + getPlayer( ).getSoulPower( ) );
			}
		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getDamage( )
	{
		return 1 + getPlayer( ).getSoulPower( );
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
		return "Soul Steal";
	}

	public int getPathType( )
	{
		return PATH_LINEAR;
	}

	public String getPromptPosition( )
	{
		return "Where do you want to invoke the soul?";
	}

	public int getRange( )
	{
		return 15;
	}

	public String getSelfTargettedMessage( )
	{
		return "";
	}

	public String getSFX( )
	{
		return "wav/fire.wav";
	}

	public String getSFXID( )
	{
		return "SFX_SOUL_STEAL";
	}

	public String getShootMessage( )
	{
		return "Soul Steal!";
	}

	public String getSpellAttackDesc( )
	{
		return "soul";
	}
}