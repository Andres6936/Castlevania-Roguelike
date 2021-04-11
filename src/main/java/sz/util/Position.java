package sz.util;

public class Position implements java.io.Serializable
{
	public int x, y, z;

	public Position( int px, int py )
	{
		x = px;
		y = py;
	}

	public Position( int px, int py, int pz )
	{
		this( px, py );
		z = pz;
	}

	public Position( Position p )
	{
		x = p.x;
		y = p.y;
		z = p.z;
	}

	public static Position add( Position a, Position b )
	{
		return new Position( a.x + b.x, a.y + b.y, a.z + b.z );
	}

	public static int distance( Position a, Position b )
	{
		return (int) Math.sqrt( Math.pow( a.x - b.x, 2 ) + Math.pow( a.y - b.y, 2 ) );
	}

	public static int flatDistance( int x1, int y1, int x2, int y2 )
	{
		return (int) Math.sqrt( Math.pow( x1 - x2, 2 ) + Math.pow( y1 - y2, 2 ) );
	}

	public static int flatDistance( Position a, Position b )
	{
		return (int) Math.sqrt( Math.pow( a.x - b.x, 2 ) + Math.pow( a.y - b.y, 2 ) );
	}

	public static Position mul( Position a, int c )
	{
		return new Position( a.x * c, a.y * c, a.z * c );
	}

	public static Position mul( Position a, Position b )
	{
		return new Position( a.x * b.x, a.y * b.y, a.z * b.z );
	}

	public static Position subs( Position a, Position b )
	{
		return new Position( a.x - b.x, a.y - b.y, a.z - b.z );
	}

	public void add( Position p )
	{
		x += p.x;
		y += p.y;
		z += p.z;

	}

	public boolean equals( Object o )
	{
		if ( o == null )
			return false;
		try
		{
			if ( ( (Position) o ).x == x && ( (Position) o ).y == y
					&& ( (Position) o ).z == z )
			{
				return true;
			}
		}
		catch ( ClassCastException cce )
		{
			Debug.byebye( "Error comparing points " + this + " " + o );
		}
		return false;
	}

	public int hashCode( )
	{
		return toString( ).hashCode( );
	}

	public void mul( Position pos )
	{
		x *= pos.x;
		y *= pos.y;
		z *= pos.z;
	}

	public String toString( )
	{
		return "(" + x + "," + y + "," + z + ")";
	}

	public int x( )
	{
		return x;
	}

	public int y( )
	{
		return y;
	}

	/*
	 * public static int distance(Position a, Position b){ return (int)
	 * Math.s(Math.pow(a.x - b.x, 2) + Math.pow (a.y - b.y, 2)); }
	 */

	public int z( )
	{
		return z;
	}

}