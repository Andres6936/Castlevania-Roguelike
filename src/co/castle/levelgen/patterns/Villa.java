package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.villa.Villa1;
import co.castle.levelgen.MonsterSpawnInfo;

public class Villa extends StaticPattern
{
	public Villa( )
	{
		cellMap = new String[ ][ ]
		{
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"w                                                                    w",
				"w                                                                    w",
				"w       3==3                            3333=33333=333               w",
				"w      33,,33                          33,,,,,,,,,,,,33              w",
				"w     33,,,,33                        33,,,c,,,c,,,c,,33             w",
				"w     3,,,,,,3                       33,c,,,,,,,,,,,,,c,33           w",
				"w    33,,,,,,33                      3,,,,,,,,,,,,,,,,,,g3           w",
				"w    3,,,,,,,,3                      3,,,,,,,,,,,,,,,,,,,3           w",
				"w    33,,,>,,33                      3,c,,,,1,,,,,,2,,,c,3           w",
				"w     3,,,,,,3                       =,,,,,,,,,,,,,,,,,,,=           w",
				"w     333333,333                     3,,,,,,,,,,,,,,,,,,,3           w",
				"w     3     ,,,b                     3,,,,,,,,,,,,,,,,,,,3           w",
				"w     3     ,,Lb                     3,c,,,,,,,>,,,,,,,c,3           w",
				"w     3     ,,,b                     3,,,,,,,,,,,,,,,,,,,3           w",
				"w     333333,333                     3,,,,,,,,,,,,,,,,,,,3           w",
				"w     3,,,,,,3                       =,,,,,,5,,,,,,,4,,,,=           w",
				"w    33,,,>,,33                      3,c,,,,,,,,,,,,,,,c,3           w",
				"w    3,,,,,,,,3                      3,,,,,,,,,,,,,,,,,,,3           w",
				"w    33,,,,,,33                      3,,,,,,,,,,,,,,,,,,g3           w",
				"w     3,,,,,,3                       33,c,,,,,,,,,,,,,c,33           w",
				"w     33,,,,33                        33,,,c,,,c,,,c,,,33            w",
				"w      33,,33                          33,,,,,,,,,,,,,33             w",
				"w       3==3                            3333=33333=3333              w",
				"w                                                                    w",
				"w                                                                    w",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" },
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"w............................................................t.......w",
				"w...........t.................t..33333333333.333333..................w",
				"w......t..........3333333.......33,,,,,,.,,..,,,,,33.................w",
				"w...............dd3...,,3.......3,,,,.,,,,,.,,,,,,,33................w",
				"w...............dddd.ddd3.......3,,,c,,c,,c,,c,,c,,,33333333ss.......w",
				"w...............d.3d,,,.3....t..3,,,,,,,,,,,,,,,,,,,,,,,,,..ss.......w",
				"w....333....333...3..,,.3.......3333,,,,,,,,,,,,,,,,,,,,g,,.ss.......w",
				"w....3333333333...333.333..........33,,,,,,,,,,,,,,,,,,,,,c,ss...t...w",
				"w....33ooo<oo33....t......t........s33,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w.....3oooooo3.....................sh3,,,,,,,,,,,,,,,,,,,,c,ss.......w",
				"w.....3oooooo3..t..++++++++..t.....sh3,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w.....3oooooo3....++ffffff++.......s33,,,,,,,,,,,,,,,,,,,,c,ss.......w",
				"w.....ooMoooo/....+ffffffff+.......s,,,,,,,,,,,<,,,,,,,,,,,,ss.......E",
				"w.....3oooooo3....++ffffff++.......s33,,,,,,,,,,,,,,,,,,,,c,ss.......w",
				"w.....3oooooo3..t..++++++++..t.....sh3,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w.....3oooooo3.....................sh3,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w....33ooo<oo33....t......t........s33,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w..t.3333333333...3.3..33..........3,,,,,,,,,,,,,,,,,,,,,,c,ss.......w",
				"w....333....333...3ddd,.3........333,,,,,,,,,,,,,,,,,,,,g,,,ss.......w",
				"w..............t..3dd.d.3........3,,,,,,,,,,,,,,,,,,,,,,,,,,ss.......w",
				"w.................3ddd,.3........3,,,,,c,,c,,c,,c,,,33333333ss.......w",
				"w.................3ddd,,3........3,,,,,,.,,,,,,,,,,33................w",
				"w...........t.....3333333....t...33,,,,...,,..,,,,33.............t...w",
				"w.................................3333333.333.33333..................w",
				"w....................................................t...............w",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" } };

		charMap.put( "w", "COURTYARD_WALL" );
		charMap.put( ".", "COURTYARD_GRASS" );

		charMap.put( "1", "COURTYARD_FLOOR MERCHANT 1" );
		charMap.put( "2", "COURTYARD_FLOOR MERCHANT 2" );
		charMap.put( "4", "COURTYARD_FLOOR MERCHANT 3" );
		charMap.put( "5", "COURTYARD_FLOOR MERCHANT 4" );
		charMap.put( "+", "COURTYARD_FENCE" );
		charMap.put( "t", "COURTYARD_TREE" );
		charMap.put( "3", "COURTYARD_RUINED_WALL" );
		charMap.put( "d", "COURTYARD_GRASS" );
		charMap.put( "<", "CHURCH_STAIRSUP" );
		charMap.put( ">", "CHURCH_STAIRSDOWN" );
		charMap.put( "/", "COURTYARD_FLOOR" );
		charMap.put( "o", "COURTYARD_FLOOR" );
		charMap.put( "b", "COURTYARD_FLOOR" );
		charMap.put( "M", "COURTYARD_FLOOR NPC MAIDEN" );
		charMap.put( "L", "COURTYARD_FLOOR ITEM ART_CARD_LOVE" );
		charMap.put( "c", "COURTYARD_COLUMN" );
		charMap.put( "E", "COURTYARD_GRASS EXIT COURTYARD0" );
		charMap.put( ",", "COURTYARD_FLOOR" );
		charMap.put( "s", "COURTYARD_STAIRS" );
		charMap.put( "h", "HUMAN_STATUE" );
		charMap.put( "g", "GARGOYLE_STATUE" );
		charMap.put( "f", "COURTYARD_FLOWERS" );
		charMap.put( "=", "COURTYARD_FLOOR" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 1 ), };

		unleashers = new Unleasher[ ]
		{ new Villa1( ) };

	}

	public String getDescription( )
	{
		return "Castle Villa";
	}

	public String getMapKey( )
	{
		return "VILLA";
	}

	public String getMusicKeyMorning( )
	{
		return "COURTYARD";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

	public boolean isHostageSafe( )
	{
		return true;
	}

	public boolean isRutinary( )
	{
		return true;
	}
}