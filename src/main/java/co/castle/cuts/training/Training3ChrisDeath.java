package co.castle.cuts.training;

import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.npc.NPC;
import co.castle.player.Player;
import co.castle.ui.Display;

public class Training3ChrisDeath extends Unleasher
{
	public void unleash( Level level, Game game )
	{
		NPC chris = level.getNPCByID( "CHRISTRAIN" );
		if ( chris == null || chris.isDead( ) )
		{
			Display.thus.showScreen(
					"What kind of Belmont are you? in a state of madness you betrayed your father and killed him, just to steal the vampire killer whip... Your conscience will haunt you for eternity" );
			level.getPlayer( ).setPlayerClass( Player.CLASS_VAMPIREKILLER );
			level.getPlayer( ).setWeapon( Player.VAMPIRE_WHIP );
			level.getPlayer( ).setFlag( "ONLY_VK", true );
			enabled = false;
		}
	}
}
