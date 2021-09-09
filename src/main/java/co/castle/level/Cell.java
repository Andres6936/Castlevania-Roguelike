package co.castle.level;

import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import sz.util.Debug;

public class Cell implements Cloneable, java.io.Serializable {
	private transient Appearance appearance;
	private String appearanceID;
	private int damageOnStep;
	private final String description;
	private final String shortDescription;
	private boolean ethereal;
	private int height;
	private int heightMod;
	private final String ID;

	private boolean isStair, isSolid, isWater, isOpaque, shallowWater;
	private int keyCost;

	public final static int DOOR = 0;

	public Cell(String pID, String pShortDescription, String pDescription,
				Appearance pApp) {
		ID = pID;
		appearance = pApp;
		appearanceID = pApp.getSerial();
		description = pDescription;
		shortDescription = pShortDescription;
		Debug.doAssert( pApp != null, "No se especifico apariencia pa la celda" );
	}

	public Cell(	String pID, String sdes, String pDescription, Appearance pApp,
					boolean isSolid, boolean isOpaque )
	{
		this( pID, sdes, pDescription, pApp );
		this.isSolid = isSolid;
		this.isOpaque = isOpaque;
	}

	public Appearance getAppearance( )
	{
		if ( appearance == null )
		{
			if ( appearanceID != null )
				appearance = AppearanceFactory.getAppearanceFactory( )
						.getAppearance( appearanceID );
		}
		return appearance;
	}

	public int getDamageOnStep( )
	{
		return damageOnStep;
	}

	public String getDescription( )
	{
		return description;
	}

	public int getHeight( )
	{
		return height;
	}

	public int getHeightMod( )
	{
		return heightMod;
	}

	public String getID( )
	{
		return ID;
	}

	public int getKeyCost( )
	{
		return keyCost;
	}

	public String getShortDescription( )
	{
		return shortDescription;
	}

	public boolean isEthereal( )
	{
		return ethereal;
	}

	public boolean isOpaque( )
	{
		return isOpaque;
	}

	public boolean isShallowWater( )
	{
		return shallowWater;
	}

	public boolean isSolid( )
	{
		return isSolid;
	}

	public boolean isStair( )
	{
		return isStair;
	}

	public boolean isWater( )
	{
		return isWater;
	}

	public void setAppearance( Appearance what )
	{
		appearanceID = what.getSerial();
		appearance = what;
	}

	public void setDamageOnStep( int damageOnStep )
	{
		this.damageOnStep = damageOnStep;
	}

	public void setEthereal( boolean ethereal )
	{
		this.ethereal = ethereal;
	}

	public void setHeight( int value )
	{
		height = value;
	}

	public void setHeightMod( int heightMod )
	{
		this.heightMod = heightMod;
	}

	public void setIsStair( boolean value )
	{
		isStair = value;
	}

	public void setKeyCost( int value )
	{
		keyCost = value;
	}

	public void setShallowWater( boolean shallowWater )
	{
		this.shallowWater = shallowWater;
	}

	public void setWater( boolean what )
	{
		isWater = what;
	}
}