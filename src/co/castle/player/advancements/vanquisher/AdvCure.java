package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvCure extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_RECOVER" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_CURE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Removes Poison";
	}

	public String getID( )
	{
		return "ADV_CURE";
	}

	public String getName( )
	{
		return "Cure";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
