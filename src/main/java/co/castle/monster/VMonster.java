package co.castle.monster;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

import sz.util.Position;

public class VMonster implements java.io.Serializable
{
	private Vector <Monster> monsters;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8389021604207397015L;

	public VMonster( int size )
	{
		monsters = new Vector <Monster>( size );
	}

	public void addMonster( Monster what )
	{
		monsters.add( what );
		// Debug.say(what.getPosition().toString());
		// mLocs.put(what.getPosition().toString(), what);
	}

	public boolean contains( Monster who )
	{
		return monsters.contains( who );
	}

	public Monster elementAt( int i )
	{
		return (Monster) monsters.elementAt( i );
	}

	public Enumeration <Monster> elements( )
	{
		return monsters.elements( );
	}

	public Monster getMonsterAt( Position p )
	{
		for ( int i = 0; i < monsters.size( ); i++ )
			if ( ( (Monster) monsters.elementAt( i ) ).getPosition( ).equals( p ) )
				return (Monster) monsters.elementAt( i );
		return null;
	}

	public Vector <Monster> getVector( )
	{
		return monsters;
	}

	public void remove( Object o )
	{
		monsters.remove( o );
	}

	public void removeAll( )
	{
		monsters.removeAllElements( );
	}

	public void removeAll( Collection <Monster> c )
	{
		monsters.removeAll( c );
	}

	public int size( )
	{
		return monsters.size( );
	}

}