package co.castle.player.advancements;

import java.io.Serializable;

import co.castle.player.Player;

public abstract class Advancement implements Serializable
{
	public String[ ] NO_REQUIREMENTS = new String[ ]
	{ };

	private String[ ] bans = new String[ 0 ];

	public abstract void advance( Player p );

	public String[ ] getBans( )
	{
		return bans;
	}

	public abstract String getDescription( );

	public abstract String getID( );

	public abstract String getName( );

	public abstract String[ ] getRequirements( );
}
