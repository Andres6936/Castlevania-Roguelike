package co.castle.item;

import java.util.Hashtable;

import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;

public class ItemDefinition
{
	// Weapon Categories

	private Appearance appearance;

	private int attack;

	private int attackCost;

	private String attackSFX;

	private String attackSound;

	private int coolness;
	/*
	 * private ListItem sightListItem; Debe ser calculado por la UI, y guardado en
	 * esta
	 */

	private int coverage;

	private int defense;
	private String description;;

	private String effectOnAcquire;

	private String effectOnStep;
	private String effectOnUse;
	private int equipCategory;
	// private String throwMessage;
	private int featureTurns;
	private boolean fixedMaterial;
	private int goldPrice;
	private boolean harmsUndead;
	// Attributes
	private String ID;
	private String menuDescription;
	private int pinLevel;
	private String placedSmartFeature;
	private int range;
	private int rarity;
	private int reloadCostGold;
	private int reloadTurns;
	private int shopCategory;
	private int shopChance;
	// private MenuItem shopMenuItem;
	private String shopDescription;
	private boolean singleUse;
	private boolean slicesThrough;
	private int throwRange;
	private boolean twoHanded;
	private boolean unique;
	private String useMessage;
	private int verticalRange;
	private String weaponCategory;
	public final static String CAT_UNARMED = "HAND_TO_HAND", CAT_DAGGERS = "DAGGERS",
			CAT_SWORDS = "SWORDS", CAT_SPEARS = "SPEARS", CAT_WHIPS = "WHIPS",
			CAT_MACES = "MACES", CAT_STAVES = "POLE_WEAPONS", CAT_RINGS = "RINGS",
			CAT_PROJECTILES = "THROWN", CAT_BOWS = "BOWS", CAT_PISTOLS = "MISSILE_CRAFT",
			CAT_SHIELD = "SHIELD";
	public final static String[ ] CATS = new String[ ]
	{	ItemDefinition.CAT_UNARMED, ItemDefinition.CAT_DAGGERS, ItemDefinition.CAT_SWORDS,
		ItemDefinition.CAT_SPEARS, ItemDefinition.CAT_WHIPS, ItemDefinition.CAT_MACES,
		ItemDefinition.CAT_STAVES, ItemDefinition.CAT_RINGS,
		ItemDefinition.CAT_PROJECTILES, ItemDefinition.CAT_BOWS,
		ItemDefinition.CAT_PISTOLS, ItemDefinition.CAT_SHIELD };
	public static int EQUIP_ARMOR = 1, EQUIP_WEAPON = 2, EQUIP_SHIELD = 3;
	public final static Hashtable HASH_DESCRIPTIONS = new Hashtable( );
	// Shop Categories
	public final static int SHOP_CRAFTS = 1, SHOP_MAGIC = 2, SHOP_WEAPONS = 3,
			SHOP_ARMOR = 4;
	static
	{
		HASH_DESCRIPTIONS.put( CAT_UNARMED, "hand to hand combat" );
		HASH_DESCRIPTIONS.put( CAT_DAGGERS, "daggers" );
		HASH_DESCRIPTIONS.put( CAT_SWORDS, "swords" );
		HASH_DESCRIPTIONS.put( CAT_SPEARS, "spears" );
		HASH_DESCRIPTIONS.put( CAT_WHIPS, "whips" );
		HASH_DESCRIPTIONS.put( CAT_STAVES, "pole weapons" );
		HASH_DESCRIPTIONS.put( CAT_RINGS, "rings" );
		HASH_DESCRIPTIONS.put( CAT_PISTOLS, "missile craft" );
		HASH_DESCRIPTIONS.put( CAT_PROJECTILES, "thrown weapons" );
		HASH_DESCRIPTIONS.put( CAT_BOWS, "bows" );
		HASH_DESCRIPTIONS.put( CAT_MACES, "maces" );
		HASH_DESCRIPTIONS.put( CAT_SHIELD, "shields" );
	}

	public ItemDefinition(	String pID, String pDescription, String pAppearance,
							int pEquipCategory, String pMenuDescription, int pinLevel,
							int pShopChance, String pShopDescription, int pGoldPrice,
							int pShopCategory, int pAttack, int pRange, int pReloadTurns,
							String pWeaponCategory, boolean pHarmsUndead,
							boolean pSlicesThrough, int pDefense, int pCoverage,
							int pVerticalRange, int attackCost, int pReloadGoldCost,
							boolean pTwoHanded, String pEffectOnUse,
							String pEffectOnAcquire, int pThrowRange,
							String pPlacedSmartFeature, boolean pSingleUse,
							int pFeatureTurns, String pUseMessage, boolean pUnique,
							boolean fixedMaterial, String pAttackSFX, String pAttackSound,
							int pCoolness, int pRarity )
	{
		ID = pID;
		rarity = pRarity;
		coolness = pCoolness;
		description = pDescription;
		appearance = AppearanceFactory.getAppearanceFactory( )
				.getAppearance( pAppearance );
		goldPrice = pGoldPrice;
		effectOnUse = pEffectOnUse;
		effectOnAcquire = pEffectOnAcquire;
		throwRange = pThrowRange;
		placedSmartFeature = pPlacedSmartFeature;
		singleUse = pSingleUse;
		twoHanded = pTwoHanded;
		coverage = pCoverage;
		featureTurns = pFeatureTurns;
		attack = pAttack;
		range = pRange;
		attackSound = pAttackSound;
		reloadTurns = pReloadTurns;
		harmsUndead = pHarmsUndead;
		slicesThrough = pSlicesThrough;
		useMessage = pUseMessage;
		shopChance = pShopChance;
		shopDescription = pShopDescription;
		defense = pDefense;
		equipCategory = pEquipCategory;
		attackSFX = pAttackSFX;
		weaponCategory = pWeaponCategory;
		shopCategory = pShopCategory;
		verticalRange = pVerticalRange;
		this.attackCost = attackCost;
		menuDescription = pMenuDescription;
		// shopMenuItem = new ShopMenuItem(this);
		setReloadCostGold( pReloadGoldCost );
		unique = pUnique;
		this.fixedMaterial = fixedMaterial;
		this.pinLevel = pinLevel;
		// sightListItem = new BasicListItem(appearance.getChar(),
		// appearance.getColor(), description);
	}
	public static String getCategoryDescription( String catID )
	{
		return (String) HASH_DESCRIPTIONS.get( catID );
	}

