package co.castle.action.vkiller;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Player;

public class BlastCrystal extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "You release a mystic crystal!" );
		aLevel.addSmartFeature( "BLAST_CRYSTAL", performer.getPosition( ) );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "BLAST_CRYSTAL";
	}
}