package co.castle.ui.graphicsUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class AddornedBorderPanel extends JPanel
{
	private int borderWidth, borderHeight;
	private Color OUT_COLOR, IN_COLOR;
	private Image UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT;
	// private static Color TRANSPARENT_BLUE = new Color(100,100,100,200);
	private static Color TRANSPARENT_BLUE = new Color( 20, 20, 20, 200 );

	public AddornedBorderPanel(	Image UPRIGHT, Image UPLEFT, Image DOWNRIGHT,
								Image DOWNLEFT, Color OUT_COLOR, Color IN_COLOR,
								int borderWidth, int borderHeight )
	{
		this.UPRIGHT = UPRIGHT;
		this.UPLEFT = UPLEFT;
		this.DOWNRIGHT = DOWNRIGHT;
		this.DOWNLEFT = DOWNLEFT;
		this.OUT_COLOR = OUT_COLOR;
		this.IN_COLOR = IN_COLOR;
		this.borderHeight = borderHeight;
		this.borderWidth = borderWidth;
	}

	public void paintAt( Graphics g, int x, int y )
	{
		g.setColor( TRANSPARENT_BLUE );
		g.fillRect( x + 6, y + 6, getWidth( ) - 14, getHeight( ) - 14 );
		g.setColor( OUT_COLOR );
		g.drawRect( x + 6, y + 6, getWidth( ) - 14, getHeight( ) - 14 );
		g.setColor( IN_COLOR );
		g.drawRect( x + 8, y + 8, getWidth( ) - 18, getHeight( ) - 18 );
		g.drawImage( UPLEFT, x, y, this );
		g.drawImage( UPRIGHT, x + getWidth( ) - borderWidth, y, this );
		g.drawImage( DOWNLEFT, x, y + getHeight( ) - borderHeight, this );
		g.drawImage( DOWNRIGHT, x + getWidth( ) - borderWidth,
				y + getHeight( ) - borderHeight, this );
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.setColor( TRANSPARENT_BLUE );
		g.fillRect( 6, 6, getWidth( ) - 14, getHeight( ) - 14 );
		g.setColor( OUT_COLOR );
		g.drawRect( 6, 6, getWidth( ) - 14, getHeight( ) - 14 );
		g.setColor( IN_COLOR );
		g.drawRect( 8, 8, getWidth( ) - 18, getHeight( ) - 18 );
		g.drawImage( UPLEFT, 0, 0, this );
		g.drawImage( UPRIGHT, getWidth( ) - borderWidth, 0, this );
		g.drawImage( DOWNLEFT, 0, getHeight( ) - borderHeight, this );
		g.drawImage( DOWNRIGHT, getWidth( ) - borderWidth, getHeight( ) - borderHeight,
				this );
	}

}
