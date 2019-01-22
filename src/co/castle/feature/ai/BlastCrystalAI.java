package co.castle.feature.ai;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.feature.SmartFeature;
import co.castle.feature.action.Blast;

public class BlastCrystalAI implements ActionSelector, Cloneable
{
	private boolean activated;
	private int blastCounter;
	private int turnsToBlast;

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
		return "CRYSTAL_SELECTOR";
	}

	public Action selectAction( Actor who )
	{
		if ( activated )
		{
			if ( blastCounter > 2 )
			{
				who.die( );
				who.getLevel( ).removeSmartFeature( (SmartFeature) who );
				activated = false;
				return null;
			}
			else
			{
				turnsToBlast--;
				if ( turnsToBlast == 0 )
				{
					turnsToBlast = 5;
					blastCounter++;
					return new Blast( );
				}
				else
				{
					return null;
				}
			}
		}
		else
		{
			turnsToBlast = 5;
			activated = true;
			return new Blast( );
		}
	}

}