package co.castle.levelgen.patterns;

public class ReservoirPrize extends StaticPattern
{
	public ReservoirPrize( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.......##", "#....i....#", "#.........#",
				"#....*....S", "#.........#", "#....i....#", "##.......##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "CAVE_WALL" );
		charMap.put( ".", "CAVE_WATER" );
		charMap.put( "i", "CAVE_WALL" );
		charMap.put( "*", "CAVE_FLOOR ITEM ART_CARD_DEATH" );
		charMap.put( "S", "CAVE_WATER EXIT _BACK" );
	}

	public String getDescription( )
	{
		return "Humid Room";
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