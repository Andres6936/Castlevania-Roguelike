package co.castle.cuts.mummies;

import co.castle.cuts.Unleasher;
import co.castle.game.CRLException;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.level.Level;
import co.castle.levelgen.StaticGenerator;
import co.castle.ui.Display;
import sz.util.Position;

import java.util.Hashtable;

public class Mummy1 extends Unleasher {
	private Hashtable charMap = new Hashtable();

	String[] newMap = new String[]
			{"---wwwwwwwwwwwwwwwwwwwwwwwwwwwwww---", "--ww------.....------------.....ww--",
					"-ww.-----------..o------------...ww-", "ww..----------..ooo-------------..ww",
					"w..--o-----------o------------o---.w", "w---ooo----------------------ooo---w",
					"w----o------------------------o----w", "w----------------------------------w",
		"w.--------------------------------.w", "w>.-------------------------------.w",
		"w..------------------------------..w", "E.-------------------------------..w",
		"w..-------------------------------.w", "w...------------------------------.w",
		"w..--------------------------------w", "w----------------------------------w",
		"w----o------------------------o----w", "w.--ooo----------------------ooo---w",
		"w...-o-----------o------------o--..w", "ww..------------ooo-------------..ww",
		"-ww..------..----o--------...--..ww-", "--ww............................ww--",
		"---wwwwwwwwwwwwwwwwwwwwwwwwwwwwww---" };

	{
		charMap.put( "o", "RUINS_COLUMN" );
		charMap.put( ".", "RUINS_FLOOR" );
		charMap.put( "-", "AIR" );
		charMap.put( "w", "RUINS_WALL" );
		charMap.put( "h", "RUINS_FLOOR_H" );
		charMap.put( "s", "RUINS_STAIRS" );
		charMap.put( "<", "MARBLE_STAIRSUP" );
		charMap.put( ">", "MARBLE_STAIRSDOWN" );
		charMap.put( "c", "RUINS_FLOOR FEATURE CANDLE 70" );
		charMap.put( "C", "RUINS_FLOOR FEATURE COFFIN 100" );
		charMap.put( "E", "NOTHING" );
		charMap.put( "D", "RUINS_FLOOR EXIT _NEXT" );
	}
	public void unleash( Level level, Game game )
	{
		if ( level.getBoss( ) == null ) {
			Display.thus.showScreen(
					"As you destroy the mummy of Akmodan, the whole room trembles, and a cold hurricaned wind covers all the place, shredding everything on sight. All of a sudden, the floor under you collapses.");
			level.getPlayer().reduceKeys(1);
			level.setMusicKeyMorning("");
			level.setMusicKeyNoon("");
			MusicManager.stopMusic();
			try {
				StaticGenerator.getGenerator().renderOverLevel(level, newMap, charMap,
						new Position(0, 0));
			} catch (CRLException crle) {
				Game.crash("Error on Mummy1 unleasher", crle);
			}
			enabled = false;
		}
	}
}
