package sz.util;

import java.io.Serializable;

public class Counter implements Serializable
{

	private int value;

	public Counter( int initialCoun )
	{
		value = initialCoun;
	}

	public int getCount( )
	{
		return value;
	}

	public void increase( )
	{
		value++;
	}

	public boolean isOver( )
	{
		return value < 0;
	}

	public void reduce( )
	{
		value--;
	}

	public void reset( )
	{
		value = 0;
	}
}