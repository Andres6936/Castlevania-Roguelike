package sz.csi.wswing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sz.csi.KeyCode;

public class StrokeInformer implements KeyListener, java.io.Serializable
{
	private int bufferCode;
	// private transient UserInterface aUI;
	private transient Thread keyListener;

	public StrokeInformer( )
	{
		bufferCode = -1;
	}

	public int getInkeyBuffer( )
	{
		return bufferCode;
	}

	public void informKey( Thread toWho )
	{
		keyListener = toWho;
	}

	/*
	 * public void setUI(UserInterface pUI){ aUI = pUI; }
	 */

	public void keyPressed( KeyEvent e )
	{
		bufferCode = charCode( e );
		// if (!e.isShiftDown())
		keyListener.interrupt( );
	}

	public void keyReleased( KeyEvent e )
	{
	}

	public void keyTyped( KeyEvent e )
	{
	}

	private int charCode( KeyEvent x )
	{
		int code = x.getKeyCode( );
		if ( x.isControlDown( ) )
		{
            return KeyCode.CTRL;
		}
		if ( code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z )
		{
			if ( x.getKeyChar( ) >= 'a' )
			{
                int diff = KeyEvent.VK_A - KeyCode.a;
				return code - diff;
			}
			else
			{
                int diff = KeyEvent.VK_A - KeyCode.A;
				return code - diff;
			}
		}

		if ( x.getKeyChar( ) == '?' )
            return KeyCode.QUESTION;

		switch ( x.getKeyCode( ) )
		{
		case KeyEvent.VK_SPACE:
            return KeyCode.SPACE;

		case KeyEvent.VK_COMMA:
            return KeyCode.COMMA;
		case KeyEvent.VK_PERIOD:
		case KeyEvent.VK_DECIMAL:
            return KeyCode.DOT;
		case KeyEvent.VK_NUMPAD0:
            return KeyCode.N0;
		case KeyEvent.VK_NUMPAD1:
            return KeyCode.N1;
		case KeyEvent.VK_NUMPAD2:
            return KeyCode.N2;
		case KeyEvent.VK_NUMPAD3:
            return KeyCode.N3;
		case KeyEvent.VK_NUMPAD4:
            return KeyCode.N4;
		case KeyEvent.VK_NUMPAD5:
            return KeyCode.N5;
		case KeyEvent.VK_NUMPAD6:
            return KeyCode.N6;
		case KeyEvent.VK_NUMPAD7:
            return KeyCode.N7;
		case KeyEvent.VK_NUMPAD8:
            return KeyCode.N8;
		case KeyEvent.VK_NUMPAD9:
            return KeyCode.N9;
		case KeyEvent.VK_F1:
            return KeyCode.F1;
		case KeyEvent.VK_ENTER:
            return KeyCode.ENTER;
		case KeyEvent.VK_BACK_SPACE:
            return KeyCode.BACKSPACE;
		case KeyEvent.VK_ESCAPE:
            return KeyCode.ESC;
		case KeyEvent.VK_UP:
            return KeyCode.UARROW;
		case KeyEvent.VK_DOWN:
            return KeyCode.DARROW;
		case KeyEvent.VK_LEFT:
            return KeyCode.LARROW;
		case KeyEvent.VK_RIGHT:
            return KeyCode.RARROW;

		}
        return KeyCode.NONE;
	}
}