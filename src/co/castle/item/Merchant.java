package co.castle.item;

import java.util.Vector;

import co.castle.data.Items;
import co.castle.npc.NPC;
import co.castle.npc.NPCDefinition;
import co.castle.player.Player;
import sz.util.Util;

public class Merchant extends NPC
{
	// private Vector merchandises = new Vector();
	private Vector inventory;
	private int merchandiseType;
	private String merchantName;

	private int refreshTurns = -1;

	private final static String[ ] merchantNames = new String[ ]
	{	"Kaleth", "Adam", "Invenior", "Dimitri", "Merdotios", "Richard", "Tommy",
		"Valentina", "Astrith", "Julieth", "Jazeth", "Juran", "Camilla", "Emer" };

	public Merchant( NPCDefinition def, int pMerchandiseType )
	{
		super( def );
		merchandiseType = pMerchandiseType;
		merchantName = merchantNames[ Util.rand( 0, merchantNames.length - 1 ) ];
	}

	public int getAttack( )
	{
		return 4;
	}

	public String getDescription( )
	{
		return merchantName;
	}

	public Vector getMerchandiseFor( Player player )
	{
		if ( refreshTurns == -1
				|| player.getGameSessionInfo( ).getTurns( ) - refreshTurns > 1000 )
		{
			if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER
					&& ( merchandiseType == ItemDefinition.SHOP_WEAPONS
							|| merchandiseType == ItemDefinition.SHOP_ARMOR ) )
				;
			else
				refreshMerchandise( player );
			refreshTurns = player.getGameSessionInfo( ).getTurns( );
		}
		return inventory;
	}

	/*
	 * private void refreshMerchandise(Player player){ merchandises = new Vector();
	 * ItemDefinition[] defs = Items.getItemDefinitions(); //int itemNumber =
	 * Util.rand(6,12); int itemNumber = Util.rand(6,12); int items = 0; int tries =
	 * 0; while (items < itemNumber){ tries++; if (tries > 200) break;
	 * ItemDefinition trying = defs[Util.rand(0, defs.length - 1)]; if
	 * (trying.getShopCategory() != merchandiseType) continue; if
	 * (merchandises.contains(trying.getShopMenuItem())) continue; if
	 * (Util.chance(trying.getRarity())){
	 * merchandises.add(trying.getShopMenuItem()); items++; } } }
	 */

	public String getMerchandiseTypeDesc( )
	{
		switch ( merchandiseType )
		{
		case ItemDefinition.SHOP_ARMOR:
			return "armor";
		case ItemDefinition.SHOP_CRAFTS:
			return "general goods";
		case ItemDefinition.SHOP_MAGIC:
			return "magic goods";
		case ItemDefinition.SHOP_WEAPONS:
			return "weapons";
		}
		return "";
	}

	public String getName( )
	{
		return merchantName;
	}

	public void refreshMerchandise( Player player )
	{
		if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER
				&& ( merchandiseType == ItemDefinition.SHOP_WEAPONS
						|| merchandiseType == ItemDefinition.SHOP_ARMOR ) )
			return;

		inventory = new Vector( );
		Vector vectorIDs = new Vector( );
		ItemDefinition[ ] defs = Items.getItemDefinitions( );
		int itemNumber = Util.rand( 6, 12 );
		int items = 0;
		int tries = 0;
		while ( items < itemNumber )
		{
			tries++;
			if ( tries > 200 )
				break;
			ItemDefinition def = defs[ Util.rand( 0, defs.length - 1 ) ];
			if ( def.getShopCategory( ) != merchandiseType )
				continue;
			if ( !Util.chance( def.getShopChance( ) ) )
				continue;
			if ( vectorIDs.contains( def.getID( ) ) )
				continue;
			items++;
			Item item = new Item( def );
			vectorIDs.add( def.getID( ) );
			if ( def.isUnique( ) )
				inventory.add( item );
			else if ( def.getAttack( ) > 0 )
			{
				if ( !def.isFixedMaterial( ) )
					ItemFactory.getItemFactory( ).setMaterial( item,
							level.getLevelNumber( ), ItemFactory.MOD_MATERIAL );
				if ( Util.chance( 20 ) )
					ItemFactory.getItemFactory( ).setWeaponModifiers( item,
							level.getLevelNumber( ) );
				inventory.add( item );
			}
			else if ( def.getDefense( ) > 0 )
			{
				if ( !def.isFixedMaterial( ) )
					ItemFactory.getItemFactory( ).setMaterial( item,
							level.getLevelNumber( ), ItemFactory.MOD_ARMOR_MATERIAL );
				if ( Util.chance( 10 ) )
					ItemFactory.getItemFactory( ).setArmorModifiers( item,
							level.getLevelNumber( ) );
				inventory.add( item );
			}
			else
			{
				inventory.add( item );
			}
		}
	}
}