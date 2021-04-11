package co.castle.cuts;

import java.io.Serializable;

import co.castle.game.Game;
import co.castle.level.Level;

public abstract class Unleasher implements Serializable
{
	protected boolean enabled = true;

	public void disable( )
	{
		enabled = false;
	}

	public boolean enabled( )
	{
		return enabled;
	}

	public abstract void unleash( Level level, Game game );
	/* This must check condition first */

}
