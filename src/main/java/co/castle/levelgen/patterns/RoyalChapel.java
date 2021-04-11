package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;

public class RoyalChapel extends StaticPattern
{
	public RoyalChapel( )
	{
		cellMap = new String[ ][ ]
		{
			{	"    ######WWWWW###########WWWWWWW##########WWWW####################                   ",
				"   ##..sssssssssss.....sssssssssssss....ssssssssss................###                 ",
				"  ##................................................................###               ",
				"  #.d..sssssssssss.....sssssssssssss....ssssssssss...d................#               ",
				" ##...................................................................##              ",
				" #.....+++++++++++.....+++++++++++++....++++++++++.....................#              ",
				" #...                                                             .....#              ",
				"##...                                                              ....#              ",
				"#....                                                              ....##             ",
				"#...b                                                               ....#             ",
				"#...b                                                               ....#             ",
				"#...b                                                                ...##            ",
				"#...b                                                                b...#            ",
				"#...b                                                                b...#            ",
				"#...b                                                                b...#            ",
				"#...b                                                                ....#            ",
				"#...b                                                                b....            ",
				"#...b                                                                b....            ",
				"#...b                                                                b....            ",
				"#.k.b                                                                b....            ",
				"#...b                                                                b....            ",
				"#...b                                                                ....#            ",
				"#...b                                                                b...#            ",
				"#...b                                                                b...#            ",
				"#...b                                                                b...#            ",
				"#...b                                                                ...##            ",
				"#...b                                                               ....#             ",
				"#...b                                                               ....#             ",
				"#....                                                              ....##             ",
				"##...                                                              ....#              ",
				" #...                                                             .....#              ",
				" #.....+++++++++++.....+++++++++++++....++++++++++.....................#              ",
				" ##...................................................................##              ",
				"  #.d..sssssssssss.....sssssssssssss....ssssssssss...d................#               ",
				"  ##................................................................###               ",
				"   ##..sssssssssss.....sssssssssssss....ssssssssss................###                 ",
				"    ######WWWWW###########WWWWWWW##########WWWW####################                   "

			},
			{	"...###############WWWW################WWWW#########################...................",
				"...#................................................#.............###.................",
				"..##................................................#...............###...............",
				"..#.u...............................................#u................############....",
				".##.................................................#.............#####hhhhhhhhhh##...",
				".#################WWWW################WWWW#####################/###hhhhhhhhhhhhhhh##..",
				".#...o..o..o.................o..o..o.................o..o..o.....#hhhhhhhhhhhhhhhhh##.",
				"##...o.ooo.o.................o.ooo.o.................o.ooo.o.....bhhhhhhhhhhhhhhhhhh##",
				"#................................................................bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s.1..s....s....s....s....s..........==hhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#................................................................bhhhhhhhhhhhhhhhhhhh#",
				"#cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc....bhhhhhhhhhhhhhhhhhhh#",
				"#cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc....bhhhhhhhhhhhhhhhhh..#",
				"Scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc..1.bhhhhhhhhhhhhhhhh=..E",
				"#cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc....bhhhhhhhhhhhhhhhhh..#",
				"#cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc....bhhhhhhhhhhhhhhhhhhh#",
				"#................................................................bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s.1..s....s....s....s....s..........==hhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#........s....s....s....s....s....s....s....s....s....s..........bhhhhhhhhhhhhhhhhhhh#",
				"#................................................................bhhhhhhhhhhhhhhhhhhh#",
				"##...o.ooo.o.................o.ooo.o.................o.ooo.o.....bhhhhhhhhhhhhhhhhhh##",
				".#...o..o..o.................o..o..o.................o..o..o.....#hhhhhhhhhhhhhhhhh##.",
				".#################WWWW################WWWW#####################/###hhhhhhhhhhhhhhh##..",
				".##.................................................#.............#####hhhhhhhhhh##...",
				"..#.u...............................................#u................############....",
				"..##................................................#...............###...............",
				"...#................................................#.............###.................",
				"...##############WWWW#################WWWW#########################..................."

			} };

		charMap.put( "#", "CHURCH_WALL" );
		charMap.put( ".", "CHURCH_FLOOR" );
		charMap.put( "/", "CHURCH_FLOOR" );
		charMap.put( "k", "CHURCH_FLOOR FEATURE KEY 101" );
		charMap.put( "+", "CHURCH_WOODEN_BARRIER_H" );
		charMap.put( "b", "CHURCH_WOODEN_BARRIER_V" );
		charMap.put( "s", "CHURCH_CHAIR" );
		charMap.put( "u", "CHURCH_STAIRSUP" );
		charMap.put( "d", "CHURCH_STAIRSDOWN" );
		charMap.put( "o", "CHURCH_CONFESSIONARY" );
		charMap.put( "c", "CHURCH_CARPET" );
		charMap.put( "h", "ATRIUM" );
		charMap.put( "=", "MARBLE_STAIRS" );
		charMap.put( "W", "CHURCH_STAINED_WINDOW" );
		charMap.put( "1", "CHURCH_FLOOR MONSTER RULER_SWORD_LV1" );
		charMap.put( "S", "CHURCH_FLOOR EXIT _BACK" );
		charMap.put( "E", "CHURCH_FLOOR EOL MAGIC_DOOR COST 1" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "SKULL_HEAD", MonsterSpawnInfo.BORDER, 1 ), };
	}

	public String getDescription( )
	{
		return "Royal Chapel";
	}

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "SKULL_LORD", MonsterSpawnInfo.UNDERGROUND, 60 ),
			new MonsterSpawnInfo( "BONE_ARCHER", MonsterSpawnInfo.UNDERGROUND, 60 ),
			new MonsterSpawnInfo( "SALOME", MonsterSpawnInfo.UNDERGROUND, 60 ),
			new MonsterSpawnInfo( "RULER_SWORD_LV1", MonsterSpawnInfo.UNDERGROUND, 20 ),
			new MonsterSpawnInfo( "BALLOON_POD", MonsterSpawnInfo.UNDERGROUND, 80 ), };
	}

	public String getMapKey( )
	{
		return "CHAPEL";
	}

	public String getMusicKeyMorning( )
	{
		return "CHAPEL";
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