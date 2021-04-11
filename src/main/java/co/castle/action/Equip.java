package co.castle.action;

import co.castle.actor.Actor;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.player.Player;

public class Equip extends Action
{
	public boolean canPerform( Actor a )
	{
		Player player = getPlayer( a );
		if ( !player.canWield( ) )
		{
			invalidationMessage = "You can't wear anything";
			return false;
		}
		return true;
	}

	public void execute( )
	{
		ItemDefinition def = targetItem.getDefinition( );
		Player player = (Player) performer;
		if ( !player.canWield( ) )
		{
			performer.getLevel( ).addMessage( "You can't wear anything!" );
			return;
		}

		switch ( def.getEquipCategory( ) )
		{
		case 1:
			for ( int i = 0; i < player.getBannedArmors( ).length; i++ )
				if ( player.getBannedArmors( )[ i ].equals( def.getID( ) ) )
				{
					performer.getLevel( )
							.addMessage( "You can't wear that kind of armor!" );
					return;
				}
			Item currentArmor = player.getArmor( );
			player.reduceQuantityOf( targetItem );
			if ( currentArmor != null )
				player.addItem( currentArmor );
			performer.getLevel( ).addMessage( "You wear the " + def.getDescription( ) );
			player.setArmor( targetItem );
			break;
		case 2:
			if ( player.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER
					&& player.getFlag( "ONLY_VK" ) )
			{
				performer.getLevel( ).addMessage( "You can't abandon the mystic whip" );
				return;
			}
			Item currentWeapon = player.getWeapon( );
			Item currentSecondaryWeapon = player.getSecondaryWeapon( );
			if ( targetItem.isTwoHanded( ) )
			{
				Item currentShield = player.getShield( );
				if ( currentShield != null )
				{
					if ( !player.canCarry( ) )
					{
						performer.getLevel( )
								.addMessage( "You can't store your "
										+ currentShield.getDescription( )
										+ " to wear this two handed weapon." );
						return;
					}
					else
					{
						performer.getLevel( ).addMessage(
								"You remove your " + currentShield.getDescription( ) );
						player.addItem( currentShield );
						player.setShield( null );
					}
				}
			}

			if ( currentWeapon != null )
			{
				if ( currentSecondaryWeapon != null )
				{
					if ( !player.canCarry( ) )
					{
						performer.getLevel( ).addMessage( "You are unable to store your "
								+ currentSecondaryWeapon.getDescription( ) );
						return;
					}
					else
					{
						player.setSecondaryWeapon( currentWeapon );
						performer.getLevel( ).addMessage( "You put your "
								+ currentWeapon.getDescription( ) + " in your belt." );
						player.addItem( currentSecondaryWeapon );
						performer.getLevel( ).addMessage( "You store your "
								+ currentSecondaryWeapon.getDescription( ) );
					}
				}
				else
				{
					player.setSecondaryWeapon( currentWeapon );
					performer.getLevel( ).addMessage( "You put your "
							+ currentWeapon.getDescription( ) + " in your belt." );
				}
			}
			performer.getLevel( ).addMessage( "You wield the " + def.getDescription( ) );
			player.setWeapon( targetItem );
			player.reduceQuantityOf( targetItem );

			break;
		case 3: // SHIELDS
			Item currentShield = player.getShield( );
			currentWeapon = player.getWeapon( );

			if ( currentWeapon != null && currentWeapon.isTwoHanded( ) )
			{
				// Must remove weapon to use shield
				if ( !player.canCarry( ) )
				{
					performer.getLevel( ).addMessage(
							"You are unable to store your current weapon to wear the shield." );
					return;
				}
				else
				{
					player.setWeapon( null );
					player.addItem( currentWeapon );
				}
			}
			if ( currentShield != null )
			{
				// Remove the current shield
				if ( !player.canCarry( ) )
				{
					performer.getLevel( ).addMessage(
							"You are unable to store your current shiled to wear the new one." );
					return;
				}
				else
				{
					player.setShield( null );
					player.addItem( currentShield );
				}
			}

			player.setShield( targetItem );
			player.reduceQuantityOf( targetItem );
			performer.getLevel( ).addMessage( "You wear the " + def.getDescription( ) );
			break;
		}
	}

	public int getCost( )
	{
		return 50;
	}

	public String getID( )
	{
		return "Equip";
	}

	public String getPromptItem( )
	{
		return "Wear what?";
	}

	public boolean needsItem( )
	{
		return true;
	}
}