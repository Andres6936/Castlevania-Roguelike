package co.castle.player.advancements.stats;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvSaturn extends Advancement
{
	public void advance( Player p )
	{
		p.setWalkCost( p.getWalkCost( ) - getIncrement( p ) );
		p.addLastIncrement( Player.INCREMENT_SPEED, getIncrement( p ) );
	}

	public String getDescription( )
	{
		return "Moves quickly, increasing Movement skill";
	}

	public String getID( )
	{
		return "ADV_SATURN";
	}

	public String getName( )
	{
		return "Saturn Spirit";
	}

	public String[ ] getRequirements( )
	{
		return NO_REQUIREMENTS;
	}

	private int getIncrement( Player p )
	{
		int increment = 5;
		if ( p.getPlayerLevel( ) > 40 )
			increment = 15;
		else if ( p.getPlayerLevel( ) > 30 )
			increment = 10;
		else if ( p.getPlayerLevel( ) > 20 )
			increment = 7;
		else if ( p.getPlayerLevel( ) > 10 )
			increment = 5;
		return increment;
	}
}
