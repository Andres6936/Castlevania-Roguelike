package co.castle.levelgen;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class LevelMetaData implements Serializable
{
	private final Hashtable<String, String> hexits = new Hashtable<>();
	private String levelID;
	private int levelNumber = -1;

	public void addExits( String exit, String exitID )
	{
		hexits.put( exitID, exit );
	}

	public String getExit( String exitID )
	{
		return hexits.get(exitID);
	}

	public String getLevelID( )
	{
		return levelID;
	}

	public int getLevelNumber( )
	{
		return levelNumber;
	}

	public void setLevelID( String levelID )
	{
		this.levelID = levelID;
	}

	public void setLevelNumber( int levelNumber )
	{
		this.levelNumber = levelNumber;
	}
}
