package co.castle.levelgen.patterns;

public class QuartersFork extends StaticPattern
{
	public QuartersFork( )
	{
		cellMap = new String[ ][ ]
		{
			{	"#####1#####", "###.....###", "##.i.c.i.##", "#.i.c-c.i.#", "#..c-.-c..#",
				"Scc-...-ccE", "#..c-.-c..#", "#.i.c-c.i.#", "##.i.c.i.##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "CASTLE_WALL" );
		charMap.put( ".", "MARBLE_FLOOR" );
		charMap.put( "i", "MARBLE_FLOOR FEATURE CANDLE" );
		charMap.put( "-", "MARBLE_FLOOR" );
		charMap.put( "c", "MARBLE_FLOOR" );
		charMap.put( "W", "CHURCH_STAINED_WINDOW" );
		charMap.put( "S", "CAVE_FLOOR EXIT _BACK" );
		charMap.put( "E", "CAVE_FLOOR EXIT _NEXT" );
		charMap.put( "1", "CAVE_FLOOR EXIT INNER_QUARTERS0" );
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