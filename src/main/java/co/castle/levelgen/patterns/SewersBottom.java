package co.castle.levelgen.patterns;

public class SewersBottom extends StaticPattern
{
	public SewersBottom( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "#####S#####", "#####-#####", "#####-#####", "#####-#####",
				"#####-#####", "#####-#####", "#####-#####", "#####-#####", "#####-#####",
				"#####-#####", "#####-#####", "#####-#####", "###.---.###", "##...-...##",
				"#...---...#", "#..-----..#", "#..--E--..#", "#..-----..#", "#...---...#",
				"##.......##", "###.....###", "###########",

			} };

		charMap.put( "#", "SEWERS_WALL" );
		charMap.put( ".", "SEWERS_FLOOR" );
		charMap.put( "-", "SEWERS_FLOOR" );
		charMap.put( "S", "STATIC_VOID EXIT _BACK" );
		charMap.put( "E", "WEIRD_MACHINE" );
	}

	public String getDescription( )
	{
		return "Belmonts' Secret";
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