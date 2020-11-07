package co.castle.feature.action;

import co.castle.action.Action;

public class Shine extends Action {

	private static final Shine singleton = new Shine();

	public static Shine getAction() {
		return singleton;
	}

	public void execute() {
		// Level aLevel = performer.getLevel();
		// aLevel.addMessage("The holy flame glows!");
		// aLevel.addEffect(new StaticAnimEffect(performer.getPosition(), "\\|/|\\",
		// Appearance.YELLOW));
		// aLevel.addEffect(new StaticAnimEffect(performer.getPosition(), "�o�o",
		// Appearance.YELLOW));
	}

	public String getID( )
	{
		return "Shine";
	}
}