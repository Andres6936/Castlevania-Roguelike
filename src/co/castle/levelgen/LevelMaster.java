package co.castle.levelgen;

import java.util.ArrayList;

import co.castle.cuts.CaveEntranceSeal;
import co.castle.cuts.Unleasher;
import co.castle.feature.Feature;
import co.castle.feature.FeatureFactory;
import co.castle.game.CRLException;
import co.castle.item.Item;
import co.castle.item.ItemFactory;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.RespawnAI;
import co.castle.level.Respawner;
import co.castle.levelgen.cave.BeginningLevelGenerator;
import co.castle.levelgen.cave.ForestLevelGenerator;
import co.castle.levelgen.cave.LavaCaveLevelGenerator;
import co.castle.levelgen.featureCarve.CircularRoom;
import co.castle.levelgen.featureCarve.ColumnsRoom;
import co.castle.levelgen.featureCarve.FeatureCarveGenerator;
import co.castle.levelgen.featureCarve.RingRoom;
import co.castle.levelgen.featureCarve.RoomFeature;
import co.castle.levelgen.patterns.BadBelmontLair;
import co.castle.levelgen.patterns.BatLair;
import co.castle.levelgen.patterns.BigTown;
import co.castle.levelgen.patterns.CastleBridge;
import co.castle.levelgen.patterns.CastleCenter;
import co.castle.levelgen.patterns.CatacombsPrize;
import co.castle.levelgen.patterns.CaveFork;
import co.castle.levelgen.patterns.ClaraMeeting;
import co.castle.levelgen.patterns.Courtyard;
import co.castle.levelgen.patterns.DeathHall;
import co.castle.levelgen.patterns.DeepFork;
import co.castle.levelgen.patterns.DimensionalVoid;
import co.castle.levelgen.patterns.Entrance;
import co.castle.levelgen.patterns.FrankLair;
import co.castle.levelgen.patterns.Garden;
import co.castle.levelgen.patterns.Keep;
import co.castle.levelgen.patterns.LegionLair;
import co.castle.levelgen.patterns.MedusaLair;
import co.castle.levelgen.patterns.Moat;
import co.castle.levelgen.patterns.MummiesLair;
import co.castle.levelgen.patterns.PreludeArena;
import co.castle.levelgen.patterns.PrologueKeep;
import co.castle.levelgen.patterns.QuartersFork;
import co.castle.levelgen.patterns.QuartersPrize;
import co.castle.levelgen.patterns.ReservoirPrize;
import co.castle.levelgen.patterns.ReservoirTelepad;
import co.castle.levelgen.patterns.RoyalChapel;
import co.castle.levelgen.patterns.SewersBottom;
import co.castle.levelgen.patterns.SewersEntrance;
import co.castle.levelgen.patterns.StaticPattern;
import co.castle.levelgen.patterns.Telepad;
import co.castle.levelgen.patterns.Tower;
import co.castle.levelgen.patterns.TowerTop;
import co.castle.levelgen.patterns.TrainingArea;
import co.castle.levelgen.patterns.Villa;
import co.castle.levelgen.patterns.VindelithMeeting;
import co.castle.levelgen.patterns.WaterDragonLair;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import co.castle.npc.Hostage;
import co.castle.npc.NPCFactory;
import co.castle.player.Player;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public class LevelMaster
{
	// private static Dispatcher currentDispatcher;
	private static boolean firstCave = true;

	public static Level createLevel(	LevelMetaData metadata,
										Player player ) throws CRLException
	{

		String levelID = metadata.getLevelID( );
		Debug.enterStaticMethod( "LevelMaster", "createLevel" );
		Debug.say( "levelID " + levelID );
		boolean overrideLevelNumber = false;
		Level ret = null;
		PatternGenerator.getGenerator( ).resetFeatures( );
		Respawner x = new Respawner( 5, 90 );
		x.setSelector( new RespawnAI( ) );
		boolean hasHostage = false;
		boolean isStatic = false;
		StaticPattern pattern = null;
		if ( levelID.startsWith( "TOWN" ) )
		{
			isStatic = true;
			pattern = new BigTown( );
		}
		else if ( levelID.startsWith( "CASTLE_BRIDGE" ) )
		{
			isStatic = true;
			pattern = new CastleBridge( );
		}
		else if ( levelID.startsWith( "MEDUSA_LAIR" ) )
		{
			isStatic = true;
			pattern = new MedusaLair( );
		}
		else if ( levelID.startsWith( "FRANK_LAIR" ) )
		{
			isStatic = true;
			pattern = new FrankLair( );
		}
		else if ( levelID.startsWith( "TOWER_TOP" ) )
		{
			isStatic = true;
			pattern = new TowerTop( );
		}
		else if ( levelID.startsWith( "KEEP" ) )
		{
			isStatic = true;
			pattern = new Keep( );
		}
		else if ( levelID.startsWith( "VOID" ) )
		{
			isStatic = true;
			pattern = new DimensionalVoid( );
		}
		else if ( levelID.startsWith( "PROLOGUE_KEEP" ) )
		{
			isStatic = true;
			pattern = new PrologueKeep( );
		}
		else if ( levelID.startsWith( "CHAPEL" ) )
		{
			isStatic = true;
			pattern = new RoyalChapel( );
			hasHostage = true;
		}
		else if ( levelID.startsWith( "GARDEN" ) )
		{
			isStatic = true;
			pattern = new Garden( );
		}
		else if ( levelID.startsWith( "MUMMIES_LAIR" ) )
		{
			isStatic = true;
			pattern = new MummiesLair( );
		}
		else if ( levelID.startsWith( "COURTYARD" ) )
		{
			isStatic = true;
			pattern = new Courtyard( );
		}
		else if ( levelID.startsWith( "TOWER" ) )
		{
			isStatic = true;
			pattern = new Tower( );
		}
		else if ( levelID.startsWith( "VILLA" ) )
		{
			isStatic = true;
			pattern = new Villa( );
		}
		else if ( levelID.startsWith( "TRAINING" ) )
		{
			isStatic = true;
			pattern = new TrainingArea( );
		}
		else if ( levelID.startsWith( "PRELUDE_ARENA" ) )
		{
			isStatic = true;
			pattern = new PreludeArena( );
		}
		else if ( levelID.startsWith( "TELEPAD" ) )
		{
			isStatic = true;
			pattern = new Telepad( );
		}
		else if ( levelID.startsWith( "DEATH_HALL" ) )
		{
			isStatic = true;
			pattern = new DeathHall( );
		}
		else if ( levelID.startsWith( "LEGION_LAIR" ) )
		{
			isStatic = true;
			pattern = new LegionLair( );
		}
		else if ( levelID.startsWith( "CAVE_FORK" ) )
		{
			isStatic = true;
			pattern = new CaveFork( );
		}
		else if ( levelID.startsWith( "DEEP_FORK" ) )
		{
			isStatic = true;
			pattern = new DeepFork( );
		}
		else if ( levelID.startsWith( "WATER_DRAGON_LAIR" ) )
		{
			isStatic = true;
			pattern = new WaterDragonLair( );
		}
		else if ( levelID.startsWith( "DINING_HALL" ) )
		{
			isStatic = true;
			pattern = new CastleCenter( );
		}
		else if ( levelID.startsWith( "QUARTERS_FORK" ) )
		{
			isStatic = true;
			pattern = new QuartersFork( );
		}
		else if ( levelID.startsWith( "QUARTERS_PRIZE" ) )
		{
			isStatic = true;
			pattern = new QuartersPrize( );
		}
		else if ( levelID.startsWith( "PRIZE_CATACOMBS" ) )
		{
			isStatic = true;
			pattern = new CatacombsPrize( );
		}
		else if ( levelID.startsWith( "PRIZE_RESERVOIR" ) )
		{
			isStatic = true;
			pattern = new ReservoirPrize( );
		}
		else if ( levelID.startsWith( "SPECIAL_RESERVOIR_TELEPAD" ) )
		{
			isStatic = true;
			pattern = new ReservoirTelepad( );
		}
		else if ( levelID.startsWith( "VINDELITH_MEETING" ) )
		{
			isStatic = true;
			pattern = new VindelithMeeting( );
		}
		else if ( levelID.startsWith( "CLARA_MEETING" ) )
		{
			isStatic = true;
			pattern = new ClaraMeeting( );
		}
		else if ( levelID.startsWith( "BADBELMONT" ) )
		{
			isStatic = true;
			pattern = new BadBelmontLair( );
		}
		else if ( levelID.startsWith( "SPECIAL_SEWERS_ENTRANCE" ) )
		{
			isStatic = true;
			pattern = new SewersEntrance( );
		}
		else if ( levelID.startsWith( "PRIZE_SEWERS" ) )
		{
			isStatic = true;
			pattern = new SewersBottom( );
		}

		if ( isStatic )
		{
			pattern.setup( StaticGenerator.getGenerator( ) );
			ret = StaticGenerator.getGenerator( ).createLevel( );
			ret.setRutinary( pattern.isRutinary( ) );
			if ( pattern.isHaunted( ) )
			{
				ret.setHaunted( true );
				ret.setNightRespawner( x );
				ret.savePop( );
				ret.getMonsters( ).removeAll( );
				ret.getDispatcher( ).removeAll( );

			}
			ret.setRespawner( x );
			ret.setInhabitants( pattern.getSpawnInfo( ) );
			ret.setDwellersInfo( pattern.getDwellers( ) );
			ret.setDescription( pattern.getDescription( ) );
			ret.setHostageSafe( pattern.isHostageSafe( ) );
			ret.setMusicKeyMorning( pattern.getMusicKeyMorning( ) );
			ret.setMusicKeyNoon( pattern.getMusicKeyNoon( ) );

			if ( pattern.getBoss( ) != null )
			{
				Monster monsBoss = MonsterFactory.getFactory( )
						.buildMonster( pattern.getBoss( ) );
				monsBoss.setPosition( pattern.getBossPosition( ) );
				ret.setBoss( monsBoss );
			}
			if ( pattern.getUnleashers( ) != null )
			{
				ret.setUnleashers( pattern.getUnleashers( ) );
			}
			ret.setMapLocationKey( pattern.getMapKey( ) );
		}

		if ( levelID.startsWith( "PRELUDE_ARENA" ) )
		{
			x = new Respawner( 2, 100 );
			x.setSelector( new RespawnAI( ) );
			ret.setRespawner( x );
			overrideLevelNumber = true;
			ret.setLevelNumber( 1 );
		}
		if ( levelID.startsWith( "TOWN" ) )
		{
			x = new Respawner( 2, 100 );
			x.setSelector( new RespawnAI( ) );
			ret.setRespawner( x );
		}

		if ( levelID.startsWith( "CHARRIOT_W" ) )
		{
			BeginningLevelGenerator clg = new BeginningLevelGenerator( );
			clg.init( "FOREST_TREE", "FOREST_GRASS", "FOREST_DIRT" );
			ret = clg.generateLevel( Util.rand( 40, 50 ), Util.rand( 50, 70 ), false );
			// ret.setDispatcher(new Dispatcher());
			ret.setRespawner( x );
			ret.setDescription( "Dark Forest" );
			ret.setMusicKeyMorning( "" );
			ret.setMusicKeyNoon( null );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "WARG", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "PANTHER", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 100 ),
				new MonsterSpawnInfo( "R_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						70 ), } );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 100 ),
				new MonsterSpawnInfo( "R_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						40 ) } );
			ret.setLevelNumber( 0 );
			overrideLevelNumber = true;
			ret.setMapLocationKey( "FOREST" );
			ret.setID( "CHARRIOT_W" );
			// ret.setIsCandled(true);
			ret.setRutinary( false );

		}
		else if ( levelID.startsWith( "FOREST" ) )
		{
			ForestLevelGenerator clg = new ForestLevelGenerator( );
			clg.init( "FOREST_TREE", "FOREST_GRASS", "FOREST_DIRT" );
			ret = clg.generateLevel( Util.rand( 50, 60 ), Util.rand( 50, 60 ), false );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Dark Forest" );
			ret.setMusicKeyMorning( "DAY_TRANSYLVANIA" );
			ret.setMusicKeyNoon( "NIGHT_TRANSYLVANIA" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "WARG", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "PANTHER", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 100 ),
				new MonsterSpawnInfo( "R_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						70 ), } );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 100 ),
				new MonsterSpawnInfo( "R_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						60 ) } );
			ret.setLevelNumber( 1 );
			overrideLevelNumber = true;
			ret.setMapLocationKey( "FOREST" );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "MAIN_HALL" ) )
		{
			Entrance.setup( PatternGenerator.getGenerator( ) );
			ret = PatternGenerator.getGenerator( ).createLevel( );
			//// ret.setDispatcher(new Dispatcher());
			ret.setInhabitants( Entrance.spawnInfo );
			ret.setRespawner( x );
			ret.setDescription( "Marble Hall" );
			ret.setMusicKeyMorning( "HALLS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "WARG", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "PANTHER", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "WHITE_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						80 ),
				new MonsterSpawnInfo( "APE_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						20 ), } );
			ret.setMapLocationKey( "HALL" );
			hasHostage = Util.chance( 10 );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "MOAT" ) )
		{
			Moat.setup( PatternGenerator.getGenerator( ) );
			ret = PatternGenerator.getGenerator( ).createLevel( );
			// ret.setDispatcher(new Dispatcher());
			ret.setInhabitants( Moat.spawnInfo );
			ret.setRespawner( x );
			ret.setDescription( "Moat" );
			ret.setMusicKeyMorning( "HALLS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BONE_PILLAR", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.UNDERGROUND, 80 ), } );
			ret.setMapLocationKey( "HALL" );
			hasHostage = Util.chance( 40 );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "BAT_HALL" ) )
		{
			PatternGenerator.getGenerator( ).resetFeatures( );
			BatLair.setup( PatternGenerator.getGenerator( ) );
			ret = PatternGenerator.getGenerator( ).createLevel( );
			// ret.setDispatcher(new Dispatcher());
			ret.setInhabitants( BatLair.spawnInfo );
			ret.setRespawner( x );
			Monster monsBoss = MonsterFactory.getFactory( ).buildMonster( BatLair.boss );
			monsBoss.setPosition( BatLair.bossPosition );
			ret.setBoss( monsBoss );
			ret.setDescription( "Giant Bat Lair" );
			ret.setMusicKeyMorning( "BOSS1" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BONE_PILLAR", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "PANTHER", MonsterSpawnInfo.UNDERGROUND, 50 ),
				new MonsterSpawnInfo( "SPEAR_KNIGHT", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 90 ), } );

			ret.setMapLocationKey( "HALL" );
		}
		else if ( levelID.startsWith( "LAB" ) )
		{
			GirdLevelGenerator glg = new GirdLevelGenerator( );
			glg.init( "RED_WALL", "RED_FLOOR" );
			glg.setCandles( 30 );
			ret = glg.generateLevel( new Position( 10, 6 ), 3, 2, 5, 5, true );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 80 ),
				new MonsterSpawnInfo( "MEDUSA_HEAD", MonsterSpawnInfo.BORDER, 80 ), } );
			if ( Util.chance( 10 ) )
				glg.placeClue( ret, 2 );
			ret.setDescription( "Castle Lab" );
			ret.setMusicKeyMorning( "LAB" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BLACK_KNIGHT", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "PARANTHROPUS", MonsterSpawnInfo.UNDERGROUND, 70 ),
				new MonsterSpawnInfo( "WEREBEAR", MonsterSpawnInfo.UNDERGROUND, 50 ),
				new MonsterSpawnInfo( "BONE_PILLAR", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "AXE_KNIGHT", MonsterSpawnInfo.UNDERGROUND,
						80 ), } );
			ret.setMapLocationKey( "LAB" );
			hasHostage = Util.chance( 30 );
			// ret.setIsCandled(true);
			lightCandles( ret );
		}
		else if ( levelID.startsWith( "RUINS" ) )
		{
			RuinLevelGenerator rlg = new RuinLevelGenerator( );
			rlg.init( "RUINS_WALL", "RUINS_FLOOR", "RUINS_DOOR" );
			ret = rlg.generateLevel( Util.rand( 40, 50 ), Util.rand( 40, 50 ),
					Util.rand( 10, 30 ) );
			// ret.setInhabitants(Ruins.getInhabitants(0));
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			if ( Util.chance( 15 ) )
				rlg.placeClue( ret, 2 );
			ret.setDescription( "Castle Ruins" );
			ret.setMusicKeyMorning( "RUINS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BLADE_SOLDIER", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "BONE_HALBERD", MonsterSpawnInfo.UNDERGROUND, 50 ),
				new MonsterSpawnInfo( "LIZARD_SWORDSMAN", MonsterSpawnInfo.UNDERGROUND,
						80 ),
				new MonsterSpawnInfo( "COCKATRICE", MonsterSpawnInfo.UNDERGROUND,
						20 ) } );

			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "SKELETON_PANTHER", MonsterSpawnInfo.UNDERGROUND,
					80 ) } );
			ret.setMapLocationKey( "RUINS" );
			hasHostage = Util.chance( 40 );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "CAVES" ) )
		{

			LavaCaveLevelGenerator clg = new LavaCaveLevelGenerator( );
			clg.init( "CAVE_WALL", "CAVE_FLOOR", "CAVE_WATER" );
			ret = clg.generateLevel( Util.rand( 40, 50 ), Util.rand( 40, 50 ), true,
					false );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "KILLER_PLANT", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 80 ),
				new MonsterSpawnInfo( "MUD_MAN", MonsterSpawnInfo.UNDERGROUND, 80 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			if ( Util.chance( 10 ) )
				clg.placeClue( ret, 3 );
			ret.setDescription( "Underground Caverns" );
			ret.setMusicKeyMorning( "CAVES" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "BLOOD_SKELETON", MonsterSpawnInfo.UNDERGROUND, 50 ),
				new MonsterSpawnInfo( "COOPER_ARMOR", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "DEATH_MANTIS", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "BEAST_DEMON", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "GOLEM", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "KILLER_PLANT", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 90 ) } );
			ret.setMapLocationKey( "CAVES" );
			if ( firstCave )
			{
				ret.setUnleashers( new Unleasher[ ]
				{ new CaveEntranceSeal( ) } );
				firstCave = false;
			}
			hasHostage = Util.chance( 60 );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "DRAGON_KING_LAIR" ) )
		{
			LavaCaveLevelGenerator clg = new LavaCaveLevelGenerator( );
			clg.init( "CAVE_WALL", "CAVE_FLOOR", "CAVE_WATER" );
			ret = clg.generateLevel( 30, 30, false, false );
			Feature door = FeatureFactory.getFactory( ).buildFeature( "MAGIC_DOOR" );
			Position exit = ret.getExitFor( "_NEXT" );
			door.setPosition( exit.x, exit.y, exit.z );
			door.setKeyCost( 1 );
			ret.addFeature( door );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 80 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			Monster monsBoss = MonsterFactory.getFactory( ).buildMonster( "DRAGON_KING" );
			monsBoss.setPosition( new Position( exit ) );
			ret.setBoss( monsBoss );
			ret.setDescription( "Dragon King Lair" );
			ret.setMusicKeyMorning( "BOSS2" );
			ret.setMapLocationKey( "CAVES" );
		}
		else if ( levelID.startsWith( "WAREHOUSE" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getWareHouseRooms( );
			fcg.initialize( rooms, "WAREHOUSE_WALL", Util.rand( 80, 100 ),
					Util.rand( 80, 100 ), "WAREHOUSE_FLOOR", "DUNGEON_UP",
					"DUNGEON_DOWN" );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "VAMPIRE_BAT", MonsterSpawnInfo.BORDER, 80 ), } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Warehouse" );
			ret.setMusicKeyMorning( "WAREHOUSE" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "ZELDO", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "GARGOYLE", MonsterSpawnInfo.UNDERGROUND, 70 ),
				new MonsterSpawnInfo( "SPEAR_SKELETON", MonsterSpawnInfo.UNDERGROUND,
						70 ),
				new MonsterSpawnInfo( "VAMPIRE_BAT", MonsterSpawnInfo.UNDERGROUND,
						50 ), } );
			ret.setMapLocationKey( "WAREHOUSE" );
			// ret.setIsCandled(true);
			// lightCandles(ret);
			ret.populate( );
			ret.setRutinary( true );
			if ( levelID.equals( "WAREHOUSEX0" ) )
			{
				Position p = ret.getExitFor( "_BACK" );
				ret.removeExit( "_BACK" );
				ret.addExit( p, "CAVE_FORK0" );
			}
			/*
			 * int xsize = Util.rand(15,17); int ysize = Util.rand(15,17); int entrance =
			 * Util.rand(2,xsize-2); int exit = Util.rand(2,xsize-2); int roomSize = 5;
			 * GirdLevelGenerator glg = new GirdLevelGenerator();
			 * glg.init("WAREHOUSE_WALL", "WAREHOUSE_FLOOR"); glg.setCandles(30); ret =
			 * glg.generateLevel(new Position(xsize,ysize), entrance, exit, roomSize,
			 * roomSize, false); ret.setDispatcher(new Dispatcher()); ret.setRespawner(x);
			 * ret.setInhabitants(new MonsterSpawnInfo[]{ new
			 * MonsterSpawnInfo("VAMPIRE_BAT", MonsterSpawnInfo.BORDER, 80), });
			 * ret.setDescription("Warehouse"); ret.setMusicKeyMorning("WAREHOUSE");
			 * ret.setDwellersInfo(new String[]{"ZELDO", "GARGOYLE", "SPEAR_SKELETON",
			 * "VAMPIRE_BAT"}); ret.setMapLocationKey("WAREHOUSE"); if
			 * (levelID.equals("WAREHOUSEX0")){ Position p = ret.getExitFor("_BACK");
			 * ret.removeExit("_BACK"); ret.addExit(p, "CAVE_FORK0"); }
			 * ret.setIsCandled(true);
			 */
		}
		else if ( levelID.startsWith( "CATACOMBS" ) )
		{
			LavaCaveLevelGenerator clg = new LavaCaveLevelGenerator( );
			clg.init( "CAVE_WALL", "CAVE_FLOOR", "LAVA" );
			ret = clg.generateLevel( Util.rand( 40, 50 ), Util.rand( 40, 50 ), true,
					false );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "HEAT_SHADE", MonsterSpawnInfo.UNDERGROUND, 80 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Catacombs" );
			ret.setMusicKeyMorning( "CATACOMBS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "MUMMY_MAN", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "ARACHNE", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "BONE_ARK", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "DEMON_LORD", MonsterSpawnInfo.UNDERGROUND, 10 ),
				new MonsterSpawnInfo( "HEAT_SHADE", MonsterSpawnInfo.UNDERGROUND,
						80 ), } );
			ret.setMapLocationKey( "CATACOMBS" );
			// ret.setIsCandled(true);
		}
		else if ( levelID.startsWith( "RESERVOIR" ) )
		{
			LavaCaveLevelGenerator clg = new LavaCaveLevelGenerator( );
			clg.init( "CAVE_WALL", "CAVE_WATER", "CAVE_FLOOR" );
			ret = clg.generateLevel( Util.rand( 50, 70 ), Util.rand( 30, 40 ), true,
					true );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "FROZEN_SHADE", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "MANDRAGORA", MonsterSpawnInfo.UNDERGROUND, 3 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Underwater Reservoir" );
			ret.setMusicKeyMorning( "RESERVOIR" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "TRITON", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "FROZEN_SHADE", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.WATER, 80 ), } );
			ret.setMapLocationKey( "RESERVOIR" );
			// ret.setIsCandled(true);
			if ( levelID.equals( "RESERVOIR0" ) )
			{
				Position p = ret.getExitFor( "_BACK" );
				ret.removeExit( "_BACK" );
				ret.addExit( p, "DEEP_FORK0" );
			}
		}
		else if ( levelID.startsWith( "INNER_QUARTERS" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getInnerQuartersRooms( );
			fcg.initialize( rooms, "QUARTERS_WALL", Util.rand( 80, 100 ),
					Util.rand( 80, 100 ), "QUARTERS_CORRIDOR", "MARBLE_STAIRSDOWN_FAKE",
					"MARBLE_STAIRSUP_FAKE" );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 80 ), } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Inner Quarters" );
			ret.setMusicKeyMorning( "QUARTERS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "WHITE_SKELETON", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "ZOMBIE", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "SPEAR_KNIGHT", MonsterSpawnInfo.UNDERGROUND,
						20 ), } );
			ret.setMapLocationKey( "INNER_QUARTERS" );
			// ret.setIsCandled(true);
			if ( levelID.equals( "INNER_QUARTERS0" ) )
			{
				Position p = ret.getExitFor( "_BACK" );
				ret.removeExit( "_BACK" );
				ret.addExit( p, "QUARTERS_FORK0" ); // Add the correspondant fork
			}
			// lightCandles(ret);
			ret.setRutinary( true );
		}
		else if ( levelID.startsWith( "DUNGEON" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getDungeonRooms( );
			fcg.initialize( rooms, "DUNGEON_WALL", Util.rand( 80, 100 ),
					Util.rand( 80, 100 ), "DUNGEON_PASSAGE", "DUNGEON_UP",
					"DUNGEON_DOWN" );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "KILLER_PLANT", MonsterSpawnInfo.UNDERGROUND, 70 ),
				new MonsterSpawnInfo( "VAMPIRE_BAT", MonsterSpawnInfo.BORDER, 40 ),
				new MonsterSpawnInfo( "WIGHT", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "SPECTER", MonsterSpawnInfo.UNDERGROUND, 40 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Castle Dungeon" );
			ret.setMusicKeyMorning( "DUNGEON" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "SKULL_LORD", MonsterSpawnInfo.UNDERGROUND, 20 ),
				new MonsterSpawnInfo( "BONE_ARCHER", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "AXE_KNIGHT", MonsterSpawnInfo.UNDERGROUND, 80 ),
				new MonsterSpawnInfo( "SALOME", MonsterSpawnInfo.UNDERGROUND, 50 ), } );

			ret.setMapLocationKey( "DUNGEON" );
			// ret.setIsCandled(true);
			// lightCandles(ret);
			ret.populate( );
			ret.setRutinary( true );
		}
		else if ( levelID.startsWith( "CLOCK_BASE" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getClockTowerRooms( );
			fcg.initialize( rooms, "TOWER_WALL", Util.rand( 80, 100 ),
					Util.rand( 80, 100 ), "TOWER_STAIRS", "MARBLE_STAIRSDOWN_FAKE",
					"MARBLE_STAIRSUP_FAKE" );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{ new MonsterSpawnInfo( "MEDUSA_HEAD", MonsterSpawnInfo.BORDER, 50 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Clock Tower" );
			ret.setMusicKeyMorning( "TOWER" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "CROW", MonsterSpawnInfo.UNDERGROUND, 90 ),
				new MonsterSpawnInfo( "DRAGON_SKULL_CANNON", MonsterSpawnInfo.UNDERGROUND,
						20 ),
				new MonsterSpawnInfo( "BUER", MonsterSpawnInfo.UNDERGROUND, 50 ),
				new MonsterSpawnInfo( "HARPY", MonsterSpawnInfo.UNDERGROUND, 60 ),
				new MonsterSpawnInfo( "BONE_MUSKET", MonsterSpawnInfo.UNDERGROUND, 40 ),
				new MonsterSpawnInfo( "LILITH", MonsterSpawnInfo.UNDERGROUND, 50 ), } );
			ret.setMapLocationKey( "CLOCKTOWER" );
			// ret.setIsCandled(true);
			// lightCandles(ret);
			ret.populate( );
			ret.setRutinary( true );
		}
		else if ( levelID.startsWith( "SEWERS" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getSewersRooms( );
			fcg.initialize( rooms, "SEWERS_WALL", Util.rand( 50, 70 ),
					Util.rand( 50, 70 ), "SEWERS_FLOOR", "SEWERS_UP", "SEWERS_DOWN" );
			fcg.setCheckCorridor( false );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.UNDERGROUND, 100 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.UNDERGROUND,
						100 ), } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Petra Sewers" );
			ret.setMusicKeyMorning( "SEWERS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 90 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.UNDERGROUND,
						20 ), } );
			ret.setMapLocationKey( "TOWN" );
			ret.populate( );
			ret.setRutinary( false );
		}
		else if ( levelID.startsWith( "DEEP_SEWERS" ) )
		{
			FeatureCarveGenerator fcg = new FeatureCarveGenerator( );
			ArrayList rooms = getDeepSewersRooms( );
			fcg.initialize( rooms, "SEWERS_WALL_WATER", Util.rand( 50, 70 ),
					Util.rand( 50, 70 ), "SEWERS_FLOOR_WATER", "SEWERS_UP_WATER",
					"SEWERS_DOWN_WATER" );
			fcg.setCheckCorridor( false );
			ret = fcg.generateLevel( );
			ret.setInhabitants( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.UNDERGROUND, 100 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.UNDERGROUND,
						100 ) } );
			ret.setDispatcher( new Dispatcher( ) );
			ret.setRespawner( x );
			ret.setDescription( "Petra Sewers" );
			ret.setMusicKeyMorning( "SEWERS" );
			ret.setDwellersInfo( new MonsterSpawnInfo[ ]
			{	new MonsterSpawnInfo( "MERMAN", MonsterSpawnInfo.WATER, 90 ),
				new MonsterSpawnInfo( "KNIFE_MERMAN", MonsterSpawnInfo.WATER, 20 ), } );
			ret.setMapLocationKey( "TOWN" );
			ret.populate( );
			ret.setRutinary( false );
		}

		if ( hasHostage )
		{
			Hostage hostage = NPCFactory.getFactory( ).buildHostage( );
			hostage.setReward( 100 * ( Util.rand( 100, 150 ) / 100 ) );
			while ( true )
			{
				Position rand = new Position( Util.rand( 5, ret.getWidth( ) ),
						Util.rand( 5, ret.getHeight( ) ),
						Util.rand( 0, ret.getDepth( ) ) );
				if ( ret.isItemPlaceable( rand ) )
				{
					hostage.setPosition( rand );
					break;
				}
			}
			ret.addMonster( hostage );
		}
		ret.setID( levelID );
		if ( !overrideLevelNumber )
			ret.setLevelNumber( metadata.getLevelNumber( ) );

		if ( ret.getExitFor( "_BACK" ) != null )
		{
			Position p = ret.getExitFor( "_BACK" );
			ret.removeExit( "_BACK" );
			ret.addExit( p, metadata.getExit( "_BACK" ) );

		}

		if ( ret.getExitFor( "_NEXT" ) != null )
		{
			Position p = ret.getExitFor( "_NEXT" );
			ret.removeExit( "_NEXT" );
			ret.addExit( p, metadata.getExit( "_NEXT" ) );
		}
		ret.setMetaData( metadata );
		if ( ret.isRutinary( ) )
		{
			placeItems( ret, player );
			ret.lightLights( );
		}
		Debug.exitMethod( ret );
		return ret;

	}

	/*
	 * public static Dispatcher getCurrentDispatcher() { return currentDispatcher; }
	 */

	public static void lightCandles( Level l )
	{
		int candles = ( l.getHeight( ) * l.getWidth( ) ) / 200;
		Position temp = new Position( 0, 0 );
		for ( int i = 0; i < candles; i++ )
		{
			temp.x = Util.rand( 1, l.getWidth( ) - 1 );
			temp.y = Util.rand( 1, l.getHeight( ) - 1 );
			if ( !l.isItemPlaceable( temp ) )
			{
				i--;
				continue;
			}

			Feature vFeature = FeatureFactory.getFactory( ).buildFeature( "CANDLE" );
			vFeature.setPosition( temp.x, temp.y, temp.z );
			l.addFeature( vFeature );
		}
	}

	public static void placeItems( Level ret, Player player )
	{
		int items = Util.rand( 8, 12 );
		// int items = 300;
		for ( int i = 0; i < items; i++ )
		{
			Item item = ItemFactory.getItemFactory( ).createItemForLevel( ret, player );
			if ( item == null )
				break;
			int xrand = 0;
			int yrand = 0;
			Position pos = null;
			do
			{
				xrand = Util.rand( 1, ret.getWidth( ) - 1 );
				yrand = Util.rand( 1, ret.getHeight( ) - 1 );
				pos = new Position( xrand, yrand );
			}
			while ( !ret.isItemPlaceable( pos ) );
			ret.addItem( pos, item );
		}
	}

	private static ArrayList getClockTowerRooms( )
	{
		int rooms = Util.rand( 12, 15 );
		// rooms = 3;
		co.castle.levelgen.featureCarve.Feature room = null;
		ArrayList ret = new ArrayList( );
		String wall = "TOWER_WALL";
		String floor = "TOWER_FLOOR";
		String candle = "F_TOWER_FLOOR CANDLE";

		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 3 ) )
			{
			case 1:
				room = new CircularRoom( Util.rand( 6, 10 ), Util.rand( 6, 10 ), floor,
						wall );
				break;
			case 2:
				room = new RingRoom( Util.rand( 6, 12 ), Util.rand( 6, 12 ), floor,
						wall );
				break;
			case 3:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						candle );
				break;
			}
			ret.add( room );
		}
		return ret;

	}

	private static ArrayList getDeepSewersRooms( )
	{
		int rooms = Util.rand( 4, 6 );
		co.castle.levelgen.featureCarve.Feature room = null;

		ArrayList ret = new ArrayList( );
		String floor = "SEWERS_FLOOR_WATER";
		String column = "SEWERS_WALL_WATER";

		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 2 ) )
			{
			case 1:
				room = new CircularRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						column );
				break;
			case 2:
				room = new RingRoom( Util.rand( 6, 12 ), Util.rand( 6, 12 ), floor,
						column );
				;
				break;
			}
			ret.add( room );
		}

		return ret;
	}

	private static ArrayList getDungeonRooms( )
	{
		int rooms = Util.rand( 12, 15 );
		// rooms = 3;
		co.castle.levelgen.featureCarve.Feature room = null;
		ArrayList ret = new ArrayList( );
		String floor = "DUNGEON_FLOOR";
		String candle = "F_DUNGEON_FLOOR CANDLE";
		String column = "DUNGEON_WALL";
		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 3 ) )
			{
			case 1:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						column );
				break;
			case 2:
				room = new RoomFeature( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor );
				break;
			case 3:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						candle );
				break;
			}
			ret.add( room );
		}
		return ret;

	}

	private static ArrayList getInnerQuartersRooms( )
	{
		int rooms = Util.rand( 12, 15 );
		// rooms = 3;
		co.castle.levelgen.featureCarve.Feature room = null;
		ArrayList ret = new ArrayList( );
		String wall = "QUARTERS_WALL";
		String floor = "QUARTERS_FLOOR";
		String column = "MARBLE_COLUMN";
		String candle = "F_QUARTERS_FLOOR CANDLE";
		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 5 ) )
			{
			case 1:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						column );
				break;
			case 2:
				room = new CircularRoom( Util.rand( 6, 10 ), Util.rand( 6, 10 ), floor,
						wall );
				break;
			case 3:
				room = new RingRoom( Util.rand( 6, 12 ), Util.rand( 6, 12 ), floor,
						wall );
				break;
			case 4:
				room = new RoomFeature( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor );
				break;
			case 5:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						candle );
				break;
			}
			ret.add( room );
		}
		return ret;

	}

	private static ArrayList getSewersRooms( )
	{
		int rooms = Util.rand( 4, 6 );
		co.castle.levelgen.featureCarve.Feature room = null;

		ArrayList ret = new ArrayList( );
		String floor = "SEWERS_FLOOR";
		String column = "SEWERS_WALL";

		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 2 ) )
			{
			case 1:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						column );
				break;
			case 2:
				room = new RingRoom( Util.rand( 6, 12 ), Util.rand( 6, 12 ), floor,
						column );
				;
				break;
			}
			ret.add( room );
		}

		return ret;
	}

	private static ArrayList getWareHouseRooms( )
	{
		int rooms = Util.rand( 12, 15 );
		// rooms = 3;
		co.castle.levelgen.featureCarve.Feature room = null;

		ArrayList ret = new ArrayList( );
		String floor = "WAREHOUSE_FLOOR";
		String column = "WAREHOUSE_WALL";
		String candle = "F_WAREHOUSE_FLOOR CANDLE";

		for ( int i = 0; i < rooms; i++ )
		{
			switch ( Util.rand( 1, 3 ) )
			{
			case 1:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						column );
				break;
			case 2:
				room = new RoomFeature( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor );
				break;
			case 3:
				room = new ColumnsRoom( Util.rand( 5, 12 ), Util.rand( 5, 12 ), floor,
						candle );
				break;
			}
			ret.add( room );
		}

		return ret;

	}

	protected int placeKeys( Level ret )
	{
		Debug.enterMethod( this, "placeKeys" );
		// Place the magic Keys
		int keys = Util.rand( 1, 4 );
		Position tempPosition = new Position( 0, 0 );
		for ( int i = 0; i < keys; i++ )
		{
			int keyx = Util.rand( 1, ret.getWidth( ) - 1 );
			int keyy = Util.rand( 1, ret.getHeight( ) - 1 );
			int keyz = Util.rand( 0, ret.getDepth( ) - 1 );
			tempPosition.x = keyx;
			tempPosition.y = keyy;
			tempPosition.z = keyz;
			if ( ret.isItemPlaceable( tempPosition ) )
			{
				Feature keyf = FeatureFactory.getFactory( ).buildFeature( "KEY" );
				keyf.setPosition( tempPosition.x, tempPosition.y, tempPosition.z );
				ret.addFeature( keyf );
			}
			else
			{
				i--;
			}
		}
		Debug.exitMethod( keys );
		return keys;

	}
}