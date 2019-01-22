package co.castle.player.advancements.manbeast;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvImpactBlow extends Advancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_POWERBLOW", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Builds up energy to perform a powerful blow";
	}

	public String getID( )
	{
		return "ADV_POWERBLOW";
	}

	public String getName( )
	{
		return "Power Blow";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
