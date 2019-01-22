package co.castle.cuts.ingame.vindelith1;

import co.castle.action.Action;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.level.Level;
import co.castle.npc.NPC;
import co.castle.ui.Display;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;

public class Vindelith2 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		// if (level.getFlag("VINDELITHMEETING")&&
		// level.getCounter("COUNTBACK_VINDELITHMEETING").isOver()){
		Display.thus.showChat( "VINDELITH1", game );
		NPC claw = level.getNPCByID( "UNIDED_CLAW" );
		NPC vind = level.getNPCByID( "UNIDED_VINDELITH" );
		level.addEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
				claw.getPosition( ), Action.RIGHT, 1, "SFX_WP_BASELARD" ) );
		level.addEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
				vind.getPosition( ), Action.LEFT, 2, "SFX_WP_BASELARD" ) );
		level.addEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
				claw.getPosition( ), Action.RIGHT, 1, "SFX_WP_BASELARD" ) );
		level.addEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
				vind.getPosition( ), Action.LEFT, 2, "SFX_WP_BASELARD" ) );
		level.addEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
				claw.getPosition( ), Action.RIGHT, 1, "SFX_WP_BASELARD" ) );
		Display.thus.showChat( "VINDELITH2", game );
		level.removeMonster( level.getNPCByID( "UNIDED_VINDELITH" ) );
		level.removeMonster( claw );
		UserInterface.getUI( ).refresh( );
		enabled = false;
		// }
	}

}
