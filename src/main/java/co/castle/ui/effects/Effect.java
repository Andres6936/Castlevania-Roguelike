package co.castle.ui.effects;

import sz.util.Position;

public abstract class Effect
{
	private String id;
	private Position position;

	protected int animationDelay = 50;

	public Effect( String id )
	{
		this.id = id;
	}

	public Effect( String id, int delay )
	{
		this.id = id;
		animationDelay = delay;
	}

	public String getID( )
	{
		return id;
	}

	public Position getPosition( )
	{
		return position;
	}

	// public abstract void drawEffect(UserInterface ui, ConsoleSystemInterface si);

	public void set( Position loc )
	{
		setPosition( loc );
	}

	public void setAnimationDelay( int value )
	{
		animationDelay = value;
	}

	public void setPosition( Position value )
	{
		position = value;
	}

	protected final void animationPause( )
	{
		try
		{
			Thread.sleep( animationDelay );
		}
		catch ( Exception e )
		{
		}
	}
}