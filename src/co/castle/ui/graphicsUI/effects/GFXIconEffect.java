package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXIconEffect extends GFXEffect
{
	private Image tile;

	public GFXIconEffect(	String ID, Image tile, int delay,
							Asset configuration )
	{
		super( ID, configuration );
		this.tile = tile;
		setAnimationDelay( delay );
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		si.saveBuffer( );
		// si.setAutoRefresh(false);
		int height = 0;
		if ( ui.getPlayer( ).getLevel( ).getMapCell( getPosition( ) ) != null )
			height = ui.getPlayer( ).getLevel( ).getMapCell( getPosition( ) )
					.getHeight( );
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		if ( ui.insideViewPort( center ) )
			si.drawImage( center.x * configuration.NORMAL_TILE_WIDTH,
					center.y * configuration.NORMAL_TILE_WIDTH - 4 * height, tile );
		si.refresh( );
		animationPause( );
		si.restore( );
	}
}