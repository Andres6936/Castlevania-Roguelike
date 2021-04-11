package co.castle.ai.monster;

import java.util.Vector;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import sz.util.Debug;

public abstract class MonsterAI implements ActionSelector, Cloneable
{
	protected Vector <RangedAttack> rangedAttacks;

	public ActionSelector derive( )
	{
		try
		{
			return (ActionSelector) clone( );
		}
		catch ( Exception e )
		{
			Debug.byebye( "Failed to clone MonsterAI " + getID( ) );
			return null;
		}
	}

	public abstract String getID( );

	public abstract Action selectAction( Actor who );

	public void setRangedAttacks( Vector <RangedAttack> pRangedAttacks )
	{
		rangedAttacks = pRangedAttacks;
	}
}
