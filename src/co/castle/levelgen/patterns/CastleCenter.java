package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;

public class CastleCenter extends StaticPattern
{
	public CastleCenter( )
	{
		cellMap = new String[ ][ ]
		{
			{	"###########e###########################g################",
				"#......................................................#",
				"#......................................................#",
				"#..<................................................<..#",
				"#......................................................#",
				"#.....                                            .....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....f",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#....                                              ....#",
				"#.....                                             ....#",
				"#......................................................#",
				"#..<................................................<..#",
				"#......................................................#",
				"#......................................................#",
				"########################################################", },
			{	"########################################################",
				"#......................................................#",
				"#......................................................#",
				"#..>#..............................................#>..#",
				"#..##..............................................##..#",
				"#......................................................#",
				"#......................................................#",
				"#......------------------------------------------......#",
				"#......------------------------------------------......#",
				"#......----------c----c----c----c----c-----------......#",
				"#......------ttttttttttttttttttttttttttttt-------......#",
				"#......-----ctttttttttttttttttttttttttttttc------......#",
				"#......------ttttttttttttttttttttttttttttt-------......#",
				"#......----------c----c----c----c----c-----------......#",
				"#......------------------------------------------......#",
				"#......------------------------------------------......#",
				"#......................................................#",
				"#......................................................#",
				"#..##..............................................##..#",
				"#..>#..............................................#>..#",
				"#......................................................#",
				"#......................................................#",
				"############################K###########################", } };

		charMap.put( "#", "CASTLE_WALL" );
		charMap.put( ".", "MARBLE_FLOOR" );
		charMap.put( "-", "RED_CARPET" );
		charMap.put( ">", "MARBLE_STAIRSUP" );
		charMap.put( "<", "MARBLE_STAIRSDOWN" );
		charMap.put( "c", "DINING_CHAIR" );
		charMap.put( "t", "DINING_TABLE" );

		charMap.put( "K", "MARBLE_FLOOR EXIT COURTYARD0" );
		charMap.put( "e", "MARBLE_FLOOR" );
		charMap.put( "f", "MARBLE_FLOOR" );
		charMap.put( "g", "MARBLE_FLOOR" );

		/*
		 * charMap.put("e", "MARBLE_FLOOR EXIT TWINTOWERS0"); charMap.put("f",
		 * "MARBLE_FLOOR EXIT LIBRARY0"); charMap.put("g",
		 * "MARBLE_FLOOR EXIT COLOSSEUM0");
		 */
	}

	public String getDescription( )
	{
		return "Dining Hall";
	}

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "SKULL_LORD", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "CAGNAZOO", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "ALRAUNE", MonsterSpawnInfo.UNDERGROUND, 20 ), };
	}

	public String getMapKey( )
	{
		return "CASTLE_CENTER";
	}

	public String getMusicKeyMorning( )
	{
		return "DINING_HALL";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

	public boolean isRutinary( )
	{
		return true;
	}
}