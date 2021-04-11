package co.castle.ui.graphicsUI.effects;

import java.awt.Color;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;

public class GFXFlashEffect extends GFXEffect
{
	private Color color;

	public GFXFlashEffect( String ID, Color color, Asset configuration )
	{
		super( ID, configuration );
		this.color = color;
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		si.flash( color );
		// animationPause();
	}

}