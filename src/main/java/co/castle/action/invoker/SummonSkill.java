package co.castle.action.invoker;

import co.castle.action.HeartAction;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.monster.MonsterFactory;
import co.castle.player.Consts;
import sz.util.Position;
import sz.util.Util;

public abstract class SummonSkill extends HeartAction
{
	public void execute( )
	{
		super.execute( );
		Level aLevel = performer.getLevel( );
		Position randPos = null;
		if ( Util.chance( 100 ) )
		{ // TODO: This is related to soul
			int count = 20;
			out: while ( count > 0 )
			{
				randPos = Position.add( performer.getPosition( ),
						new Position( Util.rand( -1, 1 ), Util.rand( -1, 1 ), 0 ) );
				if ( aLevel.isWalkable( randPos ) )
				{
					break out;
				}
				count--;
			}
			if ( count > 0 )
			{
				Monster m = MonsterFactory.getFactory( ).buildMonster( getMonsterID( ) );
				aLevel.addMessage(
						"A " + m.getDescription( ) + " rises from the floor!" );
				m.setCounter( Consts.C_MONSTER_CHARM, 9999999 );
				m.setPosition( randPos );
				m.increaseHits( getHitBonus( ) );
				aLevel.addMonster( m );
			}
		}
		else
		{
			aLevel.addMessage( "Nothing happens." );
		}

	}

	public int getHitBonus( )
	{
		return 0;
	}

	public abstract String getMonsterID( );
}
