package co.castle.levelgen.patterns;

public class QuartersPrize extends StaticPattern
{
	public QuartersPrize( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.......##", "#..i.*.i..#", "#.........#",
				"#.........#", "#.........#", "#....S....#", "##.......##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "QUARTERS_WALL" );
		charMap.put( ".", "QUARTERS_FLOOR" );
		charMap.put( "i", "MARBLE_COLUMN" );
		charMap.put( "*", "QUARTERS_FLOOR ITEM ART_CARD_SOL" );
		charMap.put( "S", "MARBLE_STAIRSDOWN_FAKE EXIT _BACK" );
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