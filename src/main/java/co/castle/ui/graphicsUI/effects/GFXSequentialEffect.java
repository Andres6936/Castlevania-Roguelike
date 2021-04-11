package co.castle.ui.graphicsUI.effects;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXSequentialEffect extends GFXEffect {
	private Vector sequence;
	private final Image[] tiles;

	public GFXSequentialEffect(String ID, Vector sequence, Image[] tiles, int delay,
							   Asset configuration) {
		super(ID, configuration);
		setAnimationDelay(delay);
		this.tiles = tiles;
		this.sequence = sequence;
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		si.saveBuffer( );
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		int tileIndex = 0;
		Enumeration seq = sequence.elements( );
		while ( seq.hasMoreElements( ) )
		{
			Position nextPosition = Position.add( center, (Position) seq.nextElement( ) );
			tileIndex++;
			if ( tileIndex == tiles.length )
				tileIndex = 0;
			if ( ui.insideViewPort( nextPosition ) )
				si.drawImage( nextPosition.x * configuration.NORMAL_TILE_WIDTH,
						nextPosition.y * configuration.NORMAL_TILE_WIDTH,
						tiles[ tileIndex ] );
			si.refresh( );
			animationPause( );
		}
		si.restore( );
	}

}