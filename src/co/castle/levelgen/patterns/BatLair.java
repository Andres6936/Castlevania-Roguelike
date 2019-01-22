package co.castle.levelgen.patterns;

import java.util.Hashtable;

import co.castle.levelgen.LevelFeature;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.levelgen.PatternGenerator;
import sz.util.Position;

public class BatLair
{

	public static String[ ] base = new String[ ]
	{	"##############################################,,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
		"#.........................................#####,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
		"#.............................................#####,,,,,,,,,,,,,,,,,,,,,,,,",
		"#.................................................##,,,,,,,,,,,,,,,,,,,,,,,",
		"#.............c..................c.............c...########################",
		"#..........................................................o........o....o#",
		"#.................................................22o2....................#",
		"#.................................................22222.-----------.......#",
		"#.....c...........................................2222222-x----------.....#",
		"#...........o.............o.............o.....o...2222----..........--....#",
		"#.................................................-2-.---------------...%2#",
		"#...............................................---2......x...------x-..%2#",
		"#...................................o.....---------2--------......----2.22#",
		"#...........c.............c..............................------------22222#",
		"#-----------------------------.-----------------..--------222--x--x--22222#",
		"S---------------.--------------.------------------.x--x--x222-----x--22222E",
		"#-------------..------------------------------------------222--x--x..22222#",
		"#...........c.............c.................----..22.-...------------22222#",
		"#...................................o.........----22..----------------2.22#",
		"#..................................................2.......-x-----..x...%2#",
		"#.................................................-%------------------..%2#",
		"#...........o.............o.............o.....o....%...-------------......#",
		"#.........................................................................#",
		"#.........................................................................#",
		"#...................................................o.....................#",
		"#..........................................................o........o....o#",
		"#.............c..................c.............c...########################",
		"#.................................................##,,,,,,,,,,,,,,,,,,,,,,,",
		"#.............................................#####,,,,,,,,,,,,,,,,,,,,,,,,",
		"#.........................................#####,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
		"##############################################,,,,,,,,,,,,,,,,,,,,,,,,,,,,,", };
	public static LevelFeature baseFeature = new LevelFeature( );

	public final static String boss = "GIANTBAT";

	public final static Position bossPosition = new Position( 60, 15 );

	public static Hashtable defaultCharMap = new Hashtable( );

	public static LevelFeature down, up;

	public static String[ ] down1 = new String[ ]
	{	"                        ", "                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "o~    ~o~   $~o~    ~o~ ",
		"##====###====###====####" };

	public static String[ ] down2 = new String[ ]
	{	"                        ", "                        ", "    s            o      ",
		"    s                   ", "   2s2222222222         ",
		"   2222222222222222222  ", "   222222222222222222   ",
		"  2222222222222222222   ", "  222222222222222222sss ",
		"  2222222222222222222   ", "  2o*2222*o*22222*o222  ",
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
		" 22222222222222222222   ", " 22222#######2222#222 $ ",
		"########################" };

	public final static Position end = new Position( 73, 15 );

	public final static MonsterSpawnInfo[ ] spawnInfo = new MonsterSpawnInfo[ ]
	{ new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 1 ), };

	// public final static Position playerPosition = new Position(1,15);
	public final static Position start = new Position( 0, 15 );

	public static String[ ] up1 = new String[ ]
	{	"##====###====###====####", "o~$   ~o~    ~o~    ~o~ ", "                        ",
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
	{	"#==#==#==#######====####", "o  o$ o  o~  ~o~    ~o  ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        ", "                        ",
		"                        " };

	public static String[ ] up4 = new String[ ]
	{	"########################", "o o~ @ ~o~ @ ~o~ @$~o o ", "                        ",
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
		defaultCharMap.put( ",", "VOID" );
		defaultCharMap.put( "%", "MARBLE_STAIRS" );
		defaultCharMap.put( "s", "MARBLE_STAIRS" );
		defaultCharMap.put( "2", "MARBLE_MIDFLOOR" );
		defaultCharMap.put( "]", "CASTLE_DOOR" );
		defaultCharMap.put( "#", "CASTLE_WALL" );
		defaultCharMap.put( "=", "BIG_WINDOW" );
		defaultCharMap.put( "~", "MARBLE_FLOOR FEATURE RED_CURTAIN" );
		defaultCharMap.put( "*", "MARBLE_MIDFLOOR FEATURE RED_CURTAIN" );
		defaultCharMap.put( "E", "MARBLE_MIDFLOOR EOL MAGIC_DOOR COST 1" );
		defaultCharMap.put( "S", "RED_CARPET EXIT _BACK" );
		defaultCharMap.put( "$", "NOTHING FEATURE TREASURE_SPAWNER 30" );

		defaultCharMap.put( "x", "GRAY_COLUMN" );
		defaultCharMap.put( "@", "GODNESS_STATUE" );
		defaultCharMap.put( "c", "MARBLE_FLOOR FEATURE CANDLE 70" );
		defaultCharMap.put( "-", "RED_CARPET" );

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

	}

	public static void setup( PatternGenerator who )
	{
		who.setBaseFeature( baseFeature );
		who.assignFeature( down, new Position( 1, 19 ) );
		who.assignFeature( up, new Position( 1, 0 ) );
		who.setCharMap( defaultCharMap );
		who.setHasBoss( true );
	}
}