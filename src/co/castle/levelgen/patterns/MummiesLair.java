package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.mummies.Mummy1;
import sz.util.Position;

public class MummiesLair extends StaticPattern
{

	public MummiesLair( )
	{
		cellMap = new String[ ][ ]
		{
			{

				"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   ",
				"  ww............................ww  ",
				" ww.......cc.....o.....cc........ww ",
				"ww..............ooo...............ww",
				"w....o..........hoh...........o....w",
				"w...ooo..c..c..hhhhh..c..c...ooo...w",
				"w....o.........shhhs.........Co....w",
				"w..............hhhhh...............w",
				"w....h..........hhh...........h....w",
				"w.c.hhh......................hhh...w",
				"w....h........................h....w",
				"E...ss...........................c.w",
				"w....h........................h..c.w",
				"w.c.hhh......................hhh...w",
				"w....h..........hhh...........h....w",
				"w..............hhhhh...............w",
				"w....o.........shhhs.........Co....w",
				"w...ooo..c..c..hhhhh..c..c...ooo...w",
				"w....o..........hoh...........o....w",
				"ww..............ooo...............ww",
				" ww.......cc.....o.....cc........ww ",
				"  ww............................ww  ",
				"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   " },
			{	"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   ",
				"  ww                            ww  ",
				" ww              o               ww ",
				"ww              ooo               ww",
				"w    o           o            o    w",
				"w   ooo                      ooo   w",
				"w    o                        o    w",
				"w                                  w",
				"w                                  w",
				"w<                                 w",
				"w.                                 w",
				"w.                                 w",
				"w.                                 w",
				"w>                                 w",
				"w                                  w",
				"w                                  w",
				"w    o                        o    w",
				"w   ooo                      ooo   w",
				"w    o           o            o    w",
				"ww              ooo               ww",
				" ww              o               ww ",
				"  ww                            ww  ",
				"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   " },
			{	"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   ",
				"  ww                            ww  ",
				" ww              o               ww ",
				"ww              ooo               ww",
				"w    o           o            o    w",
				"w   ooo                      ooo   w",
				"w    o                        o    w",
				"w                                  w",
				"w                                  w",
				"w>                                 w",
				"w.                                 w",
				"w.                                 w",
				"w.                                 w",
				"w<                                 w",
				"w                                  w",
				"w                                  w",
				"w    o                        o    w",
				"w   ooo                      ooo   w",
				"w    o           o            o    w",
				"ww              ooo               ww",
				" ww              o               ww ",
				"  ww                            ww  ",
				"   wwwwwwwwwwwwwwwwwwwwwwwwwwwwww   " },
			{	"...wwwwwwwwwwwwwwwwwwwwwwwwwwwwww...",
				"..ww............................ww..",
				".ww..............o...............ww.",
				"ww..............ooo...............ww",
				"w....o...........o............o....w",
				"w...ooo......................ooo...w",
				"w....o........................o....w",
				"w..................................w",
				"w..................................w",
				"w<.................................w",
				"w..................................w",
				"w..................................D",
				"w..................................w",
				"w..................................w",
				"w..................................w",
				"w..................................w",
				"w....o........................o....w",
				"w...ooo......................ooo...w",
				"w....o...........o............o....w",
				"ww..............ooo...............ww",
				".ww..............o...............ww.",
				"..ww............................ww..",
				"...wwwwwwwwwwwwwwwwwwwwwwwwwwwwww..."

			} };

		charMap.put( "o", "RUINS_COLUMN" );
		charMap.put( ".", "RUINS_FLOOR" );
		charMap.put( "w", "RUINS_WALL" );
		charMap.put( "h", "RUINS_FLOOR_H" );
		charMap.put( "s", "RUINS_STAIRS" );
		charMap.put( "<", "MARBLE_STAIRSUP" );
		charMap.put( ">", "MARBLE_STAIRSDOWN" );
		charMap.put( "c", "RUINS_FLOOR FEATURE CANDLE 70" );
		charMap.put( "C", "RUINS_FLOOR FEATURE COFFIN 100" );
		charMap.put( "E", "RUINS_FLOOR EXIT _BACK" );
		charMap.put( "D", "RUINS_FLOOR EXIT _NEXT" );

		unleashers = new Unleasher[ ]
		{ new Mummy1( ), };
	}

	public String getBoss( )
	{
		return "AKMODAN";

	}

	public Position getBossPosition( )
	{
		return new Position( 25, 10 );
	}

	public String getDescription( )
	{
		return "Akmodans Tomb";
	}

	public String getMapKey( )
	{
		return "RUINS";
	}

	public String getMusicKeyMorning( )
	{
		return "BOSS2";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}
}