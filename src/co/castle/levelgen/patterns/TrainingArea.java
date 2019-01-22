package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.training.Training1;
import co.castle.cuts.training.Training2;
import co.castle.cuts.training.Training3ChrisDeath;

public class TrainingArea extends StaticPattern
{
	public TrainingArea( )
	{
		this.cellMap = new String[ ][ ]
		{
			{	"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb......bbbbbbbbbbbbbbbbbbbbbb",
				"bb.......h.............rrr....bb..........bb...bbb...wwwwwwwwwwwww...bb",
				"b..............dd......rrr....b............bbbbb.....wxxxxxxxxxxxw....b",
				"b.....h........dd......rrr...bb...............b......wx!xx@xxxxxxw....b",
				"b......................rrr..bb......b..sssss..b......wxxxxxxxxxxxw....b",
				"b.........sssssss.s.....rrr.b.......b..ssss6..b...b..wwwwwwwww/www....b",
				"b.....Bsssssssss........rr..b.......b.h.ss....b.......................b",
				"b......sssssssbssss......rr.b.......b.h.......b................1......b",
				"b......s..sssssssss.....rrr.b.......bb..rrrrrrbb..........h...h.......b",
				"b..b....sssssBs.ss.....rrr.bb........b.rrrrrrrbbb......h....h........bb",
				"b......................rr..b.....7..bbrrrrrrrrbbbb....................b",
				"bb...B.......rrr.......rr..b..c.....bbbrrbbbbbbrrbb...................b",
				".bb............rrr......rr.bb.......bbbbbbrrrrrr..bb.................bb",
				"bbbb....B............rrrrr.bb.........bbrrrrrrr.5..bb...............bb.",
				"b....9.....b..........rrr...bb.........bbrrrr.......bbbbb......2...bbbb",
				"X...............rr...........bbb........b...............bb.......bbb...",
				"b...............r.............dbbb......bbb...b......b...bbrrrrrbb.....",
				"bbbb............r.............dddbb.......bb......b......b.......b.....",
				"..b................S..........ddd.bb.......bb...........bb.......bb....",
				"..b.....sssss..ssss............dd..b........bb..........b.........b....",
				"..b....Sss.sssssssss....b......dd..b........b...........b.....3...b....",
				".bb....ssssssssss.ss..ss.......dd..bb.......b...........b.........b....",
				".b.....ssssssssbsss...ss......ddd.bb........bb..q......bb..b..b...bb...",
				"bb.....ss..sssssssss..ss......ddd............b.........b..........b....",
				"b...b..Sssssss.ssss...........ddd..8.........b.4.............b...bb....",
				"bb............................dddd........bbbb...................b.....",
				"bbb...........bbb.......bbb..bbddd.....bbbb..bb......bbbbb......bb.....",
				".bbbbbbbbbbbbbb.bbbbbbbbb.bbbbbbbbbbbbbb......bbbbbbbb...bbbbbbbb......" },
			{	"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbb-----bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbb---bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbb---bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbb--bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb--bbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb-------bbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbb-bbbbbbbbbbbbbb-------bbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbb---bbbbbbbbbbbbb----------bbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbb--bbbb-----bbbbbbbbbbbbbbb------bbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbb----------bbbbbbbbbbbbb-------bbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbb----bbbbbbbbbbbbbbb----bbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbb-bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" }

		};

		charMap.put( ".", "FOREST_GRASS" );
		charMap.put( "s", "FOREST_DIRT" );
		charMap.put( "d", "FOREST_DIRT" );
		charMap.put( "r", "TOWN_WATERWAY" );
		charMap.put( "-", "TOWN_WATER" );
		charMap.put( "b", "TOWN_TREE" );
		charMap.put( "w", "TOWN_WALL" );
		charMap.put( "x", "HOUSE_FLOOR" );
		charMap.put( "/", "TOWN_DOOR" );
		charMap.put( "q", "FOREST_GRASS WEAPON BOW X" );
		charMap.put( "c", "FOREST_GRASS FEATURE CROSSWP 100" );
		charMap.put( "h", "FOREST_GRASS ITEM HEAL_HERB" );
		charMap.put( "B", "FOREST_GRASS MONSTER R_SKELETON" );
		charMap.put( "S", "FOREST_GRASS MONSTER BAT" );
		charMap.put( "1", "SIGNPOST_T1" );
		charMap.put( "2", "SIGNPOST_T2" );
		charMap.put( "3", "SIGNPOST_T3" );
		charMap.put( "4", "SIGNPOST_T4" );
		charMap.put( "5", "SIGNPOST_T5" );
		charMap.put( "6", "SIGNPOST_T6" );
		charMap.put( "7", "SIGNPOST_T7" );
		charMap.put( "8", "SIGNPOST_T8" );
		charMap.put( "9", "SIGNPOST_T9" );
		charMap.put( "!", "HOUSE_FLOOR NPC CHRISTRAIN" );
		charMap.put( "@", "HOUSE_FLOOR EXIT _START" );
		charMap.put( "X", "FOREST_GRASS EXIT #END" );

		unleashers = new Unleasher[ ]
		{ new Training1( ), new Training2( ), new Training3ChrisDeath( ) };
	}

	public String getDescription( )
	{
		return "Christopher House";
	}

	public String getMapKey( )
	{
		return "FOREST";
	}

	public String getMusicKeyMorning( )
	{
		return "TRAINING";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

}