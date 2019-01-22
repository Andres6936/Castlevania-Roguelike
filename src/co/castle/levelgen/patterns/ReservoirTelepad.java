package co.castle.levelgen.patterns;

public class ReservoirTelepad extends StaticPattern
{
	public ReservoirTelepad( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.......##", "#...c-c...#", "#..c---c..#",
				"E.c--*--c.S", "#..c---c..#", "#...c-c...#", "##.......##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "CAVE_WALL" );
		charMap.put( ".", "CAVE_WATER" );
		charMap.put( "-", "CAVE_FLOOR" );
		charMap.put( "*", "CAVE_FLOOR FEATURE TELEPORT" );
		charMap.put( "c", "CAVE_WALL" );
		charMap.put( "W", "CAVE_WALL" );
		charMap.put( "S", "CAVE_WATER EXIT _BACK" );
		charMap.put( "E", "CAVE_WATER EXIT _NEXT" );
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