package co.castle.levelgen.patterns;

public class CatacombsPrize extends StaticPattern
{
	public CatacombsPrize( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.....###", "##.......##", "#....i....#", "#.........#",
				"S....*....#", "#.........#", "#....i....#", "##.......##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "CAVE_WALL" );
		charMap.put( ".", "CAVE_FLOOR" );
		charMap.put( "i", "CAVE_WALL" );
		charMap.put( "*", "CAVE_FLOOR ITEM ART_CARD_MOONS" );
		charMap.put( "S", "CAVE_FLOOR EXIT _BACK" );
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