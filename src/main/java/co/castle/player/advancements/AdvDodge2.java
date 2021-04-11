package co.castle.player.advancements;

import co.castle.player.Player;

public class AdvDodge2 extends Advancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{ "ADV_DODGE" };

	public void advance( Player p )
	{
		p.setFlag( "PASIVE_DODGE2", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Adds another 10% to chance of evading attack";
	}

	public String getID( )
	{
		return "ADV_DODGE2";
	}

	public String getName( )
	{
		return "Mirror Dodge";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
