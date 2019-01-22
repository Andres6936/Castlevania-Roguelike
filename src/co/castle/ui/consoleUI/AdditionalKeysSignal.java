package co.castle.ui.consoleUI;

public class AdditionalKeysSignal extends Exception
{
	private int keycode;

	public AdditionalKeysSignal( int keycode )
	{
		setKeycode( keycode );
	}

	public int getKeyCode( )
	{
		return keycode;
	}

	public void setKeycode( int keycode )
	{
		this.keycode = keycode;
	}
}
