package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvBackflip extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_AIR_DASH", "ADV_WARP_DASH" };

	public String[ ] requirements = new String[ ]
	{ "ADV_SLIDEKICK" };

	public void advance( Player p )
	{
		p.setFlag( "PASIVE_BACKFLIP", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "40% chance of double evade check";
	}

	public String getID( )
	{
		return "ADV_BACKFLIP";
	}

	public String getName( )
	{
		return "Backflip";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
