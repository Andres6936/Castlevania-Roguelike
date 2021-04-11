package co.castle.player.advancements.manbeast;

import co.castle.player.Consts;
import co.castle.player.advancements.FlagAdvancement;

public class AdvSelfControl extends FlagAdvancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_REGEN" };

	public String[ ] requirements = new String[ ]
	{ "ADV_CLAWASSAULT" };

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Halves risk of going out of control when morphing";
	}

	public String getFlagName( )
	{
		return Consts.F_SELFCONTROL;
	}

	public String getID( )
	{
		return "ADV_SELFCONTROL";
	}

	public String getName( )
	{
		return "Self Control";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
