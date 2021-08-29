package co.castle.ai;

import java.util.Hashtable;

import co.castle.ai.monster.BasicMonsterAI;
import co.castle.ai.monster.RangedAI;
import co.castle.ai.monster.UnderwaterAI;
import co.castle.ai.monster.WanderToPlayerAI;
import co.castle.ai.npc.PriestAI;
import co.castle.ai.npc.VillagerAI;
import co.castle.ai.player.WildMorphAI;
import co.castle.feature.CountDown;
import co.castle.feature.ai.BlastCrystalAI;
import co.castle.feature.ai.CrossAI;
import co.castle.feature.ai.FlameAI;
import co.castle.feature.ai.NullSelector;
import sz.util.Debug;

public class SelectorFactory {
	private final Hashtable<String, ActionSelector> definitions = new Hashtable<>(40);
	private final static SelectorFactory singleton = new SelectorFactory();

	/*
	 * public ActionSelector buildSelector (String id){ Cell x = (Cell)
	 * definitions.get(id); return x.clone(); }
	 */

	public SelectorFactory() {
		System.out.println("Initializing Action Objects");
		var actionSelectors = new ActionSelector[]
				{new WanderToPlayerAI(), new UnderwaterAI(), new RangedAI(), new FlameAI(), new CrossAI(),
						new BlastCrystalAI(), new CountDown(), new VillagerAI(), new PriestAI(), new NullSelector(),
						new BasicMonsterAI(), new WildMorphAI()};
		for (ActionSelector actionSelector : actionSelectors) {
			addDefinition(actionSelector);
		}
	}

	public static SelectorFactory getSelectorFactory( )
	{
		return singleton;
	}

	public void addDefinition( ActionSelector definition )
	{
		definitions.put( definition.getID( ), definition );
	}

	public ActionSelector createSelector( String id ) {
		ActionSelector ret = definitions.get(id).derive();
		Debug.doAssert(ret != null, "Tried to create an invalid " + id
				+ " ActionSelector" + " " + this.toString());
		return ret;
	}

	public ActionSelector getSelector( String id ) {
		ActionSelector ret = definitions.get(id);
		Debug.doAssert(ret != null,
				"Tried to get an invalid " + id + " ActionSelector");
		return ret;
	}

}