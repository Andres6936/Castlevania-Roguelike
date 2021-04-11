package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulWind extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ITEMBREAK" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULWIND", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Stops Time";
	}

	public String getID( )
	{
		return "ADV_SOULWIND";
	}

	public String getName( )
	{
		return "Soul Wind";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
