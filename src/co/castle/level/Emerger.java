package co.castle.level;

import co.castle.actor.Actor;
import co.castle.feature.Feature;
import co.castle.monster.Monster;
import sz.util.Position;

public class Emerger extends Actor
{
	private int counter;
	private Monster monster;
	private Feature mound;
	private Position point;

	public Emerger( Monster pMonster, Position point, int counter )
	{
		monster = pMonster;
		this.point = point;
		this.counter = counter;
	}

	public Emerger( Monster pMonster, Position point, int counter, Feature pMound )
	{
		mound = pMound;
		monster = pMonster;
		this.point = point;
		this.counter = counter;
	}

	public int getCounter( )
	{
		return counter;
	}

	public String getDescription( )
	{
		return "Emergie";
	}

	public Monster getMonster( )
	{
		return monster;
	}

	public Feature getMound( )
	{
		return mound;
	}

	public Position getPoint( )
	{
		return point;
	}
}