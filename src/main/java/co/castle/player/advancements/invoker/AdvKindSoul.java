package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvKindSoul extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_DRAGONFIRE", };

	public void advance( Player p )
	{
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Befriend Monster Souls";
	}

	public String getID( )
	{
		return "ADV_KIND_SOUL";
	}

	public String getName( )
	{
		return "Kind Soul";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
