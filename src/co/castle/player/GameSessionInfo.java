package co.castle.player;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.monster.Monster;
import co.castle.monster.MonsterDefinition;

public class GameSessionInfo implements Serializable
{
	private int deathCause = -1;
	private Hashtable deathCount = new Hashtable( );
	private int deathLevel;
	private String deathLevelDescription;
	private long goldCount;
	private Vector history = new Vector( );
	private Monster killerMonster;

	private Player player;

	private int turns;

	public final static int KILLED = 0, DROWNED = 1, QUIT = 2, SMASHED = 3,
			STRANGLED_BY_ARMOR = 4, BURNED_BY_LAVA = 5, ASCENDED = 6, ENDLESS_PIT = 7,
			POISONED_TO_DEATH = 8;

	public void addDeath( MonsterDefinition who )
	{
		MonsterDeath md = (MonsterDeath) deathCount.get( who.getID( ) );
		if ( md == null )
			deathCount.put( who.getID( ), new MonsterDeath( who.getDescription( ) ) );
		else
		{
			md.increaseDeaths( );
		}
	}

	public void addGold( int val )
	{
		goldCount += val;
	}

	public void addHistoryItem( String desc )
	{
		history.add( desc );
	}

	public Hashtable getDeathCount( )
	{
		return deathCount;
	}

	public int getDeathCountFor( Monster who )
	{
		MonsterDeath md = (MonsterDeath) deathCount.get( who.getID( ) );
		if ( md == null )
			return 0;
		else
			return md.getTimes( );
	}

	/*
	 * public Monster getKillerMonster() { return killerMonster; }
	 */

	public int getDeathLevel( )
	{
		return deathLevel;
	}

	public String getDeathLevelDescription( )
	{
		return deathLevelDescription;
	}

	public String getDeathString( )
	{
		switch ( deathCause )
		{
		case KILLED:
			return "was killed by a " + killerMonster.getDescription( );
		case DROWNED:
			return "drowned";
		case QUIT:
			return "ran away with tears on his eyes";
		case STRANGLED_BY_ARMOR:
			return "was stranged inside an armor";
		case ASCENDED:
			return "vanquished Demon Dracula and freed the world from darkness... for now...";
		case ENDLESS_PIT:
			return "fell into an endless pit";
		case POISONED_TO_DEATH:
			return "poisoned to the death";
		}
		return "perished...";
	}

	public long getGoldCount( )
	{
		return goldCount;
	}

	public Vector getHistory( )
	{
		return history;
	}

	public Monster getKillerMonster( )
	{
		return killerMonster;
	}

	public Player getPlayer( )
	{
		return player;
	}

	public String getShortDeathString( )
	{
		switch ( deathCause )
		{
		case KILLED:
			return "Killed by a " + killerMonster.getDescription( );
		case DROWNED:
			return "Drowned";
		case SMASHED:
			return "Smashed";
		case QUIT:
			return "Ran away crying";
		case STRANGLED_BY_ARMOR:
			return "Strangled inside an armor";
		case BURNED_BY_LAVA:
			return "Burned to the death by hot lava";
		case ASCENDED:
			return "Vanquished Demon Dracula";
		case ENDLESS_PIT:
			return "Fell into an endless pit";
		case POISONED_TO_DEATH:
			return "Poisoned to the death";
		}
		return "Perished...";
	}

	public int getTotalDeathCount( )
	{
		Enumeration x = deathCount.elements( );
		int acum = 0;
		while ( x.hasMoreElements( ) )
			acum += ( (MonsterDeath) x.nextElement( ) ).getTimes( );
		return acum;
	}

	public int getTurns( )
	{
		return turns;

	}

	public void increaseTurns( )
	{
		turns++;
	}

	public void setDeathCause( int value )
	{
		deathCause = value;
	}

	public void setDeathLevel( int deathLevel )
	{
		this.deathLevel = deathLevel;
	}

	public void setDeathLevelDescription( String deathLevelDescription )
	{
		this.deathLevelDescription = deathLevelDescription;
	}

	public void setKillerMonster( Monster value )
	{
		killerMonster = value;
	}

	public void setPlayer( Player value )
	{
		player = value;
	}
}