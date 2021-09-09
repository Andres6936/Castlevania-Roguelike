package co.castle.ui.graphicsUI;

import co.castle.conf.gfx.data.Asset;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Sketch extends JPanel {

	private Graphics backGraphics;
	private Graphics[] backGraphicsBuffers;
	private Image backImage;

	private Image[] backImageBuffers;
	private Graphics bufferGraphics;

	private Image bufferImage;
	private FontMetrics fontMetrics;
	protected Asset configuration;
	private static final long serialVersionUID = -7392757206841150146L;

	public Sketch(Asset configuration) {
		this.configuration = configuration;
		setLayout(null);
		setBorder(new LineBorder(Color.GRAY));
	}

	public Component add( Component comp )
	{
		return super.add( comp );
	}

	public void cls( )
	{
		Color oldColor = bufferGraphics.getColor( );
		bufferGraphics.setColor( Color.BLACK );
		bufferGraphics.fillRect( 0, 0, configuration.SCREEN_WIDTH, configuration.SCREEN_HEIGHT );
		bufferGraphics.setColor( oldColor );
	}

	public void drawImage( Image img )
	{
		bufferGraphics.drawImage( img, 0, 0, this );
	}

	public void drawImage( int scrX, int scrY, Image img )
	{
		bufferGraphics.drawImage( img, scrX, scrY, this );
	}

	public void flash( Color c )
	{

	}

	public Graphics2D getCurrentGraphics( )
	{
		return (Graphics2D) bufferGraphics;
	}

	public void init( )
	{
		bufferImage = createImage( configuration.SCREEN_WIDTH, configuration.SCREEN_HEIGHT );
		bufferGraphics = bufferImage.getGraphics( );
		bufferGraphics.setColor( Color.WHITE );
		backImage = createImage( configuration.SCREEN_WIDTH, configuration.SCREEN_HEIGHT );
		backGraphics = backImage.getGraphics( );
		backImageBuffers = new Image[ 5 ];
		backGraphicsBuffers = new Graphics[ 5 ];
		for ( int i = 0; i < 5; i++ )
		{
			backImageBuffers[ i ] = createImage( configuration.SCREEN_WIDTH, configuration.SCREEN_HEIGHT );
			backGraphicsBuffers[ i ] = backImageBuffers[ i ].getGraphics( );
		}

	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		if ( bufferImage != null )
		{
			g.drawImage( bufferImage, 0, 0, this );
		}
	}

	public void print( int x, int y, String text )
	{
		bufferGraphics.drawString( text, x, y );
		// repaint();
	}

	public void print( int x, int y, String text, Color c )
	{
		print( x, y, text, c, false );
		// repaint();
	}

	public void print( int x, int y, String text, Color c, boolean centered )
	{
		if ( centered )
		{
			int width = fontMetrics.stringWidth( text );
			x = x - ( width / 2 );
		}
		Color old = bufferGraphics.getColor( );
		bufferGraphics.setColor( c );
		bufferGraphics.drawString( text, x, y );
		bufferGraphics.setColor( old );
	}

	public void restore( )
	{
		bufferGraphics.drawImage( backImage, 0, 0, this );
	}

	public void restore( int buffer )
	{
		bufferGraphics.drawImage( backImageBuffers[ buffer ], 0, 0, this );
	}

	public void saveBuffer( )
	{
		backGraphics.drawImage( bufferImage, 0, 0, this );
	}

	public void saveBuffer( int buffer )
	{
		backGraphicsBuffers[ buffer ].drawImage( bufferImage, 0, 0, this );
	}

	public void setColor( Color color )
	{
		bufferGraphics.setColor( color );
	}

	public void setFontFace( Font f )
	{
		bufferGraphics.setFont( f );
		fontMetrics = bufferGraphics.getFontMetrics( );
	}

}