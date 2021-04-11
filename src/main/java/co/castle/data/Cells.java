package co.castle.data;

import co.castle.level.Cell;
import co.castle.ui.AppearanceFactory;

public class Cells
{
	public static Cell[ ] getCellDefinitions( AppearanceFactory apf )
	{

		Cell[ ] ret = new Cell[ 179 ];

		ret[ 149 ] = new Cell( "AIR", "nothing", "Nothing",
				apf.getAppearance( "NOTHING" ) );
		ret[ 149 ].setEthereal( true );

		ret[ 153 ] = new Cell( "DINING_CHAIR", "chair", "Dining chair",
				apf.getAppearance( "DINING_CHAIR" ), true, false );
		ret[ 154 ] = new Cell( "DINING_TABLE", "table", "Dining table",
				apf.getAppearance( "DINING_TABLE" ), true, false );
		ret[ 155 ] = new Cell( "MARBLE_STAIRSUP", "stairs", "Stairs",
				apf.getAppearance( "MARBLE_STAIRSUP" ) );
		ret[ 155 ].setHeightMod( -1 );
		ret[ 156 ] = new Cell( "MARBLE_STAIRSDOWN", "stairs", "Stairs",
				apf.getAppearance( "MARBLE_STAIRSDOWN" ) );
		ret[ 156 ].setHeightMod( 1 );
		ret[ 161 ] = new Cell( "MARBLE_STAIRSUP_FAKE", "stairs", "Stairs",
				apf.getAppearance( "MARBLE_STAIRSUP" ) );
		ret[ 162 ] = new Cell( "MARBLE_STAIRSDOWN_FAKE", "stairs", "Stairs",
				apf.getAppearance( "MARBLE_STAIRSDOWN" ) );

		/* Town */
		ret[ 1 ] = new Cell( "DIRT", "dirt", "A patch of dirt",
				apf.getAppearance( "TOWN_GRASS" ) );
		ret[ 8 ] = new Cell( "DARK_FOREST", "moss tree", "A moss tree",
				apf.getAppearance( "TOWN_GRASS" ), true, true );
		ret[ 16 ] = new Cell( "STREAM", "stream of water", "A stream of water",
				apf.getAppearance( "TOWN_GRASS" ) );
		ret[ 16 ].setWater( true );
		ret[ 30 ] = new Cell( "VOID", "", "", apf.getAppearance( "VOID" ), true, true );
		ret[ 165 ] = new Cell( "STATIC_VOID", "", "", apf.getAppearance( "VOID" ) );

		ret[ 133 ] = new Cell( "SIGNPOST_T1", "signpost", "Exercise 1: Items \n \n "
				+ " Pick up the herbs moving over them and pressing 'g', move to the house and drop them using 'd'.\n \n"
				+ " During your journeys, you will find items laying in the ground, you must pick them up before using "
				+ "or wearing them.", apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 177 ] = new Cell( "SIGNPOST_T2", "signpost", "Exercise 2: Jumping \n \n "
				+ "Jump the water moving next to it and pressing the jump key 'j' and the direction. \n \n "
				+ "Jumping is useful in combat to gain tactical advantage, and you can also use it to pass over gaps in the terrain.",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 132 ] = new Cell( "SIGNPOST_T3", "signpost", "Exercise 3: Attacking \n \n "
				+ "Move next to a tree and press 'a', then the direction to attack the tree. \n \n "
				+ "The world is full of monsters eager to kill you. This is the main way of attacking. Depending on your "
				+ "current weapon, you may hit enemies which are not adjacent to you.",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 135 ] = new Cell( "SIGNPOST_T4", "signpost",
				"Exercise 4: Ranged Weapons \n \n "
						+ "Pick up this bow and press 'e' and then the item letter to equip it. \n \n Press 'f' and aim at a tree using the directional keys and press 'f' again to shoot an arrow. \n \n "
						+ "Ranged weapons allow you to attack at a distance, but must be reloaded consuming hearts and gold.",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 134 ] = new Cell( "SIGNPOST_T5", "signpost", "Exercise 5: Swimming \n \n "
				+ "Walk over the water and use 'p' to plunge into the lake, swim north into the other side and use 'j' to go back to the surface. \n \n "
				+ "Diving is a dangerous.. you will die as soon as you run "
				+ "out of oxygen (02 indicator).\n \n Be sure to resurface to replenish your oxygen.",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 136 ] = new Cell( "SIGNPOST_T6", "signpost", "Exercise 6: Throwing \n \n "
				+ "Pick up the herbs and throw them to the dirt. Press 't', select the herb, picking a landing spot and then press space. \n \n "
				+ "Some items explode or have special effects when thrown",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 137 ] = new Cell( "SIGNPOST_T7", "signpost",
				"Exercise 7: Mystic Weapons \n \n "
						+ "This is a mystic weapon, only vampire killers can use it. Step on it and use space bar to aim it as if it was a ranged weapon. Press Space again to shoot \n \n "
						+ "Mystic weapons consume hearts on each shoot.",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 178 ] = new Cell( "SIGNPOST_T8", "signpost", "Exercise 8: Monsters! \n \n "
				+ "You are now ready to fight! with your mystic weapon you should have no problem dispatching these weak foes.\n \n "
				+ "You can evade the skeleton bones by moving out of their firing range, they always target your previous location!",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 139 ] = new Cell( "SIGNPOST_T9", "signpost",
				"You can check the area map using 'M', access your skills using 's' "
						+ "and check your inventory using 'i'. Press '?' for complete reference. \n \n You can access most of the item manipulation commands from the Inventory screen!\n \n Walk through here to leave this area and end your training \n \n CastleVania awaits!",
				apf.getAppearance( "SIGN_POST" ), true, false );

		/* Town */
		ret[ 31 ] = new Cell( "TOWN_GRASS", "grass", "Darkened grass",
				apf.getAppearance( "TOWN_GRASS" ) );
		ret[ 32 ] = new Cell( "TOWN_WALL", "wall", "Wall from somebody house",
				apf.getAppearance( "TOWN_WALL" ), true, true );
		ret[ 33 ] = new Cell( "HOUSE_FLOOR", "floor", "A common house floor",
				apf.getAppearance( "HOUSE_FLOOR" ) );
		ret[ 34 ] = new Cell( "MIDWALKWAY", "brick Walkway (high)",
				"Walkway made of old bricks", apf.getAppearance( "MIDWALKWAY" ) );
		ret[ 35 ] = new Cell( "TOWN_CHURCH_FLOOR", "floor",
				"A beautifully mantained floor",
				apf.getAppearance( "TOWN_CHURCH_FLOOR" ) );
		ret[ 28 ] = new Cell( "TOWN_WATERWAY", "water", "Stream of water",
				apf.getAppearance( "TOWN_WATERWAY" ) );
		ret[ 28 ].setShallowWater( true );
		ret[ 144 ] = new Cell( "TOWN_WATERWAY_UP", "water", "Stream of water",
				apf.getAppearance( "TOWN_WATERWAY" ) );
		ret[ 144 ].setWater( true );
		ret[ 144 ].setHeightMod( -1 );
		ret[ 145 ] = new Cell( "TOWN_WATERWAY_DOWN", "water", "Stream of water",
				apf.getAppearance( "TOWN_WATERWAY" ) );
		ret[ 145 ].setShallowWater( true );
		ret[ 145 ].setHeightMod( 1 );
		ret[ 146 ] = new Cell( "TOWN_SEWER", "water", "Stream of water",
				apf.getAppearance( "TOWN_WATERWAY" ) );
		ret[ 146 ].setWater( true );
		ret[ 150 ] = new Cell( "TOWN_WATER", "water", "Stream of water",
				apf.getAppearance( "TOWN_WATERWAY" ) );
		ret[ 150 ].setWater( true );
		ret[ 2 ] = new Cell( "BRICK_WALKWAY", "walkway",
				"Walkway made of old damaged bricks",
				apf.getAppearance( "BRICKWALKWAY" ) );
		ret[ 111 ] = new Cell( "TOWN_TREE", "tree", "Dark mossy tree",
				apf.getAppearance( "TOWN_TREE" ), true, true );
		ret[ 59 ] = new Cell( "BRICK_WALKWAY2", "walkway",
				"Walkway made of old damaged bricks",
				apf.getAppearance( "BRICKWALKWAY2" ) );
		ret[ 60 ] = new Cell( "TOWN_ROOF", "roof", "House roof",
				apf.getAppearance( "TOWN_ROOF" ) );
		ret[ 57 ] = new Cell( "TOWN_STAIRSUP", "stairs", "Stairs",
				apf.getAppearance( "TOWN_STAIRSUP" ) );
		ret[ 57 ].setHeightMod( -1 );
		ret[ 57 ].setHeight( 1 );
		ret[ 58 ] = new Cell( "TOWN_STAIRSDOWN", "stairs", "Stairs",
				apf.getAppearance( "TOWN_STAIRSDOWN" ) );
		ret[ 58 ].setHeightMod( 1 );/* ret[58].setHeight(1); */
		ret[ 118 ] = new Cell( "TOWN_STAIRS", "stairs", "Stairs",
				apf.getAppearance( "TOWN_STAIRS" ) );
		ret[ 118 ].setHeight( 1 );
		ret[ 118 ].setIsStair( true );
		ret[ 127 ] = new Cell( "TOWN_DOOR", "wooden door", "Wooden Door",
				apf.getAppearance( "TOWN_DOOR" ), false, true );
		ret[ 128 ] = new Cell( "TOWN_DOOR_H", "wooden door", "Wooden Door",
				apf.getAppearance( "TOWN_DOOR" ), false, true );
		ret[ 128 ].setHeight( 3 );

		/* Dark Forest */
		ret[ 105 ] = new Cell( "FOREST_TREE", "tree", "Dark mossy tree",
				apf.getAppearance( "FOREST_TREE" ), true, true );
		ret[ 106 ] = new Cell( "FOREST_GRASS", "grass", "Humid grass",
				apf.getAppearance( "FOREST_GRASS" ) );
		ret[ 107 ] = new Cell( "FOREST_DIRT", "dirt patch", "Wet dirt patch",
				apf.getAppearance( "FOREST_DIRT" ) );
		ret[ 108 ] = new Cell( "WRECKED_CHARRIOT", "wrecked charriot",
				"Destroyed charriot", apf.getAppearance( "WRECKED_CHARRIOT" ) );
		ret[ 109 ] = new Cell( "WRECKED_WHEEL", "wrecked wheel", "Wrecked Wheel",
				apf.getAppearance( "WRECKED_WHEEL" ) );
		ret[ 110 ] = new Cell( "SIGNPOST", "signpost",
				"AHEAD: \"Left to Petra Town, Right to Dracula's Castle\"",
				apf.getAppearance( "SIGN_POST" ), true, false );
		ret[ 173 ] = new Cell( "FOREST_TREE_1", "tree", "Dark mossy tree",
				apf.getAppearance( "FOREST_TREE_1" ), false, false );
		ret[ 174 ] = new Cell( "FOREST_TREE_2", "tree", "Dark mossy tree",
				apf.getAppearance( "FOREST_TREE_2" ), false, false );

		/* Bridge */
		ret[ 62 ] = new Cell( "WOODEN_BRIDGE", "wooden bridge", "Wooden bridgeway",
				apf.getAppearance( "WOODEN_BRIDGE" ) );
		ret[ 62 ].setHeight( 4 );
		ret[ 63 ] = new Cell( "DARK_LAKE_ETH", "dark lake", "Dark Lake",
				apf.getAppearance( "DARK_LAKE" ) );
		ret[ 63 ].setShallowWater( true );
		ret[ 147 ] = new Cell( "DARK_LAKE", "dark lake", "Dark Lake",
				apf.getAppearance( "DARK_LAKE" ) );
		ret[ 147 ].setWater( true );
		ret[ 148 ] = new Cell( "LAKE_STAIRSUP", "stairs up", "Stairs Up",
				apf.getAppearance( "WOODEN_BRIDGE" ) );
		ret[ 148 ].setWater( true );
		ret[ 148 ].setHeightMod( -1 );
		ret[ 175 ] = new Cell( "DARK_LAKE_ETH_STAIR", "dark lake", "Dark Lake",
				apf.getAppearance( "DARK_LAKE" ) );
		ret[ 175 ].setShallowWater( true );
		ret[ 175 ].setHeight( 2 );
		ret[ 176 ] = new Cell( "STONE_BLOCK", "stone block", "A massive stone block",
				apf.getAppearance( "STONE_BLOCK" ), true, true );

		ret[ 112 ] = new Cell( "BRIDGE_WALKWAY", "walkway",
				"Walkway made of old damaged bricks",
				apf.getAppearance( "BRIDGE_WALKWAY" ) );
		ret[ 112 ].setHeight( 4 );
		ret[ 113 ] = new Cell( "BRIDGE_COLUMN", "granite column",
				"A weary granite column", apf.getAppearance( "BRIDGE_COLUMN" ), true,
				true );

		/* Castle Garden */
		ret[ 0 ] = new Cell( "GARDEN_GRASS", "grass", "Somewhat grown grass",
				apf.getAppearance( "GARDEN_GRASS" ) );
		ret[ 140 ] = new Cell( "GARDEN_GRASS_ORNAMENTAL", "grass", "Somewhat grown grass",
				apf.getAppearance( "GARDEN_GRASS" ), true, false );
		ret[ 114 ] = new Cell( "GARDEN_WALKWAY", "walkway",
				"Walkway made of old damaged bricks",
				apf.getAppearance( "GARDEN_WALKWAY" ) );
		ret[ 9 ] = new Cell( "DEAD_STUMP", "dead stump", "A dead stump",
				apf.getAppearance( "DEAD_STUMP" ), true, false );
		ret[ 11 ] = new Cell( "GARDEN_FENCE", "wooden fence", "A wooden fence",
				apf.getAppearance( "GARDEN_FENCE" ), true, false );
		ret[ 13 ] = new Cell( "GARDEN_FOUNTAIN_CENTER", "water fountain",
				"Source of water", apf.getAppearance( "GARDEN_FOUNTAIN_CENTER" ) );
		ret[ 14 ] = new Cell( "GARDEN_FOUNTAIN_AROUND", "water fountain",
				"A fountain of water", apf.getAppearance( "GARDEN_FOUNTAIN_AROUND" ) );
		ret[ 15 ] = new Cell( "GARDEN_FOUNTAIN_POOL", "fountain wall",
				"A fountain of water", apf.getAppearance( "GARDEN_FOUNTAIN_POOL" ) );
		ret[ 15 ].setShallowWater( true );
		ret[ 3 ] = new Cell( "GARDEN_DOOR", "castle door", "The doors of Dracula castle",
				apf.getAppearance( "GARDEN_DOOR" ), true, false );
		ret[ 4 ] = new Cell( "GARDEN_WALL", "stone wall", "Beaten stone wall",
				apf.getAppearance( "GARDEN_WALL" ), true, true );
		ret[ 5 ] = new Cell( "CASTLE_WALL", "castle wall", "Imponent wall of the castle",
				apf.getAppearance( "CASTLE_WALL" ), true, true );
		ret[ 6 ] = new Cell( "GARGOYLE_STATUE", "gargoyle statue",
				"A Gargoyle statue, or a sleeping monster",
				apf.getAppearance( "GARGOYLESTATUE" ), true, true );
		ret[ 7 ] = new Cell( "HUMAN_STATUE", "human statue", "A Sculpture of an human",
				apf.getAppearance( "HUMANSTATUE" ), true, true );
		ret[ 10 ] = new Cell( "GARDEN_TORCH", "torch", "A torch",
				apf.getAppearance( "GARDEN_TORCH" ) );
		ret[ 12 ] = new Cell( "CASTLE_DOOR", "castle door", "The castle doors",
				apf.getAppearance( "CASTLE_DOOR" ), true, false );
		ret[ 138 ] = new Cell( "GARDEN_TREE", "tree", "A Tree",
				apf.getAppearance( "GARDEN_TREE" ), true, true );

		/* Marble Hall */
		ret[ 29 ] = new Cell( "MARBLE_COLUMN", "stone pillar", "Stone pillar",
				apf.getAppearance( "MARBLE_COLUMN" ), true, true );
		ret[ 18 ] = new Cell( "MARBLE_FLOOR", "marble floor", "Marble Floor",
				apf.getAppearance( "MARBLE_FLOOR" ) );
		ret[ 19 ] = new Cell( "MARBLE_STAIRS", "stairs", "Stairs",
				apf.getAppearance( "MARBLE_STAIRS" ) );
		ret[ 19 ].setHeight( 1 );
		ret[ 19 ].setIsStair( true );
		ret[ 20 ] = new Cell( "MARBLE_MIDFLOOR", "marble floor", "Marble Floor",
				apf.getAppearance( "MARBLE_MIDFLOOR" ) );
		/**/ret[ 21 ] = new Cell( "MAGIC_DOOR", "magic door", "A Magic door",
				apf.getAppearance( "VOID" ) );
		ret[ 22 ] = new Cell( "BIG_WINDOW", "tall window", "A tall addorned window",
				apf.getAppearance( "BIG_WINDOW" ), true, false );
		ret[ 23 ] = new Cell( "RED_CURTAIN", "red curtain", "A badly worn red curtain",
				apf.getAppearance( "RED_CURTAIN" ) );
		ret[ 24 ] = new Cell( "GODNESS_STATUE", "statue of a goddess",
				"The statue of a goddess", apf.getAppearance( "GODNESS_STATUE" ), true,
				true );
		ret[ 25 ] = new Cell( "RED_CARPET", "red carpet", "Damaged red carpet",
				apf.getAppearance( "RED_CARPET" ) );

		ret[ 157 ] = new Cell( "QUARTERS_WALL", "stucco wall",
				"Imponent wall of the castle", apf.getAppearance( "QUARTERS_WALL" ), true,
				true );
		ret[ 158 ] = new Cell( "QUARTERS_FLOOR", "tiled floor", "Tiled Floor",
				apf.getAppearance( "QUARTERS_FLOOR" ) );
		ret[ 159 ] = new Cell( "QUARTERS_CORRIDOR", "tiled floor", "Tiled Floor",
				apf.getAppearance( "QUARTERS_FLOOR" ), false, false );
		ret[ 160 ] = new Cell( "QUARTERS_DOOR", "tiled floor", "Tiled Floor",
				apf.getAppearance( "QUARTERS_FLOOR" ) );

		/* Moat */
		ret[ 17 ] = new Cell( "GRAY_COLUMN", "granite column", "A weary granite column",
				apf.getAppearance( "GRAY_COLUMN" ), true, true );
		ret[ 61 ] = new Cell( "MOSS_COLUMN", "moss column", "Mossy stone column",
				apf.getAppearance( "MOSS_COLUMN" ), true, true );
		ret[ 26 ] = new Cell( "MOSS_FLOOR", "moss stone", "Mossy stone floor",
				apf.getAppearance( "MOSS_FLOOR" ) );
		ret[ 27 ] = new Cell( "RUSTY_PLATFORM", "rusty platform", "Rusty iron platform",
				apf.getAppearance( "RUSTY_PLATFORM" ) );
		ret[ 117 ] = new Cell( "MOSS_WATERWAY_ETH", "water", "Stream of putrid water",
				apf.getAppearance( "MOSS_WATERWAY_ETH" ) );
		ret[ 117 ].setShallowWater( true );
		ret[ 117 ].setHeight( -3 );
		ret[ 141 ] = new Cell( "MOSS_WATERWAY", "water", "Stream of putrid water",
				apf.getAppearance( "MOSS_WATERWAY" ) );
		ret[ 141 ].setWater( true );

		ret[ 142 ] = new Cell( "MOSS_STAIRS", "mossy stairs", "Mossy Stairs",
				apf.getAppearance( "MOSS_STAIRS" ) );
		ret[ 142 ].setHeight( 1 );
		ret[ 142 ].setIsStair( true );
		ret[ 143 ] = new Cell( "MOSS_MIDFLOOR", "mossy floor", "Mossy Floor",
				apf.getAppearance( "MOSS_MIDFLOOR" ) );
		ret[ 143 ].setHeight( 3 );

		/* Lab */
		ret[ 47 ] = new Cell( "RED_FLOOR", "brick floor", "Damaged brick floor",
				apf.getAppearance( "RED_FLOOR" ) );
		ret[ 48 ] = new Cell( "RED_WALL", "brick wall", "Decrepit brick wall",
				apf.getAppearance( "RED_WALL" ), true, true );
		ret[ 52 ] = new Cell( "SMALL_WINDOW", "small window",
				"A cross shaped window made inside the wall",
				apf.getAppearance( "SMALL_WINDOW" ), true, false );

		/* Ruins */
		ret[ 38 ] = new Cell( "STONE_FLOOR", "stone floor", "Stone floor",
				apf.getAppearance( "STONE_FLOOR" ) );
		ret[ 39 ] = new Cell( "STONE_WALL", "stone wall", "Stone wall",
				apf.getAppearance( "STONE_WALL" ), true, true );

		/* Sewers */
		ret[ 163 ] = new Cell( "SEWERS_FLOOR", "mossy floor", "Mossy floor",
				apf.getAppearance( "SEWERS_FLOOR" ) );
		ret[ 163 ].setShallowWater( true );
		ret[ 164 ] = new Cell( "SEWERS_WALL", "mossy wall", "Mossy wall",
				apf.getAppearance( "SEWERS_WALL" ), true, true );
		ret[ 166 ] = new Cell( "SEWERS_DOWN", "hole down", "Hole going deeper",
				apf.getAppearance( "SEWERS_DOWN" ) );
		ret[ 166 ].setShallowWater( true );
		ret[ 167 ] = new Cell( "SEWERS_UP", "hole up", "Hole on the ceiling",
				apf.getAppearance( "SEWERS_UP" ) );
		ret[ 167 ].setShallowWater( true );
		ret[ 168 ] = new Cell( "SEWERS_FLOOR_WATER", "mossy floor", "Mossy floor",
				apf.getAppearance( "SEWERS_FLOOR" ) );
		ret[ 168 ].setWater( true );
		ret[ 169 ] = new Cell( "SEWERS_WALL_WATER", "mossy wall", "Mossy wall",
				apf.getAppearance( "SEWERS_WALL" ), true, true );
		ret[ 170 ] = new Cell( "SEWERS_DOWN_WATER", "hole down", "Hole going deeper",
				apf.getAppearance( "SEWERS_DOWN" ) );
		ret[ 170 ].setWater( true );
		ret[ 171 ] = new Cell( "SEWERS_UP_WATER", "hole up", "Hole on the ceiling",
				apf.getAppearance( "SEWERS_UP" ) );
		ret[ 171 ].setWater( true );

		ret[ 172 ] = new Cell( "WEIRD_MACHINE", "weird machine", "Weird Machine",
				apf.getAppearance( "WEIRD_MACHINE" ) );

		/* Caves */
		ret[ 36 ] = new Cell( "CAVE_FLOOR", "soil", "Harsh cave soil",
				apf.getAppearance( "CAVE_FLOOR" ) );
		ret[ 37 ] = new Cell( "CAVE_WALL", "cave", "Rocky wall",
				apf.getAppearance( "CAVE_WALL" ), true, true );
		ret[ 119 ] = new Cell( "CAVE_WATER", "subterranean lake", "Subterranean Lake",
				apf.getAppearance( "CAVE_WATER" ) );
		ret[ 119 ].setShallowWater( true );
		ret[ 55 ] = new Cell( "LAVA", "lava", "Lava", apf.getAppearance( "LAVA" ) );

		/* Warehouse */
		ret[ 151 ] = new Cell( "WAREHOUSE_FLOOR", "ruined stone floor",
				"Ruined Stone Floor", apf.getAppearance( "WAREHOUSE_FLOOR" ) );
		ret[ 152 ] = new Cell( "WAREHOUSE_WALL", "cracked stone wal",
				"Cracked Stone Wall", apf.getAppearance( "WAREHOUSE_WALL" ), true, true );

		/* Castle Keep */
		ret[ 41 ] = new Cell( "BARRED_WINDOW", "barred window",
				"A wide window, covered with iron bars",
				apf.getAppearance( "BARRED_WINDOW" ), true, false );
		ret[ 42 ] = new Cell( "DRACULA_THRONE1", "Dracula's Throne",
				"The throne of Dracula", apf.getAppearance( "DRACULA_THRONE1" ), true,
				false );
		ret[ 43 ] = new Cell( "DRACULA_THRONE2", "Dracula's Throne",
				"The throne of Dracula", apf.getAppearance( "DRACULA_THRONE2" ) );
		ret[ 44 ] = new Cell( "BALCONY", "balcony", "Marble Balcony",
				apf.getAppearance( "BALCONY" ), true, false );
		ret[ 45 ] = new Cell( "STONE_STAIRWAY", "stone stairway", "Rough stone stairway",
				apf.getAppearance( "STONE_STAIRWAY" ) );
		ret[ 46 ] = new Cell( "CLOCK_TOWER", "clock tower", "Clock Tower",
				apf.getAppearance( "STONE_WALL" ), true, true );
		ret[ 129 ] = new Cell( "KEEP_FLOOR", "tiled floor", "Tiled floor",
				apf.getAppearance( "KEEP_FLOOR" ) );
		ret[ 130 ] = new Cell( "KEEP_WALL", "painted wall", "Painted Wall",
				apf.getAppearance( "KEEP_WALL" ), true, true );
		ret[ 131 ] = new Cell( "KEEP_CARPET", "red carpet", "Red Carpet",
				apf.getAppearance( "KEEP_CARPET" ) );
		/* Void */
		ret[ 49 ] = new Cell( "VOID_STAR", "star", "A star",
				apf.getAppearance( "VOID_STAR" ) );
		ret[ 50 ] = new Cell( "VOID_SUN", "star", "A star",
				apf.getAppearance( "VOID_SUN" ) );
		ret[ 51 ] = new Cell( "VOID_FLOOR", "nothing", "Nothing",
				apf.getAppearance( "VOID" ) );
		ret[ 56 ] = new Cell( "VOID_WALL", "nothing", "Nothing",
				apf.getAppearance( "VOID" ), true, true );

		/* Frank Lab */
		ret[ 53 ] = new Cell( "WIRES", "wires", "Electricity Wires",
				apf.getAppearance( "WIRES" ) );
		ret[ 54 ] = new Cell( "FRANK_TABLE", "experiment table", "Experiment Table",
				apf.getAppearance( "FRANK_TABLE" ) );

		/* Chapel */
		ret[ 64 ] = new Cell( "CHURCH_WALL", "chapel wall", "Decrepit brick wall",
				apf.getAppearance( "CHURCH_WALL" ), true, true );
		ret[ 65 ] = new Cell( "CHURCH_FLOOR", "chapel floor", "Polished stone floor",
				apf.getAppearance( "CHURCH_FLOOR" ) );
		ret[ 66 ] = new Cell( "CHURCH_WOODEN_BARRIER_H", "wooden barrier",
				"Ellaborated wooden barrier",
				apf.getAppearance( "CHURCH_WOODEN_BARRIER_H" ), true, false );
		ret[ 67 ] = new Cell( "CHURCH_WOODEN_BARRIER_V", "wooden barrier",
				"Ellaborated wooden barrier",
				apf.getAppearance( "CHURCH_WOODEN_BARRIER_V" ), true, false );
		ret[ 68 ] = new Cell( "CHURCH_CHAIR", "chapel chair", "Decrepit church chair",
				apf.getAppearance( "CHURCH_CHAIR" ), true, false );
		ret[ 69 ] = new Cell( "CHURCH_CONFESSIONARY", "confessionary",
				"A wooden confessionary", apf.getAppearance( "CHURCH_CONFESSIONARY" ),
				true, true );
		ret[ 70 ] = new Cell( "CHURCH_CARPET", "purple carpet", "A purple carpet",
				apf.getAppearance( "CHURCH_CARPET" ) );
		ret[ 71 ] = new Cell( "ATRIUM", "atrium", "The Chapel Atrium",
				apf.getAppearance( "ATRIUM" ) );
		ret[ 71 ].setHeight( 3 );
		ret[ 72 ] = new Cell( "CHURCH_STAINED_WINDOW", "stained window",
				"Beautiful glass window", apf.getAppearance( "CHURCH_STAINED_WINDOW" ),
				true, false );
		ret[ 73 ] = new Cell( "CHURCH_FLOOR_H", "chapel floor", "Polished stone floor",
				apf.getAppearance( "CHURCH_FLOOR_H" ) );
		ret[ 115 ] = new Cell( "CHURCH_STAIRSUP", "stairs", "Stairs",
				apf.getAppearance( "TOWN_STAIRSUP" ) );
		ret[ 115 ].setHeightMod( -1 );
		ret[ 116 ] = new Cell( "CHURCH_STAIRSDOWN", "stairs", "Stairs",
				apf.getAppearance( "TOWN_STAIRSDOWN" ) );
		ret[ 116 ].setHeightMod( 1 );

		/* Ruins and Mummies Lair */
		ret[ 74 ] = new Cell( "RUINS_COLUMN", "stone column", "Ruined stone column",
				apf.getAppearance( "RUINS_COLUMN" ), true, true );
		ret[ 75 ] = new Cell( "RUINS_FLOOR", "stone floor", "Ruined stone floor",
				apf.getAppearance( "RUINS_FLOOR" ) );
		ret[ 76 ] = new Cell( "RUINS_WALL", "stone wall", "Ruined stone wall",
				apf.getAppearance( "RUINS_WALL" ), true, true );
		ret[ 77 ] = new Cell( "RUINS_FLOOR_H", "stone floor", "Ruined stone floor",
				apf.getAppearance( "RUINS_FLOOR_H" ) );
		ret[ 77 ].setHeight( 3 );
		ret[ 78 ] = new Cell( "RUINS_STAIRS", "stone stairs", "Ruined stone stairway",
				apf.getAppearance( "RUINS_STAIRS" ) );
		ret[ 78 ].setHeight( 1 );
		ret[ 78 ].setIsStair( true );
		ret[ 126 ] = new Cell( "RUINS_DOOR", "broken door", "Ruined door",
				apf.getAppearance( "RUINS_DOOR" ), false, true );

		/* Courtyard */
		ret[ 79 ] = new Cell( "COURTYARD_WALL", "castle wall", "Cracked stone wall",
				apf.getAppearance( "COURTYARD_WALL" ), true, true );
		ret[ 80 ] = new Cell( "COURTYARD_FLOOR", "stone floor", "Cracked stone floor",
				apf.getAppearance( "COURTYARD_FLOOR" ) );
		ret[ 81 ] = new Cell( "COURTYARD_COLUMN", "massive column",
				"Massive stone column", apf.getAppearance( "COURTYARD_COLUMN" ), true,
				true );
		ret[ 82 ] = new Cell( "COURTYARD_FENCE", "iron fence", "Iron fence",
				apf.getAppearance( "COURTYARD_FENCE" ), true, false );
		ret[ 83 ] = new Cell( "COURTYARD_GRASS", "dry grass", "Dry grass",
				apf.getAppearance( "COURTYARD_GRASS" ) );
		ret[ 84 ] = new Cell( "COURTYARD_FLOWERS", "flowers", "Flowers",
				apf.getAppearance( "COURTYARD_FLOWERS" ) );
		ret[ 120 ] = new Cell( "COURTYARD_FOUNTAIN_CENTER", "water fountain",
				"Source of water", apf.getAppearance( "COURTYARD_FOUNTAIN_CENTER" ) );
		ret[ 121 ] = new Cell( "COURTYARD_FOUNTAIN_AROUND", "water fountain",
				"A fountain of water", apf.getAppearance( "COURTYARD_FOUNTAIN_AROUND" ) );
		ret[ 122 ] = new Cell( "COURTYARD_FOUNTAIN_POOL", "fountain wall",
				"A fountain of water", apf.getAppearance( "COURTYARD_FOUNTAIN_POOL" ) );
		ret[ 122 ].setShallowWater( true );
		ret[ 123 ] = new Cell( "COURTYARD_TREE", "burnt tree", "Burnt tree",
				apf.getAppearance( "COURTYARD_TREE" ), true, true );

		ret[ 124 ] = new Cell( "COURTYARD_RUINED_WALL", "ruined brick wall",
				"Ruined brick wall", apf.getAppearance( "COURTYARD_RUINED_WALL" ), true,
				true );
		ret[ 125 ] = new Cell( "COURTYARD_STAIRS", "ruined brick stairs",
				"Ruined brick stairs", apf.getAppearance( "COURTYARD_STAIRS" ) );
		ret[ 125 ].setHeight( 1 );
		ret[ 125 ].setIsStair( true );

		/* Clock Tower */
		ret[ 85 ] = new Cell( "TOWER_FLOOR", "brick floor", "Gray brick floor",
				apf.getAppearance( "TOWER_FLOOR" ) );
		ret[ 86 ] = new Cell( "TOWER_WALL", "brick wall", "Gray brick wall",
				apf.getAppearance( "TOWER_WALL" ), true, true );
		ret[ 87 ] = new Cell( "TOWER_COLUMN", "massive brick column",
				"Gigantic bricks pillar", apf.getAppearance( "TOWER_COLUMN" ), true,
				true );
		ret[ 88 ] = new Cell( "TOWER_WINDOW", "small barred window", "Arc barred window",
				apf.getAppearance( "TOWER_WINDOW" ), true, false );
		ret[ 89 ] = new Cell( "CAMPANARIUM", "campanarium", "Tower with bronze bell",
				apf.getAppearance( "CAMPANARIUM" ), true, true );
		ret[ 90 ] = new Cell( "TOWER_FLOOR_H", "brick floor (high)", "Gray brick floor",
				apf.getAppearance( "TOWER_FLOOR_H" ) );
		ret[ 90 ].setHeight( 3 );
		ret[ 91 ] = new Cell( "TOWER_STAIRS", "brick stairs", "Brick stairs",
				apf.getAppearance( "TOWER_STAIRS" ) );
		ret[ 91 ].setHeight( 1 );
		ret[ 91 ].setIsStair( true );
		ret[ 92 ] = new Cell( "CLOCK_GEAR_1", "big clock gear", "Rotating clock gear",
				apf.getAppearance( "CLOCK_GEAR_1" ) );
		ret[ 93 ] = new Cell( "CLOCK_GEAR_2", "big clock gear", "Rotating clock gear",
				apf.getAppearance( "CLOCK_GEAR_2" ) );
		ret[ 94 ] = new Cell( "CLOCK_GEAR_3", "big clock gear", "Clock gear",
				apf.getAppearance( "CLOCK_GEAR_3" ) );
		ret[ 95 ] = new Cell( "CLOCK_GEAR_4", "big clock gear", "Rotating clock gear",
				apf.getAppearance( "CLOCK_GEAR_4" ) );
		ret[ 102 ] = new Cell( "TOWER_UP", "stairway up", "Up stairway",
				apf.getAppearance( "TOWER_UP" ) );
		ret[ 102 ].setHeightMod( -1 );
		ret[ 103 ] = new Cell( "TOWER_DOWN", "stairway down", "Down stairway",
				apf.getAppearance( "TOWER_DOWN" ) );
		ret[ 103 ].setHeightMod( 1 );
		ret[ 104 ] = new Cell( "TOWER_FLOOR_UP", "stairway up", "Up stairway",
				apf.getAppearance( "TOWER_UP" ) );
		ret[ 40 ] = new Cell( "FAKE_STAIRDOWN", "Downward stairs", "Downward stairs",
				apf.getAppearance( "TOWER_DOWN" ) );

		/* Dungeon */
		ret[ 96 ] = new Cell( "DUNGEON_FLOOR", "dungeon floor", "Dungeon floor",
				apf.getAppearance( "DUNGEON_FLOOR" ) );
		ret[ 97 ] = new Cell( "DUNGEON_DOOR", "eerie door", "Badly damaged door",
				apf.getAppearance( "DUNGEON_DOOR" ) );
		ret[ 98 ] = new Cell( "DUNGEON_WALL", "dungeon wall", "Dungeon Wall",
				apf.getAppearance( "DUNGEON_WALL" ), true, true );
		ret[ 99 ] = new Cell( "DUNGEON_PASSAGE", "dark passageway", "Dark passageway",
				apf.getAppearance( "DUNGEON_PASSAGE" ), false, true );
		ret[ 100 ] = new Cell( "DUNGEON_DOWN", "stairs down", "Stairs going down",
				apf.getAppearance( "DUNGEON_DOWN" ) );
		ret[ 101 ] = new Cell( "DUNGEON_UP", "stairs up", "Stairs going up",
				apf.getAppearance( "DUNGEON_UP" ) );

		ret[ 20 ].setHeight( 3 );
		// ret[29].setHeight(3);
		ret[ 34 ].setHeight( 3 );

		ret[ 55 ].setDamageOnStep( 2 );

		return ret;
	}

}
