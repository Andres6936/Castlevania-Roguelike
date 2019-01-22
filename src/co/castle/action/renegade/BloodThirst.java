package co.castle.action.renegade;

import co.castle.action.HeartAction;
import co.castle.player.Consts;
import co.castle.player.Player;

public class BloodThirst extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		aPlayer.getLevel( ).addMessage( "Your vampire instinct awakes!" );
		aPlayer.setCounter( Consts.C_BLOOD_THIRST, 10 + aPlayer.getSoulPower( ) * 4 );
	}

	public int getHeartCost( )
	{
		return 10;
	}

	public String getID( )
	{
		return "BloodThirst";
	}

	public String getSFX( )
	{
		return "wav/alu_dark.wav";
	}

}