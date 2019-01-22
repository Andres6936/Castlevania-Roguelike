package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvShadeTeleport extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_DARKMETAMORPHOSIS", "ADV_SUMMONSOUL" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SHADETELEPORT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Roll in your cape and teleport in a straight line";
	}

	public String getID( )
	{
		return "ADV_SHADETELEPORT";
	}

	public String getName( )
	{
		return "Shade Teleport";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
