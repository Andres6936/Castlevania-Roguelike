package co.castle.item;

import java.io.Serializable;

public class Modifier implements Serializable
{
	private int atkBonus;
	private int atkCostBonus;
	private int chance;

	private int defenseBonus;

	private String description;

	private boolean harmsUndead;

	private String id;

	private double priceModifier;

	private int rangeBonus;
	private boolean slicesThru;
	public Modifier( String ID, String description, int chance )
	{
		this.id = ID;
		this.description = description;
		this.chance = chance;
	}
	public int getAtkBonus( )
	{
		return atkBonus;
	}
	public int getAtkCostBonus( )
	{
		return atkCostBonus;
	}
	public int getChance( )
	{
		return chance;
	}
	public int getDefenseBonus( )
	{
		return defenseBonus;
	}

	public String getDescription( )
	{
		return description;
	}

	public String getID( )
	{
		return id;
	}

	public double getPriceModifier( )
	{
		return priceModifier;
	}

	public int getRangeBonus( )
	{
		return rangeBonus;
	}

	public boolean isHarmsUndead( )
	{
		return harmsUndead;
	}

	public boolean isSlicesThru( )
	{
		return slicesThru;
	}

	public void setAtkBonus( int atkBonus )
	{
		this.atkBonus = atkBonus;
	}

	public void setAtkCostBonus( int atkCostBonus )
	{
		this.atkCostBonus = atkCostBonus;
	}

	public void setChance( int chance )
	{
		this.chance = chance;
	}

	public void setDefenseBonus( int defenseBonus )
	{
		this.defenseBonus = defenseBonus;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public void setHarmsUndead( boolean harmsUndead )
	{
		this.harmsUndead = harmsUndead;
	}

	public void setPriceModifier( double priceModifier )
	{
		this.priceModifier = priceModifier;
	}

	public void setRangeBonus( int rangeBonus )
	{
		this.rangeBonus = rangeBonus;
	}

	public void setSlicesThru( boolean slicesThru )
	{
		this.slicesThru = slicesThru;
	}
}
