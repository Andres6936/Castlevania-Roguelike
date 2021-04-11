package co.castle.levelgen.patterns;

import java.util.Hashtable;

import co.castle.levelgen.LevelFeature;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.levelgen.PatternGenerator;
import sz.util.Position;

public class Moat
{
	public static String[ ][ ] base = new String[ ][ ]
	{
		{	"#######################################################",
			"##......o.........o...222222...........o.........o...##",
			"#....................2222222..........................#",
			"#.....................2222222.........................#",
			"#....................22222222.........................#",
			"#.....................2222222.........................#",
			"#....................2222222..........................#",
			"#o...................22222222........................o#",
			"#.....................22222222........................#",
			"#......................2222222........................#",
			"#.....................2222222.........................#",
			"#....................22222222.........................#",
			"#....................222222222........................#",
			"S....................222222222........................E",
			"#....................222222222........................#",
			"#....................222222222........................#",
			"#.....................2222222.........................#",
			"#......................222222.........................#",
			"#....................2222222..........................#",
			"#o...................22222222........................o#",
			"#....................222222222........................#",
			"#.....................2222222.........................#",
			"#.....................222222..........................#",
			"#....................222222222........................#",
			"#....................222222222........................#",
			"##......o.........o...222222...........o.........o...##",
			"#######################################################" },
		{ // x x
			"#######################################################",
			"##......o........owwwwwwwwwwwwwwwwwwwwwow........o...##",
			"#................wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#................wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwww..............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwww..............#",
			"#o..............wwwwwwwwwwwwwwwwwwwwwwwww............o#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#.....#.#.......wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#......*.........wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#.....#.#........wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwww..............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwww..............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#................wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#................wwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#o..............wwwwwwwwwwwwwwwwwwwwwwwww............o#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwww..............#",
			"#...............wwwwwwwwwwwwwwwwwwwwwwwww.............#",
			"##......o.......wowwwwwwwwwwwwwwwwwwwwwow........o...##",
			"#######################################################" },
		{ // x x
			"#######################################################",
			"##################---------------------#-##############",
			"########---------------------------------##############",
			"########-########------------------------##############",
			"########-#######-------------------------##############",
			"########-#######-----1------------1-----###############",
			"########-#######------------------------###############",
			"########-#######-------------------------##############",
			"#######----------------------------------##############",
			"#######-########-------------------------##############",
			"#######-########-------------------------##############",
			"######---#######-------------------------##############",
			"######-&-########------------------------##############",
			"######---########----------1-------------##############",
			"#######-########-------------------------##############",
			"#######-########------------------------###############",
			"#######---------------------------------###############",
			"########-#######-------------------------########...###",
			"########-########------------------------########.-.###",
			"########-########------------------------#########-####",
			"########-#######-------------------------#########-####",
			"########-#######-------------------------#########-####",
			"########-------------1------------1----------------####",
			"################------------------------###############",
			"################-------------------------##############",
			"################-#---------------------#-##############",
			"#######################################################" } };
	public static LevelFeature baseFeature = new LevelFeature( );

	public static Hashtable defaultCharMap = new Hashtable( );

	public static String[ ] entrance1 = new String[ ]
	{	"               ", "     sssss     ", "   22222       ", "  22222        ",
		"22222          ", "222222         ", "22222          ", "  22222        ",
		"   22222       ", "     ssss      ", "               " };

	public static String[ ] entrance2 = new String[ ]
	{	"  222222       ", "   2222        ", "   22222   s   ", "  22222  22    ",
		"22222          ", "22222222 22222 ", "22222          ", "  22222  222   ",
		"   2222222 s   ", "   2222        ", "  2222222      " };

	public static String[ ] entrance3 = new String[ ]
	{	"               ", "2222222        ", "2222222     o  ", "2222222        ",
		"22222          ", "22222ssss      ", "22222          ", "2222222        ",
		"2222222     o  ", "2222222        ", "               " };

	public static String[ ] entrance4 = new String[ ]
	{	"               ", "222     o  o  o", "2222           ", "22222          ",
		"22222          ", "22222  2s      ", "22222          ", "22222          ",
		"2222           ", "222     o     o", "               " };

