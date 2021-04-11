package co.castle.player.advancements.manbeast;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvImpactBlow3 extends Advancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{ "ADV_POWERBLOW2" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_POWERBLOW3", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Replaces power strike for a devasting power crash";
	}

	public String getID( )
	{
		return "ADV_POWERBLOW3";
	}

	public String getName( )
	{
		return "Power Crash";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
