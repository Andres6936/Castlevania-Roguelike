package co.castle.levelgen.featureCarve;

import java.util.ArrayList;

import co.castle.action.Action;
import sz.util.Position;
import sz.util.Util;

public class RoomFeature extends Feature
{
	private String floor;
	protected Position start;
	protected int width, height;

	public RoomFeature( int width, int height, String floor )
	{
		start = new Position( 0, 0 );
		this.width = width;
		this.height = height;
		this.floor = floor;
	}

	public boolean drawOverCanvas(	String[ ][ ] canvas, Position where, int direction,
									boolean[ ][ ] mask, ArrayList hotspots )
	{
		int rndPin = 0;
		switch ( direction )
		{
		case Action.UP:
			rndPin = Util.rand( 1, width - 2 );
			start.x = where.x - rndPin;
			start.y = where.y - height + 1;
			break;
		case Action.DOWN:
			rndPin = Util.rand( 1, width - 2 );
			start.x = where.x - rndPin;
			start.y = where.y;
			break;
		case Action.LEFT:
			rndPin = Util.rand( 1, height - 2 );
			start.x = where.x - width + 1;
			start.y = where.y - rndPin;
			break;
		case Action.RIGHT:
			rndPin = Util.rand( 1, height - 2 );
			start.x = where.x;
			// start.x = where.x+1;
			start.y = where.y - rndPin;
			break;
		}
		// Check the mask
		for ( int x = start.x; x < start.x + width; x++ )
		{
			for ( int y = start.y; y < start.y + height; y++ )
			{
				if ( !isValid( x, y, canvas ) || mask[ x ][ y ] )
				{
					return false;
				}
			}
		}

		// Carve
		for ( int x = start.x; x < start.x + width; x++ )
		{
			for ( int y = start.y; y < start.y + height; y++ )
			{
				if ( x == start.x || x == start.x + width - 1 || y == start.y
						|| y == start.y + height - 1 )
				{
					mask[ x ][ y ] = true;
					if ( !( x == start.x && y == start.y )
							&& !( x == start.x + width - 1 && y == start.y )
							&& !( x == start.x && y == start.y + height - 1 )
							&& !( x == start.x + width - 1
									&& y == start.y + height - 1 ) )
						hotspots.add( new Position( x, y ) );
				}
				else
				{
					canvas[ x ][ y ] = floor;
					mask[ x ][ y ] = true;
				}

			}
		}

		/*
		 * for (int i = 0; i < doors; i++){ Position doorPosition = new Position(0,0);
		 * if (Util.chance(50)){ //Horizontal doorPosition.x = Util.rand(start.x+1,
		 * start.x + width-2); if (Util.chance(50)){ doorPosition.y = start.y; } else {
		 * doorPosition.y = start.y + height-1; } mask[doorPosition.x+1][doorPosition.y]
		 * = false; mask[doorPosition.x-1][doorPosition.y] = false; } else {
		 * doorPosition.y = Util.rand(start.y+1, start.y + height-2); if
		 * (Util.chance(50)){ doorPosition.x = start.x; } else { doorPosition.x =
		 * start.x + width-1; } mask[doorPosition.x][doorPosition.y-1] = false;
		 * mask[doorPosition.x][doorPosition.y+1] = false; }
		 * canvas[doorPosition.x][doorPosition.y] = door;
		 * mask[doorPosition.x][doorPosition.y] = false; hotspots.add(doorPosition); }
		 */
		return true;
	}
}
