package co.castle.action.monster;

import co.castle.action.Action;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;
import sz.util.Debug;
import sz.util.Position;

public class Swim extends Action
{
	public void execute( )
	{
		Debug.doAssert( performer instanceof Monster, "The player tried to Swim..." );
		Monster aMonster = (Monster) performer;
		Position var = directionToVariation( targetDirection );
		Position destinationPoint = Position.add( performer.getPosition( ), var );
		Level aLevel = performer.getLevel( );
		Cell destinationCell = aLevel.getMapCell( destinationPoint );
		Monster destinationMonster = aLevel.getMonsterAt( destinationPoint );
		if ( destinationCell != null && !destinationCell.isSolid( ) )
		{
			if ( destinationCell.isShallowWater( ) || destinationCell.isWater( ) )
			{
				if ( destinationMonster == null )
					performer.setPosition( destinationPoint );
			}
			else
			{
				if ( destinationMonster == null )
				{
					aLevel.addMessage( "A " + aMonster.getDescription( )
							+ " jumps out of the water!", destinationPoint );
					performer.setPosition( destinationPoint );
				}
			}
		}

		if ( aMonster.getAttack( ) > 0
				&& aLevel.getPlayer( ).getPosition( ).equals( destinationPoint )
				&& aLevel.getPlayer( ).getStandingHeight( ) == aMonster
						.getStandingHeight( ) )
		{
			// Damage the poor player and bounce him back
			StringBuffer buff = new StringBuffer(
					"The " + aMonster.getDescription( ) + " hits you with his jump!" );
			if ( aLevel.getPlayer( ).damage( buff, aMonster,
					new Damage( aMonster.getAttack( ), false ) ) )
				aLevel.getPlayer( ).bounceBack( var, 1 );
			aLevel.addMessage( buff.toString( ) );
		}
	}

	public String getID( )
	{
		return "Swim";
	}

	public boolean needsDirection( )
	{
		return true;
	}

}