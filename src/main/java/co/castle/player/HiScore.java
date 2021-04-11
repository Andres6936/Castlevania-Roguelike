package co.castle.player;

public class HiScore
{
	private String date;
	private int deathLevel;
	private String deathString;
	private String name;
	private String playerClass;
	private int score;
	private String turns;

	public String getDate( )
	{
		return date;
	}

	public int getDeathLevel( )
	{
		return deathLevel;
	}

	public String getDeathString( )
	{
		return deathString;
	}

	public String getName( )
	{
		return name;
	}

	public String getPlayerClass( )
	{
		return playerClass;
	}

	public int getScore( )
	{
		return score;
	}

	public String getTurns( )
	{
		return turns;
	}

	public void setDate( String value )
	{
		date = value;
	}

	public void setDeathLevel( int deathLevel )
	{
		this.deathLevel = deathLevel;
	}

	public void setDeathString( String value )
	{
		deathString = value;
	}

	public void setName( String value )
	{
		name = value;
	}

	public void setPlayerClass( String playerClass )
	{
		this.playerClass = playerClass;
	}

	public void setScore( int value )
	{
		score = value;
	}

	public void setTurns( String value )
	{
		turns = value;
	}
}
