package co.castle.levelgen.patterns;

public class Telepad extends StaticPattern
{
	public Telepad( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.i.c.i.##", "#.i.c-c.i.#", "#..c-.-c..#",
				"Scc-.*.-ccE", "#..c-.-c..#", "#.i.c-c.i.#", "##.i.c.i.##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "CHURCH_WALL" );
		charMap.put( ".", "CHURCH_FLOOR" );
		charMap.put( "i", "CHURCH_FLOOR FEATURE CANDLE" );
		charMap.put( "-", "COURTYARD_FLOOR" );
		charMap.put( "*", "CHURCH_FLOOR FEATURE TELEPORT" );
		charMap.put( "c", "CHURCH_CARPET" );
		charMap.put( "W", "CHURCH_STAINED_WINDOW" );
		charMap.put( "S", "CHURCH_FLOOR EXIT _BACK" );
		charMap.put( "E", "CHURCH_FLOOR EXIT _NEXT" );
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