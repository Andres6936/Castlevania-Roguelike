package co.castle.action.weapon;

import co.castle.action.Action;
import co.castle.action.Attack;
import co.castle.actor.Actor;
import co.castle.item.Item;
import co.castle.level.Level;
import co.castle.player.Player;

public class EnergyBurst extends Action
{
	public boolean canPerform( Actor a )
	{
		Player aPlayer = (Player) a;
		if ( aPlayer.getHearts( ) < 10 )
		{
			invalidationMessage = "You need more energy!";
			return false;
		}
		Item wp = aPlayer.getWeapon( );
		if ( wp == null || wp.getReloadTurns( ) == 0 )
		{
			invalidationMessage = "This will only work with ranged weapons!";
			return false;
		}
		return true;
	}

	public void execute( )
	{
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		Item wp = aPlayer.getWeapon( );
		if ( wp == null || wp.getReloadTurns( ) == 0 )
		{
			aLevel.addMessage( "This will only work with ranged weapons!" );
			return;
		}
		if ( !checkHearts( 10 ) )
		{
			aLevel.addMessage( "You need more power!" );
			return;
		}

		int shots = wp.getRemainingTurnsToReload( );
		Attack atk = new Attack( );
		atk.setDirection( targetDirection );
		atk.setPerformer( performer );

		for ( int i = 0; i < shots; i++ )
		{
			atk.execute( );
		}

	}

	public String getID( )
	{
		return "ENERGY_BURST";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to fire at?";
	}

	public boolean needsDirection( )
	{
		return true;
	}
}