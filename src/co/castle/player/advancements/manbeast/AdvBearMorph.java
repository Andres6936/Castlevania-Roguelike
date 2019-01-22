package co.castle.player.advancements.manbeast;

import co.castle.player.advancements.FlagAdvancement;

public class AdvBearMorph extends FlagAdvancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_POWERBLOW" };

	public String getDescription( )
	{
		return "Turns into a huge werebear";
	}

	public String getFlagName( )
	{
		return "SKILL_BEARMORPH";
	}

	public String getID( )
	{
		return "ADV_BEARMORPH";
	}

	public String getName( )
	{
		return "Ursidae Morph";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
