package co.castle.player.advancements.stats;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvJupiter extends Advancement
{
	public void advance( Player p )
	{
		p.setHitsMax( p.getHitsMax( ) + getIncrement( p ) );
		p.addLastIncrement( Player.INCREMENT_HITS, getIncrement( p ) );
	}

	public String getDescription( )
	{
		return "Increases maximum resistance to damage (hits)";
	}

	public String getID( )
	{
		return "ADV_HITPOINT";
	}

	public String getName( )
	{
		return "Jupiter Spirit";
	}

	public String[ ] getRequirements( )
	{
		return NO_REQUIREMENTS;
	}

	private int getIncrement( Player p )
	{
		int increment = 3;
		if ( p.getPlayerLevel( ) > 40 )
			increment = 15;
		else if ( p.getPlayerLevel( ) > 30 )
			increment = 12;
		else if ( p.getPlayerLevel( ) > 20 )
			increment = 8;
		else if ( p.getPlayerLevel( ) > 10 )
			increment = 5;
		return increment;

	}
}
