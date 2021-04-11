
package co.castle.game;

import java.util.Hashtable;

import co.castle.item.ItemDefinition;
import co.castle.item.ItemFactory;
import co.castle.player.Player;
import co.castle.ui.AppearanceFactory;
import sz.util.ScriptUtil;
import sz.util.Util;

public abstract class PlayerGenerator {
	private final int[] ADVANCEMENT_LEVELS_HARD = new int[]
			{1, 5, 9, 13, 18, 23, 28, 34, 40, 46, 53, 60, 67, 75, 83, 91

			};

	private final int[] ADVANCEMENT_LEVELS_HARDER = new int[]
			{1, 6, 11, 16, 22, 28, 34, 41, 48, 55, 63, 71, 79, 88, 97, 106

			};

	private final int[] ADVANCEMENT_LEVELS_NORMAL = new int[]
			{1, 4, 7, 10, 14, 18, 22, 27, 32, 37, 43, 49, 55, 62, 69, 76

			};

	private final String[] ARMOR_MATERIALS = new String[]
			{"BRONZE"};

	private final String[] INVOKER_ARMORS = new String[]
			{"CLOTH_TUNIC"};

	private final String[] INVOKER_BANNED_ARMOR = new String[]
			{"DIAMOND_PLATE", "VEST", "CUIRASS", "SUIT", "PLATE"};

	private final String[] INVOKER_DESCRIPTIONS = new String[]
			{"%%NAME, a mysterious resident of the woods of Transylvannia",
					"%%NAME, the human who discovered %%SEX fate was to battle evil using %%SEX powers",
					"%%NAME", "%%NAME the monster summoner"};

	private final String[] INVOKER_ITEMS = new String[]
			{"HEAL_POTION", "GARLIC"};

	private final String[] INVOKER_PLOTS = new String[]
			{"Feeling the call of destiny to make use of %%SEX powers",
					"After taking monsters out of %%SEX village, using %%SEX supernatural powers",
					"Finally seeing an oportunity to show off %%SEX powers to battle evil",
					"Going after a secret tome, told to be hidden inside the castle",
					"After years of study and preparation, reading ancient tomes and discovering %%SEX true powers"};

	private final String[] INVOKER_WEAPONS = new String[]
			{"BASELARD", "STAFF"};

	private final String[] KNIGHT_ARMORS = new String[]
			{"CUIRASS"};

	private final String[] KNIGHT_BANNED_ARMOR = new String[]
			{};

	private final String[] KNIGHT_DESCRIPTIONS = new String[]
			{"%%NAME, knight of the Order of Light", "%%NAME, the knight of light", "%%NAME",
					"%%NAME the sacred knight"};

	private final String[] KNIGHT_ITEMS = new String[]
			{"HEAL_POTION", "BIBUTI", "GARLIC"};

	private final String[] KNIGHT_PLOTS = new String[]
			{"After seeing %%SEX village burned by Dracula's minions, and looking forward to avenge the death of %%SEX father",
					/**/ "As the sole survivor of the company sent to battle the count Dracula on his castle",
					"On a last effort to rid the world from the influence of the darkness that rose again",
					"After years of preparation on %%SEX order, anticipating the return of the Dark Count"};

	private final String[] KNIGHT_SHIELDS = new String[]
			{"BUCKLER", "WOODEN_SHIELD"};

	private final String[] KNIGHT_WEAPONS = new String[]
			{"SHORT_SPEAR", "FLAIL", "SABRE"};

	private final String[] MANBEAST_ARMORS = new String[]
			{"CLOTH_TUNIC"};

	private final String[] MANBEAST_BANNED_ARMOR = new String[]
			{"DIAMOND_PLATE", "VEST", "CUIRASS", "SUIT", "PLATE"};

	private final String[] MANBEAST_DESCRIPTIONS = new String[]
			{"%%NAME, the powerful beast morpher from the mountains",
					"%%NAME, the only survivor of the manbeast race", "%%NAME",
					"%%NAME the half-beast"};
	private final String[] MANBEAST_ITEMS = new String[]
			{"HEAL_HERB"};
	private final String[] MANBEAST_PLOTS = new String[]
			{"After seeing %%SEX village burned by Dracula's minions, and looking forward to avenge the death of %%SEX loved ones",
					"As one of the few survivors from the raids of the legion of the undead, and looking forward to avenge the death of %%SEX loved ones",
					/**/ "After being taken captive by a group of careless skeletons, smashed when %%SEX power awoke",
					"After having learned of the destruction of the manbeast race, and abandoning %%SEX secret hideout in the caverns"};
	private final String[] RENEGADE_ARMORS = new String[]
			{"FINE_GARMENT"};

