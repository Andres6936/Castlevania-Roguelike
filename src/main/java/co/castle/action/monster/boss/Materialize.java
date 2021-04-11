package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;
import sz.util.Util;

public class Materialize extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Dracula materializes!" );
		Monster mon = (Monster) performer;
		mon.setVisible( true );
		Position var = new Position( Util.rand( -5, 5 ), Util.rand( -5, 5 ) );
		Position pum = Position.add( aLevel.getPlayer( ).getPosition( ), var );
		mon.setPosition( pum );
		// drawEffect(new SplashEffect(pum, "Oo.", Appearance.WHITE));
		drawEffect( EffectFactory.getSingleton( ).createLocatedEffect( pum,
				"SFX_MATERIALIZE" ) );
	}

	public String getID( )
	{
		return "MATERIALIZE";
	}
}