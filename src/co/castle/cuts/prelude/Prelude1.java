package co.castle.cuts.prelude;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.Display;
import sz.util.Position;

public class Prelude1 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Display.thus.showScreen(
				"It was supposed to be a day to remember joyfully, as his son was soon to become the one to hold the hope for a peaceful future, to inherit the fate of the night hunters and team with him as vanquisher of darkness... however, it was not; Christopher Belmont had to face Count Dracula yet again, striving to restore his son to his human shape, and dismiss Dracula again for good." );
		Display.thus.showScreen(
				"15 years have gone by now... after being restored from his demon shape by his father, Soleiyu has been constantly running away from his vampire killer fate... the traces left on his body and soul by the demonic spell of Dracula never healed completely, and on his own self he refused to let his fate be determined by his blood." );
		Display.thus.showScreen(
				"One day however, the bloody full moon reappeared on the dark skies of Transylvania, one by one the towns of the region fell to the wrath of the dark armies, as no one seemed to assume the responsibility of facing the count on his own castle. Christopher Belmont, which had become a renowned trainer, was feeling the weight of the years coming over him, still, he joined the most brave and skilled warriors he could and headed to Castlevania, the dwelling of chaos." );
		Display.thus.showScreen(
				"One by one, his companions fell to the atrocities of the castle, their sacrifice being the only way to aid their master unto the only chance at sight to save mankind, finally, wounded and tired, he was for the third time in the same place he had been fifteen years ago; the vampire killer whip, which should have been in the hands of his son, gave him the last strengths to face Dracula again, and so, the final face-off began." );
		Monster dracula = level.getMonsterByID( "PRELUDE_DRACULA" );
		dracula.setPosition( new Position( level.getExitFor( "#DRACPOS" ) ) );
		dracula.setVisible( false );
		level.getMapCell( level.getExitFor( "#DRACPOS" ) )
				.setAppearance( AppearanceFactory.getAppearanceFactory( )
						.getAppearance( "DRACULA_THRONE2" ) );
		// level.removeExit("_START");
		enabled = false;
	}
}
