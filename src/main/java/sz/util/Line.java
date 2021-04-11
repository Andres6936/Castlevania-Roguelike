package sz.util;

public class Line
{
	private int deltax;
	private int deltay;
	private int error;
	private boolean steep;
	private int x;
	private int xvar = 1;
	private int y;
	private int ystep;
	private int z;

	public Line( Position start, Position end )
	{
		start = new Position( start );
		end = new Position( end );
		z = start.z;
		// System.out.println("new line st "+start+" end "+end);
		steep = Util.abs( end.y - start.y ) > Util.abs( end.x - start.x );
		if ( steep )
		{
			int tmp = start.x;
			start.x = start.y;
			start.y = tmp;
			tmp = end.x;
			end.x = end.y;
			end.y = tmp;
		}
		if ( start.x > end.x )
		{
			/*
			 * int tmp = start.x; start.x = end.x; end.x = tmp; tmp = start.y; start.y =
			 * end.y; end.y = tmp;
			 */
			xvar = -1;
			deltax = start.x - end.x;
		}
		else
		{
			deltax = end.x - start.x;
		}
		// deltax = end.x - start.x;
		deltay = Util.abs( end.y - start.y );
		error = 0;
		y = start.y;
		x = start.x;
		if ( start.y < end.y )
			ystep = 1;
		else
			ystep = -1;
	}

	public static void main( String[ ] args )
	{
		Position start = new Position( 10, 6 );
		Position end = new Position( 2, 4 );
		Line test = new Line( start, end );
		boolean[ ][ ] testM = new boolean[ 20 ][ 20 ];
		for ( int i = 0; i < 5; i++ )
		{
			Position next = test.next( );
			testM[ next.x ][ next.y ] = true;
		}
		System.out.print( "\n" );
		for ( int y = 0; y < testM[ 0 ].length; y++ )
		{
			for ( int x = 0; x < testM.length; x++ )
			{
				if ( testM[ x ][ y ] )
					System.out.print( "X" );
				else
					System.out.print( "." );
			}
			System.out.print( "\n" );
		}
	}

	public Position next( )
	{
		Position ret = null;
		if ( steep )
		{
			ret = new Position( y, x, z );
		}
		else
		{
			ret = new Position( x, y, z );
		}
		error = error + deltay;
		if ( 2 * error >= deltax )
		{
			y = y + ystep;
			error = error - deltax;
		}
		x += xvar;
		return ret;
	}

	public void setZ( int z )
	{
		this.z = z;
	}
}