	private final String[] RENEGADE_BANNED_ARMOR = new String[]
			{"DIAMOND_PLATE", "SUIT", "PLATE"};
	private final String[] RENEGADE_DESCRIPTIONS = new String[]
			{"%%NAME, heir to the throne of darkness",
					"%%NAME, a vampire turned to the light side", "%%NAME", "%%NAME the renegade"};
	private final String[] RENEGADE_ITEMS = new String[]
			{"HEAL_HERB", "BIBUTI"};
	private final String[] RENEGADE_PLOTS = new String[]
			{"Coming back to the castle which once was %%SEX home",
					"Looking for a way to redeem the evil brought by the vampire race to the mankind",
					"After being mysteriously awaken from %%SEX self imposed eternal slumber",
					"Looking forward to give this 'Dark Count' a lesson about true power",
					"After being told about a powerful vampire breaking the sacred pact of peace made with mankind",
					"After rebelling against %%SEX father and being shunned by the vampire race"};
	private final String[] RENEGADE_SHIELDS = new String[]
			{"BUCKLER"};

	private final String[] RENEGADE_WEAPONS = new String[]
			{"SHORT_SWORD", "BOW"};
	private final String[] SONIA_ITEMS = new String[]
			{"HEAL_HERB", "GARLIC", "BIBUTI"};
	private final Hashtable<String, Player> SPECIAL_PLAYERS = new Hashtable<>();
	private final String[] VANQUISHER_ARMORS = new String[]
			{"CLOTH_TUNIC"};

	private final String[] VANQUISHER_BANNED_ARMOR = new String[]
			{"DIAMOND_PLATE", "VEST", "CUIRASS", "SUIT", "PLATE"};
	private final String[] VANQUISHER_DESCRIPTIONS = new String[]
			{"%%NAME, heir of the powers of Sypha",
					"%%NAME, a witch for some, the last hope for anothers,", "%%NAME",
					"%%NAME the slayer"};
	private final String[] VANQUISHER_ITEMS = new String[]
			{"HEAL_POTION", "BIBUTI"};
	private final String[] VANQUISHER_PLOTS = new String[]
			{    /**/ "After being taken to the castle by a group of skeletons, ",
					"After discovering %%SEX powers when %%SEX friends were killed by the armies of the count",
					"Interpreting the latest events as a call of destiny for a need to be fulfilled with %%SEX powers",
					"After being brought to the castle door by a mysterious cartman",
					"After being chased as a witch on %%SEX home town"};

	private final String[] VANQUISHER_WEAPONS = new String[]
			{"RINGS", "STAFF"};
	private final String[] VKILLER_ARMORS = new String[]
			{"VARMOR"};
	private final String[] VKILLER_BANNED_ARMOR = new String[]
			{"DIAMOND_PLATE", "LEATHER_ARMOR", "CLOTH_TUNIC", "VEST", "CUIRASS", "SUIT",
					"PLATE"};

	private final String[] VKILLER_DESCRIPTIONS = new String[]
			{"%%NAME, last member on the lineage of vampire hunters, the Belmonts",
					"%%NAME, the wielder of the vampire hunter fate", "%%NAME",
					"%%NAME the Vampire Hunter"};
	private final String[] VKILLER_ITEMS = new String[]
			{"HEAL_HERB", "GARLIC"};
	private final String[] VKILLER_PLOTS = new String[]
			{"On a last attempt to rescue %%SEX loved one, which was taken by an evil daemon last night",
					"Seeking to bring an end to the problem as %%SEX grandfather did, about 100 years ago",
					"After seeing %%SEX home town leveled, and all of %%SEX friends slain by the dark armies of the count",
					"Finally seeing an opportunity to drop some vampire blood",
					"After a long trip around all transylvannia, becoming stronger"};
	private final String[] VKILLER_WEAPONS = new String[]
			{"LEATHER_WHIP"};

	private final String[] WEAPON_MATERIALS = new String[]
			{"STEEL"};

