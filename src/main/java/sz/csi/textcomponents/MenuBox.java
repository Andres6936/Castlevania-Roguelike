package sz.csi.textcomponents;

import java.util.Vector;

import sz.csi.KeyCode;
import sz.csi.ConsoleSystemInterface;
import sz.util.Util;

public class MenuBox extends TextComponent
{

	// State Attributes
	private int currentPage;
	private Vector items;
	private boolean ordinal = true;

	private int pages;
	// Components
	private TextBox promptBox;

	private int promptSize;

	private String title = "";

	public MenuBox( ConsoleSystemInterface si )
	{
		super( si );
		promptBox = new TextBox( si );
	}

	public void draw( )
	{
		// pages = (int)(Math.floor((items.size()-1) / (inHeight-promptSize)) +1);
		pages = (int) ( Math.floor(
				( items.size( ) - 1 ) / (double) ( inHeight - promptSize ) ) + 1 );
		clearBox( );
		drawBorder( );
		if ( hasBorder( ) )
			si.print( position.x + 2, position.y, title );
		// promptBox.clear();
		promptBox.draw( );

		int pageElements = inHeight - promptSize;
		/*
		 * si.print(inPosition.x, inPosition.y-1+promptSize, "items.len"+items.size() +
		 * " PE "+pageElements+"CP"+currentPage+"pages"+pages); si.refresh();
		 * si.waitKey(CharKey.SPACE);
		 */
		Vector shownItems = Util.page( items, pageElements, currentPage );

		int i = 0;
		for ( ; i < shownItems.size( ); i++ )
		{
			MenuItem item = (MenuItem) shownItems.elementAt( i );
			if ( ordinal )
				si.print( inPosition.x, inPosition.y + i + promptSize,
						( (char) ( 97 + i ) ) + "." );
			si.print( inPosition.x + 2, inPosition.y + i + promptSize,
					item.getMenuChar( ), item.getMenuColor( ) );
			String description = item.getMenuDescription( );
			if ( description.length( ) > getWidth( ) - 5 )
			{
				description = description.substring( 0, getWidth( ) - 6 );
			}
			si.print( inPosition.x + 4, inPosition.y + i + promptSize, description );
		}
		// si.print(inPosition.x, inPosition.y, inHeight+" "+pageElements+" "+pages);
		/*
		 * for (; i < inHeight-promptSize; i++){ si.print(inPosition.x,
		 * inPosition.y+i+promptSize+1, spaces); }
		 */
		si.refresh( );
	}

	public void draw( boolean ordinal )
	{
		this.ordinal = ordinal;
		draw( );
		this.ordinal = true;
	}

	public Object getNullifiedSelection( int[ ] keys ) throws co.castle.ui.consoleUI.AdditionalKeysSignal
	{
		while ( true ) {
            clearBox();
            draw(false);
            KeyCode key = new KeyCode(KeyCode.NONE);
            while (key.code != KeyCode.SPACE && key.code != KeyCode.UARROW
                    && key.code != KeyCode.DARROW && !isOneOf(key.code, keys))
                key = si.inkey();
            if (key.code == KeyCode.SPACE)
                return null;
            if (key.code == KeyCode.UARROW)
                if (currentPage > 0)
                    currentPage--;
            if (key.code == KeyCode.DARROW)
                if (currentPage < pages - 1)
                    currentPage++;

			if ( isOneOf( key.code, keys ) )
				throw new co.castle.ui.consoleUI.AdditionalKeysSignal( key.code );

		}
	}

	public Object getSelection( )
	{
		int pageElements = inHeight - promptSize;
		while ( true ) {
            clearBox();
            draw();
            Vector shownItems = Util.page(items, pageElements, currentPage);
            KeyCode key = new KeyCode(KeyCode.NONE);
            while (key.code != KeyCode.SPACE && key.code != KeyCode.UARROW
                    && key.code != KeyCode.DARROW
                    && (key.code < KeyCode.A || key.code > KeyCode.A + pageElements - 1)
                    && (key.code < KeyCode.a
                    || key.code > KeyCode.a + pageElements - 1))
                key = si.inkey();
            if (key.code == KeyCode.SPACE)
                return null;
            if (key.code == KeyCode.UARROW)
                if (currentPage > 0)
                    currentPage--;
            if (key.code == KeyCode.DARROW)
                if (currentPage < pages - 1)
                    currentPage++;

            if (key.code >= KeyCode.A && key.code <= KeyCode.A + shownItems.size() - 1)
                return shownItems.elementAt(key.code - KeyCode.A);
            else if (key.code >= KeyCode.a
                    && key.code <= KeyCode.a + shownItems.size() - 1)
                return shownItems.elementAt(key.code - KeyCode.a);

        }
	}

	public Object getSelectionAKS( int[ ] keys ) throws co.castle.ui.consoleUI.AdditionalKeysSignal
	{
		int pageElements = inHeight - promptSize;
		while ( true ) {
            clearBox();
            draw();
            Vector shownItems = Util.page(items, pageElements, currentPage);
            KeyCode key = new KeyCode(KeyCode.NONE);
            while (key.code != KeyCode.SPACE && key.code != KeyCode.UARROW
                    && key.code != KeyCode.DARROW
                    && (key.code < KeyCode.A || key.code > KeyCode.A + pageElements - 1)
                    && (key.code < KeyCode.a || key.code > KeyCode.a + pageElements - 1)
                    && !isOneOf(key.code, keys))
                key = si.inkey();
            if (key.code == KeyCode.SPACE)
                return null;
            if (key.code == KeyCode.UARROW)
                if (currentPage > 0)
                    currentPage--;
            if (key.code == KeyCode.DARROW)
                if (currentPage < pages - 1)
                    currentPage++;

            if (key.code >= KeyCode.A && key.code <= KeyCode.A + shownItems.size() - 1)
                return shownItems.elementAt(key.code - KeyCode.A);
            else if (key.code >= KeyCode.a
                    && key.code <= KeyCode.a + shownItems.size() - 1)
                return shownItems.elementAt(key.code - KeyCode.a);
            if (isOneOf(key.code, keys))
                throw new co.castle.ui.consoleUI.AdditionalKeysSignal(key.code);

        }
	}

	public void setBorder( boolean val )
	{
		super.setBorder( val );
		promptBox.setWidth( inWidth );
		promptBox.setPosition( inPosition.x, inPosition.y );
	}

	public void setMenuItems( Vector items )
	{
		this.items = items;
	}

	public void setPosition( int x, int y )
	{
		super.setPosition( x, y );
		promptBox.setPosition( inPosition.x, inPosition.y + 1 );
	}

	public void setPrompt( String prompt )
	{
		promptBox.clear( );
		promptBox.setText( prompt );
	}

	public void setPromptSize( int size )
	{
		promptSize = size;
		promptBox.setHeight( size );
	}

	public void setTitle( String s )
	{
		title = s;
	}

	public void setWidth( int width )
	{
		super.setWidth( width );
		promptBox.setWidth( inWidth );
		promptBox.setPosition( inPosition.x, inPosition.y );
	}

	private boolean isOneOf( int value, int[ ] values )
	{
		for ( int i = 0; i < values.length; i++ )
		{
			if ( value == values[ i ] )
				return true;
		}
		return false;
	}
}