package co.castle.player;

/**
 * This class represents damage taken in combat
 * 
 * @author Tuukka Turto
 */
public class Damage
{

	private int damage;
	private boolean ignoreArmor;

	/**
	 * Default constructor
	 * 
	 * @param damage
	 *            Amount of damage
	 * @param ignoreArmor
	 *            Does the damage ignore armor
	 */
	public Damage( int damage, boolean ignoreArmor )
	{
		this.damage = damage;
		this.ignoreArmor = ignoreArmor;
	}

	/**
	 * Boost damage
	 * 
	 * @param boost
	 *            amount to add to damage
	 */
	public void boostDamage( int boost )
	{
		assert boost >= 0;

		this.damage = this.damage + boost;
	}

	/**
	 * Amount of damage
	 * 
	 * @return amount of damage
	 */
	public int getDamage( )
	{
		return this.damage;
	}

	/**
	 * Does this damage ignore armor
	 * 
	 * @return true if damage ignores armor, otherwise false
	 */
	public boolean ignoresArmor( )
	{
		return this.ignoreArmor;
	}

	/**
	 * Reduce damage
	 * 
	 * @param reduction
	 *            amount to subtract from damage
	 */
	public void reduceDamage( int reduction )
	{
		assert reduction >= 0;

		this.damage = this.damage - reduction;
		if ( this.damage < 0 )
		{
			this.damage = 0;
		}
	}

	/**
	 * Set damage
	 * 
	 * @param damage
	 *            amount of damage
	 */
	public void setDamage( int damage )
	{
		this.damage = damage;
	}
}
