package co.castle.cuts.villa;

import co.castle.ai.npc.VillagerAI;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.Display;
import sz.util.Position;

public class Villa1 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster maiden = level.getNPCByID( "MAIDEN" );
		if ( maiden == null )
			return;
		int distance = Position.distance( maiden.getPosition( ),
				game.getPlayer( ).getPosition( ) );
		if ( ( (VillagerAI) maiden.getSelector( ) ).isHostile( ) )
		{
			enabled = false;
			return;
		}
		if ( distance > 2 )
			return;
		Display.thus.showChat( "MAIDEN1", game );
		level.getPlayer( ).addKeys( 1 );
		// level.removeMonster(level.getNPCByID("MAIDEN"));
		enabled = false;
	}

}
