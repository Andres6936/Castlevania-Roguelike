package co.castle.ui.consoleUI.effects;

import co.castle.ui.consoleUI.ConsoleUserInterface;
import co.castle.ui.effects.Effect;
import sz.csi.ConsoleSystemInterface;

public abstract class CharEffect extends Effect
{
	public CharEffect( String id )
	{
		super( id );
	}

	public CharEffect( String id, int delay )
	{
		super( id, delay );
	}

	public abstract void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si );

}
