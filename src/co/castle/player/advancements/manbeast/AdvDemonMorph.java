package co.castle.player.advancements.manbeast;

import co.castle.player.advancements.FlagAdvancement;

public class AdvDemonMorph extends FlagAdvancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_BEASTMORPH" };

	public String getDescription( )
	{
		return "Turns into a strong demon";
	}

	public String getFlagName( )
	{
		return "SKILL_DEMONMORPH";
	}

	public String getID( )
	{
		return "ADV_DEMONMORPH";
	}

	public String getName( )
	{
		return "Demonic Morph";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
