package co.castle.game;

public class MonsterRecord
{
	private long killed, killers;
	private String monsterID;

	public long getKilled( )
	{
		return killed;
	}

	public long getKillers( )
	{
		return killers;
	}

	public String getMonsterID( )
	{
		return monsterID;
	}

	public void setKilled( long killed )
	{
		this.killed = killed;
	}

	public void setKillers( long killers )
	{
		this.killers = killers;
	}

	public void setMonsterID( String monsterID )
	{
		this.monsterID = monsterID;
	}
}
