package co.castle.action.vkiller;

import co.castle.action.Action;
import co.castle.feature.Feature;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import sz.util.Debug;
import sz.util.Position;

public class Rebound extends Action
{
	public void execute( )
	{
		Debug.doAssert( performer instanceof Player, "action.Rebound" );
		Player aPlayer = (Player) performer;

		Level aLevel = performer.getLevel( );
		if ( aPlayer.getHearts( ) < 1 )
		{
			aLevel.addMessage( "You don't have enough hearts" );
			return;
		}
		aPlayer.reduceHearts( 1 );
		aLevel.addMessage( "You throw a rebound crystal!" );

		Position var = new Position( directionToVariation( targetDirection ) );
		int runLength = 0;
		// DirectionMissileEffect x = new DirectionMissileEffect(aPlayer.getPosition(),
		// "\\|/--/|\\", Appearance.BLUE, targetDirection, 20, 15);
		Position runner = new Position( performer.getPosition( ) );
		StringBuffer message = new StringBuffer( );
		Position bouncePoint = new Position( performer.getPosition( ) );
		for ( int i = 0; i < 20; i++ )
		{
			runLength++;
			runner.add( var );
			Feature destinationFeature = aLevel.getFeatureAt( runner );
			if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
			{
				message.append(
						"The crystal hits the " + destinationFeature.getDescription( ) );
				Feature prize = destinationFeature.damage( aPlayer, 1 );
				if ( prize != null )
				{
					message.append( ", and destroys it" );
				}
				aLevel.addMessage( message.toString( ) );
				/*
				 * x.setPosition(bouncePoint); x.setDepth(runLength);
				 * x.setDirection(Action.toIntDirection(var)); aLevel.addEffect(x);
				 */
				break;
			}
			Monster targetMonster = performer.getLevel( ).getMonsterAt( runner );
			message = new StringBuffer( );
			if ( targetMonster != null && !targetMonster.isInWater( ) )
			{
				message.append(
						"The crystal hits the " + targetMonster.getDescription( ) );
				// targetMonster.damage(player.getWhipLevel());
				targetMonster.damage( message, 1 );
				if ( targetMonster.isDead( ) )
				{
					message.append( ", destroying it!" );
					performer.getLevel( ).removeMonster( targetMonster );
				}
				if ( targetMonster.wasSeen( ) )
					aLevel.addMessage( message.toString( ) );
				/*
				 * x.setPosition(bouncePoint); x.setDepth(runLength);
				 * x.setDirection(Action.toIntDirection(var)); aLevel.addEffect(x);
				 */
				break;
			}

			Cell targetCell = performer.getLevel( ).getMapCell( runner );
			if ( targetCell != null
					&& ( targetCell.isSolid( ) || targetCell.getHeight( ) > aLevel
							.getMapCell( performer.getPosition( ) ).getHeight( ) + 2 ) )
			{
				aLevel.addMessage(
						"The crystal rebounds in the " + targetCell.getDescription( ) );
				/*
				 * x.setPosition(bouncePoint); x.setDepth(runLength);
				 * x.setDirection(Action.toIntDirection(var));
				 */
				bouncePoint = new Position( runner );
				// aLevel.addEffect(x);
				Position bounce = new Position( 0, 0 );
				if ( aLevel.getMapCell( runner.x + var.x, runner.y,
						performer.getPosition( ).z ) == null
						|| aLevel.getMapCell( runner.x + var.x, runner.y,
								performer.getPosition( ).z ).isSolid( ) )
					bounce.x = 1;
				if ( aLevel.getMapCell( runner.x, runner.y + var.y,
						performer.getPosition( ).z ) == null
						|| aLevel.getMapCell( runner.x, runner.y + var.y,
								performer.getPosition( ).z ).isSolid( ) )
					bounce.y = 1;
				/*
				 * if (aLevel.getMapCell(runner.x, runner.y + var.y,
				 * performer.getPosition().z) == null || aLevel.getMapCell(runner.x+var.x,
				 * runner.y + var.y, performer.getPosition().z).isSolid()){ bounce.y = 1;
				 * bounce.x = 1; }
				 */
				var.mul( Position.mul( new Position( -1, -1 ), bounce ) );
				runLength = 0;
			}

			if ( i == 19 )
			{
				/*
				 * x.setPosition(bouncePoint); x.setDepth(runLength); aLevel.addEffect(x);
				 */
			}
		}

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public String getID( )
	{
		return "Rebound";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to throw the Dagger?";
	}

	public boolean needsDirection( )
	{
		return true;
	}
}