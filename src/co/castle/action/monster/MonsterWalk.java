package co.castle.action.monster;

import co.castle.action.Action;
import co.castle.ai.monster.BasicMonsterAI;
import co.castle.feature.Feature;
import co.castle.feature.SmartFeature;
import co.castle.game.SFXManager;
import co.castle.level.Cell;
import co.castle.level.Level;
import co.castle.monster.Monster;
import co.castle.player.Consts;
import co.castle.player.Damage;
import sz.util.Debug;
import sz.util.Position;

public class MonsterWalk extends Action
{
	public void execute( )
	{
		Debug.doAssert( performer instanceof Monster,
				"The player tried to MonsterWalk..." );
		Monster aMonster = (Monster) performer;
		Position var = directionToVariation( targetDirection );
		Position destinationPoint = Position.add( performer.getPosition( ), var );
		Level aLevel = performer.getLevel( );
		if ( !aLevel.isValidCoordinate( destinationPoint ) )
			return;
		Cell destinationCell = aLevel.getMapCell( destinationPoint );
		Cell currentCell = aLevel.getMapCell( performer.getPosition( ) );

		Monster destinationMonster = aLevel.getMonsterAt( destinationPoint );
		Feature destinationFeature = aLevel.getFeatureAt( destinationPoint );
		SmartFeature standing = aLevel.getSmartFeature( performer.getPosition( ) );
		if ( standing != null )
			if ( standing.getEffectOnStep( ) != null )
			{
				String[ ] effects = standing.getEffectOnStep( ).split( " " );
				if ( effects[ 0 ].equals( "TRAP" ) && aMonster != aLevel.getBoss( ) )
				{
					aLevel.addMessage(
							"The " + aMonster.getDescription( ) + " is trapped!" );
					return;
				}
			}

		if ( destinationFeature != null && destinationFeature.isSolid( ) )
		{
			// if (Util.chance(50)){
			if ( aMonster.wasSeen( ) )
				aLevel.addMessage( "The " + aMonster.getDescription( ) + " hits the "
						+ destinationFeature.getDescription( ) );
			destinationFeature.damage( aMonster );
			// }
		}
		if ( destinationCell == null || destinationCell.isSolid( )
				|| ( destinationCell.isEthereal( ) && !aMonster.isFlying( ) ) )
			if ( aMonster.getSelector( ) instanceof BasicMonsterAI )
				if ( ( (BasicMonsterAI) aMonster.getSelector( ) ).getPatrolRange( ) > 0 )
					( (BasicMonsterAI) aMonster.getSelector( ) )
							.setChangeDirection( true );
		if ( destinationCell != null
				&& ( !destinationCell.isEthereal( ) || ( destinationCell.isEthereal( )
						&& ( aMonster.isEthereal( ) || aMonster.isFlying( ) ) ) ) )
		{
			if ( destinationFeature != null && destinationFeature.isSolid( ) )
			{

			}
			else
			{
				if ( aMonster.isEthereal( ) || !destinationCell.isSolid( ) )
				{
					if ( destinationMonster == null )
					{
						if ( aMonster.isEthereal( ) || aMonster.isFlying( )
								|| currentCell == null
								|| destinationCell
										.getHeight( ) <= currentCell.getHeight( )
												+ aMonster.getLeaping( ) + 1 )
						{
							if ( destinationCell.isShallowWater( ) )
							{
								if ( aMonster.canSwim( ) || aMonster.isEthereal( ) )
								{
									performer.setPosition( destinationPoint );
								}
							}
							else if ( destinationCell.isWater( ) )
							{
								if ( aMonster.canSwim( ) || aMonster.isEthereal( ) )
								{
									performer.setPosition( destinationPoint );
								}
							}
							else
							{
								if ( aLevel.getPlayer( ).getPosition( )
										.equals( destinationPoint ) )
								{
									if ( aLevel.getPlayer( )
											.getStandingHeight( ) == aMonster
													.getStandingHeight( ) )
									{
										if ( aMonster.getWavOnHit( ) != null )
											SFXManager.play( aMonster.getWavOnHit( ) );
										StringBuffer buff = new StringBuffer(
												"You are hit by the "
														+ aMonster.getDescription( )
														+ "!" );
										if ( aLevel.getPlayer( ).damage( buff, aMonster,
												new Damage( aMonster.getAttack( ),
														false ) ) )
										{
											aLevel.getPlayer( ).bounceBack( var, 2 );
											aLevel.addMessage( buff.toString( ) );
											if ( !aLevel.getPlayer( ).getPosition( )
													.equals( destinationPoint ) )
											{
												aLevel.addMessage(
														"You are bounced back!" );
												performer.setPosition( destinationPoint );
											}
										}
									}
									else if ( aLevel.getPlayer( )
											.getStandingHeight( ) > aMonster
													.getStandingHeight( ) )
									{
										aLevel.addMessage(
												"The " + aMonster.getDescription( )
														+ " walks beneath you" );
										performer.setPosition( destinationPoint );
										/* Esto deberia estar en un landing */
										if ( aLevel.getSmartFeature(
												destinationPoint ) != null )
										{
											SmartFeature sf = aLevel
													.getSmartFeature( destinationPoint );
											if ( sf.getDamageOnStep( ) > 0 )
											{
												StringBuffer buff = new StringBuffer(
														"The " + aMonster
																.getDescription( )
																+ " is injured by the "
																+ sf.getDescription( ) );
												aMonster.damage( buff,
														sf.getDamageOnStep( ) );
												aLevel.addMessage( buff.toString( ) );
											}
										}
									}
									else
									{
										aLevel.addMessage(
												"The " + aMonster.getDescription( )
														+ " hovers over you!" );
										performer.setPosition( destinationPoint );
										if ( aLevel.getSmartFeature(
												destinationPoint ) != null )
										{
											SmartFeature sf = aLevel
													.getSmartFeature( destinationPoint );
											if ( sf.getDamageOnStep( ) > 0 )
											{
												StringBuffer buff = new StringBuffer(
														"The " + aMonster
																.getDescription( )
																+ " is injured by the "
																+ sf.getDescription( ) );
												aMonster.damage( buff,
														sf.getDamageOnStep( ) );
												aLevel.addMessage( buff.toString( ) );
											}
										}
									}
								}
								else
								{
									performer.setPosition( destinationPoint );
									if ( aLevel
											.getSmartFeature( destinationPoint ) != null )
									{
										SmartFeature sf = aLevel
												.getSmartFeature( destinationPoint );
										if ( sf.getDamageOnStep( ) > 0 )
										{
											StringBuffer buff = new StringBuffer(
													"The " + aMonster.getDescription( )
															+ " is injured by the "
															+ sf.getDescription( ) );
											aMonster.damage( buff,
													sf.getDamageOnStep( ) );
											aLevel.addMessage( buff.toString( ) );
										}
									}
								}
							}
						}
					}
					else
					{
						if ( aMonster.hasCounter( Consts.C_MONSTER_CHARM )
								|| aMonster.getEnemy( ) == destinationMonster )
						{
							destinationMonster.tryHit( (Monster) performer );
						}
					}
				}
			}
		}

	}

	public int getCost( )
	{
		Monster m = (Monster) performer;
		if ( m.getWalkCost( ) == 0 )
		{
			Debug.say( "quickie monster" );
			return 10;
		}
		return m.getWalkCost( );
	}

	public String getID( )
	{
		return "MonsterWalk";
	}

	public boolean needsDirection( )
	{
		return true;
	}
}