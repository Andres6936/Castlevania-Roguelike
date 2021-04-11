package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvTame extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_INVOKEDRAGON" };

	public String[ ] requirements = new String[ ]
	{ "ADV_INVOKEEAGLE", "ADV_INVOKETORTOISE", "ADV_INVOKETIGER" };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_TAME", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Permanently turns a soul to your side";
	}

	public String getID( )
	{
		return "ADV_TAME";
	}

	public String getName( )
	{
		return "Tame Soul";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
