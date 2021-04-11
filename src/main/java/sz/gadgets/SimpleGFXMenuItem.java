package sz.gadgets;

import java.awt.Image;

public class SimpleGFXMenuItem implements GFXMenuItem
{
	private String description;
	private int value;

	public SimpleGFXMenuItem( String description, int value )
	{
		this.description = description;
		this.value = value;
	}

	public String getMenuDescription( )
	{
		return description;
	}

	public String getMenuDetail( )
	{
		return null;
	}

	public Image getMenuImage( )
	{
		return null;
	}

	public int getValue( )
	{
		return value;
	}

}
