package co.castle.levelgen.patterns;

import java.util.Hashtable;

import co.castle.cuts.Unleasher;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.levelgen.StaticGenerator;

public abstract class StaticPattern
{
	protected String[ ][ ] cellMap;;
	protected Hashtable charMap = new Hashtable( );
	protected String[ ][ ] inhabitants;;
	protected Hashtable inhabitantsMap = new Hashtable( );
	protected MonsterSpawnInfo[ ] spawnInfo;
	protected Unleasher[ ] unleashers;

	public String getBoss( )
	{
		return null;
	}

	public sz.util.Position getBossPosition( )
	{
		return null;
	}

	public String[ ][ ] getCellMap( )
	{
		return cellMap;
	}

	public Hashtable getCharMap( )
	{
		return charMap;
	}

	public abstract String getDescription( );

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return null;
	}

	public String[ ][ ] getInhabitants( )
	{
		return inhabitants;
	}

	public Hashtable getInhabitantsMap( )
	{
		return inhabitantsMap;
	}

	public abstract String getMapKey( );

	public abstract String getMusicKeyMorning( );

	public abstract String getMusicKeyNoon( );

	public MonsterSpawnInfo[ ] getSpawnInfo( )
	{
		return spawnInfo;
	}

	public Unleasher[ ] getUnleashers( )
	{
		return unleashers;
	}

	public boolean isHaunted( )
	{
		return false;
	}

	public boolean isHostageSafe( )
	{
		return false;
	}

	public boolean isRutinary( )
	{
		return false;
	}

	public void setup( StaticGenerator gen )
	{
		gen.reset( );
		gen.setCharMap( getCharMap( ) );
		gen.setLevel( getCellMap( ) );
		gen.setInhabitantsMap( getInhabitantsMap( ) );
		gen.setInhabitants( getInhabitants( ) );
	}
}
