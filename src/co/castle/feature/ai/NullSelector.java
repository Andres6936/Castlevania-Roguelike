package co.castle.feature.ai;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;

public class NullSelector implements ActionSelector, Cloneable
{
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
		return "NULL_SELECTOR";
	}

	public Action selectAction( Actor who )
	{
		return null;
	}

}