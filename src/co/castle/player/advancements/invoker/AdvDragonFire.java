package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvDragonFire extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_CATSOUL" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_DRAGONFIRE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Invokes ethereal dragon fire";
	}

	public String getID( )
	{
		return "ADV_DRAGONFIRE";
	}

	public String getName( )
	{
		return "DragonFire";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
