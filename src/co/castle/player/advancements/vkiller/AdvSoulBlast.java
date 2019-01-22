package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulBlast extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_SOULICE", "ADV_SOULSAINT" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULBLAST", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Invokes a deadly blast of holy energy";
	}

	public String getID( )
	{
		return "ADV_SOULBLAST";
	}

	public String getName( )
	{
		return "Soul Blast";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
