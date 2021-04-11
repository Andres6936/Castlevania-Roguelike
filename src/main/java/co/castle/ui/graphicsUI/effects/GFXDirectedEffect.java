package co.castle.ui.graphicsUI.effects;

import co.castle.conf.gfx.data.Asset;
import sz.util.Line;
import sz.util.Position;

public abstract class GFXDirectedEffect extends GFXEffect
{
	protected int depth;
	protected Line effectLine;

	public GFXDirectedEffect( String id, Asset configuration )
	{
		super( id, configuration );
	}

	public GFXDirectedEffect( String id, int delay, Asset configuration )
	{
		super( id, delay, configuration );
	}

	/*
	 * public void drawEffect(GFXUserInterface ui, SwingSystemInterface si){
	 * Position toPrint = null; for (int i = 0; i < depth; i+=10){ Position next =
	 * effectLine.next(); Position relative = Position.subs(next,
	 * ui.getPlayer().getPosition()); toPrint = Position.add(ui.PC_POS, relative);
	 * if (!ui.insideViewPort(toPrint)) break; } startPosition =
	 * Position.add(ui.PC_POS, Position.subs(startPosition,
	 * ui.getPlayer().getPosition())); toPrint = Position.mul(toPrint, 36);
	 * startPosition = Position.mul(startPosition, 36); depth =
	 * Position.distance(startPosition, toPrint); effectLine = new
	 * Line(startPosition, toPrint); }
	 */

	public int getDepth( )
	{
		return depth;
	}

	public void set(	Position loc, Position startPosition, Position pivotPosition,
						int depth )
	{
        super.set(loc);
        Position startPosition1 = new Position(loc);
        effectLine = new Line(startPosition, pivotPosition);
        setDepth(depth);
    }

	public void setDepth( int value )
	{
		depth = value;
	}

}
