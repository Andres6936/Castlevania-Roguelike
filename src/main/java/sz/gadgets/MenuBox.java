package sz.gadgets;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import co.castle.main.ApplicationGraphics;
import sz.csi.KeyCode;
import sz.util.Util;

public class MenuBox
{

	private BufferedImage box;
	// State Attributes
	private int currentPage;

	private int gap = 24;
	private Vector items;

	private int pages;;
	private ApplicationGraphics si;
	private String title = "";

	// Components
	private int xpos, ypos, width, itemsPerPage;

	boolean ordinal = false;

	private static Color COLOR_BORDER_IN = new Color( 160, 160, 160 );

	private static Color COLOR_BORDER_OUT = new Color( 80, 80, 255 );

	private static Color TRANSPARENT_BLUE = new Color( 0, 0, 0, 250 );

	public MenuBox( ApplicationGraphics g, BufferedImage box )
	{
		this.si = g;
		this.box = box;
	}
	public void draw( )
	{
		int realW = width * 10 + 20;
		int realH = ( itemsPerPage + 1 ) * gap + 20;
		int realPosX = xpos * 10 - 20;
		int realPosY = ypos * 24 - 30;

		si.getGraphics2D( ).setColor( TRANSPARENT_BLUE );
		si.getGraphics2D( ).fillRect( realPosX + 6, realPosY + 6, realW - 14,
				realH - 14 );
		si.getGraphics2D( ).setColor( COLOR_BORDER_OUT );
		si.getGraphics2D( ).drawRect( realPosX + 6, realPosY + 6, realW - 14,
				realH - 14 );
		si.getGraphics2D( ).setColor( COLOR_BORDER_IN );
		si.getGraphics2D( ).drawRect( realPosX + 8, realPosY + 8, realW - 18,
				realH - 18 );

		// pages = (int)(Math.floor((items.size()-1) / inHeight) +1);
		pages = (int) ( Math.floor( ( items.size( ) - 1 ) / ( itemsPerPage ) ) + 1 );
		/*
		 * System.out.println("items.size() "+items.size());
		 * System.out.println("inHeight "+inHeight);
		 */
		si.print( xpos, ypos, title, Color.BLUE );
		Vector shownItems = Util.page( items, itemsPerPage, currentPage );

		if ( ordinal )
		{
			xpos -= 2;
		}

		int i = 0;
		for ( ; i < shownItems.size( ); i++ )
		{

			GFXMenuItem item = (GFXMenuItem) shownItems.elementAt( i );
			if ( !ordinal )
			{
				si.printAtPixel( xpos * 10, ( ypos + 1 ) * 24 + i * gap,
						( (char) ( 97 + i ) ) + ".", Color.BLUE );
			}
			if ( box != null )
			{
				si.drawImage( ( xpos + 2 ) * 10 + 1,
						ypos * 24 + i * gap + (int) ( gap * 0.3D ) - 4, box );
			}
			if ( item.getMenuImage( ) != null )
				si.drawImage( ( xpos + 2 ) * 10 + 5,
						ypos * 24 + i * gap + (int) ( gap * 0.3D ),
						item.getMenuImage( ) );
			String description = item.getMenuDescription( );
			if ( description.length( ) > width - 2 )
			{
				description = description.substring( 0, width - 4 );
			}
			String detail = item.getMenuDetail( );
			if ( detail != null && detail.length( ) > width - 2 )
			{
				detail = detail.substring( 0, width - 4 );
			}
			si.printAtPixel( ( xpos + 6 ) * 10, ( ypos + 1 ) * 24 + i * gap, description,
					Color.WHITE );
			if ( detail != null && !detail.equals( "" ) )
			{
				si.printAtPixel( ( xpos + 6 ) * 10, ( ypos + 1 ) * 24 + i * gap + 18,
						detail, Color.WHITE );
			}
		}
		ordinal = false;
		// si.print(inPosition.x, inPosition.y, inHeight+" "+pageElements+" "+pages);
		/*
		 * for (; i < inHeight-promptSize; i++){ si.print(inPosition.x,
		 * inPosition.y+i+promptSize+1, spaces); }
		 */
		si.refresh( );
	}
	public Object getSelection( )
	{
		int pageElements = itemsPerPage;
		while ( true ) {

			draw();
			Vector shownItems = Util.page(items, pageElements, currentPage);
			KeyCode key = new KeyCode(KeyCode.NONE);
			while (key.code != KeyCode.SPACE && key.code != KeyCode.UARROW
					&& key.code != KeyCode.DARROW && key.code != KeyCode.N8
					&& key.code != KeyCode.N2
					&& (key.code < KeyCode.A || key.code > KeyCode.A + pageElements - 1)
					&& (key.code < KeyCode.a
					|| key.code > KeyCode.a + pageElements - 1))
				key = si.getKeyPressed();
			if (key.code == KeyCode.SPACE)
				return null;
			if (key.code == KeyCode.UARROW || key.code == KeyCode.N8)
				if (currentPage > 0)
					currentPage--;
			if (key.code == KeyCode.DARROW || key.code == KeyCode.N2)
				if (currentPage < pages - 1)
					currentPage++;

			if (key.code >= KeyCode.A && key.code <= KeyCode.A + shownItems.size() - 1)
				return shownItems.elementAt(key.code - KeyCode.A);
			else if (key.code >= KeyCode.a
					&& key.code <= KeyCode.a + shownItems.size() - 1)
				return shownItems.elementAt(key.code - KeyCode.a);
			si.restore();

		}
	}

