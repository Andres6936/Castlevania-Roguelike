package co.castle.player.advancements.manbeast;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvRegen extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_SELFCONTROL" };

	public String[ ] requirements = new String[ ]
	{ "ADV_CLAWASSAULT" };

	public void advance( Player p )
	{
		p.setFlag( "HEALTH_REGENERATION", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Regenerates health at fixed intervals";
	}

	public String getID( )
	{
		return "ADV_REGEN";
	}

	public String getName( )
	{
		return "Regeneration";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
