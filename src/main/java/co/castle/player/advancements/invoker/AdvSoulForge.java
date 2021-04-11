package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulForge extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_KIND_SOUL" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULFORGE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Raises Tame chance by 20%";
	}

	public String getID( )
	{
		return "ADV_SOULFORGE";
	}

	public String getName( )
	{
		return "Soul Forge";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
