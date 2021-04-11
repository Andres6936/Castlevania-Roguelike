package co.castle.cuts.ingame.badBelmont;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.main.Service;
import co.castle.ui.Display;

public class BadBelmont1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Display.thus.showChat( "BADSOLIEYU1", game );
		if ( level.getPlayer( ).hasItemByID( "JUKEBOX" ) )
		{
			level.addMessage( "The jukebox plays a mellow melody" );
			Display.thus.showChat( "SAVESOLIEYU", game );
			level.removeMonster( level.getMonsterByID( "BADBELMONT" ) );
			level.getPlayer( ).setFlag( "SAVED_SOLIEYU", true );
			level.removeBoss( );
			level.getPlayer( )
					.addHistoricEvent( "saved Solieyu Belmont from certain doom" );
			level.getPlayer( ).addKeys( 1 );
		}
		else
		{
			level.setMusicKeyMorning("BADBELMONT");
			level.setMusicKeyNoon("BADBELMONT");
			Service.playKey("BADBELMONT");
		}
		enabled = false;
	}

}
