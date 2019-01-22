package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvBatMorph extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_MYSTMORPH" };

	public String[ ] requirements = new String[ ]
	{ "ADV_MORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_BATMORPH", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Turns into a flying bat";
	}

	public String getID( )
	{
		return "ADV_BATMORPH";
	}

	public String getName( )
	{
		return "Chiropteran Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
