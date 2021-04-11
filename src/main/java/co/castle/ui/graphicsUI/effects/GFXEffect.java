package co.castle.ui.graphicsUI.effects;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.effects.Effect;
import co.castle.ui.graphicsUI.GFXUserInterface;

public abstract class GFXEffect extends Effect
{

	protected Asset configuration;

	public GFXEffect( String ID, Asset configuration )
	{
		super( ID );
		this.configuration = configuration;
	}

	public GFXEffect( String id, int delay, Asset configuration )
	{
		super( id, delay );
		this.configuration = configuration;
	}

	public abstract void drawEffect( GFXUserInterface ui, ApplicationGraphics si );

}
