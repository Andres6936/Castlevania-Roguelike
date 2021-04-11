package co.castle.ui;

import co.castle.actor.Actor;

public class RefreshActor extends Actor
{
	private int opCode;

	public final static int REFRESHVIEWPORT = 1, NONE = 0;

	/**
	 * This actor stays in the game queue and operates on the UI
	 */

	private static RefreshActor singleton = new RefreshActor( 0 );
	/*
	 * { singleton.setSelector(new DumbSelector());
	 */

	public RefreshActor( int pCode )
	{
		opCode = pCode;
	}

	public static RefreshActor getRefreshActor( int code )
	{
		singleton.setOpCode( code );
		return singleton;
	}

	public void act( )
	{
		switch ( opCode )
		{
		case REFRESHVIEWPORT:
			// UserInterface.getUI().refresh();
			// System.out.println("Refreshing!");
		}
		level.removeActor( this );
	}

	public int getOpCode( )
	{
		return opCode;
	}

	public void setOpCode( int code )
	{
		opCode = code;
	}
}