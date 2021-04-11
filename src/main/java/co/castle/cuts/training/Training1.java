package co.castle.cuts.training;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;

public class Training1 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Display.thus.showScreen(
				"Dracula has been defeated, and Christopher Belmont lives a peaceful life with his family; his son Soleiyu is growing strong and is about to begin his training." );
		Display.thus.showScreen(
				"Use the numerical pad or directional keys to move, 'L' to look at the different sign posts, '?' for help. Press 'Q' to exit this training." );
		UserInterface.getUI( ).setPersistantMessage(
				"Movement \n \n Use the numerical pad or directional keys to move (including diagonals!), exit through the door and bump into the signposts to follow the tutorial. \n \n Press '?' for help and 'Q' to exit." );

		enabled = false;
	}
}
