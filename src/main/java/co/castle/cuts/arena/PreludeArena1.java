package co.castle.cuts.arena;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;

public class PreludeArena1 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Display.thus.showScreen(
				"In the year of 1451, the castle of Dracula emerges from the dust of the cursed soils in Transylvania. \n \n Sonia, a girl choosen by fate to be the first of vampire hunters, heads to the mysterious castle seeking to finish the misery and havoc unleashed by the servants of the count of chaos, Dracula. \n \n Wielding the sacred Vampire Killer whip and full of courage within her heart, she opens the porticullis of the castle courtyard." );
		Display.thus.showScreen(
				"Sonia crosses the castle courtyard leaving behind her all traces of light and venturing into the source of chaos itself. \n \n Suddenly, she hears a voice into her head.... \"This is not a place for mortals, prove to be worthy...  and you may then come in...\"" );
		Display.thus.showScreen(
				"Monstruous creatures of all classes crawl out from the soil and soar through the dark night, and the castle doors are sealed shut. \n \n Knowing that there is no way back, Sonia cracks her Vampire Killer whip... \n \n \n And so this adventure begins..." );
		level.removeExit( "_START" );
		enabled = false;
	}
}