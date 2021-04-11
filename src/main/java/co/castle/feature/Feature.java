package co.castle.feature;

import co.castle.game.SFXManager;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public class Feature implements Cloneable, java.io.Serializable {
	private transient Appearance appearance;
	private int currentResistance; // How many blows til it gives the prize
	private boolean destroyable, isSolid;
	private String effect;
	private int faint;
	private int healPrize;
	private int heartPrize, mysticWeaponPrize = -1, keyPrize, upgradePrize;
	private int heightMod;
	private final String ID;
	private final String description;
	private final String appearanceID;
	private int keyCost;
	private final int light;
	private Position position;
	/**
	 * A feature is something that stays inside the level but may be moved,
	 * destroyed or otherwise affected.
	 */
	private Feature prize;
	private boolean relevant = true;
	private int scorePrize;
	private String trigger;

	public Feature(	String pID, Appearance pApp, int resistance, String pDescription,
					int faint, int light) {
		ID = pID;
		appearance = pApp;
		appearanceID = pApp.getID();
		// How many blows til it gives the prize (max)
		description = pDescription;
		currentResistance = resistance;
		this.faint = faint;
		this.light = light;
		// sightListItem = new BasicListItem(appearance.getChar(),
		// appearance.getColor(), description);
		Debug.doAssert(pApp != null, "No se especifico apariencia pa la featura" );
	}

	public Object clone( )
	{
		try
		{
			Feature x = (Feature) super.clone( );

			if ( position != null )
				x.setPosition( position.x, position.y, position.z );
			if ( prize != null )
				x.setPrize( (Feature) prize.clone( ) );
			return x;
		}
		catch ( CloneNotSupportedException cnse )
		{
			Debug.doAssert( false, "failed class cast, Feature.clone()" );
		}
		return null;
	}

	public void damage( Monster m )
	{
		currentResistance -= m.getAttack( );
		if ( currentResistance < 0 )
		{
			m.getLevel( ).destroyFeature( this );
		}
	}

	public Feature damage( Player p, int damage )
	{
		currentResistance -= damage;
		if ( currentResistance < 0 )
		{
			Feature pPrize = getPrizeFor( p );
			p.getLevel( ).destroyFeature( this );
			SFXManager.play( "wav/breakpot.wav" );
			if ( pPrize != null )
			{
				pPrize.setPosition( position.x, position.y, position.z );
				p.getLevel( ).addFeature( pPrize );
			}
			return pPrize;
		}
		return null;
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

	public String getDescription( )
	{
		return description;
	}

	public String getEffect( )
	{
		return effect;
	}

	public int getFaint( )
	{
		return faint;
	}

	public int getHealPrize( )
	{
		return healPrize;
	}

	public int getHeartPrize( )
	{
		return heartPrize;
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

	public int getKeyPrize( )
	{
		return keyPrize;
	}

	public int getLight( )
	{
		return light;
	}

	public int getMysticWeaponPrize( )
	{
		return mysticWeaponPrize;
	}

	public Position getPosition( )
	{
		return position;
	}

	public int getScorePrize( )
	{
		return scorePrize;
	}

	public String getTrigger( )
	{
		return trigger;
	}

	public int getUpgradePrize( )
	{
		return upgradePrize;
	}

	public boolean isDestroyable( )
	{
		return destroyable;
	}

	public boolean isRelevant( )
	{
		return relevant;
	}

	public boolean isSolid( )
	{
		return isSolid;
	}

	public boolean isVisible( )
	{
		return !getAppearance( ).getID( ).equals( "VOID" );
	}

	public void setDestroyable( boolean value )
	{
		destroyable = value;
	}

	public void setEffect( String value )
	{
		effect = value;
	}

	public void setFaint( int faint )
	{
		this.faint = faint;
	}

	public void setHealPrize( int value )
	{
		healPrize = value;
	}

	public void setHeartPrize( int value )
	{
		heartPrize = value;
	}

	public void setHeightMod( int value )
	{
		heightMod = value;
	}

	public void setKeyCost( int value )
	{
		keyCost = value;
	}

	public void setKeyPrize( int value )
	{
		keyPrize = value;
	}

	public void setMysticWeaponPrize( int value )
	{
		mysticWeaponPrize = value;
	}

	public void setPosition( int x, int y, int z )
	{
		position = new Position( x, y, z );
	}

	public void setPrize( Feature what )
	{
		prize = what;
	}

	public void setPrizesFor( Player p )
	{
		heartPrize = 0;
		mysticWeaponPrize = -1;
		upgradePrize = 0;

		if ( p.deservesUpgrade( ) )
			upgradePrize = 1;
	}

	public void setRelevant( boolean relevant )
	{
		this.relevant = relevant;
	}

	public void setScorePrize( int value )
	{
		scorePrize = value;
	}

	public void setSolid( boolean value )
	{
		isSolid = value;
	}

	public void setTrigger( String value )
	{
		trigger = value;
	}

	public void setUpgradePrize( int value )
	{
		upgradePrize = value;
	}

	private Feature getPrizeFor( Player p) {
		if (p.deservesUpgrade())
			return FeatureFactory.getFactory().buildFeature("UPGRADE");

		String[] prizeList;

		if (p.getPlayerClass() == Player.CLASS_VAMPIREKILLER) {
			if (Util.chance(10)) {
				// Will get a mystic weapon
				if (p.getFlag("MYSTIC_CRYSTAL") && Util.chance(50))
					prizeList = new String[]
							{"CRYSTALWP"};
				else if (p.getFlag("MYSTIC_FIST") && Util.chance( 50 ) )
					prizeList = new String[ ]
					{ "FISTWP" };
				else if ( p.getFlag( "MYSTIC_CROSS" ) && Util.chance( 50 ) )
					prizeList = new String[ ]
					{ "CROSSWP" };
				else if ( p.getFlag( "MYSTIC_STOPWATCH" ) && Util.chance( 50 ) )
					prizeList = new String[ ]
					{ "STOPWATCHWP" };
				else if ( p.getFlag( "MYSTIC_HOLY_WATER" ) && Util.chance( 50 ) )
					prizeList = new String[ ]
					{ "HOLYWP" };
				else if ( p.getFlag( "MYSTIC_HOLY_BIBLE" ) && Util.chance( 50 ) )
					prizeList = new String[ ]
					{ "BIBLEWP" };
				else
					prizeList = new String[ ]
					{ "AXEWP", "DAGGERWP" };
			}
			else if ( Util.chance( 40 ) )
				if ( Util.chance( 30 ) )
					if ( Util.chance( 10 ) )
						if ( Util.chance( 10 ) )
							if ( Util.chance( 10 ) )
								prizeList = new String[ ]
								{ "WHITE_MONEY_BAG" };
							else
								prizeList = new String[ ]
								{ "POT_ROAST" };
						else
							prizeList = new String[ ]
							{ "INVISIBILITY_POTION", "ROSARY", "BLUE_MONEY_BAG" };
					else
						prizeList = new String[ ]
						{ "RED_MONEY_BAG" };
				else
					prizeList = new String[ ]
					{ "BIGHEART" };
			else
				prizeList = new String[ ]
				{ "SMALLHEART" };
		}
		else
		{
			if ( Util.chance( 50 ) )
				if ( Util.chance( 40 ) )
					if ( Util.chance( 10 ) )
						if ( Util.chance( 10 ) )
							if ( Util.chance( 10 ) )
								prizeList = new String[ ]
								{ "WHITE_MONEY_BAG" };
							else
								prizeList = new String[ ]
								{ "POT_ROAST" };
						else
							prizeList = new String[ ]
							{ "INVISIBILITY_POTION", "ROSARY", "BLUE_MONEY_BAG" };
					else
						prizeList = new String[ ]
						{ "RED_MONEY_BAG" };
				else
					prizeList = new String[ ]
					{ "BIGHEART" };
			else
				prizeList = new String[ ]
				{ "SMALLHEART" };
		}
		// return FeatureFactory.getFactory().buildFeature("ROSARY");
		if ( prizeList != null )
			return FeatureFactory.getFactory( )
					.buildFeature( Util.randomElementOf( prizeList ) );
		else
			return null;
	}
}