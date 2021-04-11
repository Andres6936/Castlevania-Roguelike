package co.castle.level;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;

public class EmergerAI implements ActionSelector
{
	private int counter;

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
		return "Emerge";
	}

	public Action selectAction( Actor who )
	{
		Emerger x = (Emerger) who;
		counter++;
		if ( x.getCounter( ) < counter )
		{
			who.die( );
			return EmergeMonster.getAction( );
		}
		// if (Util.chance(20))
		// return PreemergeEffects.getAction();
		// return null;
		return null;
	}
}