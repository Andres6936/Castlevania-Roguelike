package co.castle.action.renegade;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class BallOfDestruction extends Action
{
	public boolean canPerform( Actor a )
	{
		Player aPlayer = (Player) a;
		Level aLevel = performer.getLevel( );
		if ( aPlayer.getHearts( ) < 4 )
		{
			invalidationMessage = "You need more energy!";
			return false;
		}
		return true;
	}

	public void execute( )
	{
		Position var = directionToVariation( targetDirection );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		if ( aPlayer.getHearts( ) < 4 )
		{
			aLevel.addMessage( "You need more hearts." );
			return;
		}
		aPlayer.reduceHearts( 4 );
		aLevel.addMessage( "Three balls of fire emerge from your cape!" );

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
			aLevel.addMessage( "The balls dissapear uppon hitting the floor" );
			return;
		}
		Position var1 = directionToVariation( otherDir1 );
		Position var2 = directionToVariation( otherDir2 );
		int i = 0;
		for ( ; i < 20; i++ )
		{
			Position destinationPoint = Position.add( aPlayer.getPosition( ),
					Position.mul( var, i + 1 ) );
			if ( hit( destinationPoint, i ) )
				break;
		}
		if ( i == 20 )
			// drawEffect(new MissileEffect(new Position(aPlayer.getPosition()), "*~",
			// Appearance.RED, targetDirection,20, true, false));
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aPlayer.getPosition( ),
					this.getPositionalDirectionFrom( aPlayer.getPosition( ) ),
					"SFX_RENEGADE_BOD", 20 ) );
		// -----
		i = 0;
		for ( ; i < 20; i++ )
		{
			Position destinationPoint = Position.add( aPlayer.getPosition( ),
					Position.mul( var1, i + 1 ) );
			if ( hit( destinationPoint, i ) )
				break;
		}
		if ( i == 20 )
			// drawEffect(new MissileEffect(new Position(aPlayer.getPosition()), "*~",
			// Appearance.RED, otherDir1,20, true, false));
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aPlayer.getPosition( ),
					this.getPositionalDirectionFrom( aPlayer.getPosition( ), otherDir1 ),
					"SFX_RENEGADE_BOD", 20 ) );
		// -----
		i = 0;
		for ( ; i < 20; i++ )
		{
			Position destinationPoint = Position.add( aPlayer.getPosition( ),
					Position.mul( var2, i + 1 ) );
			if ( hit( destinationPoint, i ) )
				break;
		}
		if ( i == 20 )
			// drawEffect(new MissileEffect(new Position(aPlayer.getPosition()), "*~",
			// Appearance.RED, otherDir2,20, true, false));
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aPlayer.getPosition( ),
					this.getPositionalDirectionFrom( aPlayer.getPosition( ), otherDir2 ),
					"SFX_RENEGADE_BOD", 20 ) );

	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( p.getCastCost( ) * 1.3 );
	}

	public String getID( )
	{
		return "BallOfDestruction";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to throw the fireball?";
	}

	public String getSFX( )
	{
		return "wav/fire.wav";
	}

	public boolean needsDirection( )
	{
		return true;
	}

	private boolean hit( Position destinationPoint, int i )
	{
		StringBuffer message = new StringBuffer( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );

		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
		{
			message.append(
					"The fireball hits the " + destinationFeature.getDescription( ) );
			// drawEffect(new MissileEffect(new Position(aPlayer.getPosition()), "*~",
			// Appearance.RED, targetDirection,i, true, false));
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aPlayer.getPosition( ),
					this.getPositionalDirectionFrom( aPlayer.getPosition( ) ),
					"SFX_RENEGADE_BOD", i ) );
			Feature prize = destinationFeature.damage( aPlayer, 1 );
			if ( prize != null )
			{
				message.append( ", burning it!" );
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
				message.append(
						"The fireball burns the " + targetMonster.getDescription( ) );
			// targetMonster.damage(player.getWhipLevel());
			targetMonster.damage( message, 1 + aPlayer.getSoulPower( ) * 2 );
			// drawEffect(new MissileEffect(new Position(aPlayer.getPosition()), "*~",
			// Appearance.RED, targetDirection,i, true, false));
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aPlayer.getPosition( ),
					this.getPositionalDirectionFrom( aPlayer.getPosition( ) ),
					"SFX_RENEGADE_BOD", i ) );
			aLevel.addMessage( message.toString( ) );

			return true;
		}
		return false;
	}
}