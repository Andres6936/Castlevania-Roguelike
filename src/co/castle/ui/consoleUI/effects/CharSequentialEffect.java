package co.castle.ui.consoleUI.effects;

import java.util.Enumeration;
import java.util.Vector;

import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharSequentialEffect extends CharEffect
{
	private int color;
	private Vector sequence;
	private String tiles;

	public CharSequentialEffect(	String ID, Vector sequence, String tiles, int color,
									int delay )
	{
		super( ID );
		setAnimationDelay( delay );
		this.tiles = tiles;
		this.color = color;
		this.sequence = sequence;
	}

	public void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si )
	{
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		int tileIndex = 0;
		Enumeration seq = sequence.elements( );
		while ( seq.hasMoreElements( ) )
		{
			Position nextPosition = Position.add( center, (Position) seq.nextElement( ) );
			tileIndex++;
			if ( tileIndex == tiles.length( ) )
				tileIndex = 0;
			if ( ui.insideViewPort( nextPosition ) )
				si.print( nextPosition.x, nextPosition.y, tiles.charAt( tileIndex ),
						color );
			animationPause( );
		}
	}

}