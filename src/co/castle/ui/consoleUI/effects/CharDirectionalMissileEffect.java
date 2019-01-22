package co.castle.ui.consoleUI.effects;

import co.castle.action.Action;
import co.castle.ui.UserInterface;
import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharDirectionalMissileEffect extends CharDirectedEffect
{
	private int misColor;
	private String missile;

	public CharDirectionalMissileEffect(	String ID, String missile, int misColor,
											int delay )
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
		int oldColor = -1;
		char oldChar = ' ';

		for ( int i = 0; i < depth; i++ )
		{
			Position next = effectLine.next( );
			int direction = solveDirection( oldPoint, next );
			if ( i != 0 )
			{
				Position relative = Position.subs( oldPoint,
						ui.getPlayer( ).getPosition( ) );
				Position toPrint = Position.add( ui.PC_POS, relative );
				si.safeprint( toPrint.x( ), toPrint.y( ), oldChar, oldColor );
			}

			oldPoint = new Position( next );
			char icon = ' ';
			switch ( direction )
			{
			case Action.UPLEFT:
				icon = missile.charAt( 0 );
				break;
			case Action.UP:
				icon = missile.charAt( 1 );
				break;
			case Action.UPRIGHT:
				icon = missile.charAt( 2 );
				break;
			case Action.LEFT:
				icon = missile.charAt( 3 );
				break;
			case Action.RIGHT:
				icon = missile.charAt( 4 );
				break;
			case Action.DOWNLEFT:
				icon = missile.charAt( 5 );
				break;
			case Action.DOWN:
				icon = missile.charAt( 6 );
				break;
			case Action.DOWNRIGHT:
				icon = missile.charAt( 7 );
				break;
			}
			Position relative = Position.subs( next, ui.getPlayer( ).getPosition( ) );
			Position toPrint = Position.add( ui.PC_POS, relative );
			if ( !ui.insideViewPort( toPrint ) )
				break;
			oldChar = si.peekChar( toPrint.x( ), toPrint.y( ) );
			oldColor = si.peekColor( toPrint.x( ), toPrint.y( ) );
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

	private int solveDirection( Position old, Position newP )
	{
		if ( newP.x( ) == old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
			{
				return Action.DOWN;
			}
			else
			{
				return Action.UP;
			}
		}
		else if ( newP.y( ) == old.y( ) )
		{
			if ( newP.x( ) > old.x( ) )
			{
				return Action.RIGHT;
			}
			else
			{
				return Action.LEFT;
			}
		}
		else if ( newP.x( ) < old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNLEFT;
			else
				return Action.UPLEFT;
		}
		else
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNRIGHT;
			else
				return Action.UPRIGHT;
		}
	}

}