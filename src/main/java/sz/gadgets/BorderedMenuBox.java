package sz.gadgets;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import co.castle.main.ApplicationGraphics;
import sz.csi.CharKey;
import sz.util.Util;

public class BorderedMenuBox
{

	private Color backgroundColor;
	private BufferedImage border1, border2, border3, border4, box;

	private Color borderIn;
	private Color borderOut;

	// State Attributes
	private int currentPage;;
	private int gap = 24;
	private int inset;

	private Vector items;

	private int pages;

	private ApplicationGraphics si;

	private String title = "";

	// Components
	private int xpos, ypos, width, itemsPerPage;

	/*
	 * private static Color TRANSPARENT_BLUE = new Color(0,0,0,250); private static
	 * Color COLOR_BORDER_IN = new Color(160,160,160); private static Color
	 * COLOR_BORDER_OUT = new Color(80,80,255);
	 */

	/* UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT */
	public BorderedMenuBox(	BufferedImage border1, BufferedImage border2,
							BufferedImage border3, BufferedImage border4,
							ApplicationGraphics g, Color backgroundColor, Color borderIn,
							Color borderOut, int inset, BufferedImage box )
	{
		this.backgroundColor = backgroundColor;
		this.borderIn = borderIn;
		this.borderOut = borderOut;
		this.border1 = border1;
		this.border2 = border2;
		this.border3 = border3;
		this.border4 = border4;
		this.inset = inset;
		this.si = g;
		this.box = box;
	}
	public void draw( )
	{
		int realW = width * 10 + 20;
		int realH = ( itemsPerPage + 1 ) * gap + 20;
		int realPosX = xpos * 10 - 20;
		int realPosY = ypos * 24 - 30;

		si.getGraphics2D( ).setColor( backgroundColor );
		si.getGraphics2D( ).fillRect( realPosX + 6, realPosY + 6, realW - 14,
				realH - 14 );
		si.getGraphics2D( ).setColor( borderOut );
		si.getGraphics2D( ).drawRect( realPosX + 6, realPosY + 6, realW - 14,
				realH - 14 );
		si.getGraphics2D( ).setColor( borderIn );
		si.getGraphics2D( ).drawRect( realPosX + 8, realPosY + 8, realW - 18,
				realH - 18 );
		/*
		 * si.getGraphics2D().drawImage(borders[0], 0,0, null);
		 * si.getGraphics2D().drawImage(borders[1], realW-inset,0, null);
		 * si.getGraphics2D().drawImage(borders[2], 0, realH - inset,null);
		 * si.getGraphics2D().drawImage(borders[3], realW -inset, realH - inset,null);
		 */
		si.drawImage( realPosX, realPosY, border2 );
		si.drawImage( realPosX + realW - inset, realPosY, border1 );
		si.drawImage( realPosX + 0, realPosY + realH - inset, border4 );
		si.drawImage( realPosX + realW - inset, realPosY + realH - inset, border3 );

		// pages = (int)(Math.floor((items.size()-1) / inHeight) +1);
		pages = (int) ( Math.floor( ( items.size( ) - 1 ) / (double) ( itemsPerPage ) )
				+ 1 );
		/*
		 * System.out.println("items.size() "+items.size());
		 * System.out.println("inHeight "+inHeight);
		 */
		si.print( xpos, ypos, title, Color.BLUE );
		Vector shownItems = Util.page( items, itemsPerPage, currentPage );

		int i = 0;
		for ( ; i < shownItems.size( ); i++ )
		{

			GFXMenuItem item = (GFXMenuItem) shownItems.elementAt( i );
			si.printAtPixel( xpos * 10, ( ypos + 1 ) * 24 + i * gap,
					( (char) ( 97 + i ) ) + ".", Color.BLUE );
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
		while ( true )
		{

			draw( );
			Vector shownItems = Util.page( items, pageElements, currentPage );
			CharKey key = new CharKey( CharKey.NONE );
			while ( key.code != CharKey.SPACE && key.code != CharKey.UARROW
					&& key.code != CharKey.DARROW && key.code != CharKey.N8
					&& key.code != CharKey.N2
					&& ( key.code < CharKey.A || key.code > CharKey.A + pageElements - 1 )
					&& ( key.code < CharKey.a
							|| key.code > CharKey.a + pageElements - 1 ) )
				key = si.inkey( );
			if ( key.code == CharKey.SPACE )
				return null;
			if ( key.code == CharKey.UARROW || key.code == CharKey.N8 )
				if ( currentPage > 0 )
					currentPage--;
			if ( key.code == CharKey.DARROW || key.code == CharKey.N2 )
				if ( currentPage < pages - 1 )
					currentPage++;

			if ( key.code >= CharKey.A && key.code <= CharKey.A + shownItems.size( ) - 1 )
				return shownItems.elementAt( key.code - CharKey.A );
			else if ( key.code >= CharKey.a
					&& key.code <= CharKey.a + shownItems.size( ) - 1 )
				return shownItems.elementAt( key.code - CharKey.a );
			si.restore( );

		}
	}
	public Object getSelectionAKS( int[ ] keys ) throws AdditionalKeysSignal
	{
		int pageElements = itemsPerPage;
		while ( true )
		{

			draw( );
			Vector shownItems = Util.page( items, pageElements, currentPage );
			CharKey key = new CharKey( CharKey.NONE );
			while ( key.code != CharKey.SPACE && key.code != CharKey.UARROW
					&& key.code != CharKey.DARROW && key.code != CharKey.N8
					&& key.code != CharKey.N2
					&& ( key.code < CharKey.A || key.code > CharKey.A + pageElements - 1 )
					&& ( key.code < CharKey.a || key.code > CharKey.a + pageElements - 1 )
					&& !isOneOf( key.code, keys ) )
				key = si.inkey( );
			if ( key.code == CharKey.SPACE )
				return null;
			if ( key.code == CharKey.UARROW || key.code == CharKey.N8 )
				if ( currentPage > 0 )
					currentPage--;
			if ( key.code == CharKey.DARROW || key.code == CharKey.N2 )
				if ( currentPage < pages - 1 )
					currentPage++;

			if ( key.code >= CharKey.A && key.code <= CharKey.A + shownItems.size( ) - 1 )
				return shownItems.elementAt( key.code - CharKey.A );
			else if ( key.code >= CharKey.a
					&& key.code <= CharKey.a + shownItems.size( ) - 1 )
				return shownItems.elementAt( key.code - CharKey.a );
			if ( isOneOf( key.code, keys ) )
				throw new AdditionalKeysSignal( key.code );
			si.restore( );

		}
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