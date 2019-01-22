package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvWolfMorph extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_MORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_WOLFMORPH", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Turns into a quick and fierce wolf";
	}

	public String getID( )
	{
		return "ADV_WOLFMORPH";
	}

	public String getName( )
	{
		return "Lupine Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
