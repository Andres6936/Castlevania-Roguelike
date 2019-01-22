package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXSplashEffect extends GFXEffect
{
	private Image[ ] tiles;

	public GFXSplashEffect(	String ID, Image[ ] tiles, int delay,
							Asset configuration )
	{
		super( ID, delay, configuration );
		this.tiles = tiles;
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		si.saveBuffer( );
		// ui.refresh();
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		int height = 0;
		if ( ui.getPlayer( ).getLevel( ).getMapCell( getPosition( ) ) != null )
			height = ui.getPlayer( ).getLevel( ).getMapCell( getPosition( ) )
					.getHeight( );
		si.drawImage( center.x * configuration.NORMAL_TILE_WIDTH, center.y * configuration.NORMAL_TILE_WIDTH,
				tiles[ 0 ] );

		for ( int ring = 1; ring < tiles.length; ring++ )
		{
			drawCircle( ui, si, center, ring, tiles[ ring ], height );
			si.refresh( );
			animationPause( );
		}
		/*
		 * si.cls(); ui.refresh();
		 */
	}

	private void drawCircle(	GFXUserInterface ui, ApplicationGraphics si, Position p,
								int radius, Image tile, int height )
	{
		int d = 3 - ( 2 * radius );
		Position runner = new Position( 0, radius );
		Position zero = new Position( 0, 0 );
		while ( true )
		{
			if ( Position.flatDistance( zero, runner ) <= radius )
				drawCirclePixels( ui, si, p, runner.x, runner.y, tile, height );
			if ( d < 0 )
				d = d + ( 4 * runner.x ) + 6;
			else
			{

				// d = d + 4 * (x-y) + 10;
				d = d + 4 * ( runner.x - runner.y ) + 10;
				runner.y--;
			}
			runner.x++;
			if ( runner.y == 0 )
				break;
		}

	}

	private void drawCirclePixels(	GFXUserInterface ui, ApplicationGraphics si,
									Position center, int x, int y, Image tile,
									int height )
	{
		if ( ui.insideViewPort( center.x + x, center.y + y ) )
			si.drawImage( ( center.x + x ) * configuration.NORMAL_TILE_WIDTH,
					( center.y + y ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x + x, center.y - y ) )
			si.drawImage( ( center.x + x ) * configuration.NORMAL_TILE_WIDTH,
					( center.y - y ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x - x, center.y + y ) )
			si.drawImage( ( center.x - x ) * configuration.NORMAL_TILE_WIDTH,
					( center.y + y ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x - x, center.y - y ) )
			si.drawImage( ( center.x - x ) * configuration.NORMAL_TILE_WIDTH,
					( center.y - y ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x + y, center.y + x ) )
			si.drawImage( ( center.x + y ) * configuration.NORMAL_TILE_WIDTH,
					( center.y + x ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x + y, center.y - x ) )
			si.drawImage( ( center.x + y ) * configuration.NORMAL_TILE_WIDTH,
					( center.y - x ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x - y, center.y + x ) )
			si.drawImage( ( center.x - y ) * configuration.NORMAL_TILE_WIDTH,
					( center.y + x ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
		if ( ui.insideViewPort( center.x - y, center.y - x ) )
			si.drawImage( ( center.x - y ) * configuration.NORMAL_TILE_WIDTH,
					( center.y - x ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					tile );
	}
}