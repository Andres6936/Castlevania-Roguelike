package co.castle.cuts.intro;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.SFXManager;
import co.castle.level.Level;

public class Intro2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		if ( level.getFlag( "INTRO1" )
				&& level.getCounter( "COUNTBACK_INTRO_1" ).isOver( ) )
		{
			level.addMessage( "You hear howling in the distance!" );
			SFXManager.play( "wav/howl.wav" );
			level.addCounter( "COUNTBACK_INTRO_2", 5 );
			level.setFlag( "INTRO2", true );
			level.removeCounter( "COUNTBACK_INTRO_1" );
			enabled = false;
		}
	}

}