	protected String[] CLASS_APPEARANCES = new String[]
			{"VKILLER", "RENEGADE", "VANQUISHER", "INVOKER", "MANBEAST", "KNIGHT"};
	protected String[] CLASS_DESCRIPTIONS = new String[]
			{"Heir of the Belmont fate, destined to confront the dark count or die " + ""
					+ "trying. Master in the use of the mystic vampire killer whip and the "
					+ "only able to use mystic weapons",
					"A Vampire turned to the side of light, his will is to clean the world "
							+ "from the dark influence of the count. Able with weapons and wielder of "
				+ "vampiric skills.",
		"An human envowed with mystical powers since childhood to fight darkness, they are able "
				+ "to learn spells from the dark tomes hidden within the castle and enchant weapons",
		"Able to manipulate spirits and bring them in a physical form to bring havoc down to "
				+ "its enemies.",
		"Fights using an ancient martial style, his racial powers allow him to transform "
				+ "into a powerful bestial creature. Can stand much more damage than common humans.",
		"An agent from the church, trained to defend the world from chaos and armed with the "
				+ "most advanced human weaponry" };

	protected String[ ] CLASS_NAMES = new String[ ]
	{	"Vampire Killer", "Darkness Renegade", "Vanquisher Wizard", "Souls' Master",
		"Manbeast", "Knight of the Order" };
	protected String[ ][ ] CLASS_STATS = new String[ ][ ]
	{
		{	"5", "1", "Normal", "5", "Quick", "Normal", "Deadly", "Normal", "Normal",
			"Poor" },
		{	"5", "2", "Normal", "5", "Quick", "Deadly", "Normal", "Normal", "Long",
			"Normal" },
		{ "3", "3", "Weak", "0", "Slow", "Poor", "Deadly", "Weak", "Normal", "Wealthy" },
		{ "3", "3", "Weak", "0", "Slow", "Normal", "Normal", "Weak", "Normal", "Normal" },
		{	"7", "1", "Very Hardy", "20", "Quick", "Deadly", "Poor", "Strong", "Very Long",
			"Very poor" },
		{	"7", "0", "Hardy", "0", "Normal", "Deadly", "Poor", "Strong", "Normal",
			"Wealthy" } };

	protected String[ ] FEMALE_NAMES = new String[ ]
	{ "Eliann", "Sonia", "Valentina", "Carrie", "Sypha", "Mina" };

	protected String[ ] MALE_NAMES = new String[ ]
	{ "Slash", "Simon", "Trevor", "Alan", "Reinhart", "Juste", "Alucard", "Daniel" };

	public static PlayerGenerator thus;

	public static String getClassID( int classId )
	{
		switch ( classId )
		{
		case Player.CLASS_VAMPIREKILLER:
			return "VKILLER";
		case Player.CLASS_VANQUISHER:
			return "VANQUISHER";
		case Player.CLASS_RENEGADE:
			return "RENEGADE";
		case Player.CLASS_INVOKER:
			return "INVOKER";
		case Player.CLASS_MANBEAST:
			return "MANBEAST";
		case Player.CLASS_KNIGHT:
			return "KNIGHT";
		}
		return "";
	}

	public Player createSpecialPlayer( String playerID )
	{
		initSpecialPlayers( );
		return (Player) SPECIAL_PLAYERS.get( playerID );
	}

	public abstract Player generatePlayer( );

