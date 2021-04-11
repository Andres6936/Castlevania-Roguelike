package co.castle.levelgen.patterns;

import java.util.Hashtable;

import co.castle.levelgen.MonsterSpawnInfo;
import sz.util.Position;

public class Town
{
	public final static String[ ][ ] cellMap = new String[ ][ ]
	{
		{	"tttt~ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",
			"ttt~~tt.........ttt.......................9..........tt...........tttt",
			"tt~~~d.tt.......++++++++++++++++++++++++...................a........tt",
			"t.~~....e...5...+:::::::::.++....e......++++++++++++++++......d....ttt",
			"t..~~.........6.+#######::+############:::++#######/####+.....dd..t..t",
			"t~~~.....dd.....+#xxxxx#::+#xxxxxxxxxx##=#+.#xxxxxxxxxx#+..4ee..d...tt",
			"tt~d...dd.......+###/###:#+#xxxxxxxxxx##=#++############+............t",
			"tt~d........+++++++++++#=#+#######/####++++++++++++++++++............t",
			"t~~......1...+::::::::++++++++++++++++++..+############.+++++++.....tt",
			"t.~~...S....+=:###s########+..............+#vvvvvvvvv##+++++++++....tt",
			"tdd~~.......+:#::::::#xxxx#+......2...t...+#vvv===::vv#+++++........tt",
			"t.dd~~......+:#::::::#xDxx/+...t..~~......+#vvvvvv:p:v#++++++........t",
			"tt.~~~~~~~..+:#::::::#xxxx#+7....~~....8..+/vvvvvv:::v#+++..+++++++++E",
			"t..~~.....~.+:#############+......~......d+#vvv===::vv#.+++++.......tt",
			"t..~~~....~.+:::::::::::::++......t.....d.+#vvvvvvvvv##.+++++........t",
			"t...~~...~+++++++++++++++++++++++.....dd..+############.+++..........t",
			"t..dd~~.~~...+:::::::::::==++++++++++++++++++++++++++++++...........tt",
			"t...d~~~~....+#s##########+::::#######=#::+.########+.+............ttt",
			"tt..~~.d.....+#:::#xxxxxx#+::::#xxxxx#=#::+.#xxxxxx###+....e.....d..tt",
			"tt.~~~d......+#:::#xxxxxx#+::::#xxBxx#=#::+.#xxxxxxxx#+..........d..tt",
			"t..~~d.......+#####xxxAxx#+::#=#xxxxx#::::+.#xxxxCxxx/....3.....d....t",
			"t...~~d......+/xxx#xxxxxx#+::#=#xxxxx#::::.+#xxxxxxxx#+..........d...t",
			"t...~~~......+##xx#xxxxx##+::#=##/##/#::::.+####xxxxx#+.....b........t",
			"t..d~~.......++#####/####++++++++++++++++++++++#######+.............tt",
			"t..d.~........++++++++++++...ee...............++++++++++.......c.....t",
			"t.tt~~.t............dd................t....e.........t......t.......tt",
			"tt~~~~.ttt...t...dddt..ttt.......tt..ttt...tt......tttt....ttt..tttttt",
			"ttt~~~tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" } };

	public final static Hashtable defaultCharMap = new Hashtable( );
	public final static Position endPoint = new Position( 69, 11 );

	public final static MonsterSpawnInfo[ ] spawnInfo = new MonsterSpawnInfo[ ]
	{ new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 1 ), };

	public final static Position startPoint = new Position( 12, 14 );
	// public final static Position startPoint = new Position(69,12);
	// public final static Position playerPosition = new Position(50,11);
	static
	{
		defaultCharMap.put( ".", "TOWN_GRASS" );
		defaultCharMap.put( "S", "TOWN_GRASS SOL" );
		defaultCharMap.put( "E", "BRICK_WALKWAY EOLNF" );
		defaultCharMap.put( "t", "DARK_FOREST" );
		defaultCharMap.put( "~", "WATERWAY" );
		defaultCharMap.put( "d", "DIRT" );
		defaultCharMap.put( "+", "BRICK_WALKWAY" );
		defaultCharMap.put( "#", "TOWN_WALL" );
		defaultCharMap.put( "x", "HOUSE_FLOOR" );
		defaultCharMap.put( ":", "MIDWALKWAY" );
		defaultCharMap.put( "=", "STAIRS" );
		defaultCharMap.put( "v", "CHURCH_FLOOR" );
		defaultCharMap.put( "/", "HOUSE_FLOOR FEATURE MAGIC_DOOR 100 COST 0" );
		defaultCharMap.put( "s", "MIDWALKWAY FEATURE MAGIC_DOOR 100 COST 0" );

		defaultCharMap.put( "A", "HOUSE_FLOOR MERCHANT 1" );
		defaultCharMap.put( "B", "HOUSE_FLOOR MERCHANT 2" );
		defaultCharMap.put( "C", "HOUSE_FLOOR MERCHANT 3" );
		defaultCharMap.put( "D", "HOUSE_FLOOR MERCHANT 4" );
		defaultCharMap.put( "1", "TOWN_GRASS NPC MAN0" );
		defaultCharMap.put( "2", "TOWN_GRASS NPC MAN1" );
		defaultCharMap.put( "3", "TOWN_GRASS NPC MAN2" );
		defaultCharMap.put( "4", "TOWN_GRASS NPC WOMAN0" );
		defaultCharMap.put( "5", "TOWN_GRASS NPC WOMAN1" );
		defaultCharMap.put( "6", "TOWN_GRASS NPC WOMAN2" );
		defaultCharMap.put( "7", "TOWN_GRASS NPC OLDMAN0" );
		defaultCharMap.put( "8", "TOWN_GRASS NPC OLDMAN1" );
		defaultCharMap.put( "9", "TOWN_GRASS NPC OLDMAN2" );
		defaultCharMap.put( "a", "TOWN_GRASS NPC OLDWOMAN0" );
		defaultCharMap.put( "b", "TOWN_GRASS NPC OLDWOMAN1" );
		defaultCharMap.put( "c", "TOWN_GRASS NPC OLDWOMAN2" );
		defaultCharMap.put( "p", "MIDWALKWAY NPC PRIEST" );
		defaultCharMap.put( "e", "TOWN_GRASS NPC DOG" );
	}

}