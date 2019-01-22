package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvFlamesShot extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_SOULSSTRIKE", "ADV_SHADETELEPORT" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_FLAMESSHOOT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Shoots three fireballs";
	}

	public String getID( )
	{
		return "ADV_FLAMESSHOOT";
	}

	public String getName( )
	{
		return "Flame's Shoot";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
