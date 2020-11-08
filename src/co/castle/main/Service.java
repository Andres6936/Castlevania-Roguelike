package co.castle.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import co.castle.action.Action;
import co.castle.action.ActionFactory;
import co.castle.action.Attack;
import co.castle.action.Dive;
import co.castle.action.Drop;
import co.castle.action.Equip;
import co.castle.action.Get;
import co.castle.action.Jump;
import co.castle.action.Reload;
import co.castle.action.SwitchWeapons;
import co.castle.action.TargetPS;
import co.castle.action.Throw;
import co.castle.action.Unequip;
import co.castle.action.Use;
import co.castle.action.Walk;
import co.castle.action.monster.Dash;
import co.castle.action.monster.MandragoraScream;
import co.castle.action.monster.MonsterCharge;
import co.castle.action.monster.MonsterMissile;
import co.castle.action.monster.MonsterWalk;
import co.castle.action.monster.SummonMonster;
import co.castle.action.monster.Swim;
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
import co.castle.conf.console.data.CharAppearances;
import co.castle.conf.console.data.CharCuts;
import co.castle.conf.console.data.CharEffects;
import co.castle.conf.gfx.data.GFXAppearances;
import co.castle.conf.gfx.data.GFXEffects;
import co.castle.data.Cells;
import co.castle.data.Features;
import co.castle.data.Items;
import co.castle.data.MonsterLoader;
import co.castle.data.NPCs;
import co.castle.data.SmartFeatures;
import co.castle.feature.CountDown;
import co.castle.feature.FeatureFactory;
import co.castle.feature.SmartFeatureFactory;
import co.castle.feature.ai.BlastCrystalAI;
import co.castle.feature.ai.CrossAI;
import co.castle.feature.ai.FlameAI;
import co.castle.feature.ai.NullSelector;
import co.castle.game.CRLException;
import co.castle.game.Game;
import co.castle.game.MusicManager;
import co.castle.game.PlayerGenerator;
import co.castle.game.SFXManager;
import co.castle.item.ItemFactory;
import co.castle.level.MapCellFactory;
import co.castle.monster.MonsterFactory;
import co.castle.npc.NPCDefinition;
import co.castle.npc.NPCFactory;
import co.castle.player.Player;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.CommandListener;
import co.castle.ui.Display;
import co.castle.ui.UISelector;
import co.castle.ui.UserAction;
import co.castle.ui.UserCommand;
import co.castle.ui.UserInterface;
import co.castle.ui.consoleUI.CharDisplay;
import co.castle.ui.consoleUI.CharPlayerGenerator;
import co.castle.ui.consoleUI.ConsoleUISelector;
import co.castle.ui.consoleUI.ConsoleUserInterface;
import co.castle.ui.consoleUI.effects.CharEffectFactory;
import co.castle.ui.effects.EffectFactory;
import co.castle.ui.graphicsUI.GraphicsDisplay;
import co.castle.ui.graphicsUI.GFXPlayerGenerator;
import co.castle.ui.graphicsUI.GFXUISelector;
import co.castle.ui.graphicsUI.GFXUserInterface;
import co.castle.ui.graphicsUI.effects.GFXEffectFactory;
import sz.csi.CharKey;
import sz.csi.ConsoleSystemInterface;
import sz.csi.jcurses.JCursesConsoleInterface;
import sz.csi.wswing.WSwingConsoleInterface;
import sz.midi.STMidiPlayer;

//With keyword final, we be prevent the inheritance of this class
public final class Service
{
	private final static byte JCURSES_CONSOLE = 0;
	private final static byte SWING_GFX = 1;
	private final static byte SWING_CONSOLE = 2;

	/**
	 * Class type Singleton, reference to only object
	 */
	private static Service instance;

	/**
	 * Play all application sound as effect and music background
	 */
	public static MusicManager musicManager = MusicManager.getInstance( );

