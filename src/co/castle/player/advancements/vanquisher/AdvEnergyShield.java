package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvEnergyShield extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ENCHANT" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_ENERGYSHIELD", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Creates a shield that reduces harm caused by enemies";
	}

	public String getID( )
	{
		return "ADV_ENERGYSHIELD";
	}

	public String getName( )
	{
		return "Energy Shield";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
