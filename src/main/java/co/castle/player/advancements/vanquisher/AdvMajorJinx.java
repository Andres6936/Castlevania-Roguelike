package co.castle.player.advancements.vanquisher;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMajorJinx extends Advancement
{
	public String[ ] requirements = new String[ ]
	{ "ADV_LIGHT", "ADV_MINDBLAST", };

	public void advance( Player p )
	{
		p.setFlag( "SKILL_MAJORJINX", true );
		p.setFlag( "SKILL_RECOVER", false );
		p.setFlag( getID( ), true );
	}

	public String getDescription( )
	{
		return "Attacks all surrounding enemies minds";
	}

	public String getID( )
	{
		return "ADV_MAJORJINX";
	}

	public String getName( )
	{
		return "Major Jinx";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
