package co.castle.levelgen.patterns;

public class CaveFork extends StaticPattern
{
	public CaveFork( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.i.c.i.##", "#.i.c-c.i.#", "#..c-.-c..#",
				"Scc-.*.-ccE", "#..c-.-c..#", "#.i.c-c.i.#", "##.i.c.i.##", "###.....###",
				"#####1#####",

			} };

		charMap.put( "#", "CAVE_WALL" );
		charMap.put( ".", "CAVE_FLOOR" );
		charMap.put( "i", "CAVE_FLOOR FEATURE CANDLE" );
		charMap.put( "-", "CAVE_FLOOR" );
		charMap.put( "*", "CAVE_FLOOR FEATURE TELEPORT" );
		charMap.put( "c", "CAVE_FLOOR" );
		charMap.put( "W", "CHURCH_STAINED_WINDOW" );
		charMap.put( "S", "CAVE_FLOOR EXIT _BACK" );
		charMap.put( "E", "CAVE_FLOOR EXIT _NEXT" );
		charMap.put( "1", "CAVE_FLOOR EXIT WAREHOUSEX0" );
	}

	public String getDescription( )
	{
		return "???";
	}

	public String getMapKey( )
	{
		return null;
	}

	public String getMusicKeyMorning( )
	{
		return "";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}
}