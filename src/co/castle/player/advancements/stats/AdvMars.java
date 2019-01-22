package co.castle.player.advancements.stats;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvMars extends Advancement
{
	public void advance( Player p )
	{
		p.setAttack( p.getAttack( ) + getIncrement( p ) );
		p.addLastIncrement( Player.INCREMENT_ATTACK, getIncrement( p ) );
	}

	public String getDescription( )
	{
		return "Increases Attack";
	}

	public String getID( )
	{
		return "ADV_MARS";
	}

	public String getName( )
	{
		return "Mars Spirit";
	}

	public String[ ] getRequirements( )
	{
		return NO_REQUIREMENTS;
	}

	private int getIncrement( Player p )
	{
		int increment = 1;
		if ( p.getPlayerLevel( ) > 40 )
			increment = 5;
		else if ( p.getPlayerLevel( ) > 30 )
			increment = 2;
		else if ( p.getPlayerLevel( ) > 20 )
			increment = 2;
		else if ( p.getPlayerLevel( ) > 10 )
			increment = 1;
		return increment;
	}
}