	public Player getPlayer( String name, int sex, int choice )
	{
		Player player = new Player( );
		player.setSex( sex );
		if ( name.trim( ).equals( "" ) )
		{
			if ( sex == Player.MALE )
				player.setName( Util.randomElementOf( MALE_NAMES ) );
			else
				player.setName( Util.randomElementOf( FEMALE_NAMES ) );
		}
		else
		{
			player.setName( name );
		}
		switch ( choice )
		{
		case 0:
			player.setPlayerClass( Player.CLASS_VAMPIREKILLER );
			break;
		case 1:
			player.setPlayerClass( Player.CLASS_RENEGADE );
			break;
		case 2:
			player.setPlayerClass( Player.CLASS_VANQUISHER );
			break;
		case 3:
			player.setPlayerClass( Player.CLASS_INVOKER );
			break;
		case 4:
			player.setPlayerClass( Player.CLASS_MANBEAST );
			break;
		case 5:
			player.setPlayerClass( Player.CLASS_KNIGHT );
			break;
		}

		// String heshe = (sex == Player.MALE ? "he" : "she");
		String hisher = ( sex == Player.MALE ? "his" : "her" );

		String classID = null;
		String[ ] plots = null;
		String[ ] descriptions = null;
		String[ ] initWeapons = null;
		String[ ] initArmors = null;
		String[ ] initItems = null;
		player.setBaseSightRange( 4 );
		switch ( player.getPlayerClass( ) )
		{
		case Player.CLASS_VAMPIREKILLER:
			classID = "VKILLER";
			player.setGold( Util.rand( 3, 7 ) * 100 );
			plots = VKILLER_PLOTS;
			descriptions = VKILLER_DESCRIPTIONS;
			initWeapons = VKILLER_WEAPONS;
			initArmors = VKILLER_ARMORS;
			initItems = VKILLER_ITEMS;
			player.setWalkCost( 40 );
			player.setAttackCost( 50 );
			player.setAttack( 5 );
			player.setBaseEvadeChance( 5 );
			player.setCastCost( 30 );
			player.setSoulPower( 1 );
			player.setCarryMax( 15 );
			player.setBreathing( 35 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_PROJECTILES );
			player.setBannedArmors( VKILLER_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_NORMAL );
			player.setFlag( "ONLY_VK", true );
			player.putCustomMessage( "INTRO_1",
					"This forest suffers as darkness corrupts its roots, it is my fate to get to Castlevania and fight my way through." );
			player.putCustomMessage( "CLARA1",
					"I came here to fulfill my fate as a Vampire Killer, and destroy the dark count Dracula" );
			break;
		case Player.CLASS_VANQUISHER:
			classID = "VANQUISHER";
			player.setGold( Util.rand( 5, 15 ) * 100 );
			player.setHitsMax( 17 );
			player.heal( );
			plots = VANQUISHER_PLOTS;
			descriptions = VANQUISHER_DESCRIPTIONS;
			initWeapons = VANQUISHER_WEAPONS;
			initArmors = VANQUISHER_ARMORS;
			initItems = VANQUISHER_ITEMS;
			player.setWalkCost( 60 );
			player.setAttackCost( 60 );
			player.setAttack( 3 );
			player.setSoulPower( 3 );
			player.setCastCost( 30 );
			player.setCarryMax( 15 );
			player.setHeartMax( 30 );
			player.setBreathing( 25 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
			player.setBannedArmors( VANQUISHER_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_HARD );
			player.putCustomMessage( "INTRO_1",
					"The time is almost over, the trip over here will be wasted if I dont reach the castle soon." );
			player.putCustomMessage( "CLARA1",
					"Get out of my way, I have issues to attend inside the castle" );
			break;
		case Player.CLASS_RENEGADE:
			classID = "RENEGADE";
			player.setGold( Util.rand( 2, 10 ) * 100 );
			plots = RENEGADE_PLOTS;
			descriptions = RENEGADE_DESCRIPTIONS;
			initWeapons = RENEGADE_WEAPONS;
			initArmors = RENEGADE_ARMORS;
			initItems = RENEGADE_ITEMS;
			player.setBaseSightRange( 5 );
			player.setBaseEvadeChance( 5 );
			player.setWalkCost( 40 );
			player.setAttackCost( 40 );
			player.setAttack( 5 );
			player.setCastCost( 40 );
			player.setCarryMax( 15 );
			player.setSoulPower( 2 );
			player.setBreathing( 35 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_DAGGERS );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
			player.setBannedArmors( RENEGADE_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_NORMAL );

			player.setShield( ItemFactory.getItemFactory( ).createShield(
					Util.randomElementOf( RENEGADE_SHIELDS ),
					Util.randomElementOf( ARMOR_MATERIALS ) ) );
			player.setSecondaryWeapon( ItemFactory.getItemFactory( ).createWeapon(
					"BASELARD", Util.randomElementOf( WEAPON_MATERIALS ) ) );

			player.putCustomMessage( "INTRO_1",
					"I can almost breathe the chaos that invades every tree and animal in this forest. It is time to return to the castle." );
			player.putCustomMessage( "CLARA1",
					"Commonner? . . . Ignorant human... get out of my way." );
			break;
		case Player.CLASS_INVOKER:
			classID = "INVOKER";
			player.setGold( Util.rand( 2, 10 ) * 100 );
			player.setHitsMax( 17 );
			player.heal( );
			plots = INVOKER_PLOTS;
			descriptions = INVOKER_DESCRIPTIONS;
			initWeapons = INVOKER_WEAPONS;
			initArmors = INVOKER_ARMORS;
			initItems = INVOKER_ITEMS;
			player.setWalkCost( 60 );
			player.setSoulPower( 3 );
			player.setAttackCost( 50 );
			player.setAttack( 3 );
			player.setHeartMax( 30 );
			player.setCastCost( 40 );
			player.setCarryMax( 15 );
			player.setBreathing( 25 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
			player.setBannedArmors( INVOKER_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_NORMAL );
			player.putCustomMessage( "INTRO_1",
					"I can sense all kind of dreaded souls lurking in here, I am afraid my master was right, I really need to reach the castle." );
			player.putCustomMessage( "CLARA1",
					"Something bigger than myself has got me here, you would never understand it... the castle calls me." );
			break;
		case Player.CLASS_MANBEAST:
			// player.setRegenRate(40);
			player.setGold( Util.rand( 1, 5 ) * 100 );
			player.setHitsMax( 26 );
			player.setBaseSightRange( 6 );
			player.heal( );
			classID = "MANBEAST";
			plots = MANBEAST_PLOTS;
			descriptions = MANBEAST_DESCRIPTIONS;
			initWeapons = null;
			initArmors = MANBEAST_ARMORS;
			initItems = MANBEAST_ITEMS;
			player.setWalkCost( 35 );
			player.setAttackCost( 40 );
			player.setCastCost( 60 );
			player.setSoulPower( 1 );
			player.setHeartMax( 15 );
			player.setAttack( 3 );
			player.setCarryMax( 20 );
			player.setBreathing( 45 );
			player.setBaseEvadeChance( 20 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_PROJECTILES );
			player.setBannedArmors( MANBEAST_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_HARDER );
			player.addItem(
					ItemFactory.getItemFactory( ).createItem( "THROWING_KNIFE" ) );
			player.putCustomMessage( "INTRO_1",
					"I can hear, I can smell, I can sense the damned creatures that almost anihilated my brothers... tell me, where is the castle?" );
			player.putCustomMessage( "CLARA1",
					"Don't let appearances fool you... the blood of my brothers and sisters fills my veins, anger will get me through this damned place and to the heart of the count" );
			break;
		case Player.CLASS_KNIGHT:
			classID = "KNIGHT";
			player.setGold( Util.rand( 5, 12 ) * 100 );
			player.setHitsMax( 23 );
			player.setHeartMax( 10 );
			player.setBreathing( 15 );
			player.heal( );
			int skilleds = Util.rand( 4, 5 );
			String[ ] randoms = new String[ ]
			{	ItemDefinition.CAT_UNARMED, ItemDefinition.CAT_DAGGERS,
				ItemDefinition.CAT_SWORDS, ItemDefinition.CAT_SPEARS,
				ItemDefinition.CAT_WHIPS, ItemDefinition.CAT_MACES,
				ItemDefinition.CAT_STAVES, ItemDefinition.CAT_RINGS,
				ItemDefinition.CAT_PROJECTILES, ItemDefinition.CAT_BOWS,
				ItemDefinition.CAT_PISTOLS, ItemDefinition.CAT_SHIELD };
			for ( int i = 0; i < skilleds; i++ )
			{
				player.increaseWeaponSkillLevel( Util.randomElementOf( randoms ) );
			}
			plots = KNIGHT_PLOTS;
			descriptions = KNIGHT_DESCRIPTIONS;
			initWeapons = KNIGHT_WEAPONS;
			initArmors = KNIGHT_ARMORS;
			initItems = KNIGHT_ITEMS;
			player.setWalkCost( 50 );
			player.setAttackCost( 40 );
			player.setCastCost( 60 );
			player.setAttack( 7 );
			player.setCarryMax( 20 );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_SPEARS );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_PISTOLS );
			player.increaseWeaponSkillLevel( ItemDefinition.CAT_SHIELD );
			player.setBannedArmors( KNIGHT_BANNED_ARMOR );
			player.setAdvancementLevels( ADVANCEMENT_LEVELS_NORMAL );
			player.setShield( ItemFactory.getItemFactory( ).createShield(
					Util.randomElementOf( KNIGHT_SHIELDS ),
					Util.randomElementOf( ARMOR_MATERIALS ) ) );
			player.putCustomMessage( "INTRO_1",
					"Indeed, those seem to be Wargs: demonic wolf-like creatures summoned by the Count of Darkness to protect his castle. We better get to the castle quickly." );
			player.putCustomMessage( "CLARA1",
					"Can you not see the mark of heavens? I am a Knight of the Sacred Order, and I came to cleanse this place from darkness." );
			break;
		}

		ItemFactory itf = ItemFactory.getItemFactory( );
		AppearanceFactory apf = AppearanceFactory.getAppearanceFactory( );
		if ( player.getSex( ) == Player.MALE )
			player.setAppearance( apf.getAppearance( classID ) );
		else
			player.setAppearance( apf.getAppearance( classID + "_W" ) );
		String[ ] marks = new String[ ]
		{ "%%SEX", "%%NAME" };
		String[ ] replacements = new String[ ]
		{ hisher, player.getName( ) };
		player.setPlot(
				ScriptUtil.replace( marks, replacements, Util.randomElementOf( plots ) ),
				ScriptUtil.replace( marks, replacements,
						getClassStartEquipmentDescription( player.getPlayerClass( ) ) ) );
		player.setDescription( ScriptUtil.replace( marks, replacements,
				Util.randomElementOf( descriptions ) ) );
		if ( initWeapons != null )
			player.setWeapon( itf.createWeapon( Util.randomElementOf( initWeapons ),
					Util.randomElementOf( WEAPON_MATERIALS ) ) );
		player.setArmor( itf.createArmor( Util.randomElementOf( initArmors ),
				Util.randomElementOf( ARMOR_MATERIALS ) ) );
		int items = Util.rand( 5, 7 );
		for ( int i = 0; i < items; i++ )
		{
			player.addItem( itf.createItem( Util.randomElementOf( initItems ) ) );
		}
		player.increaseCoolness( 10 );
		return player;

	}

