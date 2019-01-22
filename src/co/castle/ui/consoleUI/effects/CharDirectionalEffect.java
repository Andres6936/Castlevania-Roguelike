package co.castle.ui.consoleUI.effects;

import sz.util.Position;

public abstract class CharDirectionalEffect extends CharEffect
{
	protected int depth;
	protected int direction;

	public CharDirectionalEffect( String id )
	{
		super( id );
	}

	public CharDirectionalEffect( String id, int delay )
	{
		super( id, delay );
	}

	public void set( Position position, int pDirection, int pDepth )
	{
		super.set( position );
		direction = pDirection;
		depth = pDepth;
	}
}
