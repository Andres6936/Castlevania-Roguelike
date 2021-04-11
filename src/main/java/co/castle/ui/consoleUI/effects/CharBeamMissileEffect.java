package co.castle.ui.consoleUI.effects;

import co.castle.ui.UserInterface;
import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharBeamMissileEffect extends CharDirectedEffect
{
	private int misColor;
	private String missile;

	public CharBeamMissileEffect( String ID, String missile, int misColor, int delay )
	{
		super( ID );
		setMissile( missile );
		setMisColor( misColor );
		setAnimationDelay( delay );

	}

	public void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si )
	{
		UserInterface.getUI( ).getPlayer( ).see( );
		UserInterface.getUI( ).refresh( );

		Position oldPoint = effectLine.next( );
		int too = 0;
		for ( int i = 0; i < depth; i++ )
		{
			Position next = effectLine.next( );
			too++;
			if ( too == missile.length( ) )
				too = 0;
			char icon = missile.charAt( too );
			Position relative = Position.subs( next, ui.getPlayer( ).getPosition( ) );
			Position toPrint = Position.add( ui.PC_POS, relative );
			if ( !ui.insideViewPort( toPrint ) )
				break;
			si.safeprint( toPrint.x( ), toPrint.y( ), icon, misColor );
			animationPause( );
		}
	}

	public void setMisColor( int value )
	{
		misColor = value;
	}

	public void setMissile( String value )
	{
		missile = value;
	}

}