package co.castle.level;

import java.util.Vector;

import co.castle.actor.Actor;
import sz.util.SZPriorityQueue;

public class Dispatcher implements java.io.Serializable {
	private final SZPriorityQueue actors;
	private int countdown;
	private Actor fixed;

	public Dispatcher() {
		actors = new SZPriorityQueue();
	}

	public void addActor(Actor what) {
		if ( !actors.contains( what ) )
			actors.enqueue( what );
	}

	public void addActor( Actor what, boolean high )
	{
		if ( !actors.contains( what ) )
			actors.forceToFront( what );
	}

	public void addActor( Actor what, boolean high, Object classObj )
	{
		if ( !actors.contains( what ) )
			actors.forceToFront( what, classObj );
	}

	public boolean contains( Actor what )
	{
		return actors.contains( what );
	}

	public Vector getActors( )
	{
		return actors.getVector( );
	}

	public Actor getNextActor( )
	{
		// Debug.say("---"+(ixx++)+"--------");
		if ( countdown > 0 )
		{
			countdown--;
			return fixed;
		}

		// actors.printStatus();
		Actor x = (Actor) actors.unqueue( );
		// Debug.say(x);

		while ( x != null && x.wannaDie( ) )
		{
			actors.remove( x );
			x = (Actor) actors.unqueue( );
		}
		// actors.enqueue(x);
		return x;
	}

	public void removeActor( Actor what )
	{
		actors.remove( what );
	}

	public void removeAll( )
	{
		actors.removeAll( );
	}

	public void returnActor( Actor what )
	{
		if ( !actors.contains( what ) )
			actors.enqueue( what );
	}

	public void setFixed( Actor who, int howMuch )
	{
		countdown = howMuch;
		fixed = who;
	}
}