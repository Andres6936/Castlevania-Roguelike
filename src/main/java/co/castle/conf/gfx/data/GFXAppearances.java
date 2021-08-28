package co.castle.conf.gfx.data;

import java.awt.image.BufferedImage;

import co.castle.game.Game;
import co.castle.ui.Appearance;
import co.castle.ui.graphicsUI.GFXAppearance;
import sz.util.ImageUtils;

public class GFXAppearances {
	private final int CELL_HEIGHT;
	private Appearance[] defs;
	private final int WIDTH_BIG;
	private final int WIDTH_HALF;

	private final int WIDTH_NORMAL;

	// Get instance of Asset
	protected Asset configuration;

	public GFXAppearances(Asset configuration) {
		this.configuration = configuration;
		WIDTH_BIG = configuration.BIG_TILE_WIDTH;
		WIDTH_HALF = configuration.HALF_TILE_WIDTH;
		CELL_HEIGHT = configuration.CELL_HEIGHT;
		WIDTH_NORMAL = configuration.NORMAL_TILE_WIDTH;

		SetAppearances();
	}

	private GFXAppearance createAppearance(String ID, BufferedImage bigImage, int xpos,
										   int ypos)
	{
		xpos--;
		ypos--;

		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos * WIDTH_NORMAL,
					ypos * WIDTH_NORMAL, WIDTH_NORMAL, WIDTH_NORMAL );
            return new GFXAppearance( ID, img, 0, 0 );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

    private GFXAppearance createAppearance( String ID, BufferedImage bigImage, int xpos,
                                            int ypos, int width, int height, int superw,
                                            int superh )
	{
		xpos--;
		ypos--;
		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos, ypos, width,
					height );
            return new GFXAppearance( ID, img, superw, superh );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

    private GFXAppearance createBAppearance( String ID, BufferedImage bigImage, int xpos,
                                             int ypos )
	{
		xpos--;
		ypos--;
		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos * WIDTH_BIG,
					ypos * WIDTH_BIG, WIDTH_BIG, WIDTH_BIG );
            return new GFXAppearance( ID, img, WIDTH_HALF / 2, WIDTH_HALF );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

