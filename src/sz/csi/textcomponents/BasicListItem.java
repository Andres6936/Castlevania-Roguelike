package sz.csi.textcomponents;

public class BasicListItem implements ListItem
{
	private char index;
	private int indexColor;
	private String rowData;

	public BasicListItem( char index, int indexColor, String data )
	{
		setRow( data );
		this.index = index;
		this.indexColor = indexColor;
	}

	public char getIndex( )
	{
		return index;
	}

	public int getIndexColor( )
	{
		return indexColor;
	}

	public String getRow( )
	{
		return rowData;
	}

	public void setIndex( char index )
	{
		this.index = index;
	}

	public void setIndexColor( int indexColor )
	{
		this.indexColor = indexColor;
	}

	public void setRow( String data )
	{
		rowData = data;
	}
}
