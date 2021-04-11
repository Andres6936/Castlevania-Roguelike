package co.castle.player;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import co.castle.action.Action;
import co.castle.action.Equip;
import co.castle.action.invoker.Bird;
import co.castle.action.invoker.Cat;
import co.castle.action.invoker.Charm;
import co.castle.action.invoker.DrakeSoul;
import co.castle.action.invoker.Egg;
import co.castle.action.invoker.InvokeBird;
import co.castle.action.invoker.InvokeCat;
import co.castle.action.invoker.InvokeDragon;
import co.castle.action.invoker.InvokeEagle;
import co.castle.action.invoker.InvokeTiger;
import co.castle.action.invoker.InvokeTortoise;
import co.castle.action.invoker.InvokeTurtle;
import co.castle.action.invoker.Tame;
import co.castle.action.invoker.Turtle;
import co.castle.action.knight.Defend;
import co.castle.action.manbeast.BearMorph;
import co.castle.action.manbeast.BeastMorph;
import co.castle.action.manbeast.ClawAssault;
import co.castle.action.manbeast.ClawSwipe;
import co.castle.action.manbeast.DemonMorph;
import co.castle.action.manbeast.EnergyScythe;
import co.castle.action.manbeast.LupineMorph;
import co.castle.action.manbeast.PowerBlow;
import co.castle.action.manbeast.PowerBlow2;
import co.castle.action.manbeast.PowerBlow3;
import co.castle.action.manbeast.WereWolfMorph;
import co.castle.action.renegade.BatMorph;
import co.castle.action.renegade.BatMorph2;
import co.castle.action.renegade.BloodThirst;
import co.castle.action.renegade.Fireball;
import co.castle.action.renegade.FlamesShoot;
import co.castle.action.renegade.HellFire;
import co.castle.action.renegade.MystMorph;
import co.castle.action.renegade.MystMorph2;
import co.castle.action.renegade.SoulSteal;
import co.castle.action.renegade.SoulsStrike;
import co.castle.action.renegade.SummonSpirit;
import co.castle.action.renegade.Teleport;
import co.castle.action.renegade.WolfMorph;
import co.castle.action.renegade.WolfMorph2;
import co.castle.action.vanquisher.ChargeBall;
import co.castle.action.vanquisher.Cure;
import co.castle.action.vanquisher.Enchant;
import co.castle.action.vanquisher.EnergyShield;
import co.castle.action.vanquisher.FlameSpell;
import co.castle.action.vanquisher.HomingBall;
import co.castle.action.vanquisher.IceSpell;
import co.castle.action.vanquisher.Light;
import co.castle.action.vanquisher.LitSpell;
import co.castle.action.vanquisher.MajorJinx;
import co.castle.action.vanquisher.MindLock;
import co.castle.action.vanquisher.Mindblast;
import co.castle.action.vanquisher.Recover;
import co.castle.action.vkiller.AirDash;
import co.castle.action.vkiller.Axe;
import co.castle.action.vkiller.Bible;
import co.castle.action.vkiller.BlastCrystal;
import co.castle.action.vkiller.Cross;
import co.castle.action.vkiller.Dagger;
import co.castle.action.vkiller.Holy;
import co.castle.action.vkiller.ItemBreak;
import co.castle.action.vkiller.Rebound;
import co.castle.action.vkiller.SacredFist;
import co.castle.action.vkiller.SlideKick;
import co.castle.action.vkiller.SoulBlast;
import co.castle.action.vkiller.SoulFlame;
import co.castle.action.vkiller.SoulIce;
import co.castle.action.vkiller.SoulSaint;
import co.castle.action.vkiller.SoulWind;
import co.castle.action.vkiller.Stopwatch;
import co.castle.action.vkiller.WarpDash;
import co.castle.action.weapon.DivingSlide;
import co.castle.action.weapon.EnergyBeam;
import co.castle.action.weapon.EnergyBurst;
import co.castle.action.weapon.FinalSlash;
import co.castle.action.weapon.SpinningSlice;
import co.castle.action.weapon.TigerClaw;
import co.castle.action.weapon.WhirlwindWhip;
import co.castle.actor.Actor;
import co.castle.ai.ActionSelector;
import co.castle.ai.SelectorFactory;
import co.castle.feature.Feature;
import co.castle.game.Game;
import co.castle.game.PlayerGenerator;
import co.castle.game.SFXManager;
import co.castle.item.Item;
import co.castle.item.ItemDefinition;
import co.castle.item.ItemFactory;
import co.castle.level.Cell;
import co.castle.monster.Monster;
import co.castle.monster.VMonster;
import co.castle.npc.Hostage;
import co.castle.npc.NPC;
import co.castle.player.advancements.AdvDodge;
import co.castle.player.advancements.AdvDodge2;
import co.castle.player.advancements.Advancement;
import co.castle.player.advancements.invoker.AdvBirdsEgg;
import co.castle.player.advancements.invoker.AdvCatSoul;
import co.castle.player.advancements.invoker.AdvConfidence;
import co.castle.player.advancements.invoker.AdvDragonFire;
import co.castle.player.advancements.invoker.AdvInvokeBird;
import co.castle.player.advancements.invoker.AdvInvokeCat;
import co.castle.player.advancements.invoker.AdvInvokeDragon;
import co.castle.player.advancements.invoker.AdvInvokeEagle;
import co.castle.player.advancements.invoker.AdvInvokeTiger;
import co.castle.player.advancements.invoker.AdvInvokeTortoise;
import co.castle.player.advancements.invoker.AdvInvokeTurtle;
import co.castle.player.advancements.invoker.AdvKindSoul;
import co.castle.player.advancements.invoker.AdvManipulate;
import co.castle.player.advancements.invoker.AdvSoulForge;
import co.castle.player.advancements.invoker.AdvTame;
import co.castle.player.advancements.manbeast.AdvBearMorph;
import co.castle.player.advancements.manbeast.AdvBeastMorph;
import co.castle.player.advancements.manbeast.AdvClawAssault;
import co.castle.player.advancements.manbeast.AdvCompleteControl;
import co.castle.player.advancements.manbeast.AdvDemonMorph;
import co.castle.player.advancements.manbeast.AdvEnergyScythe;
import co.castle.player.advancements.manbeast.AdvImpactBlow;
import co.castle.player.advancements.manbeast.AdvImpactBlow3;
import co.castle.player.advancements.manbeast.AdvPowerBlow2;
import co.castle.player.advancements.manbeast.AdvRegen;
import co.castle.player.advancements.manbeast.AdvSelfControl;
import co.castle.player.advancements.renegade.AdvBatMorph;
import co.castle.player.advancements.renegade.AdvBatMorph2;
import co.castle.player.advancements.renegade.AdvDarkMetamorphosis;
import co.castle.player.advancements.renegade.AdvFlamesShot;
import co.castle.player.advancements.renegade.AdvLavaShot;
import co.castle.player.advancements.renegade.AdvMorph;
import co.castle.player.advancements.renegade.AdvMystMorph;
import co.castle.player.advancements.renegade.AdvMystMorph2;
import co.castle.player.advancements.renegade.AdvShadeTeleport;
import co.castle.player.advancements.renegade.AdvSoulsStrike;
import co.castle.player.advancements.renegade.AdvSummonSoul;
import co.castle.player.advancements.renegade.AdvWolfMorph;
import co.castle.player.advancements.renegade.AdvWolfMorph2;
import co.castle.player.advancements.stats.AdvJupiter;
import co.castle.player.advancements.stats.AdvMars;
import co.castle.player.advancements.stats.AdvMercury;
import co.castle.player.advancements.stats.AdvNeptune;
import co.castle.player.advancements.stats.AdvPluto;
import co.castle.player.advancements.stats.AdvSaturn;
import co.castle.player.advancements.stats.AdvTerra;
import co.castle.player.advancements.stats.AdvUranus;
import co.castle.player.advancements.stats.AdvVenus;
import co.castle.player.advancements.vanquisher.AdvCure;
import co.castle.player.advancements.vanquisher.AdvEnchant;
import co.castle.player.advancements.vanquisher.AdvEnergyShield;
import co.castle.player.advancements.vanquisher.AdvLight;
import co.castle.player.advancements.vanquisher.AdvMajorJinx;
import co.castle.player.advancements.vanquisher.AdvMindblast;
import co.castle.player.advancements.vanquisher.AdvMindlock;
import co.castle.player.advancements.vanquisher.AdvRecover;
import co.castle.player.advancements.vanquisher.AdvTeleport;
import co.castle.player.advancements.vkiller.AdvAirDash;
import co.castle.player.advancements.vkiller.AdvBackflip;
import co.castle.player.advancements.vkiller.AdvCross;
import co.castle.player.advancements.vkiller.AdvCrystal;
import co.castle.player.advancements.vkiller.AdvFist;
import co.castle.player.advancements.vkiller.AdvHolyBible;
import co.castle.player.advancements.vkiller.AdvHolyWater;
import co.castle.player.advancements.vkiller.AdvItemBreak;
import co.castle.player.advancements.vkiller.AdvSlideKick;
import co.castle.player.advancements.vkiller.AdvSoulBlast;
import co.castle.player.advancements.vkiller.AdvSoulFlame;
import co.castle.player.advancements.vkiller.AdvSoulIce;
import co.castle.player.advancements.vkiller.AdvSoulSaint;
import co.castle.player.advancements.vkiller.AdvSoulWind;
import co.castle.player.advancements.vkiller.AdvStopwatch;
import co.castle.player.advancements.vkiller.AdvWarpDash;
import co.castle.ui.Appearance;
import co.castle.ui.AppearanceFactory;
import co.castle.ui.UserInterface;
import co.castle.ui.effects.EffectFactory;
import sz.fov.FOV;
import sz.util.Counter;
import sz.util.Debug;
import sz.util.Position;
import sz.util.Util;

public class Player extends Actor {
	private int[] advancementLevels;
	private Item armor;
	private int attack;
	private int attackCost = 50;

	private final Vector<Skill> availableSkills = new Vector<Skill>(10);

	private String[] bannedArmors;
	private int baseSightRange;
	private int blockDirection, blockDirection1, blockDirection2;
	private int breathing = 25;
	private int carryMax;
	private int castCost = 50;

	private String classString;
	private int coolness;
	private final Vector<Item> counteredItems = new Vector<>();
	private Hostage currentHostage;
	private int currentMysticWeapon;
	private final Hashtable<String, String> customMessages = new Hashtable<>();
	// Vampire Killer
	private int daggerLevel;
	private int defense; // Temporary stat
	private int defenseCounter;
	private String description;
	private boolean deservesLevelUp = false;
	private boolean doNotRecordScore = false;
	private Monster enemy;
	private int energyFieldCounter;
	private int evadeChance;
	private int faintCount;
	private transient FOV fov;
	private Game game;
	private GameSessionInfo gameSessionInfo;

	private int gold;
	private int heartMax;
	private int hearts;
	private int hits;
	private int hitsMax;
	private final Hashtable<String, Equipment> inventory = new Hashtable<>();

	private int invincibleCount;
	// Status Auxiliars
	private int invisibleCount;
	private int jumpingCounter;
	/*
	 * private int[] weaponSkillsCounters = new int[13]; private int[] weaponSkills
	 * = new int[13];
	 */
	private boolean justJumped = false;
	private int keys;
	private final Hashtable<String, Integer> lastIncrements = new Hashtable<>();

	private int minorHeartCount;

	private int mUpgradeCount;
	// Attributes
	private String name;
	private int nextLevelXP = 1000; // 5000
	private ActionSelector originalSelector;
	private int petrifyCount;

	private int playerClass;

	// Relationships
	private transient PlayerEventListener playerEventListener;
	// Status
	private int playerLevel = 1;

	private String plot;

	private String plot2;

	private int poisonCount;

	private Position previousPosition;

	private int score;

	private Item secondaryWeapon;

	private int sex;

	private Item shield;

	private int shotLevel;

	private int soulPower;

	private int[] statAdvancementLevels;

	private int stunCount;

	private final Vector<Advancement> tmpAvailableAdvancements = new Vector<>();

	private int walkCost = 50;

	private Item weapon;

	private final Hashtable<String, Counter> weaponSkills = new Hashtable<>();

	private final Hashtable<String, Counter> weaponSkillsCounters = new Hashtable<>();

	private int whipLevel;

	private int xp;

	public static final Advancement ADV_MERCURY = new AdvMercury(),
			ADV_VENUS = new AdvVenus(), ADV_TERRA = new AdvTerra(),
			ADV_MARS = new AdvMars(), ADV_JUPITER = new AdvJupiter(),
			ADV_SATURN = new AdvSaturn(), ADV_URANUS = new AdvUranus(),
			ADV_NEPTUNE = new AdvNeptune(), ADV_PLUTO = new AdvPluto();

	public static Advancement[ ][ ] ADVANCEMENTS;

	public static final Advancement[ ] ALL_ADVANCEMENTS = new Advancement[ ]
	{	ADV_MERCURY, ADV_VENUS, ADV_TERRA, ADV_MARS, ADV_JUPITER, ADV_SATURN, ADV_URANUS,
		ADV_NEPTUNE, ADV_PLUTO };

	public final static int CLASS_VAMPIREKILLER = 0, CLASS_RENEGADE = 1,
			CLASS_VANQUISHER = 2, CLASS_INVOKER = 3, CLASS_MANBEAST = 4, CLASS_KNIGHT = 5;

	public final static int DAGGER = 0, AXE = 1, HOLY = 2, STOPWATCH = 3, CROSS = 4,
			BIBLE = 5, SACRED_CRYSTAL = 6, SACRED_FIST = 7, SACRED_REBOUND = 8;

	public final static int DAMAGE_MORPHED_WITH_STRONG_ARMOR = 0,
			DAMAGE_WALKED_ON_LAVA = 1, DAMAGE_USING_ITEM = 2, DAMAGE_POISON = 3,
			DAMAGE_JINX = 4;

	/*
	 * public void finishLevel(){ playerEventListener.informEvent(EVT_FORWARD); }
	 */

	public final static int DEATH = 0, WIN = 1, DROWNED = 2, KEYINMINENT = 3;

	public final static int EVT_FO23RWARD = 7, EVT_RE23TURN = 8, EVT_MERCHANT = 9,
			EVT_SMASHED = 10, EVT_CHAT = 11, EVT_LEVELUP = 12,
			EVT_NEXT_LEVEL_DEPRECATED = 13, EVT_BACK_LEVEL_DEPRECATED = 14,
			EVT_GOTO_LEVEL = 15, EVT_FORWARDTIME = 16, EVT_INN = 17;

	public static final String INCREMENT_HITS = "hits", INCREMENT_HEARTS = "hearts",
			INCREMENT_ATTACK = "attack", INCREMENT_COMBAT = "combat",
			INCREMENT_INVOKATION = "invok", INCREMENT_SPEED = "speed",
			INCREMENT_SOUL = "soul", INCREMENT_CARRYING = "carry",
			INCREMENT_DEFENSE = "defense", INCREMENT_EVADE = "evade";

