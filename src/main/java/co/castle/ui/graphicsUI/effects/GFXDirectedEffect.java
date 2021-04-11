package co.castle.ui.graphicsUI.effects;

import co.castle.conf.gfx.data.Asset;
import co.castle.level.Cell;
import co.castle.ui.graphicsUI.GFXUserInterface;
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

	public void setDepth(int value) {
		depth = value;
	}

	protected int getCellHeight(final GFXUserInterface userInterface, final Position next) {
		final Cell mapCell = userInterface.getPlayer().getLevel().getMapCell(next);
		return mapCell == null ? 0 : mapCell.getHeight();
	}

}
