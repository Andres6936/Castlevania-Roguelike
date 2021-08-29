package co.castle.monster;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.data.MonsterLoader;
import co.castle.game.CRLException;
import co.castle.level.Level;
import co.castle.levelgen.MonsterSpawnInfo;
import co.castle.ui.AppearanceFactory;
import sz.util.Debug;
import sz.util.Util;

public class MonsterFactory {
	private final Hashtable<String, MonsterDefinition> definitions = new Hashtable<>(40);

	private int lastSpawnLocation;
	private final Vector<MonsterDefinition> vDefinitions = new Vector<>(50);

	private final static MonsterFactory singleton = new MonsterFactory();

	public MonsterFactory() {
		try {
			for (MonsterDefinition def : MonsterLoader.getMonsterDefinitions()) {
				def.setAppearance(AppearanceFactory.getAppearanceFactory()
						.getAppearance(def.getID()));
				definitions.put(def.getID(), def);
				vDefinitions.add(def);

			}
		} catch (CRLException e) {
			System.err.println("Faild to load monster configuration.");
			e.printStackTrace();
		}
	}

	public static MonsterFactory getFactory( )
	{
		return singleton;
	}

	/*
	 * public void addDefinition(MonsterDefinition definition){
	 * definitions.put(definition.getID(), definition); }
	 */

	public Monster buildMonster( String id ) {
		return new Monster(definitions.get(id));
	}

	public MonsterDefinition getDefinition( String id ) {
		return definitions.get(id);
	}

	public int getLastSpawnPosition( )
	{
		return lastSpawnLocation;
	}

	public Monster getMonsterForLevel( Level level )
	{
		MonsterSpawnInfo[ ] spawnIDs = level.getSpawnInfo( );
		if ( spawnIDs == null || spawnIDs.length == 0 )
			return null;
		while ( true )
		{
			int rand = Util.rand( 0, spawnIDs.length - 1 );
			if ( Util.chance( spawnIDs[ rand ].getFrequency( ) ) ) {
				lastSpawnLocation = spawnIDs[rand].getSpawnLocation();
				return new Monster(definitions
						.get(spawnIDs[rand].getMonsterID()));
			}
		}
	}

	public void printAppearances( ) {
		Enumeration<String> x = definitions.keys();
		while (x.hasMoreElements()) {
			MonsterDefinition d = definitions.get(x.nextElement());
			Debug.say("Monstero " + d.getDescription() + " app " + d.getAppearance());
		}
	}
}