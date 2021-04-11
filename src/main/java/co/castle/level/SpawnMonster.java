package co.castle.level;

import co.castle.action.Action;
import sz.util.Util;

public class SpawnMonster extends Action {
	private static final SpawnMonster singleton = new SpawnMonster();

	public static SpawnMonster getAction() {
		return singleton;
	}

	public void execute() {
		Level level = performer.getLevel();
		Respawner perf = (Respawner) performer;
		if ( Util.chance( perf.getProb( ) ) )
			level.respawn( );
	}

	public String getID( )
	{
		return "SpawnMonster";
	}
}