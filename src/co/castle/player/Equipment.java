package co.castle.player;

import java.awt.Image;

import co.castle.item.Item;
import co.castle.ui.consoleUI.CharAppearance;
import co.castle.ui.graphicsUI.GFXAppearance;
import sz.csi.textcomponents.MenuItem;
import sz.gadgets.GFXMenuItem;

public class Equipment implements MenuItem, GFXMenuItem
{
	private Item item;
	private int quantity;

	public static boolean eqMode = false;

	public static boolean menuDetail = false;

	public Equipment( Item pItem, int pQuantity )
	{
		item = pItem;
		quantity = pQuantity;
	}
	public Item getItem( )
	{
		return item;
	}

	/* Unsafe, Coupled */
	public char getMenuChar( )
	{
		return ( (CharAppearance) item.getAppearance( ) ).getChar( );
	}

	/* Unsafe, Coupled */
	public int getMenuColor( )
	{
		return ( (CharAppearance) item.getAppearance( ) ).getColor( );
	}

	public String getMenuDescription( )
	{
		if ( quantity == 1 )
		{
			return item.getAttributesDescription( );
		}
		else
		{
			return item.getAttributesDescription( ) + " x" + quantity;
		}
		// if (eqMode)
		// return item.getAttributesDescription() +" x"+quantity+ "
		// ["+item.getDefinition().getMenuDescription()+"]";
	}

	public String getMenuDetail( )
	{
		if ( menuDetail )
			return "  " + item.getDefinition( ).getMenuDescription( );
		else
			return null;
	}

	public Image getMenuImage( )
	{
		return ( (GFXAppearance) item.getAppearance( ) ).getImage( );
	}

	public int getQuantity( )
	{
		return quantity;
	}

	public void increaseQuantity( )
	{
		quantity++;
	}

	public boolean isEmpty( )
	{
		return quantity == 0;
	}

	public void reduceQuantity( )
	{
		quantity--;
	}

	public void setQuantity( int value )
	{
		quantity = value;
	}
}