	public static Item LEATHER_WHIP, CHAIN_WHIP, VAMPIRE_WHIP, THORN_WHIP, FLAME_WHIP,
			LIT_WHIP;

	public final static int MALE = 1, FEMALE = 2;

	public static Advancement[ ][ ] STATADVANCEMENTS = new Advancement[ ][ ]
	{
		{ ADV_VENUS, ADV_PLUTO, ADV_SATURN },
		{ ADV_MARS, ADV_MERCURY, ADV_URANUS },
		{ ADV_TERRA, ADV_URANUS, ADV_SATURN },
		{ ADV_VENUS, ADV_TERRA, ADV_MERCURY },
		{ ADV_JUPITER, ADV_NEPTUNE, ADV_PLUTO },
		{ ADV_JUPITER, ADV_NEPTUNE, ADV_MARS }

	};

	public final static String STATUS_STUN = "STUN", STATUS_POISON = "POISON",
			STATUS_PETRIFY = "PETRIFY", STATUS_FAINTED = "FAINTED";

	private final static Action[] MYSTIC_ACTIONS = new Action[]
			{new Dagger(), new Axe(), new Holy(), new Stopwatch(), new Cross(),
					new Bible(), new BlastCrystal(), new SacredFist(), new Rebound()};

	private final static Hashtable<String, Skill> skills = new Hashtable<>();

	static {
		skills.put("DIVING_SLIDE", new Skill("Diving Slide", new DivingSlide(), 8));
		skills.put("SPINNING_SLICE",
				new Skill("Spinning Slice", new SpinningSlice(), 8));
		skills.put("WHIRLWIND_WHIP",
				new Skill("Whirlwind Whip", new WhirlwindWhip(), 5));
		skills.put("ENERGY_BEAM", new Skill("Energy Beam", new EnergyBeam(), 10));
		skills.put("FINAL_SLASH", new Skill("Final Slash!", new FinalSlash(), 10));
		skills.put( "TIGER_CLAW", new Skill( "Tiger Claw", new TigerClaw( ), 10 ) );
		skills.put( "ENERGY_BURST", new Skill( "Energy Burst", new EnergyBurst( ), 10 ) );
		skills.put( "REGEN", new Skill( "Regeneration" ) );

		skills.put( "DODGE", new Skill( "Dodge" ) );
		skills.put( "DODGE2", new Skill( "Mirror Dodge" ) );

		/*
		 * skills.put("MYSTIC_WEAPON", new Skill("Mystic Weapons"));
		 * skills.put("HMYSTIC_WEAPON", new Skill("Sacred Mystics"));
		 */

		// Vampire Killer Skills
		skills.put( "MYSTIC_DAGGER", new Skill( "Mystic Dagger" ) );
		skills.put( "MYSTIC_AXE", new Skill( "Mystic Axe" ) );
		skills.put( "MYSTIC_HOLYWATER", new Skill( "Mystic Holy Water" ) );
		skills.put( "MYSTIC_BIBLE", new Skill( "Mystic Bible" ) );
		skills.put( "MYSTIC_STOPWATCH", new Skill( "Mystic Stopwatch" ) );
		skills.put( "MYSTIC_CROSS", new Skill( "Mystic Cross" ) );
		skills.put( "MYSTIC_FIST", new Skill( "Sacred Fist" ) );
		skills.put( "MYSTIC_CRYSTAL", new Skill( "Blast Crystal" ) );
		skills.put( "WARP_DASH", new Skill( "Warp Dash", new WarpDash( ), 3 ) );
		skills.put( "AIR_DASH", new Skill( "Air Dash", new AirDash( ), 5 ) );
		skills.put( "ITEM_BREAK", new Skill( "Item Break", new ItemBreak( ), 0 ) );
		skills.put( "BACKFLIP", new Skill( "Backflip" ) );
		skills.put( "SKILL_SOULWIND", new Skill( "Soul Wind", new SoulWind( ), 10 ) );
		skills.put( "SKILL_SOULFLAME", new Skill( "Soul Flame", new SoulFlame( ), 10 ) );
		skills.put( "SKILL_SOULICE", new Skill( "Soul Ice", new SoulIce( ), 20 ) );
		skills.put( "SKILL_SOULSAINT", new Skill( "Soul Saint", new SoulSaint( ), 15 ) );
		skills.put( "SKILL_SOULBLAST", new Skill( "Soul Blast", new SoulBlast( ), 20 ) );
		skills.put( "SLIDE_KICK", new Skill( "Slide Kick", new SlideKick( ), 2 ) );

		// Renegade Skills
		skills.put( "FIREBALL", new Skill( "Fireball", new Fireball( ), 2 ) );
		skills.put( "SOULSTEAL", new Skill( "Soul Steal", new SoulSteal( ), 5 ) );
		skills.put( "SUMMON_SPIRIT",
				new Skill( "Summon Spirit", new SummonSpirit( ), 4 ) );
		skills.put( "SKILL_SOULSSTRIKE",
				new Skill( "Soul's Strike", new SoulsStrike( ), 8 ) );
		skills.put( "SKILL_FLAMESSHOOT",
				new Skill( "Flame Shoot", new FlamesShoot( ), 10 ) );
		skills.put( "SKILL_HELLFIRE", new Skill( "Hellfire", new HellFire( ), 15 ) );
		skills.put( "MINOR_JINX", new Skill( "Minor Jinx",
				new co.castle.action.renegade.MinorJinx( ), 0 ) );
		skills.put( "BLOOD", new Skill( "Dark Metamorphosis", new BloodThirst( ), 10 ) );
		skills.put( "TELEPORT", new Skill( "Shade Teleport", new Teleport( ), 5 ) );
		skills.put( "SKILL_WOLFMORPH",
				new Skill( "Lupine Metamorphosis", new WolfMorph( ), 10 ) );
		skills.put( "SKILL_MYSTMORPH",
				new Skill( "Ethereal Metamorphosis", new MystMorph( ), 10 ) );
		skills.put( "SKILL_BATMORPH",
				new Skill( "Chiroptera Metamorphosis", new BatMorph( ), 10 ) );
		skills.put( "SKILL_WOLFMORPH2",
				new Skill( "Adv. Lupine Metamorphosis", new WolfMorph2( ), 10 ) );
		skills.put( "SKILL_MYSTMORPH2",
				new Skill( "Adv. Ethereal Metamorphosis", new MystMorph2( ), 10 ) );
		skills.put( "SKILL_BATMORPH2",
				new Skill( "Adv. Chiroptera Metamorphosis", new BatMorph2( ), 10 ) );

		// Vanquisher Skills
		skills.put( "HOMING_BALL", new Skill( "Homing Ball", new HomingBall( ), 2 ) );
		skills.put( "CHARGE_BALL", new Skill( "Charge Ball", new ChargeBall( ), 6 ) );
		skills.put( "FLAME_SPELL", new Skill( "Flame Spell", new FlameSpell( ), 8 ) );
		skills.put( "ICE_SPELL", new Skill( "Ice Spell", new IceSpell( ), 8 ) );
		skills.put( "LIT_SPELL", new Skill( "Lighting Spell", new LitSpell( ), 8 ) );
		skills.put( "MINDBLAST", new Skill( "MindBlast", new Mindblast( ), 15 ) );
		skills.put( "TELEPORT",
				new Skill( "Teleport", new co.castle.action.vanquisher.Teleport( ), 5 ) );
		skills.put( "RECOVER", new Skill( "Recover", new Recover( ), 15 ) );
		skills.put( "CURE", new Skill( "Cure", new Cure( ), 5 ) );
		skills.put( "ENCHANT", new Skill( "Enchant", new Enchant( ), 5 ) );
		skills.put( "ENERGYSHIELD",
				new Skill( "Energy Shield", new EnergyShield( ), 15 ) );
		skills.put( "LIGHT", new Skill( "Light", new Light( ), 15 ) );
		skills.put( "MINDLOCK", new Skill( "Mindlock", new MindLock( ), 7 ) );
		skills.put( "MAJOR_JINX", new Skill( "Major Jinx", new MajorJinx( ), 0 ) );

		// Invoker Skills
		skills.put( "INVOKE_CAT", new Skill( "Cat Soul", new Cat( ), 3 ) );
		skills.put( "INVOKE_TURTLE", new Skill( "Turtle Soul", new Turtle( ), 5 ) );
		skills.put( "INVOKE_BIRD", new Skill( "Birds' Soul", new Bird( ), 2 ) );
		skills.put( "INVOKE_DRAGON", new Skill( "Dragonfire", new DrakeSoul( ), 8 ) );
		skills.put( "THROW_EGG", new Skill( "Throw Egg", new Egg( ), 1 ) );
		skills.put( "MANIPULATE", new Skill( "Manipulate", new Charm( ), 5 ) );
		skills.put( "TAME", new Skill( "Tame", new Tame( ), 5 ) );
		skills.put( "SUMMON_CAT", new Skill( "Invoke Cat", new InvokeCat( ), 5 ) );
		skills.put( "SUMMON_TURTLE",
				new Skill( "Invoke Turtle", new InvokeTurtle( ), 5 ) );
		skills.put( "SUMMON_BIRD", new Skill( "Invoke Bird", new InvokeBird( ), 5 ) );
		skills.put( "SUMMON_TIGER", new Skill( "Invoke Tiger", new InvokeTiger( ), 5 ) );
		skills.put( "SUMMON_TORTOISE",
				new Skill( "Invoke Tortoise", new InvokeTortoise( ), 5 ) );
		skills.put( "SUMMON_EAGLE", new Skill( "Invoke Eagle", new InvokeEagle( ), 5 ) );
		skills.put( "SUMMON_DRAGON",
				new Skill( "Invoke Dragon", new InvokeDragon( ), 8 ) );

		// ManBeast
		skills.put( "CLAW_SWIPE", new Skill( "Claw Swipe", new ClawSwipe( ), 3 ) );
		skills.put( "LUPINE_MORPH", new Skill( "Lupine Morph", new LupineMorph( ), 15 ) );
		skills.put( "IMPACT_BLOW", new Skill( "Power Blow", new PowerBlow( ), 3 ) );
		skills.put( "ENERGY_SCYTHE",
				new Skill( "Energy Scythe", new EnergyScythe( ), 5 ) );
		skills.put( "BEAR_MORPH", new Skill( "Ursinae Morph", new BearMorph( ), 15 ) );
		skills.put( "IMPACT_BLOW2", new Skill( "Power Strike", new PowerBlow2( ), 3 ) );
		skills.put( "CLAW_ASSAULT", new Skill( "Claw Assault", new ClawAssault( ), 5 ) );
		skills.put( "SELFCONTROL", new Skill( "SelfControl" ) );
		skills.put( "REGEN", new Skill( "Regeneration" ) );
		skills.put( "BEAST_MORPH", new Skill( "BeaSt MoRPh", new BeastMorph( ), 15 ) );
		skills.put( "IMPACT_BLOW3", new Skill( "Power Crash", new PowerBlow3( ), 3 ) );
		skills.put( "DEMON_MORPH", new Skill( "Demon Morph", new DemonMorph( ), 15 ) );
		skills.put( "COMPLETECONTROL", new Skill( "Complete Control" ) );
		skills.put( "WEREWOLF_MORPH",
				new Skill( "Legendary Werewolf", new WereWolfMorph( ), 20 ) );

		// Knight
		skills.put( "SHIELD_GUARD", new Skill( "Shield Guard", new Defend( ), 1 ) );

	}

	static
	{
		ADVANCEMENTS = new Advancement[ ][ ]
		{
			{ // Vampire Killer
				new AdvDodge( ), new AdvDodge2( ),

				new AdvHolyBible( ), new AdvHolyWater( ), new AdvStopwatch( ),
				new AdvCross( ), new AdvFist( ), new AdvCrystal( ), new AdvAirDash( ),
				new AdvBackflip( ), new AdvSlideKick( ), new AdvWarpDash( ),

				new AdvItemBreak( ), new AdvSoulBlast( ), new AdvSoulFlame( ),
				new AdvSoulIce( ), new AdvSoulSaint( ), new AdvSoulWind( ) },
			{	new AdvBatMorph( ), new AdvBatMorph2( ), new AdvDarkMetamorphosis( ),
				new AdvFlamesShot( ), new AdvLavaShot( ), new AdvMystMorph( ),
				new AdvMystMorph2( ), new AdvShadeTeleport( ), new AdvSoulsStrike( ),
				new AdvSummonSoul( ), new AdvWolfMorph( ), new AdvWolfMorph2( ),
				new AdvMorph( ) },
			{	new AdvDodge( ), new AdvDodge2( ), new AdvCure( ), new AdvEnchant( ),
				new AdvEnergyShield( ), new AdvLight( ), new AdvMindblast( ),
				new AdvMindlock( ), new AdvRecover( ), new AdvTeleport( ),
				new AdvMajorJinx( ) },
			{	new AdvDodge( ), new AdvDodge2( ), new AdvBirdsEgg( ), new AdvCatSoul( ),
				new AdvDragonFire( ), new AdvInvokeBird( ), new AdvInvokeCat( ),
				new AdvInvokeDragon( ), new AdvInvokeEagle( ), new AdvInvokeTiger( ),
				new AdvInvokeTortoise( ), new AdvInvokeTurtle( ), new AdvManipulate( ),
				new AdvTame( ), new AdvConfidence( ), new AdvSoulForge( ),
				new AdvKindSoul( ) },
			{	new AdvDodge( ), new AdvDodge2( ), new AdvBearMorph( ),
				new AdvBeastMorph( ), new AdvClawAssault( ), new AdvCompleteControl( ),
				new AdvDemonMorph( ), new AdvEnergyScythe( ), new AdvImpactBlow( ),
				new AdvPowerBlow2( ), new AdvImpactBlow3( ), new AdvRegen( ),
				new AdvSelfControl( ) },
			{

			} };
	}

	public Player( )
	{
		hitsMax = 20;
		hits = hitsMax;
		heartMax = 20;
		carryMax = 15;
		hearts = 5;
		gold = 0;
		currentMysticWeapon = -1;
		for ( int i = 0; i < ItemDefinition.CATS.length; i++ )
		{
			resetWeaponSkillLevel( ItemDefinition.CATS[ i ] );
		}
	}

	public static String getFeatureNameForMystic( int mysticID )
	{
		switch ( mysticID )
		{
		case AXE:
			return "AXEWP";
		case DAGGER:
			return "DAGGERWP";
		case HOLY:
			return "HOLYWP";
		case CROSS:
			return "CROSSWP";
		case STOPWATCH:
			return "STOPWATCHWP";
		case BIBLE:
			return "BIBLEWP";
		case SACRED_FIST:
			return "FISTWP";
		case SACRED_CRYSTAL:
			return "CRYSTALWP";
		case SACRED_REBOUND:
			return "REBOUNDWP";
		}
		return "DAGGERWP";
	}

