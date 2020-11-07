package co.castle.conf.gfx.data;

import sz.util.ImageUtils;
import sz.util.Position;
import sz.util.PropertyFilters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * In this class is saved the configuration parameters and asset for user
 * interface, grant part of this field are constant
 * 
 * @author Tuukka Turto
 */
public final class Asset
{
	public final float SCREEN_SCALE;

	public final int SCREEN_WIDTH;
	public final int SCREEN_HEIGHT;
	public final int SCREEN_WIDTH_IN_TILES;
	public final int SCREEN_HEIGHT_IN_TILES;

    final int BIG_TILE_WIDTH;
	public final int HALF_TILE_WIDTH;
	public final int NORMAL_TILE_WIDTH;

    final int CELL_HEIGHT;

	public BufferedImage IMAGE_TITLE;
	public BufferedImage IMAGE_PROLOGUE;
	public BufferedImage IMAGE_RESUME;
	public BufferedImage IMAGE_BACKGROUND;
	public BufferedImage IMAGE_ENDGAME;
	public BufferedImage IMAGE_HISCORES;
	public BufferedImage IMAGE_LEVEL_UP;
	public BufferedImage IMAGE_SAVED;
	public BufferedImage IMAGE_MAP;
	public BufferedImage IMAGE_MAPMARKER;
	public BufferedImage IMAGE_PICKER;
	public BufferedImage IMAGE_BORDERS;

	public BufferedImage IMAGE_AIM_LINE_TILE;
	public BufferedImage IMAGE_STEPS_TILE;
	public BufferedImage IMAGE_SCAN_TILE;

    BufferedImage IMAGE_CHARACTERS;
    BufferedImage IMAGE_MONSTERS;
    BufferedImage IMAGE_BIG_MONSTERS;
    BufferedImage IMAGE_TERRAIN;
    BufferedImage IMAGE_NIGHT_TERRAIN;
    BufferedImage IMAGE_DARK_NIGHT_TERRAIN;
    BufferedImage IMAGE_DARK_TERRAIN;
    BufferedImage IMAGE_EFFECTS;
    BufferedImage IMAGE_FEATURES;
    BufferedImage IMAGE_ITEMS;
    BufferedImage IMAGE_SHADOW;

	public final Color COLOR_BORDER_INNER;
	public final Color COLOR_BORDER_OUTER;
	public final Color COLOR_BACKGROUND;
	public final Color COLOR_BOLD;

	public Font FONT_TITLE;
	public Font FONT_TEXT;
	public Font FONT_MESSAGE_BOX;
	public Font FONT_MESSAGE_BOX_PERSISTANT;

	private final Position playerLocationOnScreen;

