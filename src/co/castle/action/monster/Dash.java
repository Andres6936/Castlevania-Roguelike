package co.castle.action.monster;

import co.castle.action.Action;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;
import co.castle.player.Player;
import sz.util.Position;

public class Dash extends Action
{
	public void execute( )
	{
		Monster aMonster = (Monster) performer;
		targetDirection = aMonster.starePlayer( );
		Position var = directionToVariation( targetDirection );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		StringBuffer message = new StringBuffer(
				"The " + aMonster.getDescription( ) + " dives to you!" );
		// Cell currentCell = aLevel.getMapCell(performer.getPosition());
		Position destinationPoint = null;
		for ( int i = 0; i < 5; i++ )
		{
			destinationPoint = Position.add( performer.getPosition( ), var );
			// Cell destinationCell = aLevel.getMapCell(destinationPoint);
			if ( !aLevel.isValidCoordinate( destinationPoint )
					|| aLevel.isSolid( destinationPoint ) )
				break;
			if ( aPlayer.getPosition( ).equals( destinationPoint )
					&& aPlayer.getStandingHeight( ) == aMonster.getStandingHeight( ) )
			{
				message.append( "The " + aMonster.getDescription( ) + " slices you!" );
				if ( aPlayer.damage( message, aMonster,
						new Damage( aMonster.getAttack( ), false ) ) )
					aPlayer.bounceBack( var, 1 );
			}

			aMonster.setPosition( destinationPoint );
			// currentCell = aLevel.getMapCell(destinationPoint);
		}
		aLevel.addMessage( message.toString( ) );
	}

	public String getID( )
	{
		return "SLICE_DIVE";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to slice?";
	}

	public boolean needsDirection( )
	{
		return true;
	}

}