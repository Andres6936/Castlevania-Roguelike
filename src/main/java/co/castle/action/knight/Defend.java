package co.castle.action.knight;

import co.castle.action.Action;
import co.castle.action.HeartAction;
import co.castle.actor.Actor;

public class Defend extends HeartAction
{
	public boolean canPerform( Actor a )
	{
		if ( getPlayer( ).getShield( ) == null )
		{
			invalidationMessage = "You don't have a shield.";
			return false;
		}
		return super.canPerform( a );
	}

	public void execute( )
	{
		super.execute( );
		if ( targetDirection == Action.SELF )
		{
			return;
		}
		getPlayer( ).getLevel( ).addMessage( "You defend yourself with your "
				+ getPlayer( ).getShield( ).getDescription( ) );
		getPlayer( ).setShieldGuard( targetDirection, 5 );
	}

	public int getHeartCost( )
	{
		return 1;
	}

	public String getID( )
	{
		return "DEFEND";
	}

	public String getPromptDirection( )
	{
		return "Where will you locate your shield to?";
	}

	public boolean needsDirection( )
	{
		return true;
	}

}