	public Appearance getAppearance( )
	{
		return appearance;
	}

	public int getAttack( )
	{
		return attack;
	}

	public int getAttackCost( )
	{
		return attackCost;
	}

	public String getAttackSFX( )
	{
		return attackSFX;
	}

	public String getAttackSound( )
	{
		return attackSound;
	}

	/*
	 * public MenuItem getShopMenuItem(){ return shopMenuItem; }
	 */

	public String getAttributesDescription( )
	{
		String base = getDescription( );
		if ( getAttack( ) > 0 || getDefense( ) > 0 || getRange( ) > 1
				|| getVerticalRange( ) > 0 )
			base += " (";
		if ( getAttack( ) > 0 )
			base += "ATK:" + getAttack( ) + " ";
		if ( getDefense( ) > 0 )
			base += "DEF:" + getDefense( ) + " ";
		if ( getRange( ) > 1 || getVerticalRange( ) > 0 )
			if ( getVerticalRange( ) > 0 )
				base += "RNG:" + getRange( ) + "," + getVerticalRange( );
			else
				base += "RNG:" + getRange( );
		if ( getReloadCostGold( ) > 0 )
		{
			base += " RLD:" + getReloadCostGold( );
		}
		if ( getAttack( ) > 0 || getDefense( ) > 0 || getRange( ) > 1
				|| getVerticalRange( ) > 0 )
			base += ")";
		return base;
	}

	public int getCoolness( )
	{
		return coolness;
	}

	public int getCoverage( )
	{
		return coverage;
	}

	public int getDefense( )
	{
		return defense;
	}

	public String getDescription( )
	{
		return description;
	}

	public String getEffectOnAcquire( )
	{
		return effectOnAcquire;
	}

	public String getEffectOnUse( )
	{
		return effectOnUse;
	}

	public int getEquipCategory( )
	{
		return equipCategory;
	}

	public int getFeatureTurns( )
	{
		return featureTurns;
	}

	/*
	 * public String getThrowMessage() { return throwMessage; }
	 */

	public int getGoldPrice( )
	{
		return goldPrice;
	}

	public String getID( )
	{
		return ID;
	}

	public String getMenuDescription( )
	{
		return menuDescription;
	}

	public int getPinLevel( )
	{
		return pinLevel;
	}

	public String getPlacedSmartFeature( )
	{
		return placedSmartFeature;
	}

	public int getRange( )
	{
		return range;
	}

	public int getRarity( )
	{
		return rarity;
	}

	/*
	 * public String getMenuDescription(){ return getAttributesDescription(); }
	 * public Appearance getMenuAppearance(){ return getAppearance(); }
	 */

	public int getReloadCostGold( )
	{
		return reloadCostGold;
	}

	public int getReloadTurns( )
	{
		return reloadTurns;
	}

	public int getShopCategory( )
	{
		return shopCategory;
	}

	public int getShopChance( )
	{
		return shopChance;
	}

	/*
	 * public ListItem getSightListItem(){ return sightListItem; } public void
	 * setSightListItem(ListItem sightListItem) { this.sightListItem =
	 * sightListItem; }
	 */
	public String getShopDescription( )
	{
		return shopDescription;
	}

	public int getThrowRange( )
	{
		return throwRange;
	}

	public String getUseMessage( )
	{
		return useMessage;
	}

	public int getVerticalRange( )
	{
		return verticalRange;
	}

	public String getWeaponCategory( )
	{
		return weaponCategory;
	}

	public boolean isFixedMaterial( )
	{
		return fixedMaterial;
	}

	public boolean isHarmsUndead( )
	{
		return harmsUndead;
	}

	public boolean isSingleUse( )
	{
		return singleUse;
	}

	public boolean isSlicesThrough( )
	{
		return slicesThrough;
	}

	public boolean isTwoHanded( )
	{
		return twoHanded;
	}

	public boolean isUnique( )
	{
		return unique;
	}

	public void setMenuDescription( String menuDescription )
	{
		this.menuDescription = menuDescription;
	}

	public void setReloadCostGold( int reloadCostGold )
	{
		this.reloadCostGold = reloadCostGold;
	}

	public void setShopCategory( int shopCategory )
	{
		this.shopCategory = shopCategory;
	}

	public void setWeaponCategory( String weaponCategory )
	{
		this.weaponCategory = weaponCategory;
	}
}