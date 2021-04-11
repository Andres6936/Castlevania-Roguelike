package sz.csi.textcomponents;

import java.io.Serializable;

import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public abstract class TextComponent implements Serializable
{
	private boolean border;
	protected int borderColor = ConsoleSystemInterface.WHITE;
	protected int foreColor = ConsoleSystemInterface.WHITE;
	protected int height;

	protected int inHeight;

	protected Position inPosition;
	protected int inWidth;
	protected Position position = new Position( 0, 0 );

	protected transient ConsoleSystemInterface si;
	protected String spaces;
	protected int width;

	public TextComponent( ConsoleSystemInterface si )
	{
		this.si = si;
	}

	public void clearBox( )
	{
		for ( int i = 0; i <= inHeight; i++ )
			si.print( inPosition.x, inPosition.y + i, spaces );
	}

	public abstract void draw( );

	public void drawBorder( )
	{
		if ( !hasBorder( ) )
			return;
		for ( int x = position.x; x <= position.x + width; x++ )
		{
			si.print( x, position.y, '-', borderColor );
			si.print( x, position.y + height, '-', borderColor );
		}
		for ( int y = position.y; y <= position.y + height; y++ )
		{
			si.print( position.x, y, '|', borderColor );
			si.print( position.x + width, y, '|', borderColor );
		}

		si.print( position.x, position.y, '/', borderColor );
		si.print( position.x + width, position.y + height, '/', borderColor );
		si.print( position.x, position.y + height, '\\', borderColor );
		si.print( position.x + width, position.y, '\\', borderColor );
	}

	public int getForeColor( )
	{
		return foreColor;
	}

	public int getHeight( )
	{
		return height;
	}

	public int getWidth( )
	{
		return width;
	}

	public boolean hasBorder( )
	{
		return border;
	}

	public void setBorder( boolean value )
	{
		border = value;
		recalcInnerBounds( );
		spaces = "";
		for ( int i = 0; i <= inWidth; i++ )
			spaces += " ";
	}

	public void setBorderColor( int borderColor )
	{
		this.borderColor = borderColor;
	}

	public void setBounds( int x, int y, int width, int height )
	{
		setPosition( x, y );
		setWidth( width );
		setHeight( height );
	}

	public void setForeColor( int foreColor )
	{
		this.foreColor = foreColor;
	}

	public void setHeight( int value )
	{
		height = value;
		recalcInnerBounds( );
	}

	public void setPosition( int x, int y )
	{
		position.x = x;
		position.y = y;
		recalcInnerBounds( );
	}

	public void setWidth( int value )
	{
		width = value;
		recalcInnerBounds( );
		spaces = "";
		for ( int i = 0; i <= inWidth; i++ )
			spaces += " ";
	}

	private void recalcInnerBounds( )
	{
		if ( hasBorder( ) )
		{
			inPosition = new Position( position.x + 1, position.y + 1 );
			inWidth = width - 2;
			inHeight = height - 2;
		}
		else
		{
			inPosition = new Position( position.x, position.y );
			inWidth = width;
			inHeight = height;
		}
	}
}
