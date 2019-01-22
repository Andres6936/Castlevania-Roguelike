package co.castle.levelgen;

import co.castle.feature.Feature;
import co.castle.feature.FeatureFactory;
import co.castle.game.CRLException;
import co.castle.item.Item;
import co.castle.item.ItemFactory;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.level.MapCellFactory;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public abstract class LevelGenerator
{
	// public abstract Level generateLevel(String param, Dispatcher dispa);

	public void placeClue( Level ret, int clueLevel )
	{
		Debug.enterMethod( this, "placeClue" );
		// Place the clue
		Position tempPosition = new Position( 0, 0 );
		while ( true )
		{
			int cluex = Util.rand( 1, ret.getWidth( ) - 1 );
			int cluey = Util.rand( 1, ret.getHeight( ) - 1 );
			int cluez = Util.rand( 0, ret.getDepth( ) - 1 );
			tempPosition.x = cluex;
			tempPosition.y = cluey;
			tempPosition.z = cluez;
			if ( ret.isWalkable( tempPosition ) )
			{
				Item clue = ItemFactory.getItemFactory( )
						.createItem( "CLUE_PAGE" + clueLevel );
				ret.addItem( tempPosition, clue );
				break;
			}
		}
		Debug.exitMethod( );
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
			if ( ret.isWalkable( tempPosition )
					&& !ret.getMapCell( tempPosition ).isWater( ) )
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

	protected Cell[ ][ ] renderLevel( String[ ][ ] cellIds ) throws CRLException
	{
		Debug.enterMethod( this, "renderLevel" );
		MapCellFactory mcf = MapCellFactory.getMapCellFactory( );
		Cell[ ][ ] ret = new Cell[ cellIds.length ][ cellIds[ 0 ].length ];
		for ( int x = 0; x < cellIds.length; x++ )
			for ( int y = 0; y < cellIds[ 0 ].length; y++ )
				if ( cellIds[ x ][ y ].startsWith( "F_" ) )
					ret[ x ][ y ] = mcf.getMapCell(
							cellIds[ x ][ y ].substring( 2 ).split( " " )[ 0 ] );
				else
					ret[ x ][ y ] = mcf.getMapCell( cellIds[ x ][ y ] );
		Debug.exitMethod( ret );
		return ret;
	}

	protected void renderLevelFeatures(	Level level,
										String[ ][ ] cellIds ) throws CRLException
	{
		for ( int x = 0; x < cellIds.length; x++ )
			for ( int y = 0; y < cellIds[ 0 ].length; y++ )
			{
				if ( cellIds[ x ][ y ].startsWith( "F_" ) )
				{
					Feature f = FeatureFactory.getFactory( ).buildFeature(
							cellIds[ x ][ y ].substring( 2 ).split( " " )[ 1 ] );
					f.setPosition( x, y, 0 );
					level.addFeature( f );
				}
			}
	}

	/*
	 * protected boolean checkConnection(int[][] map, Position start, Position end){
	 * Stack stack = new Stack(); Position now = start; stack.push(now); boolean
	 * [][] goneThru = new boolean[map.length][map[0].length]; for (int x = 0; x <
	 * goneThru.length; x++) for (int y = 0; y < goneThru[0].length; y++)
	 * goneThru[x][y] = map[x][y] == 1; do { now = (Position) stack.pop(); if (now.x
	 * > 0 && goneThru[now.x-1][now.y] == true){ stack.push(new Position(now.x-1,
	 * now.y)); goneThru[now.x-1][now.y] = false; } if (now.y > 0 &&
	 * goneThru[now.x][now.y-1] == true){ stack.push(new Position(now.x, now.y-1));
	 * goneThru[now.x][now.y-1] = false; } if (now.x < map.length - 1 &&
	 * goneThru[now.x+1][now.y] == true){ stack.push(new Position(now.x+1, now.y));
	 * goneThru[now.x+1][now.y] = false; } if (now.y < map[0].length - 1 &&
	 * goneThru[now.x][now.y+1] == true){ stack.push(new Position(now.x, now.y+1));
	 * goneThru[now.x][now.y+1] = false; } if (now.equals(end)){ return true; } }
	 * while (!stack.isEmpty()); Debug.exitMethod("false"); return false; }
	 */

}