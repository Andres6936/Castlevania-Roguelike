package co.castle.ui.effects;

import java.util.Vector;

public class VEffect
{
	Vector <Effect> effects;

	public VEffect( int size )
	{
		effects = new Vector <Effect>( size );
	}

	public void addEffect( Effect what )
	{
		effects.add( what );
	}

	public void erase( )
	{
		effects = new Vector <Effect>( effects.capacity( ) );
	}

	public Effect getEffect( int index )
	{
		return (Effect) effects.elementAt( index );
	}

	public int size( )
	{
		return effects.size( );
	}

}