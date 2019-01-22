package sz.util;

import java.util.ArrayList;

public class Circle
{
	private Position center;
	private int radius;

	public Circle( Position p, int radius )
	{
		this.center = p;
		this.radius = radius;
	}

	public ArrayList getPoints( )
	{
		ArrayList ret = new ArrayList( );
		int d = 3 - ( 2 * radius );
		Position runner = new Position( 0, radius );
		Position zero = new Position( 0, 0 );
		while ( true )
		{
			// System.out.println("x "+x+" y "+y);
			if ( Position.flatDistance( zero, runner ) <= radius )
				addPoints( center, runner.x, runner.y, ret );
			if ( d < 0 )
				d = d + ( 4 * runner.x ) + 6;
			else
			{
				// d = d + 4 * (x-y) + 10;
				d = d + 4 * ( runner.x - runner.y ) + 10;
				runner.y--;
			}
			runner.x++;
			if ( runner.y == 0 )
				break;
		}
		return ret;
		// System.out.println("Circle finished");

	}

	private void addPoints( Position center, int x, int y, ArrayList collection )
	{
		collection.add( new Position( center.x + x, center.y + y ) );
		collection.add( new Position( center.x + x, center.y - y ) );
		collection.add( new Position( center.x - x, center.y + y ) );
		collection.add( new Position( center.x - x, center.y - y ) );
		collection.add( new Position( center.x + y, center.y + x ) );
		collection.add( new Position( center.x + y, center.y - x ) );
		collection.add( new Position( center.x - y, center.y + x ) );
		collection.add( new Position( center.x - y, center.y - x ) );
	}

}
