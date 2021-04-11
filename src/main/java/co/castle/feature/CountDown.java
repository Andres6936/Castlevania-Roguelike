package co.castle.feature;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;

public class CountDown implements ActionSelector, Cloneable
{
	private int turnsToDie;

	public ActionSelector derive( )
	{
		try
		{
			return (ActionSelector) clone( );
		}
		catch ( CloneNotSupportedException cnse )
		{
			return null;
		}
	}

	public String getID( )
	{
		return "COUNTDOWN";
	}

	public Action selectAction( Actor who )
	{
		// Debug.say("cpuntdown " + turnsToDie);
		turnsToDie--;
		if ( turnsToDie == 0 )
		{
			who.die( );
			who.getLevel( ).removeSmartFeature( (SmartFeature) who );
		}
		return null;
	}

	public void setTurns( int turns )
	{
		turnsToDie = turns;
	}

}