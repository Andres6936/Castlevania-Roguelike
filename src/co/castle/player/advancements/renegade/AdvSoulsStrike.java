package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSoulsStrike extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_SUMMONSOUL", "ADV_DARKMETAMORPHOSIS" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SOULSSTRIKE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Three souls fly straight to the nearest enemies";
	}

	public String getID( )
	{
		return "ADV_SOULSSTRIKE";
	}

	public String getName( )
	{
		return "Soul's Strike";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
