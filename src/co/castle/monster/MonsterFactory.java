package co.castle.monster;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.level.Level;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.ui.AppearanceFactory;
import sz.util.Debug;
import sz.util.Util;

public class MonsterFactory
{
	private Hashtable definitions;

	private int lastSpawnLocation;
	private Vector vDefinitions = new Vector( 50 );

	private final static MonsterFactory singleton = new MonsterFactory( );

	public MonsterFactory( )
	{
		definitions = new Hashtable( 40 );
	}

	public static MonsterFactory getFactory( )
	{
		return singleton;
	}

	/*
	 * public void addDefinition(MonsterDefinition definition){
	 * definitions.put(definition.getID(), definition); }
	 */

	public Monster buildMonster( String id )
	{
		return new Monster( (MonsterDefinition) definitions.get( id ) );
	}

	public MonsterDefinition getDefinition( String id )
	{
		return (MonsterDefinition) definitions.get( id );
	}

	public int getLastSpawnPosition( )
	{
		return lastSpawnLocation;
	}

	public Monster getMonsterForLevel( Level level )
	{
		MonsterSpawnInfo[ ] spawnIDs = level.getSpawnInfo( );
		if ( spawnIDs == null || spawnIDs.length == 0 )
			return null;
		while ( true )
		{
			int rand = Util.rand( 0, spawnIDs.length - 1 );
			if ( Util.chance( spawnIDs[ rand ].getFrequency( ) ) )
			{
				lastSpawnLocation = spawnIDs[ rand ].getSpawnLocation( );
				return new Monster( (MonsterDefinition) definitions
						.get( spawnIDs[ rand ].getMonsterID( ) ) );
			}
		}
	}

	public void init( MonsterDefinition[ ] defs )
	{
		for ( int i = 0; i < defs.length; i++ )
		{
			defs[ i ].setAppearance( AppearanceFactory.getAppearanceFactory( )
					.getAppearance( defs[ i ].getID( ) ) );
			definitions.put( defs[ i ].getID( ), defs[ i ] );
			vDefinitions.add( defs[ i ] );

		}
	}

	public void printAppearances( )
	{
		Enumeration x = definitions.keys( );
		while ( x.hasMoreElements( ) )
		{
			MonsterDefinition d = (MonsterDefinition) definitions.get( x.nextElement( ) );
			Debug.say( "Monstero " + d.getDescription( ) + " app " + d.getAppearance( ) );
		}
	}
}