	private String getClassStartEquipmentDescription( int classID )
	{
		switch ( classID )
		{
		case Player.CLASS_VANQUISHER:
			return "%%SEX abilities to conjure the forces of light";
		case Player.CLASS_INVOKER:
			return "%%SEX power to invoke souls unto aid";
		case Player.CLASS_KNIGHT:
			return "the weapons of the sacred church";
		case Player.CLASS_MANBEAST:
			return "%%SEX martial art and shapeshifting skills";
		case Player.CLASS_RENEGADE:
			return "%%SEX dark powers";
		case Player.CLASS_VAMPIREKILLER:
			return "the vampire killer whip, %%SEX sacred charm";
		}
		return null;
	}

	private void initSpecialPlayers( )
	{
		SPECIAL_PLAYERS.clear( );
		ItemFactory itf = ItemFactory.getItemFactory( );
		AppearanceFactory apf = AppearanceFactory.getAppearanceFactory( );

		Player christopher = new Player( );
		christopher.setSex( Player.MALE );
		christopher.setDoNotRecordScore( true );
		christopher.setName( "Christopher" );
		christopher.setPlayerClass( Player.CLASS_VAMPIREKILLER );
		christopher.setPlayerLevel( 38 );
		christopher.setBaseSightRange( 6 );
		christopher.setGold( Util.rand( 30, 70 ) * 100 );
		christopher.setAttack( 20 );
		christopher.setWalkCost( 15 );
		christopher.setAttackCost( 20 );
		christopher.setBaseEvadeChance( 25 );
		christopher.setCastCost( 15 );
		christopher.setCarryMax( 35 );
		christopher.setSoulPower( 15 );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_UNARMED );
		christopher.setAppearance( apf.getAppearance( "CHRISTOPHER_B" ) );
		christopher.setPlot(
				"After defeating Dracula two times, he returns to the castle to fulfill his fate for a last time",
				"" );
		christopher
				.setDescription( "The famous Vampire Killer, heir of the Belmont fate" );
		christopher.setWeapon( itf.createWeapon( "LEATHER_WHIP", "" ) );
		christopher.setArmor( itf.createArmor( "VARMOR", "" ) );
		christopher.setAdvancementLevels( ADVANCEMENT_LEVELS_HARDER );
		SPECIAL_PLAYERS.put( "CHRIS", christopher );
		christopher = new Player( );
		christopher.setSex( Player.MALE );
		christopher.setName( "Soleiyu" );
		christopher.setDoNotRecordScore( true );
		christopher.setAttack( 15 );
		christopher.setPlayerLevel( 35 );
		christopher.setPlayerClass( Player.CLASS_VANQUISHER );
		christopher.setBaseSightRange( 6 );
		christopher.setGold( Util.rand( 30, 70 ) * 100 );
		christopher.setWalkCost( 25 );
		christopher.setAttackCost( 25 );
		christopher.setBaseEvadeChance( 20 );
		christopher.setCastCost( 10 );
		christopher.setCarryMax( 25 );
		christopher.setSoulPower( 20 );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_STAVES );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_SWORDS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_RINGS );
		christopher.setAppearance( apf.getAppearance( "SOLIEYU_B" ) );
		christopher.setPlot(
				"Finally deciding to face his own fate, he travels to the cursed Castlevania",
				"" );
		christopher.setDescription( "The son of Christopher Belmont" );
		christopher.setWeapon( itf.createWeapon( "HARPER", "" ) );
		christopher.setArmor( itf.createArmor( "PLATE", "STEEL" ) );
		christopher.setAdvancementLevels( ADVANCEMENT_LEVELS_HARDER );
		SPECIAL_PLAYERS.put( "SOLIEYU", christopher );
		christopher = new Player( );
		christopher.setSex( Player.MALE );
		christopher.setName( "Child Soleiyu" );
		christopher.setDoNotRecordScore( true );
		christopher.setAttack( 1 );
		christopher.setPlayerLevel( 1 );
		christopher.setPlayerClass( Player.CLASS_VAMPIREKILLER );
		christopher.setBaseSightRange( 5 );
		christopher.setGold( 200 );
		christopher.setWalkCost( 60 );
		christopher.setHearts( 15 );
		christopher.setAttackCost( 80 );
		christopher.setBaseEvadeChance( 5 );
		christopher.setCastCost( 40 );
		christopher.setCarryMax( 5 );
		christopher.setSoulPower( 0 );

		christopher.setAppearance( apf.getAppearance( "SOLIEYU_B_KID" ) );
		christopher.setPlot( "Trains to become the next vampire killer", "" );
		christopher.setDescription( "The son of Christopher Belmont" );
		christopher.setAdvancementLevels( ADVANCEMENT_LEVELS_HARDER );
		SPECIAL_PLAYERS.put( "SOLIEYU_KID", christopher );

		christopher = new Player( );
		christopher.setSex( Player.FEMALE );
		christopher.setDoNotRecordScore( false );
		christopher.setName( "Sonia" );
		christopher.setPlayerClass( Player.CLASS_VAMPIREKILLER );
		christopher.setPlayerLevel( 1 );
		christopher.setBaseSightRange( 7 );
		christopher.setGold( Util.rand( 30, 70 ) * 100 );
		christopher.setAttack( 7 );
		christopher.setWalkCost( 40 );
		christopher.setAttackCost( 50 );
		christopher.setBaseEvadeChance( 5 );
		christopher.setCastCost( 30 );
		christopher.setCarryMax( 10 );
		christopher.setSoulPower( 4 );
		christopher.setSoulPower( 1 );
		christopher.setFlag( "ARENA_FIGHTER", true );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_WHIPS );
		christopher.increaseWeaponSkillLevel( ItemDefinition.CAT_PROJECTILES );
		int items = Util.rand( 10, 15 );
		for ( int i = 0; i < items; i++ )
		{
			christopher.addItem( itf.createItem( Util.randomElementOf( SONIA_ITEMS ) ) );
		}
		christopher.setAppearance( apf.getAppearance( "SONIA_B" ) );
		christopher.setPlot( "Came to the castle as the first Belmont ever", "" );
		christopher.setDescription( "Sonia Belmont, a mysterious girl" );
		christopher.setWeapon( itf.createWeapon( "LEATHER_WHIP", "" ) );
		christopher.setArmor( itf.createArmor( "VARMOR", "" ) );
		christopher.setAdvancementLevels( ADVANCEMENT_LEVELS_NORMAL );
		SPECIAL_PLAYERS.put( "SONIA", christopher );

	}

}
