package co.castle.levelgen.cave;

import java.util.Hashtable;

import co.castle.cuts.Unleasher;
import co.castle.cuts.intro.Intro1;
import co.castle.cuts.intro.Intro2;
import co.castle.cuts.intro.Intro3;
import co.castle.game.CRLException;
import co.castle.level.Cell;
import co.castle.level.Dispatcher;
import co.castle.level.Level;
import co.castle.level.MapCellFactory;
import co.castle.levelgen.LevelGenerator;
import co.castle.levelgen.StaticGenerator;
import sz.ca.CARandomInitializer;
import sz.ca.CARule;
import sz.ca.Matrix;
import sz.ca.SZCA;
import sz.util.Position;
import sz.util.Util;

public class BeginningLevelGenerator extends LevelGenerator
{
	private String baseWall, baseFloor, baseLava;

	private Hashtable charMap = new Hashtable( );

	private String[ ][ ][ ] STARTING_MAPS = new String[ ][ ][ ]
	{
		{
			{	"                    ", "             ---    ", "  wwwwww     ->-    ",
				"  w--b-w     ---    ", "  w----w            ", "  w----w     ---    ",
				"  ---www     ---    ", "         --  ---    ", "   ---  --          ",
				"            wwwwww  ", "            w----w  ", "            w----w  ",
				"            w----w  ", "            wwwwww  ", "                    ",

			},
			{	"                    ", "                    ", "  --wwww      <     ",
				"  w----w      -     ", "  w------------     ", "  w----w      -     ",
				"  ---www      -     ", "              -     ", "              -     ",
				"            ww-www  ", "            w-----  ", "            w----w  ",
				"            --->-w  ", "            ---www  ", "                    ", },
			{	".....%%%.........%%.", "...&&&&.%%....&&&&.%", "..w.wwww..&&&&w&&&&.",
				"%...---%....&&&&....", "%.%%---.....&&&&&&.%", "..w.---w&...C..&&&&.",
				"..w.%%ww..S..H.W.&&.", "...&&.............W.", "....W...............",
				"....%.......w.ww.w&.", "..&.........%-..-w.%", "..........&&.----...",
				"%.%...&&....w%-<-w&.", ".%&..%.&&...%%w.ww%%", "%%%..%%%%.......%%%.", }

		} };

	private Unleasher[ ][ ] STARTING_UNLEASHERS = new Unleasher[ ][ ]
	{
		{ new Intro1( ), new Intro2( ), new Intro3( ) },

	};

	{
		charMap.put( "&", "FOREST_TREE" );
		charMap.put( ".", "FOREST_GRASS" );
		charMap.put( "%", "FOREST_DIRT" );
		charMap.put( "C", "WRECKED_CHARRIOT" );
		charMap.put( "W", "WRECKED_WHEEL" );
		charMap.put( "H", "FOREST_GRASS NPC MELDUCK" );
		charMap.put( "S", "FOREST_GRASS EXIT _START" );
		charMap.put( "b", "HOUSE_FLOOR ITEM RED_CARD" );
		charMap.put( "F", "FOREST_GRASS EXIT FOREST0" );
		charMap.put( "w", "TOWN_WALL" );
		charMap.put( "-", "HOUSE_FLOOR" );
		charMap.put( "<", "TOWN_STAIRSUP" );
		charMap.put( ">", "TOWN_STAIRSDOWN" );

		charMap.put( "+", "SIGNPOST" );
	}

