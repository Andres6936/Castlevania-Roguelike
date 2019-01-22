package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMystMorph2 extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_MYSTMORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MYSTMORPH2", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Turns into a energy cloud";
	}

	public String getID( )
	{
		return "ADV_MYSTMORPH2";
	}

	public String getName( )
	{
		return "Advanced Ethereal Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
