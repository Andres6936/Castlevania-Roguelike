package co.castle.player.advancements.vkiller;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvItemBreak extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_HOLY_WATER", "ADV_HOLY_BIBLE" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_ITEM_BREAK", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Allows execution of item break mystic attacks";
	}

	public String getID( )
	{
		return "ADV_ITEMBREAK";
	}

	public String getName( )
	{
		return "Item Break";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
