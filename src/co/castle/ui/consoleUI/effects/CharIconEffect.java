package co.castle.ui.consoleUI.effects;

import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharIconEffect extends CharEffect
{
	private int color;
	private char tile;

	public CharIconEffect( String ID, char tile, int color, int delay )
	{
		super( ID );
		this.tile = tile;
		this.color = color;
		setAnimationDelay( delay );
	}

	public void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si )
	{
		// si.setAutoRefresh(false);
		Position relative = Position.subs( getPosition( ),
				ui.getPlayer( ).getPosition( ) );
		Position center = Position.add( ui.PC_POS, relative );
		if ( ui.insideViewPort( center ) )
			si.safeprint( center.x, center.y, tile, color );
		animationPause( );
	}
}