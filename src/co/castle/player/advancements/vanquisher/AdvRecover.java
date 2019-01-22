package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvRecover extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_RECOVER", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Recovers hit points";
	}

	public String getID( )
	{
		return "ADV_RECOVER";
	}

	public String getName( )
	{
		return "Recover";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
