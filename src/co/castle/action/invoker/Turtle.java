package co.castle.action.invoker;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.player.Consts;
import co.castle.player.Player;

public class Turtle extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		aLevel.addMessage( "A cute turtle soul surrounds you" );
		aPlayer.setCounter( Consts.C_TURTLESHELL, 50 + aPlayer.getSoulPower( ) * 2 );
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.1 );
	}

	public int getHeartCost( )
	{
		return 5;
	}

	public String getID( )
	{
		return "Turtle";
	}

	public String getSFX( )
	{
		return "wav/turtleCry.wav";
	}
}