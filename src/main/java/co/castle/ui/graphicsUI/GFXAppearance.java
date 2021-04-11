package co.castle.ui.graphicsUI;

import java.awt.Image;

import co.castle.ui.Appearance;

public class GFXAppearance extends Appearance
{
	private Image darkImage;
	private Image darkniteImage;
	private Image img;
	private Image niteImage;
	private int superWidth, superHeight;

	public GFXAppearance(	String ID, Image pimg, Image darkImage, Image niteImage,
							Image darkniteImage, int superWidth, int superHeight )
	{
		super( ID );
		img = pimg;
		this.darkImage = darkImage;
		this.niteImage = niteImage;
		this.darkniteImage = darkniteImage;
		this.superHeight = superHeight;
		this.superWidth = superWidth;
	}

	public GFXAppearance( String ID, Image pimg, int superWidth, int superHeight )
	{
		super( ID );
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