	public static String[ ] entrance5 = new String[ ]
	{	"               ", "222     o  o  o", "2222           ", "22222  22222   ",
		"22222     22   ", "22222   sss2   ", "22222     22   ", "22222  22222   ",
		"2222           ", "222     o     o", "               " };

	public static String[ ] exit1 = new String[ ]
	{	"               ", "           o  o", "2       22     ", "           22  ",
		" 2      s  2   ", "  222   ss22222", "22222   s  2   ", "  222      222 ",
		" 222           ", "  2ss   o     o", "               " };

	public static String[ ] exit2 = new String[ ]
	{	"               ", " 222   o   2222", " 222ss     2222", " 222      22222",
		" 222      22222", " 222   sss22222", " 222      22222", " 222      22222",
		" 222ss     2222", " 222    o  2222", "               " };

	public static String[ ] exit3 = new String[ ]
	{	"               ", " s22   o   2222", " s22       2222", "  22      22222",
		"   2      22222", "   222222222222", "   2      22222", " 222      22222",
		" s22       2222", " s22    o  2222", "               " };

	public static String[ ] mid1 = new String[ ]
	{	"         222        ", "    222222ssssss    ", "   222222222        ",
		"   222222222        ", "   222222222        ", "   222222222        ",
		"    222222ssssss    ", "         222        " };

	public static String[ ] mid2 = new String[ ]
	{	"         22         ", "     22222          ", "     2222222s       ",
		"     22222   s      ", "     22222   s      ", "     2222222s       ",
		"     22222          ", "         22         " };

	public static String[ ] mid3 = new String[ ]
	{	"     222222         ", "    222222          ", "   2222s2222        ",
		"   222...222ss      ", "   222...222ss      ", "   2222s2222        ",
		"    222222          ", "     2222222        " };

	public static String[ ] mid4 = new String[ ]
	{	"    22222222222222  ", "    22s....22222    ", "   222222222ss      ",
		"   222222222        ", "   222222222        ", "   222222222ss      ",
		"    22s....22222    ", "    22222222222222  " };

	public static String[ ] plat1 = new String[ ]
	{	"        ", "  xxx   ", "  xxx   ", " M Mxxxx", "  M xxxx", "  xxx   ", "  xxx   ",
		"        " };

	public static String[ ] plat10 = new String[ ]
	{	"mm  mm  ", "  mm  mm", "xxxxxxxx", "x.......", "xxx.xx.x", "xx..x.xx", "..$x....",
		"xxxxxxxx" };

	public static String[ ] plat11 = new String[ ]
	{	"    x   ", "  M  M  ", " xx     ", " x  x  x", "     M  ", "        ", " xx x   ",
		"        " };

	public static String[ ] plat12 = new String[ ]
	{	"  ..s.. ", ".... mm ", "  M   s ", "  m. 22 ", "  .. 22 ", "  M   s ", ".... .. ",
		"  ..s.. " };

	public static String[ ] plat13 = new String[ ]
	{	"M    ...", "  ....  ", "...m.. M", "    ... ", " .  .mm ", " ....   ", "  ..$   ",
		"        " };

	public static String[ ] plat14 = new String[ ]
	{	" .  M . ", " .xx. . ", " .mxx . ", "  mmm.x ", "  m.mx..", " .mxm. .", "..mxmm  ",
		"        " };

	public static String[ ] plat15 = new String[ ]
	{	"   M    ", " .  .. .", " .mmmm  ", " .mom.  ", " .mmo. .", " ...$.  ", " M      ",
		"  .   . " };

	public static String[ ] plat2 = new String[ ]
	{	"     . M", "  .o.. M", "...... M", ".  .   M", "   .....", "  mm..  ", "  .mm.  ",
		"   m    " };

	public static String[ ] plat3 = new String[ ]
	{	"   ...  ", "   ..   ", " MM..$  ", "    ....", "   .....", " .....  ", "....    ",
		" ...    " };

