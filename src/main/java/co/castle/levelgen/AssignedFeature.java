package co.castle.levelgen;

import sz.util.Position;

public class AssignedFeature
{
	private LevelFeature feature;
	private Position position;

	public AssignedFeature( Position position, LevelFeature feature )
	{
		this.position = position;
		this.feature = feature;
	}

	public LevelFeature getFeature( )
	{
		return feature;
	}

	public Position getPosition( )
	{
		return position;
	}
}