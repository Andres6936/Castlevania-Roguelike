package co.castle.player.advancements.manbeast;

import co.castle.player.Consts;
import co.castle.player.advancements.FlagAdvancement;

public class AdvCompleteControl extends FlagAdvancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{ "ADV_SELFCONTROL" };

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Complete control when morphing";
	}

	public String getFlagName( )
	{
		return Consts.F_COMPLETECONTROL;
	}

	public String getID( )
	{
		return "ADV_COMPLETECONTROL";
	}

	public String getName( )
	{
		return "Complete Control";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
