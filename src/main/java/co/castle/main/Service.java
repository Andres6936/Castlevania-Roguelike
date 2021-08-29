package co.castle.main;

import co.castle.conf.KeyBindings;
import co.castle.conf.UserActions;
import co.castle.conf.UserCommands;
import co.castle.game.PlayerGenerator;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserInterface;
import co.castle.ui.graphicsUI.GFXPlayerGenerator;
import co.castle.ui.graphicsUI.GFXUISelector;
import co.castle.ui.graphicsUI.GFXUserInterface;
import co.castle.ui.graphicsUI.GraphicsDisplay;

/**
 * Roles: Play all application sound as effect and music background.
 *
 * @implNote With keyword final, we be prevent the inheritance of this class.
 */
public final class Service {

	/**
	 * Class type Singleton, reference to only object
	 */
	private static Service instance;

	// NOTE: Change this defines and move, in resume: clear
	public final UserInterface userInterface;
	public final UISelector uiSelector;

	// We make the constructor private to prevent the use of 'new'
	private Service() {
		System.out.println("CastlevaniaRL");
		System.out.println("Slash ~ 2005-2010");
		System.out.println("Reading Configuration");

		ApplicationGraphics appFrame = ApplicationGraphics.getInstance();

		PlayerGenerator.thus = new GFXPlayerGenerator(appFrame);
		Display.thus = new GraphicsDisplay();


		userInterface = UserInterface.getUI();
		uiSelector = new GFXUISelector();
		initializeUI(appFrame);
	}

	/**
	 * @return Instance of Service
	 */
	public static Service getInstance() {
		return instance == null ? instance = new Service() : instance;
	}

	private void initializeUI(Object si) {
		KeyBindings keyBindings = new KeyBindings();
		Display.setKeyBindings(keyBindings);
		var userActions = new UserActions(keyBindings);
		var userCommands = new UserCommands(keyBindings);

		((GFXUserInterface) userInterface).init((ApplicationGraphics) si, userCommands, userActions);
		((GFXUISelector) uiSelector).init(userActions, (GFXUserInterface) userInterface, keyBindings);
	}

}
