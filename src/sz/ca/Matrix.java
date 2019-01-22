package sz.ca;

public class Matrix
{
	private int[ ][ ] futureValues;
	private int[ ][ ] values;

	public Matrix( int dim )
	{
		this( dim, dim );
	}

	public Matrix( int xdim, int ydim )
	{
		values = new int[ xdim ][ ydim ];
		futureValues = new int[ xdim ][ ydim ];
	}

	public void advance( )
	{
		/** All future values become current */
		for ( int x = 0; x < values.length; x++ )
		{
			for ( int y = 0; y < values[ 0 ].length; y++ )
			{
				values[ x ][ y ] = futureValues[ x ][ y ];
			}
		}
	}

	public void clean( )
	{
		/** Sets all the values and old Values to 0 */
		values = new int[ values.length ][ values[ 0 ].length ];
		futureValues = new int[ values.length ][ values[ 0 ].length ];
	}

	public int get( int x, int y )
	{
		/** returns the current state of cell(x,y) */
		return values[ x ][ y ];
	}

	public int[ ][ ] getArrays( )
	{
		return values;
	}

	public int getHeight( )
	{
		return values[ 0 ].length;
	}

	public int getSurroundingCount( int x, int y, int type )
	{
		/**
		 * Returns the number of cells of type type that surround the matrix at x,y,
		 * wrapping from the sides
		 */
		int upIndex = ( y == 0 ? getHeight( ) - 1 : y - 1 );
		int downIndex = ( y == getHeight( ) - 1 ? 0 : y + 1 );
		int rightIndex = ( x == getWidth( ) - 1 ? 0 : x + 1 );
		int leftIndex = ( x == 0 ? getWidth( ) - 1 : x - 1 );
		int count = ( values[ leftIndex ][ upIndex ] == type ? 1 : 0 )
				+ ( values[ leftIndex ][ y ] == type ? 1 : 0 )
				+ ( values[ leftIndex ][ downIndex ] == type ? 1 : 0 )
				+ ( values[ rightIndex ][ upIndex ] == type ? 1 : 0 )
				+ ( values[ rightIndex ][ y ] == type ? 1 : 0 )
				+ ( values[ rightIndex ][ downIndex ] == type ? 1 : 0 )
				+ ( values[ x ][ downIndex ] == type ? 1 : 0 )
				+ ( values[ x ][ upIndex ] == type ? 1 : 0 );
		return count;
	}

	public int getSurroundingCountNoWrap( int x, int y, int type )
	{
		/**
		 * Returns the number of cells of type type that surround the matrix at x,y
		 */
		int count = ( y == 0
				|| x == 0 ? 0 : ( values[ x - 1 ][ y - 1 ] == type ? 1 : 0 ) )
				+ ( x == 0 ? 0 : ( values[ x - 1 ][ y ] == type ? 1 : 0 ) )
				+ ( x == 0 || y == getHeight( )
						- 1 ? 0 : ( values[ x - 1 ][ y + 1 ] == type ? 1 : 0 ) )
				+ ( y == 0 || x == getWidth( )
						- 1 ? 0 : ( values[ x + 1 ][ y - 1 ] == type ? 1 : 0 ) )
				+ ( x == getWidth( ) - 1 ? 0 : ( values[ x + 1 ][ y ] == type ? 1 : 0 ) )
				+ ( x == getWidth( ) - 1 || y == getHeight( )
						- 1 ? 0 : ( values[ x + 1 ][ y + 1 ] == type ? 1 : 0 ) )
				+ ( y == getHeight( ) - 1 ? 0 : ( values[ x ][ y + 1 ] == type ? 1 : 0 ) )
				+ ( y == 0 ? 0 : ( values[ x ][ y - 1 ] == type ? 1 : 0 ) );
		return count;
	}

	public int getWidth( )
	{
		return values.length;
	}

	public void setFuture( int value, int x, int y )
	{
		/** Sets the future state of cell(x,y) to value */
		futureValues[ x ][ y ] = value;
	}

	public void setPresent( int value, int x, int y )
	{
		/** Sets the future state of cell(x,y) to value */
		values[ x ][ y ] = value;
	}
}
