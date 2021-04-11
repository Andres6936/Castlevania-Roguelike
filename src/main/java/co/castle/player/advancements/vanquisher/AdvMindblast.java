package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMindblast extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_ENERGYSHIELD", "ADV_MINDLOCK", };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MINDBLAST", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Attacks all surrounding enemies minds";
	}

	public String getID( )
	{
		return "ADV_MINDBLAST";
	}

	public String getName( )
	{
		return "Mind Blast";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
