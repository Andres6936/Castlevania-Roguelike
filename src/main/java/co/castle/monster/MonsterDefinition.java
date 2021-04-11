package co.castle.monster;

import co.castle.ai.ActionSelector;
import co.castle.ui.Appearance;

public class MonsterDefinition
{
	private /* transient */ Appearance appearance;
	private int attack = 1;
	private int attackCost = 50, walkCost = 50;
	private int autorespawnCount;
	/*
	 * Debe ser calculado en la UI private ListItem sightListItem;
	 */
	private int bloodContent;
	private boolean canFly;
	private boolean canSwim;
	private ActionSelector defaultSelector;
	private String description;
	private boolean ethereal; // Walks thru solid
	private int evadeChance;
	private String evadeMessage;
	private String ID;
	private int leaping; // Capability of moving to an higher cell
	private String longDescription;
	private int maxHits;
	private int minLevel, maxLevel;
	private int score;
	private int sightRange = 10;
	private boolean undead;
	private String wavOnHit;

	public MonsterDefinition( String pID )
	{
		// sightListItem = new BasicListItem(' ',0, "");
		ID = pID;
	}

	public Appearance getAppearance( )
	{
		return appearance;
	}

	public int getAttack( )
	{
		return attack;
	}

	public int getAttackCost( )
	{
		return attackCost;
	}

	public int getAutorespawnCount( )
	{
		return autorespawnCount;
	}

	public int getBloodContent( )
	{
		return bloodContent;
	}

	public ActionSelector getDefaultSelector( )
	{
		return defaultSelector;
	}

	public String getDescription( )
	{
		return description;
	}

	public int getEvadeChance( )
	{
		return evadeChance;
	}

	public String getEvadeMessage( )
	{
		return evadeMessage;
	}

	public String getID( )
	{
		return ID;
	}

	public int getLeaping( )
	{
		return leaping;
	}

	public String getLongDescription( )
	{
		return longDescription;
	}

	public int getMaxHits( )
	{
		return maxHits;
	}

	public int getMaxLevel( )
	{
		return maxLevel;
	}

	public int getMinLevel( )
	{
		return minLevel;
	}

	public int getScore( )
	{
		return score;
	}

	public int getSightRange( )
	{
		return sightRange;
	}

	public int getWalkCost( )
	{
		return walkCost;
	}

	public String getWavOnHit( )
	{
		return wavOnHit;
	}

	public boolean isBleedable( )
	{
		return getBloodContent( ) > 0;
	}

	public boolean isCanFly( )
	{
		return canFly;
	}

	public boolean isCanSwim( )
	{
		return canSwim;
	}

	public boolean isEthereal( )
	{
		return ethereal;
	}

	public boolean isUndead( )
	{
		return undead;
	}

	public void setAppearance( Appearance appearance )
	{
		this.appearance = appearance;
		/*
		 * if (appearance != null){
		 * ((BasicListItem)sightListItem).setIndex(appearance.getChar());
		 * ((BasicListItem)sightListItem).setIndexColor(appearance.getColor()); }
		 */
	}

	public void setAttack( int touchDamage )
	{
		this.attack = touchDamage;
	}

	public void setAttackCost( int attackCost )
	{
		this.attackCost = attackCost;
	}

	public void setAutorespawnCount( int autorespawnCount )
	{
		this.autorespawnCount = autorespawnCount;
	}

	public void setBloodContent( int bloodContent )
	{
		this.bloodContent = bloodContent;
	}

	public void setCanFly( boolean canFly )
	{
		this.canFly = canFly;
	}

	public void setCanSwim( boolean canSwim )
	{
		this.canSwim = canSwim;
	}

	public void setDefaultSelector( ActionSelector defaultSelector )
	{
		this.defaultSelector = defaultSelector;
	}

	public void setDescription( String description )
	{
		this.description = description;
		/*
		 * if (appearance != null)
		 * ((BasicListItem)sightListItem).setRow(getDescription());
		 */
	}

	public void setEthereal( boolean ethereal )
	{
		this.ethereal = ethereal;
	}

	/*
	 * public ListItem getSightListItem() { return sightListItem; } public void
	 * setSightListItem(ListItem sightListItem) { this.sightListItem =
	 * sightListItem; }
	 */

	public void setEvadeChance( int evadeChance )
	{
		this.evadeChance = evadeChance;
	}

	public void setEvadeMessage( String evadeMessage )
	{
		this.evadeMessage = evadeMessage;
	}

	public void setLeaping( int leaping )
	{
		this.leaping = leaping;
	}

	public void setLongDescription( String longDescription )
	{
		this.longDescription = longDescription;
	}

	public void setMaxHits( int maxHits )
	{
		this.maxHits = maxHits;
	}

	public void setMaxLevel( int maxLevel )
	{
		this.maxLevel = maxLevel;
	}

	public void setMinLevel( int minLevel )
	{
		this.minLevel = minLevel;
	}

	public void setScore( int score )
	{
		this.score = score;
	}

	public void setSightRange( int sightRange )
	{
		this.sightRange = sightRange;
	}

	public void setUndead( boolean undead )
	{
		this.undead = undead;
	}

	public void setWalkCost( int walkCost )
	{
		this.walkCost = walkCost;
	}

	public void setWavOnHit( String value )
	{
		wavOnHit = value;
	}

}