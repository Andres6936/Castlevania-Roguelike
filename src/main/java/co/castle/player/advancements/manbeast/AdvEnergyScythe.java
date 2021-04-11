package co.castle.player.advancements.manbeast;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvEnergyScythe extends Advancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_ENERGYSCYTHE", true );
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
		return "ADV_ENERGYSCHYTE";
	}

	public String getName( )
	{
		return "Energy Scythe";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
