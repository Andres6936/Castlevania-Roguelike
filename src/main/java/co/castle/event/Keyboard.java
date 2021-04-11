package co.castle.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sz.csi.CharKey;

public class Keyboard implements KeyListener
{
	protected int bufferCode;
	protected transient Thread keyListener;

	public Keyboard( )
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

	public void keyPressed( KeyEvent e )
	{
		bufferCode = charCode( e );
		// if (!e.isShiftDown())
		if ( keyListener != null )
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
			return CharKey.CTRL;
		}
		if ( code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z )
		{
			if ( x.getKeyChar( ) >= 'a' )
			{
				int diff = KeyEvent.VK_A - CharKey.a;
				return code - diff;
			}
			else
			{
				int diff = KeyEvent.VK_A - CharKey.A;
				return code - diff;
			}
		}

		switch ( x.getKeyCode( ) )
		{
		case KeyEvent.VK_SPACE:
			return CharKey.SPACE;
		case KeyEvent.VK_COMMA:
			return CharKey.COMMA;
		case KeyEvent.VK_PERIOD:
			return CharKey.DOT;
		case KeyEvent.VK_DELETE:
			return CharKey.DELETE;
		case KeyEvent.VK_NUMPAD0:
			return CharKey.N0;
		case KeyEvent.VK_NUMPAD1:
			return CharKey.N1;
		case KeyEvent.VK_NUMPAD2:
			return CharKey.N2;
		case KeyEvent.VK_NUMPAD3:
			return CharKey.N3;
		case KeyEvent.VK_NUMPAD4:
			return CharKey.N4;
		case KeyEvent.VK_NUMPAD5:
			return CharKey.N5;
		case KeyEvent.VK_NUMPAD6:
			return CharKey.N6;
		case KeyEvent.VK_NUMPAD7:
			return CharKey.N7;
		case KeyEvent.VK_NUMPAD8:
			return CharKey.N8;
		case KeyEvent.VK_NUMPAD9:
			return CharKey.N9;
		case KeyEvent.VK_1:
			return CharKey.N1;
		case KeyEvent.VK_2:
			return CharKey.N2;
		case KeyEvent.VK_3:
			return CharKey.N3;
		case KeyEvent.VK_4:
			return CharKey.N4;
		case KeyEvent.VK_5:
			return CharKey.N5;
		case KeyEvent.VK_6:
			return CharKey.N6;
		case KeyEvent.VK_7:
			return CharKey.N7;
		case KeyEvent.VK_8:
			return CharKey.N8;
		case KeyEvent.VK_9:
			return CharKey.N9;
		case KeyEvent.VK_F1:
			return CharKey.F1;
		case KeyEvent.VK_F2:
			return CharKey.F2;
		case KeyEvent.VK_F3:
			return CharKey.F3;
		case KeyEvent.VK_F4:
			return CharKey.F4;
		case KeyEvent.VK_F5:
			return CharKey.F5;
		case KeyEvent.VK_F6:
			return CharKey.F6;
		case KeyEvent.VK_F7:
			return CharKey.F7;
		case KeyEvent.VK_F8:
			return CharKey.F8;
		case KeyEvent.VK_F9:
			return CharKey.F9;
		case KeyEvent.VK_F10:
			return CharKey.F10;
		case KeyEvent.VK_F11:
			return CharKey.F11;
		case KeyEvent.VK_F12:
			return CharKey.F12;
		case KeyEvent.VK_ENTER:
			return CharKey.ENTER;
		case KeyEvent.VK_BACK_SPACE:
			return CharKey.BACKSPACE;
		case KeyEvent.VK_ESCAPE:
			return CharKey.ESC;
		case KeyEvent.VK_UP:
			return CharKey.UARROW;
		case KeyEvent.VK_DOWN:
			return CharKey.DARROW;
		case KeyEvent.VK_LEFT:
			return CharKey.LARROW;
		case KeyEvent.VK_RIGHT:
			return CharKey.RARROW;

		}
		if ( x.getKeyChar( ) == '.' )
			return CharKey.DOT;
		if ( x.getKeyChar( ) == '?' )
			return CharKey.QUESTION;
		return -1;
	}
}
