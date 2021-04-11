package co.castle.cuts.dracula;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import sz.util.Position;

public class Dracul2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster dracula = level.getMonsterByID( "DRACULA" );
		if ( dracula != null )
			return;
		Display.thus.showChat( "DRACULA2", game );
		Player player = level.getPlayer( );
		player.informPlayerEvent( Player.EVT_GOTO_LEVEL, "VOID0" );
		player.see( );
		UserInterface.getUI( ).refresh( );
		player.setPosition( new Position( player.getLevel( ).getExitFor( "#START" ) ) );
		enabled = false;
	}
}