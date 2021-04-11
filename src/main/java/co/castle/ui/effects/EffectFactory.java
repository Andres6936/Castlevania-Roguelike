package co.castle.ui.effects;

import sz.util.Position;

public abstract class EffectFactory
{
	private static EffectFactory singleton;

	public static EffectFactory getSingleton( )
	{
		return singleton;
	}

	public static void setSingleton( EffectFactory ef )
	{
		singleton = ef;
	}

	public abstract Effect createDirectedEffect(	Position start, Position end, String ID,
													int length );

	public abstract Effect createDirectionalEffect(	Position start, int direction,
													int depth, String ID );

	public abstract Effect createLocatedEffect( Position location, String ID );

}
