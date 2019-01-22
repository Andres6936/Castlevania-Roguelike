package co.castle.player;

import java.awt.Image;

import co.castle.action.Action;
import sz.csi.textcomponents.MenuItem;
import sz.gadgets.GFXMenuItem;

public class Skill implements MenuItem, GFXMenuItem
{
	private Action action;
	private String actionDescription;
	private int heartCost;
	private boolean symbolic;

	public Skill( String pActionDescription )
	{
		actionDescription = pActionDescription;
		symbolic = true;
	}

	public Skill( String pActionDescription, Action pAction, int pHeartCost )
	{
		actionDescription = pActionDescription;
		action = pAction;
		heartCost = pHeartCost;
	}

	public Action getAction( )
	{
		return action;
	}

	/* Unsafe, Coupled */
	public char getMenuChar( )
	{
		return ' ';
	}

	/* Unsafe, Coupled */
	public int getMenuColor( )
	{
		return 0;
	}

	public String getMenuDescription( )
	{
		if ( isSymbolic( ) )
			return actionDescription;
		else
			return actionDescription + " (" + heartCost + ")";
	}

	public String getMenuDetail( )
	{
		return null;
	}

	public Image getMenuImage( )
	{
		return null;
	}

	public boolean isSymbolic( )
	{
		return symbolic;
	}
}