	// We make the constructor private to prevent the use of 'new'
	private Service( )
	{

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

	public void start(final byte mode) {
		System.out.println("CastlevaniaRL");
		System.out.println("Slash ~ 2005-2010");
		System.out.println("Reading Configuration" );

		if ( mode == SWING_GFX )
		{
			System.out.println( "Initializing Swing GFX System Interface" );
			ApplicationGraphics appFrame = ApplicationGraphics.getInstance( );
			appFrame.start( );

			System.out.println( "Initializing Graphics Appearances" );
			initializeGAppearances( );

			System.out.println( "Initializing Swing GFX User Interface" );
			UserInterface.setSingleton( new GFXUserInterface( ) );

			Display.thus = new GraphicsDisplay( );
			PlayerGenerator.thus = new GFXPlayerGenerator( appFrame );

			EffectFactory.setSingleton( new GFXEffectFactory( ) );

			( (GFXEffectFactory) EffectFactory.getSingleton( ) )
					.setEffects( new GFXEffects( ).getEffects( ) );
			ui = UserInterface.getUI( );
			initializeUI( appFrame, mode );
		}
		else if ( mode == SWING_CONSOLE )
		{
			System.out.println("Initializing Char Appearances");
			initializeCAppearances();

			System.out.println("Initializing Swing Console System Interface");
			final ConsoleSystemInterface csi = new WSwingConsoleInterface();

			System.out.println("Initializing Console User Interface");
			UserInterface.setSingleton(new ConsoleUserInterface());

			CharCuts.initializeSingleton();
			Display.thus = new CharDisplay(csi);
			PlayerGenerator.thus = new CharPlayerGenerator(csi);
			// PlayerGenerator.thus.initSpecialPlayers();

			EffectFactory.setSingleton(new CharEffectFactory());

			((CharEffectFactory) EffectFactory.getSingleton())
					.setEffects(new CharEffects().getEffects());
			ui = UserInterface.getUI();
			initializeUI( csi, mode );
		}
		else if ( mode == JCURSES_CONSOLE ) {
			System.out.println("Initializing Char Appearances");
			initializeCAppearances();

			System.out.println("Initializing JCurses System Interface");
			ConsoleSystemInterface csi = new JCursesConsoleInterface();

			System.out.println("Initializing Console User Interface");
			UserInterface.setSingleton(new ConsoleUserInterface());

			CharCuts.initializeSingleton();
			Display.thus = new CharDisplay(csi);
			PlayerGenerator.thus = new CharPlayerGenerator(csi);
			// PlayerGenerator.thus.initSpecialPlayers();

			EffectFactory.setSingleton(new CharEffectFactory());

			( (CharEffectFactory) EffectFactory.getSingleton( ) )
					.setEffects( new CharEffects( ).getEffects( ) );
			ui = UserInterface.getUI( );
			initializeUI( csi, mode );
		}

		System.out.println( "Initializing Action Objects" );
		initializeActions( );
		initializeSelectors( );
		System.out.println( "Loading Data" );
		initializeCells( );
		initializeItems( );

		try
		{
			initializeMonsters( );
		} catch (CRLException e) {
			System.out.println("Faild to load monster configuration.");
			e.printStackTrace();
		}

		initializeNPCs();
		initializeFeatures();
		initializeSmartFeatures();

		final Properties configurationFile = new Properties();

		try (var in = new FileInputStream("properties/configuration.properties")) {
			configurationFile.load(in);
		} catch (IOException exception) {
			System.err.println("Configuration file not found or error while loading the file.\n");
		}

		// NOTE: Move and Clear
		if (configurationFile.getProperty("enableSound").equals("true")) {
			System.out.println("Initializing Midi Sequencer");
			try {
				STMidiPlayer.sequencer = MidiSystem.getSequencer();
				// STMidiPlayer.setVolume(0.1d);
				STMidiPlayer.sequencer.open();

			} catch (MidiUnavailableException mue) {
				Game.addReport("Midi device unavailable");
				System.out.println("Midi Device Unavailable");
				Service.musicManager.setEnabled(false);
				return;
			}
			System.out.println("Initializing Music Manager");

			Service.musicManager.addMusic(configurationFile);

			SFXManager.setEnabled(configurationFile.getProperty("enableSFX") != null
					&& configurationFile.getProperty("enableSFX").equals("true"));
		}
		else
		{
			musicManager.setEnabled( false );
		}

		Player.initializeWhips( "LEATHER_WHIP", "CHAIN_WHIP", "VKILLERW", "THORN_WHIP", "FLAME_WHIP",
				"LIT_WHIP" );
	}

	private static void initializeGAppearances( )
	{
		Appearance[ ] definitions = new GFXAppearances( ).getAppearances( );
        for ( Appearance definition : definitions )
        {
            AppearanceFactory.getAppearanceFactory( ).addDefinition( definition );
		}
	}

	private static void initializeCAppearances( )
	{
		Appearance[ ] definitions = new CharAppearances( ).getAppearances( );
        for ( Appearance definition : definitions )
        {
            AppearanceFactory.getAppearanceFactory( ).addDefinition( definition );
		}
	}

	private void initializeUI( Object si, final byte mode )
	{
		Action walkAction = new Walk( );
		Action jump = new Jump( );
		Action thrown = new Throw( );
		Action use = new Use( );
		Action equip = new Equip( );
		Action unequip = new Unequip( );
		Action attack = new Attack( );
		Action reload = new Reload( );
		Action target = new TargetPS( );
		Action switchWeapons = new SwitchWeapons( );
		Action get = new Get( );
		Action drop = new Drop( );
		Action dive = new Dive( );

        UserAction[] userActions;
        UserCommand[] userCommands;
        Properties keyBindings;
		try
		{
			Properties keyConfig = new Properties( );
			keyConfig.load( new FileInputStream( "keys.cfg" ) );

			keyBindings = new Properties( );
			keyBindings.put( "WEAPON_KEY", readKeyString( keyConfig, "weapon" ) );
			keyBindings.put( "DONOTHING1_KEY", readKeyString( keyConfig, "doNothing" ) );
			keyBindings.put( "DONOTHING2_KEY", readKeyString( keyConfig, "doNothing2" ) );
			keyBindings.put( "UP1_KEY", readKeyString( keyConfig, "up" ) );
			keyBindings.put( "UP2_KEY", readKeyString( keyConfig, "up2" ) );
			keyBindings.put( "LEFT1_KEY", readKeyString( keyConfig, "left" ) );
			keyBindings.put( "LEFT2_KEY", readKeyString( keyConfig, "left2" ) );
			keyBindings.put( "RIGHT1_KEY", readKeyString( keyConfig, "right" ) );
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
		catch ( FileNotFoundException e )
		{
			e.printStackTrace( );
			throw new RuntimeException( "keys.cfg config file not found" );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
			throw new RuntimeException( "Problem reading keys.cfg config file" );
		}

		switch ( mode )
		{
		case SWING_GFX:
			( (GFXUserInterface) ui ).init( (ApplicationGraphics) si, userCommands, target );
			uiSelector = new GFXUISelector( );
			( (GFXUISelector) uiSelector ).init( userActions, walkAction, target, attack,
					(GFXUserInterface) ui, keyBindings );
			break;
		case JCURSES_CONSOLE:
			( (ConsoleUserInterface) ui ).init( (ConsoleSystemInterface) si, userCommands, target );
			uiSelector = new ConsoleUISelector( );
			( (ConsoleUISelector) uiSelector ).init( (ConsoleSystemInterface) si, userActions, walkAction,
					target, attack, (ConsoleUserInterface) ui, keyBindings );
			break;
		case SWING_CONSOLE:
			// ((ConsoleUserInterface)ui).init((WSwingConsoleInterface)si, userActions,
			// userCommands, walkAction, target, attack);
			break;
		}
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
