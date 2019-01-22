package co.castle.level;

import co.castle.action.Action;
import co.castle.monster.Monster;
import sz.util.Position;

public class EmergeMonster extends Action
{
	private static EmergeMonster singleton = new EmergeMonster( );

	public static EmergeMonster getAction( )
	{
		return singleton;
	}

	public void execute( )
	{
		Level level = performer.getLevel( );
		Emerger em = (Emerger) performer;
		Monster monster = em.getMonster( );
		monster.setPosition( new Position( em.getPoint( ) ) );
		level.addMonster( monster );
		if ( em.getMound( ) != null )
			level.destroyFeature( em.getMound( ) );
	}

	public String getID( )
	{
		return "EmergeMonster";
	}
}