	public Object getUnpagedOrdinalSelectionAKS( int[ ] keys ) throws AdditionalKeysSignal {
		ordinal = true;
		draw();
		KeyCode key = new KeyCode(KeyCode.NONE);
		while (key.code != KeyCode.SPACE && !isOneOf(key.code, keys))
			key = si.getKeyPressed();
		if (key.code == KeyCode.SPACE)
			return null;
		if (isOneOf(key.code, keys))
			throw new AdditionalKeysSignal(key.code);
		return null;

	}

	public Object getUnpagedSelection( ) {
		int pageElements = itemsPerPage;
		draw();
		Vector shownItems = Util.page(items, pageElements, currentPage);
		KeyCode key = new KeyCode(KeyCode.NONE);
		while (key.code != KeyCode.SPACE
				&& (key.code < KeyCode.A || key.code > KeyCode.A + pageElements - 1)
				&& (key.code < KeyCode.a || key.code > KeyCode.a + pageElements - 1))
			key = si.getKeyPressed();
		if (key.code == KeyCode.SPACE)
			return null;
		if (key.code >= KeyCode.A && key.code <= KeyCode.A + shownItems.size() - 1)
			return shownItems.elementAt(key.code - KeyCode.A);
		else if (key.code >= KeyCode.a
				&& key.code <= KeyCode.a + shownItems.size() - 1)
			return shownItems.elementAt(key.code - KeyCode.a);
		return null;

	}

	public void setBounds( int x, int y, int width, int height )
	{
		this.xpos = x;
		this.ypos = y;
		this.width = width;
		this.itemsPerPage = height;
	}

	public void setGap( int val )
	{
		gap = val;
	}

	public void setItemsPerPage( int ipp )
	{
		itemsPerPage = ipp;
	}

	public void setMenuItems( Vector items )
	{
		this.items = items;
	}

	public void setPosition( int x, int y )
	{
		xpos = x;
		ypos = y;
	}

	public void setTitle( String s )
	{
		title = s;
	}

	public void setWidth( int width )
	{
		this.width = width;
	}

	protected boolean isOneOf( int value, int[ ] values )
	{
		for ( int i = 0; i < values.length; i++ )
		{
			if ( value == values[ i ] )
				return true;
		}
		return false;
	}
}