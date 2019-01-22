package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvCross extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_HOLY_WATER", "ADV_HOLY_BIBLE" };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_CROSS", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of Holy Cross mystic weapon";
	}

	public String getID( )
	{
		return "ADV_CROSS";
	}

	public String getName( )
	{
		return "Holy Cross";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
