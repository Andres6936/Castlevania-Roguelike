package co.castle.action;

import co.castle.actor.Actor;
import co.castle.item.Item;
import co.castle.player.Player;

public class Reload extends Action
{
	private transient Item weapon;

	public boolean canPerform( Actor a )
	{
		Player aPlayer = getPlayer( a );
		Item weapon = aPlayer.getWeapon( );
		if ( weapon != null )
		{
			if ( weapon.getReloadTurns( ) > 0 )
			{
				if ( aPlayer.getGold( ) < weapon.getDefinition( ).getReloadCostGold( ) )
				{
					invalidationMessage = "You need "
							+ weapon.getDefinition( ).getReloadCostGold( )
							+ " gold to reload the " + weapon.getDescription( );
					return false;
				}
				else
				{
					if ( aPlayer.getHearts( ) > 0 )
						return true;
					else
					{
						invalidationMessage = "You need soul power to reload the "
								+ weapon.getDescription( );
						return false;
					}
				}
			}
			else
			{
				invalidationMessage = "The " + weapon.getDescription( )
						+ " cannot be reloaded";
				return false;
			}
		}
		else
		{
			invalidationMessage = "You can't reload yourself";
			return false;
		}
	}

	public void execute( )
	{
		Player aPlayer = (Player) performer;
		weapon = aPlayer.getWeapon( );
		if ( weapon != null )
		{
			if ( weapon.getDefinition( ).isSingleUse( ) )
			{
				aPlayer.getLevel( )
						.addMessage( "You can't reload the " + weapon.getDescription( ) );
			}
			else if ( aPlayer.getGold( ) < weapon.getDefinition( ).getReloadCostGold( ) )
				aPlayer.getLevel( )
						.addMessage( "You can't reload the " + weapon.getDescription( ) );
			else
			{
				weapon.reload( );
				aPlayer.reduceGold( weapon.getDefinition( ).getReloadCostGold( ) );
				aPlayer.reduceHearts( 1 );
				aPlayer.getLevel( )
						.addMessage( "You reload the " + weapon.getDescription( ) + " ("
								+ weapon.getDefinition( ).getReloadCostGold( )
								+ " gold)" );
			}
		}
		else
			aPlayer.getLevel( ).addMessage( "You can't reload yourself" );
	}

	public int getCost( )
	{
		if ( weapon != null )
			return 10 * weapon.getDefinition( ).getReloadCostGold( );
		else
			return 50;
	}

	public String getID( )
	{
		return "Reload";
	}
}