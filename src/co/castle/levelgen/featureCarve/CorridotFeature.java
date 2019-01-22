package co.castle.levelgen.featureCarve;

import java.util.ArrayList;

import co.castle.action.Action;
import sz.util.Position;

public class CorridotFeature extends Feature
{
	private String floor;
	private int length;

	private Position tip;

	public CorridotFeature( int length, String floor )
	{
		this.length = length;
		this.floor = floor;
	}

	public boolean drawOverCanvas(	String[ ][ ] canvas, Position where, int direction,
									boolean[ ][ ] mask, ArrayList hotspotss )
	{
		Position start = new Position( 0, 0 );
		switch ( direction )
		{
		case Action.UP:
			start.x = where.x;
			start.y = where.y - length + 1;
			break;
		case Action.DOWN:
			start.x = where.x;
			start.y = where.y;
			break;
		case Action.LEFT:
			start.x = where.x - length + 1;
			start.y = where.y;
			break;
		case Action.RIGHT:
			start.x = where.x;
			start.y = where.y;
			break;
		}
		// Check the mask
		if ( direction == Action.UP || direction == Action.DOWN )
		{
			for ( int y = start.y; y < start.y + length; y++ )
			{
				if ( !isValid( start.x, y, canvas ) || !isValid( start.x - 1, y, canvas )
						|| !isValid( start.x + 1, y, canvas ) )
					return false;
			}
			for ( int y = start.y + 1; y < start.y + length - 1; y++ )
			{
				if ( mask[ start.x ][ y ] )
				{
					return false;
				}
			}

			// Carve
			for ( int y = start.y; y < start.y + length; y++ )
			{
				canvas[ start.x ][ y ] = floor;
			}

			for ( int y = start.y + 1; y < start.y + length - 1; y++ )
			{
				mask[ start.x - 1 ][ y ] = true;
				mask[ start.x ][ y ] = true;
				mask[ start.x + 1 ][ y ] = true;
			}

			if ( direction == Action.UP )
			{
				tip = start;
			}
			else
			{
				tip = new Position( start.x, start.y + length - 1 );
			}

		}
		else
		{
			for ( int x = start.x; x < start.x + length; x++ )
			{
				if ( !isValid( x, start.y, canvas ) || !isValid( x, start.y - 1, canvas )
						|| !isValid( x, start.y + 1, canvas ) )
					return false;
			}

			for ( int x = start.x + 1; x < start.x + length - 1; x++ )
			{
				if ( mask[ x ][ start.y ] )
					return false;
			}

			// Carve
			for ( int x = start.x; x < start.x + length; x++ )
			{
				canvas[ x ][ start.y ] = floor;
			}

			for ( int x = start.x + 1; x < start.x + length - 1; x++ )
			{
				mask[ x ][ start.y - 1 ] = true;
				mask[ x ][ start.y ] = true;
				mask[ x ][ start.y + 1 ] = true;
			}

			if ( direction == Action.RIGHT )
			{
				tip = new Position( start.x + length - 1, start.y );
			}
			else
			{
				tip = start;
			}
			mask[ tip.x ][ tip.y ] = false;
		}
		return true;
	}

	public void eraseOverCanvas(	String[ ][ ] canvas, Position where, int direction,
									boolean[ ][ ] mask, String wall )
	{
		Position start = new Position( 0, 0 );
		switch ( direction )
		{
		case Action.UP:
			start.x = where.x;
			start.y = where.y - length + 1;
			break;
		case Action.DOWN:
			start.x = where.x;
			start.y = where.y;
			break;
		case Action.LEFT:
			start.x = where.x;
			start.y = where.y;
			break;
		case Action.RIGHT:
			start.x = where.x - length + 1;
			start.y = where.y;
			break;
		}
		// Check the mask
		if ( direction == Action.UP || direction == Action.DOWN )
		{
			// Carve
			for ( int y = start.y; y < start.y + length; y++ )
			{
				canvas[ start.x ][ y ] = wall;
			}

			for ( int y = start.y + 1; y < start.y + length - 1; y++ )
			{
				mask[ start.x - 1 ][ y ] = false;
				mask[ start.x ][ y ] = false;
				mask[ start.x + 1 ][ y ] = false;
			}

			if ( direction == Action.UP )
			{
				tip = new Position( start.x, start.y + length - 1 );
			}
			else
			{
				tip = start;
			}

		}
		else
		{
			// Carve
			for ( int x = start.x; x < start.x + length; x++ )
			{
				canvas[ x ][ start.y ] = wall;
			}

			for ( int x = start.x + 1; x < start.x + length - 1; x++ )
			{
				mask[ x ][ start.y ] = false;
				mask[ x ][ start.y - 1 ] = false;
				mask[ x ][ start.y + 1 ] = false;
			}

			if ( direction == Action.RIGHT )
			{
				tip = start;
			}
			else
			{
				tip = new Position( start.x + length - 1, start.y );
			}
		}

	}

	public Position getTip( )
	{
		return tip;
	}

}
