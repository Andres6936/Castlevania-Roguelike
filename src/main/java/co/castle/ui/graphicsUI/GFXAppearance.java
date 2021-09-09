package co.castle.ui.graphicsUI;

import co.castle.ui.Appearance;

import java.awt.*;

public class GFXAppearance extends Appearance {
	private Image darkImage;
	private Image darkniteImage;
	private final Image img;
	private Image niteImage;
	private final int superWidth;
	private final int superHeight;

	public GFXAppearance(String serial, Image pimg, Image darkImage, Image niteImage,
						 Image darkniteImage, int superWidth, int superHeight) {
		super(serial);
		img = pimg;
		this.darkImage = darkImage;
		this.niteImage = niteImage;
		this.darkniteImage = darkniteImage;
		this.superHeight = superHeight;
		this.superWidth = superWidth;
	}

	public GFXAppearance(String serial, Image pimg, int superWidth, int superHeight) {
		super(serial);
		img = pimg;
		this.superHeight = superHeight;
		this.superWidth = superWidth;
	}

	public Image getDarkImage( )
	{
		return darkImage;
	}

	public Image getDarkniteImage( )
	{
		return darkniteImage;
	}

	public Image getImage( )
	{
		return img;
	}

	public Image getNiteImage( )
	{
		return niteImage;
	}

	public int getSuperHeight( )
	{
		return superHeight;
	}

	public int getSuperWidth( )
	{
		return superWidth;
	}

}
