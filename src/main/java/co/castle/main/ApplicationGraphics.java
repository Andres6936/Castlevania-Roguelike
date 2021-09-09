package co.castle.main;

import co.castle.conf.gfx.data.Asset;
import co.castle.conf.gfx.data.GFXAppearances;
import co.castle.conf.gfx.data.GFXEffects;
import co.castle.event.Keyboard;
import co.castle.system.FileLoader;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import co.castle.ui.graphicsUI.GFXUserInterface;
import co.castle.ui.graphicsUI.Panel;
import co.castle.ui.graphicsUI.effects.GFXEffectFactory;
import sz.csi.KeyCode;
import sz.util.ImageUtils;
import sz.util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Properties;

// With keyword final, we be prevent the inheritance of this class
public final class ApplicationGraphics extends JFrame {

	// Fields Final

	private boolean mouseEnable = false;

	private static final long serialVersionUID = 3339068814173683092L;

	private final Position caretPosition = new Position(0, 0);

	private final Hashtable<String, Image> images = new Hashtable<>();

	// Fields Static

	/**
	 * Content the asset for application
	 */
	public final static Asset assets = new Asset();

	// Fields

	private final Keyboard keyboard;

	private final Panel panelGame;

	/**
	 * Class type Singleton, reference to only object
	 */
	private static ApplicationGraphics instance;

	private Font font;

	// Construct

	/**
     * Loader the file of properties for the parameters of user interface and
     * path resources of application.
     * <p>
     * Post-condition: The configuration user interface has been initialized.
     *
     * @implNote We make the constructor private to prevent the use of 'new'.
     */
    public ApplicationGraphics() {
        Properties configurationUI = new Properties();
        try {
            configurationUI.load(FileLoader.getInputStream("properties/configurationUI.properties"));
            mouseEnable = configurationUI.getProperty("useMouse").equals("true");
            var fontName = configurationUI.getProperty("FNT_TEXT");
            var fontSize = configurationUI.getProperty("FNT_TEXT_SIZE");
            font = Font.createFont(Font.TRUETYPE_FONT, FileLoader.getInputStream(fontName)).deriveFont(Font.PLAIN, Integer.parseInt(fontSize));
        } catch (IOException e) {
            System.out.println("Error loading configuration for user interface.\n");
            System.exit(-1);
		} catch (FontFormatException e) {
			System.out.println("Error loading the font" );
		}

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((size.width - Asset.SCREEN_WIDTH) / 2, (size.height - Asset.SCREEN_HEIGHT) / 2,
				Asset.SCREEN_WIDTH, Asset.SCREEN_HEIGHT);
		getContentPane().setLayout(new GridLayout(1, 1));
		setUndecorated(true);
		setVisible(true);

		panelGame = new Panel(assets);

		getContentPane().add(panelGame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		keyboard = new Keyboard();

		addKeyListener(keyboard);
		setFocusable(true);

		panelGame.init();

		System.out.println("Initializing Swing GFX System Interface");
		System.out.println("Initializing Graphics Appearances");
		for (Appearance definition : new GFXAppearances(assets)) {
			AppearanceFactory.getAppearanceFactory().addDefinition(definition);
		}

		System.out.println("Initializing Swing GFX User Interface");
		UserInterface.setSingleton(new GFXUserInterface());
		EffectFactory.setSingleton(new GFXEffectFactory());
		((GFXEffectFactory) EffectFactory.getSingleton())
				.setEffects(new GFXEffects().getEffects());
	}

	// Method Static

	public Font getFont() {
		return Objects.requireNonNull(font);
	}

	public boolean isMouseEnable() {
		return mouseEnable;
	}

	/**
	 * @return Instance of ApplicationFrame
	 */
	public static ApplicationGraphics getInstance() {
		return instance == null ? instance = new ApplicationGraphics() : instance;
	}

	// Method Synchronized

	public synchronized KeyCode getKeyPressed() {
		keyboard.informKey(Thread.currentThread());
		try {
			this.wait();
		} catch (InterruptedException ignored) {
		}
		return new KeyCode(keyboard.getInkeyBuffer());
	}

	// Method

	public void addComponentToPanel(Component c) {
		panelGame.add(c);
		panelGame.validate();
	}

	public void cls()
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
				System.err.println("Exception trying to create image " + filename);
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
				System.err.println("Exception trying to create image " + filename);
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
		StringBuilder ret = new StringBuilder();
		KeyCode read = new KeyCode(KeyCode.NONE);
		saveBuffer( );
		while ( true )
		{
			restore( );
			printAtPixel( xpos, ypos, ret + "_", textColor );
			refresh( );
			while (read.code == KeyCode.NONE)
				read = getKeyPressed();
			if (read.code == KeyCode.ENTER)
				break;
			if (read.code == KeyCode.BACKSPACE) {
				if (ret.toString().equals("")) {
					read.code = KeyCode.NONE;
					continue;
				}
				if (ret.length() > 1) {
					ret = new StringBuilder(ret.substring(0, ret.length() - 1));
				} else {
					ret = new StringBuilder();
				}
				caretPosition.x--;
				// print(caretPosition.x, caretPosition.y, " ");
			}
			else
			{
				if ( ret.length( ) >= 50 )
				{
					read.code = KeyCode.NONE;
					continue;
				}
				if ( !read.isAlphaNumeric( ) )
				{
					read.code = KeyCode.NONE;
					continue;
				}

				String nuevo = read.toString( );
				// print(caretPosition.x, caretPosition.y, nuevo, Color.WHITE);
                ret.append( nuevo );
				caretPosition.x++;
			}
			read.code = KeyCode.NONE;
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
		KeyCode x = new KeyCode(KeyCode.NONE);
		while ( x.code != keyCode )
			x = getKeyPressed();
	}
}