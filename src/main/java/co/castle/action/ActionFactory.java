package co.castle.action;

import java.util.Hashtable;

import co.castle.action.monster.*;
import co.castle.action.monster.boss.MummyStrangle;
import co.castle.action.monster.boss.MummyTeleport;
import co.castle.action.monster.boss.Teleport;
import sz.util.Debug;

public class ActionFactory {
    private final Hashtable<String, Action> definitions = new Hashtable<>(20);
    private final static ActionFactory singleton = new ActionFactory();

    public ActionFactory() {
        Action[] definitions = new Action[]
                {
                        new Dash(), new MonsterWalk(), new Swim(), new MonsterCharge(), new MonsterMissile(),
                        new SummonMonster(), new MummyStrangle(), new MummyTeleport(), new Teleport(),
                        new MandragoraScream()
                };
        for (Action definition : definitions) {
            addDefinition(definition);
        }
    }

    public static ActionFactory getActionFactory() {
        return singleton;
    }

    public void addDefinition(Action definition) {
        definitions.put(definition.getID(), definition);
    }

    public Action getAction(String id) {
        Action ret = definitions.get(id);
        Debug.doAssert(ret != null, "Tried to get an invalid " + id + " Action");
        return ret;
    }

}
