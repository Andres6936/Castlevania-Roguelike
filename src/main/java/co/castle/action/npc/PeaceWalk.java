package co.castle.action.npc;

import co.castle.action.Action;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import sz.util.Position;

public class PeaceWalk extends Action
{
	public void execute( )
	{

		Position var = directionToVariation( targetDirection );
		Position destinationPoint = Position.add( performer.getPosition( ), var );
		Level aLevel = performer.getLevel( );
		if ( !aLevel.isValidCoordinate( destinationPoint ) )
			return;
		Cell destinationCell = aLevel.getMapCell( destinationPoint );
		Cell currentCell = aLevel.getMapCell( performer.getPosition( ) );
		Monster destinationMonster = aLevel.getMonsterAt( destinationPoint );
		// SmartFeature standing = aLevel.getSmartFeature(performer.getPosition());
		if ( destinationCell != null )
			if ( !destinationCell.isSolid( ) )
				if ( destinationMonster == null )
					if ( currentCell == null
							|| destinationCell.getHeight( ) == currentCell.getHeight( ) )
						if ( !destinationCell.isWater( )
								&& !destinationCell.isShallowWater( ) )
							if ( !aLevel.getPlayer( ).getPosition( )
									.equals( destinationPoint ) )
								if ( destinationCell.isEthereal( ) )
									performer.setPosition(
											aLevel.getDeepPosition( destinationPoint ) );
								else
									performer.setPosition( destinationPoint );
	}

	public String getID( )
	{
		return "PeaceWalk";
	}

	public boolean needsDirection( )
	{
		return true;
	}

}