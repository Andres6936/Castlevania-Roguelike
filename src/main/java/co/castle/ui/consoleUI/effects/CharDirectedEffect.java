package co.castle.ui.consoleUI.effects;

import sz.util.Line;
import sz.util.Position;

public abstract class CharDirectedEffect extends CharEffect
{
	protected int depth;
	protected Line effectLine;

	public CharDirectedEffect( String id )
	{
		super( id );
	}

	public CharDirectedEffect( String id, int delay )
	{
		super( id, delay );
	}

	public int getDepth( )
	{
		return depth;
	}

	public void set(	Position loc, Position startPosition, Position pivotPosition,
						int depth )
	{
		super.set( loc );
		effectLine = new Line( startPosition, pivotPosition );
		setDepth( depth );
	}

	public void setDepth( int value )
	{
		depth = value;
	}

}
