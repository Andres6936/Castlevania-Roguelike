package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;

public class Tower extends StaticPattern
{

	public Tower( )
	{
		cellMap = new String[ ][ ]
		{
			{	"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ",
				"    www............w...............www    ",
				"   ww..c...........w..............c..ww   ",
				"  ww..ccc..........wd............ccc..ww  ",
				" ww....c...........w..............c....ww ",
				" w.................w....................w ",
				"ww.................w....................ww",
				"w...........                  ...........w",
				"w..........                    ..........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"wwwwww....                      .........w",
				"w....wwwwwwww                   .........w",
				"+...........wwww+wwwwwwwwww+wwwwwwwww....w",
				"w.........i....i....i....i....i.....wwww.w",
				"w..N..................................dwww",
				"w.........i....i....i....i....i.....wwww.w",
				"+...........wwww+wwwwwwwwww+wwwwwwwww....w",
				"w....wwwwwwww -            -    .........w",
				"wwwwww....   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w..........                    ..........w",
				"ww....................w.................ww",
				" w....................w.................w ",
				" ww....c..............w...........c....ww ",
				"  ww..ccc............dw..........ccc..ww  ",
				"   ww..c..............w...........c..ww   ",
				"    www...............w............www    ",
				"      wwwwwwwwwwwwwwwwwwwwwwwwwwwwww      ", },
			{	"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ",
				"    www............w...............www    ",
				"   ww..c...........w..............c..ww   ",
				"  ww..ccc..........wd............ccc..ww  ",
				" ww....c...........w..............c....ww ",
				" w.................w....................w ",
				"ww.................w....................ww",
				"w...........                  ...........w",
				"w..........                    ..........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"www.ww....                      .........w",
				"w....wwwww                      .........w",
				"w.........  -                -  .........w",
				"w..d...... <o>              <o> .........w",
				"w.........  -                -  ......u..w",
				"w.........                      .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w..........                    ..........w",
				"ww....................w.................ww",
				" w....................w.................w ",
				" ww....c..............w...........c....ww ",
				"  ww..ccc............dw..........ccc..ww  ",
				"   ww..c..............w...........c..ww   ",
				"    www...............w............www    ",
				"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ", },
			{	"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ",
				"    www..............w.............www    ",
				"   ww..c.............w............c..ww   ",
				"  ww..ccc...........uw...........ccc..ww  ",
				" ww....c.............w............c....ww ",
				" w...................w..................w ",
				"ww...................w..................ww",
				"w...........                  ...........w",
				"w..........                    ..........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........        wwwwww        .........w",
				"w.........       ww....ww       .........w",
				"w..u......       w..  ..w       ......d..w",
				"w.........       ww....ww       .........w",
				"w.........        wwwwww        .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w..........                    ..........w",
				"ww..................w...................ww",
				" w..................w...................w ",
				" ww....c............w.............c....ww ",
				"  ww..ccc...........wu...........ccc..ww  ",
				"   ww..c............w.............c..ww   ",
				"    www.............w..............www    ",
				"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ", },
			{	"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ",
				"    www............................www    ",
				"   ww..c..........................c..ww   ",
				"  ww..ccc...........d............ccc..ww  ",
				" ww....c..........................c....ww ",
				" w......................................w ",
				"ww......................................ww",
				"w...........                  ...........w",
				"w..........                    ..........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........        wwwwww        .........w",
				"w.........       ww....ww       .........w",
				"S.........       w..  ..w       ......u..w",
				"w.........       ww....ww       .........w",
				"w.........        wwwwww        .........w",
				"w.........                      .........w",
				"w.........    -            -    .........w",
				"w.........   <o>          <o>   .........w",
				"w.........    -            -    .........w",
				"w.........                      .........w",
				"w.........     c          c     .........w",
				"w.........    ccc        ccc    .........w",
				"w.........     c          c     .........w",
				"w.........                      .........w",
				"w..........                    ..........w",
				"ww......................................ww",
				" w......................................w ",
				" ww....c..........................c....ww ",
				"  ww..ccc...........d............ccc..ww  ",
				"   ww..c..........................c..ww   ",
				"    www............................www    ",
				"      wwwwwww+wwwwwwwwwwwwww+wwwwwww      ", },
			{	"      wwwwwwwwwwwwwwwwwwwwwwwwwwwwww      ",
				"    www............................www    ",
				"   ww..cwwwwwwwwwwwwwwwwwwwwwwwwwwc..ww   ",
				"  ww..ccc...........u............ccc..ww  ",
				" ww...wc..........................cw...ww ",
				" w....w............................w....w ",
				"ww....w............................w....ww",
				"w.....w............................w.....w",
				"w.....w............................w.....w",
				"w.....w........c..........c........w.....w",
				"w.....w.......ccc........ccc.......w.....w",
				"w.....w........c..........c........w.....w",
				"w.....w............................w.....w",
				"w.....w.......-............-.......w.....w",
				"w.....w......<o>..........<o>......w.....w",
				"w.....w.......-............-.......w.....w",
				"w.....w............................w.....w",
				"w.....w...........wwwwww...........w.....w",
				"w.....w..........wwhhhhww..........w.....w",
				"w.....w..........hhhhhhhh..........w.....w",
				"w.....w..........wwhhhhww..........w.....w",
				"w.....w...........wwwwww...........w.....w",
				"w.....w............................w.....w",
				"w.....w.......-............-.......w.....w",
				"w.....w......<o>..........<o>......w.....w",
				"w.....w.......-............-.......ww....w",
				"w.....w............................w.....w",
				"w.....w........c..........c........w.....w",
				"w.....w.......ccc........ccc.......w.....w",
				"w.....w........c..........c........w.....w",
				"w.....w............................w.....w",
				"w.....w............................w.....w",
				"ww....w............................w....ww",
				" w....w............................w....w ",
				" ww...wc..........................cw...ww ",
				"  ww..ccc...........u............ccc..ww  ",
				"   ww..cwwwwwwwwwwwwwwwwwwwwwwwwwwc..ww   ",
				"    www............................www    ",
				"      wwwwwwwwwwwwwwwwwwwwwwwwwwwwww      ", }

		};

		charMap.put( ".", "TOWER_FLOOR" );
		charMap.put( "w", "TOWER_WALL" );
		charMap.put( "c", "TOWER_COLUMN" );
		charMap.put( "+", "TOWER_WINDOW" );
		charMap.put( "i", "TOWER_FLOOR FEATURE CANDLE" );
		charMap.put( "^", "CAMPANARIUM" );
		charMap.put( "h", "TOWER_FLOOR_H" );
		charMap.put( "N", "TOWER_FLOOR_UP EXIT _NEXT" );
		charMap.put( "S", "TOWER_FLOOR EXIT _BACK" );
		charMap.put( "u", "TOWER_UP" );
		charMap.put( "d", "TOWER_DOWN" );
		charMap.put( ">", "CLOCK_GEAR_1" );
		charMap.put( "<", "CLOCK_GEAR_2" );
		charMap.put( "o", "CLOCK_GEAR_3" );
		charMap.put( "-", "CLOCK_GEAR_4" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "MEDUSA_HEAD", MonsterSpawnInfo.BORDER, 50 ) };
	}

	public String getDescription( )
	{
		return "Clock Tower";
	}

	public MonsterSpawnInfo[ ] getDwellers( )
	{
		return new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "CROW", MonsterSpawnInfo.UNDERGROUND, 90 ),
			new MonsterSpawnInfo( "DRAGON_SKULL_CANNON", MonsterSpawnInfo.UNDERGROUND,
					20 ),
			new MonsterSpawnInfo( "BUER", MonsterSpawnInfo.UNDERGROUND, 50 ),
			new MonsterSpawnInfo( "HARPY", MonsterSpawnInfo.UNDERGROUND, 60 ),
			new MonsterSpawnInfo( "BONE_MUSKET", MonsterSpawnInfo.UNDERGROUND, 40 ),
			new MonsterSpawnInfo( "LILITH", MonsterSpawnInfo.UNDERGROUND, 50 ), };

	}

	public String getMapKey( )
	{
		return "CLOCKTOWER";
	}

	public String getMusicKeyMorning( )
	{
		return "TOWER";
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