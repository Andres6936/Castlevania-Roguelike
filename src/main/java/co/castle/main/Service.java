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
import co.castle.conf.gfx.data.GFXAppearances;
import co.castle.conf.gfx.data.GFXEffects;
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
import co.castle.ui.effects.EffectFactory;
import co.castle.ui.graphicsUI.GFXPlayerGenerator;
import co.castle.ui.graphicsUI.GFXUISelector;
import co.castle.ui.graphicsUI.GFXUserInterface;
import co.castle.ui.graphicsUI.GraphicsDisplay;
import co.castle.ui.graphicsUI.effects.GFXEffectFactory;
import sz.csi.CharKey;
import sz.midi.STMidiPlayer;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
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
		Action switchWeapons = new SwitchWeapons( );
		Action get = new Get( );
		Action drop = new Drop( );
		Action dive = new Dive( );

        UserAction[] userActions;
        UserCommand[] userCommands;
        Properties keyBindings;
		try {
			Properties keyConfig = new Properties();
			keyConfig.load(FileLoader.getInputStream("keys.cfg"));

			keyBindings = new Properties();
			keyBindings.put("WEAPON_KEY", readKeyString(keyConfig, "weapon"));
			keyBindings.put("DONOTHING1_KEY", readKeyString(keyConfig, "doNothing"));
			keyBindings.put("DONOTHING2_KEY", readKeyString(keyConfig, "doNothing2"));
			keyBindings.put("UP1_KEY", readKeyString(keyConfig, "up"));
			keyBindings.put("UP2_KEY", readKeyString(keyConfig, "up2"));
			keyBindings.put("LEFT1_KEY", readKeyString(keyConfig, "left"));
			keyBindings.put("LEFT2_KEY", readKeyString(keyConfig, "left2"));
			keyBindings.put("RIGHT1_KEY", readKeyString(keyConfig, "right"));
			keyBindings.put( "RIGHT2_KEY", readKeyString( keyConfig, "right2" ) );
			keyBindings.put( "DOWN1_KEY", readKeyString( keyConfig, "down" ) );
			keyBindings.put( "DOWN2_KEY", readKeyString( keyConfig, "down2" ) );
			keyBindings.put( "UPRIGHT1_KEY", readKeyString( keyConfig, "upRight" ) );
			keyBindings.put( "UPRIGHT2_KEY", readKeyString( keyConfig, "upRight2" ) );
			keyBindings.put( "UPLEFT1_KEY", readKeyString( keyConfig, "upLeft" ) );
			keyBindings.put( "UPLEFT2_KEY", readKeyString( keyConfig, "upLeft2" ) );
			keyBindings.put( "DOWNLEFT1_KEY", readKeyString( keyConfig, "downLeft" ) );
			keyBindings.put( "DOWNLEFT2_KEY", readKeyString( keyConfig, "downLeft2" ) );
			keyBindings.put( "DOWNRIGHT1_KEY", readKeyString( keyConfig, "downRight" ) );
			keyBindings.put( "DOWNRIGHT2_KEY", readKeyString( keyConfig, "downRight2" ) );
			keyBindings.put( "SELF1_KEY", readKeyString( keyConfig, "self" ) );
			keyBindings.put( "SELF2_KEY", readKeyString( keyConfig, "self2" ) );
			keyBindings.put( "ATTACK1_KEY", readKeyString( keyConfig, "attack1" ) );
			keyBindings.put( "ATTACK2_KEY", readKeyString( keyConfig, "attack2" ) );
			keyBindings.put( "JUMP_KEY", readKeyString( keyConfig, "jump" ) );
			keyBindings.put( "THROW_KEY", readKeyString( keyConfig, "throw" ) );
			keyBindings.put( "EQUIP_KEY", readKeyString( keyConfig, "equip" ) );
			keyBindings.put( "UNEQUIP_KEY", readKeyString( keyConfig, "unequip" ) );
			keyBindings.put( "RELOAD_KEY", readKeyString( keyConfig, "reload" ) );
			keyBindings.put( "USE_KEY", readKeyString( keyConfig, "use" ) );
			keyBindings.put( "GET_KEY", readKeyString( keyConfig, "get" ) );
			keyBindings.put( "GET2_KEY", readKeyString( keyConfig, "get2" ) );
			keyBindings.put( "DROP_KEY", readKeyString( keyConfig, "drop" ) );
			keyBindings.put( "DIVE_KEY", readKeyString( keyConfig, "dive" ) );
			keyBindings.put( "TARGET_KEY", readKeyString( keyConfig, "target" ) );
			keyBindings.put( "SWITCH_WEAPONS_KEY", readKeyString( keyConfig, "switchWeapons" ) );
			keyBindings.put( "QUIT_KEY", readKeyString( keyConfig, "PROMPTQUIT" ) );
			keyBindings.put( "HELP1_KEY", readKeyString( keyConfig, "HELP1" ) );
			keyBindings.put( "HELP2_KEY", readKeyString( keyConfig, "HELP2" ) );
			keyBindings.put( "LOOK_KEY", readKeyString( keyConfig, "LOOK" ) );
			keyBindings.put( "PROMPT_SAVE_KEY", readKeyString( keyConfig, "PROMPTSAVE" ) );
			keyBindings.put( "SHOW_SKILLS_KEY", readKeyString( keyConfig, "SHOWSKILLS" ) );
			keyBindings.put( "SHOW_INVENTORY_KEY", readKeyString( keyConfig, "SHOWINVEN" ) );
			keyBindings.put( "SHOW_STATS_KEY", readKeyString( keyConfig, "SHOWSTATS" ) );
			keyBindings.put( "CHARDUMP_KEY", readKeyString( keyConfig, "CHARDUMP" ) );
			keyBindings.put( "SHOW_MESSAGE_HISTORY_KEY", readKeyString( keyConfig, "SHOWMESSAGEHISTORY" ) );
			keyBindings.put( "SHOW_MAP_KEY", readKeyString( keyConfig, "SHOWMAP" ) );
			keyBindings.put( "EXAMINE_LEVEL_MAP_KEY", readKeyString( keyConfig, "EXAMINELEVELMAP" ) );
			keyBindings.put( "SWITCH_MUSIC_KEY", readKeyString( keyConfig, "SWITCHMUSIC" ) );

			Display.thus.setKeyBindings( keyBindings );

			userActions = new UserAction[ ]
			{	new UserAction( attack, i( keyBindings.getProperty( "ATTACK1_KEY" ) ) ),
				new UserAction( attack, i( keyBindings.getProperty( "ATTACK2_KEY" ) ) ),
				new UserAction( jump, i( keyBindings.getProperty( "JUMP_KEY" ) ) ),
				new UserAction( thrown, i( keyBindings.getProperty( "THROW_KEY" ) ) ),
				new UserAction( equip, i( keyBindings.getProperty( "EQUIP_KEY" ) ) ),
				new UserAction( unequip, i( keyBindings.getProperty( "UNEQUIP_KEY" ) ) ),
				new UserAction( reload, i( keyBindings.getProperty( "RELOAD_KEY" ) ) ),
				new UserAction( use, i( keyBindings.getProperty( "USE_KEY" ) ) ),
				new UserAction( get, i( keyBindings.getProperty( "GET_KEY" ) ) ),
				new UserAction( drop, i( keyBindings.getProperty( "DROP_KEY" ) ) ),
				new UserAction( dive, i( keyBindings.getProperty( "DIVE_KEY" ) ) ),
				new UserAction( target, i( keyBindings.getProperty( "TARGET_KEY" ) ) ),
				new UserAction( switchWeapons, i( keyBindings.getProperty( "SWITCH_WEAPONS_KEY" ) ) ),
				new UserAction( get, i( keyBindings.getProperty( "GET2_KEY" ) ) ), };

			userCommands = new UserCommand[ ]
			{	new UserCommand( CommandListener.PROMPTQUIT, i( keyBindings.getProperty( "QUIT_KEY" ) ) ),
				new UserCommand( CommandListener.HELP, i( keyBindings.getProperty( "HELP1_KEY" ) ) ),
				new UserCommand( CommandListener.LOOK, i( keyBindings.getProperty( "LOOK_KEY" ) ) ),
				new UserCommand( CommandListener.PROMPTSAVE,
						i( keyBindings.getProperty( "PROMPT_SAVE_KEY" ) ) ),
				new UserCommand( CommandListener.SHOWSKILLS,
						i( keyBindings.getProperty( "SHOW_SKILLS_KEY" ) ) ),
				new UserCommand( CommandListener.HELP, i( keyBindings.getProperty( "HELP2_KEY" ) ) ),
				new UserCommand( CommandListener.SHOWINVEN,
						i( keyBindings.getProperty( "SHOW_INVENTORY_KEY" ) ) ),
				new UserCommand( CommandListener.SHOWSTATS,
						i( keyBindings.getProperty( "SHOW_STATS_KEY" ) ) ),
				new UserCommand( CommandListener.CHARDUMP, i( keyBindings.getProperty( "CHARDUMP_KEY" ) ) ),
				new UserCommand( CommandListener.SHOWMESSAGEHISTORY,
						i( keyBindings.getProperty( "SHOW_MESSAGE_HISTORY_KEY" ) ) ),
				new UserCommand( CommandListener.SHOWMAP, i( keyBindings.getProperty( "SHOW_MAP_KEY" ) ) ),
				new UserCommand( CommandListener.EXAMINELEVELMAP,
						i( keyBindings.getProperty( "EXAMINE_LEVEL_MAP_KEY" ) ) ),
				new UserCommand( CommandListener.SWITCHMUSIC,
						i( keyBindings.getProperty( "SWITCH_MUSIC_KEY" ) ) ), };

		}
		catch ( FileNotFoundException e ) {
			e.printStackTrace();
			throw new RuntimeException("keys.cfg config file not found");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problem reading keys.cfg config file");
		}

		((GFXUserInterface) ui).init((ApplicationGraphics) si, userCommands, target);
		uiSelector = new GFXUISelector();
		((GFXUISelector) uiSelector).init(userActions, walkAction, target, attack,
				(GFXUserInterface) ui, keyBindings);
	}

	private static String readKeyString( Properties config, String keyName )
	{
		return readKey( config, keyName ) + "";
	}

	private static int readKey( Properties config, String keyName )
	{
		String fieldName = config.getProperty( keyName ).trim( );
		if ( fieldName == null )
			throw new RuntimeException( "Invalid key.cfg file, property not found: " + keyName );
		try
		{
			Field field = CharKey.class.getField( fieldName );
			return field.getInt( CharKey.class );
		}
        catch ( SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e )
		{
			e.printStackTrace( );
			throw new RuntimeException( "Error reading field : " + fieldName );
		}
	}

	private static int i( String s )
	{
		return Integer.parseInt( s );
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