	public Level generateLevel( int xdim, int ydim, boolean locked ) throws CRLException
	{
		/**
		 * Uses Cave Cellular automata, by SantiagoZ Init (1) 30% If 0 and more than 3 1
		 * around, 1 If 1 and less than 2 1 around, 0 If 0 and more than 7 0 around, 2
		 * If 2 and more than 2 0 around, 0 Run for 5 turns
		 */

		CARandomInitializer vInit = new CARandomInitializer( new double[ ]
		{ 0.3 }, true );
		CARule[ ] vRules = new CARule[ ]
		{	new CARule( 0, CARule.MORE_THAN, 3, 1, 1 ),
			new CARule( 1, CARule.LESS_THAN, 2, 1, 0 ),
			new CARule( 0, CARule.MORE_THAN, 7, 0, 2 ),
			new CARule( 2, CARule.MORE_THAN, 2, 0, 0 ), };

		int[ ][ ] intMap = null;
		int xEntrance;
		Matrix map = new Matrix( xdim, ydim );
		vInit.init( map );
		SZCA.runCA( map, vRules, 5, false );
		intMap = map.getArrays( );

		int halfHeight = (int) ( Math.round( intMap[ 0 ].length / 3 ) );
		Position start = new Position( Util.rand( 25, intMap.length - 25 ),
				2 * halfHeight + Util.rand( 0, halfHeight - 15 ), 2 );

		// Carve the exit to forest
		xEntrance = Util.rand( 5, intMap.length - 5 );
		intMap[ xEntrance ][ 0 ] = 0;
		for ( int i = 1; i < 4; i++ )
		{
			intMap[ xEntrance - 1 ][ i ] = 0;
			intMap[ xEntrance ][ i ] = 0;
			intMap[ xEntrance + 1 ][ i ] = 0;
		}

		Position end = new Position( xEntrance, 0, 2 );

		// Run the wisps
		WispSim.setWisps( new Wisp( start, 10, 40, 5 ), new Wisp( end, 10, 40, 5 ) );
		WispSim.run( intMap );

		String[ ][ ] tiles = new String[ intMap.length ][ intMap[ 0 ].length ];
		Level ret = new Level( );
		for ( int x = 0; x < intMap.length; x++ )
			for ( int y = 0; y < intMap[ 0 ].length; y++ )
				if ( intMap[ x ][ y ] == 0 )
					tiles[ x ][ y ] = baseFloor;
				else if ( intMap[ x ][ y ] == 1 )
					tiles[ x ][ y ] = baseWall;
				else if ( intMap[ x ][ y ] == 2 )
					tiles[ x ][ y ] = baseLava;
				else if ( intMap[ x ][ y ] == 4 )
				{
					tiles[ x ][ y ] = baseFloor;
					// ret.addBlood(new Position(x,y,0), 8);
				}

		Cell[ ][ ] cells = renderLevel( tiles );

		Cell[ ][ ][ ] levelCells = new Cell[ 3 ][ ][ ];
		levelCells[ 0 ] = new Cell[ cells.length ][ cells[ 0 ].length ];
		levelCells[ 1 ] = new Cell[ cells.length ][ cells[ 0 ].length ];
		levelCells[ 2 ] = cells;
		ret.setCells( levelCells );
		Position startUL = new Position( start.x - 10, start.y - 7 );
		String[ ][ ] startingMap = pickStartingMap( );
		ret.setUnleashers( (Unleasher[ ]) Util.randomElementOf( STARTING_UNLEASHERS ) );
		ret.setDispatcher( new Dispatcher( ) );
		StaticGenerator.getGenerator( ).renderOverLevel( ret, startingMap, charMap,
				startUL );

		int trees = ( ret.getHeight( ) * ret.getWidth( ) ) / 10;
		for ( int i = 0; i < trees; i++ )
		{
			int xrnd = Util.rand( 1, ret.getWidth( ) - 1 );
			int yrnd = Util.rand( 1, ret.getHeight( ) - 1 );
			if ( ret.getMapCell( xrnd, yrnd, ret.getDepth( ) - 1 ).isSolid( ) )
			{
				i--;
				continue;
			}
			if ( Util.chance( 50 ) )
				ret.getCells( )[ ret.getDepth( ) - 1 ][ xrnd ][ yrnd ] = MapCellFactory
						.getMapCellFactory( ).getMapCell( "FOREST_TREE_1" );
			else
				ret.getCells( )[ ret.getDepth( ) - 1 ][ xrnd ][ yrnd ] = MapCellFactory
						.getMapCellFactory( ).getMapCell( "FOREST_TREE_2" );
		}

		// ret.addExit(start, "_START");
		ret.addExit( end, "FOREST0" );
		return ret;
	}

	public void init( String baseWall, String baseFloor, String baseLava )
	{
		this.baseWall = baseWall;
		this.baseFloor = baseFloor;
		this.baseLava = baseLava;
	}

	private String[ ][ ] pickStartingMap( )
	{
		return (String[ ][ ]) Util.randomElementOf( STARTING_MAPS );
	}

}
