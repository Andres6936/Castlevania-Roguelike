package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.action.Action;
import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXDirectionalMissileEffect extends GFXDirectedEffect
{
	private Image[ ] missile;

	public GFXDirectionalMissileEffect(	String ID, Image[ ] missile, int delay,
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
		Position oldPoint = effectLine.next( );
		for ( int i = 0; i < depth; i++ )
		{
			Position next = effectLine.next( );

			// int direction = solveDirection(getPosition(), oldPoint);
			int direction = solveDirection( oldPoint, next );
			oldPoint = new Position( next );
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
					icon = missile[5];
					break;
				case Action.UPLEFT:
					icon = missile[6];
					break;
				case Action.UPRIGHT:
					icon = missile[7];
					break;
			}
			final int height = getHeight(ui, next);
			Position relative = Position.subs(next, ui.getPlayer().getPosition());
			Position toPrint = Position.add(ui.PC_POS, relative);
			if (!ui.insideViewPort(toPrint))
				break;
			si.drawImage(toPrint.x() * configuration.NORMAL_TILE_WIDTH,
					toPrint.y() * configuration.NORMAL_TILE_WIDTH - 4 * height,
					icon);
			si.refresh();
			animationPause();
			si.restore();
		}

		/*
		 * for (int i = 0; i < depth; i++){ si.drawImage(next.x(), next.y(), icon);
		 * si.refresh(); animationPause(); si.restore(); for (int j = 0; j < 2; j++, i++
		 * ) next = effectLine.next(); } setAnimationDelay(animationDelay * 36);
		 */
	}

	public void setMissile( Image[ ] value )
	{
		missile = value;
	}

	private int solveDirection( Position old, Position newP )
	{
		if ( newP.x( ) == old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
			{
				return Action.DOWN;
			}
			else
			{
				return Action.UP;
			}
		}
		else if ( newP.y( ) == old.y( ) )
		{
			if ( newP.x( ) > old.x( ) )
			{
				return Action.RIGHT;
			}
			else
			{
				return Action.LEFT;
			}
		}
		else if ( newP.x( ) < old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNLEFT;
			else
				return Action.UPLEFT;
		}
		else
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNRIGHT;
			else
				return Action.UPRIGHT;
		}
	}

}