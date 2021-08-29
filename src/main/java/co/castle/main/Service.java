package co.castle.main;

import co.castle.action.*;
import co.castle.action.monster.*;
import co.castle.action.monster.boss.MummyStrangle;
import co.castle.action.monster.boss.MummyTeleport;
import co.castle.action.monster.boss.Teleport;
import co.castle.ai.ActionSelector;
import co.castle.ai.SelectorFactory;
import co.castle.ai.monster.BasicMonsterAI;
import co.castle.ai.monster.RangedAI;
import co.castle.ai.monster.UnderwaterAI;
import co.castle.ai.monster.WanderToPlayerAI;
import co.castle.ai.npc.PriestAI;
import co.castle.ai.npc.VillagerAI;
import co.castle.ai.player.WildMorphAI;
import co.castle.conf.KeyBindings;
import co.castle.data.*;
import co.castle.feature.CountDown;
import co.castle.feature.FeatureFactory;
import co.castle.feature.SmartFeatureFactory;
import co.castle.feature.ai.BlastCrystalAI;
import co.castle.feature.ai.CrossAI;
import co.castle.feature.ai.FlameAI;
import co.castle.feature.ai.NullSelector;
import co.castle.game.*;
import co.castle.item.ItemFactory;
import co.castle.level.MapCellFactory;
import co.castle.monster.MonsterFactory;
import co.castle.npc.NPCDefinition;
import co.castle.npc.NPCFactory;
import co.castle.player.Player;
import co.castle.system.FileLoader;
import co.castle.ui.*;
import co.castle.ui.graphicsUI.GFXPlayerGenerator;
import co.castle.ui.graphicsUI.GFXUISelector;
import co.castle.ui.graphicsUI.GFXUserInterface;
import sz.midi.STMidiPlayer;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;
import java.util.Properties;

/**
 * Roles: Play all application sound as effect and music background.
 *
 * @implNote With keyword final, we be prevent the inheritance of this class.
 */
public final class Service extends MusicManager {
	private final static byte JCURSES_CONSOLE = 0;
	private final static byte SWING_GFX = 1;
	private final static byte SWING_CONSOLE = 2;

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

		System.out.println("Initializing Swing GFX System Interface");
		ApplicationGraphics appFrame = ApplicationGraphics.getInstance();

		PlayerGenerator.thus = new GFXPlayerGenerator(appFrame);


		ui = UserInterface.getUI();
		initializeUI(appFrame);

		System.out.println("Initializing Action Objects");
		initializeActions();
		initializeSelectors();
		System.out.println("Loading Data");
		initializeCells();
		initializeItems();

		try {
			initializeMonsters();
		} catch (CRLException e) {
			System.out.println("Faild to load monster configuration.");
			e.printStackTrace();
		}

		initializeNPCs();
		initializeFeatures();
		initializeSmartFeatures();

		// Load the file of configuration, this properties file have the path
		// to tracks of app.
		final Properties configurationFile = new Properties();

		try (var in = FileLoader.getInputStream("properties/configuration.properties")) {
			configurationFile.load(in);
		} catch (IOException exception) {
			System.err.println("Configuration file not found or error while loading the file.\n");
			exception.printStackTrace();
		}

		// NOTE: Move and Clear
		if (configurationFile.getProperty("enableSound", "false").equals("true")) {
			System.out.println("Initializing Midi Sequencer");
			try {
				STMidiPlayer.sequencer = MidiSystem.getSequencer();
				// STMidiPlayer.setVolume(0.1d);
				STMidiPlayer.sequencer.open();

			} catch (MidiUnavailableException mue) {
				Game.addReport("Midi device unavailable");
				System.out.println("Midi Device Unavailable");
				return;
			}
			System.out.println("Initializing Music Manager");
			addTracks(configurationFile);

			SFXManager.setEnabled(configurationFile.getProperty("enableSFX") != null
					&& configurationFile.getProperty("enableSFX").equals("true"));
		}

