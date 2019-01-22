package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSlideKick extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_HOLY_WATER", "ADV_HOLY_BIBLE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SLIDEKICK", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Slides 3 spaces ahead kicking any enemy in the path";
	}

	public String getID( )
	{
		return "ADV_SLIDEKICK";
	}

	public String getName( )
	{
		return "Slide Kick";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
