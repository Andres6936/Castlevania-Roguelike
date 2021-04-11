package co.castle.item;

import java.util.Hashtable;
import java.util.Vector;

import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.player.Player;
import sz.util.Debug;
import sz.util.Util;

public class ItemFactory {
	private final Vector<ItemDefinition> armorDefinitions = new Vector<>();

	private final Hashtable<String, ItemDefinition> definitions = new Hashtable<>();

	private final Vector<ItemDefinition> generalItemsDefinitions = new Vector<>();

	private final Vector<ItemDefinition> weaponDefinitions = new Vector<>();

	public final static Modifier[] MOD_ADDITIONAL = new Modifier[]
			{new Modifier("OFHUNTING", " of Hunting", 2),
					new Modifier("OFDESTRUCTION", " of Destruction", 2),
					new Modifier("OFPUNISHMENT", " of Punishment", 2),
					new Modifier("OFTHEMOON", "  of the Moon", 2),};

	public final static Modifier[] MOD_ARMOR_ADDITIONAL = new Modifier[]
			{new Modifier("OFTHEMOON", " of the Moon", 5),
					new Modifier("OFTHESUN", " of the Sun", 5),};

	public final static Modifier[ ] MOD_ARMOR_MAGIC = new Modifier[ ]
	{	new Modifier( "HOLY", "Holy ", 5 ), new Modifier( "SHIELDING", "Shielding ", 10 ),
		new Modifier( "DARK", "Dark ", 5 ), new Modifier( "STAR", "Star ", 5 ), };

	public final static Modifier[ ] MOD_ARMOR_MATERIAL = new Modifier[ ]
	{	new Modifier( "BRONZE", "Bronze ", 80 ), new Modifier( "STEEL", "Steel ", 10 ),
		new Modifier( "IRON", "Iron ", 5 ), new Modifier( "SILVER", "Silver ", 2 ), };

	public final static Modifier[ ] MOD_ARMOR_STRENGTH = new Modifier[ ]
	{	new Modifier( "WEAKENED", "Weakened ", 10 ),
		new Modifier( "REINFORCED", "Reinforced ", 20 ), };

	public final static Modifier[ ] MOD_MAGIC = new Modifier[ ]
	{	new Modifier( "PULSATING", "Pulsating ", 5 ), new Modifier( "HOLY", "Holy ", 5 ),
		new Modifier( "SHIELDING", "Shielding ", 5 ), new Modifier( "DARK", "Dark ", 5 ),
		new Modifier( "STAR", "Star ", 5 ), };

	public final static Modifier[ ] MOD_MATERIAL = new Modifier[ ]
	{	new Modifier( "STEEL", "Steel ", 80 ), new Modifier( "WOODEN", "Wooden ", 5 ),
		new Modifier( "IRON", "Iron ", 5 ), new Modifier( "SILVER", "Silver ", 2 ),
		new Modifier( "OBSIDIAN", "Obsidian ", 2 ), };

	public final static Modifier[ ] MOD_STRENGTH = new Modifier[ ]
	{	new Modifier( "WEAKENED", "Weakened ", 20 ),
		new Modifier( "REINFORCED", "Reinforced ", 20 ),
		new Modifier( "ENHANCED", "Enhanced ", 15 ),
		new Modifier( "DEADLY", "Deadly ", 10 ), };

	private static ItemFactory singleton = new ItemFactory( );

