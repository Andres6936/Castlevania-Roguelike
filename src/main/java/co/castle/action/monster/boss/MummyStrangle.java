package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;

public class MummyStrangle extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		StringBuffer buff = new StringBuffer( "Akmodan strangles you!" );
		aLevel.getPlayer( ).damage( buff, (Monster) performer, new Damage( 6, false ) );
		aLevel.addMessage( buff.toString( ) );
	}

	public String getID( )
	{
		return "MUMMY_STRANGLE";
	}
}