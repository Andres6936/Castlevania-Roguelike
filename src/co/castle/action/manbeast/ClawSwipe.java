package co.castle.action.manbeast;

import co.castle.action.Action;
import co.castle.action.HeartAction;
import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class ClawSwipe extends HeartAction
{
	public boolean canPerform( Actor a )
	{
		Player aPlayer = (Player) a;
		if ( aPlayer.getHearts( ) < 3 )
		{
			invalidationMessage = "You feel wimpy";
			return false;
		}
		return true;
	}

	public void execute( )
	{
		super.execute( );
		Player aPlayer = (Player) performer;
		int damage = 4 + aPlayer.getPunchDamage( );
		Level aLevel = aPlayer.getLevel( );
		int otherDir1 = 0;
		int otherDir2 = 0;
		switch ( targetDirection )
		{
		case Action.UP:
			otherDir1 = Action.UPLEFT;
			otherDir2 = Action.UPRIGHT;
			break;
		case Action.DOWN:
			otherDir1 = Action.DOWNLEFT;
			otherDir2 = Action.DOWNRIGHT;
			break;
		case Action.LEFT:
			otherDir1 = Action.UPLEFT;
			otherDir2 = Action.DOWNLEFT;
			break;
		case Action.RIGHT:
			otherDir1 = Action.UPRIGHT;
			otherDir2 = Action.DOWNRIGHT;
			break;
		case Action.UPRIGHT:
			otherDir1 = Action.UP;
			otherDir2 = Action.RIGHT;
			break;
		case Action.UPLEFT:
			otherDir1 = Action.UP;
			otherDir2 = Action.LEFT;
			break;
		case Action.DOWNLEFT:
			otherDir1 = Action.LEFT;
			otherDir2 = Action.DOWN;
			break;
		case Action.DOWNRIGHT:
			otherDir1 = Action.RIGHT;
			otherDir2 = Action.DOWN;
			break;
		case Action.SELF:
			aLevel.addMessage( "Hitting youself?" );
			return;
		}
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( otherDir1 ) ), damage );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( targetDirection ) ), damage );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( otherDir2 ) ), damage );

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getAttackCost( ) * 1.2 );
	}

	public int getHeartCost( )
	{
		return 3;
	}

	public String getID( )
	{
		return "ClawSwipe";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to unleash your energy?";
	}

	public String getSFX( )
	{
		return "wav/swaashll.wav";
	}

	public boolean needsDirection( )
	{
		return true;
	}

	private boolean hit( Position destinationPoint, int damage )
	{
		StringBuffer message = new StringBuffer( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		// UserInterface.getUI().drawEffect(new TileEffect(destinationPoint, '*',
		// Appearance.RED, 100));
		UserInterface.getUI( ).drawEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( destinationPoint, "SFX_RED_HIT" ) );

		// aLevel.addBlood(destinationPoint, 8);
		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
		{
			message.append( "You crush the " + destinationFeature.getDescription( ) );

			Feature prize = destinationFeature.damage( aPlayer, damage );
			if ( prize != null )
			{
				message.append( ", breaking it apart!" );
			}
			aLevel.addMessage( message.toString( ) );
			return true;
		}
		Monster targetMonster = performer.getLevel( ).getMonsterAt( destinationPoint );
		Cell destinationCell = performer.getLevel( ).getMapCell( destinationPoint );
		if ( targetMonster != null
				&& !( targetMonster.isInWater( ) && targetMonster.canSwim( ) )
				&& ( destinationCell.getHeight( ) == aLevel
						.getMapCell( aPlayer.getPosition( ) ).getHeight( )
						|| destinationCell.getHeight( ) - 1 == aLevel
								.getMapCell( aPlayer.getPosition( ) ).getHeight( )
						|| destinationCell.getHeight( ) == aLevel
								.getMapCell( aPlayer.getPosition( ) ).getHeight( ) - 1 ) )
		{
			if ( targetMonster.wasSeen( ) )
				message.append( "You crush the " + targetMonster.getDescription( ) );
			// targetMonster.damage(player.getWhipLevel());
			targetMonster.damage( message, damage );

			aLevel.addMessage( message.toString( ) );

			return true;
		}
		return false;
	}
}