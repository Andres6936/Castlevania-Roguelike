package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvAirDash extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_WARP_DASH", "ADV_BACKFLIP" };

	public String[ ] requirements = new String[ ]
	{ "ADV_SLIDEKICK" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_AIR_DASH", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Dashes 3 spaces ahead, knocks back large enemies.";
	}

	public String getID( )
	{
		return "ADV_AIR_DASH";
	}

	public String getName( )
	{
		return "Air Dash";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
