package co.castle.ui;

import co.castle.action.Action;

public class UserAction implements java.io.Serializable
{
	private Action action;
	/** Links an Action with a KeyCode with which it is triggered */
	private int keyCode;

	public UserAction( Action action, int key )
	{
		setKeyCode( key );
		setAction( action );
	}

	public Action getAction( )
	{
		return action;
	}

	public int getKeyCode( )
	{
		return keyCode;
	}

	public void setAction( Action value )
	{
		action = value;
	}

	public void setKeyCode( int value )
	{
		/*
		 * if (value<0 || value > 115) keyCode = 0; else
		 */
		keyCode = value;
	}
}