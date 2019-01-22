package co.castle.feature.ai;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.feature.SmartFeature;
import co.castle.feature.action.CrossBack;
import co.castle.ui.UserInterface;
import sz.util.Position;

public class CrossAI implements ActionSelector, Cloneable
{
	private Position targetPosition;

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
		return "CROSS_SELECTOR";
	}

	public Action selectAction( Actor who )
	{
		Action ret = new CrossBack( );
		ret.setPosition( targetPosition );
		who.die( );
		who.getLevel( ).removeSmartFeature( (SmartFeature) who );
		UserInterface.getUI( ).getPlayer( ).see( );
		UserInterface.getUI( ).refresh( );
		return ret;
	}

	public void setTargetPosition( Position value )
	{
		targetPosition = value;
	}
}