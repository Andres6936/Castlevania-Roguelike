package co.castle.levelgen.featureCarve;

import java.util.ArrayList;

import sz.util.Position;
import sz.util.Util;

public class ColumnsRoom extends RoomFeature
{
	private String column;

	public ColumnsRoom( int width, int height, String floor, String column )
	{
		super( width, height, floor );
		this.column = column;
	}

	public boolean drawOverCanvas(	String[ ][ ] canvas, Position where, int direction,
									boolean[ ][ ] mask, ArrayList hotspots )
	{
		boolean ret = super.drawOverCanvas( canvas, where, direction, mask, hotspots );
		int spacing = 0;
		if ( ret == true )
		{
			if ( Util.chance( 50 ) )
			{
				if ( width % 2 == 0 )
					spacing = 3;
				else
					spacing = 2;
				for ( int x = start.x + 2; x < start.x + width - 2; x += spacing )
				{
					canvas[ x ][ start.y + 2 ] = column;
					canvas[ x ][ start.y + height - 3 ] = column;
				}
			}
			else
			{
				if ( height % 2 == 0 )
					spacing = 3;
				else
					spacing = 2;
				for ( int y = start.y + 2; y < start.y + height - 2; y += spacing )
				{
					canvas[ start.x + 2 ][ y ] = column;
					canvas[ start.x + width - 3 ][ y ] = column;
				}
			}
		}
		return ret;
	}
}
