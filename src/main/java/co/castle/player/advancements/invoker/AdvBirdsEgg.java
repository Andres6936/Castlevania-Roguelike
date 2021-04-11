package co.castle.player.advancements.invoker;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvBirdsEgg extends Advancement
{
	public String[ ] requirements = new String[ ]
	{

	};

	public void advance( Player p )
	{
		p.setFlag( "SKILL_BIRDSEGG", true );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Throw an ethereal exploding bird's egg";
	}

	public String getID( )
	{
		return "ADV_BIRDSEGG";
	}

	public String getName( )
	{
		return "Birds' Egg";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}

}