	public static String[ ] plat4 = new String[ ]
	{	"        ", "  xxx   ", "  xxx   ", "  x xxx ", "      xx", "  MMMMM ", " xxxx   ",
		"        " };

	public static String[ ] plat5 = new String[ ]
	{	"        ", "  xxx   ", "  xxx   ", "x xMM x ", "   MM   ", "  x x   ", "  xxx   ",
		"        " };

	public static String[ ] plat6 = new String[ ]
	{	"  ... M ", " .$...M ", ".....m..", "  ...m. ", "  ....  ", "  ..... ", "  m.....",
		" m....  " };

	public static String[ ] plat7 = new String[ ]
	{	" xxxxxx ", " xoxxoxM", " xxxxox ", " xoxxxx ", " xxxxox ", " xoxxxxM", " xxxxox ",
		" xxxxxx " };

	public static String[ ] plat8 = new String[ ]
	{	"  ..mm M", "  ...m  ", "  .... M", "........", ".mm..$..", "  m...  ", "  ...m  ",
		"  ..m.  " };

	public static String[ ] plat9 = new String[ ]
	{	".   M  .", " ..     ", " ..     ", "  m..m  ", "    ..  ", "   ...m ", "  ..  ..",
		"   M   ." };

	public final static MonsterSpawnInfo[ ] spawnInfo = new MonsterSpawnInfo[ ]
	{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 50 ),
		new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 80 ), };

	public static LevelFeature stairway, entrance, exit, platform, mid;

	public static String[ ][ ] stairway1 = new String[ ][ ]
	{
		{	"  #### ######  ", "  #>        #  ", "  # #######    ", "            #  ",
			"  ### #######  " },
		{

			"  # ### #####  ", "  #<#          ", "  # # ## ####  ", "               ",
			"  ###########  " } };

	public static String[ ][ ] stairway2 = new String[ ][ ]
	{
		{	"  222222222    ", "  22> ss222    ", "  222222222    ", "   2222222sss  ",
			"               " },
		{

			"   2222 22     ", "   2u22222ss   ", "   2222222     ", "    s          ",
			"    s          " } };

	public static String[ ][ ] stairway3 = new String[ ][ ]
	{
		{	"     222       ", "     2d2    >  ", "     22        ", "      s        ",
			"      s        " },
		{

			"      22       ", "     2u2    <  ", "     222       ", "               ",
			"               " } };

	public static String[ ][ ] stairway4 = new String[ ][ ]
	{
		{	"               ", "               ", "  o  > >     o ", "               ",
			"               " },
		{

			"               ", "               ", "  o  < <     o ", "               ",
			"               " } };

	public static String[ ][ ] stairway5 = new String[ ][ ]
	{
		{	"               ", " >             ", "           222 ", "           2d2 ",
			"           2 2 " },
		{

			"222            ", "2u2            ", "222            ", "            <  ",
			"               " } };
	static
	{
		baseFeature.addLayout( base );
	}
	static
	{
		defaultCharMap.put( "o", "MOSS_COLUMN" );
		defaultCharMap.put( ".", "MOSS_FLOOR" );
		defaultCharMap.put( "2", "MOSS_MIDFLOOR" );
		defaultCharMap.put( "#", "CASTLE_WALL" );
		defaultCharMap.put( "s", "MOSS_STAIRS" );
		defaultCharMap.put( "m", "MOSS_FLOOR" );
		defaultCharMap.put( "x", "RUSTY_PLATFORM" );
		defaultCharMap.put( "w", "MOSS_WATERWAY_ETH" );
		defaultCharMap.put( "-", "MOSS_WATERWAY" );
		defaultCharMap.put( "M", "MOSS_WATERWAY_ETH MONSTER MERMAN" );
		defaultCharMap.put( "1", "MOSS_WATERWAY MONSTER BONE_PILLAR" );
		defaultCharMap.put( "c", "MOSS_FLOOR FEATURE CANDLE 70" );
		defaultCharMap.put( "E", "MOSS_MIDFLOOR EOL MAGIC_DOOR COST 1" );
		defaultCharMap.put( "S", "MOSS_MIDFLOOR EXIT _BACK" );
		defaultCharMap.put( ">", "MOSS_FLOOR FEATURE STAIRDOWN 100" );
		defaultCharMap.put( "<", "MOSS_FLOOR FEATURE STAIRUP 100" );
		defaultCharMap.put( "*", "MOSS_WATERWAY FEATURE STAIRDOWN 100" );
		defaultCharMap.put( "&", "MOSS_WATERWAY FEATURE STAIRUP 100" );

		defaultCharMap.put( "d", "MOSS_MIDFLOOR FEATURE STAIRDOWN" );
		defaultCharMap.put( "u", "MOSS_MIDFLOOR FEATURE STAIRUP" );
		defaultCharMap.put( "$", "MOSS_FLOOR FEATURE TREASURE_SPAWNER 30" );
	}

	static
	{

		stairway = new LevelFeature( );
		stairway.addLayout( stairway1 );
		stairway.addLayout( stairway2 );
		stairway.addLayout( stairway3 );
		stairway.addLayout( stairway4 );
		stairway.addLayout( stairway5 );

		entrance = new LevelFeature( );
		entrance.addLayout( entrance1 );
		entrance.addLayout( entrance2 );
		entrance.addLayout( entrance3 );
		entrance.addLayout( entrance4 );
		entrance.addLayout( entrance5 );

		exit = new LevelFeature( );
		exit.addLayout( exit1 );
		exit.addLayout( exit2 );
		exit.addLayout( exit3 );

		platform = new LevelFeature( );
		platform.addLayout( plat1 );
		platform.addLayout( plat2 );
		platform.addLayout( plat3 );
		platform.addLayout( plat4 );
		platform.addLayout( plat5 );
		platform.addLayout( plat6 );
		platform.addLayout( plat7 );
		platform.addLayout( plat8 );
		platform.addLayout( plat9 );
		platform.addLayout( plat10 );
		platform.addLayout( plat11 );
		platform.addLayout( plat12 );
		platform.addLayout( plat13 );
		platform.addLayout( plat14 );
		platform.addLayout( plat15 );

		mid = new LevelFeature( );
		mid.addLayout( mid1 );
		mid.addLayout( mid2 );
		mid.addLayout( mid3 );
		mid.addLayout( mid4 );

	}

	public static void setup( PatternGenerator who )
	{
		who.setBaseFeature( Moat.baseFeature );
		who.assignFeature( Moat.stairway, new Position( 1, 1, 0 ) );
		who.assignFeature( Moat.stairway, new Position( 1, 18, 0 ) );
		who.assignFeature( Moat.stairway, new Position( 38, 1, 0 ) );
		who.assignFeature( Moat.stairway, new Position( 38, 18, 0 ) );
		who.assignFeature( Moat.entrance, new Position( 1, 8, 0 ) );
		who.assignFeature( Moat.exit, new Position( 39, 8, 0 ) );
		who.assignFeature( Moat.mid, new Position( 16, 1, 0 ) );
		who.assignFeature( Moat.mid, new Position( 16, 9, 0 ) );
		who.assignFeature( Moat.mid, new Position( 16, 17, 0 ) );
		who.assignFeature( Moat.platform, new Position( 16, 1, 1 ) );
		who.assignFeature( Moat.platform, new Position( 16, 9, 1 ) );
		who.assignFeature( Moat.platform, new Position( 16, 17, 1 ) );
		who.assignFeature( Moat.platform, new Position( 24, 1, 1 ) );
		who.assignFeature( Moat.platform, new Position( 24, 9, 1 ) );
		who.assignFeature( Moat.platform, new Position( 24, 17, 1 ) );
		who.assignFeature( Moat.platform, new Position( 32, 1, 1 ) );
		who.assignFeature( Moat.platform, new Position( 32, 9, 1 ) );
		who.assignFeature( Moat.platform, new Position( 32, 17, 1 ) );
		who.setCharMap( Moat.defaultCharMap );
	}
}