    private GFXAppearance createIAppearance( String ID, BufferedImage bigImage, int xpos,
                                             int ypos )
	{
		xpos--;
		ypos--;
		try {
			BufferedImage img = ImageUtils.crearImagen(bigImage, xpos * WIDTH_HALF,
					ypos * WIDTH_HALF, WIDTH_HALF, WIDTH_HALF);
			return new GFXAppearance(ID, img, -8, 0);
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

    private GFXAppearance createTAppearance( String ID, int xpos, int ypos )
	{
		xpos--;
		ypos--;
		BufferedImage bigImage = configuration.IMAGE_TERRAIN;
		BufferedImage bigDarkImage = configuration.IMAGE_DARK_TERRAIN;
		BufferedImage bigNiteImage = configuration.IMAGE_NIGHT_TERRAIN;
		BufferedImage bigDarkNiteImage = configuration.IMAGE_DARK_NIGHT_TERRAIN;

		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos * WIDTH_NORMAL,
					ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage darkimg = ImageUtils.crearImagen( bigDarkImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage niteimg = ImageUtils.crearImagen( bigNiteImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage darkniteimg = ImageUtils.crearImagen( bigDarkNiteImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
            return new GFXAppearance( ID, img, darkimg, niteimg, darkniteimg,
                                      0, 0 );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading terrain image ", e );
		}
		return null;
	}

    private GFXAppearance createTAppearance( String ID, int xpos, int ypos, int xoff,
                                             int yoff )
	{
		xpos--;
		ypos--;
		BufferedImage bigImage = configuration.IMAGE_TERRAIN;
		BufferedImage bigDarkImage = configuration.IMAGE_DARK_TERRAIN;
		BufferedImage bigNiteImage = configuration.IMAGE_NIGHT_TERRAIN;
		BufferedImage bigDarkNiteImage = configuration.IMAGE_DARK_NIGHT_TERRAIN;

		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos * WIDTH_NORMAL,
					ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage darkimg = ImageUtils.crearImagen( bigDarkImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage niteimg = ImageUtils.crearImagen( bigNiteImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
			BufferedImage darkniteimg = ImageUtils.crearImagen( bigDarkNiteImage,
					xpos * WIDTH_NORMAL, ypos * CELL_HEIGHT, WIDTH_NORMAL, CELL_HEIGHT );
            return new GFXAppearance( ID, img, darkimg, niteimg, darkniteimg,
                                      xoff, yoff );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading terrain image ", e );
		}
		return null;
	}

    private GFXAppearance createXAppearance( String ID, BufferedImage bigImage, int xpos,
                                             int ypos, int width, int height )
	{
		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos, ypos, width,
					height );
            return new GFXAppearance( ID, img, ( width - WIDTH_NORMAL ) / 2,
                                      height - WIDTH_NORMAL );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

    private GFXAppearance createXAppearance( String ID, BufferedImage bigImage, int xpos,
                                             int ypos, int width, int height, int yoff )
	{
		try
		{
			BufferedImage img = ImageUtils.crearImagen( bigImage, xpos, ypos, width,
					height );
            return new GFXAppearance( ID, img, ( width - WIDTH_NORMAL ) / 2,
                                      height - WIDTH_NORMAL + yoff );
		}
		catch ( Exception e )
		{
			Game.crash( "Error loading image ", e );
		}
		return null;
	}

	public Appearance[ ] getAppearances( )
	{
		return defs;
	}

    private void SetAppearances( )
	{
		defs = new Appearance[ ]
		{	createTAppearance( "VOID", 4, 5 ),
			new GFXAppearance( "NOTHING", null, null, null, null, 0, 0 ),

			/* NEEDED */
			createTAppearance( "COFFIN", 14, 2 ),

			createTAppearance( "DOOR", 11, 2 ),

			createAppearance( "SHADOW", configuration.IMAGE_SHADOW, 1, 1 ),

			createAppearance( "CHRISTOPHER_B", configuration.IMAGE_CHARACTERS, 1, 5 ),

			createAppearance( "SOLIEYU_B", configuration.IMAGE_CHARACTERS, 2, 5 ),
			createAppearance( "BADBELMONT", configuration.IMAGE_CHARACTERS, 2, 6 ),
			createBAppearance( "PRELUDE_DRACULA", configuration.IMAGE_BIG_MONSTERS, 4,
					5 ),

			/* Town */
			createTAppearance( "TOWN_GRASS", 4, 1 ),
			createTAppearance( "TOWN_WALL", 1, 1 ),
			createTAppearance( "HOUSE_FLOOR", 9, 1 ),
			createTAppearance( "TOWN_DOOR", 1, 11 ),
			createTAppearance( "MIDWALKWAY", 2, 1 ),
			createTAppearance( "TOWN_CHURCH_FLOOR", 10, 1 ),
			createTAppearance( "TOWN_WATERWAY", 7, 1 ),
			createTAppearance( "BRICKWALKWAY", 6, 1 ),
			createTAppearance( "BRICKWALKWAY2", 10, 1 ),
			createTAppearance( "TOWN_ROOF", 14, 1 ),
			createTAppearance( "TOWN_STAIRSDOWN", 15, 1 ),
			createTAppearance( "TOWN_STAIRSUP", 16, 1 ),
			createTAppearance( "TOWN_TREE", 12, 1 ),
			createTAppearance( "TOWN_STAIRS", 3, 1 ),

			/* Dark Forest */
			createTAppearance( "FOREST_TREE", 8, 1 ),
			createTAppearance( "FOREST_TREE_1", 12, 1 ),
			createTAppearance( "FOREST_TREE_2", 13, 1 ),
			createTAppearance( "FOREST_GRASS", 17, 1 ),
			createTAppearance( "FOREST_DIRT", 19, 1 ),
			createTAppearance( "WRECKED_CHARRIOT", 15, 2 ),
			createTAppearance( "WRECKED_WHEEL", 14, 2 ),
			createTAppearance( "SIGN_POST", 16, 2 ),

			/* Castle Bridge */
			createTAppearance( "WOODEN_BRIDGE", 17, 3 ),
			createTAppearance( "DARK_LAKE", 13, 3 ),
			createTAppearance( "BRIDGE_WALKWAY", 16, 3 ),
			createTAppearance( "BRIDGE_COLUMN", 14, 3 ),
			createTAppearance( "STONE_BLOCK", 1, 1 ),

			/* Castle Garden */
			createTAppearance( "GARDEN_GRASS", 17, 1 ),
			createTAppearance( "GARDEN_WALKWAY", 19, 1 ),
			createTAppearance( "DEAD_STUMP", 4, 2 ),
			createTAppearance( "GARDEN_TREE", 5, 2 ),
			createTAppearance( "GARDEN_TORCH", 3, 2 ),
			createTAppearance( "GARDEN_FENCE", 6, 2 ),
			createTAppearance( "CASTLE_DOOR", 11, 2 ),
			createTAppearance( "GARDEN_FOUNTAIN_CENTER", 9, 2 ),
			createTAppearance( "GARDEN_FOUNTAIN_AROUND", 7, 2 ),
			createTAppearance( "GARDEN_FOUNTAIN_POOL", 8, 2 ),
			createTAppearance( "GARGOYLESTATUE", 2, 2 ),
			createTAppearance( "HUMANSTATUE", 1, 2 ),
			createTAppearance( "GARDEN_DOOR", 11, 1 ),
			createTAppearance( "GARDEN_WALL", 10, 2 ),
			createTAppearance( "CASTLE_WALL", 10, 2 ),

			/* Marble Hall */
			createTAppearance( "GRAY_COLUMN", 15, 3 ),
			createTAppearance( "MARBLE_COLUMN", 5, 3 ),
			createTAppearance( "MARBLE_FLOOR", 1, 3 ),
			createTAppearance( "MARBLE_STAIRS", 2, 3 ),
			createTAppearance( "MARBLE_MIDFLOOR", 3, 3 ),
			createTAppearance( "BIG_WINDOW", 8, 3 ),
			createTAppearance( "RED_CURTAIN", 6, 3 ),
			createTAppearance( "GODNESS_STATUE", 7, 3 ),
			createTAppearance( "RED_CARPET", 4, 3 ),

			/* Moat */
			createTAppearance( "MOSS_COLUMN", 7, 4 ),
			createTAppearance( "MOSS_FLOOR", 2, 4 ),
			createTAppearance( "RUSTY_PLATFORM", 3, 10 ),
			createTAppearance( "MOSS_WATERWAY_ETH", 8, 4, 0, -10 ),
			createTAppearance( "MOSS_WATERWAY", 8, 4, 0, -10 ),
			/*
			 * createAppearance("MOAT_DOWN", imgConfig.getTerrainImage(), 128,
			 * 164,WIDTH_NORMAL,WIDTH_NORMAL, 0,0), createAppearance("MOAT_UP",
			 * imgConfig.getTerrainImage(), 160, 154,WIDTH_NORMAL,42, 0,10),
			 */
			createAppearance( "MOAT_DOWN", configuration.IMAGE_TERRAIN, 5, 4 ),
			createAppearance( "MOAT_UP", configuration.IMAGE_TERRAIN, 6, 4 ),
			createTAppearance( "MOSS_MIDFLOOR", 4, 4 ),
			createTAppearance( "MOSS_STAIRS", 3, 4 ),
			// createTAppearance("MOAT_UP", 6, 4),

			/* Sewers */
			createTAppearance( "SEWERS_FLOOR", 8, 4 ),
			createTAppearance( "SEWERS_WALL", 1, 4 ),
			createTAppearance( "SEWERS_DOWN", 9, 10 ),
			createTAppearance( "SEWERS_UP", 10, 10 ),
			createTAppearance( "WEIRD_MACHINE", 8, 10 ),

			/* Alchemy Lab */
			createTAppearance( "RED_FLOOR", 3, 5 ), createTAppearance( "RED_WALL", 1, 5 ),
			createTAppearance( "SMALL_WINDOW", 2, 5 ),

			/* Chapel */
			createTAppearance( "CHURCH_WALL", 11, 4 ),
			createTAppearance( "CHURCH_FLOOR", 13, 4 ),
			createTAppearance( "CHURCH_WOODEN_BARRIER_H", 19, 4 ),
			createTAppearance( "CHURCH_WOODEN_BARRIER_V", 20, 4 ),
			createTAppearance( "CHURCH_CHAIR", 18, 4 ),
			createTAppearance( "CHURCH_CONFESSIONARY", 11, 5 ),
			createTAppearance( "CHURCH_CARPET", 14, 4 ),
			createTAppearance( "ATRIUM", 14, 5 ),
			createTAppearance( "CHURCH_STAINED_WINDOW", 12, 4 ),
			createTAppearance( "CHURCH_FLOOR_H", 13, 4 ),

			createTAppearance( "QUARTERS_WALL", 11, 4 ), /* Pending */
			createTAppearance( "QUARTERS_FLOOR", 13, 4 ), /* Pending */

			/* left some unused */

			/* Ruins */
			/**/createTAppearance( "STONE_WALL", 4, 1 ),
			/**/createTAppearance( "STONE_FLOOR", 4, 1 ),

			/* Ruins */
			createTAppearance( "RUINS_COLUMN", 4, 6 ),
			createTAppearance( "RUINS_FLOOR", 2, 6 ),
			createTAppearance( "RUINS_WALL", 1, 6 ),
			createTAppearance( "RUINS_FLOOR_H", 5, 6 ),
			createTAppearance( "RUINS_STAIRS", 6, 6 ),
			createTAppearance( "RUINS_DOOR", 3, 11 ),

			/* Caves */
			createTAppearance( "CAVE_WALL", 1, 7 ),
			createTAppearance( "CAVE_FLOOR", 2, 7 ),
			createTAppearance( "CAVE_WATER", 5, 7 ), /* Missing */
			createTAppearance( "LAVA", 4, 7 ),

			/* Warehouse */
			createTAppearance( "WAREHOUSE_WALL", 1, 12 ),
			createTAppearance( "WAREHOUSE_FLOOR", 2, 12 ),

			/* Courtyard */ /* Missing */
			createTAppearance( "COURTYARD_WALL", 1, 6 ),
			createTAppearance( "COURTYARD_FLOOR", 2, 6 ),
			createTAppearance( "COURTYARD_COLUMN", 4, 6 ),
			createTAppearance( "COURTYARD_FENCE", 6, 2 ),
			createTAppearance( "COURTYARD_GRASS", 18, 1 ),
			createTAppearance( "COURTYARD_FLOWERS", 13, 2 ),
			createTAppearance( "COURTYARD_FOUNTAIN_CENTER", 9, 2 ),
			createTAppearance( "COURTYARD_FOUNTAIN_AROUND", 7, 2 ),
			createTAppearance( "COURTYARD_FOUNTAIN_POOL", 8, 2 ),
			createTAppearance( "COURTYARD_TREE", 12, 2 ),
			createTAppearance( "COURTYARD_RUINED_WALL", 1, 6 ),
			createTAppearance( "COURTYARD_STAIRS", 6, 6 ),

			createTAppearance( "DINING_CHAIR", 15, 5 ), /* Missing */
			createTAppearance( "DINING_TABLE", 8, 10 ), /* Missing */
			createTAppearance( "MARBLE_STAIRSUP", 6, 4 ),
			createTAppearance( "MARBLE_STAIRSDOWN", 5, 4 ),

			/* Dungeon */
			createTAppearance( "DUNGEON_FLOOR", 2, 10 ),
			createTAppearance( "DUNGEON_DOOR", 4, 11 ),
			createTAppearance( "DUNGEON_WALL", 1, 10 ),
			createTAppearance( "DUNGEON_PASSAGE", 3, 10 ),
			createTAppearance( "DUNGEON_DOWN", 9, 10 ),
			createTAppearance( "DUNGEON_UP", 10, 10 ),

			/* Frank Lab, goes with Dungeon Theme */
			createTAppearance( "WIRES", 7, 10 ),
			createTAppearance( "FRANK_TABLE", 8, 10 ),

			/* Clock Tower */ /* Missing */
			createTAppearance( "TOWER_FLOOR", 13, 4 ),
			createTAppearance( "TOWER_WALL", 11, 4 ),
			createTAppearance( "TOWER_COLUMN", 4, 6 ),
			createTAppearance( "TOWER_WINDOW", 11, 4 ),
			createTAppearance( "TOWER_DOWN", 9, 10 ),
			createTAppearance( "TOWER_UP", 10, 10 ),
			createTAppearance( "CAMPANARIUM", 13, 4 ),
			createTAppearance( "TOWER_FLOOR_H", 5, 10 ),
			createTAppearance( "TOWER_STAIRS", 4, 10 ),
			createTAppearance( "CLOCK_GEAR_1", 4, 6 ),
			createTAppearance( "CLOCK_GEAR_2", 4, 6 ),
			createTAppearance( "CLOCK_GEAR_3", 4, 6 ),
			createTAppearance( "CLOCK_GEAR_4", 4, 6 ),

			/* Castle Keep */
			createTAppearance( "BARRED_WINDOW", 2, 8 ),
			createTAppearance( "DRACULA_THRONE1", 6, 8 ),
			createTAppearance( "DRACULA_THRONE2", 5, 8 ),
			createTAppearance( "DRACULA_THRONE2_X", 4, 8 ),
			createTAppearance( "BALCONY", 3, 8 ),
			createTAppearance( "STONE_STAIRWAY", 8, 8 ),
			createTAppearance( "KEEP_FLOOR", 7, 8 ),
			createTAppearance( "KEEP_WALL", 1, 8 ),
			createTAppearance( "KEEP_CARPET", 16, 4 ),

			/* Void */
			createTAppearance( "VOID_STAR", 1, 9 ), createTAppearance( "VOID_SUN", 2, 9 ),

			createAppearance( "TELEPORT", configuration.IMAGE_TERRAIN, 193, 99,
					WIDTH_NORMAL, CELL_HEIGHT, 0, WIDTH_HALF ),

			// Sacred weapons
			createIAppearance( "ART_CARD_SOL", configuration.IMAGE_ITEMS, 1, 1 ),
			createIAppearance( "ART_CARD_MOONS", configuration.IMAGE_ITEMS, 2, 1 ),
			createIAppearance( "ART_CARD_DEATH", configuration.IMAGE_ITEMS, 3, 1 ),
			createIAppearance( "ART_CARD_LOVE", configuration.IMAGE_ITEMS, 4, 1 ),
			createIAppearance( "RED_CARD", configuration.IMAGE_ITEMS, 5, 1 ),
			createIAppearance( "GOLDEN_MEDALLION", configuration.IMAGE_ITEMS, 6, 1 ),
			createIAppearance( "SILVER_MEDALLION", configuration.IMAGE_ITEMS, 7, 1 ),
			createIAppearance( "THORN_BRACELET", configuration.IMAGE_ITEMS, 8, 1 ),
			createIAppearance( "LIFE_POTION", configuration.IMAGE_ITEMS, 9, 1 ),
			createIAppearance( "FLAME_BOOK", configuration.IMAGE_ITEMS, 1, 2 ),
			createIAppearance( "ICE_BOOK", configuration.IMAGE_ITEMS, 2, 2 ),
			createIAppearance( "LIT_BOOK", configuration.IMAGE_ITEMS, 3, 2 ),
			createIAppearance( "HEART_CONTAINER", configuration.IMAGE_ITEMS, 4, 2 ),
			createIAppearance( "MIRACLE_POTION", configuration.IMAGE_ITEMS, 5, 2 ),
			createIAppearance( "TEPES_RING", configuration.IMAGE_ITEMS, 6, 2 ),
			createIAppearance( "CLUE_PAGE2", configuration.IMAGE_ITEMS, 8, 2 ),
			createIAppearance( "CLUE_PAGE3", configuration.IMAGE_ITEMS, 9, 2 ),
			createIAppearance( "JUKEBOX", configuration.IMAGE_ITEMS, 9, 2 ),
			createIAppearance( "CLUE_PAGE1", configuration.IMAGE_ITEMS, 7, 2 ),
			createIAppearance( "JUMPING_WING", configuration.IMAGE_ITEMS, 1, 3 ),
			createIAppearance( "FIRE_GEM", configuration.IMAGE_ITEMS, 2, 3 ),
			createIAppearance( "FLAME_ITEM", configuration.IMAGE_ITEMS, 3, 3 ),
			createIAppearance( "MAGIC_SHIELD", configuration.IMAGE_ITEMS, 8, 4 ),
			createIAppearance( "LIGHT_CRYSTAL", configuration.IMAGE_ITEMS, 4, 3 ),
			createIAppearance( "LANTERN", configuration.IMAGE_ITEMS, 5, 3 ),
			createIAppearance( "SOUL_RECALL", configuration.IMAGE_ITEMS, 6, 3 ),
			createIAppearance( "SUN_CARD", configuration.IMAGE_ITEMS, 7, 3 ),
			createIAppearance( "MOON_CARD", configuration.IMAGE_ITEMS, 8, 3 ),
			createIAppearance( "HEAL_POTION", configuration.IMAGE_ITEMS, 9, 3 ),
			createIAppearance( "HEAL_HERB", configuration.IMAGE_ITEMS, 1, 4 ),
			createIAppearance( "OXY_HERB", configuration.IMAGE_ITEMS, 1, 4 ),
			createIAppearance( "BIBUTI", configuration.IMAGE_ITEMS, 2, 4 ),
			createIAppearance( "GARLIC", configuration.IMAGE_ITEMS, 4, 4 ),
			createIAppearance( "TORCH", configuration.IMAGE_ITEMS, 5, 4 ),
			createIAppearance( "SILK_BAG", configuration.IMAGE_ITEMS, 6, 4 ),
			createIAppearance( "LAUREL", configuration.IMAGE_ITEMS, 7, 4 ),
			createIAppearance( "VARMOR", configuration.IMAGE_ITEMS, 1, 8 ),
			createIAppearance( "VEST", configuration.IMAGE_ITEMS, 2, 8 ),
			createIAppearance( "STUDDED_LEATHER", configuration.IMAGE_ITEMS, 3, 8 ),
			createIAppearance( "LEATHER_ARMOR", configuration.IMAGE_ITEMS, 4, 8 ),
			createIAppearance( "CLOTH_TUNIC", configuration.IMAGE_ITEMS, 5, 8 ),
			createIAppearance( "FINE_GARMENT", configuration.IMAGE_ITEMS, 5, 8 ),
			createIAppearance( "CUIRASS", configuration.IMAGE_ITEMS, 6, 8 ),
			createIAppearance( "SUIT", configuration.IMAGE_ITEMS, 7, 8 ),
			createIAppearance( "PLATE", configuration.IMAGE_ITEMS, 8, 8 ),
			createIAppearance( "DIAMOND_PLATE", configuration.IMAGE_ITEMS, 9, 8 ),
			createIAppearance( "BOW", configuration.IMAGE_ITEMS, 1, 11 ),
			createIAppearance( "HOLBEIN_DAGGER", configuration.IMAGE_ITEMS, 2, 11 ),
			createIAppearance( "WEREBANE", configuration.IMAGE_ITEMS, 3, 11 ),
			createIAppearance( "SHOTEL", configuration.IMAGE_ITEMS, 4, 11 ),
			createIAppearance( "COMBAT_KNIFE", configuration.IMAGE_ITEMS, 5, 11 ),
			createIAppearance( "STAKE", configuration.IMAGE_ITEMS, 6, 11 ),
			createIAppearance( "BASELARD", configuration.IMAGE_ITEMS, 7, 11 ),
			createIAppearance( "KAISER_KNUCKLE", configuration.IMAGE_ITEMS, 1, 12 ),
			createIAppearance( "MARTIAL_ARMBAND", configuration.IMAGE_ITEMS, 2, 12 ),
			createIAppearance( "TULKAS_FIST", configuration.IMAGE_ITEMS, 3, 12 ),
			createIAppearance( "SPIKY_KNUCKLES", configuration.IMAGE_ITEMS, 4, 12 ),
			createIAppearance( "COMBAT_GAUNTLET", configuration.IMAGE_ITEMS, 5, 12 ),
			createIAppearance( "KNUCKLES", configuration.IMAGE_ITEMS, 6, 12 ),
			createIAppearance( "GAUNTLET", configuration.IMAGE_ITEMS, 7, 12 ),
			createIAppearance( "HAMMER_JUSTICE", configuration.IMAGE_ITEMS, 1, 13 ),
			createIAppearance( "MORNING_STAR", configuration.IMAGE_ITEMS, 2, 13 ),
			createIAppearance( "FLAIL", configuration.IMAGE_ITEMS, 3, 13 ),
			createIAppearance( "MACE", configuration.IMAGE_ITEMS, 4, 13 ),
			createIAppearance( "SILVER_HANDGUN", configuration.IMAGE_ITEMS, 1, 14 ),
			createIAppearance( "REVOLVER", configuration.IMAGE_ITEMS, 2, 14 ),
			createIAppearance( "HANDGUN", configuration.IMAGE_ITEMS, 3, 14 ),
			createIAppearance( "AGUEN", configuration.IMAGE_ITEMS, 4, 14 ),
			createIAppearance( "CROSSBOW", configuration.IMAGE_ITEMS, 5, 14 ),
			createIAppearance( "ROD", configuration.IMAGE_ITEMS, 1, 15 ),
			createIAppearance( "STAFF", configuration.IMAGE_ITEMS, 2, 15 ),
			createIAppearance( "BLADE_RINGSET", configuration.IMAGE_ITEMS, 1, 16 ),
			createIAppearance( "COMBAT_RINGS", configuration.IMAGE_ITEMS, 2, 16 ),
			createIAppearance( "SPIKED_RINGS", configuration.IMAGE_ITEMS, 3, 16 ),
			createIAppearance( "RINGS", configuration.IMAGE_ITEMS, 4, 16 ),
			createIAppearance( "TOWER_SHIELD", configuration.IMAGE_ITEMS, 1, 17 ),
			createIAppearance( "BUCKLER", configuration.IMAGE_ITEMS, 2, 17 ),
			createIAppearance( "WOODEN_SHIELD", configuration.IMAGE_ITEMS, 3, 17 ),
			createIAppearance( "ROUND_SHIELD", configuration.IMAGE_ITEMS, 4, 17 ),
			createIAppearance( "SHIELD", configuration.IMAGE_ITEMS, 5, 17 ),
			createIAppearance( "DUALBLADE_SPEAR", configuration.IMAGE_ITEMS, 1, 18 ),
			createIAppearance( "HALBERD", configuration.IMAGE_ITEMS, 2, 18 ),
			createIAppearance( "ALCARDE_SPEAR", configuration.IMAGE_ITEMS, 3, 18 ),
			createIAppearance( "BATTLE_SPEAR", configuration.IMAGE_ITEMS, 4, 18 ),
			createIAppearance( "LONG_SPEAR", configuration.IMAGE_ITEMS, 5, 18 ),
			createIAppearance( "SHORT_SPEAR", configuration.IMAGE_ITEMS, 6, 18 ),
			createIAppearance( "MASAMUNE", configuration.IMAGE_ITEMS, 1, 19 ),
			createIAppearance( "CRISSAEGRIM", configuration.IMAGE_ITEMS, 2, 19 ),
			createIAppearance( "TERMINUS", configuration.IMAGE_ITEMS, 3, 19 ),
			createIAppearance( "MOURNEBLADE", configuration.IMAGE_ITEMS, 4, 19 ),
			createIAppearance( "OSAFUNE", configuration.IMAGE_ITEMS, 5, 19 ),
			createIAppearance( "MORMEGIL", configuration.IMAGE_ITEMS, 6, 19 ),
			createIAppearance( "GRAM", configuration.IMAGE_ITEMS, 7, 19 ),
			createIAppearance( "RAPIER", configuration.IMAGE_ITEMS, 8, 19 ),
			createIAppearance( "BASTARDSWORD", configuration.IMAGE_ITEMS, 9, 19 ),
			createIAppearance( "BROADSWORD", configuration.IMAGE_ITEMS, 1, 22 ),
			createIAppearance( "VORPAL_BLADE", configuration.IMAGE_ITEMS, 1, 20 ),
			createIAppearance( "FIREBRAND", configuration.IMAGE_ITEMS, 2, 20 ),
			createIAppearance( "ICEBRAND", configuration.IMAGE_ITEMS, 3, 20 ),
			createIAppearance( "GURTHANG", configuration.IMAGE_ITEMS, 4, 20 ),
			createIAppearance( "KATANA", configuration.IMAGE_ITEMS, 5, 20 ),
			createIAppearance( "FALCHION", configuration.IMAGE_ITEMS, 6, 20 ),
			createIAppearance( "HARPER", configuration.IMAGE_ITEMS, 7, 20 ),
			createIAppearance( "HADOR", configuration.IMAGE_ITEMS, 8, 20 ),
			createIAppearance( "GLADIUS", configuration.IMAGE_ITEMS, 9, 20 ),
			createIAppearance( "CUTLASS", configuration.IMAGE_ITEMS, 1, 21 ),
			createIAppearance( "CLAYMORE", configuration.IMAGE_ITEMS, 2, 21 ),
			createIAppearance( "ETHANOS_BLADE", configuration.IMAGE_ITEMS, 3, 21 ),
			createIAppearance( "FLAMBERGE", configuration.IMAGE_ITEMS, 4, 21 ),
			createIAppearance( "SABRE", configuration.IMAGE_ITEMS, 5, 21 ),
			createIAppearance( "MABLUNG", configuration.IMAGE_ITEMS, 6, 21 ),
			createIAppearance( "SCIMITAR", configuration.IMAGE_ITEMS, 7, 21 ),
			createIAppearance( "ESTOC", configuration.IMAGE_ITEMS, 8, 21 ),
			createIAppearance( "SHORT_SWORD", configuration.IMAGE_ITEMS, 9, 21 ),
			createIAppearance( "BWAKA_KNIFE", configuration.IMAGE_ITEMS, 1, 24 ),
			createIAppearance( "CHAKRAM", configuration.IMAGE_ITEMS, 2, 24 ),
			createIAppearance( "BUFFALO_STAR", configuration.IMAGE_ITEMS, 3, 24 ),
			createIAppearance( "SHURIKEN", configuration.IMAGE_ITEMS, 4, 24 ),
			createIAppearance( "THROWING_KNIFE", configuration.IMAGE_ITEMS, 5, 24 ),
			createIAppearance( "LIT_WHIP", configuration.IMAGE_ITEMS, 1, 25 ),
			createIAppearance( "FLAME_WHIP", configuration.IMAGE_ITEMS, 2, 25 ),
			createIAppearance( "VKILLERW", configuration.IMAGE_ITEMS, 3, 25 ),
			createIAppearance( "WHIP", configuration.IMAGE_ITEMS, 4, 25 ),
			createIAppearance( "CHAIN_WHIP", configuration.IMAGE_ITEMS, 5, 25 ),
			createIAppearance( "THORN_WHIP", configuration.IMAGE_ITEMS, 6, 25 ),
			createIAppearance( "LEATHER_WHIP", configuration.IMAGE_ITEMS, 7, 25 ),

			// Monsters
			createAppearance( "R_SKELETON", configuration.IMAGE_MONSTERS, 1, 1 ),
			createAppearance( "GZOMBIE", configuration.IMAGE_MONSTERS, 4, 2 ),
			createAppearance( "ZOMBIE", configuration.IMAGE_MONSTERS, 7, 8 ),
			createAppearance( "WHITE_SKELETON", configuration.IMAGE_MONSTERS, 1, 1 ),
			createAppearance( "PANTHER", configuration.IMAGE_MONSTERS, 5, 3 ),
			createBAppearance( "WARG", configuration.IMAGE_BIG_MONSTERS, 7, 1 ),
			createAppearance( "BLACK_KNIGHT", configuration.IMAGE_MONSTERS, 9, 4 ),
			createAppearance( "APE_SKELETON", configuration.IMAGE_MONSTERS, 7, 3 ),
			createBAppearance( "PARANTHROPUS", configuration.IMAGE_BIG_MONSTERS, 1, 1 ),
			createAppearance( "BAT", configuration.IMAGE_MONSTERS, 8, 3 ),
			createAppearance( "SKULL_HEAD", configuration.IMAGE_MONSTERS, 2, 6 ),
			createAppearance( "SKULL_LORD", configuration.IMAGE_MONSTERS, 3, 6 ),
			createAppearance( "MERMAN", configuration.IMAGE_MONSTERS, 9, 5 ),
			createAppearance( "WEREBEAR", configuration.IMAGE_MONSTERS, 6, 6 ),
			createAppearance( "HUNCHBACK", configuration.IMAGE_MONSTERS, 6, 7 ),
			createAppearance( "BONE_ARCHER", configuration.IMAGE_MONSTERS, 2, 1 ),
			createAppearance( "SKELETON_PANTHER", configuration.IMAGE_MONSTERS, 6, 3 ),
			createAppearance( "BONE_PILLAR", configuration.IMAGE_MONSTERS, 9, 6 ),
			createAppearance( "AXE_KNIGHT", configuration.IMAGE_MONSTERS, 1, 5 ),
			createAppearance( "MEDUSA_HEAD", configuration.IMAGE_MONSTERS, 4, 6 ),
			createAppearance( "DURGA", configuration.IMAGE_MONSTERS, 1, 4 ),
			createAppearance( "SKELETON_ATHLETE", configuration.IMAGE_MONSTERS, 3, 1 ),
			createAppearance( "BLADE_SOLDIER", configuration.IMAGE_MONSTERS, 1, 2 ),
			createAppearance( "BONE_HALBERD", configuration.IMAGE_MONSTERS, 4, 1 ),
			createAppearance( "CROW", configuration.IMAGE_MONSTERS, 4, 8 ),
			createAppearance( "BLOOD_SKELETON", configuration.IMAGE_MONSTERS, 9, 1 ),
			createAppearance( "LIZARD_SWORDSMAN", configuration.IMAGE_MONSTERS, 7, 5 ),
			createBAppearance( "COCKATRICE", configuration.IMAGE_BIG_MONSTERS, 4, 1 ),
			createAppearance( "COOPER_ARMOR", configuration.IMAGE_MONSTERS, 10, 4 ),
			createAppearance( "GHOUL", configuration.IMAGE_MONSTERS, 8, 8 ),
			createAppearance( "SALOME", configuration.IMAGE_MONSTERS, 7, 4 ),
			createAppearance( "ECTOPLASM", configuration.IMAGE_MONSTERS, 3, 3 ),
			createBAppearance( "RULER_SWORD_LV1", configuration.IMAGE_BIG_MONSTERS, 2,
					2 ),
			createBAppearance( "BEAST_DEMON", configuration.IMAGE_BIG_MONSTERS, 2, 1 ),
			createBAppearance( "DEVIL", configuration.IMAGE_BIG_MONSTERS, 3, 1 ),
			createAppearance( "BALLOON_POD", configuration.IMAGE_MONSTERS, 5, 7 ),
			createAppearance( "LILITH", configuration.IMAGE_MONSTERS, 5, 4 ),
			createAppearance( "BONE_MUSKET", configuration.IMAGE_MONSTERS, 5, 1 ),
			createAppearance( "KILLER_PLANT", configuration.IMAGE_MONSTERS, 3, 7 ),
			createAppearance( "VAMPIRE_BAT", configuration.IMAGE_MONSTERS, 9, 3 ),
			createBAppearance( "DEATH_MANTIS", configuration.IMAGE_BIG_MONSTERS, 5, 2 ),
			createAppearance( "DHURON", configuration.IMAGE_MONSTERS, 7, 2 ),
			createAppearance( "DRAGON_SKULL_CANNON", configuration.IMAGE_MONSTERS, 10,
					6 ),
			createAppearance( "MUMMY_MAN", configuration.IMAGE_MONSTERS, 5, 2 ),
			createAppearance( "ZELDO", configuration.IMAGE_MONSTERS, 8, 2 ),
			createAppearance( "MUD_MAN", configuration.IMAGE_MONSTERS, 2, 3 ),
			createAppearance( "CAGNAZOO", configuration.IMAGE_MONSTERS, 4, 5 ),
			createBAppearance( "ALRAUNE", configuration.IMAGE_BIG_MONSTERS, 4, 4 ),
			createBAppearance( "GOLEM", configuration.IMAGE_BIG_MONSTERS, 2, 3 ),
			createAppearance( "ARACHNE", configuration.IMAGE_MONSTERS, 3, 4 ),
			createAppearance( "SPEAR_SKELETON", configuration.IMAGE_MONSTERS, 8, 1 ),

			createAppearance( "KNIFE_MERMAN", configuration.IMAGE_MONSTERS, 10, 5 ),
			createAppearance( "MASTER_LIZARD", configuration.IMAGE_MONSTERS, 8, 5 ),
			createAppearance( "WHIP_SKELETON", configuration.IMAGE_MONSTERS, 6, 1 ),
			createAppearance( "FROZEN_SHADE", configuration.IMAGE_MONSTERS, 10, 2 ),
			createAppearance( "MINOTAUR", configuration.IMAGE_MONSTERS, 7, 6 ),
			createBAppearance( "TRITON", configuration.IMAGE_BIG_MONSTERS, 6, 2 ),
			createAppearance( "NOVA_SKELETON", configuration.IMAGE_MONSTERS, 10, 1 ),
			createBAppearance( "ARMOR_LORD", configuration.IMAGE_BIG_MONSTERS, 1, 3 ),
			createAppearance( "FLEA_ARMOR", configuration.IMAGE_MONSTERS, 7, 7 ),
			createAppearance( "BUER", configuration.IMAGE_MONSTERS, 4, 7 ),
			createAppearance( "WIGHT", configuration.IMAGE_MONSTERS, 9, 2 ),
			createAppearance( "SPECTER", configuration.IMAGE_MONSTERS, 4, 3 ),
			createBAppearance( "RULER_SWORD_LV2", configuration.IMAGE_BIG_MONSTERS, 3,
					2 ),
			createAppearance( "CURLY", configuration.IMAGE_MONSTERS, 2, 4 ),
			createBAppearance( "FIRE_WARG", configuration.IMAGE_BIG_MONSTERS, 1, 2 ),
			createAppearance( "BONE_ARK", configuration.IMAGE_MONSTERS, 1, 7 ),
			createAppearance( "MIMIC", configuration.IMAGE_MONSTERS, 5, 6 ),
			createBAppearance( "MANTICORE", configuration.IMAGE_BIG_MONSTERS, 7, 2 ),
			createAppearance( "FLAME_KNIGHT", configuration.IMAGE_MONSTERS, 2, 5 ),
			createBAppearance( "ARMOR_GUARDIAN", configuration.IMAGE_BIG_MONSTERS, 1, 4 ),
			createBAppearance( "DEMON_LORD", configuration.IMAGE_BIG_MONSTERS, 6, 1 ),
			createAppearance( "HEAT_SHADE", configuration.IMAGE_MONSTERS, 1, 3 ),
			createBAppearance( "FLESH_GOLEM", configuration.IMAGE_BIG_MONSTERS, 4, 3 ),
			createAppearance( "WEREWOLF", configuration.IMAGE_MONSTERS, 8, 6 ),
			createBAppearance( "ALURA_UNE", configuration.IMAGE_BIG_MONSTERS, 5, 4 ),
			createAppearance( "DRAHIGNAZOO", configuration.IMAGE_MONSTERS, 5, 5 ),
			createAppearance( "SUCCUBUS", configuration.IMAGE_MONSTERS, 6, 4 ),
			createAppearance( "BLADE_MASTER", configuration.IMAGE_MONSTERS, 2, 2 ),
			createBAppearance( "BASILISK", configuration.IMAGE_BIG_MONSTERS, 5, 1 ),
			createAppearance( "GARGOYLE", configuration.IMAGE_MONSTERS, 6, 5 ),
			createAppearance( "HARPY", configuration.IMAGE_MONSTERS, 4, 4 ),
			createAppearance( "KICKER_SKELETON", configuration.IMAGE_MONSTERS, 7, 1 ),
			createBAppearance( "BEHEMOTH", configuration.IMAGE_BIG_MONSTERS, 6, 3 ),
			createBAppearance( "DISCUS_LORD", configuration.IMAGE_BIG_MONSTERS, 7, 3 ),
			createBAppearance( "GIANT_ARMOR", configuration.IMAGE_BIG_MONSTERS, 2, 4 ),
			createAppearance( "WITCH", configuration.IMAGE_MONSTERS, 8, 4 ),
			createAppearance( "MANDRAGORA", configuration.IMAGE_MONSTERS, 8, 7 ),
			createBAppearance( "IRON_GOLEM", configuration.IMAGE_BIG_MONSTERS, 2, 3 ),
			createBAppearance( "VICTORY_ARMOR", configuration.IMAGE_BIG_MONSTERS, 3, 4 ),
			createBAppearance( "RULER_SWORD_LV3", configuration.IMAGE_BIG_MONSTERS, 4,
					2 ),
			createAppearance( "SPEAR_KNIGHT", configuration.IMAGE_MONSTERS, 3, 9 ),
			createAppearance( "FLYING_SPEAR_SKELETON", configuration.IMAGE_MONSTERS, 4,
					9 ),

			createBAppearance( "GIANTBAT", configuration.IMAGE_BIG_MONSTERS, 2, 5 ),
			createBAppearance( "DEATH", configuration.IMAGE_BIG_MONSTERS, 3, 5 ),
			createAppearance( "SICKLE", configuration.IMAGE_MONSTERS, 2, 9 ),
			createBAppearance( "DRACULA", configuration.IMAGE_BIG_MONSTERS, 4, 5 ),
			createBAppearance( "MEDUSA", configuration.IMAGE_BIG_MONSTERS, 1, 5 ),
			createAppearance( "SNAKE", configuration.IMAGE_MONSTERS, 1, 9 ),
			createBAppearance( "FRANK", configuration.IMAGE_BIG_MONSTERS, 7, 4 ),
			createAppearance( "IGOR", configuration.IMAGE_MONSTERS, 10, 8 ),
			createBAppearance( "DEMON_DRACULA", configuration.IMAGE_BIG_MONSTERS, 5, 5 ),
			createBAppearance( "AKMODAN", configuration.IMAGE_BIG_MONSTERS, 6, 5 ),
			createBAppearance( "DRAGON_KING", configuration.IMAGE_BIG_MONSTERS, 1, 6 ),
			createBAppearance( "ORLOX", configuration.IMAGE_BIG_MONSTERS, 4, 6 ),
			createBAppearance( "WATER_DRAGON", configuration.IMAGE_BIG_MONSTERS, 2, 6 ),
			createBAppearance( "LEGION", configuration.IMAGE_BIG_MONSTERS, 7, 5 ),
			createBAppearance( "CERBERUS", configuration.IMAGE_BIG_MONSTERS, 3, 6 ),
			createAppearance( "DOPPELGANGER", configuration.IMAGE_MONSTERS, 6, 5 ),

			createAppearance( "S_CAT", configuration.IMAGE_MONSTERS, 5, 9 ),
			createAppearance( "S_BIRD", configuration.IMAGE_MONSTERS, 6, 9 ),
			createAppearance( "S_TURTLE", configuration.IMAGE_MONSTERS, 7, 9 ),
			createBAppearance( "S_TIGER", configuration.IMAGE_MONSTERS, 8, 9 ),
			createAppearance( "S_EAGLE", configuration.IMAGE_MONSTERS, 9, 9 ),
			createAppearance( "S_TORTOISE", configuration.IMAGE_MONSTERS, 7, 9 ),
			createBAppearance( "S_DRAGON", configuration.IMAGE_MONSTERS, 6, 5 ),

			// Features
			createXAppearance( "CANDLE", configuration.IMAGE_FEATURES, 0, 112,
					WIDTH_HALF, WIDTH_NORMAL ),
			createIAppearance( "SMALLHEART", configuration.IMAGE_FEATURES, 2, 1 ),
			createIAppearance( "DAGGER", configuration.IMAGE_FEATURES, 3, 1 ),
			createIAppearance( "AXE", configuration.IMAGE_FEATURES, 4, 1 ),
			createIAppearance( "VIAL", configuration.IMAGE_FEATURES, 8, 1 ),
			createIAppearance( "CROSS", configuration.IMAGE_FEATURES, 5, 1 ),
			createIAppearance( "CLOCK", configuration.IMAGE_FEATURES, 6, 1 ),
			createIAppearance( "BIGHEART", configuration.IMAGE_FEATURES, 1, 2 ),
			createIAppearance( "KEY", configuration.IMAGE_FEATURES, 2, 2 ),
			createIAppearance( "UPGRADE", configuration.IMAGE_FEATURES, 3, 2 ),
			createIAppearance( "ROSARY", configuration.IMAGE_FEATURES, 5, 2 ),
			createIAppearance( "COIN", configuration.IMAGE_FEATURES, 6, 2 ),
			createIAppearance( "RED_MONEY_BAG", configuration.IMAGE_FEATURES, 7, 2 ),
			createIAppearance( "BLUE_MONEY_BAG", configuration.IMAGE_FEATURES, 8, 2 ),
			createIAppearance( "WHITE_MONEY_BAG", configuration.IMAGE_FEATURES, 9, 2 ),
			createIAppearance( "CROWN", configuration.IMAGE_FEATURES, 1, 3 ),
			createIAppearance( "CHEST", configuration.IMAGE_FEATURES, 2, 3 ),
			createIAppearance( "MOAUI_HEAD", configuration.IMAGE_FEATURES, 3, 3 ),
			createIAppearance( "RAINBOW_MONEY_BAG", configuration.IMAGE_FEATURES, 10,
					2 ),
			createIAppearance( "POT_ROAST", configuration.IMAGE_FEATURES, 4, 3 ),
			createIAppearance( "INVISIBILITY_POTION", configuration.IMAGE_FEATURES, 5,
					3 ),
			createIAppearance( "BIBLE", configuration.IMAGE_FEATURES, 7, 1 ),
			createIAppearance( "CRYSTAL", configuration.IMAGE_FEATURES, 9, 1 ),
			createIAppearance( "FIST", configuration.IMAGE_FEATURES, 10, 1 ),
			createIAppearance( "REBOUND_CRYSTAL", configuration.IMAGE_FEATURES, 9, 1 ),
			createIAppearance( "MUPGRADE", configuration.IMAGE_FEATURES, 4, 2 ),
			createXAppearance( "URN_FLAME", configuration.IMAGE_FEATURES, WIDTH_NORMAL,
					112, WIDTH_HALF, WIDTH_NORMAL, 12 ),
			createIAppearance( "BLAST_CRYSTAL", configuration.IMAGE_FEATURES, 9, 1 ),

			createXAppearance( "FLAME", configuration.IMAGE_EFFECTS, 416, 446,
					WIDTH_NORMAL, WIDTH_NORMAL ),
			createAppearance( "MOUND", configuration.IMAGE_EFFECTS, 11, 17 ),

			// Characters
			createAppearance( "VKILLER", configuration.IMAGE_CHARACTERS, 1, 1 ),
			createAppearance( "VANQUISHER", configuration.IMAGE_CHARACTERS, 3, 1 ),
			createAppearance( "RENEGADE", configuration.IMAGE_CHARACTERS, 5, 1 ),
			createAppearance( "INVOKER", configuration.IMAGE_CHARACTERS, 1, 2 ),
			createAppearance( "MANBEAST", configuration.IMAGE_CHARACTERS, 3, 2 ),
			createAppearance( "BEAST", configuration.IMAGE_CHARACTERS, 5, 2 ),
			createAppearance( "KNIGHT", configuration.IMAGE_CHARACTERS, 1, 3 ),

			createAppearance( "VKILLER_W", configuration.IMAGE_CHARACTERS, 2, 1 ),
			createAppearance( "SONIA_B", configuration.IMAGE_CHARACTERS, 2, 1 ),
			createAppearance( "VANQUISHER_W", configuration.IMAGE_CHARACTERS, 4, 1 ),
			createAppearance( "RENEGADE_W", configuration.IMAGE_CHARACTERS, 6, 1 ),
			createAppearance( "INVOKER_W", configuration.IMAGE_CHARACTERS, 2, 2 ),
			createAppearance( "MANBEAST_W", configuration.IMAGE_CHARACTERS, 4, 2 ),
			createAppearance( "BEAST_W", configuration.IMAGE_CHARACTERS, 6, 2 ),
			createAppearance( "KNIGHT_W", configuration.IMAGE_CHARACTERS, 2, 3 ),

			createAppearance( "MORPHED_WOLF", configuration.IMAGE_MONSTERS, 1, 10 ),
			createAppearance( "MORPHED_WOLF2", configuration.IMAGE_MONSTERS, 2, 10 ),
			createAppearance( "MORPHED_BAT", configuration.IMAGE_MONSTERS, 3, 10 ),
			createAppearance( "MORPHED_BAT2", configuration.IMAGE_MONSTERS, 4, 10 ),
			createAppearance( "MORPHED_MYST", configuration.IMAGE_MONSTERS, 5, 10 ),
			createAppearance( "MORPHED_MYST2", configuration.IMAGE_MONSTERS, 6, 10 ),
			createAppearance( "MORPHED_WEREBEAR", configuration.IMAGE_MONSTERS, 7, 10 ),
			createAppearance( "MORPHED_WEREDEMON", configuration.IMAGE_MONSTERS, 8, 10 ),
			createAppearance( "MORPHED_WEREWOLF", configuration.IMAGE_MONSTERS, 10, 10 ),
			createAppearance( "MORPHED_WEREBEAST", configuration.IMAGE_MONSTERS, 9, 10 ),
			createAppearance( "MORPHED_LUPINE", configuration.IMAGE_CHARACTERS, 5, 2 ),

			createAppearance( "SOLIEYU_B_KID", configuration.IMAGE_CHARACTERS, 6, 6 ),
			createAppearance( "MAN", configuration.IMAGE_CHARACTERS, 3, 3 ),
			createAppearance( "WOMAN", configuration.IMAGE_CHARACTERS, 4, 3 ),
			createAppearance( "OLDMAN", configuration.IMAGE_CHARACTERS, 5, 3 ),
			createAppearance( "OLDWOMAN", configuration.IMAGE_CHARACTERS, 6, 3 ),
			createAppearance( "MERCHANT", configuration.IMAGE_CHARACTERS, 1, 4 ),
			createAppearance( "PRIEST", configuration.IMAGE_CHARACTERS, 2, 4 ),
			createAppearance( "DOG", configuration.IMAGE_CHARACTERS, 3, 4 ),
			createAppearance( "HOSTAGE_GUY", configuration.IMAGE_CHARACTERS, 4, 4 ),
			createAppearance( "HOSTAGE_GIRL", configuration.IMAGE_CHARACTERS, 5, 4 ),
			createAppearance( "CLARA", configuration.IMAGE_CHARACTERS, 1, 6 ),
			createAppearance( "VINDELITH", configuration.IMAGE_CHARACTERS, 1, 6 ),
			createAppearance( "CLAW", configuration.IMAGE_CHARACTERS, 5, 5 ),
			createAppearance( "MAIDEN", configuration.IMAGE_CHARACTERS, 4, 5 ),
			createAppearance( "MELDUCK", configuration.IMAGE_CHARACTERS, 3, 5 ),
			createAppearance( "ICEY", configuration.IMAGE_CHARACTERS, 4, 6 ),
			createAppearance( "LARDA", configuration.IMAGE_CHARACTERS, 3, 3 ),
			createAppearance( "CHRISTOPHER_BELMONT_NPC", configuration.IMAGE_CHARACTERS,
					3, 6 ),
			createAppearance( "BARRETT", configuration.IMAGE_CHARACTERS, 5, 6 ),

				// Weapons
		};
	}

}