	public static void initializeWhips(	String leatherWhip, String chainWhip,
										String vampireKiller, String thornWhip,
										String flameWhip, String litWhip )
	{
		ItemFactory itf = ItemFactory.getItemFactory( );
		LEATHER_WHIP = itf.createWeapon( leatherWhip, "" );
		CHAIN_WHIP = itf.createWeapon( chainWhip, "" );
		VAMPIRE_WHIP = itf.createWeapon( vampireKiller, "" );
		THORN_WHIP = itf.createWeapon( thornWhip, "" );
		FLAME_WHIP = itf.createWeapon( flameWhip, "" );
		LIT_WHIP = itf.createWeapon( litWhip, "" );
	}

	public static String weaponName( int code )
	{
		switch ( code )
		{
		case AXE:
			return "Axe";
		case CROSS:
			return "Cross";
		case DAGGER:
			return "Mystic Dagger";
		case HOLY:
			return "Holy water";
		case STOPWATCH:
			return "Stopwatch";
		case BIBLE:
			return "Holy Bible";
		case SACRED_CRYSTAL:
			return "Crystal";
		case SACRED_FIST:
			return "Sacred Fist";
		case SACRED_REBOUND:
			return "Rebound Crystal";
		default:
			return "No Weapon";
		}
	}

	public void abandonHostage( )
	{
		getHostage( ).setPosition( getPosition( ) );
		level.addMonster( getHostage( ) );
		addHistoricEvent( "abandoned " + getHostage( ).getDescription( ) + " at the "
				+ getLevel( ).getDescription( ) );
		setHostage( null );
	}

	public void act( )
	{
		setPreviousPosition( );
		if ( deservesLevelUp )
		{
			levelUp( );
		}
		if ( justJumped( ) )
		{
			setJustJumped( false );
		}
		else if ( isStunned( ) )
		{
			if ( Util.chance( 40 ) )
			{
				level.addMessage( "You cannot move!" );
				updateStatus( );
			}
			else
				super.act( );
		}
		else if ( isPetrified( ) )
		{
			level.addMessage( "You are petrified!" );
			updateStatus( );
			see( );
			UserInterface.getUI( ).refresh( );
		}
		else if ( isFainted( ) )
		{
			updateStatus( );
			see( );
			UserInterface.getUI( ).refresh( );
		}
		else
		{
			super.act( );
		}

	}

	public void addCounteredItem( Item i )
	{
		counteredItems.add( i );
	}

	public void addGold( int x )
	{
		gold += x;
		addScore( x );
		gameSessionInfo.addGold( x );
	}

	public void addHearts( int howMuch )
	{
		minorHeartCount++;
		hearts += howMuch;
		if ( hearts > heartMax )
			hearts = heartMax;
	}

	public void addHistoricEvent( String description )
	{
		gameSessionInfo.addHistoryItem( description );
	}

	public void addItem( Item toAdd )
	{
		if ( !canCarry( ) )
		{
			if ( level != null )
				level.addMessage( "You can't carry anything more" );
			return;
		}
		String[ ] effectOnAcquire = toAdd.getEffectOnAcquire( ).split( " " );
		if ( effectOnAcquire[ 0 ].equals( "KEYS" ) )
			addKeys( Integer.parseInt( effectOnAcquire[ 1 ] ) );
		else if ( effectOnAcquire[ 0 ].equals( "HEARTMAX" ) )
			increaseHeartMax( Integer.parseInt( effectOnAcquire[ 1 ] ) );
		else if ( effectOnAcquire[ 0 ].equals( "HITSMAX" ) )
			increaseHitsMax( Integer.parseInt( effectOnAcquire[ 1 ] ) );
		else if ( effectOnAcquire[ 0 ].equals( "ENABLE" ) )
		{
			if ( effectOnAcquire[ 1 ].equals( "LITSPELL" ) )
				setFlag( Consts.C_SPELL_LIT, true );
			else if ( effectOnAcquire[ 1 ].equals( "FLAMESPELL" ) )
				setFlag( Consts.C_SPELL_FIRE, true );
			else if ( effectOnAcquire[ 1 ].equals( "ICESPELL" ) )
				setFlag( Consts.C_SPELL_ICE, true );
			if ( effectOnAcquire[ 1 ].equals( "SILVERDAGGER" ) )
			{
				if ( daggerLevel == 0 )
					daggerLevel = 1;
			}
			else if ( effectOnAcquire[ 1 ].equals( "GOLDDAGGER" ) )
			{
				daggerLevel = 2;
			}
		}
		else if ( effectOnAcquire[ 0 ].equals( "CARRY" ) )
			setCarryMax( Integer.parseInt( effectOnAcquire[ 1 ] ) );

		if ( !effectOnAcquire[ 0 ].equals( "" ) && toAdd.getDefinition( ).isSingleUse( ) )
			;

		else
		{
			if ( canCarry( ) )
			{
				String toAddID = toAdd.getFullID( );
				Equipment equipmentx = (Equipment) inventory.get( toAddID );
				if ( equipmentx == null )
					inventory.put( toAddID, new Equipment( toAdd, 1 ) );
				else
					equipmentx.increaseQuantity( );
			}
		}
	}

	public void addKeys( int x )
	{
		keys += x;
	}

	public void addLastIncrement( String key, int value ) {
		Integer current = lastIncrements.get(key);
		if (current == null) {
			current = value;
		} else {
			current = current + value;
		}
		lastIncrements.put(key, current);
	}

	public void addScore( int x )
	{
		score += x;
	}
	public void addXP( int x )
	{
		xp += x;
		if ( xp >= nextLevelXP )
		{
			deservesLevelUp = true;
		}
	}
	public void bounceBack( Position var, int dep )
	{
		Debug.enterMethod( this, "bounceBack", var + "," + dep );
		int startingHeight = level.getMapCell( getPosition( ) ).getHeight( );
		out: for ( int i = 1; i < dep; i++ )
		{
			Position destinationPoint = Position.add( getPosition( ), var );
			Cell destinationCell = level.getMapCell( destinationPoint );
			/*
			 * if (destinationCell == null) break out;
			 */
			if ( destinationCell == null )
			{
				if ( !level.isValidCoordinate( destinationPoint ) )
				{
					destinationPoint = Position.subs( destinationPoint, var );
					landOn( destinationPoint );
					break out;
				}
				if ( i < dep - 1 )
				{
					setPosition( destinationPoint );
					continue out;
				}
				else
				{
					landOn( destinationPoint );
					break out;
				}

			}
			Feature destinationFeature = level.getFeatureAt( destinationPoint );
			if ( destinationFeature != null
					&& destinationFeature.getKeyCost( ) > getKeys( ) )
			{
				land( );
				break out;
			}
			if ( destinationCell.getHeight( ) > startingHeight + 2 )
			{
				land( );
				break out;
			}
			else
			{
				if ( !destinationCell.isSolid( ) )
				{
					if ( i < dep - 1 )
						setPosition( destinationPoint );
					else
						landOn( destinationPoint );
				}
				else
				{
					level.addMessage( "You bump into the "
							+ destinationCell.getShortDescription( ) );
					land( );
					break out;
				}
			}
		}
		Debug.exitMethod( );
	}
	/*
	 * if (standsOnPlace() || level.getMapCell(getPosition()).isStair() ||
	 * isInvincible() || hasEnergyField()) return; Position landingPoint = null; for
	 * (int run = 0; run < dep; run++){ landingPoint = Position.add(getPosition(),
	 * variation); if (!level.isValidCoordinate(landingPoint)){ land(); return; }
	 * Cell landingCell = getLevel().getMapCell(landingPoint); if (landingCell ==
	 * null){ if (run < dep-1){ //setPosition(landingPoint); landOn(landingPoint); }
	 * else { landingPoint = level.getDeepPosition(landingPoint); if (landingPoint
	 * == null){ level.addMessage("You are thrown into a endless pit!");
	 * gameSessionInfo.setDeathCause(GameSessionInfo.ENDLESS_PIT); hits = -1;
	 * informPlayerEvent(Player.DEATH); return; } else { landOn(landingPoint);
	 * return; } } } else { if (!landingCell.isSolid() && landingCell.getHeight() <=
	 * getLevel().getMapCell(getPosition()).getHeight()) { if (run < dep-1){
	 * landOn(landingPoint); } else { landOn(landingPoint); return; } } else {
	 * return; } } } }
	 */
	public boolean canAttack( )
	{
		if ( isSwimming( ) )
		{

			if ( getWeapon( ) == null
					|| getWeapon( ).getWeaponCategory( )
							.equals( ItemDefinition.CAT_UNARMED )
					|| getWeapon( ).getWeaponCategory( )
							.equals( ItemDefinition.CAT_DAGGERS )
					|| getWeapon( ).getWeaponCategory( )
							.equals( ItemDefinition.CAT_SPEARS )
					|| getWeapon( ).getWeaponCategory( )
							.equals( ItemDefinition.CAT_RINGS ) )
				return true;
			else
				return false;
		}
		return !( hasCounter( Consts.C_BATMORPH ) || hasCounter( Consts.C_BATMORPH2 )
				|| hasCounter( Consts.C_MYSTMORPH )
				|| hasCounter( Consts.C_MYSTMORPH2 ) );
	}

	public boolean canCarry( )
	{
		return getItemCount( ) < carryMax;
	}

	public boolean canCarry( int quantity )
	{
		return getItemCount( ) + quantity <= carryMax;
	}

	public boolean canWield( )
	{
		return !isMorphed( );
	}

	public void checkDeath( )
	{
		if ( hits < 0 )
		{
			level.addMessage( "You are dead.." );
			informPlayerEvent( DEATH );
		}
	}

	public void cure( )
	{
		if ( isPoisoned( ) )
		{
			level.addMessage( "The poison leaves your veins" );
			setPoison( 0 );
		}
		else
		{
			level.addMessage( "Nothing happens" );
		}
	}

	public boolean damage( StringBuffer message, Monster who, Damage dam )
	{
		int attackDirection = Action.getGeneralDirection( who.getPosition( ),
				getPosition( ) );
		if ( hasEnergyField( ) )
		{
			StringBuffer buff = new StringBuffer(
					"The " + who.getDescription( ) + " is shocked!" );
			who.damage( buff, 1 );
			level.addMessage( buff.toString( ) );
			return false;
		}

		if ( Util.chance( getEvadeChance( ) ) )
		{
			level.addMessage(
					"You jump and avoid the " + who.getDescription( ) + " attack" );
			return false;
		}

		if ( getFlag( "PASIVE_BACKFLIP" ) && Util.chance( getBackFlipChance( ) )
				&& Util.chance( evadeChance ) )
		{
			level.addMessage(
					"You backflip and avoid the " + who.getDescription( ) + " attack!" );
			return false;
		}

		if ( getWeapon( ) != null && Util.chance( getWeapon( ).getCoverage( ) ) )
		{
			level.addMessage(
					"You parry the attack with your " + getWeapon( ).getDescription( ) );
			return false;
		}

		if ( getShield( ) != null && ( getWeapon( ) == null
				|| ( getWeapon( ) != null && !getWeapon( ).isTwoHanded( ) ) ) )
		{
			int blockChance = getShieldBlockChance( );
			int coverageChance = getShieldCoverageChance( );

			if ( hasCounter( "SHIELD_GUARD" ) )
			{
				if ( attackDirection == blockDirection
						|| attackDirection == blockDirection1
						|| attackDirection == blockDirection2 )
				{
					level.addMessage( "You withstand the attack!" );
					blockChance *= 3;
					coverageChance = 100;
				}
			}

			if ( Util.chance( blockChance ) )
			{
				level.addMessage( "You completely block the attack with your "
						+ getShield( ).getDescription( ) );
				increaseWeaponSkill( ItemDefinition.CAT_SHIELD );
				return false;
			}

			if ( Util.chance( coverageChance ) )
			{
				level.addMessage( "Your " + getShield( ).getDescription( ) + " is hit." );
				dam.reduceDamage( getShield( ).getDefense( ) );
			}
		}

		damage( message, dam );
		if ( hits < 0 )
		{
			if ( getSex( ) == MALE )
				SFXManager.play( "wav/die_male.wav" );
			else
				SFXManager.play( "wav/die_female.wav" );

			gameSessionInfo.setDeathCause( GameSessionInfo.KILLED );
			gameSessionInfo.setKillerMonster( who );
			gameSessionInfo.setDeathLevel( level.getLevelNumber( ) );
		}
		return true;
	}

	/*
	 * public void removeItem(Item toRemove){
	 * inventory.remove(toRemove.getDefinition().getID()); }
	 */

	public void darken( )
	{
		level.darken( );
	}

	public void decreaseWhip( )
	{
		if ( !( getPlayerClass( ) == CLASS_VAMPIREKILLER ) )
			return;
		if ( shotLevel > 0 )
			shotLevel--;
		if ( whipLevel > 0 )
			whipLevel--;
		else
			return;
		switch ( whipLevel )
		{
		case 1:
			level.addMessage( "Your Vampire Killer turns into a chain whip!" );
			weapon = CHAIN_WHIP;
			break;
		case 0:
			level.addMessage( "Your chain whip turns into a leather whip!" );
			weapon = LEATHER_WHIP;
		}
	}

	public void deMorph( )
	{
		if ( hasCounter( Consts.C_BATMORPH ) )
			setCounter( Consts.C_BATMORPH, 0 );
		if ( hasCounter( Consts.C_BATMORPH2 ) )
			setCounter( Consts.C_BATMORPH2, 0 );
		if ( hasCounter( Consts.C_MYSTMORPH ) )
			setCounter( Consts.C_MYSTMORPH, 0 );
		if ( hasCounter( Consts.C_MYSTMORPH2 ) )
			setCounter( Consts.C_MYSTMORPH2, 0 );
		if ( hasCounter( Consts.C_WOLFMORPH ) )
			setCounter( Consts.C_WOLFMORPH, 0 );
		if ( hasCounter( Consts.C_WOLFMORPH2 ) )
			setCounter( Consts.C_WOLFMORPH2, 0 );
		if ( hasCounter( Consts.C_BEARMORPH ) )
			setCounter( Consts.C_BEARMORPH, 0 );
		if ( hasCounter( Consts.C_BEASTMORPH ) )
			setCounter( Consts.C_BEASTMORPH, 0 );
		if ( hasCounter( Consts.C_DEMONMORPH ) )
			setCounter( Consts.C_DEMONMORPH, 0 );
		if ( hasCounter( Consts.C_LUPINEMORPH ) )
			setCounter( Consts.C_LUPINEMORPH, 0 );
		if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
			setCounter( Consts.C_WEREWOLFMORPH, 0 );
	}

	public boolean deservesAdvancement( int level ) {
		for (int advancementLevel : advancementLevels) {
			if (advancementLevel == level)
				return true;
		}
		return false;
	}

	public boolean deservesMUpgrade( )
	{
		if ( getPlayerClass( ) != CLASS_VAMPIREKILLER )
			return false;
		// Debug.say(mUpgradeCount);
		if ( shotLevel > 1 )
			return false;
		if ( mUpgradeCount > 50 )
		{
			mUpgradeCount = 0;
			return true;
		}
		return false;
	}

