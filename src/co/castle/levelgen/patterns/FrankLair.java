package co.castle.levelgen.patterns;

import sz.util.Position;

public class FrankLair extends StaticPattern
{

	public FrankLair( )
	{
		cellMap = new String[ ][ ]
		{
			{

				",,,,,,,,,,,,,,,,########################",
				",,,,,,,,,,,,,,,,#.....W..........W.....#",
				",,,,,,,,,,,,,,,,#.....W..........W..i.##",
				",,,,,,,,,,,,,,,,#.....W..........W...###",
				",,,,,,,,,,,,,,,,#.....W.....o...W.....##",
				",,,,,,,,,,,,,,,,#.....W.........W......#",
				",,,,,,,,,,,,,,,,#.....W.......WW.......#",
				",,,,,,,,,,,,,,,,#.....W..WWWWWW........#",
				",,,,,,,,,,,,,,,,#.....WWWW..o..........#",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#......W..............##",
				"#################......W.............###",
				"#......c....c...........W..............#",
				"S......................TTT.............E",
				"#......c....c...........W..............#",
				"#################.......W............###",
				",,,,,,,,,,,,,,,,#......W..............##",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#......W...............#",
				",,,,,,,,,,,,,,,,#.....WWWW..o..........#",
				",,,,,,,,,,,,,,,,#.....W..WWWWWW........#",
				",,,,,,,,,,,,,,,,#...WWW.......WW.......#",
				",,,,,,,,,,,,,,,,#.WW...........WWW.....#",
				",,,,,,,,,,,,,,,,#WW...............WWWW##",
				",,,,,,,,,,,,,,,,#....................###",
				",,,,,,,,,,,,,,,,#...........o.........##",
				",,,,,,,,,,,,,,,,#......................#",
				",,,,,,,,,,,,,,,,########################" } };

		charMap.put( "o", "MARBLE_COLUMN" );
		charMap.put( ".", "DUNGEON_FLOOR" );
		charMap.put( ",", "VOID" );
		charMap.put( "#", "DUNGEON_WALL" );
		charMap.put( "W", "WIRES" );
		charMap.put( "T", "FRANK_TABLE" );
		charMap.put( "E", "DUNGEON_FLOOR EOL MAGIC_DOOR COST 1" );
		charMap.put( "S", "DUNGEON_FLOOR EXIT _BACK" );
		charMap.put( "$", "DUNGEON_FLOOR FEATURE TREASURE_SPAWNER 30" );
		charMap.put( "c", "DUNGEON_FLOOR FEATURE CANDLE 70" );
		charMap.put( "i", "DUNGEON_FLOOR MONSTER IGOR" );
	}

	public String getBoss( )
	{
		return "FRANK";

	}

	public Position getBossPosition( )
	{
		return new Position( 22, 15 );
	}

	public String getDescription( )
	{
		return "Dr. Frank Lab";
	}

	public String getMapKey( )
	{
		return "DUNGEON";
	}

	public String getMusicKeyMorning( )
	{
		return "BOSS2";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}
}