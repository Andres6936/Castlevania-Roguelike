package co.castle.action.invoker;

public class InvokeEagle extends SummonSkill
{
	public int getHeartCost( )
	{
		return 5;
	}

	public int getHitBonus( )
	{
		return 3 * getPlayer( ).getSoulPower( );
	}

	public String getID( )
	{
		return "Invoke Eagle";
	}

	public String getMonsterID( )
	{
		return "S_EAGLE";
	}
}