	static
	{
		MOD_ARMOR_STRENGTH[ 0 ].setDefenseBonus( -1 );
		MOD_ARMOR_STRENGTH[ 1 ].setDefenseBonus( 1 );
		MOD_ARMOR_MAGIC[ 1 ].setDefenseBonus( 1 );
		MOD_ARMOR_MATERIAL[ 0 ].setDefenseBonus( 0 );
		MOD_ARMOR_MATERIAL[ 1 ].setDefenseBonus( 1 );
		MOD_ARMOR_MATERIAL[ 2 ].setDefenseBonus( 2 );
		MOD_ARMOR_MATERIAL[ 3 ].setDefenseBonus( 3 );

		MOD_ARMOR_STRENGTH[ 1 ].setPriceModifier( 100 );
		MOD_ARMOR_MAGIC[ 1 ].setPriceModifier( 150 );

		MOD_ARMOR_MATERIAL[ 0 ].setPriceModifier( 0 );
		MOD_ARMOR_MATERIAL[ 1 ].setPriceModifier( 100 );
		MOD_ARMOR_MATERIAL[ 2 ].setPriceModifier( 300 );
		MOD_ARMOR_MATERIAL[ 3 ].setPriceModifier( 1000 );

		MOD_STRENGTH[ 0 ].setAtkBonus( -3 );
		MOD_STRENGTH[ 1 ].setAtkBonus( 1 );
		MOD_STRENGTH[ 2 ].setAtkBonus( 3 );
		MOD_STRENGTH[ 3 ].setAtkBonus( 5 );
		MOD_STRENGTH[ 3 ].setSlicesThru( true );

		MOD_STRENGTH[ 0 ].setPriceModifier( 0 );
		MOD_STRENGTH[ 1 ].setPriceModifier( 100 );
		MOD_STRENGTH[ 2 ].setPriceModifier( 3000 );
		MOD_STRENGTH[ 3 ].setPriceModifier( 5000 );

		MOD_MAGIC[ 0 ].setRangeBonus( 1 );
		MOD_MAGIC[ 1 ].setHarmsUndead( true );
		MOD_MAGIC[ 2 ].setDefenseBonus( 5 );
		MOD_MAGIC[ 4 ].setHarmsUndead( true );

		MOD_MAGIC[ 0 ].setPriceModifier( 2000 );
		MOD_MAGIC[ 1 ].setPriceModifier( 3000 );
		MOD_MAGIC[ 2 ].setPriceModifier( 1000 );
		MOD_MAGIC[ 4 ].setPriceModifier( 3000 );

		MOD_MATERIAL[ 0 ].setAtkCostBonus( 0 );
		MOD_MATERIAL[ 1 ].setAtkCostBonus( 20 );
		MOD_MATERIAL[ 1 ].setAtkBonus( -5 );
		MOD_MATERIAL[ 2 ].setAtkBonus( 1 );
		MOD_MATERIAL[ 3 ].setAtkBonus( 3 );
		MOD_MATERIAL[ 3 ].setHarmsUndead( true );
		MOD_MATERIAL[ 4 ].setAtkBonus( 2 );

		MOD_MATERIAL[ 0 ].setPriceModifier( 0 );
		MOD_MATERIAL[ 1 ].setPriceModifier( 50 );
		MOD_MATERIAL[ 2 ].setPriceModifier( 100 );
		MOD_MATERIAL[ 3 ].setPriceModifier( 1000 );
		MOD_MATERIAL[ 4 ].setPriceModifier( 500 );

		MOD_ADDITIONAL[ 0 ].setRangeBonus( 1 );
		MOD_ADDITIONAL[ 1 ].setAtkBonus( 1 );
		MOD_ADDITIONAL[ 2 ].setAtkBonus( 2 );

		MOD_ADDITIONAL[ 0 ].setPriceModifier( 1500 );
		MOD_ADDITIONAL[ 1 ].setPriceModifier( 1500 );
		MOD_ADDITIONAL[ 2 ].setPriceModifier( 2000 );

	}

	public static ItemFactory getItemFactory( )
	{
		return singleton;
	}

	public Item createArmor(	Modifier strength, Modifier magic, Modifier material,
								String baseID, Modifier additional )
	{
		Item weapon = createItem( baseID );
		if ( weapon != null )
			weapon.addPreModifier( strength );
		if ( magic != null )
			weapon.addPreModifier( magic );
		weapon.addPreModifier( material );
		if ( additional != null )
			weapon.addPostModifier( additional );
		return weapon;
	}

	public Item createArmor( String ID, String material )
	{
		Modifier modMaterial = null;
		for ( int i = 0; i < MOD_ARMOR_MATERIAL.length; i++ )
		{
			if ( MOD_ARMOR_MATERIAL[ i ].getID( ).equals( material ) )
				modMaterial = MOD_ARMOR_MATERIAL[ i ];
		}

		ItemDefinition def = getDefinition( ID );
		Item item = new Item( def );
		if ( !def.isFixedMaterial( ) )
		{
			item.addPreModifier( modMaterial );
		}
		return item;
	}

