package co.castle.data;

import co.castle.feature.Feature;
import co.castle.player.Player;
import co.castle.ui.AppearanceFactory;

public class Features
{

	public static Feature[ ] getFeatureDefinitions( AppearanceFactory apf )
	{
		Feature[ ] ret = new Feature[ 42 ];
		ret[0] = new Feature("CANDLE", apf.getAppearance("CANDLE"), 0, "Candlestick",
				0, 3);
		ret[38] = new Feature("COFFIN", apf.getAppearance("COFFIN"), 2, "Coffin", 0,
				0);

		ret[40] = new Feature("MOUND", apf.getAppearance("MOUND"), 9999, "Mound", 0,
				0);
		ret[41] = new Feature("TELEPORT", apf.getAppearance("TELEPORT"), 9999,
				"Teleport Pad", 0, 5);

		int COMMONFAINT = 30;
		ret[1] = new Feature("SMALLHEART", apf.getAppearance("SMALLHEART"), 9999,
				"Small Heart", COMMONFAINT, 0);
		ret[2] = new Feature("DAGGERWP", apf.getAppearance("DAGGER"), 9999,
				"Mystic Dagger", COMMONFAINT, 2);
		ret[3] = new Feature("AXEWP", apf.getAppearance("AXE"), 9999, "Axe",
				COMMONFAINT, 2);
		ret[4] = new Feature("HOLYWP", apf.getAppearance("VIAL"), 9999,
				"Holy Water vial", COMMONFAINT, 2);
		ret[5] = new Feature("CROSSWP", apf.getAppearance("CROSS"), 9999,
				"Holy Cross", COMMONFAINT, 2);
		ret[ 6 ] = new Feature( "STOPWATCHWP", apf.getAppearance( "CLOCK" ), 9999,
				"Stopwatch", COMMONFAINT, 2 );
		ret[ 7 ] = new Feature( "DAGGERCANDLE", apf.getAppearance( "CANDLE" ), 0,
				"Candle", 0, 3 );
		ret[ 8 ] = new Feature( "AXECANDLE", apf.getAppearance( "CANDLE" ), 0, "Candle",
				0, 3 );
		ret[ 9 ] = new Feature( "HOLYCANDLE", apf.getAppearance( "CANDLE" ), 0, "Candle",
				0, 3 );
		ret[ 10 ] = new Feature( "CROSSCANDLE", apf.getAppearance( "CANDLE" ), 0,
				"Candle", 0, 3 );
		ret[ 11 ] = new Feature( "STOPWATCHCANDLE", apf.getAppearance( "CANDLE" ), 0,
				"Candle", 0, 3 );
		ret[ 12 ] = new Feature( "BIGCANDLE", apf.getAppearance( "CANDLE" ), 0, "Candle",
				0, 4 );
		ret[ 13 ] = new Feature( "BIGHEART", apf.getAppearance( "BIGHEART" ), 9999,
				"Big Heart", COMMONFAINT, 0 );
		ret[ 14 ] = new Feature( "KEY", apf.getAppearance( "KEY" ), 9999, "Magic Key", 0,
				0 );
		ret[ 15 ] = new Feature( "ENDFLOOR", apf.getAppearance( "BRICKWALKWAY" ), 9999999,
				"Walkway made of old damaged bricks", 0, 0 );
		ret[ 16 ] = new Feature( "UPGRADE", apf.getAppearance( "UPGRADE" ), 9999999,
				"Whip upgrade", COMMONFAINT, 2 );
		ret[ 17 ] = new Feature( "STAIRDOWN", apf.getAppearance( "MOAT_DOWN" ), 9999999,
				"Downward stairs", 0, 0 );
		ret[ 18 ] = new Feature( "STAIRUP", apf.getAppearance( "MOAT_UP" ), 999999,
				"Upward stairs", 0, 0 );
		ret[ 19 ] = new Feature( "MAGIC_DOOR", apf.getAppearance( "DOOR" ), 999999,
				"Magic door", 0, 0 );
		ret[ 20 ] = new Feature( "RED_CURTAIN", apf.getAppearance( "RED_CURTAIN" ), 5,
				"Badly worn red curtain", 0, 0 );
		ret[ 21 ] = new Feature( "ROSARY", apf.getAppearance( "ROSARY" ), 9999,
				"Holy Rosary", COMMONFAINT, 2 );

		ret[ 37 ] = new Feature( "COIN", apf.getAppearance( "COIN" ), 9999, "Gold Coin",
				COMMONFAINT, 0 );
		ret[ 22 ] = new Feature( "RED_MONEY_BAG", apf.getAppearance( "RED_MONEY_BAG" ),
				9999, "Red Bag of gold", COMMONFAINT, 0 );
		ret[ 23 ] = new Feature( "BLUE_MONEY_BAG", apf.getAppearance( "BLUE_MONEY_BAG" ),
				9999, "Blue Bag of gold", COMMONFAINT, 0 );
		ret[ 24 ] = new Feature( "WHITE_MONEY_BAG",
				apf.getAppearance( "WHITE_MONEY_BAG" ), 9999, "White Bag of gold",
				COMMONFAINT, 0 );
		ret[ 25 ] = new Feature( "CROWN", apf.getAppearance( "CROWN" ), 9999,
				"Golden Crown", COMMONFAINT, 0 );
		ret[ 26 ] = new Feature( "CHEST", apf.getAppearance( "CHEST" ), 9999,
				"Treasure Chest", COMMONFAINT, 0 );
		ret[ 27 ] = new Feature( "MOAUI_HEAD", apf.getAppearance( "MOAUI_HEAD" ), 9999,
				"Moaui Head", COMMONFAINT, 0 );
		ret[ 28 ] = new Feature( "RAINBOW_MONEY_BAG",
				apf.getAppearance( "RAINBOW_MONEY_BAG" ), 9999, "Multihued Bag of gold",
				COMMONFAINT, 0 );
		ret[ 29 ] = new Feature( "TREASURE_SPAWNER", apf.getAppearance( "VOID" ), 9999,
				"", 0, 0 );
		ret[ 30 ] = new Feature( "POT_ROAST", apf.getAppearance( "POT_ROAST" ), 9999,
				"Pot roast", COMMONFAINT, 0 );
		ret[ 31 ] = new Feature( "INVISIBILITY_POTION",
				apf.getAppearance( "INVISIBILITY_POTION" ), 9999, "Potion of Invisibilty",
				COMMONFAINT, 0 );
		ret[ 32 ] = new Feature( "BIBLEWP", apf.getAppearance( "BIBLE" ), 9999,
				"Holy Bible", COMMONFAINT, 2 );
		ret[ 33 ] = new Feature( "CRYSTALWP", apf.getAppearance( "CRYSTAL" ), 9999,
				"Blast Crystal", COMMONFAINT, 2 );
		ret[ 34 ] = new Feature( "FISTWP", apf.getAppearance( "FIST" ), 9999,
				"Sacred Fist", COMMONFAINT, 2 );
		ret[ 35 ] = new Feature( "REBOUNDWP", apf.getAppearance( "REBOUND_CRYSTAL" ),
				9999, "Rebound Crystal", COMMONFAINT, 2 );
		ret[ 36 ] = new Feature( "MUPGRADE", apf.getAppearance( "MUPGRADE" ), 9999999,
				"Mystic upgrade", COMMONFAINT, 2 );

		ret[ 39 ] = new Feature( "URN_CANDLE", apf.getAppearance( "URN_FLAME" ), 0, "Urn",
				0, 3 );

		ret[ 22 ].setScorePrize( 50 );
		ret[ 23 ].setScorePrize( 200 );
		ret[ 24 ].setScorePrize( 400 );
		ret[ 25 ].setScorePrize( 500 );
		ret[ 26 ].setScorePrize( 1000 );
		ret[ 27 ].setScorePrize( 2000 );
		ret[ 28 ].setScorePrize( 1500 );
		ret[ 37 ].setScorePrize( 5 );

		ret[ 30 ].setHealPrize( 6 );

		ret[ 1 ].setHeartPrize( 1 );
		ret[ 2 ].setMysticWeaponPrize( Player.DAGGER );
		ret[ 3 ].setMysticWeaponPrize( Player.AXE );
		ret[ 4 ].setMysticWeaponPrize( Player.HOLY );
		ret[ 5 ].setMysticWeaponPrize( Player.CROSS );
		ret[ 6 ].setMysticWeaponPrize( Player.STOPWATCH );
		ret[ 32 ].setMysticWeaponPrize( Player.BIBLE );
		ret[ 33 ].setMysticWeaponPrize( Player.SACRED_CRYSTAL );
		ret[ 34 ].setMysticWeaponPrize( Player.SACRED_FIST );
		ret[ 35 ].setMysticWeaponPrize( Player.SACRED_REBOUND );

		ret[ 13 ].setHeartPrize( 5 );
		ret[ 14 ].setKeyPrize( 1 );
		ret[ 16 ].setUpgradePrize( 1 );

		ret[ 0 ].setPrize( ret[ 1 ] );
		ret[ 39 ].setPrize( ret[ 1 ] );
		ret[ 7 ].setPrize( ret[ 2 ] );
		ret[ 8 ].setPrize( ret[ 3 ] );
		ret[ 9 ].setPrize( ret[ 4 ] );
		ret[ 10 ].setPrize( ret[ 5 ] );
		ret[ 11 ].setPrize( ret[ 6 ] );
		ret[ 12 ].setPrize( ret[ 13 ] );

		ret[ 21 ].setEffect( "ROSARY" );
		ret[ 29 ].setEffect( "SPAWN_TREASURE" );
		ret[ 31 ].setEffect( "INVISIBILITY" );
		ret[ 36 ].setEffect( "MUPGRADE" );

		ret[ 0 ].setDestroyable( true );
		ret[ 10 ].setDestroyable( true );
		ret[ 39 ].setDestroyable( true );
		ret[ 20 ].setDestroyable( true );

		ret[ 17 ].setHeightMod( 1 );
		ret[ 18 ].setHeightMod( -1 );

		ret[ 15 ].setTrigger( "ENDGAME" );

		ret[ 0 ].setRelevant( false );
		ret[ 39 ].setRelevant( false );
		ret[ 15 ].setRelevant( false );
		ret[ 17 ].setRelevant( false );
		ret[ 18 ].setRelevant( false );
		ret[ 19 ].setRelevant( false );
		ret[ 20 ].setRelevant( false );
		ret[ 29 ].setRelevant( false );

		ret[ 40 ].setRelevant( false );

		return ret;
	}
}
