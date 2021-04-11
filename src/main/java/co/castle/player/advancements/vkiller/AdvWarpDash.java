package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvWarpDash extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_AIR_DASH", "ADV_BACKFLIP" };

	public String[ ] requirements = new String[ ]
	{ "ADV_SLIDEKICK" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_WARP_DASH", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Dashes 4 spaces ahead passing through any enemies in the way";
	}

	public String getID( )
	{
		return "ADV_WARP_DASH";
	}

	public String getName( )
	{
		return "Warp Dash";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
