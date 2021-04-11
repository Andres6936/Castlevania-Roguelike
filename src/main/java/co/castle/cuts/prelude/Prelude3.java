package co.castle.cuts.prelude;

import co.castle.ai.monster.boss.DraculaAI;
import co.castle.cuts.Unleasher;
import co.castle.game.Game;
import co.castle.game.PlayerGenerator;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;

public class Prelude3 extends Unleasher
{

	public void unleash( Level level, Game game )
	{
		Monster dracula = level.getMonsterByID( "PRELUDE_DRACULA" );
		if ( dracula.getHits( ) > dracula.getMaxHits( ) / 2 )
			return;

		level.addMessage( "Dracula invokes a deadly beam of chaos energy!!!" );
		level.addEffect( EffectFactory.getSingleton( ).createLocatedEffect(
				level.getPlayer( ).getPosition( ), "SFX_KILL_CHRIS" ) );

		Player p = level.getPlayer( );
		game.setPlayer( PlayerGenerator.thus.createSpecialPlayer( "SOLIEYU" ) );
		level.removeActor( p );
		p.die( );
		level.getPlayer( ).setPosition( level.getExitFor( "_START" ) );
		level.getPlayer( ).see( );
		UserInterface.getUI( ).refresh( );
		level.setFlag( "CHRIS_DEAD", true );
		( (DraculaAI) level.getMonsterByID( "PRELUDE_DRACULA" ).getSelector( ) ).reset( );
		dracula.setPosition( level.getExitFor( "#DRACPOS" ) );
		dracula.setVisible( true );
		enabled = false;
	}
}
