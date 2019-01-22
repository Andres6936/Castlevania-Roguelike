package co.castle.level;

import co.castle.action.Action;
import co.castle.ui.effects.EffectFactory;

public class SFX extends Action
{
	private int effect;

	private static SFX singleton = new SFX( );

	private final static int THUNDER = 1;

	public static SFX getThunder( )
	{
		singleton.setEffect( THUNDER );
		return singleton;
	}
	public void execute( )
	{
		Level level = performer.getLevel( );
		// level.addEffect(new
		// FlashEffect(performer.getLevel().getPlayer().getPosition(),
		// Appearance.WHITE));
		drawEffect( EffectFactory.getSingleton( ).createLocatedEffect(
				performer.getLevel( ).getPlayer( ).getPosition( ),
				"SFX_THUNDER_FLASH" ) );
	}

	public String getID( )
	{
		return "SFX";
	}

	public void setEffect( int pEffect )
	{
		effect = pEffect;
	}
}