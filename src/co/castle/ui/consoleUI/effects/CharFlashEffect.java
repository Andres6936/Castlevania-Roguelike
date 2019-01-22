package co.castle.ui.consoleUI.effects;

import co.castle.ui.consoleUI.ConsoleUserInterface;
import sz.csi.ConsoleSystemInterface;

public class CharFlashEffect extends CharEffect
{
	private int color;

	public CharFlashEffect( String ID, int color )
	{
		super( ID );
		this.color = color;
	}

	public void drawEffect( ConsoleUserInterface ui, ConsoleSystemInterface si )
	{
		si.flash( color );
		// animationPause();
	}

}