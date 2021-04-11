package co.castle.action;

import co.castle.actor.Actor;
import co.castle.player.Player;

public class Unequip extends Action
{
	public boolean canPerform( Actor a )
	{
		boolean ret = ( (Player) a ).canCarry( );
		if ( !ret )
		{
			invalidationMessage = "Your pack is full";
		}
		return ret;
	}

	public void execute( )
	{
		Player player = (Player) performer;
		if ( player.getArmor( ) == targetEquipedItem )
		{
			if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
			{
				performer.getLevel( )
						.addMessage( "You can't abandon the vampire killer armor" );
				return;
			}
			player.addItem( player.getArmor( ) );
			player.getLevel( ).addMessage(
					"You take off your " + targetEquipedItem.getDescription( ) );
			player.setArmor( null );
		}
		if ( player.getWeapon( ) == targetEquipedItem )
		{
			if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER
					&& player.getFlag( "ONLY_VK" ) )
			{
				performer.getLevel( ).addMessage( "You can't abandon the mystic whip" );
				return;
			}
			player.addItem( player.getWeapon( ) );
			player.getLevel( ).addMessage(
					"You unwield your " + targetEquipedItem.getDescription( ) );
			player.setWeapon( null );
		}
		if ( player.getShield( ) == targetEquipedItem )
		{
			player.addItem( player.getShield( ) );
			player.getLevel( ).addMessage(
					"You take off your " + targetEquipedItem.getDescription( ) );
			player.setShield( null );
		}
		if ( player.getSecondaryWeapon( ) == targetEquipedItem )
		{
			player.addItem( player.getSecondaryWeapon( ) );
			player.getLevel( ).addMessage(
					"You draw back your " + targetEquipedItem.getDescription( ) );
			player.setSecondaryWeapon( null );
		}
	}

	public String getID( )
	{
		return "Unequip";
	}

	public String getPromptEquipedItem( )
	{
		return "What do you want to unequip?";
	}

	public boolean needsEquipedItem( )
	{
		return true;
	}
}