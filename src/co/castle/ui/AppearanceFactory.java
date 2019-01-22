package co.castle.ui;

import java.util.Hashtable;

import sz.util.Debug;

public class AppearanceFactory
{
	private Hashtable <String, Appearance> definitions;
	private static AppearanceFactory singleton = new AppearanceFactory( );

	public AppearanceFactory( )
	{
		definitions = new Hashtable <String, Appearance>( 40 );
	}

	public static AppearanceFactory getAppearanceFactory( )
	{
		return singleton;
	}

	public void addDefinition( Appearance definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public Appearance getAppearance( String id )
	{
		Appearance ret = (Appearance) definitions.get( id );
		Debug.doAssert( ret != null, "Couldnt find the appearance " + id );
		return ret;
	}

}