	public Item createItem( String ID )
	{
		ItemDefinition def = (ItemDefinition) definitions.get( ID );
		if ( def == null )
			Debug.doAssert( false, "Invalid Item ID " + ID );
		return new Item( def );
	}

	public Item createItemForLevel( Level level, Player player )
	{
		/*
		 * String[] itemIDs = level.getSpawnItemsIDs(); String itemID =
		 * Util.randomElementOf(itemIDs); ItemDefinition def = getDefinition(itemID);
		 */
		if ( level.getLevelNumber( ) == -1 )
			return null;
		int tries = 150;
		int i = 0;
		ItemDefinition def = null;
		out: while ( i < tries )
		{
			int pin = Util.rand( 0, 100 );
			if ( pin > 15 )
				def = (ItemDefinition) Util.randomElementOf( generalItemsDefinitions );
			else if ( pin > 5 )
				def = (ItemDefinition) Util.randomElementOf( weaponDefinitions );
			else
				def = (ItemDefinition) Util.randomElementOf( armorDefinitions );
			int pinLevel = def.getPinLevel( );
			if ( pinLevel == 0 )
				continue;
			int diff = Math.abs( level.getLevelNumber( ) - pinLevel );
			if ( diff > 5 )
			{
				i++;
				def = null;
				continue;
			}
			int prob = 80;
			for ( i = 0; i < diff; i++ )
			{
				prob = (int) Math.round( prob / 2.0d );
			}

			if ( def.isUnique( ) )
			{
				if ( Game.wasUniqueGenerated( def.getID( ) ) )
				{
					i++;
					continue;
				}
				prob = (int) Math.round( prob / 5.0d );
			}

			if ( def.getCoolness( ) > player.getCoolness( ) )
			{
				i++;
				continue;
			}

			if ( Util.chance( prob ) && Util.chance( def.getRarity( ) ) )
			{
				if ( def.isUnique( ) )
				{
					Game.registerUniqueGenerated( def.getID( ) );
				}
				break out;
			}

			def = null;
			i++;
		}

		if ( def == null )
			return null;
		Item item = new Item( def );
		player.reduceCoolness( def.getCoolness( ) );
		if ( def.isUnique( ) )
			return item;
		if ( def.getEquipCategory( ) == ItemDefinition.EQUIP_WEAPON )
		{
			if ( !def.isFixedMaterial( ) )
				setMaterial( item, level.getLevelNumber( ), MOD_MATERIAL );
			if ( Util.chance( 20 ) )
				setWeaponModifiers( item, level.getLevelNumber( ) );
			return item;
		}
		else if ( def.getEquipCategory( ) == ItemDefinition.EQUIP_ARMOR )
		{
			if ( !def.isFixedMaterial( ) )
				setMaterial( item, level.getLevelNumber( ), MOD_ARMOR_MATERIAL );
			if ( Util.chance( 10 ) )
				setArmorModifiers( item, level.getLevelNumber( ) );
			return item;
		}
		else if ( def.getEquipCategory( ) == ItemDefinition.EQUIP_SHIELD )
		{
			if ( !def.isFixedMaterial( ) )
				setMaterial( item, level.getLevelNumber( ), MOD_MATERIAL );
			if ( Util.chance( 20 ) )
				setWeaponModifiers( item, level.getLevelNumber( ) );
			return item;
		}
		else
		{
			return item;
		}

	}

	public Item createShield( String ID, String material )
	{
		Modifier modMaterial = null;
		for ( int i = 0; i < MOD_ARMOR_MATERIAL.length; i++ )
		{
			if ( MOD_ARMOR_MATERIAL[ i ].getID( ).equals( material ) )
				modMaterial = MOD_ARMOR_MATERIAL[ i ];
		}

		ItemDefinition def = getDefinition( ID );
		Item item = new Item( def );
		if ( !def.isFixedMaterial( ) )
		{
			Debug.doAssert( modMaterial != null,
					"Material " + material + " not found at create shield" );
			item.addPreModifier( modMaterial );
		}
		return item;
	}

