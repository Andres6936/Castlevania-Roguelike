package sz.util;

public class OutParameter
{
	private int intValue;
	private Object object;

	public int getIntValue( )
	{
		return intValue;
	}

	public Object getObject( )
	{
		return object;
	}

	public void setIntValue( int intValue )
	{
		this.intValue = intValue;
	}

	public void setObject( Object what )
	{
		object = what;
	}
}
