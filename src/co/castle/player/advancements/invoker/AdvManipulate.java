package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvManipulate extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_BIRDSEGG" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MANIPULATE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Manipulates a monster's soul to your side";
	}

	public String getID( )
	{
		return "ADV_MANIPULATE";
	}

	public String getName( )
	{
		return "Manipulate";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
