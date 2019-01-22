package co.castle.player.advancements.renegade;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMorph extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ };

	public void advance( Player p )
	{
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Allows access to morphing skills";
	}

	public String getID( )
	{
		return "ADV_MORPH";
	}

	public String getName( )
	{
		return "Morph";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
