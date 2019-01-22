package co.castle.action.vkiller;

import java.util.Vector;

import co.castle.action.HeartAction;
import co.castle.feature.Feature;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class Bible extends HeartAction
{
	private final static Vector steps = new Vector( 10 );

	static
	{
		steps.add( new Position( 1, 0 ) );
		steps.add( new Position( 2, -1 ) );
		steps.add( new Position( 1, -2 ) );
		steps.add( new Position( 0, -2 ) );
		steps.add( new Position( -1, -2 ) );
		steps.add( new Position( -2, -1 ) );
		steps.add( new Position( -2, 0 ) );
		steps.add( new Position( -2, 1 ) );
		steps.add( new Position( -1, 2 ) );
		steps.add( new Position( 0, 2 ) );
		steps.add( new Position( 1, 2 ) );
		steps.add( new Position( 2, 2 ) );
		steps.add( new Position( 3, 1 ) );
		steps.add( new Position( 4, 0 ) );
		steps.add( new Position( 4, -1 ) );
		steps.add( new Position( 4, -2 ) );
		steps.add( new Position( 4, -3 ) );
		steps.add( new Position( 3, -4 ) );
		steps.add( new Position( 2, -4 ) );
		steps.add( new Position( 1, -4 ) );
		steps.add( new Position( 0, -4 ) );
		steps.add( new Position( -1, -4 ) );
		steps.add( new Position( -2, -4 ) );
		steps.add( new Position( -3, -3 ) );
		steps.add( new Position( -4, -2 ) );
		steps.add( new Position( -4, -1 ) );
		steps.add( new Position( -4, 0 ) );
		steps.add( new Position( -4, 1 ) );
		steps.add( new Position( -4, 2 ) );
		steps.add( new Position( -3, 3 ) );
		steps.add( new Position( -2, 4 ) );
		steps.add( new Position( -1, 4 ) );
		steps.add( new Position( 0, 4 ) );
		steps.add( new Position( 1, 4 ) );
		steps.add( new Position( 2, 4 ) );
		steps.add( new Position( 3, 4 ) );
		steps.add( new Position( 4, 3 ) );
		steps.add( new Position( 5, 2 ) );
		steps.add( new Position( 6, 1 ) );
		steps.add( new Position( 6, 0 ) );
		steps.add( new Position( 6, -1 ) );
		steps.add( new Position( 6, -2 ) );
		steps.add( new Position( 6, -3 ) );
		steps.add( new Position( 6, -4 ) );
		steps.add( new Position( 5, -5 ) );
		steps.add( new Position( 4, -6 ) );
		steps.add( new Position( 3, -7 ) );
		steps.add( new Position( 2, -8 ) );
		steps.add( new Position( 1, -9 ) );
		steps.add( new Position( 0, -10 ) );
		steps.add( new Position( -1, -11 ) );
		steps.add( new Position( -2, -12 ) );
		steps.add( new Position( -3, -13 ) );
		steps.add( new Position( -4, -14 ) );
		steps.add( new Position( -5, -15 ) );
		steps.add( new Position( -6, -16 ) );
	}

	public void execute( )
	{
		super.execute( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = (Player) performer;
		aPlayer.getLevel( ).addMessage( "You open the bible!" );
		// drawEffect(new SequentialEffect(performer.getPosition(), steps, "?�",
		// Appearance.CYAN, 10));
		drawEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( performer.getPosition( ), "SFX_BIBLE" ) );

		int damage = getDamage( );
		for ( int i = 0; i < steps.size( ); i++ )
		{
			Position destinationPoint = Position.add( performer.getPosition( ),
					(Position) steps.elementAt( i ) );
			StringBuffer message = new StringBuffer( );
			Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
			if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
			{
				message.append(
						"The " + destinationFeature.getDescription( ) + " is slashed" );
				Feature prize = destinationFeature.damage( aLevel.getPlayer( ), damage );
				if ( prize != null )
				{
					message.append( " and thorn apart!" );
				}
				else
					message.append( "." );
				aLevel.addMessage( message.toString( ) );
			}

			Monster targetMonster = performer.getLevel( )
					.getMonsterAt( destinationPoint );
			message = new StringBuffer( );
			if ( targetMonster != null )
			{
				message.append(
						"The " + targetMonster.getDescription( ) + " is slashed" );
				// targetMonster.damage(player.getWhipLevel());
				targetMonster.damage( message, damage );
				if ( targetMonster.isDead( ) )
				{
					message.append( " apart!" );
					performer.getLevel( ).removeMonster( targetMonster );
				}
				else
				{
					message.append( "." );
				}
				if ( targetMonster.wasSeen( ) )
					aLevel.addMessage( message.toString( ) );
			}
		}
	}

	public int getCost( )
	{
		Player p = (Player) performer;
		return (int) ( 25 / ( p.getShotLevel( ) + 1 ) );
	}

	public int getHeartCost( )
	{
		return 2;
	}

	public String getID( )
	{
		return "Bible";
	}

	public String getPromptDirection( )
	{
		return "Where do you want to throw the Cross?";
	}

	public String getSFX( )
	{
		return "wav/loutwarp.wav";
	}

	private int getDamage( )
	{
		return 7 + getPlayer( ).getShotLevel( ) + getPlayer( ).getSoulPower( ) * 2;
	}

}