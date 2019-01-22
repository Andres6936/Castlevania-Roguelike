package co.castle.item;

import co.castle.ui.consoleUI.CharAppearance;
import sz.csi.textcomponents.MenuItem;

//TODO: Delete this no longer used class
public class ShopMenuItem implements MenuItem
{
	private String defId;
	private transient Item x;

	public ShopMenuItem( Item x )
	{
		this.x = x;
		defId = x.getFullID( );
	}

	public Item getItem( )
	{
		return x;
	}

	/* Unsafe, Coupled */
	public char getMenuChar( )
	{
		return ( (CharAppearance) getItem( ).getAppearance( ) ).getChar( );
	}

	/* Unsafe, Coupled */
	public int getMenuColor( )
	{
		return ( (CharAppearance) getItem( ).getAppearance( ) ).getColor( );
	}

	public String getMenuDescription( )
	{
		return getItem( ).getAttributesDescription( ) + " : "
				+ getItem( ).getMenuDescription( ) + " ($" + getItem( ).getGoldPrice( )
				+ ")";
	}
}