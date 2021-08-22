package sz.csi.wswing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import co.castle.system.FileLoader;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.util.FileUtil;
import sz.util.Position;

public class WSwingConsoleInterface
		implements ConsoleSystemInterface, Runnable, ComponentListener
{
	private StrokeInformer aStrokeInformer; // Object to which strokes are informed
	/** Current printing cursor position */
	private boolean autorefresh;
	private Position caretPosition = new Position( 0, 0 );

	private char[ ][ ] chars;
	private char[ ][ ] charsBuffer;

	private int[ ][ ] colors;
	private int[ ][ ] colorsBuffer;
	/**
	 * Provides Console IO. Returns keystrokes as CharKeys. Shows the characters in
	 * a Frame
	 */

	// Relations
	private SwingConsoleFrame targetFrame; // To get the keypresses from the AWT Model

	private SwingConsolePanel targetPanel; // To output characters
	// Attributes
	private int xpos, ypos;

	// Static Attributes
	// public static Font consoleFont = new Font ("Comic Sans MS", Font.BOLD, 16);
	// public static Font consoleFont = new Font ("Terminal", Font.PLAIN, 16);
	// public static Font consoleFont = new Font ("Fixedsys Excelsior 2.00",
	// Font.PLAIN, 16);
	public static Font consoleFont;
	public static int xdim = 80;

	public static int ydim = 25;

	private final static Color DARKRED_COLOR = new Color( 128, 0, 0 ),
			DARKBLUE_COLOR = new Color( 0, 0, 200 ),
			DARKGREEN_COLOR = new Color( 0, 128, 0 ),
			DARKMAGENTA_COLOR = new Color( 128, 0, 128 ),
			TEAL_COLOR = new Color( 0, 128, 128 ), BROWN_COLOR = new Color( 128, 128, 0 );

	public WSwingConsoleInterface( )
	{
		aStrokeInformer = new StrokeInformer( );
		targetFrame = new SwingConsoleFrame( );
		java.awt.Dimension initialSize = new java.awt.Dimension( 1208, 1024 );
		int fontSize = defineFontSize( initialSize.height, initialSize.width );
		String strConsoleFont = loadFont( );
		consoleFont = new Font( strConsoleFont, Font.PLAIN, fontSize );
		// targetFrame.setUndecorated(true);
		targetFrame.init( consoleFont, xdim, ydim );

		colors = new int[ xdim ][ ydim ];
		chars = new char[ xdim ][ ydim ];
		colorsBuffer = new int[ xdim ][ ydim ];
		charsBuffer = new char[ xdim ][ ydim ];

		targetPanel = targetFrame.getSwingConsolePanel( );
		targetFrame.addKeyListener( aStrokeInformer );
		targetFrame.addComponentListener( this );

		targetFrame.setSize( (int) ( fontSize * xdim * 0.7 * 1.05 ),
				(int) ( fontSize * ydim * 1.15 ) );
		// targetFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		targetFrame.setLocation( 0, 0 );
		locate( 1, 1 );
		targetFrame.show( );
	}

	public void cls( )
	{
		targetPanel.cls( );
		/*
		 * for (int i = 0; i< xdim; i++){ for (int j = 0; j< ydim; j++){
		 * targetPanel.plot(' ', i, j) ; } } /
		 */
	}

	public void componentHidden( ComponentEvent e )
	{
		// TODO Auto-generated method stub

	}

	public void componentMoved( ComponentEvent e )
	{
		// TODO Auto-generated method stub

	}

	public void componentResized( ComponentEvent e )
	{
		int fontSize = defineFontSize( ( (Component) e.getSource( ) ).getHeight( ),
				( (Component) e.getSource( ) ).getWidth( ) );
		String strConsoleFont = loadFont( );
		consoleFont = new Font( strConsoleFont, Font.PLAIN, fontSize );
		targetFrame.setFont( consoleFont );
		targetPanel.setFont( consoleFont );

	}

	public void componentShown( ComponentEvent e )
	{
		// TODO Auto-generated method stub

	}

	public void flash( int color )
	{
		// targetPanel.flash(getColorFromCode(color));
	}

	public int getColor( String colorName )
	{
		if ( colorName == null )
			return -1;
		if ( colorName.equals( "BLACK" ) )
			return BLACK;
		if ( colorName.equals( "DARK_BLUE" ) )
			return DARK_BLUE;
		if ( colorName.equals( "GREEN" ) )
			return GREEN;
		if ( colorName.equals( "TEAL" ) )
			return TEAL;
		if ( colorName.equals( "DARK_RED" ) )
			return DARK_RED;
		if ( colorName.equals( "PURPLE" ) )
			return PURPLE;
		if ( colorName.equals( "BROWN" ) )
			return BROWN;
		if ( colorName.equals( "LIGHT_GRAY" ) )
			return LIGHT_GRAY;
		if ( colorName.equals( "GRAY" ) )
			return GRAY;
		if ( colorName.equals( "BLUE" ) )
			return BLUE;
		if ( colorName.equals( "LEMON" ) )
			return LEMON;
		if ( colorName.equals( "CYAN" ) )
			return CYAN;
		if ( colorName.equals( "RED" ) )
			return RED;
		if ( colorName.equals( "MAGENTA" ) )
			return MAGENTA;
		if ( colorName.equals( "YELLOW" ) )
			return YELLOW;
		if ( colorName.equals( "WHITE" ) )
			return WHITE;
		return -1;
	}

	public synchronized CharKey inkey( )
	{
		aStrokeInformer.informKey( Thread.currentThread( ) );
		try
		{
			this.wait( );
		}
		catch ( InterruptedException ie )
		{
		}
		CharKey ret = new CharKey( aStrokeInformer.getInkeyBuffer( ) );
		return ret;
	}

	public String input( )
	{
		return input( 9999 );
	}

	public String input( int l )
	{
		String ret = "";
		CharKey read = new CharKey( CharKey.NONE );
		while ( true )
		{
			while ( read.code == CharKey.NONE )
				read = inkey( );
			if ( read.isMetaKey( ) )
			{
				read.code = CharKey.NONE;
				continue;
			}
			if ( read.code == CharKey.ENTER )
				return ret;
			if ( read.code == CharKey.BACKSPACE )
			{
				if ( ret.equals( "" ) )
				{
					read.code = CharKey.NONE;
					continue;
				}
				if ( ret.length( ) > 1 )
					ret = ret.substring( 0, ret.length( ) - 1 );
				else
					ret = "";
				caretPosition.x--;
				print( caretPosition.x, caretPosition.y, " " );

			}
			else
			{
				if ( ret.length( ) >= l )
				{
					read.code = CharKey.NONE;
					continue;
				}
				String nuevo = read.toString( );
				print( caretPosition.x, caretPosition.y, nuevo );
				ret += nuevo;
				caretPosition.x++;
			}
			refresh( );
			read.code = CharKey.NONE;

		}
		// return ret;
	}

	public boolean isInsideBounds( int x, int y )
	{
		return x >= 0 && x <= xdim - 1 && y >= 0 && y <= ydim - 1;
	}

	public boolean isInsideBounds( Position p )
	{
		return p.x >= 0 && p.x <= xdim && p.y >= 0 && p.y <= ydim;
	}

	public void locate( int x, int y )
	{
		xpos = x;
		ypos = y;
	}

	public void locateCaret( int x, int y )
	{
		caretPosition.x = x;
		caretPosition.y = y;
	}

	public char peekChar( int x, int y )
	{
		return targetPanel.peekChar( x, y );
	}

	public int peekColor( int x, int y )
	{
		return colors[ x ][ y ];
	}

	public void print( int x, int y, char what, int color )
	{
		locate( x, y );
		if ( chars[ x ][ y ] == what && colors[ x ][ y ] == color )
			return;
		Color front = getColorFromCode( color );
		targetPanel.plot( what, xpos, ypos, front );
		colors[ x ][ y ] = color;
		chars[ x ][ y ] = what;

	}

	public void print( int x, int y, String what )
	{
		print( x, y, what, ConsoleSystemInterface.WHITE );
	}

	public void print( int x, int y, String what, int color )
	{
		locate( x, y );

		Color front = getColorFromCode( color );
		for ( int i = 0; i < what.length( ); i++ )
		{
			if ( xpos >= xdim )
			{
				xpos = 0;
				ypos++;
			}
			if ( ypos >= ydim )
				break;
			targetPanel.plot( what.charAt( i ), xpos, ypos, front );
			chars[ x + i ][ y ] = what.charAt( i );
			colors[ x + i ][ y ] = color;
			xpos++;
		}
	}

	/*
	 * public void refresh(Thread t) { refresh(); t.interrupt(); }
	 */
	// int rcount;
	public void refresh( )
	{
		// System.out.println("Count "+ (rcount++) );
		targetFrame.repaint( );
		/*
		 * try { Thread.currentThread().sleep(1000); } catch (InterruptedException ie){
		 * }
		 */
	}

	public synchronized void refresh( Thread toNotify )
	{
		refresh( );

		toNotify.interrupt( );
	}

	public void restore( )
	{
		/*
		 * for (int i = 0; i < colors.length; i++){ System.arraycopy(colorsBuffer[i], 0,
		 * colors[i], 0, colors[i].length-1); System.arraycopy(charsBuffer[i], 0,
		 * chars[i], 0, colors[i].length-1); }
		 */
		for ( int x = 0; x < colors.length; x++ )
			for ( int y = 0; y < colors[ 0 ].length; y++ )
				this.print( x, y, charsBuffer[ x ][ y ], colorsBuffer[ x ][ y ] );
	}

	public void run( )
	{
	}

	public void safeprint( int x, int y, char what, int color )
	{
		if ( isInsideBounds( x, y ) )
			print( x, y, what, color );
	}

	public void saveBuffer( )
	{
		for ( int i = 0; i < colors.length; i++ )
		{
			System.arraycopy( colors[ i ], 0, colorsBuffer[ i ], 0,
					colors[ i ].length - 1 );
			System.arraycopy( chars[ i ], 0, charsBuffer[ i ], 0,
					colors[ i ].length - 1 );
		}
	}

	public void setAutoRefresh( boolean value )
	{
		targetPanel.setAutoUpdate( value );
	}

	public void waitKey( int keyCode )
	{
		CharKey x = new CharKey( CharKey.NONE );
		while ( x.code != keyCode )
			x = inkey( );
	}

	private int defineFontSize( int scrHeight, int scrWidth )
	{
		int byHeight = (int) ( scrHeight / ydim );
		int byWidth = (int) ( scrWidth / ( xdim * 0.8 ) );

		if ( byHeight < byWidth )
			return byHeight;
		else
			return byWidth;

	}

	private Color getColorFromCode( int code )
	{
		switch ( code )
		{
		case BLACK:
			return Color.BLACK;
		case DARK_BLUE:
			return DARKBLUE_COLOR;
		case GREEN:
			return DARKGREEN_COLOR;
		case TEAL:
			return TEAL_COLOR;
		case DARK_RED:
			return DARKRED_COLOR;
		case PURPLE:
			return DARKMAGENTA_COLOR;
		case BROWN:
			return BROWN_COLOR;
		case LIGHT_GRAY:
			return Color.LIGHT_GRAY;
		case GRAY:
			return Color.GRAY;
		case BLUE:
			return Color.BLUE;
		case LEMON:
			return Color.GREEN;
		case CYAN:
			return Color.CYAN;
		case RED:
			return Color.RED;
		case MAGENTA:
			return Color.MAGENTA;
		case YELLOW:
			return Color.YELLOW;
		case WHITE:
			return Color.WHITE;
		default:
			return null;
		}
	}

	private String loadFont( )
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(FileLoader.getInputStream("font.sz")));
			br.readLine();
			String fnt = br.readLine();
			br.close();
			if (!fnt.equals("NFE"))
				return fnt;

		}
		catch ( Exception e )
		{
			try
			{
				br.close( );
			}
			catch ( Exception e2 )
			{
			}
		}

		String x[] = GraphicsEnvironment.getLocalGraphicsEnvironment( )
				.getAvailableFontFamilyNames( );
		boolean lucida = false, courier = false;
		for ( int i = 0; i < x.length; i++ )
			if ( x[ i ].equals( "Lucida Console" ) )
				lucida = true;
			else if ( x[ i ].equals( "Courier New" ) )
				courier = true;
		if ( courier )
			return "Courier New";
		else if ( lucida )
			return "Lucida Console";
		return "Monospaced";

	}

}