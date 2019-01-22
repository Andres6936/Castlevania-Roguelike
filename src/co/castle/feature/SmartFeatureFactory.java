package co.castle.feature;

import java.util.Hashtable;

import sz.util.Debug;

public class SmartFeatureFactory
{
	private Hashtable definitions;
	private static SmartFeatureFactory singleton = new SmartFeatureFactory( );

	public SmartFeatureFactory( )
	{
		definitions = new Hashtable( 40 );
	}

	public static SmartFeatureFactory getFactory( )
	{
		return singleton;
	}

	public void addDefinition( SmartFeature definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public SmartFeature buildFeature( String id )
	{
		SmartFeature x = (SmartFeature) definitions.get( id );
		if ( x != null )
			return (SmartFeature) x.clone( );
		Debug.byebye( "SmartFeature " + id + " not found" );
		return null;
	}

	public void init( SmartFeature[ ] defs )
	{
		for ( int i = 0; i < defs.length; i++ )
			definitions.put( defs[ i ].getID( ), defs[ i ] );
	}
}
