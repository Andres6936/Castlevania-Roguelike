package co.castle.cuts.entrance;

import co.castle.ai.npc.VillagerAI;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import sz.util.Position;

public class Entrance1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster clara = level.getNPCByID( "UNIDED_CLAW" );
		int distance = Position.distance( clara.getPosition( ),
				game.getPlayer( ).getPosition( ) );
		if ( ( (VillagerAI) clara.getSelector( ) ).isHostile( ) )
		{
			enabled = false;
			return;
		}
		if ( distance > 2 )
			return;
		Display.thus.showChat( "CLARA1", game );
		level.removeMonster( clara );
		UserInterface.getUI( ).refresh( );
		enabled = false;
	}

}