	public boolean deservesStatAdvancement( int level ) {
		for (int statAdvancementLevel : statAdvancementLevels) {
			if (statAdvancementLevel == level)
				return true;
		}
		return false;
	}

	public boolean deservesUpgrade( )
	{
		if ( getPlayerClass( ) != CLASS_VAMPIREKILLER )
			return false;
		if ( getWeapon( ) == VAMPIRE_WHIP )
			return false;
		if ( minorHeartCount > 5 )
		{
			minorHeartCount = 0;
			return true;
		}
		return false;
	}

	public String getAccDescription( )
	{
		if ( shield == null )
			return "Nothing";
		else
			return shield.getAttributesDescription( );
	}

	public Appearance getAppearance( )
	{
		if ( hasCounter( Consts.C_BATMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_BAT" );
		else if ( hasCounter( Consts.C_BATMORPH2 ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_BAT2" );
		else if ( hasCounter( Consts.C_LUPINEMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_LUPINE" );
		else if ( hasCounter( Consts.C_BEARMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WEREBEAR" );
		else if ( hasCounter( Consts.C_BEASTMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WEREBEAST" );
		else if ( hasCounter( Consts.C_DEMONMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WEREDEMON" );
		else if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WEREWOLF" );
		else if ( hasCounter( Consts.C_WOLFMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WOLF" );
		else if ( hasCounter( Consts.C_WOLFMORPH2 ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_WOLF2" );
		else if ( hasCounter( Consts.C_MYSTMORPH ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_MYST" );
		else if ( hasCounter( Consts.C_MYSTMORPH2 ) )
			return AppearanceFactory.getAppearanceFactory( )
					.getAppearance( "MORPHED_MYST2" );
		else
		{
			Appearance ret = super.getAppearance( );
			if ( ret == null )
			{
				if ( getSex( ) == Player.MALE )
					setAppearance(
							AppearanceFactory.getAppearanceFactory( ).getAppearance(
									PlayerGenerator.getClassID( getPlayerClass( ) ) ) );
				else
					setAppearance(
							AppearanceFactory.getAppearanceFactory( ).getAppearance(
									PlayerGenerator.getClassID( getPlayerClass( ) )
											+ "_W" ) );
				ret = super.getAppearance( );
			}
			return ret;
		}
	}

	public Item getArmor( )
	{
		return armor;
	}

	public int getArmorDefense( )
	{
		if ( getArmor( ) != null )
		{
			if ( getPlayerClass( ) == CLASS_VAMPIREKILLER )
			{
				return (int) ( getArmor( ).getDefense( )
						+ Math.ceil( getPlayerLevel( ) / 3.0D ) );
			}
			else
			{
				return getArmor( ).getDefense( );
			}
		}
		else
			return 0;
	}

	public String getArmorDescription( )
	{
		if ( armor != null )
			return armor.getAttributesDescription( );
		else
			return "Nothing";
	}

	public int getAttack() {
		return attack;
	}

	public int getAttackCost() {
		return attackCost;
	}

	public Vector<Advancement> getAvailableAdvancements() {
		tmpAvailableAdvancements.clear();
		out:
		for (int i = 0; i < ADVANCEMENTS[getPlayerClass()].length; i++) {
			if (getFlag(ADVANCEMENTS[getPlayerClass()][i].getID())) {
				// Already has the advancement
				continue;
			}
			String[] requirements = ADVANCEMENTS[getPlayerClass()][i]
					.getRequirements();
			for (String requirement : requirements) {
				if (!getFlag(requirement)) {
					// Misses a requirement
					continue out;
				}
			}
			String[] bans = ADVANCEMENTS[getPlayerClass()][i].getBans();
			for (String ban : bans) {
				if (getFlag(ban)) {
					// Has a ban
					continue out;
				}
			}
			tmpAvailableAdvancements.add(ADVANCEMENTS[getPlayerClass()][i]);
		}
		return tmpAvailableAdvancements;
	}

	public Vector<Skill> getAvailableSkills() {
		availableSkills.removeAllElements();
		if (getFlag("PASIVE_DODGE"))
			availableSkills.add(skills.get("DODGE"));
		if (getFlag("PASIVE_DODGE2"))
			availableSkills.add(skills.get("DODGE2"));
		if (playerClass == CLASS_VAMPIREKILLER) {
			if (getFlag("SKILL_WARP_DASH"))
				availableSkills.add(skills.get("WARP_DASH"));
			if ( getFlag( "SKILL_AIR_DASH" ) )
				availableSkills.add( skills.get( "AIR_DASH" ) );
			if ( getFlag( "SKILL_SLIDEKICK" ) )
				availableSkills.add( skills.get( "SLIDE_KICK" ) );
			if ( getFlag( "SKILL_ITEM_BREAK" ) )
				availableSkills.add( skills.get( "ITEM_BREAK" ) );

			if ( getFlag( "SKILL_SOULBLAST" ) )
				availableSkills.add( skills.get( "SKILL_SOULBLAST" ) );
			if ( getFlag( "SKILL_SOULFLAME" ) )
				availableSkills.add( skills.get( "SKILL_SOULFLAME" ) );
			if ( getFlag( "SKILL_SOULICE" ) )
				availableSkills.add( skills.get( "SKILL_SOULICE" ) );
			if ( getFlag( "SKILL_SOULSAINT" ) )
				availableSkills.add( skills.get( "SKILL_SOULSAINT" ) );
			if ( getFlag( "SKILL_SOULWIND" ) )
				availableSkills.add( skills.get( "SKILL_SOULWIND" ) );

			availableSkills.add( skills.get( "MYSTIC_DAGGER" ) );
			availableSkills.add( skills.get( "MYSTIC_AXE" ) );
			if ( getFlag( "MYSTIC_HOLY_WATER" ) )
				availableSkills.add( skills.get( "MYSTIC_HOLYWATER" ) );
			if ( getFlag( "MYSTIC_HOLY_BIBLE" ) )
				availableSkills.add( skills.get( "MYSTIC_BIBLE" ) );
			if ( getFlag( "MYSTIC_STOPWATCH" ) )
				availableSkills.add( skills.get( "MYSTIC_STOPWATCH" ) );
			if ( getFlag( "MYSTIC_CROSS" ) )
				availableSkills.add( skills.get( "MYSTIC_CROSS" ) );
			if ( getFlag( "MYSTIC_FIST" ) )
				availableSkills.add( skills.get( "MYSTIC_FIST" ) );
			if ( getFlag( "MYSTIC_CRYSTAL" ) )
				availableSkills.add( skills.get( "MYSTIC_CRYSTAL" ) );
			if ( getFlag( "PASIVE_BACKFLIP" ) )
				availableSkills.add( skills.get( "BACKFLIP" ) );

		}
		else if ( playerClass == CLASS_RENEGADE )
		{
			availableSkills.add( skills.get( "FIREBALL" ) );
			availableSkills.add( skills.get( "SOULSTEAL" ) );
			if ( getFlag( "SKILL_SUMMONSOUL" ) )
				availableSkills.add( skills.get( "SUMMON_SPIRIT" ) );
			if ( getFlag( "SKILL_SOULSSTRIKE" ) )
				availableSkills.add( skills.get( "SKILL_SOULSSTRIKE" ) );
			if ( getFlag( "SKILL_FLAMESSHOOT" ) )
				availableSkills.add( skills.get( "SKILL_FLAMESSHOOT" ) );
			if ( getFlag( "SKILL_HELLFIRE" ) )
				availableSkills.add( skills.get( "SKILL_HELLFIRE" ) );

			availableSkills.add( skills.get( "MINOR_JINX" ) );
			if ( getFlag( "SKILL_DARKMETAMORPHOSIS" ) )
				availableSkills.add( skills.get( "BLOOD" ) );
			if ( getFlag( "SKILL_SHADETELEPORT" ) )
				availableSkills.add( skills.get( "TELEPORT" ) );

			if ( getFlag( "SKILL_WOLFMORPH2" ) )
				availableSkills.add( skills.get( "SKILL_WOLFMORPH2" ) );
			else if ( getFlag( "SKILL_WOLFMORPH" ) )
				availableSkills.add( skills.get( "SKILL_WOLFMORPH" ) );
			if ( getFlag( "SKILL_MYSTMORPH2" ) )
				availableSkills.add( skills.get( "SKILL_MYSTMORPH2" ) );
			else if ( getFlag( "SKILL_MYSTMORPH" ) )
				availableSkills.add( skills.get( "SKILL_MYSTMORPH" ) );
			if ( getFlag( "SKILL_BATMORPH2" ) )
				availableSkills.add( skills.get( "SKILL_BATMORPH2" ) );
			else if ( getFlag( "SKILL_BATMORPH" ) )
				availableSkills.add( skills.get( "SKILL_BATMORPH" ) );
		}
		else if ( playerClass == CLASS_INVOKER )
		{
			availableSkills.add( skills.get( "INVOKE_BIRD" ) );
			availableSkills.add( skills.get( "INVOKE_TURTLE" ) );
			if ( getFlag( "SKILL_CATSOUL" ) )
				availableSkills.add( skills.get( "INVOKE_CAT" ) );
			if ( getFlag( "SKILL_BIRDSEGG" ) )
				availableSkills.add( skills.get( "THROW_EGG" ) );
			if ( getFlag( "SKILL_MANIPULATE" ) )
				availableSkills.add( skills.get( "MANIPULATE" ) );
			if ( getFlag( "SKILL_DRAGONFIRE" ) )
				availableSkills.add( skills.get( "INVOKE_DRAGON" ) );
			if ( getFlag( "SKILL_INVOKEBIRD" ) )
				availableSkills.add( skills.get( "SUMMON_BIRD" ) );
			if ( getFlag( "SKILL_INVOKETURTLE" ) )
				availableSkills.add( skills.get( "SUMMON_TURTLE" ) );
			if ( getFlag( "SKILL_INVOKECAT" ) )
				availableSkills.add( skills.get( "SUMMON_CAT" ) );
			if ( getFlag( "SKILL_INVOKEEAGLE" ) )
				availableSkills.add( skills.get( "SUMMON_EAGLE" ) );
			if ( getFlag( "SKILL_INVOKETORTOISE" ) )
				availableSkills.add( skills.get( "SUMMON_TORTOISE" ) );
			if ( getFlag( "SKILL_INVOKETIGER" ) )
				availableSkills.add( skills.get( "SUMMON_TIGER" ) );
			if ( getFlag( "SKILL_INVOKEDRAGON" ) )
				availableSkills.add( skills.get( "SUMMON_DRAGON" ) );

			// availableSkills.add(skills.get("MAJOR_JINX"));

		}
		else if ( playerClass == CLASS_MANBEAST )
		{
			if ( getFlag( "SKILL_POWERBLOW3" ) )
				availableSkills.add( skills.get( "IMPACT_BLOW3" ) );
			else if ( getFlag( "SKILL_POWERBLOW2" ) )
				availableSkills.add( skills.get( "IMPACT_BLOW2" ) );
			else if ( getFlag( "SKILL_POWERBLOW" ) )
				availableSkills.add( skills.get( "IMPACT_BLOW" ) );
			availableSkills.add( skills.get( "CLAW_SWIPE" ) );
			if ( getFlag( "SKILL_ENERGYSCYTHE" ) )
				availableSkills.add( skills.get( "ENERGY_SCYTHE" ) );
			if ( getFlag( "SKILL_CLAWASSAULT" ) )
				availableSkills.add( skills.get( "CLAW_ASSAULT" ) );
			availableSkills.add( skills.get( "LUPINE_MORPH" ) );
			if ( getFlag( "SKILL_BEARMORPH" ) )
				availableSkills.add( skills.get( "BEAR_MORPH" ) );
			if ( getFlag( "SKILL_BEASTMORPH" ) )
				availableSkills.add( skills.get( "BEAST_MORPH" ) );
			if ( getFlag( "SKILL_DEMONMORPH" ) )
				availableSkills.add( skills.get( "DEMON_MORPH" ) );
			if ( getFlag( "SKILL_WEREWOLFMORPH" ) )
				availableSkills.add( skills.get( "WEREWOLF_MORPH" ) );
			if ( getFlag( Consts.F_COMPLETECONTROL ) )
				availableSkills.add( skills.get( "COMPLETECONTROL" ) );
			else if ( getFlag( Consts.F_SELFCONTROL ) )
				availableSkills.add( skills.get( "SELFCONTROL" ) );
			if ( getFlag( "HEALTH_REGENERATION" ) )
				availableSkills.add( skills.get( "REGEN" ) );
		}
		else if ( playerClass == CLASS_VANQUISHER )
		{
			availableSkills.add( skills.get( "HOMING_BALL" ) );
			availableSkills.add( skills.get( "CHARGE_BALL" ) );
			if ( getFlag( Consts.C_SPELL_FIRE ) )
				availableSkills.add( skills.get( "FLAME_SPELL" ) );
			if ( getFlag( Consts.C_SPELL_ICE ) )
				availableSkills.add( skills.get( "ICE_SPELL" ) );
			if ( getFlag( Consts.C_SPELL_LIT ) )
				availableSkills.add( skills.get( "LIT_SPELL" ) );
			if ( getFlag( "SKILL_MINDBLAST" ) )
				availableSkills.add( skills.get( "MINDBLAST" ) );
			if ( getFlag( "SKILL_TELEPORT" ) )
				availableSkills.add( skills.get( "TELEPORT" ) );
			if ( getFlag( "SKILL_RECOVER" ) )
				availableSkills.add( skills.get( "RECOVER" ) );
			if ( getFlag( "SKILL_CURE" ) )
				availableSkills.add( skills.get( "CURE" ) );
			if ( getFlag( "SKILL_ENCHANT" ) )
				availableSkills.add( skills.get( "ENCHANT" ) );
			if ( getFlag( "SKILL_ENERGYSHIELD" ) )
				availableSkills.add( skills.get( "ENERGYSHIELD" ) );
			if ( getFlag( "SKILL_LIGHT" ) )
				availableSkills.add( skills.get( "LIGHT" ) );
			if ( getFlag( "SKILL_MINDLOCK" ) )
				availableSkills.add( skills.get( "MINDLOCK" ) );
			if ( getFlag( "SKILL_MAJORJINX" ) )
				availableSkills.add( skills.get( "MAJOR_JINX" ) );

		}
		else if ( playerClass == CLASS_KNIGHT )
		{
			availableSkills.add( skills.get( "SHIELD_GUARD" ) );
		}
		if ( weaponSkill( ItemDefinition.CAT_RINGS ) > 1 )
		{
			availableSkills.add( skills.get( "DIVING_SLIDE" ) );
		}
		if ( weapon != null
				&& weapon.getWeaponCategory( ).equals( ItemDefinition.CAT_RINGS )
				&& weaponSkill( ItemDefinition.CAT_RINGS ) == 10 )
			availableSkills.add( skills.get( "SPINNING_SLICE" ) );
		if ( weapon != null
				&& weapon.getWeaponCategory( ).equals( ItemDefinition.CAT_WHIPS )
				&& weaponSkill( ItemDefinition.CAT_WHIPS ) == 10 )
			availableSkills.add( skills.get( "WHIRLWIND_WHIP" ) );
		if ( weapon != null
				&& weapon.getWeaponCategory( ).equals( ItemDefinition.CAT_STAVES )
				&& weaponSkill( ItemDefinition.CAT_STAVES ) == 10 )
			availableSkills.add( skills.get( "ENERGY_BEAM" ) );
		if ( weapon != null
				&& weapon.getWeaponCategory( ).equals( ItemDefinition.CAT_SWORDS )
				&& weaponSkill( ItemDefinition.CAT_SWORDS ) == 10 )
			availableSkills.add( skills.get( "FINAL_SLASH" ) );
		if ( ( weapon == null
				|| weapon.getWeaponCategory( ).equals( ItemDefinition.CAT_UNARMED ) )
				&& weaponSkill(ItemDefinition.CAT_UNARMED) == 10)
			availableSkills.add(skills.get("TIGER_CLAW"));
		if (weapon != null
				&& weapon.getWeaponCategory().equals(ItemDefinition.CAT_PISTOLS)
				&& weaponSkill(ItemDefinition.CAT_PISTOLS) == 10)
			availableSkills.add(skills.get("ENERGY_BURST"));

		return availableSkills;
	}

	public Vector<Advancement> getAvailableStatAdvancements() {
		tmpAvailableAdvancements.clear();
		for (int i = 0; i < STATADVANCEMENTS[getPlayerClass()].length; i++) {
			tmpAvailableAdvancements.add(STATADVANCEMENTS[getPlayerClass()][i]);
		}
		int rand = Util.rand(2, 3);
		for (int i = 0; i < rand; i++) {
			Advancement adv = (Advancement) Util.randomElementOf(ALL_ADVANCEMENTS);
			if ( !tmpAvailableAdvancements.contains( adv ) )
				tmpAvailableAdvancements.add( adv );
		}

		return tmpAvailableAdvancements;
	}

	public String[ ] getBannedArmors( )
	{
		return bannedArmors;
	}

	public int getBaseEvadeChance( )
	{
		return evadeChance;
	}

	public int getBaseSightRange( )
	{
		return baseSightRange;
	}

	public int getBreathing( )
	{
		return breathing;
	}

	public int getCarryMax( )
	{
		return carryMax;
	}

	public int getCastCost( )
	{
		return castCost;
	}

	public String getClassString( )
	{
		return classString;
	}

	public int getCoolness( )
	{
		return coolness;
	}

	public String getCustomMessage( String messageID )
	{
		return (String) customMessages.get( messageID );
	}

	public int getDaggerLevel( )
	{
		return daggerLevel;
	}

	public int getDarkSightRange( )
	{
		int base = baseSightRange + 7 + ( level.getMapCell( getPosition( ) ) != null
				&& level.getMapCell( getPosition( ) ).getHeight( ) > 0 ? 1 : 0 );
		if ( getFlag( Consts.ENV_FOG ) )
			base -= 6;
		if ( base < 1 )
			base = 1;
		return base;
	}

	public int getDefenseBonus( )
	{
		int ret = 0;
		if ( getPlayerClass( ) == Player.CLASS_MANBEAST )
			ret += Math.ceil( getPlayerLevel( ) / 2.5D );
		if ( hasIncreasedDefense( ) )
			ret++;
		ret += getMorphDefense( );
		return ret;
	}

	public int getDefenseCounter( )
	{
		return defenseCounter;
	}

	public String getDescription( )
	{
		return description;
	}

	public Monster getEnemy( )
	{
		return enemy;
	}

	public String getEquipedWeaponDescription( )
	{
		if ( weapon != null )
			return ( weapon
					.hasCounter( Consts.C_WEAPON_ENCHANTMENT ) ? "Enchanted " : "" )
					+ weapon.getAttributesDescription( );
		else
			return "Nothing";
	}

	public int getEvadeChance( )
	{
		int base = getBaseEvadeChance( ) + ( getFlag( "PASIVE_DODGE" ) ? 10 : 0 )
				+ ( getFlag( "PASIVE_DODGE2" ) ? 10 : 0 );
		if ( base > 90 )
			base = 90;
		return base;
	}

	public Game getGame( )
	{
		return game;
	}

	public GameSessionInfo getGameSessionInfo( )
	{
		return gameSessionInfo;
	}

	public int getGold( )
	{
		return gold;
	}

	public int getHearts( )
	{
		return hearts;
	}

	public int getHeartsMax( )
	{
		return heartMax;
	}

	public int getHits( )
	{
		return hits;
	}

	public int getHitsMax() {
		return hitsMax;
	}

	public Hostage getHostage() {
		return currentHostage;
	}

	public Vector<Equipment> getInventory() {
		Vector<Equipment> ret = new Vector<>();
		Enumeration<Equipment> x = inventory.elements();
		while (x.hasMoreElements())
			ret.add(x.nextElement());
		return ret;
	}

	public int getItemCount() {
		int eqCount = 0;
		Enumeration en = inventory.elements( );
		while ( en.hasMoreElements( ) )
			eqCount += ( (Equipment) en.nextElement( ) ).getQuantity( );
		return eqCount;
	}

	public int getKeys( )
	{
		return keys;
	}

	public String getLastIncrementString( )
	{
		int temp = 0;
		String tempStr = "";
		temp = getLastIncrement( INCREMENT_HITS );
		if ( temp > 0 )
		{
			tempStr += " Hits+" + temp;
		}
		temp = getLastIncrement( INCREMENT_HEARTS );
		if ( temp > 0 )
		{
			tempStr += " Hearts+" + temp;
		}
		temp = getLastIncrement( INCREMENT_ATTACK );
		if ( temp > 0 )
		{
			tempStr += " Atk+" + temp;
		}
		temp = getLastIncrement( INCREMENT_COMBAT );
		if ( temp > 0 )
		{
			tempStr += " Combat+" + temp;
		}
		temp = getLastIncrement( INCREMENT_INVOKATION );
		if ( temp > 0 )
		{
			tempStr += " Invoke+" + temp;
		}
		temp = getLastIncrement( INCREMENT_SPEED );
		if ( temp > 0 )
		{
			tempStr += " Speed+" + temp;
		}
		temp = getLastIncrement( INCREMENT_SOUL );
		if ( temp > 0 )
		{
			tempStr += " Soul+" + temp;
		}
		temp = getLastIncrement( INCREMENT_CARRYING );
		if ( temp > 0 )
		{
			tempStr += " Carrying+" + temp;
		}
		temp = getLastIncrement( INCREMENT_DEFENSE );
		if ( temp > 0 )
		{
			tempStr += " Defense+" + temp;
		}
		temp = getLastIncrement( INCREMENT_EVADE );
		if ( temp > 0 )
		{
			tempStr += " Evade+" + temp;
		}
		return tempStr;
	}

	public int getMorphDefense( )
	{
		if ( hasCounter( Consts.C_MYSTMORPH ) || hasCounter( Consts.C_MYSTMORPH2 ) )
		{
			return 1;
		}
		else if ( hasCounter( Consts.C_LUPINEMORPH ) )
			return 1;
		else if ( hasCounter( Consts.C_BEARMORPH ) )
			return 1;
		else if ( hasCounter( Consts.C_BEASTMORPH ) )
			return 2;
		else if ( hasCounter( Consts.C_DEMONMORPH ) )
			return 3;
		else if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
			return 4;
		return 0;
	}

	public Action getMysticAction( )
	{
		if ( getMysticWeapon( ) == -1 )
			return null;
		else
			return MYSTIC_ACTIONS[ getMysticWeapon( ) ];
	}

	public int getMysticWeapon( )
	{
		return currentMysticWeapon;
	}

	public String getName( )
	{
		return name;
	}

	public Monster getNearestMonster( )
	{
		VMonster monsters = level.getMonsters( );
		Monster nearMonster = null;
		int minDist = 150;
		for ( int i = 0; i < monsters.size( ); i++ ) {
			Monster monster = monsters.elementAt(i);
			if (monster instanceof NPC)
				continue;
			if (monster.getPosition().z != getPosition().z)
				continue;
			int distance = Position.flatDistance(level.getPlayer().getPosition(),
					monster.getPosition());
			if (distance < minDist) {
				minDist = distance;
				nearMonster = monster;
			}
		}
		return nearMonster;
	}

	public Position getNearestMonsterPosition( )
	{
		Monster nearMonster = getNearestMonster( );
		if ( nearMonster != null )
			return nearMonster.getPosition( );
		else
			return null;
	}

	public int getNextXP( )
	{
		return nextLevelXP;
	}

	public int getPlayerClass( )
	{
		return playerClass;
	}

	public PlayerEventListener getPlayerEventListener( )
	{
		return playerEventListener;
	}

	public int getPlayerLevel( )
	{
		return playerLevel;
	}

	public String getPlot( )
	{
		return plot;
	}

	public String getPlot2( )
	{
		return plot2;
	}

	public Position getPreviousPosition( )
	{
		if ( previousPosition == null )
			return getPosition( );
		else
			return previousPosition;
	}

	public int getPunchDamage( )
	{
		int punchDamage = (int) Math
				.floor( 1.5 * weaponSkill( ItemDefinition.CAT_UNARMED ) ) + 1;
		if ( hasCounter( Consts.C_BEASTMORPH ) )
		{
			punchDamage = 2 * punchDamage + getAttack( );
		}
		else if ( hasCounter( Consts.C_BEARMORPH ) )
		{
			punchDamage = (int) Math.ceil( 1.7 * punchDamage );
		}
		else if ( hasCounter( Consts.C_DEMONMORPH ) )
		{
			punchDamage = (int) Math.ceil( 2 * punchDamage );
		}
		else if ( hasCounter( Consts.C_LUPINEMORPH ) )
		{
			punchDamage = (int) Math.ceil( 1.5d * punchDamage );
		}
		else if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
		{
			punchDamage = (int) Math.ceil( 3 * punchDamage );
		}
		else if ( hasCounter( Consts.C_WOLFMORPH ) || hasCounter( Consts.C_WOLFMORPH2 ) )
		{
			punchDamage = getAttack( ) + 1 + Util.rand( 0, 2 );
		}
		else if ( hasCounter( Consts.C_POWERBLOW ) )
		{
			punchDamage = 10 + 2 * getAttack( ) + (int) Math.ceil( 1.3d * punchDamage );
		}
		else if ( hasCounter( Consts.C_POWERBLOW2 ) )
		{
			punchDamage = 16 + 2 * getAttack( ) + (int) Math.ceil( 1.5d * punchDamage );
		}
		else if ( hasCounter( Consts.C_POWERBLOW3 ) )
		{
			punchDamage = 25 + 3 * getAttack( ) + (int) Math.ceil( 1.7d * punchDamage );
		}
		else if ( getPlayerClass( ) == Player.CLASS_MANBEAST )
		{
			punchDamage = (int) Math.ceil( 1.3d * punchDamage );
		}
		return getAttack( ) + punchDamage;
	}
	public String getPunchDescription( )
	{
		String attackDescription = "punch";
		if ( hasCounter( Consts.C_BEASTMORPH ) )
		{
			attackDescription = "claw";
		}
		else if ( hasCounter( Consts.C_BEARMORPH ) )
		{
			attackDescription = "bash";
		}
		else if ( hasCounter( Consts.C_DEMONMORPH ) )
		{
			attackDescription = "claw";
		}
		else if ( hasCounter( Consts.C_LUPINEMORPH ) )
		{
			attackDescription = "slash at";
		}
		else if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
		{
			attackDescription = "slash through";
		}
		else if ( hasCounter( Consts.C_WOLFMORPH ) || hasCounter( Consts.C_WOLFMORPH2 ) )
		{
			attackDescription = "bite";
		}
		else if ( hasCounter( Consts.C_POWERBLOW ) )
		{
			attackDescription = "charge against";
		}
		else if ( hasCounter( Consts.C_POWERBLOW2 ) )
		{
			attackDescription = "charge against";
		}
		else if ( hasCounter( Consts.C_POWERBLOW3 ) )
		{
			attackDescription = "charge against";
		}
		else if ( getPlayerClass( ) == Player.CLASS_MANBEAST )
		{
			attackDescription = "slash at";
		}
		return attackDescription;
	}

	public int getPunchPush( )
	{
		int push = 0;
		if ( hasCounter( Consts.C_BEASTMORPH ) )
		{
			push = 3;
		}
		else if ( hasCounter( Consts.C_BEARMORPH ) )
		{
			push = 4;
		}
		else if ( hasCounter( Consts.C_LUPINEMORPH ) )
		{
			push = 2;
		}
		else if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
		{
			push = 3;
		}
		else if ( hasCounter( Consts.C_POWERBLOW ) )
		{
			push = 2;
		}
		else if ( hasCounter( Consts.C_POWERBLOW2 ) )
		{
			push = 3;
		}
		else if ( hasCounter( Consts.C_POWERBLOW3 ) )
		{
			push = 4;
		}
		return push;
	}

	public int getScore( )
	{
		return score;
	}

	public Item getSecondaryWeapon( )
	{
		return secondaryWeapon;
	}

	public String getSecondaryWeaponDescription( )
	{
		if ( getPlayerClass( ) == CLASS_VAMPIREKILLER )
		{
			if ( getMysticWeapon( ) != -1 )
				return weaponName( getMysticWeapon( ) );
			else
				return "None";
		}
		else
		{
			if ( getSecondaryWeapon( ) != null )
				return getSecondaryWeapon( ).getAttributesDescription( );
			else
				return "";
		}
	}

	public int getSex( )
	{
		return sex;
	}

	public Item getShield( )
	{
		return shield;
	}

	public int getShieldBlockChance( )
	{
		int blockChance = 0;
		if ( getShield( ) != null && ( getWeapon( ) == null
				|| ( getWeapon( ) != null && !getWeapon( ).isTwoHanded( ) ) ) )
		{
			if ( getPlayerClass( ) == CLASS_KNIGHT )
			{
				blockChance = getShield( ).getCoverage( );
			}
			else
			{
				blockChance = (int) ( getShield( ).getCoverage( ) / 2.0d );
			}
			blockChance += 2 * weaponSkill( ItemDefinition.CAT_SHIELD );
			if ( blockChance > 70 )
				return 70;
			else
				return blockChance;
		}
		else
		{
			return 0;
		}
	}

	public int getShieldCoverageChance( )
	{
		if ( getShield( ) != null && ( getWeapon( ) == null
				|| ( getWeapon( ) != null && !getWeapon( ).isTwoHanded( ) ) ) )
		{
			int coverageChance = 0;
			if ( getPlayerClass( ) == CLASS_KNIGHT )
			{
				coverageChance = 70;
			}
			else
			{
				coverageChance = getShield( ).getCoverage( );
			}
			return coverageChance;
		}
		else
		{
			return 0;
		}
	}

	public int getShotLevel( )
	{
		return shotLevel;
	}

	public int getSightRange( )
	{
		int base = baseSightRange + ( level.isDay( ) ? 3 : 0 )
				+ ( level.getMapCell( getPosition( ) ) != null ? level
						.getMapCell( getPosition( ) ).getHeight( ) > 0 ? 1 : 0 : 0 )
				+ ( hasCounter( Consts.C_MAGICLIGHT ) ? 3 : 0 )
				+ ( hasCounter( "LIGHT" ) ? 3 : 0 );
		if ( getFlag( Consts.ENV_FOG ) )
			base -= 2;
		if ( getFlag( Consts.ENV_RAIN ) || getFlag( Consts.ENV_THUNDERSTORM ) )
			base -= 1;
		if ( base < 1 )
			base = 1;
		return base;
	}

	public int getSoulPower( )
	{
		return soulPower;
	}

	public String getStatusString( )
	{
		String status = "";
		if ( isInvisible( ) )
			status += "Invisible ";
		if ( hasEnergyField( ) )
			status += "EnergyField ";
		if ( hasIncreasedDefense( ) )
			status += "Protected ";
		if ( hasIncreasedJumping( ) )
			status += "Spring ";
		if ( isInvincible( ) )
			status += "Invincible ";
		if ( hasCounter( Consts.C_BLOOD_THIRST ) )
			status += "Vampiric ";
		if ( hasCounter( Consts.C_BATMORPH ) )
			status += "Bat ";
		if ( hasCounter( Consts.C_BATMORPH2 ) )
			status += "HBat ";
		if ( hasCounter( Consts.C_MYSTMORPH ) )
			status += "Myst ";
		if ( hasCounter( Consts.C_MYSTMORPH2 ) )
			status += "HMyst ";
		if ( hasCounter( Consts.C_WOLFMORPH ) )
			status += "Wolf ";
		if ( hasCounter( Consts.C_WOLFMORPH2 ) )
			status += "HWolf ";
		if ( hasCounter( Consts.C_LUPINEMORPH ) )
			status += "Wolvish ";
		if ( hasCounter( Consts.C_BEARMORPH ) )
			status += "Bear ";
		if ( hasCounter( Consts.C_BEASTMORPH ) )
			status += "Beast ";
		if ( hasCounter( Consts.C_DEMONMORPH ) )
			status += "Demon ";
		if ( hasCounter( Consts.C_WEREWOLFMORPH ) )
			status += "WereWolf ";
		if ( hasCounter( Consts.C_TURTLESHELL ) )
		{
			status += "TurtleSoul ";
		}

		if ( hasCounter( "SHIELD_GUARD" ) )
			status += "Guarding ";

		if ( isPoisoned( ) )
			status += "Poison ";
		if ( isStunned( ) )
			status += "Stun ";
		if ( isPetrified( ) )
			status += "Stone ";
		if ( getHoverHeight( ) > 0 )
		{
			status += "Fly(" + getHoverHeight( ) + ")";
		}
		if ( hasCounter( Consts.C_FIREBALL_WHIP ) )
			status += "EnchantWhip ";
		if ( hasCounter( Consts.C_WEAPON_ENCHANTMENT ) )
			status += "EnchantWeapon ";
		if ( hasCounter( Consts.C_ENERGYSHIELD ) )
			status += "EnergyShield ";
		if ( hasCounter( Consts.C_MAGICLIGHT ) )
			status += "MagicLight ";
		if ( getFlag( "PLAYER_SWIMMING" ) )
			status += "Swimming (O2=" + getCounter( "OXYGEN" ) + ") ";
		return status;
	}

	public int getUnarmedAttack( )
	{
		return weaponSkill( ItemDefinition.CAT_UNARMED ) + 1;
	}

	public int getWalkCost( )
	{
		int walkCostBonus = 0;
		if ( hasCounter( Consts.C_WOLFMORPH2 ) )
			walkCostBonus = -25;
		else if ( hasCounter( Consts.C_WOLFMORPH ) )
			walkCostBonus = -20;
		if ( hasCounter( Consts.C_BATMORPH2 ) )
			walkCostBonus = -25;
		else if ( hasCounter( Consts.C_BATMORPH ) )
			walkCostBonus = -20;

		return walkCost + walkCostBonus > 0 ? walkCost + walkCostBonus : 1;
	}

	public Item getWeapon( )
	{
		return weapon;
	}

	public int getWeaponAttack( )
	{
		double multiplier = 1;
		if ( isSwimming( ) )
			multiplier = 0.5d;
		if ( weapon != null )
			if ( getPlayerClass( ) == Player.CLASS_VAMPIREKILLER )
			{
				return (int) ( multiplier * ( weaponSkill(
						weapon.getDefinition( ).getWeaponCategory( ) )
						+ (int) Math.round(
								getAttack( ) * ( weapon.getAttack( ) / 2.0D ) ) ) );
			}
			else
				return (int) ( multiplier * ( weapon.getAttack( )
						+ weaponSkill( weapon.getDefinition( ).getWeaponCategory( ) )
						+ getAttack( ) + ( weapon
								.hasCounter( Consts.C_WEAPON_ENCHANTMENT ) ? 2 : 0 ) ) );
		else
			return (int) ( multiplier * getPunchDamage( ) );
	}

	/*
	 * public void regen() { if (regenRate > 0) regenCont++; if (regenCont >
	 * regenRate){ regenCont = 0; recoverHits(1); } }
	 */

	public String getWeaponDescription( )
	{
		if ( getPlayerClass( ) == CLASS_VAMPIREKILLER )
			if ( getMysticWeapon( ) != -1 )
				return weaponName( getMysticWeapon( ) );
			else
				return "None";
		else if ( getWeapon( ) != null )
			if ( getWeapon( ).getReloadTurns( ) > 0 )
				return getWeapon( ).getDescription( ) + "("
						+ getWeapon( ).getRemainingTurnsToReload( ) + ")";
			else
				return getWeapon( ).getDescription( );
		else
			return "None";

	}

	public int getXp( )
	{
		return xp;
	}

	public boolean hasEnergyField( )
	{
		return energyFieldCounter > 0;
	}

	public boolean hasHostage( )
	{
		return currentHostage != null;
	}

	public boolean hasIncreasedDefense( )
	{
		return defenseCounter > 0;
	}

	public boolean hasIncreasedJumping( )
	{
		return jumpingCounter > 0;
	}

	public boolean hasItem( Item item )
	{
		return inventory.containsKey( item.getFullID( ) );
	}
	public boolean hasItemByID( String itemID )
	{
		return inventory.containsKey( itemID );
	}

	public void heal( )
	{
		hits = hitsMax;
	}

	public void increaseAttack( int param )
	{
		this.attack += param;
	}

	public void increaseCarryMax( int param )
	{
		this.carryMax += param;
	}

	public void increaseCoolness( int x )
	{
		coolness += x;
	}

	public void increaseDefense( int counter )
	{
		defense++;
		defenseCounter = counter;
	}

	public void increaseEvadeChance( int param )
	{
		this.evadeChance += param;
	}

	public void increaseHeartMax( int how )
	{
		heartMax += how;
	}

	public void increaseHitsMax( int how ) {
		hitsMax += how;
		int HITMAX = 60;
		if (hitsMax > HITMAX)
			hitsMax = HITMAX;
	}

	public void increaseJumping( int counter )
	{
		jumpingCounter = counter;
	}

	public void increaseKeys( )
	{
		keys++;
	}

	public void increaseMUpgradeCount( )
	{
		mUpgradeCount++;
	}

	public void increaseShot( )
	{
		if ( playerClass == CLASS_VAMPIREKILLER && shotLevel < 2 )
			shotLevel++;
	}

	public void increaseSoulPower( int param )
	{
		this.soulPower += param;
	}

	public void increaseWeaponSkill( String category )
	{
		Counter c = ( (Counter) weaponSkillsCounters.get( category ) );
		Counter s = ( (Counter) weaponSkills.get( category ) );

		c.increase( );
		if ( c.getCount( ) > s.getCount( ) * 80 + 10 )
		{
			c.reset( );
			if ( s.getCount( ) == 9 )
			{
				if ( getFlag( "WEAPON_MASTER" ) && getPlayerClass( ) != CLASS_KNIGHT )
				{

				}
				else
				{
					UserInterface.getUI( )
							.showImportantMessage( "You have become a master with "
									+ ItemDefinition.getCategoryDescription( category )
									+ "!" );
					s.increase( );
					setFlag( "WEAPON_MASTER", true );
				}
			}
			else if ( s.getCount( ) < 10 )
			{
				UserInterface.getUI( )
						.showImportantMessage( "You become better with "
								+ ItemDefinition.getCategoryDescription( category )
								+ ". Press Space to continue." );
				s.increase( );
			}
		}
	}

	public void increaseWeaponSkillLevel( String category )
	{
		Counter c = ( (Counter) weaponSkillsCounters.get( category ) );
		Counter s = ( (Counter) weaponSkills.get( category ) );
		c.reset( );
		if ( s.getCount( ) < 10 )
		{
			s.increase( );
		}
	}

	public void increaseWhip( )
	{
		if ( !( getPlayerClass( ) == CLASS_VAMPIREKILLER ) )
			return;
		if ( whipLevel < 2 )
			whipLevel++;
		else
			return;
		switch ( whipLevel )
		{
		case 1:
			level.addMessage( "Your leather whip turns into a chain whip!" );
			weapon = CHAIN_WHIP;
			break;
		case 2:
			level.addMessage( "Your chain whip turns into the Vampire Killer!" );
			weapon = VAMPIRE_WHIP;
		}
	}

	public void informPlayerEvent( int code )
	{
		Debug.enterMethod( this, "informPlayerEvent", code + "" );
		if ( playerEventListener != null )
			playerEventListener.informEvent( code );
		Debug.exitMethod( );
	}

	public void informPlayerEvent( int code, Object param )
	{
		playerEventListener.informEvent( code, param );
	}

	public boolean isDoNotRecordScore( )
	{
		return doNotRecordScore;
	}

	/*
	 * public boolean deservesHighMystics(){ return score > 20000 && playerLevel >
	 * 6; }
	 */

	public boolean isEthereal( )
	{
		return hasCounter( Consts.C_MYSTMORPH ) || hasCounter( Consts.C_MYSTMORPH2 );
		// return false;
	}

	public boolean isFainted( )
	{
		return faintCount > 0;
	}

	public boolean isFireWhip( )
	{
		return weapon == FLAME_WHIP;
	}

	public boolean isFlying( )
	{
		return hasCounter( Consts.C_BATMORPH ) || hasCounter( Consts.C_BATMORPH2 )
				|| isEthereal( );
	}

	public boolean isInvincible( )
	{
		return invincibleCount > 0;
	}

	public boolean isInvisible( )
	{
		return invisibleCount > 0;
	}

	public boolean isLightingWhip( )
	{
		return weapon == LIT_WHIP;
	}

	public boolean isMorphed( )
	{
		return hasCounter( Consts.C_LUPINEMORPH ) || hasCounter( Consts.C_BEARMORPH )
				|| hasCounter( Consts.C_BEASTMORPH ) || hasCounter( Consts.C_DEMONMORPH )
				|| hasCounter( Consts.C_WEREWOLFMORPH ) || hasCounter( Consts.C_BATMORPH )
				|| hasCounter( Consts.C_BATMORPH2 ) || hasCounter( Consts.C_WOLFMORPH )
				|| hasCounter( Consts.C_WOLFMORPH2 ) || hasCounter( Consts.C_MYSTMORPH )
				|| hasCounter( Consts.C_MYSTMORPH2 );
	}

	public boolean isPetrified( )
	{
		return petrifyCount > 0;
	}

	public boolean isPoisoned( )
	{
		return poisonCount > 0;
	}

	public boolean isStunned( )
	{
		return stunCount > 0;
	}

	public boolean isSwimming( )
	{
		Cell mapcell = level.getMapCell( getPosition( ) );
		return mapcell != null && ( mapcell.isWater( ) || mapcell.isShallowWater( ) );
	}

	public boolean isThornWhip( )
	{
		return weapon == THORN_WHIP;
	}

	public boolean justJumped( )
	{
		return justJumped;
	}

	public void land( )
	{
		Debug.enterMethod( this, "land" );
		landOn( getPosition( ) );
		Debug.exitMethod( );
	}

	/**
	 * Lands on the destination point, steping on height changing triggers
	 */
	public void landOn( Position destinationPoint )
	{
		landOn( destinationPoint, true );
	}

	/**
	 * Lands on the destination point
	 * 
	 * @param destinationPoint
	 * @param step
	 *            If true, step on height changing triggers
	 */
	public void landOn( Position destinationPoint, boolean step )
	{
		Debug.enterMethod( this, "landOn", destinationPoint );
		Cell destinationCell = level.getMapCell( destinationPoint );
		if ( destinationCell == null || destinationCell.isEthereal( ) )
		{
			destinationPoint = level.getDeepPosition( destinationPoint );
			if ( destinationPoint == null )
			{
				level.addMessage( "You fall into a endless pit!" );
				gameSessionInfo.setDeathCause( GameSessionInfo.ENDLESS_PIT );
				hits = -1;
				informPlayerEvent( Player.DEATH );
				Debug.exitMethod( );
				return;
			}
			else
			{
				destinationCell = level.getMapCell( destinationPoint );
			}
		}

		setPosition( destinationPoint );

		if ( destinationCell.isSolid( ) && !isEthereal( ) )
		{
			// Tries to land on a freesquare around
			Position tryp = getFreeSquareAround( destinationPoint );
			if ( tryp == null )
			{
				level.addMessage( "You are smashed inside the "
						+ destinationCell.getShortDescription( ) + "!" );
				gameSessionInfo.setDeathCause( GameSessionInfo.SMASHED );
				hits = -1;
				informPlayerEvent( Player.EVT_SMASHED );
				Debug.exitMethod( );
				return;
			}
			else
			{
				landOn( tryp );
				Debug.exitMethod( );
				return;
			}

		}
		if ( destinationCell.getDamageOnStep( ) > 0 )
		{
			if ( !isInvincible( ) )
			{
				StringBuffer buff = new StringBuffer( "You are injured by the "
						+ destinationCell.getShortDescription( ) + "!" );
				selfDamage( buff, Player.DAMAGE_WALKED_ON_LAVA, new Damage( 2, false ) );
				level.addMessage( buff.toString( ) );
			}
		}

		if ( step && destinationCell.getHeightMod( ) != 0 ) {
			setPosition(Position.add(destinationPoint,
					new Position(0, 0, destinationCell.getHeightMod())));
		}

		if (destinationCell.isShallowWater()) {
			level.addMessage(
					"You swim in the " + destinationCell.getShortDescription() + "!");
		}
		Vector<Item> destinationItems = level.getItemsAt(destinationPoint);
		if (destinationItems != null) {
			if (destinationItems.size() == 1)
				level.addMessage("There is a "
						+ ((Item) destinationItems.elementAt(0)).getDescription()
						+ " here");
			else
				level.addMessage("There are several items here");
		}

		Actor aActor = level.getActorAt( destinationPoint );
		if ( aActor instanceof Hostage )
		{
			if ( !hasHostage( ) && !( (Hostage) aActor ).isRescued( ) )
			{
				setHostage( (Hostage) aActor );
				addHistoricEvent( "rescued " + aActor.getDescription( ) + " from the "
						+ level.getDescription( ) );
				level.removeMonster( (Monster) aActor );
			}
		}

		Feature[ ] destinationFeatures = level.getFeaturesAt( destinationPoint );
		Feature destinationFeature = null;
		boolean played = false;
		if ( destinationFeatures != null )
		{
			for ( int i = 0; i < destinationFeatures.length; i++ )
			{
				destinationFeature = destinationFeatures[ i ];
				if ( destinationFeature.getKeyCost( ) > 0 )
				{
					reduceKeys( destinationFeature.getKeyCost( ) );
					// Debug.say("I destroy the "+destinationFeature);
					level.destroyFeature( destinationFeature );
				}

				if ( destinationFeature.getID( ).equals( "TELEPORT" ) )
				{
					if ( getGold( ) > 1000 )
					{
						UserInterface.getUI( ).showMessage(
								"Drop a thousand in gold to return to Petra? [Y/N]" );
						if ( UserInterface.getUI( ).prompt( ) )
						{
							if ( getHostage( ) != null )
							{
								UserInterface.getUI( ).showMessage( "Abandon "
										+ getHostage( ).getDescription( ) + "? [Y/N]" );
								if ( UserInterface.getUI( ).prompt( ) )
								{
									abandonHostage( );
								}
								else
								{
									return;
								}
							}
							SFXManager.play( "wav/loutwarp.wav" );
							informPlayerEvent( Player.EVT_GOTO_LEVEL, "TOWN0" );
							getLevel( ).setLevelNumber( 0 );
							landOn( Position.add( getLevel( ).getExitFor( "FOREST0" ),
									new Position( -1, 0, 0 ) ) );
							reduceGold( 1000 );

							return;
						}
					}
					else
					{
						level.addMessage(
								"There is something engraved here: \"Of Gold A Thousand Put Here And Be Gone\"" );
					}
				}

				if ( destinationFeature.getHeightMod( ) != 0 )
				{
					setPosition( Position.add( destinationPoint,
							new Position( 0, 0, destinationFeature.getHeightMod( ) ) ) );
				}
				if ( destinationFeature.getHeartPrize( ) > 0 )
				{
					level.addMessage( "You get " + destinationFeature.getHeartPrize( )
							+ " hearts" );
					addHearts( destinationFeature.getHeartPrize( ) );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/pickup.wav" );

					}
				}

				if ( destinationFeature.getScorePrize( ) > 0 )
				{
					level.addMessage( "You pickup the "
							+ destinationFeature.getDescription( ) + "." );
					addGold( destinationFeature.getScorePrize( ) );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/bonusblp.wav" );
					}
				}

				if ( destinationFeature.getKeyPrize( ) > 0 )
				{
					level.addMessage( "You find " + destinationFeature.getKeyPrize( )
							+ " castle key!" );
					addKeys( destinationFeature.getKeyPrize( ) );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/bonusblp.wav" );
					}
				}
				if ( destinationFeature.getUpgradePrize( ) > 0 )
				{
					if ( whipLevel < 2 )
						increaseWhip( );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/bonusblp.wav" );
					}
				}

				if ( destinationFeature.getMysticWeaponPrize( ) != -1 )
				{
					if ( getMysticWeapon( ) != -1 )
					{
						Position tryp = getFreeSquareAround( destinationPoint );
						if ( tryp != null )
						{
							level.addFeature(
									getFeatureNameForMystic( getMysticWeapon( ) ), tryp );
						}
					}
					level.addMessage(
							"You get the "
									+ Player.weaponName(
											destinationFeature.getMysticWeaponPrize( ) )
									+ "!" );
					setMysticWeapon( destinationFeature.getMysticWeaponPrize( ) );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/bonusblp.wav" );
					}
				}

				if ( destinationFeature.getHealPrize( ) > 0 )
				{
					level.addMessage(
							"You eat the " + destinationFeature.getDescription( ) + "!" );
					setHits( getHits( ) + destinationFeature.getHealPrize( ) );
					level.destroyFeature( destinationFeature );
					if ( !played )
					{
						played = true;
						SFXManager.play( "wav/bonusblp.wav" );
					}
				}

				if ( destinationFeature.getEffect( ) != null )
					if ( destinationFeature.getEffect( ).equals( "ROSARY" ) )
					{
						invokeRosary( );
						level.destroyFeature( destinationFeature );
					}
					else if ( destinationFeature.getEffect( ).equals( "SPAWN_TREASURE" ) )
					{
						level.addMessage( "A treasure rises from the ground!" );
						level.spawnTreasure( );
						level.destroyFeature( destinationFeature );
					}
					else if ( destinationFeature.getEffect( ).equals( "MUPGRADE" ) )
					{
						level.addMessage(
								"Your " + getWeaponDescription( ) + " gets stronger" );
						increaseShot( );
						level.destroyFeature( destinationFeature );
						if ( !played )
						{
							played = true;
							SFXManager.play( "wav/bonusblp.wav" );
						}
					}
					else if ( destinationFeature.getEffect( ).equals( "INVISIBILITY" ) )
					{
						level.addMessage( "You drink the potion of invisibility!" );
						setInvisible( 30 );
						level.destroyFeature( destinationFeature );
						if ( !played )
						{
							played = true;
							SFXManager.play( "wav/bonusblp.wav" );
						}
					}

				/*
				 * if (destinationFeature.getTrigger() != null) if
				 * (destinationFeature.getTrigger().equals("ENDGAME")) ; /*if
				 * (aPlayer.getKeys() == 10)
				 * aPlayer.informPlayerEvent(Player.OPENEDCASTLE);
				 */
				Feature pred = destinationFeature;
				destinationFeature = level.getFeatureAt( destinationPoint );
				if ( destinationFeature == pred )
					destinationFeature = null;
			}
		}

		if ( level.isExit( getPosition( ) ) )
		{
			String exit = level.getExitOn( getPosition( ) );
			if ( exit.equals( "_START" ) || exit.startsWith( "#" ) )
			{
				// Do nothing. This must be changed with startsWith("_");
			}
			/*
			 * else if (exit.equals("_NEXT")){ informPlayerEvent(Player.EVT_NEXT_LEVEL); }
			 * else if (exit.equals("_BACK")){ informPlayerEvent(Player.EVT_BACK_LEVEL); }
			 */else
			{
				informPlayerEvent( Player.EVT_GOTO_LEVEL, exit );
			}

		}
		Debug.exitMethod( );
	}

	public void morph(	String morphID, int count, boolean smallMorph, boolean bigMorph,
						int morphStrength, int loseMindChance )
	{
		deMorph( );
		if ( getFlag( Consts.F_SELFCONTROL ) )
			loseMindChance = (int) Math.floor( loseMindChance / 2.0D );
		if ( getFlag( Consts.F_COMPLETECONTROL ) )
			loseMindChance = 0;

		if ( Util.chance( loseMindChance ) )
		{
			level.addMessage( "You lose your mind!" );
			setFlag( "KEEPMESSAGES", true );
			originalSelector = getSelector( );
			setCounter( "REGAIN_SHAPE", count );
			setSelector( SelectorFactory.getSelectorFactory( )
					.getSelector( "WILD_MORPH_AI" ) );
		}

		// Drop items
		Item weapon = getWeapon( );
		if ( weapon != null )
		{
			level.addMessage( "You drop your " + weapon.getDescription( ) );
			carryOrDrop( weapon );
			setWeapon( null );
		}

		if ( getSecondaryWeapon( ) != null )
		{
			level.addMessage(
					"You drop your " + getSecondaryWeapon( ).getDescription( ) );
			carryOrDrop( getSecondaryWeapon( ) );
			setSecondaryWeapon( null );
		}

		if ( getShield( ) != null )
		{
			level.addMessage( "You drop your " + getShield( ).getDescription( ) );
			carryOrDrop( getShield( ) );
			setShield( null );
		}

		Item armor = getArmor( );
		if ( armor != null )
		{
			if ( bigMorph )
			{
				if ( armor.getDefense( ) > morphStrength )
				{
					StringBuffer buff = new StringBuffer(
							"Your armor is too strong! You feel trapped! You are injured!" );
					selfDamage( buff, Player.DAMAGE_MORPHED_WITH_STRONG_ARMOR,
							new Damage( 10, true ) );
					level.addMessage( buff.toString( ) );
					return;
				}
				level.addMessage( "You destroy your " + armor.getDescription( ) + "!" );
				setArmor( null );
			}
			else if ( smallMorph )
			{
				level.addMessage( "Your " + armor.getDescription( ) + " falls." );
				carryOrDrop( armor );
				setArmor( null );
			}
		}

		setCounter( morphID, count );
	}

	public void putCustomMessage( String messageID, String text )
	{
		customMessages.put( messageID, text );
	}

	public void recoverHits( int i )
	{
		hits += i;
		if ( hits > hitsMax )
			hits = hitsMax;
	}

	public void recoverHitsP( int p )
	{
		int recovery = (int) Math.round( (double) getHitsMax( ) * ( p / 100.0D ) );
		recoverHits( recovery );
	}

	public void reduceAttackCost( int param )
	{
		this.attackCost -= param;
	}

	public void reduceCastCost( int param )
	{
		this.castCost -= param;
	}

	public void reduceCoolness( int x )
	{
		coolness -= x;
	}

	public void reduceGold( int q )
	{
		gold -= q;
	}

	public void reduceHearts( int jijiji )
	{
		hearts -= jijiji;
	}

	public void reduceKeys( int k )
	{
		keys -= k;
	}

	public void reduceQuantityOf( Item what )
	{
		String toAddID = what.getFullID( );
		Equipment equipment = (Equipment) inventory.get( toAddID );
		equipment.reduceQuantity( );
		if ( equipment.isEmpty( ) )
			removeItem( equipment );
	}

	public void reduceWalkCost( int param )
	{
		this.walkCost -= param;
	}

	public void resetLastIncrements( )
	{
		lastIncrements.clear( );
	}

	public void resetWeaponSkillLevel( String category )
	{
		weaponSkills.put( category, new Counter( 0 ) );
		weaponSkillsCounters.put( category, new Counter( 0 ) );
	}

	public void see( )
	{
		// fov.startCircle(getLevel(), getPosition().x, getPosition().y,
		// getSightRange());
		fov.startCircle( getLevel( ), getPosition( ).x, getPosition( ).y,
				getDarkSightRange( ) );
	}

	public boolean sees( Monster m )
	{
		return sees( m.getPosition( ) );
	}

	public boolean sees( Position p )
	{
		return level.isVisible( p.x, p.y );
	}

	public void selfDamage( StringBuffer message, int damageType, Damage dam )
	{
		damage( message, dam );
		if ( hits < 0 )
		{
			switch ( damageType )
			{
			case Player.DAMAGE_MORPHED_WITH_STRONG_ARMOR:
				gameSessionInfo.setDeathCause( GameSessionInfo.STRANGLED_BY_ARMOR );
				break;
			case Player.DAMAGE_WALKED_ON_LAVA:
				gameSessionInfo.setDeathCause( GameSessionInfo.BURNED_BY_LAVA );
				break;
			}
		}

	}

	public void setAdvancementLevels( int[ ] advancementLevels )
	{
		this.advancementLevels = advancementLevels;
		statAdvancementLevels = new int[ advancementLevels.length - 1 ];
		for ( int i = 0; i < advancementLevels.length - 1; i++ )
		{
			statAdvancementLevels[ i ] = (int) Math.ceil(
					( advancementLevels[ i ] + advancementLevels[ i + 1 ] ) / 2.0D );
		}
	}

	public void setArmor( Item value )
	{
		armor = value;
	}

	public void setAttack( int attack )
	{
		this.attack = attack;
	}

	public void setAttackCost( int attackCost )
	{
		this.attackCost = attackCost;
	}

	public void setBannedArmors( String[ ] bannedArmors )
	{
		this.bannedArmors = bannedArmors;
	}

	public void setBaseEvadeChance( int evadeChance )
	{
		this.evadeChance = evadeChance;
	}

	public void setBaseSightRange( int baseSightRange )
	{
		this.baseSightRange = baseSightRange;
	}

	public void setBreathing( int breathing )
	{
		this.breathing = breathing;
	}

	public void setCarryMax( int value )
	{
		carryMax = value;
	}

	public void setCastCost( int castCost )
	{
		this.castCost = castCost;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public void setDoNotRecordScore( boolean doNotRecordScore )
	{
		this.doNotRecordScore = doNotRecordScore;
	}

	public void setEnemy( Monster enemy )
	{
		this.enemy = enemy;
	}

	public void setEnergyField( int counter )
	{
		energyFieldCounter = counter;
	}

	public void setFainted( int counter )
	{
		faintCount = counter;
	}

	public void setFireWhip( )
	{
		if ( playerClass == CLASS_VAMPIREKILLER )
			weapon = FLAME_WHIP;
	}

	public void setFOV( FOV fov )
	{
		this.fov = fov;
	}

	public void setGame( Game game )
	{
		this.game = game;
	}

	public void setGameSessionInfo( GameSessionInfo value )
	{
		gameSessionInfo = value;
	}

	public void setGold( int x )
	{
		gold = x;
	}

	public void setHeartMax( int value )
	{
		heartMax = value;
	}

	public void setHearts( int value )
	{
		hearts = value;
	}

	public void setHits( int value )
	{
		hits = value;
		if ( hits > hitsMax )
			hits = hitsMax;
	}
	public void setHitsMax( int hitsMax )
	{
		this.hitsMax = hitsMax;
	};

	public void setHostage( Hostage who )
	{
		currentHostage = who;
	}

	public void setInvincible( int counter )
	{
		invincibleCount = counter;
	}

	public void setInvisible( int counter )
	{
		invisibleCount = counter;
	}

	public void setJustJumped( boolean val )
	{
		justJumped = val;
	}

	public void setLitWhip( )
	{
		if ( playerClass == CLASS_VAMPIREKILLER )
			weapon = LIT_WHIP;
	}

	public void setMysticWeapon( int value )
	{
		currentMysticWeapon = value;
	}

	public void setName( String value )
	{
		name = value;
	}

	public void setPetrify( int counter )
	{
		petrifyCount = counter;
	}

	public void setPlayerClass( int value )
	{
		playerClass = value;
		switch ( playerClass )
		{
		case CLASS_VAMPIREKILLER:
			classString = "Vampire Killer";
			break;
		case CLASS_INVOKER:
			classString = "Soul Master";
			break;
		case CLASS_KNIGHT:
			classString = "Knight";
			break;
		case CLASS_MANBEAST:
			if ( getSex( ) == MALE )
				classString = "Man Beast";
			else
				classString = "Woman Beast";
			break;
		case CLASS_RENEGADE:
			classString = "Renegade";
			break;
		case CLASS_VANQUISHER:
			classString = "Vanquisher";
			break;
		}
	}

	public void setPlayerEventListener( PlayerEventListener value )
	{
		playerEventListener = value;
	}

	public void setPlayerLevel( int level )
	{
		playerLevel = level;
	}

	public void setPlot( String plot, String plot2 )
	{
		this.plot = plot;
		this.plot2 = plot2;
	}

	public void setPoison( int counter )
	{
		poisonCount = counter;
	}

	public void setPreviousPosition( )
	{
		previousPosition = getPosition( );
	}

	public void setSecondaryWeapon( Item value )
	{
		secondaryWeapon = value;
	}

	public void setSex( int value )
	{
		sex = value;
	}

	public void setShield( Item value )
	{
		shield = value;
	}

	public void setShieldGuard( int direction, int turns )
	{
		setCounter( "SHIELD_GUARD", turns );
		switch ( direction )
		{
		case Action.UP:
			blockDirection = Action.DOWN;
			blockDirection1 = Action.DOWNLEFT;
			blockDirection2 = Action.DOWNRIGHT;
			break;
		case Action.DOWN:
			blockDirection = Action.UP;
			blockDirection1 = Action.UPLEFT;
			blockDirection2 = Action.UPRIGHT;
			break;
		case Action.LEFT:
			blockDirection = Action.RIGHT;
			blockDirection1 = Action.UPRIGHT;
			blockDirection2 = Action.DOWNRIGHT;
			break;
		case Action.RIGHT:
			blockDirection = Action.LEFT;
			blockDirection1 = Action.UPLEFT;
			blockDirection2 = Action.DOWNLEFT;
			break;
		case Action.UPLEFT:
			blockDirection = Action.DOWNRIGHT;
			blockDirection1 = Action.RIGHT;
			blockDirection2 = Action.DOWN;
			break;
		case Action.UPRIGHT:
			blockDirection = Action.DOWNLEFT;
			blockDirection1 = Action.LEFT;
			blockDirection2 = Action.DOWN;
			break;
		case Action.DOWNRIGHT:
			blockDirection = Action.UPLEFT;
			blockDirection1 = Action.LEFT;
			blockDirection2 = Action.UP;
			break;
		case Action.DOWNLEFT:
			blockDirection = Action.UPRIGHT;
			blockDirection1 = Action.RIGHT;
			blockDirection2 = Action.UP;
			break;
		}
	}

	public void setSoulPower( int sp )
	{
		this.soulPower = sp;
	}

	public void setStun( int counter )
	{
		stunCount = counter;
	}

	public void setThornWhip( )
	{
		if ( playerClass == CLASS_VAMPIREKILLER )
			weapon = THORN_WHIP;
	}

	public void setWalkCost( int walkCost )
	{
		this.walkCost = walkCost;
	}

	public void setWeapon( Item value )
	{
		weapon = value;
	}

	public int stareMonster( )
	{
		Monster nearest = getNearestMonster( );
		if ( nearest == null )
			return -1;
		else
			return stareMonster( nearest );
	}

	public int stareMonster( Monster who )
	{
		if ( who.getPosition( ).z != getPosition( ).z )
			return -1;
		if ( who.wasSeen( ) )
		{
			Position pp = who.getPosition( );
			if ( pp.x == getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
				{
					return Action.DOWN;
				}
				else
				{
					return Action.UP;
				}
			}
			else if ( pp.y == getPosition( ).y )
			{
				if ( pp.x > getPosition( ).x )
				{
					return Action.RIGHT;
				}
				else
				{
					return Action.LEFT;
				}
			}
			else if ( pp.x < getPosition( ).x )
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNLEFT;
				else
					return Action.UPLEFT;
			}
			else
			{
				if ( pp.y > getPosition( ).y )
					return Action.DOWNRIGHT;
				else
					return Action.UPRIGHT;
			}
		}
		return -1;
	}

	public void updateStatus( )
	{

		if ( getCounter( Consts.C_BATMORPH ) == 1
				|| getCounter( Consts.C_BATMORPH2 ) == 1 )
		{
			level.addMessage( "You regain your human shape!" );
			land( );
		}

		if ( getCounter( Consts.C_MYSTMORPH ) == 1
				|| getCounter( Consts.C_MYSTMORPH2 ) == 1
				|| getCounter( Consts.C_BEARMORPH ) == 1
				|| getCounter( Consts.C_LUPINEMORPH ) == 1
				|| getCounter( Consts.C_BEASTMORPH ) == 1
				|| getCounter( Consts.C_DEMONMORPH ) == 1
				|| getCounter( Consts.C_WEREWOLFMORPH ) == 1 )
		{
			level.addMessage( "You regain your human shape!" );
			land( );
		}

		if ( getCounter( "REGAIN_SHAPE" ) == 1 )
		{
			setSelector( originalSelector );
			setFlag( "KEEPMESSAGES", false );
		}

		for ( int i = 0; i < counteredItems.size( ); i++ )
		{
			Item item = (Item) counteredItems.elementAt( i );
			item.reduceCounters( this );
			if ( !item.hasCounters( ) )
			{
				counteredItems.remove( item );
			}
		}

		super.updateStatus( );

		if ( hasIncreasedDefense( ) )
			defenseCounter--;
		if ( isInvisible( ) )
			invisibleCount--;
		if ( hasIncreasedJumping( ) )
			jumpingCounter--;
		if ( isInvincible( ) )
			invincibleCount--;
		if ( hasEnergyField( ) )
			energyFieldCounter--;

		if ( isPoisoned( ) )
		{
			poisonCount--;
			if ( !isPoisoned( ) )
				level.addMessage( "The poison leaves your blood." );
		}
		if ( isStunned( ) )
			stunCount--;
		if ( isPetrified( ) )
			petrifyCount--;
		if ( isFainted( ) )
			faintCount--;

		if ( isPoisoned( ) )
		{
			if ( Util.chance( 40 ) )
			{
				StringBuffer buff = new StringBuffer(
						"You feel the poison coursing through your veins!" );
				selfDamage( buff, Player.DAMAGE_POISON, new Damage( 3, true ) );
				level.addMessage( buff.toString( ) );
			}
		}
		if ( getHoverHeight( ) > 0 )
			if ( hasCounter( Consts.C_BATMORPH ) || hasCounter( Consts.C_BATMORPH2 ) )
				;
			else
				setHoverHeight( getHoverHeight( ) - 4 );
		if ( level.getMapCell( getPosition( ) ) != null
				&& level.getMapCell( getPosition( ) ).isWater( ) )
		{
			if ( getFlag( "PLAYER_SWIMMING" ) )
			{
				if ( getCounter( "OXYGEN" ) == 0 )
				{
					drown( );
				}
				else if ( getCounter( "OXYGEN" ) == 5 )
				{
					level.addMessage( "You are almost drown!" );
				}
				else if ( getCounter( "OXYGEN" ) == 15 )
				{
					level.addMessage( "You are drowning!" );
				}
			}
			else
			{
				setCounter( "OXYGEN", getBreathing( ) );
				level.addMessage( "You start swimming!" );
				setFlag( "PLAYER_SWIMMING", true );
			}
		}
		else
		{
			setFlag( "PLAYER_SWIMMING", false );
		}
		// regen();
	}

	public int weaponSkill( String catID )
	{
		return ( (Counter) weaponSkills.get( catID ) ).getCount( );
	}

	private void carryOrDrop( Item item )
	{
		if ( canCarry( ) )
		{
			addItem( item );
		}
		else
		{
			level.addItem( getPosition( ), item );
		}
	}

	private void damage( StringBuffer message, Damage dam )
	{
		if ( !level.isDay( ) )
			dam.boostDamage( 1 );
		if ( getFlag( Consts.ENV_THUNDERSTORM ) )
			dam.boostDamage( 2 );
		if ( getFlag( Consts.ENV_SUNNY ) )
		{
			if ( dam.getDamage( ) > 2 )
				dam.reduceDamage( 2 );
		}

		if ( hasCounter( Consts.C_ENERGYSHIELD ) )
		{
			level.addMessage( "The energy shield covers you!" );
			dam.setDamage( (int) Math.ceil( dam.getDamage( ) * 2.0d / 3.0d ) );
		}

		if ( hasCounter( Consts.C_TURTLESHELL ) )
		{
			level.addMessage( "The turtle soul covers you!" );
			dam.setDamage( (int) Math.ceil( dam.getDamage( ) * 2.0d / 3.0d ) );
		}

		if ( !dam.ignoresArmor( ) )
		{
			dam.reduceDamage( getArmorDefense( ) );
		}
		dam.reduceDamage( getDefenseBonus( ) );

		if ( hasCounter( "REGAIN_SHAPE" ) )
		{
			setSelector( originalSelector );
			setFlag( "KEEPMESSAGES", false );
			setCounter( "REGAIN_SHAPE", 0 );
			level.addMessage( "You recover your shape!" );
			deMorph( );
			land( );
		}

		if ( dam.getDamage( ) <= 0 )
		{
			if ( Util.chance( 70 ) )
			{
				level.addMessage( "You withstand the attack." );
				return;
			}
			dam.setDamage( 1 );
		}
		if ( isInvincible( ) )
		{
			level.addMessage( "You are invincible!" );
			return;
		}
		if ( getSex( ) == MALE )
		{
			if ( Util.chance( 50 ) )
			{
				SFXManager.play( "wav/hurt_male.wav" );
			}
			else
			{
				SFXManager.play( "wav/hurt_male2.wav" );
			}
		}
		else
		{
			if ( Util.chance( 50 ) )
			{
				SFXManager.play( "wav/hurt_female.wav" );
			}
			else
			{
				SFXManager.play( "wav/hurt_female2.wav" );
			}
		}

		hits -= dam.getDamage( );
		message.append( " {" + dam.getDamage( ) + "}" );
		if ( Util.chance( 50 ) )
			decreaseWhip( );
		if ( Util.chance( 40 ) )
			level.addBlood( getPosition( ), Util.rand( 0, 1 ) );
	}

	private void drown( )
	{
		gameSessionInfo.setDeathCause( GameSessionInfo.DROWNED );
		gameSessionInfo.setDeathLevel( level.getLevelNumber( ) );
		this.hits = -1;
		informPlayerEvent( Player.DROWNED );
	}

	private int getAvgEnemies( int level )
	{
		if ( level == 1 )
			return 25;
		else
			return getAvgEnemies( level - 1 ) + getIncreaseOnEnemies( level );
	}

	private int getAvgXP( int level )
	{
		if ( level == 1 )
			return 100;
		else
			return getAvgXP( level - 1 ) + 50;
	}

	private int getBackFlipChance( )
	{
		return 20 + getAttack( );
	}

	private Position getFreeSquareAround( Position destinationPoint )
	{
		Position tryP = Position.add( destinationPoint,
				Action.directionToVariation( Action.UP ) );
		if ( level.getMapCell( tryP ) != null && !level.getMapCell( tryP ).isSolid( ) )
		{
			return tryP;
		}

		tryP = Position.add( destinationPoint,
				Action.directionToVariation( Action.DOWN ) );
		if ( level.getMapCell( tryP ) != null && !level.getMapCell( tryP ).isSolid( ) )
		{
			return tryP;
		}

		tryP = Position.add( destinationPoint,
				Action.directionToVariation( Action.LEFT ) );
		if ( level.getMapCell( tryP ) != null && !level.getMapCell( tryP ).isSolid( ) )
		{
			return tryP;
		}

		tryP = Position.add( destinationPoint,
				Action.directionToVariation( Action.RIGHT ) );
		if ( level.getMapCell( tryP ) != null && !level.getMapCell( tryP ).isSolid( ) )
		{
			return tryP;
		}
		return null;
	}

	private int getIncreaseOnEnemies( int level )
	{
		if ( level == 1 )
			return 0;
		/*
		 * 0000029: Change level up schema to acquire skills quickly if (level == 2)
		 * return 20; else return getIncreaseOnEnemies(level-1)+5;
		 */
		return 25;
	}

	private int getLastIncrement( String key ) {
		Integer current = lastIncrements.get(key);
		if (current == null) {
			return 0;
		} else {
			return current;
		}
	}

	private int getNeededXP( int level )
	{
		return getAvgEnemies( level ) * getAvgXP( level );
	}

	private void invokeRosary( )
	{
		level.addMessage( "A blast of holy light surrounds you!" );
		// level.addEffect(new SplashEffect(getPosition(), "****~~~~,,,,....",
		// Appearance.WHITE));
		SFXManager.play( "wav/lazrshot.wav" );
		level.addEffect( EffectFactory.getSingleton( )
				.createLocatedEffect( getPosition( ), "SFX_ROSARY_BLAST" ) );

		String message = "";

		Vector monsters = (Vector) level.getMonsters( ).getVector( ).clone( );
		Vector removables = new Vector( );
		for ( int i = 0; i < monsters.size( ); i++ )
		{
			Monster monster = (Monster) monsters.elementAt( i );
			if ( Position.flatDistance( getPosition( ), monster.getPosition( ) ) < 16 )
			{
				if ( monster instanceof NPC || monster instanceof Hostage )
				{

				}
				else
				{
					// targetMonster.damage(player.getWhipLevel());

					monster.damage( new StringBuffer( ), 10 );
					if ( monster.isDead( ) )
					{
						message = "The " + monster.getDescription( )
								+ " is shredded by the holy light!";
						removables.add( monster );
					}
					else
					{
						message = "The " + monster.getDescription( )
								+ " is purified by the holy light!";
					}
					if ( monster.wasSeen( ) )
						level.addMessage( message );
				}
			}
		}
		monsters.removeAll( removables );
		// level.removeMonster(monster);
	}

	private void levelUp( )
	{
		nextLevelXP += getNeededXP( playerLevel );
		if ( playerLevel % 2 == 0 )
		{
			hitsMax++;
			addLastIncrement( INCREMENT_HITS, 1 );
		}
		if ( playerLevel % 3 == 0 )
		{
			soulPower++;
			addLastIncrement( INCREMENT_SOUL, 1 );
		}
		if ( playerLevel % 3 == 0 )
		{
			attack++;
			addLastIncrement( INCREMENT_ATTACK, 1 );
		}
		if ( playerLevel % 5 == 0 )
		{
			defense++;
			addLastIncrement( INCREMENT_DEFENSE, 1 );
		}
		heartMax += 1;
		addLastIncrement( INCREMENT_HEARTS, 1 );
		SFXManager.play( "wav/levelup.wav" );
		informPlayerEvent( EVT_LEVELUP );
		increaseCoolness( 20 );
		playerLevel++;
		deservesLevelUp = false;
	}

	private void removeItem( Equipment toRemove )
	{
		inventory.remove( toRemove.getItem( ).getFullID( ) );
	}

	private boolean standsOnPlace( )
	{
		return hasCounter( Consts.C_LUPINEMORPH ) || hasCounter( Consts.C_BEARMORPH )
				|| hasCounter( Consts.C_BEASTMORPH ) || hasCounter( Consts.C_DEMONMORPH )
				|| hasCounter( Consts.C_WEREWOLFMORPH )
				|| hasCounter( Consts.C_MYSTMORPH ) || hasCounter( Consts.C_MYSTMORPH2 );
	}
}