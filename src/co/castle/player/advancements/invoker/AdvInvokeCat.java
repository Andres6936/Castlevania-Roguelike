package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeCat extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_INVOKEBIRD", "ADV_INVOKETURTLE", };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKECAT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons a cat soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKECAT";
	}

	public String getName( )
	{
		return "Fang's Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
