package co.castle.ai;

import java.util.Hashtable;

import sz.util.Debug;

public class SelectorFactory
{
	private Hashtable definitions;
	private final static SelectorFactory singleton = new SelectorFactory( );

	/*
	 * public ActionSelector buildSelector (String id){ Cell x = (Cell)
	 * definitions.get(id); return x.clone(); }
	 */

	public SelectorFactory( )
	{
		definitions = new Hashtable( 40 );
	}

	public static SelectorFactory getSelectorFactory( )
	{
		return singleton;
	}

	public void addDefinition( ActionSelector definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public ActionSelector createSelector( String id )
	{
		ActionSelector ret = ( (ActionSelector) definitions.get( id ) ).derive( );
		Debug.doAssert( ret != null, "Tried to create an invalid " + id
				+ " ActionSelector" + " " + this.toString( ) );
		return ret;
	}

	public ActionSelector getSelector( String id )
	{
		ActionSelector ret = (ActionSelector) definitions.get( id );
		Debug.doAssert( ret != null,
				"Tried to get an invalid " + id + " ActionSelector" );
		return ret;
	}

}