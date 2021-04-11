package co.castle.levelgen.patterns;

import java.util.Hashtable;

import co.castle.levelgen.LevelFeature;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.levelgen.PatternGenerator;
import sz.util.Position;

public class EntranceBig
{

	public static String[ ][ ] base = new String[ ][ ]
	{
		{	"###########################################################################",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.............c..................c.............c..............c...........#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#...........o.............o.............o..............o............o.....#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#...........c.............c.............c..............c..................#",
			"#-----------------------------.-----------------..------------------------#",
			"S---------------.--------------.------------------.------------------.-----",
			"#-------------..---------------------------------------------------..-----#",
			"#...........c.............c.............c..............c..................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#...........o.............o.............o..............o............o.....#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.............c..................c.............c..............c...........#",
			"#.........................................................................#",
			"#.........................................................................#",
			"#.........................................................................#",
			"###########################################################################" } };

	public static LevelFeature baseFeature = new LevelFeature( );

	public static String[ ] center1 = new String[ ]
	{	"   o       o            ", "   o                  o ", "   o        o           ",
		"   o                    ", "   o     o         o    " };

	public static String[ ] center2 = new String[ ]
	{	" o             o        ", "      o               o ", "                        ",
		"  o   o      o      o   ", "           o            " };

	public static String[ ] center3 = new String[ ]
	{	"         222222         ", "        22222222        ", "      sss222222sss      ",
		"        22222222        ", "         222222         " };
	public static String[ ] center4 = new String[ ]
	{	"     22222222222        ", "   222222222222222      ", "  sssss       sssss     ",
		"   222222222222222      ", "     22222222222        " };
	public static String[ ] center5 = new String[ ]
	{	"                        ", "  o o o o o o o o o o o ", "                        ",
		"  o o o o o o o o o o o ", "                        " };
	public static Hashtable defaultCharMap = new Hashtable( );
	public static LevelFeature down, up, end, center;

	public static String[ ] down1 = new String[ ]
	{	"                        ", "                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "o~    ~o~    ~o~    ~o~ ",
		"##====###====###====####" };

	public static String[ ] down2 = new String[ ]
	{	"                        ", "                        ", "    s            o      ",
		"    s                   ", "   2s2222222222         ",
		"   2222222222222222222  ", "   222222222222222222   ",
		"  2222222222222222222   ", "  222222222222222222sss ",
		"  2222222222222222222$  ", "  2o*2222*o*22222*o222  ",
		"########################" };

	public static String[ ] down3 = new String[ ]
	{	"                        ", "                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "o  o  o  o~  ~o~    ~o  ",
		"#==#==#==#######====####" };

	public static String[ ] down4 = new String[ ]
	{	"                        ", "                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "o o~ @ ~o~ @ ~o~ @ ~o o ",
		"########################" };

	public static String[ ] down5 = new String[ ]
	{	"                        ", "                        ", "                        ",
		"                  s     ", "   22222222222    s     ",
		"  s222222222222222s22   ", "   s22222222222222222   ",
		"  22s2222222222222222   ", " 22222222222222222222   ",
		" 22222222222222222222   ", " 22222#######2222#222   ",
		"########################" };

	public static String[ ] end1 = new String[ ]
	{	"                       #", "      2222     22222222#", "      222    2222222222#",
		"        2   $2222222222#", "       2222222222222222#",
		"     ssss22222222222222#", "     ssss22222222222222/",
		"     ssss22222222222222#", "       2222222222222222#",
		"        2   $2222222222#", "      222    2222222222#",
		"      2222     22222222#", "                       #" };

	public static String[ ] end2 = new String[ ]
	{	"                      $#", "      2222  s  22222222#", "      222   s2222222222#",
		"        2   s2222222222#", "       22222s2222222222#",
		"     222222222222222222#", "     222222222222222222/",
		"     222222222222222222#", "       22222s2222222222#",
		"        2   s2222222222#", "      222   s2222222222#",
		"      2222  s  22222222#", "                      $#" };

	public static String[ ] end3 = new String[ ]
	{	"                       #", "   ssss222     22222222#", "      222    2222222222#",
		"        2    222ss $222#", "       2222222222222222#",
		"         22222222222222#", "         22222222222222/",
		"         22222222222222#", "       2222222222222222#",
		"        2    222ss $222#", "      222    2222222222#",
		"   ssss222     22222222#", "                       #" };

