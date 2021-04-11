package co.castle.player.advancements.stats;

import co.castle.player.Player;
import co.castle.player.advancements.Advancement;

public class AdvUranus extends Advancement
{
	public void advance( Player p )
	{
		p.setCastCost( p.getCastCost( ) - getIncrement( p ) );
		p.addLastIncrement( Player.INCREMENT_INVOKATION, getIncrement( p ) );
	}

	public String getDescription( )
	{
		return "Makes it easier to use soul power, increasing Invokation skill";
	}

	public String getID( )
	{
		return "ADV_MARS";
	}

	public String getName( )
	{
		return "Uranus Spirit";
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
