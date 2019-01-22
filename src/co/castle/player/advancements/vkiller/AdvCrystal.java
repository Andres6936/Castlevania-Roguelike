package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvCrystal extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_CROSS", "ADV_STOPWATCH" };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_CRYSTAL", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of Blast Crystal mystic weapon";
	}

	public String getID( )
	{
		return "ADV_CRYSTAL";
	}

	public String getName( )
	{
		return "Blast Crystal";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
