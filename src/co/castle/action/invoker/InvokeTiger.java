package co.castle.action.invoker;

public class InvokeTiger extends SummonSkill
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
		return "Invoke Tiger";
	}

	public String getMonsterID( )
	{
		return "S_TIGER";
	}

	public String getSFX( )
	{
		return "wav/tigerGrowl.wav";
	}
}
