package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.effects.EffectFactory;

public class Vanish extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Dracula disappears!" );
		Monster mon = (Monster) performer;
		mon.setVisible( false );
		// drawEffect(new SplashEffect(performer.getPosition(), ".oO",
		// Appearance.WHITE));
		drawEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( performer.getPosition( ), "SFX_VANISH" ) );
		mon.setPosition( 0, 0, 0 );
	}

	public String getID( )
	{
		return "VANISH";
	}
}