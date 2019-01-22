package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvDarkMetamorphosis extends Advancement
{
	public String[ ] requirements = new String[ ]
	{

	};

	public void advance( Player p )
	{
		p.setFlag( "SKILL_DARKMETAMORPHOSIS", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Recovers health from enemies blood for a while";
	}

	public String getID( )
	{
		return "ADV_DARKMETAMORPHOSIS";
	}

	public String getName( )
	{
		return "Dark Metamorphosis";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
