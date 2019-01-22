package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvEnchant extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_ENCHANT", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Covers weapons in a blue aura";
	}

	public String getID( )
	{
		return "ADV_ENCHANT";
	}

	public String getName( )
	{
		return "Weapon Enchantment";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
