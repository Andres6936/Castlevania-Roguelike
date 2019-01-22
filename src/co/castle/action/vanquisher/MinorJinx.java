package co.castle.action.vanquisher;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.level.Level;
import co.castle.player.Damage;
import co.castle.player.Player;

public class MinorJinx extends Action
{
	public boolean canPerform( Actor a )
	{
		Player aPlayer = (Player) a;
		Level aLevel = performer.getLevel( );
		if ( aPlayer.getHits( ) < 5 )
		{
			aLevel.addMessage( "That would be too suicidal!" );
			return false;
		}
		return true;
	}

	public void execute( )
	{
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );

		int recover = 10;
		StringBuffer buff = new StringBuffer(
				"You exchange vitality for power!! (+" + recover + ")" );
		aPlayer.addHearts( recover );
		aPlayer.selfDamage( buff, Player.DAMAGE_JINX, new Damage( 5, true ) );
		aLevel.addMessage( buff.toString( ) );

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public String getID( )
	{
		return "MINOR_JINX";
	}

	public String getSFX( )
	{
		return null;
	}
}