package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvLight extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_TELEPORT", "ADV_CURE", };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_LIGHT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Covers weapons in a blue aura";
	}

	public String getID( )
	{
		return "ADV_LIGHT";
	}

	public String getName( )
	{
		return "DarkLight";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
