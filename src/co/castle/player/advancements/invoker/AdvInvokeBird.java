package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeBird extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_MANIPULATE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKEBIRD", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons a bird soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKEBIRD";
	}

	public String getName( )
	{
		return "Feather's Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
