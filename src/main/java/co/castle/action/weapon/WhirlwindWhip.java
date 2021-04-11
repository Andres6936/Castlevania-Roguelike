package co.castle.action.weapon;

import co.castle.action.Action;
import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.item.ItemDefinition;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Player;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.util.Position;

public class WhirlwindWhip extends Action
{
	public boolean canPerform( Actor a )
	{
		Player aPlayer = (Player) a;
		if ( aPlayer.getHearts( ) < 5 )
		{
			invalidationMessage = "You need more energy!";
			return false;
		}
		return true;
	}

	public void execute( )
	{
		Player aPlayer = (Player) performer;
		Level aLevel = aPlayer.getLevel( );
		if ( !checkHearts( 5 ) )
		{
			aLevel.addMessage( "You need more hearts" );
			return;
		}
		int attack = aPlayer.getWeaponAttack( );
		if ( aPlayer.getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
		{
			attack = aPlayer.weaponSkill( ItemDefinition.CAT_WHIPS ) + (int) Math.round(
					aPlayer.getAttack( ) * ( aPlayer.getWeapon( ).getAttack( ) / 2.0D ) );
		}
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.UP ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.UPLEFT ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.LEFT ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.DOWNLEFT ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.DOWN ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.DOWNRIGHT ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.RIGHT ) ), attack );
		hit( Position.add( performer.getPosition( ),
				Action.directionToVariation( Action.UPRIGHT ) ), attack );
	}

	public String getID( )
	{
		return "WhirlwindWhip";
	}

	private boolean hit( Position destinationPoint, int attack )
	{
		StringBuffer message = new StringBuffer( );
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		UserInterface.getUI( ).drawEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( destinationPoint, "SFX_WHITE_HIT" ) );
		// aLevel.addBlood(destinationPoint, 8);
		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		if ( destinationFeature != null && destinationFeature.isDestroyable( ) )
		{
			message.append( "You crush the " + destinationFeature.getDescription( ) );

			Feature prize = destinationFeature.damage( aPlayer, attack );
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
			message.append( "You whip the " + targetMonster.getDescription( ) );
			targetMonster.damageWithWeapon( message, attack );
			if ( targetMonster.isDead( ) )
			{
				message.append( ", destroying it!" );
				// performer.getLevel().removeMonster(targetMonster);
			}
			if ( targetMonster.wasSeen( ) )
				aLevel.addMessage( message.toString( ) );

			return true;
		}
		return false;
	}
}