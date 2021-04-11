package co.castle.cuts.prelude;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;

public class Prelude5 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		if ( !level.getFlag( "CHRIS_DEAD" )
				|| level.getMonsterByID( "PRELUDE_DRACULA" ).getHits( ) > 50 )
			return;
		Display.thus.showChat( "PRELUDE_DRACULA3", game );
		UserInterface.getUI( )
				.drawEffect( EffectFactory.getSingleton( ).createLocatedEffect(
						level.getPlayer( ).getPosition( ), "SFX_MORPH_SOLIEYU" ) );
		UserInterface.getUI( ).refresh( );
		Display.thus.showChat( "PRELUDE_DRACULA4", game );
		// level.getMonsterByID("PRELUDE_DRACULA").execute(ActionFactory.getActionFactory().getAction("PRELUDE_MORPH_SOLIEYU"));
		// Display.thus.showChat(CharCuts.thus.get("PRELUDE_DRACULA4"));
		Display.thus.showScreen(
				"Soleiyu arrived to the dwelling of Dracula far too late... the former body of his father laying on the ground as the one last remain of the battle that had just finished, the dark count, almost unwounded, though his outfit burned and scratched, was at his throne having a sip of blood from his cup. Feeling the weight of his treason over his legacy, full of hatred against the murderer of his father, but more against himself for not following the path destiny had marked for him, he rushed against Dracula, whom with one alone raised hand and a slight gesture of dark manipulation, stopped the warrior and made him take again the shape that still lay deep inside him" );
		Display.thus.showScreen(
				"Darkness swept the world, as all hopes seemed to be lost, and long forgotten Belmont bloodlines had to rise again and take their paths again to the cursed castle, the time has come for the future of mankind to be decided." );
		game.exitGame( );
		// game.newGame();
	}

}
