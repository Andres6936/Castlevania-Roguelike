package co.castle.ui.consoleUI.effects;

import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharAnimatedEffect extends CharEffect
{
	private int color;
	private String frames;

	public CharAnimatedEffect( String ID, Position where, String frames, int color )
	{
		super( ID );
		setFrames( frames );
		setColor( color );
	}

	public void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si )
	{
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position absolute = Position.add( ui.PC_POS, relative );
		if ( !ui.insideViewPort( absolute ) )
			return;
		char[ ] cframes = frames.toCharArray( );
		for ( int j = 0; j < cframes.length; j++ )
		{
			si.print( absolute.x, absolute.y, cframes[ j ], color );
			animationPause( );
		}
	}

	public int getColor( )
	{
		return color;
	}

	public String getFrames( )
	{
		return frames;
	}

	public void setColor( int value )
	{
		color = value;
	}

	public void setFrames( String value )
	{
		frames = value;
	}
}
