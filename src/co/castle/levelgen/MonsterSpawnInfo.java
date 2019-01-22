package co.castle.levelgen;

import java.io.Serializable;

public class MonsterSpawnInfo implements Serializable
{
	private int frequency;
	private String monsterID;
	private int spawnLocation;

	public final static int UNDERGROUND = 0, BORDER = 1, WATER = 2;

	public MonsterSpawnInfo( String pMonsterID, int pSpawnLocation, int pFrequency )
	{
		monsterID = pMonsterID;
		spawnLocation = pSpawnLocation;
		frequency = pFrequency;
	}

	public int getFrequency( )
	{
		return frequency;
	}

	public String getMonsterID( )
	{
		return monsterID;
	}

	public int getSpawnLocation( )
	{
		return spawnLocation;
	}
}