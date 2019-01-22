package co.castle.player.advancements.manbeast;

import co.castle.player.advancements.FlagAdvancement;

public class AdvPowerBlow2 extends FlagAdvancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_POWERBLOW" };

	public String getDescription( )
	{
		return "Replaces Power Blow for a stronger Strike";
	}

	public String getFlagName( )
	{
		return "SKILL_POWERBLOW2";
	}

	public String getID( )
	{
		return "ADV_POWERBLOW2";
	}

	public String getName( )
	{
		return "Power Strike";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
