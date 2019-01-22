package co.castle.action;

import co.castle.item.Item;
import co.castle.level.Level;
import co.castle.player.Player;
import sz.util.Debug;

public class Get extends Action
{
	public void execute( )
	{
		Debug.doAssert( performer instanceof Player,
				"An actor different from the player tried to execute Walk action" );
		Debug.enterMethod( this, "execute" );
		Player aPlayer = (Player) performer;
		Level aLevel = performer.getLevel( );

		Item destinationItem = targetItem;
		if ( destinationItem != null )
		{
			// while (destinationItem != null){
			if ( aPlayer.canCarry( ) )
			{
				aLevel.addMessage(
						"You pick up the " + destinationItem.getDescription( ) + "." );
				aPlayer.addItem( destinationItem );
				aLevel.removeItemFrom( destinationItem, performer.getPosition( ) );
			}
			else
			{
				aLevel.addMessage( "You have too many things already to pick up the "
						+ destinationItem.getDescription( ) + "!." );
			}

			// destinationItem = aLevel.getItemAt(destinationPoint);
		}
		else
		{
			aLevel.addMessage( "There is nothing to pick up here!" );
		}
		Debug.exitMethod( );
	}

	public String getID( )
	{
		return "Get";
	}

	public String getPrompUnderlyingItem( )
	{
		return "What Item will you get?";
	}

	public boolean needsUnderlyingItem( )
	{
		return true;
	}

}