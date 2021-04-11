package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import sz.util.Position;
import sz.util.Util;

public class Teleport extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		Monster mon = (Monster) performer;
		do
		{
			Position dest = Position.add( aLevel.getPlayer( ).getPosition( ),
					new Position( Util.rand( -2, 2 ), Util.rand( -2, 2 ) ) );
			if ( !aLevel.isWalkable( dest ) )
				continue;
			mon.setPosition( dest );
			break;
		}
		while ( true );
	}

	public String getID( )
	{
		return "TELEPORT";
	}
}