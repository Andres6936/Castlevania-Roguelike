package co.castle.action.vanquisher;

import co.castle.action.HeartAction;

public class Cure extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		getPlayer( ).cure( );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "CURE";
	}
}
