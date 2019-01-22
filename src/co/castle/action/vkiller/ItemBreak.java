package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.player.Player;
import co.castle.ui.ActionCancelException;
import co.castle.ui.UserInterface;

public class ItemBreak extends HeartAction
{
	private HeartAction AXEBREAK = new ItemBreakAxe( ),
			BIBLEBREAK = new ItemBreakBible( ),
			CRYSTALBREAK = new ItemBreakBlastCrystal( ),
			CROSSBREAK = new ItemBreakCross( ), DAGGERBREAK = new ItemBreakDagger( ),
			HOLYBREAK = new ItemBreakHoly( ), FISTBREAK = new ItemBreakSacredFist( ),
			STOPBREAK = new ItemBreakStopwatch( );

	public void execute( )
	{
		super.execute( );
		HeartAction breakAction = getBreakAction( );
		if ( breakAction == null )
		{
			getPlayer( ).getLevel( ).addMessage( "??" );
			return;

		}
		try
		{
			UserInterface.getUI( ).setTargets( breakAction );
			if ( breakAction.canPerform( performer ) )
				breakAction.execute( );
		}
		catch ( ActionCancelException ace )
		{
			getPlayer( ).getLevel( ).addMessage( "Cancelled. " );
		}
	}

	public HeartAction getBreakAction( )
	{
		switch ( getPlayer( ).getMysticWeapon( ) )
		{
		case Player.AXE:
			return AXEBREAK;
		case Player.BIBLE:
			return BIBLEBREAK;
		case Player.SACRED_CRYSTAL:
			return CRYSTALBREAK;
		case Player.CROSS:
			return CROSSBREAK;
		case Player.DAGGER:
			return DAGGERBREAK;
		case Player.HOLY:
			return HOLYBREAK;
		case Player.SACRED_FIST:
			return FISTBREAK;
		case Player.STOPWATCH:
			return STOPBREAK;
		}
		return null;
	}

	public int getHeartCost( )
	{
		// return ((HeartAction)(getPlayer().getMysticAction())).getHeartCost();
		HeartAction breakAction = getBreakAction( );
		if ( breakAction != null )
			return getBreakAction( ).getHeartCost( );
		else
			return 0;
	}

	public String getID( )
	{
		return "ItemBreak";
	}
}
