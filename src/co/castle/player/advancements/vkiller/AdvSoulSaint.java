package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulSaint extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_SOULWIND", "ADV_SOULFLAME" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULSAINT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Projects energy from the mystic whip";
	}

	public String getID( )
	{
		return "ADV_SOULSAINT";
	}

	public String getName( )
	{
		return "Soul Saint";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
