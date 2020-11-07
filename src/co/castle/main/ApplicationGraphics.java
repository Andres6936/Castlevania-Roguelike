package co.castle.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JFrame;

import co.castle.conf.gfx.data.Asset;
import co.castle.event.Keyboard;
import co.castle.game.Game;
import co.castle.ui.graphicsUI.Panel;
import sz.csi.CharKey;
import sz.util.ImageUtils;
import sz.util.Position;

// With keyword final, we be prevent the inheritance of this class
public final class ApplicationGraphics extends JFrame {

	// Fields Final

	private static final long serialVersionUID = 3339068814173683092L;

	// Fields Static

	public static Asset assets;

	// Fields

	private Keyboard keyboard;

	private final Position caretPosition = new Position(0, 0);

	private final Hashtable<String, Image> images = new Hashtable<>();

	private Panel panelGame;

	/**
	 * Class type Singleton, reference to only object
	 */
	private static ApplicationGraphics instance;

	/**
	 * File with properties of user interface and path resource
	 */
	public Properties configurationUI;

	// Construct

	// We make the constructor private to prevent the use of 'new'
	private ApplicationGraphics() {
		loaderFileConfigurationForUserInterface();
	}

	// Method Static

	/**
	 * @return Instance of ApplicationFrame
	 */
	public static ApplicationGraphics getInstance() {
		if (instance == null) {
			instance = new ApplicationGraphics();
		}

		return instance;
	}

	// Method Synchronized

	public synchronized CharKey inkey() {
		keyboard.informKey(Thread.currentThread());
		try {
			this.wait();
		} catch (InterruptedException ignored) {
		}
		return new CharKey(keyboard.getInkeyBuffer());
	}

	// Method

	public void start() {
		// IMPORTANT: We have that initialize the instance of assets
		// here, if do not make, we create a loop infinity, because
		// Assets depend of configurationUI for can be used

		// Content the asset for application
		assets = new Asset(configurationUI);

		Dimension size = Toolkit.getDefaultToolkit( ).getScreenSize( );
		setBounds( ( size.width - assets.SCREEN_WIDTH ) / 2, ( size.height - assets.SCREEN_HEIGHT ) / 2,
                   assets.SCREEN_WIDTH, assets.SCREEN_HEIGHT );
		getContentPane( ).setLayout( new GridLayout( 1, 1 ) );
		setUndecorated( true );
		setVisible( true );

		panelGame = new Panel( assets );

		getContentPane( ).add( panelGame );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBackground( Color.BLACK );

		keyboard = new Keyboard( );

		addKeyListener( keyboard );
		setFocusable( true );

		panelGame.init( );
	}

	/**
	 * Loader the file of properties for the parameters of user interface and path
	 * resources of application
	 */
	private void loaderFileConfigurationForUserInterface( )
	{
		configurationUI = new Properties( );

		try
		{
			configurationUI.load( new FileInputStream( "properties/configurationUI.properties" ) );
		}
		catch ( IOException e )
		{
			System.out.println( "Error loading configuration for user interface.\n" );
			System.exit( -1 );
		}
	}

	public void addComponentToPanel( Component c )
	{
		panelGame.add( c );
		panelGame.validate( );
	}

	public void cls( )
	{
		panelGame.cls( );
	}

	public void drawImage( Image image )
	{
		panelGame.drawImage( image );
		panelGame.repaint( );
	}

	public void drawImage( int scrX, int scrY, Image img )
	{
		panelGame.drawImage( scrX, scrY, img );
	}

	public void drawImage( int scrX, int scrY, String filename )
	{
		Image im = images.get( filename );
		if ( im == null )
		{
			try
			{
				im = ImageUtils.createImage( filename );
			}
			catch ( Exception e )
			{
				Game.crash( "Exception trying to create image " + filename, e );
			}
            assert im != null;
			images.put( filename, im );
		}
		panelGame.drawImage( scrX, scrY, im );
	}

