package co.castle.ui.graphicsUI.effects;

import java.awt.Image;

import co.castle.conf.gfx.data.Asset;
import co.castle.main.ApplicationGraphics;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.util.Position;

public class GFXAnimatedEffect extends GFXEffect {
	private final Image[] frames;

	private int xoff, yoff;

	public GFXAnimatedEffect(String ID, Image[] frames, int delay,
							 Asset configuration) {
		super(ID, delay, configuration);
		this.frames = frames;
	}

	public GFXAnimatedEffect(	String ID, Image[ ] frames, int delay, int xoff, int yoff,
								Asset configuration )
	{
		this( ID, frames, delay, configuration );
		this.xoff = xoff;
		this.yoff = yoff;
	}

	public void drawEffect( GFXUserInterface ui, ApplicationGraphics si )
	{
		int height = 0;
		if (ui.getPlayer().getLevel().getMapCell(getPosition()) != null)
			height = ui.getPlayer().getLevel().getMapCell(getPosition())
					.getHeight();
		Position relative = Position.subs(getPosition(),
				ui.getPlayer().getPosition());
		Position absolute = Position.add(ui.PC_POS, relative);
		if (!ui.insideViewPort(absolute))
			return;
		si.saveBuffer();
		for (Image frame : frames) {
			si.drawImage(absolute.x * configuration.NORMAL_TILE_WIDTH + xoff,
					absolute.y * configuration.NORMAL_TILE_WIDTH - 4 * height + yoff,
					frame);
			si.refresh();
			animationPause();
			si.restore();
		}
	}
}
