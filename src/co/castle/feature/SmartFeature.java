package co.castle.feature;

import co.castle.actor.Actor;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;

public class SmartFeature extends Actor implements Cloneable {
	private transient Appearance appearance;
	private int damageOnStep;
	private boolean destroyable;
	private String effectOnStep;
	private int height;

	private String ID;
	private String description;
	private final String appearanceID;

	public SmartFeature(String pID, String pDescription, Appearance pAppearance) {
		ID = pID;
		description = pDescription;
		appearance = pAppearance;
		appearanceID = pAppearance.getID();
	}

	public Object clone()
	{
		// try {
		SmartFeature x = (SmartFeature) super.clone( );
		x.setSelector( selector.derive( ) );
		return x;
		/*
		 * } catch (CloneNotSupportedException cnse){ Debug.doAssert(false,
		 * "failed class cast, Feature.clone()");
		 */
		// } */
		// return null;
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

	public String getEffectOnStep( )
	{
		return effectOnStep;
	}

	public int getHeight( )
	{
		return height;
	}

	public String getID( )
	{
		return ID;
	}

	public boolean isDestroyable( )
	{
		return destroyable;
	}

	public boolean isVisible( )
	{
		return !getAppearance( ).getID( ).equals( "VOID" );
	}

	public void setAppearance( Appearance value )
	{
		appearance = value;
	}

	public void setDamageOnStep( int value )
	{
		damageOnStep = value;
	}

	public void setDescription( String value )
	{
		description = value;
	}

	public void setDestroyable( boolean value )
	{
		destroyable = value;
	}

	public void setEffectOnStep( String value )
	{
		effectOnStep = value;
	}

	public void setHeight( int val )
	{
		height = val;
	}

	public void setID( String value )
	{
		ID = value;
	}
}