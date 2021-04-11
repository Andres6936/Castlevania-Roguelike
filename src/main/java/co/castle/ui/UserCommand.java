package co.castle.ui;

public class UserCommand implements java.io.Serializable
{
	private int command;
	/** Links a Command with a KeyCode with which it is triggered */
	private int keyCode;

	public UserCommand( int command, int keycode )
	{
		this.command = command;
		setKeyCode( keycode );
	}

	public int getCommand( )
	{
		return command;
	}

	public int getKeyCode( )
	{
		return keyCode;
	}

	private void setKeyCode( int value )
	{
		if ( value < 0 || value > 115 )
			keyCode = 0;
		else
			keyCode = value;
	}
}