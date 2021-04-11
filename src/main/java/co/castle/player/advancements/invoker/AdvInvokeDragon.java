package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvInvokeDragon extends Advancement
{
	public String[ ] bans = new String[ ]
	{ "ADV_TAME" };

	public String[ ] requirements = new String[ ]
	{ "ADV_INVOKEEAGLE", "ADV_INVOKETORTOISE", "ADV_INVOKETIGER", };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_INVOKEDRAGON", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Summons an ancient dragon soul familiar";
	}

	public String getID( )
	{
		return "ADV_INVOKEDRAGON";
	}

	public String getName( )
	{
		return "Ancient Oath";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
