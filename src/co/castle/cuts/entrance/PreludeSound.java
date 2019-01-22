package co.castle.cuts.entrance;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.main.Service;

public class PreludeSound extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Service.musicManager.stopMusic( );
		Service.musicManager.playKeyOnce( "PRELUDE" );
		enabled = false;
	}

}
