package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvStopwatch extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_HOLY_WATER", "ADV_HOLY_BIBLE" };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_STOPWATCH", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of StopWatch mystic weapon";
	}

	public String getID( )
	{
		return "ADV_STOPWATCH";
	}

	public String getName( )
	{
		return "Stopwatch";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
