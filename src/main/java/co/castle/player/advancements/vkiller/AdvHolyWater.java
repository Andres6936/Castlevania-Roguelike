package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvHolyWater extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_HOLY_WATER", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of Holy Water mystic weapon";
	}

	public String getID( )
	{
		return "ADV_HOLY_WATER";
	}

	public String getName( )
	{
		return "Holy Water";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
