package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSummonSoul extends Advancement
{
	public String[ ] requirements = new String[ ]
	{

	};

	public void advance( Player p )
	{
		p.setFlag( "SKILL_SUMMONSOUL", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "A soul flies straight to the nearest enemy";
	}

	public String getID( )
	{
		return "ADV_SUMMONSOUL";
	}

	public String getName( )
	{
		return "Summon Soul";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
