package co.castle.action.vkiller;

import co.castle.action.Action;
import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Player;
import sz.util.Position;

public class ItemBreakBlastCrystal extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "You release a handful of mystic crystals!!" );
		aLevel.addSmartFeature( "BLAST_CRYSTAL",
				Position.add( performer.getPosition( ), Action.VARUP ) );
		aLevel.addSmartFeature( "BLAST_CRYSTAL",
				Position.add( performer.getPosition( ), Action.VARDN ) );
		aLevel.addSmartFeature( "BLAST_CRYSTAL",
				Position.add( performer.getPosition( ), Action.VARLF ) );
		aLevel.addSmartFeature( "BLAST_CRYSTAL",
				Position.add( performer.getPosition( ), Action.VARRG ) );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 10;
	}

	public String getID( )
	{
		return "BLAST_CRYSTAL";
	}

}