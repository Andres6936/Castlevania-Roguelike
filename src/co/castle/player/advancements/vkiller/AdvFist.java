package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvFist extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_CROSS", "ADV_STOPWATCH" };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_FIST", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of Sacred Fist mystic weapon";
	}

	public String getID( )
	{
		return "ADV_FIST";
	}

	public String getName( )
	{
		return "Sacred Fist";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
