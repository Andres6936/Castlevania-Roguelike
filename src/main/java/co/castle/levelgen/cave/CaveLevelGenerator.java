package co.castle.levelgen.cave;

import co.castle.feature.Feature;
import co.castle.feature.FeatureFactory;
import co.castle.game.CRLException;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.levelgen.LevelGenerator;
import sz.ca.CARandomInitializer;
import sz.ca.CARule;
import sz.ca.Matrix;
import sz.ca.SZCA;
import sz.util.Position;
import sz.util.Util;

public class CaveLevelGenerator extends LevelGenerator
{
	private String baseWall, baseFloor, baseWater;

	public Level generateLevel( int xdim, int ydim, boolean water ) throws CRLException
	{
		/**
		 * Uses Cave Cellular automata, by SZ Init (1) 30% If 0 and more than 3 1
		 * around, 1 If 1 and less than 2 1 around, 0 Run for 5 turns
		 */

		CARandomInitializer vInit = new CARandomInitializer( new double[ ]
		{ 0.3 }, true );
		CARule[ ] vRules = new CARule[ ]
		{	new CARule( 0, CARule.MORE_THAN, 3, 1, 1 ),
			new CARule( 1, CARule.LESS_THAN, 2, 1, 0 ) };

		int[ ][ ] intMap = null;
		int yEntrance = 0;
		int yExit = 0;

		Matrix map = new Matrix( xdim, ydim );
		vInit.init( map );
		SZCA.runCA( map, vRules, 5, false );
		intMap = map.getArrays( );
		// Carve the entrance
		yEntrance = Util.rand( 5, intMap[ 0 ].length - 5 );
		intMap[ 0 ][ yEntrance ] = 0;
		for ( int i = 1; i < 9; i++ )
		{
			intMap[ i ][ yEntrance - 1 ] = 0;
			intMap[ i ][ yEntrance ] = 0;
			intMap[ i + 1 ][ yEntrance ] = 0;
			intMap[ i ][ yEntrance + 1 ] = 0;
		}
		// Carve the exit
		yExit = Util.rand( 5, intMap[ 0 ].length - 5 );
		intMap[ intMap.length - 1 ][ yExit ] = 0;
		for ( int i = 1; i < 9; i++ )
		{
			intMap[ intMap.length - 1 - i ][ yExit - 1 ] = 0;
			intMap[ intMap.length - 1 - i ][ yExit ] = 0;
			intMap[ intMap.length - 2 - i ][ yExit ] = 0;
			intMap[ intMap.length - 1 - i ][ yExit + 1 ] = 0;
		}

		Position start = new Position( 0, yEntrance );
		Position end = new Position( intMap.length - 1, yExit );

		// Run the wisps
		WispSim.setWisps( new Wisp( start, 10, 20, 5 ), new Wisp( end, 10, 20, 5 ) );
		WispSim.run( intMap );

		// Put the keys
		Position key1 = null;
		Position key2 = null;
		while ( key1 == null )
		{
			int xpos = Util.rand( 0, intMap.length - 1 );
			int ypos = Util.rand( 0, intMap[ 0 ].length - 1 );
			if ( intMap[ xpos ][ ypos ] == 0 )
				key1 = new Position( xpos, ypos );
		}
		while ( key2 == null )
		{
			int xpos = Util.rand( 0, intMap.length - 1 );
			int ypos = Util.rand( 0, intMap[ 0 ].length - 1 );
			if ( intMap[ xpos ][ ypos ] == 0 )
				key2 = new Position( xpos, ypos );
		}

		// Run the wisps for the keys
		WispSim.setWisps( new Wisp( key1, 40, 30, 3 ), new Wisp( key2, 20, 30, 3 ) );
		WispSim.run( intMap );

		String[ ][ ] tiles = new String[ intMap.length ][ intMap[ 0 ].length ];
		for ( int x = 0; x < intMap.length; x++ )
			for ( int y = 0; y < intMap[ 0 ].length; y++ )
				if ( intMap[ x ][ y ] == 0 )
					tiles[ x ][ y ] = baseFloor;
				else if ( intMap[ x ][ y ] == 1 )
					tiles[ x ][ y ] = baseWall;
				else if ( intMap[ x ][ y ] == 4 )
					tiles[ x ][ y ] = baseFloor;
		Cell[ ][ ] cells = renderLevel( tiles );
		Level ret = new Level( );
		Cell[ ][ ][ ] levelCells = new Cell[ 1 ][ ][ ];
		levelCells[ 0 ] = cells;
		ret.setCells( levelCells );
		// Place the keys
		Feature keyf = FeatureFactory.getFactory( ).buildFeature( "KEY" );
		keyf.setPosition( key1.x, key1.y, key1.z );
		ret.addFeature( keyf );
		keyf = FeatureFactory.getFactory( ).buildFeature( "KEY" );
		keyf.setPosition( key2.x, key2.y, key2.z );
		ret.addFeature( keyf );

		// Place the door
		Feature door = FeatureFactory.getFactory( ).buildFeature( "MAGIC_DOOR" );
		door.setPosition( ret.getWidth( ) - 1, yExit, 0 );
		door.setKeyCost( 2 );
		ret.addFeature( door );

		// Place candles
		lightCandles( ret );

		ret.addExit( start, "_BACK" );
		ret.addExit( end, "_NEXT" );
		return ret;
	}

	public void init( String baseWall, String baseFloor, String baseWater )
	{
		this.baseWall = baseWall;
		this.baseFloor = baseFloor;
		this.baseWater = baseWater;
	}

	private void lightCandles( Level l )
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
}
