package co.castle.player.advancements;

import co.castle.player.Player;

public abstract class FlagAdvancement extends Advancement
{
	public final void advance( Player p )
	{
		p.setFlag( getFlagName( ), true );
		p.setFlag( getID( ), true );
	}

	public abstract String getFlagName( );
}