	@Deprecated
	public void drawImage( String filename )
	{
		Image im = images.get( filename );
		if ( im == null )
		{
			try
			{
				im = ImageUtils.createImage( filename );
			}
			catch ( Exception e )
			{
				Game.crash( "Exception trying to create image " + filename, e );
			}
            assert im != null;
			images.put( filename, im );
		}
		panelGame.drawImage( im );
		panelGame.repaint( );
	}

	/**
	 * Draw image from BufferedImage
	 * 
	 * @param image Image to draw
	 */
	public void drawImage( BufferedImage image )
	{
		panelGame.drawImage( image );
		panelGame.repaint( );
	}

	public void drawImageCC( int consoleX, int consoleY, Image img )
	{
		drawImage( consoleX * 10, consoleY * 24, img );
	}

	public void drawImageCC( int consoleX, int consoleY, String img )
	{
		drawImage( consoleX * 10, consoleY * 24, img );
	}

	public void flash( Color c )
	{
		panelGame.flash( c );
	}

	/*
	 * public void print(int x, int y, String text){ sip.print(x*10, y*24, text); }
	 */

	public Graphics2D getGraphics2D( )
	{
		return panelGame.getCurrentGraphics( );
	}

	public String input( int xpos, int ypos, Color textColor, int maxLength )
	{
        StringBuilder ret = new StringBuilder( );
		CharKey read = new CharKey( CharKey.NONE );
		saveBuffer( );
		while ( true )
		{
			restore( );
			printAtPixel( xpos, ypos, ret + "_", textColor );
			refresh( );
			while ( read.code == CharKey.NONE )
				read = inkey( );
			if ( read.code == CharKey.ENTER )
				break;
			if ( read.code == CharKey.BACKSPACE )
			{
                if ( ret.toString( ).equals( "" ) )
				{
					read.code = CharKey.NONE;
					continue;
				}
                if ( ret.length( ) > 1 )
                { ret = new StringBuilder( ret.substring( 0, ret.length( ) - 1 ) ); }
                else
                { ret = new StringBuilder( ); }
				caretPosition.x--;
				// print(caretPosition.x, caretPosition.y, " ");
			}
			else
			{
				if ( ret.length( ) >= 50 )
				{
					read.code = CharKey.NONE;
					continue;
				}
				if ( !read.isAlphaNumeric( ) )
				{
					read.code = CharKey.NONE;
					continue;
				}

				String nuevo = read.toString( );
				// print(caretPosition.x, caretPosition.y, nuevo, Color.WHITE);
                ret.append( nuevo );
				caretPosition.x++;
			}
			read.code = CharKey.NONE;
		}
        return ret.toString( );
	}

	public void print( int x, int y, String text, Color color )
	{
		panelGame.print( x * 10, y * 24, text, color );
	}

	public void printAtPixel( int x, int y, String text )
	{
		panelGame.print( x, y, text );
	}

	public void printAtPixel( int x, int y, String text, Color color )
	{
		panelGame.print( x, y, text, color );
	}

	public void printAtPixelCentered( int x, int y, String text, Color color )
	{
		panelGame.print( x, y, text, color, true );
	}

	public void recoverFocus( )
	{
		requestFocus( );
	}

	public void refresh( )
	{
		// invTextArea.setVisible(false);
		panelGame.repaint( );
	}

	public void remove( Component c )
	{
		panelGame.remove( c );
		panelGame.validate( );
	}

	public void restore( )
	{
		panelGame.restore( );
	}

	public void restore( int buffer )
	{
		panelGame.restore( buffer );
	}

	public void saveBuffer( )
	{
		panelGame.saveBuffer( );
	}

	public void saveBuffer( int buffer )
	{
		panelGame.saveBuffer( buffer );
	}

	public void setColor( Color color )
	{
		panelGame.setColor( color );
		// invTextArea.setForeground(color);
	}

	public void setCursor( Cursor c )
	{
        super.setCursor( c );
	}

	public void setFontToPanel( Font font )
	{
		panelGame.setFontFace( font );
	}

	public void setIcon( Image icon )
	{
		setIconImage( icon );
	}

	public void setTitleFrame( String title )
	{
		setTitle( title );
	}

	public void waitKey( int keyCode )
	{
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != keyCode )
			x = inkey( );
	}
}