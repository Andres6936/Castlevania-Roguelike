package co.castle.action.invoker;

public class InvokeBird extends SummonSkill
{
	public int getHeartCost( )
	{
		return 5;
	}

	public int getHitBonus( )
	{
		return 2 * getPlayer( ).getSoulPower( );
	}

	public String getID( )
	{
		return "Invoke Bird";
	}

	public String getMonsterID( )
	{
		return "S_BIRD";
	}
}
