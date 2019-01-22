package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvWolfMorph2 extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_WOLFMORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_WOLFMORPH2", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Turns into a quick and fierce lupine monster";
	}

	public String getID( )
	{
		return "ADV_WOLFMORPH2";
	}

	public String getName( )
	{
		return "Advanced Lupine Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
