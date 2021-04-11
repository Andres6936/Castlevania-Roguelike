package co.castle.action;

import co.castle.actor.Actor;
import co.castle.player.Player;

public abstract class HeartAction extends Action
{
	public boolean canPerform( Actor a )
	{
		Player p = getPlayer( a );
		setPerformer( a );
		if ( p.getHearts( ) >= getHeartCost( ) )
		{
			return true;
		}
		invalidationMessage = "Your need more power!";
		return false;
	}

	public void execute( )
	{
		reduceHearts( );
	}

	public abstract int getHeartCost( );
	// public abstract double getTimeCostModifier();

	public Player getPlayer( )
	{
		return (Player) performer;
	}

	/*
	 * public final int getCost(){ Player p = (Player) performer; return
	 * (int)(p.getCastCost() * getTimeCostModifier()); }
	 */

	public void reduceHearts( )
	{
		Player aPlayer = (Player) performer;
		aPlayer.reduceHearts( getHeartCost( ) );
	}
}