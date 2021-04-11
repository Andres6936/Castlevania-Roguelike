package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeEagle extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_INVOKEBIRD" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKEEAGLE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons an eagle soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKEEAGLE";
	}

	public String getName( )
	{
		return "Claws' Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
