package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeTurtle extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_MANIPULATE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKETURTLE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons a turtle soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKETURTLE";
	}

	public String getName( )
	{
		return "Shell Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
