package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import sz.util.Position;
import sz.util.Util;

public class UnleashSickles extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Death invokes tiny flying sickles!" );
		int sickles = Util.rand( 5, 10 );
		for ( int i = 0; i < sickles; i++ )
		{
			int xvar = Util.rand( -8, 8 );
			int yvar = Util.rand( -8, 8 );
			Position destinationPoint = Position.add( performer.getPosition( ),
					new Position( xvar, yvar ) );
			Monster sickle = MonsterFactory.getFactory( ).buildMonster( "SICKLE" );
			sickle.setPosition( destinationPoint );
			aLevel.addMonster( sickle );
		}
	}

	public int getCost( )
	{
		return 40;
	}

	public String getID( )
	{
		return "UNLEASH_SICKLES";
	}
}