package co.castle.player.advancements;

import co.castle.player.Player;

public class AdvDodge extends Advancement
{
	public String[ ] bans = new String[ ]
	{ };

	public String[ ] requirements = new String[ ]
	{

	};

	public void advance( Player p )
	{
		p.setFlag( "PASIVE_DODGE", true );
		p.setFlag( getID( ), true );
	}

	public String[ ] getBans( )
	{
		return bans;
	}

	public String getDescription( )
	{
		return "Adds 10% to chance of evading attack";
	}

	public String getID( )
	{
		return "ADV_DODGE";
	}

	public String getName( )
	{
		return "Dodge";
	}

	public String[ ] getRequirements( )
	{
		return requirements;
	}
}
