package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvConfidence extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_DRAGONFIRE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_CONFIDENCE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Raises Manipulation chance by 20%";
	}

	public String getID( )
	{
		return "ADV_CONFIDENCE";
	}

	public String getName( )
	{
		return "Confidence";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
