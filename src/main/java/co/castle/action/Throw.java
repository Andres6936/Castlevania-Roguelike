package co.castle.action;

import co.castle.feature.CountDown;
import co.castle.feature.SmartFeature;
import co.castle.feature.SmartFeatureFactory;
import co.castle.level.Level;
import sz.util.Line;
import sz.util.Position;

public class Throw extends Action
{
	public void execute( )
	{
		Level aLevel = performer.getLevel( );
		performer.getLevel( )
				.addMessage( "You throw the " + targetItem.getDescription( ) );

		// aLevel.addEffect(new PCMissileEffect(performer.getPosition(), "+x",
		// Appearance.CYAN, targetDirection, 10));
		int distance = Position.flatDistance( performer.getPosition( ), targetPosition );
		Position destinationPoint = null;
		if ( distance > targetItem.getThrowRange( ) )
		{
			distance = targetItem.getThrowRange( );
		}
		// aLevel.addEffect(new LineIconMissileEffect(performer.getPosition(),
		// targetItem.getAppearance().getChar(), targetItem.getAppearance().getColor(),
		// performer.getPosition(), targetPosition, distance, 30));
		// Removed for not complexing the tier, would need to specifiy fx for each item
		// aLevel.addEffect(EffectFactory.getSingleton().createTileMissileEffect(performer.getPosition(),
		// destinationPoint, "SFX_RED_HIT", distance));
		Line line = new Line( performer.getPosition( ), targetPosition );
		int i = 0;
		Position runner = line.next( );
		for ( i = 0; i < distance; i++ )
		{
			runner = line.next( );
			if ( !aLevel.isValidCoordinate( runner )
					|| ( aLevel.getMapCell( runner ) != null
							&& aLevel.getMapCell( runner ).isSolid( ) ) )
			{
				break;
			}
		}
		destinationPoint = runner;
		if ( aLevel.getMapCell( destinationPoint ) == null )
		{
			destinationPoint = aLevel.getDeepPosition( destinationPoint );
		}

		if ( destinationPoint == null )
		{
			// The feature falls to the infinity
		}
		else
		{
			String placedSmartFeature = targetItem.getPlacedSmartFeature( );
			if ( !placedSmartFeature.equals( "" ) )
			{
				SmartFeature feature = SmartFeatureFactory.getFactory( )
						.buildFeature( placedSmartFeature );
				feature.setPosition( destinationPoint );
				( (CountDown) feature.getSelector( ) )
						.setTurns( targetItem.getFeatureTurns( ) );
				aLevel.addSmartFeature( feature );

				feature = SmartFeatureFactory.getFactory( )
						.buildFeature( placedSmartFeature );
				feature.setPosition( Position.add( destinationPoint,
						Action.directionToVariation( Action.UP ) ) );
				( (CountDown) feature.getSelector( ) )
						.setTurns( targetItem.getFeatureTurns( ) );
				aLevel.addSmartFeature( feature );

				feature = SmartFeatureFactory.getFactory( )
						.buildFeature( placedSmartFeature );
				feature.setPosition( Position.add( destinationPoint,
						Action.directionToVariation( Action.DOWN ) ) );
				( (CountDown) feature.getSelector( ) )
						.setTurns( targetItem.getFeatureTurns( ) );
				aLevel.addSmartFeature( feature );

				feature = SmartFeatureFactory.getFactory( )
						.buildFeature( placedSmartFeature );
				feature.setPosition( Position.add( destinationPoint,
						Action.directionToVariation( Action.LEFT ) ) );
				( (CountDown) feature.getSelector( ) )
						.setTurns( targetItem.getFeatureTurns( ) );
				aLevel.addSmartFeature( feature );

				feature = SmartFeatureFactory.getFactory( )
						.buildFeature( placedSmartFeature );
				feature.setPosition( Position.add( destinationPoint,
						Action.directionToVariation( Action.RIGHT ) ) );
				( (CountDown) feature.getSelector( ) )
						.setTurns( targetItem.getFeatureTurns( ) );
				aLevel.addSmartFeature( feature );
			}
			else
				aLevel.addItem( destinationPoint, targetItem );
		}
		performer.getLevel( ).getPlayer( ).reduceQuantityOf( targetItem );

	}

	public String getID( )
	{
		return "Throw";
	}

	public String getPromptItem( )
	{
		return "What do you want to throw?";
	}

	public String getPromptPosition( )
	{
		return "Where do you want to throw the "
				+ targetItem.getDefinition( ).getDescription( ) + "?";
	}

	public String getSFX( )
	{
		return "wav/rich_yah.wav";
	}

	public boolean needsItem( )
	{
		return true;
	}

	public boolean needsPosition( )
	{
		return true;
	}
}