package co.castle.player.advancements.manbeast;

import co.castle.player.advancements.FlagAdvancement;

public class AdvBeastMorph extends FlagAdvancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_BEARMORPH" };

	public String getDescription( )
	{
		return "Turns into a huge beast";
	}

	public String getFlagName( )
	{
		return "SKILL_BEASTMORPH";
	}

	public String getID( )
	{
		return "ADV_BEASTMORPH";
	}

	public String getName( )
	{
		return "Bestial Morph";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
