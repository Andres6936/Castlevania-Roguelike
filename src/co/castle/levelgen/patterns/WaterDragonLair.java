package co.castle.levelgen.patterns;

import sz.util.Position;

public class WaterDragonLair extends StaticPattern
{

	public WaterDragonLair( )
	{
		cellMap = new String[ ][ ]
		{
			{

				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"www---------------------------------www",
				"ww-----------------------------------ww",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"w----------------.....----------------w",
				"w---------------.......---------------w",
				"w----.....-----.........-----.....----w",
				"w---.......----.........----.......---w",
				"E---.......----.........----.......---S",
				"w---.......----.........----.......---w",
				"w----.....-----.........-----.....----w",
				"w---------------.......---------------w",
				"w----------------.....----------------w",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"w-------------------------------------w",
				"ww-----------------------------------ww",
				"www---------------------------------www",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww", } };

		charMap.put( ".", "CAVE_FLOOR" );
		charMap.put( "-", "CAVE_WATER" );
		charMap.put( "w", "CAVE_WALL" );
		charMap.put( "S", "CAVE_WATER EXIT _BACK" );
		charMap.put( "E", "CAVE_WATER EOL MAGIC_DOOR COST 1" );
	}

	public String getBoss( )
	{
		return "WATER_DRAGON";

	}

	public Position getBossPosition( )
	{
		return new Position( 10, 10 );
	}

	public String getDescription( )
	{
		return "Lair of the Water Dragon";
	}

	public String getMapKey( )
	{
		return "RESERVOIR";
	}

	public String getMusicKeyMorning( )
	{
		return "BOSS2";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

	public boolean isRutinary( )
	{
		return true;
	}
}