	// We make the constructor private to prevent the use of 'new'
    public Asset( final Properties configuration )
	{
		SCREEN_SCALE = Float.parseFloat( configuration.getProperty( "SCREEN_SCALE" ) );

		SCREEN_WIDTH = Integer.parseInt(configuration.getProperty("WINDOW_WIDTH"));
		SCREEN_HEIGHT = Integer.parseInt(configuration.getProperty("WINDOW_HEIGHT"));
		SCREEN_WIDTH_IN_TILES = Integer.parseInt(configuration.getProperty("XRANGE"));
		SCREEN_HEIGHT_IN_TILES = Integer.parseInt(configuration.getProperty("YRANGE"));

		BIG_TILE_WIDTH = Integer.parseInt(configuration.getProperty("BIG_TILESIZE"));
		HALF_TILE_WIDTH = Integer.parseInt(configuration.getProperty("HALF_TILESIZE"));
		NORMAL_TILE_WIDTH = Integer.parseInt(configuration.getProperty("TILESIZE"));

		CELL_HEIGHT = Integer.parseInt(configuration.getProperty("CELL_HEIGHT"));
		int GADGET_SIZE = Integer.parseInt(configuration.getProperty("GADGETSIZE"));

		COLOR_BORDER_INNER = PropertyFilters.getColor(configuration.getProperty("COLOR_BORDER_IN"));
		COLOR_BORDER_OUTER = PropertyFilters.getColor(configuration.getProperty("COLOR_BORDER_OUT"));
		COLOR_BACKGROUND = PropertyFilters.getColor(configuration.getProperty("COLOR_BACKGROUND"));
		COLOR_BOLD = PropertyFilters.getColor(configuration.getProperty("COLOR_BOLD"));

		// NOTE: This is a big problem, move and delete of here
		playerLocationOnScreen = PropertyFilters.getPosition(configuration.getProperty("PC_POS"));

		// Load images, parameters and fonts, block try/catch is necessary.
		try
		{
			IMAGE_TITLE = ImageUtils.createImage( configuration.getProperty( "IMG_TITLE" ) );
			IMAGE_PROLOGUE = ImageUtils.createImage( configuration.getProperty( "IMG_PROLOGUE" ) );
			IMAGE_RESUME = ImageUtils.createImage( configuration.getProperty( "IMG_RESUME" ) );
			IMAGE_BACKGROUND = ImageUtils.createImage( configuration.getProperty( "IMG_BACKGROUND" ) );
			IMAGE_ENDGAME = ImageUtils.createImage( configuration.getProperty( "IMG_ENDGAME" ) );
			IMAGE_HISCORES = ImageUtils.createImage( configuration.getProperty( "IMG_HISCORES" ) );
			IMAGE_LEVEL_UP = ImageUtils.createImage(configuration.getProperty("IMG_LEVEL_UP"));
			IMAGE_SAVED = ImageUtils.createImage(configuration.getProperty("IMG_SAVED"));
			IMAGE_MAP = ImageUtils.createImage(configuration.getProperty("IMG_MAP"));

			IMAGE_MAPMARKER = PropertyFilters.getImage(configuration.getProperty("IMG_MAPMARKER"),
					configuration.getProperty("IMG_MAPMARKER_BOUNDS"));
			IMAGE_PICKER = PropertyFilters.getImage(configuration.getProperty("IMG_PICKER"),
					configuration.getProperty("IMG_PICKER_BOUNDS"));
			IMAGE_BORDERS = PropertyFilters.getImage(configuration.getProperty("IMG_BORDERS"),
					configuration.getProperty("IMG_BORDERS_BOUNDS"));
			BufferedImage IMAGE_GADGETS = PropertyFilters.getImage(configuration.getProperty("IMG_GADGETS"),
					configuration.getProperty("IMG_GADGETS_BOUNDS"));

			IMAGE_AIM_LINE_TILE = ImageUtils.crearImagen(IMAGE_GADGETS, 0, 0, GADGET_SIZE, GADGET_SIZE);
			IMAGE_STEPS_TILE = ImageUtils.crearImagen(IMAGE_GADGETS, GADGET_SIZE * 2, 0, GADGET_SIZE,
					GADGET_SIZE);
			IMAGE_SCAN_TILE = ImageUtils.crearImagen(IMAGE_GADGETS, GADGET_SIZE, 0, GADGET_SIZE,
					GADGET_SIZE);

			IMAGE_CHARACTERS = ImageUtils.createImage(configuration.getProperty("TILES_CHARACTERS"));
			IMAGE_MONSTERS = ImageUtils.createImage(configuration.getProperty("TILES_MONSTERS"));
			IMAGE_BIG_MONSTERS = ImageUtils.createImage(configuration.getProperty("TILES_BIG_MONSTERS"));
			IMAGE_TERRAIN = ImageUtils.createImage(configuration.getProperty("TILES_TERRAIN"));
			IMAGE_NIGHT_TERRAIN = ImageUtils
					.createImage(configuration.getProperty("TILES_NIGHT_TERRAIN"));
			IMAGE_DARK_NIGHT_TERRAIN = ImageUtils
					.createImage(configuration.getProperty("TILES_DARK_NIGHT_TERRAIN"));
			IMAGE_DARK_TERRAIN = ImageUtils.createImage(configuration.getProperty("TILES_DARK_TERRAIN"));
			IMAGE_EFFECTS = ImageUtils.createImage( configuration.getProperty( "TILES_EFFECTS" ) );
			IMAGE_FEATURES = ImageUtils.createImage( configuration.getProperty( "TILES_FEATURES" ) );
			IMAGE_ITEMS = ImageUtils.createImage( configuration.getProperty( "TILES_ITEMS" ) );
			IMAGE_SHADOW = ImageUtils.createImage( configuration.getProperty( "TILES_SHADOW" ) );

			FONT_TITLE = PropertyFilters.getFont( configuration.getProperty( "FNT_TITLE" ),
					configuration.getProperty( "FNT_TITLE_SIZE" ) );
			FONT_TEXT = PropertyFilters.getFont( configuration.getProperty( "FNT_TEXT" ),
					configuration.getProperty( "FNT_TEXT_SIZE" ) );
			FONT_MESSAGE_BOX = PropertyFilters.getFont( configuration.getProperty( "FNT_MESSAGEBOX" ),
					configuration.getProperty( "FNT_MESSAGEBOX_SIZE" ) );
			FONT_MESSAGE_BOX_PERSISTANT = PropertyFilters.getFont(
					configuration.getProperty( "FNT_PERSISTANTMESSAGEBOX" ),
					configuration.getProperty( "FNT_PERSISTANTMESSAGEBOX_SIZE" ) );
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage( ) + "\nError loading image, parameters or fonts." );
		}
	}

	/**
	 * @return the player location on screen
	 */
	public Position getPlayerLocationOnScreen( )
	{
		return playerLocationOnScreen;
	}
}
