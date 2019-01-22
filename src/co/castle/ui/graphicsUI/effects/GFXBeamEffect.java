package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXBeamEffect extends GFXDirectedEffect
{
	private Image[ ] missile;

	public GFXBeamEffect(	String ID, Image[ ] missile, int delay,
							Asset configuration )
	{
		super( ID, configuration );
		setMissile( missile );
		setAnimationDelay( delay );

	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		si.saveBuffer( );
		int too = 0;
		for ( int i = 0; i < depth; i++ )
		{
			Position next = effectLine.next( );
			too++;
			if ( too == missile.length )
				too = 0;
			int height = 0;
			if ( ui.getPlayer( ).getLevel( ).getMapCell( next ) != null )
				height = ui.getPlayer( ).getLevel( ).getMapCell( next ).getHeight( );
			Position relative = Position.subs( next, ui.getPlayer( ).getPosition( ) );
			Position toPrint = Position.add( ui.PC_POS, relative );
			if ( !ui.insideViewPort( toPrint ) )
				break;
			si.drawImage( toPrint.x( ) * configuration.NORMAL_TILE_WIDTH,
					toPrint.y( ) * configuration.NORMAL_TILE_WIDTH - 4 * height,
					missile[ too ] );
			si.refresh( );
			animationPause( );
		}
		si.restore( );
	}

	public void setMissile( Image[ ] value )
	{
		missile = value;
	}

}