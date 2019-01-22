package co.castle.level;

import co.castle.action.Action;
import co.castle.ui.effects.EffectFactory;

public class PreemergeEffects extends Action
{
	private static PreemergeEffects singleton = new PreemergeEffects( );

	public static PreemergeEffects getAction( )
	{
		return singleton;
	}

	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		Emerger em = (Emerger) performer;
		// aLevel.addMessage("You see something crawling out of the soil!",
		// em.getPoint());
		// aLevel.addEffect(new StaticAnimEffect(em.getPoint(), "^", Appearance.BROWN));

		drawEffect( EffectFactory.getSingleton( ).createLocatedEffect( em.getPoint( ),
				"SFX_MONSTER_CRAWLING" ) );
	}

	public String getID( )
	{
		return "Preemerge";
	}
}