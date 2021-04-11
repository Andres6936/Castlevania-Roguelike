package co.castle.action.monster;

import co.castle.action.Action;
import co.castle.game.SFXManager;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Damage;
import co.castle.player.Player;
import co.castle.ui.effects.EffectFactory;
import sz.util.Debug;
import sz.util.Line;
import sz.util.Position;

public class MonsterMissile extends Action
{
	private int damage;
	private String effectID;
	private String effectType;
	private String effectWav;
	private String message;
	private int range;
	private String statusEffect;
	private String type;

	public final static String TYPE_AXE = "AXE", TYPE_STRAIGHT = "STRAIGHT",
			TYPE_DIRECT = "DIRECT";

	public void execute( )
	{
		Debug.doAssert( performer instanceof Monster,
				"Someone not a monster tried to throw a bone" );
		Monster aMonster = (Monster) performer;
		Level aLevel = performer.getLevel( );
		Player aPlayer = aLevel.getPlayer( );
		// Cell playerCell = aLevel.getMapCell(aPlayer.getPosition());
		// Cell monsterCell = aLevel.getMapCell(aMonster.getPosition());
		int targetDirection = solveDirection( performer.getPosition( ), targetPosition );
		if ( effectWav != null )
		{
			SFXManager.play( effectWav );
		}
		if ( effectType.equals( "beam" ) )
		{
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aMonster.getPosition( ), targetPosition, effectID, range ) );
		}
		else if ( effectType.equals( "melee" ) )
		{
			drawEffect( EffectFactory.getSingleton( ).createDirectionalEffect(
					aMonster.getPosition( ), targetDirection, range, effectID ) );
		}
		else if ( effectType.equals( "missile" ) )
		{
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aMonster.getPosition( ), targetPosition, effectID, range ) );
		}
		else if ( effectType.equals( "directionalmissile" ) )
		{
			drawEffect( EffectFactory.getSingleton( ).createDirectedEffect(
					aMonster.getPosition( ), targetPosition, effectID, range ) );
		}
		Line line = new Line( aMonster.getPosition( ), targetPosition );
		boolean hits = false;
		for ( int i = 0; i < range; i++ )
		{
			Position destinationPoint = line.next( );
			if ( aPlayer.getPosition( ).equals( destinationPoint ) )
			{
				int penalty = (int) ( Position.distance( aMonster.getPosition( ),
						aPlayer.getPosition( ) ) / 4 );
				int attack = aMonster.getAttack( );
				attack -= penalty;
				if ( attack < 1 )
					attack = 1;
				if ( type.equals( TYPE_DIRECT ) )
				{
					hits = true;
				}
				else if ( type.equals( TYPE_STRAIGHT ) )
				{
					// if (monsterCell.getHeight() == playerCell.getHeight()){
					if ( aMonster.getStandingHeight( ) == aPlayer.getStandingHeight( ) )
					{
						hits = true;
					}
					else
					{
						hits = false;
					}
				}
				else if ( type.equals( TYPE_AXE ) )
				{
					if ( i > (int) ( range / 4 ) && i < (int) ( 3 * ( range / 4 ) ) )
					{
						// if (playerCell.getHeight() == monsterCell.getHeight()+2 ||
						// playerCell.getHeight() == monsterCell.getHeight()+3){
						if ( aMonster.getStandingHeight( ) + 2 == aPlayer
								.getStandingHeight( )
								|| aMonster.getStandingHeight( ) + 3 == aPlayer
										.getStandingHeight( ) )
						{
							hits = true;
						}
						else
						{
							hits = false;
						}
					}
					else
					{
						if ( aMonster.getStandingHeight( ) == aPlayer
								.getStandingHeight( ) )
						{
							hits = true;
						}
						else
						{
							hits = false;
						}
					}
				}
				if ( hits )
				{
					aLevel.addBlood( destinationPoint, 1 );
					StringBuffer buff = new StringBuffer( "You are hit." );
					if ( aPlayer.damage( buff, aMonster, new Damage(
							( damage == 0 ? aMonster.getAttack( ) : damage ), false ) ) )
					{
						aLevel.addMessage( buff.toString( ) );
						if ( statusEffect != null )
						{
							if ( statusEffect.equals( Player.STATUS_STUN ) )
							{
								aLevel.addMessage( "You are stunned!" );
								aPlayer.setStun( 8 );
							}
							else if ( statusEffect.equals( Player.STATUS_POISON ) )
							{
								aLevel.addMessage( "Your blood is poisoned!" );
								aPlayer.setPoison( 15 );
							}
							else if ( statusEffect.equals( Player.STATUS_PETRIFY ) )
							{
								aLevel.addMessage( "Your skin petrifies!" );
								aPlayer.setPetrify( 10 );
							}
						}
					}
				}
				break;
			}
		}
		aLevel.addMessage( "The " + aMonster.getDescription( ) + " " + message + "." );
	}

	/*
	 * public void set(String pStatusEffect, int pRange, String pMessage, String
	 * pEffectType, String pEffectID){ range = pRange; statusEffect = pStatusEffect;
	 * message = pMessage; effectType = pEffectType; effectID = pEffectID; damage =
	 * 0; }
	 */

	public int getCost( )
	{
		Monster m = (Monster) performer;
		if ( m.getAttackCost( ) == 0 )
		{
			Debug.say( "quickie monster" );
			return 10;
		}
		return m.getAttackCost( );
	}

	public String getEffectWav( )
	{
		return effectWav;
	}

	public String getID( )
	{
		return "MONSTER_MISSILE";
	}

	public String getPromptPosition( )
	{
		return "";
	}

	public boolean needsPosition( )
	{
		return true;
	}

	public void set(	String pType, String pStatusEffect, int pRange, String pMessage,
						String pEffectType, String pEffectID, int damage,
						String pEffectWav )
	{
		type = pType;
		range = pRange;
		statusEffect = pStatusEffect;
		message = pMessage;
		effectType = pEffectType;
		effectID = pEffectID;
		effectWav = pEffectWav;
		this.damage = damage;
	}

	private int solveDirection( Position old, Position newP )
	{
		if ( newP.x( ) == old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
			{
				return Action.DOWN;
			}
			else
			{
				return Action.UP;
			}
		}
		else if ( newP.y( ) == old.y( ) )
		{
			if ( newP.x( ) > old.x( ) )
			{
				return Action.RIGHT;
			}
			else
			{
				return Action.LEFT;
			}
		}
		else if ( newP.x( ) < old.x( ) )
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNLEFT;
			else
				return Action.UPLEFT;
		}
		else
		{
			if ( newP.y( ) > old.y( ) )
				return Action.DOWNRIGHT;
			else
				return Action.UPRIGHT;
		}
	}

}