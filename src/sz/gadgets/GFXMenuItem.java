package sz.gadgets;

import java.awt.Image;

public interface GFXMenuItem extends java.io.Serializable
{
	public String getMenuDescription( );

	public String getMenuDetail( );

	public Image getMenuImage( );

}