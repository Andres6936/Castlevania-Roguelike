package co.castle.player.advancements.manbeast;

import co.castle.player.advancements.FlagAdvancement;

public class AdvClawAssault extends FlagAdvancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ENERGYSCHYTE" };

	public String getDescription( )
	{
		return "Slashes through enemies";
	}

	public String getFlagName( )
	{
		return "SKILL_CLAWASSAULT";
	}

	public String getID( )
	{
		return "ADV_CLAWASSAULT";
	}

	public String getName( )
	{
		return "Claw Assault";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
