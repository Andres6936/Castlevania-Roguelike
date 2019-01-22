package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvHolyBible extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "MYSTIC_HOLY_BIBLE", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Enables use of Holy Bible mystic weapon";
	}

	public String getID( )
	{
		return "ADV_HOLY_BIBLE";
	}

	public String getName( )
	{
		return "Holy Bible";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
