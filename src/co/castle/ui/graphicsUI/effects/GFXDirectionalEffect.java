package co.castle.ui.graphicsUI.effects;

import co.castle.conf.gfx.data.Asset;
import sz.util.Position;

public abstract class GFXDirectionalEffect extends GFXEffect
{
	protected int depth;
	protected int direction;

	public GFXDirectionalEffect( String id, Asset configuration )
	{
		super( id, configuration );
	}

	public GFXDirectionalEffect( String id, int delay, Asset configuration )
	{
		super( id, delay, configuration );
	}

	public void set( Position position, int pDirection, int pDepth )
	{
		super.set( position );
		direction = pDirection;
		depth = pDepth;
	}
}