		Player.initializeWhips( "LEATHER_WHIP", "CHAIN_WHIP", "VKILLERW", "THORN_WHIP", "FLAME_WHIP",
				"LIT_WHIP" );
	}

	private void initializeUI(Object si) {
		Action walkAction = new Walk();
		Action jump = new Jump();
		Action thrown = new Throw();
		Action use = new Use();
		Action equip = new Equip();
		Action unequip = new Unequip();
		Action attack = new Attack();
		Action reload = new Reload();
		Action target = new TargetPS();
		Action switchWeapons = new SwitchWeapons();
		Action get = new Get();
		Action drop = new Drop();
		Action dive = new Dive();

		UserAction[] userActions;
		UserCommand[] userCommands;
		KeyBindings keyBindings = new KeyBindings();

		Display.thus.setKeyBindings(keyBindings);

		userActions = new UserAction[]
				{
						new UserAction(attack, keyBindings.getIntProperty("ATTACK1_KEY")),
						new UserAction(attack, keyBindings.getIntProperty("ATTACK2_KEY")),
						new UserAction(jump, keyBindings.getIntProperty("JUMP_KEY")),
						new UserAction(thrown, keyBindings.getIntProperty("THROW_KEY")),
						new UserAction(equip, keyBindings.getIntProperty("EQUIP_KEY")),
						new UserAction(unequip, keyBindings.getIntProperty("UNEQUIP_KEY")),
						new UserAction(reload, keyBindings.getIntProperty("RELOAD_KEY")),
						new UserAction(use, keyBindings.getIntProperty("USE_KEY")),
						new UserAction(get, keyBindings.getIntProperty("GET_KEY")),
						new UserAction(drop, keyBindings.getIntProperty("DROP_KEY")),
						new UserAction(dive, keyBindings.getIntProperty("DIVE_KEY")),
						new UserAction(target, keyBindings.getIntProperty("TARGET_KEY")),
						new UserAction(switchWeapons, keyBindings.getIntProperty("SWITCH_WEAPONS_KEY")),
						new UserAction(get, keyBindings.getIntProperty("GET2_KEY")),};

		userCommands = new UserCommand[]
				{
						new UserCommand(CommandListener.PROMPTQUIT, keyBindings.getIntProperty("QUIT_KEY")),
						new UserCommand(CommandListener.HELP, keyBindings.getIntProperty("HELP1_KEY")),
						new UserCommand(CommandListener.LOOK, keyBindings.getIntProperty("LOOK_KEY")),
						new UserCommand(CommandListener.PROMPTSAVE,
								keyBindings.getIntProperty("PROMPT_SAVE_KEY")),
						new UserCommand(CommandListener.SHOWSKILLS,
								keyBindings.getIntProperty("SHOW_SKILLS_KEY")),
						new UserCommand(CommandListener.HELP, keyBindings.getIntProperty("HELP2_KEY")),
						new UserCommand(CommandListener.SHOWINVEN,
								keyBindings.getIntProperty("SHOW_INVENTORY_KEY")),
						new UserCommand(CommandListener.SHOWSTATS,
								keyBindings.getIntProperty("SHOW_STATS_KEY")),
						new UserCommand(CommandListener.CHARDUMP, keyBindings.getIntProperty("CHARDUMP_KEY")),
						new UserCommand(CommandListener.SHOWMESSAGEHISTORY,
								keyBindings.getIntProperty("SHOW_MESSAGE_HISTORY_KEY")),
						new UserCommand(CommandListener.SHOWMAP, keyBindings.getIntProperty("SHOW_MAP_KEY")),
						new UserCommand(CommandListener.EXAMINELEVELMAP,
								keyBindings.getIntProperty("EXAMINE_LEVEL_MAP_KEY")),
						new UserCommand(CommandListener.SWITCHMUSIC,
								keyBindings.getIntProperty("SWITCH_MUSIC_KEY")),};

		((GFXUserInterface) ui).init((ApplicationGraphics) si, userCommands, target);
		uiSelector = new GFXUISelector();
		((GFXUISelector) uiSelector).init(userActions, walkAction, target, attack,
				(GFXUserInterface) ui, keyBindings);
	}

	private static void initializeActions( )
	{
		ActionFactory af = ActionFactory.getActionFactory( );
		Action[ ] definitions = new Action[ ]
		{	new Dash( ), new MonsterWalk( ), new Swim( ), new MonsterCharge( ), new MonsterMissile( ),
			new SummonMonster( ), new MummyStrangle( ), new MummyTeleport( ), new Teleport( ),
			new MandragoraScream( ) };
        for ( Action definition : definitions ) af.addDefinition( definition );
	}

	private static void initializeSelectors( )
	{
		ActionSelector[ ] definitions = getSelectorDefinitions( );
        for ( ActionSelector definition : definitions )
        {
            SelectorFactory.getSelectorFactory( ).addDefinition( definition );
		}
	}

	private static ActionSelector[ ] getSelectorDefinitions( )
	{
        return new ActionSelector[]
		{	new WanderToPlayerAI( ), new UnderwaterAI( ), new RangedAI( ), new FlameAI( ), new CrossAI( ),
			new BlastCrystalAI( ), new CountDown( ), new VillagerAI( ), new PriestAI( ), new NullSelector( ),
			new BasicMonsterAI( ), new WildMorphAI( ) };
	}

	private static void initializeCells( )
	{
		MapCellFactory.getMapCellFactory( )
				.init( Cells.getCellDefinitions( AppearanceFactory.getAppearanceFactory( ) ) );
	}

	private static void initializeItems( )
	{
		ItemFactory.getItemFactory( ).init( Items.getItemDefinitions( ) );
	}

	private static void initializeMonsters( ) throws CRLException
	{

		MonsterFactory.getFactory( )
				.init( MonsterLoader.getMonsterDefinitions( "data/monsters.ecsv", "data/monsters.exml" ) );
	}

	private static void initializeNPCs( )
	{
		NPCDefinition[ ] definitions = NPCs.getNPCDefinitions( );
		NPCFactory npcf = NPCFactory.getFactory( );
        for ( NPCDefinition definition : definitions )
        {
            npcf.addDefinition( definition );
		}
	}

	private static void initializeFeatures( )
	{
		FeatureFactory.getFactory( )
				.init( Features.getFeatureDefinitions( AppearanceFactory.getAppearanceFactory( ) ) );
	}

	private static void initializeSmartFeatures( )
	{
		SmartFeatureFactory.getFactory( )
				.init( SmartFeatures.getSmartFeatures( SelectorFactory.getSelectorFactory( ) ) );
	}
}
