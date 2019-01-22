package co.castle.level;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import sz.util.Debug;

public class RespawnAI implements ActionSelector
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
		return "Respawn";
	}

	public Action selectAction( Actor who )
	{
		Debug.enterMethod( this, "selectAction", who );
		Respawner x = (Respawner) who;
		counter++;
		if ( x.getFreq( ) < counter )
		{
			counter = 0;
			Action ret = SpawnMonster.getAction( );
			Debug.exitMethod( ret );
			return ret;
		}
		Debug.exitMethod( "null" );
		return null;
	}
}