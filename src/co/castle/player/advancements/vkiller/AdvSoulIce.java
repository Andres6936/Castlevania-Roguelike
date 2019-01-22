package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulIce extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_SOULWIND", "ADV_SOULFLAME" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULICE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Recovers Health";
	}

	public String getID( )
	{
		return "ADV_SOULICE";
	}

	public String getName( )
	{
		return "Soul Ice";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
