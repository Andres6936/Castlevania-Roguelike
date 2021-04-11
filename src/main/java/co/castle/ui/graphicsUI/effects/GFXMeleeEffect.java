package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.action.Action;
import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXMeleeEffect extends GFXDirectionalEffect
{
	private Image[ ] missile;

	public GFXMeleeEffect(	String ID, Image[ ] missile, int delay,
							Asset configuration )
	{
		super( ID, delay, configuration );
		setMissile( missile );
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		Image icon = null;
		si.saveBuffer( );
		setAnimationDelay( animationDelay );
		Position var = Action.directionToVariation( direction );
		switch ( direction )
		{
		case Action.RIGHT:
			icon = missile[ 0 ];
			break;
		case Action.UP:
			icon = missile[ 1 ];
			break;
		case Action.LEFT:
			icon = missile[ 2 ];
			break;
		case Action.DOWN:
			icon = missile[ 3 ];
			break;
		case Action.DOWNRIGHT:
			icon = missile[ 4 ];
			break;
		case Action.DOWNLEFT:
			icon = missile[ 5 ];
			break;
		case Action.UPLEFT:
			icon = missile[ 6 ];
			break;
		case Action.UPRIGHT:
			icon = missile[ 7 ];
			break;
		}
		if ( icon == null )
		{
			si.restore( );
			si.refresh( );
			return;
		}

		Position runner = new Position( getPosition( ) );
		for ( int i = 0; i < depth; i++ )
		{
			runner.add( var );
			int height = 0;
			if ( ui.getPlayer( ).getLevel( ).getMapCell( runner ) != null )
				height = ui.getPlayer( ).getLevel( ).getMapCell( runner ).getHeight( );
			Position relative = Position.subs( runner, ui.getPlayer( ).getPosition( ) );
			Position toPrint = Position.add( ui.PC_POS, relative );
			/*
			 * if (!ui.insideViewPort(toPrint)) break;
			 */
			si.drawImage( toPrint.x( ) * configuration.NORMAL_TILE_WIDTH + 8,
					toPrint.y( ) * configuration.NORMAL_TILE_WIDTH + 8 - 4 * height,
					icon );
			si.refresh( );
			animationPause( );
			si.restore( );
		}

	}

	public void setMissile( Image[ ] value )
	{
		missile = value;
	}
}