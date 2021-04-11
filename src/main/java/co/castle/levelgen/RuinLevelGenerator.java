package co.castle.levelgen;

import co.castle.feature.Feature;
import co.castle.feature.FeatureFactory;
import co.castle.game.CRLException;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.level.MapCellFactory;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

/**
 * Generates a level with building ruins The ruins are defined as a big space
 * with empty 'houses' inside.
 * 
 * @author Slash
 */
public class RuinLevelGenerator extends LevelGenerator
{
	private String baseWall, baseFloor, baseDoor;
	private String[ ][ ] preLevel;

	public Level generateLevel( int xdim, int ydim, int ruins ) throws CRLException
	{
		preLevel = new String[ xdim ][ ydim ];
		for ( int x = 0; x < xdim; x++ )
			for ( int y = 0; y < ydim; y++ )
				preLevel[ x ][ y ] = baseFloor;
		for ( int x = 0; x < xdim; x++ )
		{
			preLevel[ x ][ 0 ] = baseWall;
			preLevel[ x ][ ydim - 1 ] = baseWall;
		}
		for ( int y = 0; y < ydim; y++ )
		{
			preLevel[ 0 ][ y ] = baseWall;
			preLevel[ xdim - 1 ][ y ] = baseWall;
		}
		ruins += Util.rand( 0, (int) ( ruins * 0.1 ) );
		for ( int i = 0; i < ruins; i++ )
		{
			doRuin( );
		}
		Level ret = new Level( );
		Cell[ ][ ] floor = renderLevel( preLevel );
		Cell[ ][ ][ ] cells = new Cell[ 1 ][ ][ ];
		cells[ 0 ] = floor;
		ret.setCells( cells );

		// Place the entrance
		int yEntrance = Util.rand( 5, getHeight( ) - 5 );
		int yExit = Util.rand( 5, getHeight( ) - 5 );
		// ret.setPositions(new Position(0,yEntrance), new
		// Position(getWidth()-1,yExit));
		ret.addExit( new Position( 0, yEntrance ), "_BACK" );
		ret.addExit( new Position( getWidth( ) - 1, yExit ), "_NEXT" );
		cells[ 0 ][ 0 ][ yEntrance ] = MapCellFactory.getMapCellFactory( )
				.getMapCell( baseFloor );

		int keys = placeKeys( ret );

		// Place the magic door
		Feature door = FeatureFactory.getFactory( ).buildFeature( "MAGIC_DOOR" );
		cells[ 0 ][ getWidth( ) - 1 ][ yExit ] = MapCellFactory.getMapCellFactory( )
				.getMapCell( baseFloor );
		door.setPosition( getWidth( ) - 1, yExit, 0 );
		door.setKeyCost( keys );
		ret.addFeature( door );

		lightCandles( ret );
		return ret;
	}

	public void init( String pWall, String pFloor, String pDoor )
	{
		baseWall = pWall;
		baseFloor = pFloor;
		baseDoor = pDoor;
	}

	private void doRuin( )
	{
		Debug.enterMethod( this, "doRuin" );
		int giveUp = 0;
		int xpos = 0;
		int ypos = 0;
		int width = 0;
		int height = 0;

		do
		{
			xpos = Util.rand( 5, getWidth( ) - 20 );
			ypos = Util.rand( 1, getHeight( ) - 16 );
			width = Util.rand( 4, 15 );
			height = Util.rand( 4, 15 );
			giveUp++;
		}
		while ( hasConflicts( xpos, ypos, width, height ) && giveUp < 100 );
		if ( giveUp == 100 )
		{
			Debug.exitMethod( );
			return;
		}
		for ( int x = xpos; x < xpos + width; x++ )
		{
			if ( Util.chance( 70 ) )
				preLevel[ x ][ ypos ] = baseWall;
			if ( Util.chance( 70 ) )
				preLevel[ x ][ ypos + height - 1 ] = baseWall;

		}
		for ( int y = ypos; y < ypos + height; y++ )
		{
			if ( Util.chance( 70 ) )
				preLevel[ xpos ][ y ] = baseWall;
			if ( Util.chance( 70 ) )
				preLevel[ xpos + width - 1 ][ y ] = baseWall;
		}

		/*
		 * if (Util.chance(50)) if (Util.chance(50)) preLevel[xpos][Util.rand(ypos+1,
		 * ypos+height-1)] = baseDoor; else preLevel[xpos+width-1][Util.rand(ypos+1,
		 * ypos+height-1)] = baseDoor; else if (Util.chance(50))
		 * preLevel[Util.rand(xpos+1, xpos+width-1)][ypos] = baseDoor; else
		 * preLevel[Util.rand(xpos+1, xpos+width-1)][ypos+height-1] = baseDoor;
		 */
		Debug.exitMethod( );
	}

	private int getHeight( )
	{
		return preLevel[ 0 ].length;
	}

	private int getWidth( )
	{
		return preLevel.length;
	}

	private boolean hasConflicts( int xpos, int ypos, int width, int height )
	{
		for ( int x = xpos - 1; x < xpos + width + 1; x++ )
		{
			for ( int y = ypos - 1; y < ypos + height + 1; y++ )
			{
				if ( preLevel[ x ][ y ].equals( baseWall )
						|| preLevel[ x ][ y ].equals( baseDoor ) )
					return true;
			}
		}
		return false;
	}

	private void lightCandles( Level l )
	{
		int candles = ( getHeight( ) * getWidth( ) ) / 200;
		for ( int i = 0; i < candles; i++ )
		{
			int xrnd = Util.rand( 1, getWidth( ) - 1 );
			int yrnd = Util.rand( 1, getHeight( ) - 1 );
			if ( l.getMapCell( xrnd, yrnd, 0 ).isSolid( ) )
			{
				i--;
				continue;
			}

			Feature vFeature = FeatureFactory.getFactory( ).buildFeature( "CANDLE" );
			vFeature.setPosition( xrnd, yrnd, 0 );
			l.addFeature( vFeature );
		}
	}

}
