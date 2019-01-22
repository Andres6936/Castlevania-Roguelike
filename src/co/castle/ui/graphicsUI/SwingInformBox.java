package co.castle.ui.graphicsUI;

import javax.swing.JTextArea;

public class SwingInformBox extends JTextArea
{
	public synchronized void addText( String txt )
	{
		boolean wait = false;
		do
		{
			try
			{
				setText( getText( ) + txt + ". " );
				wait = false;
			}
			catch ( Error e )
			{
				wait = true;
			}
		}
		while ( wait );
	}

	public void clear( )
	{
		boolean wait = false;
		do
		{
			try
			{
				setText( "" );
				wait = false;
			}
			catch ( Error e )
			{
				wait = true;
			}
		}
		while ( wait );
	}

	public boolean isEditable( )
	{
		return false;
	}

	public boolean isFocusable( )
	{
		return false;
	}
}