	public static String[ ] end4 = new String[ ]
	{	"                      $#", "               22222222#", "             2222222222#",
		"             2222222222#", "       2222222222222222#",
		"     ssss22222222222222#", "     ssss22222222222222/",
		"     ssss22222222222222#", "       2222222222222222#",
		"             2222222222#", "             2222222222#",
		"               22222222#", "                      $#" };

	public final static MonsterSpawnInfo[ ] spawnInfo = new MonsterSpawnInfo[ ]
	{ new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 70 ), };

	public static String[ ] up1 = new String[ ]
	{	"##====###====###====####", "o~    ~o~    ~o~    ~o~ ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        " };

	public static String[ ] up2 = new String[ ]
	{	"########################", "  2o*2222*o*22222*o222  ", "  2222222222222222222   ",
		"  222222222222222222sss ", "  2222222222222222222   ",
		"   222222222222222222   ", "   2222222222222222222  ",
		"   2s2222222222         ", "    s                   ",
		"    s            o      ", "                        ", "                        "

	};

	public static String[ ] up3 = new String[ ]
	{	"#==#==#==#######====####", "o  o  o  o~  ~o~    ~o  ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        " };

	public static String[ ] up4 = new String[ ]
	{	"########################", "o o~ @ ~o~ @ ~o~ @ ~o o ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        " };

	public static String[ ] up5 = new String[ ]
	{	"########################", " 22222#######2222#222   ", " 22222222222222222222   ",
		" 22222222222222222222   ", "  22s2222222222222222   ",
		"   s22222222222222222   ", "  s222222222222222s22   ",
		"   22222222222    s     ", "                  s     ",
		"                        ", "                        ", "                        "

	};
	static
	{
		baseFeature.addLayout( base );
	}
	static
	{
		defaultCharMap.put( "o", "MARBLE_COLUMN" );
		defaultCharMap.put( ".", "MARBLE_FLOOR" );
		defaultCharMap.put( "s", "MARBLE_STAIRS" );
		defaultCharMap.put( "2", "MARBLE_MIDFLOOR" );
		defaultCharMap.put( "/", "MARBLE_MIDFLOOR EOL MAGIC_DOOR" );
		defaultCharMap.put( "S", "RED_CARPET EXIT _BACK" );
		defaultCharMap.put( "#", "CASTLE_WALL" );
		defaultCharMap.put( "=", "BIG_WINDOW" );
		defaultCharMap.put( "~", "MARBLE_FLOOR FEATURE RED_CURTAIN" );
		defaultCharMap.put( "*", "MARBLE_MIDFLOOR FEATURE RED_CURTAIN" );
		defaultCharMap.put( "@", "GODNESS_STATUE" );
		defaultCharMap.put( "c", "MARBLE_FLOOR FEATURE CANDLE 70" );
		defaultCharMap.put( "D", "CASTLE_DOOR" );
		defaultCharMap.put( "-", "RED_CARPET" );
		defaultCharMap.put( "$", "NOTHING FEATURE TREASURE_SPAWNER 50" );
	}

	static
	{
		// 1,19 25,19 49,19
		down = new LevelFeature( );
		down.addLayout( down1 );
		down.addLayout( down2 );
		down.addLayout( down3 );
		down.addLayout( down4 );
		down.addLayout( down5 );

		up = new LevelFeature( );
		up.addLayout( up1 );
		up.addLayout( up2 );
		up.addLayout( up3 );
		up.addLayout( up4 );
		up.addLayout( up5 );

		end = new LevelFeature( );
		end.addLayout( end1 );
		end.addLayout( end2 );
		end.addLayout( end3 );
		end.addLayout( end4 );

	}

	public static void setup( PatternGenerator who )
	{
		who.setBaseFeature( EntranceBig.baseFeature );
		who.assignFeature( EntranceBig.down, new Position( 1, 19 ) );
		who.assignFeature( EntranceBig.down, new Position( 25, 19 ) );
		who.assignFeature( EntranceBig.down, new Position( 49, 19 ) );

		who.assignFeature( EntranceBig.up, new Position( 1, 0 ) );
		who.assignFeature( EntranceBig.up, new Position( 25, 0 ) );
		who.assignFeature( EntranceBig.up, new Position( 49, 0 ) );

		who.assignFeature( EntranceBig.center, new Position( 1, 13 ) );
		who.assignFeature( EntranceBig.center, new Position( 25, 13 ) );

		who.assignFeature( EntranceBig.end, new Position( 51, 9 ) );
		who.setCharMap( EntranceBig.defaultCharMap );
	}
}