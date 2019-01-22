package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeTortoise extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_INVOKETURTLE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKETORTOISE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons a tortoise soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKETORTOISE";
	}

	public String getName( )
	{
		return "Adamant Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
