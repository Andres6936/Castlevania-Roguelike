package sz.csi;

import sz.util.Position;

public interface ConsoleSystemInterface
{
	public static final int BLACK = 0, DARK_BLUE = 1, GREEN = 2, TEAL = 3, DARK_RED = 4,
			PURPLE = 5, BROWN = 6, LIGHT_GRAY = 7, GRAY = 8, BLUE = 9, LEMON = 10,
			CYAN = 11, RED = 12, MAGENTA = 13, YELLOW = 14, WHITE = 15;

	/**
	 * Clears the screen
	 */
	public void cls( );

	/**
	 * Makes the screen flash with a given color
	 *
	 * @param color The color to use.
	 */
	public void flash( int color );

	/**
     * Waits until a key is pressed and returns it
     *
     * @return The key that was pressed
     */
    public KeyCode inkey();

	/**
	 * Reads a string from the keyboard
	 * 
	 * @return The String that was read after pressing enter
	 */
	public String input( );

	/**
	 * Reads a string from the keyboard with a maximum length
	 * 
	 * @return The String that was read after pressing enter
	 */
	public String input( int length);

	/**
	 * Checks if the position is valid
	 *
	 * @param e The position to check.
	 * @return true if the position is valid
	 */
	public boolean isInsideBounds( Position e);

	/**
	 * Locates the input caret on a given position
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 */
	public void locateCaret(int x, int y);

	/**
	 * Checks what character is at a given position
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @return The character at the x,y position
	 */
	public char peekChar(int x, int y);

	/**
	 * Checks what color is at a given position
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @return The color at the x,y position
	 */
	public int peekColor(int x, int y);

	/**
	 * Prints a character on the console
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @param what
	 *            The character to be printed
	 * @param color
	 *            The color, one of the ConsoleSystemInterface constants
	 */
	public void print( int x, int y, char what, int color);

	/**
	 * Prints a String on the console with the default color
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @param what
	 *            The String to be printed
	 */
	public void print( int x, int y, String what);

	/**
	 * Prints a String on the console
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @param what
	 *            The string to be printed
	 * @param color
	 *            The color, one of the ConsoleSystemInterface constants
	 */
	public void print( int x, int y, String what, int color );

	/**
	 * Refreshes the screen, printing all characters that were buffered Some
	 * implementations may instead write directly to the console
	 */
	public void refresh( );

	/**
	 * Refreshes the screen, printing all characters that were buffered, and
	 * interrupts the Thread Some implementations may instead write directly to the
	 * console
	 */
	public void refresh( Thread t );

	/**
	 * Restores the contents of the backup buffer to screen
	 */
	public void restore();

	/**
	 * Same as print but must check for validity of the coordinates
	 *
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @param what
	 *            The character to be printed
	 * @param color
	 *            The color, one of the ConsoleSystemInterface constants
	 */
	public void safeprint( int x, int y, char what, int color );

	/**
	 * Saves the screen contents to a backup buffer
	 */
	public void saveBuffer();

	/**
	 * Sets whether or not a buffer will be used
	 *
	 * @param value True if the buffer will be used.
	 */
	public void setAutoRefresh( boolean value);

	/**
	 * Waits for the user to press a key
	 *
	 * @param keyCode KeyCode to wait.
	 */
	public void waitKey( int keyCode );
}