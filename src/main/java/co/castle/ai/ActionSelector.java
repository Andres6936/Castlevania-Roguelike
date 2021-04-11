package co.castle.ai;

import co.castle.action.Action;
import co.castle.actor.Actor;

public interface ActionSelector extends Cloneable, java.io.Serializable {
	ActionSelector derive();

	String getID();

	Action selectAction(Actor who);

}