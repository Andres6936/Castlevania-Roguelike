package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.entrance.Entrance1;
import co.castle.cuts.entrance.PreludeSound;

public class Garden extends StaticPattern
{
	public Garden( )
	{
		this.cellMap = new String[ ][ ]
		{
			{	"&&&&&&&&&&&&############################&&&&&##########CCC",
				"&&&&&&#######,,,,,,,,,,,,,,,,,,,,,,,,,,#######,,,,dddd$CCC",
				"&&&&###,,,,,,,,,,,,,,,,,1d,,,,,,,1,,,,,,dd,,,,,,,,,,dddCCC",
				"&&&&#,,,,,,,,,,,,1,,,,,,,d,,,,,,,,,,,,ddd,,,,,,,,,,,dddCCC",
				"&&&&#,,,,,,,,,,,,,,,,,,,,d,,,,,,,,,,,,,,,,,,S,,,,,,,dddCCC",
				"&&###,,,,,,,S,,@,,,,,,,dd,g,,,,,,,,,,,,,g,,,,,,,,,W,,,dCCC",
				",,#,,,,,,,,,,,,,,,,,,,,d,,,,,,,FFF,,,,,,,,,,,,,1,WOW,,dCCC",
				",,#,,u,,,,,,,,,FFFF,,,,,,,,,,,,,,,,,,,,,FFFF,,,,,,W,,,,CCC",
				"&,#,,,,FFF,,,,,,,,,,,,,,FFFF,,,,,,,,,ddd,,,,,,,u,,,,,,,CCC",
				"&,#,,,,I,,,1,,,I,,,,,,I,,,,,,I,,,,,,I,u,,dd,I,,,,,,,,,dd.C",
				".d|.............................1.........d..............C",
				"T,........................................d.............XE",
				"d.|...........dd........................................,C",
				",,#,,,,I,,,,,d,I,,,,,,I,,,,,,I,,,,,,I,,,,,,,I,,,,,,ddd,,.C",
				",,#,,,,FFF,,,,,,,,,,,,,,FFFF,,,,u,,,,,,,,,,,,,,1,,,,,ddCCC",
				",,#,u,,,,,,,,,,FFFF,,,,,,,,,,,,,,,,,,,,,FFFF,,u,,,W,,,,CCC",
				"&,#,,,,,,,,,,,,,,,,,@dd,,,,,,,,FFF,,,,,@,,,,,,,,,WOW,,,CCC",
				"&&###,,,,1,,S,,,,,,ddd,,,,,,,,1,,,,,,,,,,,,d,,,,,,W,,,,CCC",
				"&&&&#,,,,,,,,,,,,,,,,,,,,d,,,,,,,,,,,,d,d,,,,,,,,,,,dddCCC",
				"&&&&#,,,,,,,,,,,,,,,,,,dd,,,,,m,,,,,,S,,,,,1,,,,,,,,,ddCCC",
				"&&&&###,,,,,,,,,,d,,,,,,,,,,,ddd,,,,,,,,,,d,S,,,,,,,,,,CCC",
				"&&&&&&#######,,,,dddd,,,,u,,,,,,,,,,,dd#######,,,,,,,,$CCC",
				"&&&&&&&&&&&&############################&&&&&##########CCC" } };

		charMap.put( ",", "GARDEN_GRASS" );
		charMap.put( "&", "FOREST_TREE" );
		charMap.put( "1", "GARDEN_TREE" );
		charMap.put( ".", "GARDEN_WALKWAY" );
		charMap.put( "T", "GARDEN_WALKWAY EXIT _BACK" );
		charMap.put( "E", "GARDEN_GRASS EXIT _NEXT" );
		charMap.put( "u", "DEAD_STUMP" );
		charMap.put( "d", "FOREST_DIRT" );
		charMap.put( "F", "GARDEN_FENCE" );
		charMap.put( "I", "GARDEN_TORCH FEATURE URN_CANDLE 100" );
		charMap.put( "S", "GARDEN_GRASS FEATURE CANDLE 60" );
		charMap.put( "O", "GARDEN_FOUNTAIN_CENTER" );
		charMap.put( "W", "GARDEN_FOUNTAIN_AROUND" );
		charMap.put( "@", "HUMAN_STATUE" );
		charMap.put( "g", "GARGOYLE_STATUE" );
		charMap.put( "D", "CASTLE_DOOR" );
		charMap.put( "#", "GARDEN_WALL" );
		charMap.put( "C", "CASTLE_WALL" );
		charMap.put( "X", "GARDEN_WALKWAY NPC UNIDED_CLAW" );
		charMap.put( "|", "GARDEN_DOOR" );
		charMap.put( "m", "GARDEN_GRASS MERCHANT 1" );
		charMap.put( "$", "GARDEN_GRASS FEATURE TREASURE_SPAWNER 10" );

		unleashers = new Unleasher[ ]
		{ new PreludeSound( ), new Entrance1( ) };
	}

	public String getDescription( )
	{
		return "Castle Garden";
	}

	public String getMapKey( )
	{
		return "ENTRANCE";
	}

	public String getMusicKeyMorning( )
	{
		return "GARDEN";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

	public boolean isHostageSafe( )
	{
		return true;
	}

}