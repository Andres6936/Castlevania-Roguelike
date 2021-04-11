package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulFlame extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ITEMBREAK" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULFLAME", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Burns monsters around you";
	}

	public String getID( )
	{
		return "ADV_SOULFLAME";
	}

	public String getName( )
	{
		return "Soul Flame";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
