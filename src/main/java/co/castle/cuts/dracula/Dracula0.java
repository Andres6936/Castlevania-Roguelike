package co.castle.cuts.dracula;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.AppearanceFactory;
import sz.util.Position;

public class Dracula0 extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		Monster dracula = level.getMonsterByID( "DRACULA" );
		dracula.setPosition( new Position( level.getExitFor( "#DRACPOS" ) ) );
		dracula.setVisible( false );
		level.getMapCell( level.getExitFor( "#DRACPOS" ) )
				.setAppearance( AppearanceFactory.getAppearanceFactory( )
						.getAppearance( "DRACULA_THRONE2" ) );
		enabled = false;
	}
}
