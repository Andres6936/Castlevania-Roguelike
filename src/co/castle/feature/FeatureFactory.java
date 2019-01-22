package co.castle.feature;

import java.util.Hashtable;

import sz.util.Debug;

public class FeatureFactory
{
	private Hashtable definitions;

	private static FeatureFactory singleton = new FeatureFactory( );

	public FeatureFactory( )
	{
		definitions = new Hashtable( 40 );
	}

	public static FeatureFactory getFactory( )
	{
		return singleton;
	}

	public void addDefinition( Feature definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public Feature buildFeature( String id )
	{
		Feature x = (Feature) definitions.get( id );
		if ( x != null )
			return (Feature) x.clone( );
		Debug.byebye( "Feature " + id + " not found" );
		return null;
	}

	public String getDescriptionForID( String id )
	{
		Feature x = (Feature) definitions.get( id );
		if ( x != null )
			return x.getDescription( );
		else
			return "?";
	}

	public void init( Feature[ ] defs )
	{
		for ( int i = 0; i < defs.length; i++ )
			definitions.put( defs[ i ].getID( ), defs[ i ] );
	}
}