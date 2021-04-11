package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvBatMorph2 extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_BATMORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_BATMORPH2", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Turns into a giant vampire bat";
	}

	public String getID( )
	{
		return "ADV_BATMORPH2";
	}

	public String getName( )
	{
		return "Advanced Chiropteran Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
