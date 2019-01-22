package co.castle.feature.ai;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.feature.SmartFeature;
import co.castle.feature.action.Shine;
import co.castle.monster.Monster;

/**
 * Stays alive for 5 turns, causes an animation each turn then dies;
 */

public class FlameAI implements ActionSelector, Cloneable
{
	private boolean activated;
	private int turnsToDie;

	public ActionSelector derive( )
	{
		try
		{
			return (ActionSelector) clone( );
		}
		catch ( CloneNotSupportedException cnse )
		{
			return null;
		}
	}

	public String getID( )
	{
		return "FLAME_SELECTOR";
	}

	public Action selectAction( Actor who )
	{
		if ( activated )
		{
			turnsToDie--;
			if ( turnsToDie == 0 )
			{
				who.die( );
				who.getLevel( ).removeSmartFeature( (SmartFeature) who );
				activated = false;
				return null;
			}
			Monster m = who.getLevel( ).getMonsterAt( who.getPosition( ) );
			if ( m != null )
			{
				m.damage( new StringBuffer( ), 1 );
			}
			return Shine.getAction( );
		}
		else
		{
			turnsToDie = 5;
			activated = true;
			return Shine.getAction( );
		}
	}

}