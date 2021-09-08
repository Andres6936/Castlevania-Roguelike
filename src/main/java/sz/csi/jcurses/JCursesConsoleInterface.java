package sz.csi.jcurses;

import jcurses.system.CharColor;
import jcurses.system.InputChar;
import jcurses.system.Toolkit;
import sz.csi.KeyCode;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class JCursesConsoleInterface implements ConsoleSystemInterface
{
	private final CharColor BLACK = new CharColor( CharColor.BLACK, CharColor.BLACK );
	private final CharColor BLUE = new CharColor( CharColor.BLACK, CharColor.BLUE,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor BROWN = new CharColor( CharColor.BLACK, CharColor.YELLOW );
	private Position caretPosition = new Position( 0, 0 );

	private char[ ][ ] chars;

	private char[ ][ ] charsBuffer;

	private int[ ][ ] colors;

	private int[ ][ ] colorsBuffer;

	private final CharColor CYAN = new CharColor( CharColor.BLACK, CharColor.CYAN,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor DARK_BLUE = new CharColor( CharColor.BLACK, CharColor.BLUE );

	private final CharColor DARK_RED = new CharColor( CharColor.BLACK, CharColor.RED );

	private final CharColor GRAY = new CharColor( CharColor.BLACK, CharColor.BLACK,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor GREEN = new CharColor( CharColor.BLACK, CharColor.GREEN );

	private final CharColor LEMON = new CharColor( CharColor.BLACK, CharColor.GREEN,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor LIGHT_GRAY = new CharColor( CharColor.BLACK,
			CharColor.WHITE );

	private final CharColor MAGENTA = new CharColor( CharColor.BLACK, CharColor.MAGENTA,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor PURPLE = new CharColor( CharColor.BLACK, CharColor.MAGENTA );

	private final CharColor RED = new CharColor( CharColor.BLACK, CharColor.RED,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor TEAL = new CharColor( CharColor.BLACK, CharColor.CYAN );

	private final CharColor WHITE = new CharColor( CharColor.BLACK, CharColor.WHITE,
			CharColor.BOLD, CharColor.BOLD );

	private final CharColor YELLOW = new CharColor( CharColor.BLACK, CharColor.YELLOW,
			CharColor.BOLD, CharColor.BOLD );

	private final static int KEY_BACKSPACE = InputChar.KEY_BACKSPACE;

	private final static int KEY_DOWN = InputChar.KEY_DOWN;
	private final static int KEY_F1 = InputChar.KEY_F1;
	private final static int KEY_LEFT = InputChar.KEY_LEFT;
	private final static int KEY_RIGHT = InputChar.KEY_RIGHT;
	private final static int KEY_UP = InputChar.KEY_UP;
	public JCursesConsoleInterface( )
	{
		Toolkit.startPainting( );
		colors = new int[ Toolkit.getScreenWidth( ) + 1 ][ Toolkit.getScreenHeight( )
				+ 1 ];
		chars = new char[ Toolkit.getScreenWidth( ) + 1 ][ Toolkit.getScreenHeight( )
				+ 1 ];
		colorsBuffer = new int[ Toolkit.getScreenWidth( )
				+ 1 ][ Toolkit.getScreenHeight( ) + 1 ];
		charsBuffer = new char[ Toolkit.getScreenWidth( )
				+ 1 ][ Toolkit.getScreenHeight( ) + 1 ];
	}
	public void cls( )
	{
		Toolkit.clearScreen( BLACK );
		for ( int x = 0; x < chars.length; x++ )
			for ( int y = 0; y < chars[ 0 ].length; y++ )
			{
				chars[ x ][ y ] = '\u0000';
				colors[ x ][ y ] = ConsoleSystemInterface.BLACK;
			}
	}

    public void flash(int color) {
        /*
         * Toolkit.clearScreen(new CharColor(getJCurseColor(color).getForeground(),
         * getJCurseColor(color).getForeground())); try { Thread.sleep(10); } catch
         * (InterruptedException ie){ } Toolkit.clearScreen(BLACK);
         */
        // Toolkit.changeColors(new Rectangle(Toolkit.UL_CORNER, Toolkit.LR_CORNER), new
        // CharColor(getJCurseColor(color).getForeground(), CharColor.BLACK));
    }

    public KeyCode inkey() {
        InputChar c = Toolkit.readCharacter();
        return new KeyCode(ASCtoCharKeyCode(c.getCode()));
    }

    public String input() {
        return input(999);
    }
	public String input( int l )
	{
        String ret = "";
        KeyCode read = new KeyCode(KeyCode.NONE);
		while ( true )
		{
            while (read.code == KeyCode.NONE)
                read = inkey();
            if (read.code == KeyCode.ENTER)
                break;
            if (read.code == KeyCode.BACKSPACE) {
                if (ret.equals("")) {
                    read.code = KeyCode.NONE;
                    continue;
                }
                if (ret.length() > 1)
                    ret = ret.substring(0, ret.length() - 1);
                else
                    ret = "";
				caretPosition.x--;
				print( caretPosition.x, caretPosition.y, " " );

			}
			else
			{
				if ( ret.length( ) >= l )
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
				print( caretPosition.x, caretPosition.y, nuevo + "_" );
				ret += nuevo;
				caretPosition.x++;
			}
            refresh();
            read.code = KeyCode.NONE;

		}
		return ret;
	}
	public boolean isInsideBounds( int x, int y )
	{
		return x >= 0 && x <= Toolkit.getScreenWidth( ) && y >= 0
				&& y <= Toolkit.getScreenHeight( );
	}
	public boolean isInsideBounds( Position p )
	{
		return p.x >= 0 && p.x <= Toolkit.getScreenWidth( ) && p.y >= 0
				&& p.y <= Toolkit.getScreenHeight( );
	}
	/** Waits until a key is pressed and returns it */
	public void locateCaret( int x, int y )
	{
		caretPosition.x = x;
		caretPosition.y = y;
	}
	public char peekChar( int x, int y )
	{
		return chars[ x ][ y ];
	}
	public int peekColor( int x, int y )
	{
		return colors[ x ][ y ];
	}

	public void print( int x, int y, char what, int color )
	{
		// if (isInsideBounds(x,y))
		if ( chars[ x ][ y ] == what && colors[ x ][ y ] == color )
			return;
		Toolkit.printString( what + "", x, y, getJCurseColor( color ) );
		colors[ x ][ y ] = color;
		chars[ x ][ y ] = what;
	}

	public void print( int x, int y, String what )
	{
		for ( int i = 0; i < what.length( ); i++ )
		{
			if ( !isInsideBounds( x + i, y ) )
				break;
			chars[ x + i ][ y ] = what.charAt( i );
			colors[ x + i ][ y ] = ConsoleSystemInterface.WHITE;
		}
		Toolkit.printString( what, x, y, WHITE );
	}
	public void print( int x, int y, String what, int color )
	{
		for ( int i = 0; i < what.length( ); i++ )
		{
			if ( !isInsideBounds( x + i, y ) )
				break;
			chars[ x + i ][ y ] = what.charAt( i );
			colors[ x + i ][ y ] = color;
		}
		Toolkit.printString( what, x, y, getJCurseColor( color ) );
	}
	public void refresh( )
	{
		Toolkit.endPainting( );
		Toolkit.startPainting( );
		Toolkit.printString( "", 79, 24, BLACK );
	}
	public void refresh( Thread t )
	{
		refresh( );
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
	}

	public void waitKey( int keyCode )
	{
        KeyCode x = new KeyCode(KeyCode.NONE);
		while ( x.code != keyCode )
			x = inkey( );
	}

	private int ASCtoCharKeyCode( int code )
	{
		if ( code >= 65 && code <= 90 )
            return code - (65 - KeyCode.A);
		else if ( code >= 97 && code <= 122 )
            return code - (97 - KeyCode.a);

		switch ( code )
		{

		case 32:
            return KeyCode.SPACE;
		case 63:
            return KeyCode.QUESTION;
		case 44:
            return KeyCode.COMMA;
		case 46:
            return KeyCode.DOT;
		case 48:
            return KeyCode.N0;
		case 49:
            return KeyCode.N1;
		case 50:
            return KeyCode.N2;
		case 51:
            return KeyCode.N3;
		case 52:
            return KeyCode.N4;
		case 53:
            return KeyCode.N5;
		case 54:
            return KeyCode.N6;
		case 55:
            return KeyCode.N7;
		case 56:
            return KeyCode.N8;
		case 57:
            return KeyCode.N9;
		case 10:
            return KeyCode.ENTER;
		case 27:
            return KeyCode.ESC;
		}

		if ( code == KEY_F1 )
            return KeyCode.F1;
		else if ( code == InputChar.KEY_F2 )
            return KeyCode.F2;
		else if ( code == InputChar.KEY_F3 )
            return KeyCode.F3;
		else if ( code == InputChar.KEY_F4 )
            return KeyCode.F4;
		else if ( code == InputChar.KEY_F5 )
            return KeyCode.F5;
		else if ( code == InputChar.KEY_F6 )
            return KeyCode.F6;
		else if ( code == InputChar.KEY_F7 )
            return KeyCode.F7;
		else if ( code == InputChar.KEY_F8 )
            return KeyCode.F8;
		else if ( code == InputChar.KEY_F9 )
            return KeyCode.F9;
		else if ( code == InputChar.KEY_F10 )
            return KeyCode.F10;
		else if ( code == InputChar.KEY_F11 )
            return KeyCode.F11;
		else if ( code == InputChar.KEY_F12 )
            return KeyCode.F12;
		else if ( code == KEY_BACKSPACE )
            return KeyCode.BACKSPACE;
		else if ( code == KEY_UP )
            return KeyCode.UARROW;
		else if ( code == KEY_DOWN )
            return KeyCode.DARROW;
		else if ( code == KEY_LEFT )
            return KeyCode.LARROW;
		else if ( code == KEY_RIGHT )
            return KeyCode.RARROW;
		return -1;
	}

	private CharColor getJCurseColor( int crlColor )
	{
		switch ( crlColor )
		{
		case ConsoleSystemInterface.BLACK:
			return BLACK;
		case ConsoleSystemInterface.DARK_BLUE:
			return DARK_BLUE;
		case ConsoleSystemInterface.GREEN:
			return GREEN;
		case ConsoleSystemInterface.TEAL:
			return TEAL;
		case ConsoleSystemInterface.DARK_RED:
			return DARK_RED;
		case ConsoleSystemInterface.PURPLE:
			return PURPLE;
		case ConsoleSystemInterface.BROWN:
			return BROWN;
		case ConsoleSystemInterface.LIGHT_GRAY:
			return LIGHT_GRAY;
		case ConsoleSystemInterface.GRAY:
			return GRAY;
		case ConsoleSystemInterface.BLUE:
			return BLUE;
		case ConsoleSystemInterface.LEMON:
			return LEMON;
		case ConsoleSystemInterface.CYAN:
			return CYAN;
		case ConsoleSystemInterface.RED:
			return RED;
		case ConsoleSystemInterface.MAGENTA:
			return MAGENTA;
		case ConsoleSystemInterface.YELLOW:
			return YELLOW;
		case ConsoleSystemInterface.WHITE:
			return WHITE;
		}
		return null;
	}

}