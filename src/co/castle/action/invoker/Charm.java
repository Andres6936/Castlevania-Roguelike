package co.castle.action.invoker;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Consts;
import co.castle.player.Player;
import sz.util.Util;

public class Charm extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level x = performer.getLevel( );
		Monster m = x.getMonsterAt( targetPosition );
		int chance = 40 + 2 * aPlayer.getSoulPower( );
		if ( aPlayer.getFlag( "SKILL_CONFIDENCE" ) )
			chance += 20;
		if ( m == null )
		{
			x.addMessage( "Nothing happens." );
		}
		else
		{
			if ( Util.chance( chance ) )
			{
				x.addMessage( "You manipulate the " + m.getDescription( ) + " soul!" );
				m.setCounter( Consts.C_MONSTER_CHARM, 50 + aPlayer.getSoulPower( ) * 5 );
			}
			else
			{
				x.addMessage(
						"You tried to manipulate the " + m.getDescription( ) + " soul." );
			}

		}

	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "Charm";
	}

	public String getPromptPosition( )
	{
		return "Who?";
	}

	public String getSFX( )
	{
		return "wav/clockbel.wav";
	}

	public double getTimeCostModifier( )
	{
		return 3;
	}

	public boolean needsPosition( )
	{
		return true;
	}
};