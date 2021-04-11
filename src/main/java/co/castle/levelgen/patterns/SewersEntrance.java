package co.castle.levelgen.patterns;

public class SewersEntrance extends StaticPattern
{
	public SewersEntrance( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########", "###.-S-.###", "##...-...##", "#...---...#", "#..-----..#",
				"#..--E--..#", "#..-----..#", "#...---...#", "##.......##", "###.....###",
				"###########",

			} };

		charMap.put( "#", "SEWERS_WALL" );
		charMap.put( ".", "SEWERS_FLOOR" );
		charMap.put( "-", "SEWERS_FLOOR" );
		charMap.put( "S", "STATIC_VOID EXIT TOWN0" );
		charMap.put( "E", "STATIC_VOID EXIT SEWERS0" );
	}

	public String getDescription( )
	{
		return "Petra Sewers";
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