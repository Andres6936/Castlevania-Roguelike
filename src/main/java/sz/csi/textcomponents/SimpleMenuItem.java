package sz.csi.textcomponents;

import sz.csi.ConsoleSystemInterface;

public class SimpleMenuItem implements MenuItem
{
	private String description;
	private char number;
	private int value;

	public SimpleMenuItem( char number, String description, int value )
	{
		this.number = number;
		this.description = description;
		this.value = value;
	}

	public char getMenuChar( )
	{
		return number;
	}

	public int getMenuColor( )
	{
		return ConsoleSystemInterface.WHITE;
	}

	public String getMenuDescription( )
	{
		return description;
	}

	public int getValue( )
	{
		return value;
	}

}
