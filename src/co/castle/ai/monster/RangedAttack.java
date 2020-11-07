package co.castle.ai.monster;

import co.castle.action.monster.MonsterMissile;

public class RangedAttack implements java.io.Serializable {
	private final String attackId;
	private final String attackMessage;
	private String attackType;
	private int chargeCounter;
	private final int damage;
	private final String effectID;
	private final String effectType;
	private String effectWav;
	private final int frequency;
	private final int range;
	private final String statusEffect;
	private String summonMonsterId;

	public RangedAttack(String pAttackId, String pAttackType, String pStatusEffect,
						int pRange, int pFrequency, String pAttackMessage,
						String pEffectType, String pEffectString, int pDamage) {
		attackId = pAttackId;
		attackType = pAttackType;
		if (attackType == null)
			attackType = MonsterMissile.TYPE_STRAIGHT;
		statusEffect = pStatusEffect;
		range = pRange;
		frequency = pFrequency;
		attackMessage = pAttackMessage;
		effectType = pEffectType;
		effectID = pEffectString;
		damage = pDamage;
	}

	public String getAttackId( )
	{
		return attackId;
	}

	public String getAttackMessage( )
	{
		return attackMessage;
	}

	public String getAttackType( )
	{
		return attackType;
	}

	public int getChargeCounter( )
	{
		return chargeCounter;
	}

	public int getDamage( )
	{
		return damage;
	}

	public String getEffectID( )
	{
		return effectID;
	}

	public String getEffectType( )
	{
		return effectType;
	}

	public String getEffectWav( )
	{
		return effectWav;
	}

	public int getFrequency( )
	{
		return frequency;
	}

	public int getRange( )
	{
		return range;
	}

	public String getStatusEffect( )
	{
		return statusEffect;
	}

	public String getSummonMonsterId( )
	{
		return summonMonsterId;
	}

	public void setChargeCounter( int chargeCounter )
	{
		this.chargeCounter = chargeCounter;
	}

	public void setEffectWav( String value )
	{
		effectWav = value;
	}

	public void setSummonMonsterId( String summonMonsterId )
	{
		this.summonMonsterId = summonMonsterId;
	}
}
