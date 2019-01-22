package co.castle.levelgen.featureCarve;

import java.util.ArrayList;

import sz.util.Position;
import sz.util.Util;

public class FeaturesBorderRoom extends RoomFeature
{
	private String feature;
	private String featureFloor;

	public FeaturesBorderRoom( int width, int height, String floor, String featureString )
	{
		super( width, height, floor );
		this.feature = featureString.split( " " )[ 1 ];
		this.featureFloor = featureString.split( " " )[ 0 ];
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
					canvas[ x ][ start.y + 2 ] = featureFloor;
					canvas[ x ][ start.y + height - 3 ] = featureFloor;
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
					canvas[ start.x + 2 ][ y ] = featureFloor;
					canvas[ start.x + width - 3 ][ y ] = featureFloor;
				}
			}
		}
		return ret;
	}
}
