package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMystMorph extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_BATMORPH" };

	public String[ ] requirements = new String[ ]
	{ "ADV_MORPH" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MYSTMORPH", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Turns into thin myst";
	}

	public String getID( )
	{
		return "ADV_MYSTMORPH";
	}

	public String getName( )
	{
		return "Ethereal Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
