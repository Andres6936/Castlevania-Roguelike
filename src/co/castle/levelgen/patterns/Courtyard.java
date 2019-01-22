package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;

public class Courtyard extends StaticPattern
{
	public Courtyard( )
	{
		this.cellMap = new String[ ][ ]
		{
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwCwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"ww..........................................................................ww",
				"w.........o.......o........o.......o.........o..........o........o......o....w",
				"w.................................................ssssss......ssssssssss.....w",
				"w.....sssssssssssgssssssssssss.....ggggggggg.....ssgggggg...sssggggggggss....w",
				"w....ssggggggggggggggggggggggss...ggddddddddg...ssggggggs.sssgggggggggggs....w",
				"w....sggggggggggggggggggggggggs...ggddggggdgg...sgggggggsssggggggssssssgss...w",
				"w....sggggggggggggggggggggggggs...gggddggdggg...sggggggggggggggggs....ssgs...w",
				"w....sggggggggggggggggggggggggs...gdddggddddg...sggggggggggggggggss....sss...w",
				"w....sgggggggggggggggggggggggss....ggggggggg....ssggggggggggggggggs.....ss...w",
				"w....sggggggggggggggggggssssss...................sssssggggggggggggs..........w",
				"w....sgggggggggggggggggsss...........................sssgggggggggss..........w",
				"w....sggggggggggggggggss...............................ssgggggggss...........w",
				"w....ssggggggggggggggss.................................ssgggggss............w",
				"w....osssssssgssssssss....o...fffffffffffffffffff...o....sssssss.........o...w",
				"w............................ff~~~~~~~~f~~~~~~~~ff...........................w",
				"w...........................ff~~~~~~~~~f~~~~~~~~~ff..........................w",
				"w...........................f~~~~~~~~~ccc~~~~~~~~~f..........................w",
				"w...........................f~~~~~~~~ccccc~~~~~~~~f..........................w",
				"V...........................f~~~~~~~~ccccc~~~~~~~~f..........................D",
				"w...........................f~~~~~~~~~ccc~~~~~~~~~f..........................w",
				"w...........................ff~~~~~~~~~f~~~~~~~~~ff..........................w",
				"w............................ff~~~~~~~~f~~~~~~~~ff...........................w",
				"w....ossssssssssssssss....o...fffffffffffffffffff...o....sssssss.........o...w",
				"w....ssggggggggggggggss.................................ssgggggss............w",
				"w....sggggggggggggggggss...............................ssgggggggss...........w",
				"w....sgggggggggggggggggsss...........................sssgggggggggss..........w",
				"w....sggggggggggggggggggssssss...................sssssggggggggggggs..........w",
				"w....sgggggggggggggggggggggggss....ggggggggg....ssggggggggggggggggg.....ss...w",
				"w....sggggggggggggggggggggggggs...gdddggddddg...sggggggggggggggggss....sss...w",
				"w....sggggggEgggggggggggggggggs...gggddggdggg...sggggggggggggggggs....ssgs...w",
				"w....sggggggggggggggggggggggggs...ggddggggdgg...sgggggggsssggggggssssssgss...w",
				"w....ssggggggggggggggggggggggss...ggddddddddg...ssggggggs.sssgggggggggggs....w",
				"w.....sssssssssssggsssssssssss.....ggggggggg.....ssggggss...sssggggggggss....w",
				"w.................................................ssssss......ssssssssss.....w",
				"w.........o.......o........o.......o.........o..........o........o......o....w",
				"ww..........................................................................ww",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" } };

		charMap.put( "w", "COURTYARD_WALL" );
		charMap.put( ".", "COURTYARD_FLOOR" );
		charMap.put( "o", "COURTYARD_COLUMN" );
		charMap.put( "s", "COURTYARD_FENCE" );
		charMap.put( "g", "COURTYARD_GRASS" );
		charMap.put( "d", "COURTYARD_FLOWERS" );
		charMap.put( "~", "COURTYARD_FOUNTAIN_POOL" );
		charMap.put( "c", "COURTYARD_FOUNTAIN_CENTER" );
		charMap.put( "f", "COURTYARD_FOUNTAIN_AROUND" );
		charMap.put( "E", "FAKE_STAIRDOWN EXIT _BACK" );
		charMap.put( "D", "COURTYARD_FLOOR EXIT_FEATURE _NEXT MAGIC_DOOR 100 COST 1" );
		charMap.put( "V", "COURTYARD_FLOOR EXIT VILLA" );
		charMap.put( "C", "COURTYARD_FLOOR EXIT DINING_HALL" );
		// charMap.put("C", "COURTYARD_WALL");

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "GHOUL", MonsterSpawnInfo.UNDERGROUND, 80 ) };
	}

	public String getDescription( )
	{
		return "Castle Courtyard";
	}

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "HUNCHBACK", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "DURGA", MonsterSpawnInfo.UNDERGROUND, 80 ),
			new MonsterSpawnInfo( "RULER_SWORD_LV2", MonsterSpawnInfo.UNDERGROUND, 20 ),
			new MonsterSpawnInfo( "FIRE_WARG", MonsterSpawnInfo.UNDERGROUND, 60 ),
			new MonsterSpawnInfo( "BASILISK", MonsterSpawnInfo.UNDERGROUND, 40 ), };
	}

	public String getMapKey( )
	{
		return "COURTYARD";
	}

	public String getMusicKeyMorning( )
	{
		return "COURTYARD";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

	public boolean isRutinary( )
	{
		return true;
	}

}