package sz.csi.wswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class SwingConsolePanel extends JPanel
{
	private boolean autoUpdate;
	private Color backGround, foreGround;
	private char[ ][ ] charBuffer;
	private Color[ ][ ] colorBuffer;
	private Font f;

	private boolean flash = false;
	private Color flashColor = null;

	private int fontDown;

	private int fontWidth;
	private transient Graphics graphicsBuff;
	private transient Image imageBuff;

	private boolean[ ][ ] updateBuffer;

	private int xpos, ypos, // Current Cursor Position
			width, height, // Size of the Panel in points
			xdim, ydim, // Size of the Panel in characters
			fontSize;

	public void cls( )
	{
		// autoUpdate = true;
		for ( int x = 0; x < charBuffer.length; x++ )
			for ( int y = 0; y < charBuffer[ 0 ].length; y++ )
			{
				charBuffer[ x ][ y ] = ' ';
				updateBuffer[ x ][ y ] = true;
			}
		/*
		 * graphicsBuff.setColor(Color.BLACK); graphicsBuff.clearRect(0,0,getWidth(),
		 * getHeight()); repaint(); autoUpdate = false;
		 */

	}

	public void flash( Color fc )
	{
		flash = true;

		flashColor = fc;
		repaint( );
	}

	public void init( Font f, int xdim, int ydim )
	{
		setCursor( null );
		width = Toolkit.getDefaultToolkit( ).getScreenSize( ).width;
		height = Toolkit.getDefaultToolkit( ).getScreenSize( ).height;
		setBounds( 0, 0, width, height );
		foreGround = Color.WHITE;
		backGround = Color.BLACK;
		setBackground( backGround );
		this.xdim = xdim;
		this.ydim = ydim;
		this.f = f;
		charBuffer = new char[ xdim ][ ydim ];
		colorBuffer = new Color[ xdim ][ ydim ];
		updateBuffer = new boolean[ xdim ][ ydim ];

		for ( int i = 0; i < xdim; i++ )
			for ( int j = 0; j < ydim; j++ )
			{
				charBuffer[ i ][ j ] = ' ';
				colorBuffer[ i ][ j ] = foreGround;
				updateBuffer[ i ][ j ] = true;
			}

		// Double Buffer
		imageBuff = createImage( width, height );
		graphicsBuff = imageBuff.getGraphics( );
		graphicsBuff.setFont( f );
		fontSize = f.getSize( );
		fontWidth = (int) ( fontSize * 0.7 );
		fontDown = (int) ( fontSize * 1.3 );
		repaint( );
	}

	public void paintComponent( Graphics g )
	{
		// public void paint(Graphics g){

		if ( flash )
		{

			g.setColor( flashColor );
			g.fillRect( 0, 0, getWidth( ), getHeight( ) );
			for ( int x = 0; x < charBuffer.length; x++ )
				for ( int y = 0; y < charBuffer[ 0 ].length; y++ )
				{
					g.setColor( colorBuffer[ x ][ y ] );
					g.drawString( "" + charBuffer[ x ][ y ], x * fontWidth,
							( y + 1 ) * fontSize );
				}
			// g.drawImage(imageBuff, 0, 0, null);

			for ( int x = 0; x < charBuffer.length; x++ )

				for ( int y = 0; y < charBuffer[ 0 ].length; y++ )
				{

					graphicsBuff.setColor( backGround );
					graphicsBuff.fillRect( x * fontWidth, y * fontSize, fontWidth,
							fontDown );
					// Fix upper and lower positions if possible
					if ( y - 1 >= 0 )
					{
						graphicsBuff.setColor( colorBuffer[ x ][ y - 1 ] );
						graphicsBuff.drawString( "" + charBuffer[ x ][ y - 1 ],
								x * fontWidth, ( y ) * fontSize );
					}
					if ( y + 1 < ydim )
					{
						graphicsBuff.setColor( colorBuffer[ x ][ y + 1 ] );
						graphicsBuff.drawString( "" + charBuffer[ x ][ y + 1 ],
								x * fontWidth, ( y + 2 ) * fontSize );
					}

					graphicsBuff.setColor( colorBuffer[ x ][ y ] );
					graphicsBuff.drawString( "" + charBuffer[ x ][ y ], x * fontWidth,
							( y + 1 ) * fontSize );
					// graphicsBuff.drawChars(charBuffer[x], 0,1,x * fontWidth, y *
					// fontSize);
				}

			// g.drawImage(imageBuff, 0, 0, null);

			// try {Thread.sleep(1500);}catch(Exception e){}
			flash = false;
			return;
		}

		if ( !autoUpdate )
		{
			for ( int x = 0; x < charBuffer.length; x++ )
				for ( int y = 0; y < charBuffer[ 0 ].length; y++ )
				{
					if ( updateBuffer[ x ][ y ] )
					{
						graphicsBuff.setColor( backGround );
						graphicsBuff.fillRect( x * fontWidth, y * fontSize, fontWidth,
								fontDown );
						// Fix upper and lower positions if possible
						if ( y - 1 >= 0 )
						{
							graphicsBuff.setColor( colorBuffer[ x ][ y - 1 ] );
							graphicsBuff.drawString( "" + charBuffer[ x ][ y - 1 ],
									x * fontWidth, y * fontSize );
						}
						if ( y + 1 < ydim )
						{
							graphicsBuff.setColor( colorBuffer[ x ][ y + 1 ] );
							graphicsBuff.drawString( "" + charBuffer[ x ][ y + 1 ],
									x * fontWidth, ( y + 2 ) * fontSize );
						}

						graphicsBuff.setColor( colorBuffer[ x ][ y ] );
						graphicsBuff.drawString( "" + charBuffer[ x ][ y ], x * fontWidth,
								( y + 1 ) * fontSize );
						// graphicsBuff.drawChars(charBuffer[x], 0,1,x * fontWidth, y *
						// fontSize);
						updateBuffer[ x ][ y ] = false;
					}
				}
		}
		g.drawImage( imageBuff, 0, 0, null );
	}

	public char peekChar( int x, int y )
	{
		return charBuffer[ x ][ y ];
	}

	public void plot( char c, int x, int y )
	{
		colorBuffer[ x ][ y ] = foreGround;
		charBuffer[ x ][ y ] = c;
		updateBuffer[ x ][ y ] = true;
		if ( autoUpdate )
		{
			graphicsBuff.setColor( backGround );
			graphicsBuff.fillRect( x * fontWidth, y * fontSize, fontWidth, fontDown );
			// Fix upper and lower positions if possible
			if ( y - 1 >= 0 )
			{
				graphicsBuff.setColor( colorBuffer[ x ][ y - 1 ] );
				graphicsBuff.drawString( "" + charBuffer[ x ][ y - 1 ], x * fontWidth,
						( y ) * fontSize );
			}
			if ( y + 1 < ydim )
			{
				graphicsBuff.setColor( colorBuffer[ x ][ y + 1 ] );
				graphicsBuff.drawString( "" + charBuffer[ x ][ y + 1 ], x * fontWidth,
						( y + 2 ) * fontSize );
			}

			graphicsBuff.setColor( colorBuffer[ x ][ y ] );
			graphicsBuff.drawString( "" + charBuffer[ x ][ y ], x * fontWidth,
					( y + 1 ) * fontSize );
			updateBuffer[ x ][ y ] = false;
		}
	}
	public void plot( char c, int x, int y, Color foreColor )
	{
		colorBuffer[ x ][ y ] = foreColor;
		charBuffer[ x ][ y ] = c;
		updateBuffer[ x ][ y ] = true;
		if ( autoUpdate )
		{
			graphicsBuff.setColor( backGround );
			graphicsBuff.fillRect( x * fontWidth, y * fontSize, fontWidth, fontDown );
			// Fix upper and lower positions if possible
			if ( y - 1 >= 0 )
			{
				graphicsBuff.setColor( colorBuffer[ x ][ y - 1 ] );
				graphicsBuff.drawString( "" + charBuffer[ x ][ y - 1 ], x * fontWidth,
						( y ) * fontSize );
			}
			if ( y + 1 < ydim )
			{
				graphicsBuff.setColor( colorBuffer[ x ][ y + 1 ] );
				graphicsBuff.drawString( "" + charBuffer[ x ][ y + 1 ], x * fontWidth,
						( y + 2 ) * fontSize );
			}

			graphicsBuff.setColor( colorBuffer[ x ][ y ] );
			graphicsBuff.drawString( "" + charBuffer[ x ][ y ], x * fontWidth,
					( y + 1 ) * fontSize );
			repaint( );
			updateBuffer[ x ][ y ] = false;
		}
	}

	public void refresh( )
	{
		repaint( );
	}

	public void setAutoUpdate( boolean value )
	{
		autoUpdate = value;
		// autoUpdate = false;
	}

	public void setFont( Font pFont )
	{
		f = pFont;
	}
}