package co.castle.actor;

import sz.util.Position;

public class Message
{
	private Position location;
	private String text;

	/*
	 * public void act(){ die(); }
	 */

	public Message( String pText, Position pLocation )
	{
		text = pText;
		location = pLocation;
	}

	public Position getLocation( )
	{
		return location;
	}

	public String getText( )
	{
		return text;
	}

	public String toString( )
	{
		return getText( );
	}
}