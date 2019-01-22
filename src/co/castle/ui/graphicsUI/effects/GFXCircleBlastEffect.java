package co.castle.ui.graphicsUI.effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXCircleBlastEffect extends GFXEffect
{
	private int ADVANCE = 9;
	private Color blastColor;

	public GFXCircleBlastEffect(	String ID, Color blastColor, int delay,
									Asset configuration )
	{
		super( ID, delay, configuration );
		this.blastColor = blastColor;
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		ui.refresh( );
		si.saveBuffer( );
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		Graphics2D g = si.getGraphics2D( );
		Stroke oldStroke = g.getStroke( );
		g.setStroke( new BasicStroke( 10 ) );
		g.setColor( blastColor );
		int xcenter = center.x * configuration.NORMAL_TILE_WIDTH
				+ configuration.HALF_TILE_WIDTH;
		int ycenter = center.y * configuration.NORMAL_TILE_WIDTH
				+ configuration.HALF_TILE_WIDTH;
		for ( int i = 0; i < 30; i++ )
		{
			g.fillOval( xcenter - i * ( ADVANCE + i ), ycenter - i * ( ADVANCE + i ),
					i * ( ADVANCE + i ) * 2, i * ( ADVANCE + i ) * 2 );
			si.refresh( );
			animationPause( );
			// si.restore();
		}
		g.setStroke( oldStroke );
		si.cls( );
		si.restore( );
		si.refresh( );
	}
}