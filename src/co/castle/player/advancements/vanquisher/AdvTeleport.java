package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvTeleport extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_RECOVER" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_TELEPORT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Magically self-transport";
	}

	public String getID( )
	{
		return "ADV_TELEPORT";
	}

	public String getName( )
	{
		return "Teleport";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
