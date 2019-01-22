package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMindlock extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ENCHANT" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MINDLOCK", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Paralizes an enemy for a short time";
	}

	public String getID( )
	{
		return "ADV_MINDLOCK";
	}

	public String getName( )
	{
		return "Mindlock";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
