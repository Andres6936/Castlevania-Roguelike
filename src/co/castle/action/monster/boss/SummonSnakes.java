package co.castle.action.monster.boss;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import sz.util.Position;
import sz.util.Util;

public class SummonSnakes extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		aLevel.addMessage( "Medusa throws some snakes from her head!" );
		int sickles = Util.rand( 5, 10 );
		for ( int i = 0; i < sickles; i++ )
		{
			int xvar = Util.rand( -8, 8 );
			int yvar = Util.rand( -8, 8 );
			Position destinationPoint = Position.add( performer.getPosition( ),
					new Position( xvar, yvar ) );
			if ( !aLevel.isWalkable( destinationPoint ) )
			{
				i--;
				continue;
			}

			Monster snake = MonsterFactory.getFactory( ).buildMonster( "SNAKE" );
			snake.setPosition( destinationPoint );
			aLevel.addMonster( snake );
		}
	}

	public int getCost( )
	{
		return 30;
	}

	public String getID( )
	{
		return "SUMMON_SNAKES";
	}
}