	public Item createWeapon(	Modifier strength, Modifier magic, Modifier material,
								String baseID, Modifier additional )
	{
		Item weapon = createItem( baseID );
		if ( weapon != null )
			weapon.addPreModifier( strength );
		if ( magic != null )
			weapon.addPreModifier( magic );
		weapon.addPreModifier( material );
		if ( additional != null )
			weapon.addPostModifier( additional );
		return weapon;
	}

	public Item createWeapon( String ID, String material )
	{
		Modifier modMaterial = null;
		for ( int i = 0; i < MOD_MATERIAL.length; i++ )
		{
			if ( MOD_MATERIAL[ i ].getID( ).equals( material ) )
				modMaterial = MOD_MATERIAL[ i ];
		}
		ItemDefinition def = getDefinition( ID );
		Item item = new Item( def );
		if ( !def.isFixedMaterial( ) )
		{
			Debug.doAssert( modMaterial != null,
					"Material " + material + " not found at create weapon" );
			item.addPreModifier( modMaterial );
		}
		return item;
	}

	public ItemDefinition getDefinition( String ID )
	{
		ItemDefinition def = (ItemDefinition) definitions.get( ID );
		if ( def == null )
			Debug.doAssert( false, "Invalid Item ID " + ID );
		return def;
	}

	public void init( ItemDefinition[ ] defs ) {
		for (ItemDefinition def : defs) {
			definitions.put(def.getID(), def);
			switch (def.getEquipCategory()) {
				case 0:
					generalItemsDefinitions.add(def);
					break;
				case 1:
					armorDefinitions.add(def);
					break;
				case 2:
					weaponDefinitions.add(def);
					break;
				case 4:
					armorDefinitions.add(def);
					break;
			}
		}

	}

	public void setArmorModifiers( Item weapon, int levelNumber )
	{
		Modifier strength = null;
		Modifier magic = null;
		Modifier additional = null;
		Modifier rand = (Modifier) Util.randomElementOf( MOD_ARMOR_STRENGTH );
		int chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			strength = rand;
		}
		rand = (Modifier) Util.randomElementOf( MOD_ARMOR_MAGIC );
		chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			magic = rand;
		}
		rand = (Modifier) Util.randomElementOf( MOD_ARMOR_ADDITIONAL );
		chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			additional = rand;
		}
		if ( strength != null )
			weapon.addPreModifier( strength );
		if ( magic != null )
			weapon.addPreModifier( magic );
		if ( additional != null )
			weapon.addPostModifier( additional );
	}

	public void setMaterial( Item weapon, int levelNumber, Modifier[ ] MATERIALS )
	{
		Modifier material = MATERIALS[ 0 ];
		int tries = 3;
		while ( tries > 0 )
		{
			Modifier rand = (Modifier) Util.randomElementOf( MATERIALS );
			int chance = rand.getChance( ) + levelNumber;
			if ( Util.chance( chance ) )
			{
				material = rand;
				break;
			}
			tries--;
		}
		tries = 3;
		weapon.addPreModifier( material );
	}

	public void setWeaponModifiers( Item weapon, int levelNumber )
	{
		Modifier strength = null;
		Modifier magic = null;
		Modifier additional = null;

		Modifier rand = (Modifier) Util.randomElementOf( MOD_STRENGTH );
		int chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			strength = rand;
		}
		rand = (Modifier) Util.randomElementOf( MOD_MAGIC );
		chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			magic = rand;
		}
		rand = (Modifier) Util.randomElementOf( MOD_ADDITIONAL );
		chance = rand.getChance( ) + levelNumber;
		if ( Util.chance( chance ) )
		{
			additional = rand;
		}

		if ( strength != null )
			weapon.addPreModifier( strength );
		if ( magic != null )
			weapon.addPreModifier( magic );
		if ( additional != null )
			weapon.addPostModifier( additional );
	}

}