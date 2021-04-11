package sz.ca;

import sz.util.Util;

public class CARandomInitializer
{
	private boolean border;
	private double[ ] proportions;

	public CARandomInitializer( double[ ] proportions, boolean border )
	{
		this.proportions = proportions;
		this.border = border;
	}

	public void init( Matrix map )
	{
		for ( int x = 0; x < map.getWidth( ); x++ )
			for ( int y = 0; y < map.getHeight( ); y++ )
				map.setFuture( 0, x, y );

		if ( border )
		{
			for ( int x = 0; x < map.getWidth( ); x++ )
			{
				map.setFuture( 1, x, 0 );
				map.setFuture( 1, x, map.getHeight( ) - 1 );
			}
			for ( int y = 0; y < map.getHeight( ); y++ )
			{
				map.setFuture( 1, 0, y );
				map.setFuture( 1, map.getWidth( ) - 1, y );
			}
		}

		int cellCount = map.getHeight( ) * map.getWidth( );
		for ( int i = 0; i < proportions.length; i++ )
		{
			for ( int j = 0; j < (int) ( proportions[ i ] * cellCount ); j++ )
			{
				int xgo = Util.rand( 0, map.getWidth( ) - 1 );
				int ygo = Util.rand( 0, map.getHeight( ) - 1 );
				if ( map.get( xgo, ygo ) == 0 )
					map.setFuture( i + 1, xgo, ygo );
				else
					j--;
			}
		}
		map.advance( );
	}
}
