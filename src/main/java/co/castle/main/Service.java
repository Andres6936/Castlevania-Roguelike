package co.castle.main;

import co.castle.conf.KeyBindings;
import co.castle.conf.UserActions;
import co.castle.conf.UserCommands;
import co.castle.data.*;
import co.castle.feature.FeatureFactory;
import co.castle.feature.SmartFeatureFactory;
import co.castle.game.CRLException;
import co.castle.game.PlayerGenerator;
import co.castle.item.ItemFactory;
import co.castle.level.MapCellFactory;
import co.castle.monster.MonsterFactory;
import co.castle.npc.NPCDefinition;
import co.castle.npc.NPCFactory;
import co.castle.player.Player;
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

	// We make the constructor private to prevent the use of 'new'
	private Service() {
		super();
	}

	/**
	 * @return Instance of Service
	 */
	public static Service getInstance() {
		return instance == null ? instance = new Service() : instance;
	}

	// NOTE: Change this defines and move, in resume: clear
	public UserInterface ui;
	public UISelector uiSelector;

	public void start() {
		System.out.println("CastlevaniaRL");
		System.out.println("Slash ~ 2005-2010");
		System.out.println("Reading Configuration");

		ApplicationGraphics appFrame = ApplicationGraphics.getInstance();

		PlayerGenerator.thus = new GFXPlayerGenerator(appFrame);
		Display.thus = new GraphicsDisplay();


		ui = UserInterface.getUI();
		initializeUI(appFrame);

		System.out.println("Loading Data");
		MapCellFactory.getMapCellFactory().init(Cells.getCellDefinitions());
		ItemFactory.getItemFactory().init(Items.getItemDefinitions());

		try {
			MonsterFactory.getFactory().init(MonsterLoader.getMonsterDefinitions());
		} catch (CRLException e) {
			System.out.println("Faild to load monster configuration.");
			e.printStackTrace();
		}

		for (NPCDefinition definition : NPCs.getNPCDefinitions()) {
			NPCFactory.getFactory().addDefinition(definition);
		}
		FeatureFactory.getFactory().init(Features.getFeatureDefinitions());
		SmartFeatureFactory.getFactory().init(SmartFeatures.getSmartFeatures());

		Player.initializeWhips("LEATHER_WHIP", "CHAIN_WHIP", "VKILLERW", "THORN_WHIP", "FLAME_WHIP",
				"LIT_WHIP");
	}

	private void initializeUI(Object si) {
		KeyBindings keyBindings = new KeyBindings();
		Display.thus.setKeyBindings(keyBindings);
		var userActions = new UserActions(keyBindings);
		var userCommands = new UserCommands(keyBindings);

		((GFXUserInterface) ui).init((ApplicationGraphics) si, userCommands, userActions);
		uiSelector = new GFXUISelector();
		((GFXUISelector) uiSelector).init(userActions, (GFXUserInterface) ui, keyBindings);
	}

}
