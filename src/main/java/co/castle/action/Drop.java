package co.castle.action;

import co.castle.level.Level;
import co.castle.player.Player;

public class Drop extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "You drop a " + targetItem.getDescription( ) );
		( (Player) performer ).reduceQuantityOf( targetItem );
		aLevel.addItem( performer.getPosition( ), targetItem );
	}

	public String getID( )
	{
		return "Drop";
	}

	public String getPromptItem( )
	{
		return "What do you want to drop?";
	}

	public boolean needsItem( )
	{
		return true;
	}
}