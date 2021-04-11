package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvLavaShot extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_FLAMESSHOOT" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_HELLFIRE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Shoots three big lava balls";
	}

	public String getID( )
	{
		return "ADV_HELLFIRE";
	}

	public String getName( )
	{
		return "Hellfire";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
