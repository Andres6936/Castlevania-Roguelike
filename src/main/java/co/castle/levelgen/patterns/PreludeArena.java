package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.arena.PreludeArena1;
import co.castle.cuts.arena.PreludeArena2;
import co.castle.levelgen.MonsterSpawnInfo;

public class PreludeArena extends StaticPattern
{
	public PreludeArena( )
	{
		this.cellMap = new String[ ][ ]
		{
			{	"tttttttttttttXXXXXXXXXXXXXXXXX.......t.t..............XXCCCC",
				"tttttXXXXXXXXX...............XXXXXX.tttXXXXXX.....ttt.X.CCCC",
				"ttttXX......................#.###.XXXXXX....XXXXXXXXXXX.CCCC",
				"tttXX........~~~~..........#.....#......................CCCC",
				"ttXX............~...............#.....s..~.....#....#...CCCC",
				"ttX.........#...~~........s..............~~~~...#..###..CCCC",
				"ttX........##....~~.......S........g......~..........#..CCCC",
				"t.X.......#.#.................h..t......~~~...S......#..CCCC",
				"t.X....S..#.#...........g..........................g....CCCC",
				"..X.......#.....g...#..........#................#.......CCCC",
				"..X........##.......##.........##.............h.#..s....CCCC",
				"..X.....s.#.#......#.#........##........#...............CCCC",
				"..X.....g..##......................g....##......#.......CCCC",
				"..X.......###..............#..........................S.CCCC",
				"..X...h...###......S......##.........S....s.....#.......CCCC",
				"..X.......###................ffff..........t....#......CCCCC",
				"..X.fffff..ffff...ffffffff.......s..fff.........#.ffff.CCCCC",
				".[[......i......i......i.......i...#.i.....ff====....i.CCCCC",
				".[[................................#........==pp==........]]",
				".[[==========================================paap=======..]]",
				".[[=================@=======================paaaap======..]]",
				".[[========================================paaccaap=====..]]",
				".[[========================================paaccaap=====..]]",
				".[[=========================================paaaap======..]]",
				".[[==========================================paap=======..]]",
				".[[.........................................==pp==........]]",
				".[[......i......i......i.fff#..i.....i.......====....i.CCCCC",
				"..Xfffff.........fffffff....##ffffff........fffffff....CCCCC",
				"..X......ffffff...........###........ffff..h...........CCCCC",
				"..X.....................t....h.....s................s...CCCC",
				"..X.............................tt.......t......##......CCCC",
				"..X.....g.......s..S...........t......S..........#....S.CCCC",
				"..X...h............................g....................CCCC",
				"..X.....S..#.........................~.........#...g~...CCCC",
				"..X~~~~~~.###.........g..............~~.........#..~~...CCCC",
				"..XX~~~~~~~~....................#.....~.s......#..~~....CCCC",
				"...XX~~~~~~~~~....h...S..........#......S...............CCCC",
				"...tXX~~~~~~~~~...............###.XXXXXX....XXXXXXXXXXX.CCCC",
				"t.tttXXXXXXXXX...............XXXXXX.tttXXXXXX...tttt..X.CCCC",
				"tttttttttttttXXXXXXXXXXXXXXXXX...................t..ttXXCCCC", } };

		charMap.put( ".", "GARDEN_GRASS" );
		charMap.put( "#", "FOREST_DIRT" );
		charMap.put( "=", "GARDEN_WALKWAY" );
		charMap.put( "[", "GARDEN_DOOR" );
		charMap.put( "X", "GARDEN_WALL" );
		charMap.put( "C", "CASTLE_WALL" );
		charMap.put( "g", "GARGOYLE_STATUE" );
		charMap.put( "h", "HUMAN_STATUE" );
		charMap.put( "t", "FOREST_TREE" );
		charMap.put( "s", "DEAD_STUMP" );
		charMap.put( "i", "GARDEN_TORCH FEATURE URN_CANDLE 100" );
		charMap.put( "f", "GARDEN_FENCE" );
		charMap.put( "]", "GARDEN_DOOR" );
		charMap.put( "c", "GARDEN_FOUNTAIN_CENTER" );
		charMap.put( "a", "GARDEN_FOUNTAIN_AROUND" );
		charMap.put( "p", "GARDEN_FOUNTAIN_POOL" );
		charMap.put( "@", "GARDEN_WALKWAY EXIT _START" );
		charMap.put( "S", "GARDEN_GRASS FEATURE CANDLE 60" );
		charMap.put( "~", "MOSS_WATERWAY" );

		unleashers = new Unleasher[ ]
		{ new PreludeArena1( ), new PreludeArena2( ) };

		spawnInfo = new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 100 ),
			new MonsterSpawnInfo( "WHITE_SKELETON", MonsterSpawnInfo.UNDERGROUND, 100 ),
			new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 100 ),
			new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 100 ), };
	}

	public String getDescription( )
	{
		return "Castle Garden";
	}

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 90 ),
			new MonsterSpawnInfo( "WHITE_SKELETON", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "PANTHER", MonsterSpawnInfo.UNDERGROUND, 40 ), };
	}

	public String getMapKey( )
	{
		return "ENTRANCE";
	}

	public String getMusicKeyMorning( )
	{
		return "ARENA";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

}