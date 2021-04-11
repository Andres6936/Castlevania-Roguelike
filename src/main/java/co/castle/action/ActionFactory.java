package co.castle.action;

import java.util.Hashtable;

import sz.util.Debug;

public class ActionFactory
{
	private Hashtable definitions = new Hashtable( 20 );
	private final static ActionFactory singleton = new ActionFactory( );

	public static ActionFactory getActionFactory( )
	{
		return singleton;
	}

	public void addDefinition( Action definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public Action getAction( String id )
	{
		Action ret = (Action) definitions.get( id );
		Debug.doAssert( ret != null, "Tried to get an invalid " + id + " Action" );
		return ret;
	}

}
