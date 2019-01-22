package co.castle.conf.console.data;

import java.util.Vector;

import co.castle.ui.consoleUI.effects.CharAnimatedMissileEffect;
import co.castle.ui.consoleUI.effects.CharBeamMissileEffect;
import co.castle.ui.consoleUI.effects.CharDirectionalMissileEffect;
import co.castle.ui.consoleUI.effects.CharEffect;
import co.castle.ui.consoleUI.effects.CharFlashEffect;
import co.castle.ui.consoleUI.effects.CharIconEffect;
import co.castle.ui.consoleUI.effects.CharIconMissileEffect;
import co.castle.ui.consoleUI.effects.CharMeleeEffect;
import co.castle.ui.consoleUI.effects.CharSequentialEffect;
import co.castle.ui.consoleUI.effects.CharSplashEffect;
import sz.csi.ConsoleSystemInterface;
import sz.util.Position;

public class CharEffects
{
	private CharEffect[ ] effects = new CharEffect[ ]
	{
		// Animated Missile Effects
		new CharAnimatedMissileEffect( "SFX_FIREBALL", "*~", ConsoleSystemInterface.RED,
				50 ),
		new CharAnimatedMissileEffect( "SFX_FLAMESSHOOT", "*~",
				ConsoleSystemInterface.RED, 50 ),
		new CharAnimatedMissileEffect( "SFX_SUMMON_SPIRIT", "sS",
				ConsoleSystemInterface.GRAY, 45 ),
		new CharAnimatedMissileEffect( "SFX_CHARGE_BALL", "*~",
				ConsoleSystemInterface.RED, 50 ),
		new CharAnimatedMissileEffect( "SFX_LIT_SPELL", "*.",
				ConsoleSystemInterface.YELLOW, 55 ),
		new CharAnimatedMissileEffect( "SFX_AXE", "/-\\|", ConsoleSystemInterface.WHITE,
				50 ),
		new CharAnimatedMissileEffect( "SFX_CROSS", "+x", ConsoleSystemInterface.CYAN,
				70 ),
		new CharAnimatedMissileEffect( "SFX_SHADOW_FLARE", "OX",
				ConsoleSystemInterface.RED, 20 ),
		new CharAnimatedMissileEffect( "SFX_HELLFIRE", "OX", ConsoleSystemInterface.RED,
				20 ),
		// En Target.java : 60 "SFX_"+weaponDef.getID()
		// En MonsterMissile.java : 48 "SFX_MONSTER_ID_"+aMonster.getID()

		// Directional Missile Effects
		new CharDirectionalMissileEffect( "SFX_SOUL_STEAL", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 40 ),
		new CharDirectionalMissileEffect( "SFX_TELEPORT", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 40 ),
		new CharDirectionalMissileEffect( "SFX_SHADETELEPORT", "\\|/--/|\\",
				ConsoleSystemInterface.GRAY, 40 ),
		new CharDirectionalMissileEffect( "SFX_SLEEP_SPELL", "\\|/--/|\\",
				ConsoleSystemInterface.PURPLE, 40 ),
		new CharDirectionalMissileEffect( "SFX_WHITE_DAGGER", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 10 ),
		new CharDirectionalMissileEffect( "SFX_SILVER_DAGGER", "\\|/--/|\\",
				ConsoleSystemInterface.CYAN, 10 ),
		new CharDirectionalMissileEffect( "SFX_GOLD_DAGGER", "\\|/--/|\\",
				ConsoleSystemInterface.YELLOW, 10 ),

		new CharDirectionalMissileEffect( "SFX_SOULSSTRIKE", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 40 ),

		// En MonsterMissile.java : 51 "SFX_MONSTER_ID_"+aMonster.getID()

		// Beam Effects
		new CharBeamMissileEffect( "SFX_FLAME_SPELL", "Oo.o", ConsoleSystemInterface.RED,
				46 ),
		new CharBeamMissileEffect( "SFX_ICE_SPELL", "X*.*", ConsoleSystemInterface.CYAN,
				46 ),
		new CharBeamMissileEffect( "SFX_ENERGY_BEAM", "Oo.o",
				ConsoleSystemInterface.PURPLE, 55 ),

		new CharBeamMissileEffect( "SFX_ITEMBREAKBIBLE", "OOOO",
				ConsoleSystemInterface.CYAN, 55 ),

		// En MonsterMissile.java : 42 "SFX_MONSTER_ID_"+aMonster.getID()

		// Splash Effects
		new CharSplashEffect( "SFX_EGG_BLAST", "O.*", ConsoleSystemInterface.WHITE, 20 ),
		new CharSplashEffect( "SFX_DOVE_BLAST", "Bbb.", ConsoleSystemInterface.WHITE,
				20 ),
		new CharSplashEffect( "SFX_MATERIALIZE", "Oo.", ConsoleSystemInterface.WHITE,
				60 ),
		new CharSplashEffect( "SFX_SHADOW_APOCALYPSE", "Oo*'.",
				ConsoleSystemInterface.CYAN, 60 ),
		new CharSplashEffect( "SFX_SHADOW_EXTINCTION", "o*'.", ConsoleSystemInterface.RED,
				60 ),
		new CharSplashEffect( "SFX_KILL_CHRIS", "Oo*'.", ConsoleSystemInterface.MAGENTA,
				180 ),
		new CharSplashEffect( "SFX_MORPH_SOLIEYU", "Oo*'.", ConsoleSystemInterface.GRAY,
				180 ),
		new CharSplashEffect( "SFX_VANISH", ".oO", ConsoleSystemInterface.WHITE, 50 ),
		new CharSplashEffect( "SFX_HOLY_FLAME", "*~,", ConsoleSystemInterface.YELLOW,
				20 ),
		new CharSplashEffect( "SFX_MANDRAGORA_SCREAM", "............",
				ConsoleSystemInterface.YELLOW, 20 ),

		new CharSplashEffect( "SFX_CRYSTAL_BLAST", "Oo,.", ConsoleSystemInterface.CYAN,
				30 ),
		new CharSplashEffect( "SFX_BOSS_DEATH", "O....,,..,.,.,,......",
				ConsoleSystemInterface.RED, 50 ),
		new CharSplashEffect( "SFX_ROSARY_BLAST", "****~~~~,,,,....",
				ConsoleSystemInterface.WHITE, 50 ),

		new CharSplashEffect( "SFX_SOUL_FLAME", "****~~~~,,,,....",
				ConsoleSystemInterface.RED, 50 ),
		new CharSplashEffect( "SFX_SOUL_BLAST", "****~~~~,,,,....",
				ConsoleSystemInterface.GRAY, 50 ),

		// Melee Effects
		// En MonsterMissile.java : 45 "SFX_MONSTER_ID_"+aMonster.getID()
		// En Attack.java : 116 "SFX_"+weaponDef.getID()

		// Sequential Effects
		new CharSequentialEffect( "SFX_BIBLE", SFX_BIBLE_STEPS, "?�",
				ConsoleSystemInterface.CYAN, 10 ),

		// Tile Effects
		new CharIconMissileEffect( "SFX_CAT", 'c', ConsoleSystemInterface.GRAY, 55 ),
		new CharIconMissileEffect( "SFX_DRAGONFIRE", 'o', ConsoleSystemInterface.GREEN,
				150 ),
		new CharIconEffect( "SFX_RED_HIT", '*', ConsoleSystemInterface.RED, 100 ),
		new CharIconEffect( "SFX_HOMING_BALL", '*', ConsoleSystemInterface.CYAN, 60 ),
		new CharIconEffect( "SFX_WHITE_HIT", '*', ConsoleSystemInterface.WHITE, 100 ),
		new CharIconEffect( "SFX_MONSTER_CRAWLING", '^', ConsoleSystemInterface.BROWN,
				40 ),

		// Tile Missile Effects
		new CharIconMissileEffect( "SFX_BIRD", 'b', ConsoleSystemInterface.BROWN, 20 ),
		new CharIconMissileEffect( "SFX_EGG", 'O', ConsoleSystemInterface.WHITE, 20 ),
		new CharIconMissileEffect( "SFX_RENEGADE_BOD", 'O', ConsoleSystemInterface.RED,
				20 ),
		new CharIconMissileEffect( "SFX_MISSILE_HOMING_BALL", '*',
				ConsoleSystemInterface.RED, 20 ),
		new CharIconMissileEffect( "SFX_HOLY", 'p', ConsoleSystemInterface.CYAN, 20 ),
		// En Attack.java : 128 "SFX_"+weaponDef.getID()

		// Flash Effects
		new CharFlashEffect( "SFX_THUNDER_FLASH", ConsoleSystemInterface.WHITE ),

		// Weapons
		new CharBeamMissileEffect( "SFX_WP_AGUEN", "/\\", ConsoleSystemInterface.BLUE,
				46 ),

		new CharMeleeEffect( "SFX_WP_BASELARD", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_STAFF", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_MACE", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SHORT_SWORD", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_GAUNTLET", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SHORT_SPEAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_FLAIL", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_RINGS", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_WHIP", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SCIMITAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_ROD", "||--/\\\\/", ConsoleSystemInterface.BLUE ),
		new CharMeleeEffect( "SFX_WP_KNUCKLES", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_LONG_SPEAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SABRE", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_GLADIUS", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_COMBAT_RINGS", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_BATTLE_SPEAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_CUTLASS", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_ESTOC", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_COMBAT_GAUNTLET", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_MORNING_STAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_HALBERD", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_COMBAT_KNIFE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_KATANA", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_BROADSWORD", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SPIKY_KNUCKLES", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SPIKED_RINGS", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_RAPIER", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_FALCHION", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_BASTARDSWORD", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_DUALBLADE_SPEAR", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_MARTIAL_ARMBAND", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_BLADE_RINGSET", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_CLAYMORE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_LEATHER_WHIP", "||--/\\\\/",
				ConsoleSystemInterface.BROWN ),
		new CharMeleeEffect( "SFX_WP_THORN_WHIP", "||--/\\\\/",
				ConsoleSystemInterface.GREEN ),
		new CharMeleeEffect( "SFX_WP_CHAIN_WHIP", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_VKILLERW", "||--/\\\\/",
				ConsoleSystemInterface.BROWN ),
		new CharMeleeEffect( "SFX_WP_LIT_WHIP", "||--/\\\\/",
				ConsoleSystemInterface.YELLOW ),
		new CharMeleeEffect( "SFX_WP_FLAME_WHIP", "||--/\\\\/",
				ConsoleSystemInterface.RED ),
		new CharMeleeEffect( "SFX_WP_HOLBEIN_DAGGER", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_SHOTEL", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_FLAMBERGE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_WEREBANE", "||--/\\\\/",
				ConsoleSystemInterface.PURPLE ),
		new CharMeleeEffect( "SFX_WP_ETHANOS_BLADE", "||--/\\\\/",
				ConsoleSystemInterface.RED ),
		new CharMeleeEffect( "SFX_WP_MABLUNG", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_HADOR", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_HARPER", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_TULKAS_FIST", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_ALCARDE_SPEAR", "||--/\\\\/",
				ConsoleSystemInterface.YELLOW ),
		new CharMeleeEffect( "SFX_WP_FIREBRAND", "||--/\\\\/",
				ConsoleSystemInterface.RED ),
		new CharMeleeEffect( "SFX_WP_ICEBRAND", "||--/\\\\/",
				ConsoleSystemInterface.CYAN ),
		new CharMeleeEffect( "SFX_WP_VORPAL_BLADE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_GURTHANG", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_OSAFUNE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_MORMEGIL", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_GRAM", "||--/\\\\/", ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_KAISER_KNUCKLE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_TERMINUS", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_MOURNEBLADE", "||--/\\\\/",
				ConsoleSystemInterface.GRAY ),
		new CharMeleeEffect( "SFX_WP_CRISSAEGRIM", "||--/\\\\/",
				ConsoleSystemInterface.WHITE ),
		new CharMeleeEffect( "SFX_WP_MASAMUNE", "||--/\\\\/",
				ConsoleSystemInterface.WHITE ),
		new CharMeleeEffect( "SFX_WP_HAMMER_JUSTICE", "||--/\\\\/",
				ConsoleSystemInterface.WHITE ),

		new CharIconMissileEffect( "SFX_WP_HANDGUN", '\'', ConsoleSystemInterface.GRAY,
				20 ),
		new CharIconMissileEffect( "SFX_WP_SILVER_HANDGUN", '\'',
				ConsoleSystemInterface.GRAY, 20 ),
		new CharIconMissileEffect( "SFX_WP_REVOLVER", '\'', ConsoleSystemInterface.GRAY,
				20 ),
		new CharDirectionalMissileEffect( "SFX_WP_BOW", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 40 ),
		new CharDirectionalMissileEffect( "SFX_WP_CROSSBOW", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 20 ),

		new CharAnimatedMissileEffect( "SFX_WP_SHURIKEN", "x+",
				ConsoleSystemInterface.GRAY, 10 ),
		new CharAnimatedMissileEffect( "SFX_WP_CHAKRAM", "o-",
				ConsoleSystemInterface.GRAY, 10 ),
		new CharAnimatedMissileEffect( "SFX_WP_BUFFALO_STAR", "X+",
				ConsoleSystemInterface.GRAY, 10 ),
		new CharAnimatedMissileEffect( "SFX_WP_BWAKA_KNIFE", "/-\\|",
				ConsoleSystemInterface.GRAY, 10 ),
		new CharAnimatedMissileEffect( "SFX_WP_THROWING_KNIFE", "/-\\|",
				ConsoleSystemInterface.GRAY, 10 ),

		// Monsters
		new CharAnimatedMissileEffect( "SFX_ROTATING_BONE", "/-\\|",
				ConsoleSystemInterface.WHITE, 20 ),
		new CharAnimatedMissileEffect( "SFX_ROTATING_AXE", "/-\\|",
				ConsoleSystemInterface.BLUE, 40 ),
		new CharAnimatedMissileEffect( "SFX_FLAMING_BARREL", "0o",
				ConsoleSystemInterface.BROWN, 40 ),
		new CharMeleeEffect( "SFX_SPEAR_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.BLUE ),
		new CharMeleeEffect( "SFX_GIANT_BONE_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.BLUE ),
		new CharAnimatedMissileEffect( "SFX_FIREBALL", "*~", ConsoleSystemInterface.RED,
				30 ),
		new CharAnimatedMissileEffect( "SFX_WHIP_FIREBALL", "*~",
				ConsoleSystemInterface.PURPLE, 30 ),

		new CharDirectionalMissileEffect( "SFX_ARROW", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 10 ),
		new CharMeleeEffect( "SFX_AXE_MELEE", "||--/\\\\/", ConsoleSystemInterface.BLUE ),
		new CharMeleeEffect( "SFX_BLADE_SOLDIER_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.BLUE ),
		new CharMeleeEffect( "SFX_BONE_HALBERD_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.BLUE ),
		new CharMeleeEffect( "SFX_LIZARD_SWORDSMAN_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.BLUE ),
		new CharBeamMissileEffect( "SFX_STONE_BEAM", "X*..", ConsoleSystemInterface.GRAY,
				46 ),
		new CharAnimatedMissileEffect( "SFX_MAGIC_MISSILE", "*-",
				ConsoleSystemInterface.BLUE, 20 ),
		new CharAnimatedMissileEffect( "SFX_SUMMON_CAT_WITCH", "c",
				ConsoleSystemInterface.GRAY, 20 ),
		new CharDirectionalMissileEffect( "SFX_THROWN_SWORD", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 10 ),
		new CharDirectionalMissileEffect( "SFX_THROWN_DAGGER", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 10 ),
		new CharAnimatedMissileEffect( "SFX_BIG_FIREBALL", "*~",
				ConsoleSystemInterface.RED, 30 ),
		new CharAnimatedMissileEffect( "SFX_BULLET", ".", ConsoleSystemInterface.GRAY,
				10 ),
		new CharAnimatedMissileEffect( "SFX_SEED", ".", ConsoleSystemInterface.GREEN,
				30 ),
		new CharBeamMissileEffect( "SFX_FIRE_BEAM", "oOo", ConsoleSystemInterface.YELLOW,
				30 ),
		new CharAnimatedMissileEffect( "SFX_WINDING_SHARD", "~-",
				ConsoleSystemInterface.BLUE, 30 ),
		new CharAnimatedMissileEffect( "SFX_WHIRLING_SICKLE", "+x",
				ConsoleSystemInterface.GRAY, 30 ),
		new CharDirectionalMissileEffect( "SFX_THROWN_SPEAR", "\\|/--/|\\",
				ConsoleSystemInterface.WHITE, 10 ),
		new CharAnimatedMissileEffect( "SFX_ICEBALL", "*x", ConsoleSystemInterface.CYAN,
				30 ),
		new CharBeamMissileEffect( "SFX_NOVA_BEAM", "*oOo", ConsoleSystemInterface.CYAN,
				30 ),
		new CharBeamMissileEffect( "SFX_ARK_BEAM", "O", ConsoleSystemInterface.CYAN, 30 ),
		new CharBeamMissileEffect( "SFX_POISON_CLOUD", "X*..",
				ConsoleSystemInterface.PURPLE, 40 ),
		new CharMeleeEffect( "SFX_FLAME_SWORD_MELEE", "||--/\\\\/",
				ConsoleSystemInterface.RED ),
		new CharBeamMissileEffect( "SFX_TOXIC_POWDER", "X*..",
				ConsoleSystemInterface.PURPLE, 40 ),
		new CharAnimatedMissileEffect( "SFX_SPINNING_DISK", "OX",
				ConsoleSystemInterface.GRAY, 50 ),
		new CharBeamMissileEffect( "SFX_ENERGY_BEAM", "O", ConsoleSystemInterface.MAGENTA,
				30 ),
		new CharAnimatedMissileEffect( "SFX_SPINNING_SWORD", "/-\\|",
				ConsoleSystemInterface.GREEN, 40 ),
		new CharBeamMissileEffect( "SFX_ANCIENT_BEAM", "O", ConsoleSystemInterface.CYAN,
				30 ),
		new CharBeamMissileEffect( "SFX_STUN_BEAM", "X*..", ConsoleSystemInterface.YELLOW,
				46 ),
		new CharBeamMissileEffect( "SFX_FLAME_WAVE", "~_", ConsoleSystemInterface.RED,
				40 ),
		new CharBeamMissileEffect( "SFX_PURPLE_FLAME_BEAM", "~_",
				ConsoleSystemInterface.PURPLE, 40 ),

		new CharIconEffect( "SFX_HOLY_RAINDROP", '/', ConsoleSystemInterface.CYAN, 30 ),
		new CharSplashEffect( "SFX_HOLY_RAINSPLASH", "*~,-", ConsoleSystemInterface.CYAN,
				50 ), };

	private final static Vector SFX_BIBLE_STEPS = new Vector( 10 );

	static
	{
		SFX_BIBLE_STEPS.add( new Position( 1, 0 ) );
		SFX_BIBLE_STEPS.add( new Position( 2, -1 ) );
		SFX_BIBLE_STEPS.add( new Position( 1, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( 0, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( -1, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, -1 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, 0 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, 1 ) );
		SFX_BIBLE_STEPS.add( new Position( -1, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( 0, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( 1, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( 2, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( 3, 1 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, 0 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, -1 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, -3 ) );
		SFX_BIBLE_STEPS.add( new Position( 3, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( 2, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( 1, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( 0, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( -1, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( -3, -3 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, -1 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, 0 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, 1 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( -3, 3 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( -1, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( 0, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( 1, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( 2, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( 3, 4 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, 3 ) );
		SFX_BIBLE_STEPS.add( new Position( 5, 2 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, 1 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, 0 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, -1 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, -2 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, -3 ) );
		SFX_BIBLE_STEPS.add( new Position( 6, -4 ) );
		SFX_BIBLE_STEPS.add( new Position( 5, -5 ) );
		SFX_BIBLE_STEPS.add( new Position( 4, -6 ) );
		SFX_BIBLE_STEPS.add( new Position( 3, -7 ) );
		SFX_BIBLE_STEPS.add( new Position( 2, -8 ) );
		SFX_BIBLE_STEPS.add( new Position( 1, -9 ) );
		SFX_BIBLE_STEPS.add( new Position( 0, -10 ) );
		SFX_BIBLE_STEPS.add( new Position( -1, -11 ) );
		SFX_BIBLE_STEPS.add( new Position( -2, -12 ) );
		SFX_BIBLE_STEPS.add( new Position( -3, -13 ) );
		SFX_BIBLE_STEPS.add( new Position( -4, -14 ) );
		SFX_BIBLE_STEPS.add( new Position( -5, -15 ) );
		SFX_BIBLE_STEPS.add( new Position( -6, -16 ) );
	}

	public CharEffect[ ] getEffects( )
	{
		return effects;
	}
}
