package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvCatSoul extends Advancement
{
	public String[ ] requirements = new String[ ]
	{

	};

	public void advance( Player p )
	{
		p.setFlag( "SKILL_CATSOUL", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Summons a quick running cat";
	}

	public String getID( )
	{
		return "ADV_CATSOUL";
	}

	public String getName( )
	{
		return "Cat Soul";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
