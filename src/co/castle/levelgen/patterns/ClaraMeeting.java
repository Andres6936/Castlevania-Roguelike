package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.ingame.clara1.Clara1;

public class ClaraMeeting extends StaticPattern
{
	public ClaraMeeting( )
	{
		cellMap = new String[ ][ ]
		{
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"w                                 w",
				"w        ww.www                   w",
				"w        ....>.                   w",
				"w        w....w   ..........      w",
				"w        ......   . ........      w",
				"w                 .......  ..     w",
				"w                 .  ..>...w      w",
				"w                 ......   w      w",
				"w                 www....www      w",
				"w                                 w",
				"w                                 w",
				"w w.....ww    ww.....             w",
				"w ...  ...    w ... w             w",
				"w w...  ..    ...   w             w",
				"w ..>.. ....  .... ..             w",
				"w ..ww...w    ww...ww             w",
				"w                                 w",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww", },
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"w...1.............................w",
				"S........www.ww...................w",
				"w..2.....w...<w...................w",
				"w.............w...wwww..wwww......w",
				"w........www.ww...w........w......w",
				"w.................w........w......w",
				"w......................<..........w",
				"w.................w........w......w",
				"w.................wwwww..www......w",
				"w.................................w",
				"w.................................w",
				"w.www.wwww....w...www.............w",
				"w.w......w....w.....w.............w",
				"w...................w.............w",
				"w.w..<........w.....w.............w",
				"w.ww.wwwww....wwwwwww.............E",
				"w.................................w",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",

			} };

		charMap.put( ".", "RUINS_FLOOR" );
		charMap.put( "S", "RUINS_FLOOR EXIT _BACK" );
		charMap.put( "E", "RUINS_FLOOR EXIT _NEXT" );
		charMap.put( "<", "MARBLE_STAIRSUP" );
		charMap.put( ">", "MARBLE_STAIRSDOWN" );
		charMap.put( "w", "RUINS_WALL" );
		charMap.put( "2", "RUINS_FLOOR NPC VINDELITH" );
		charMap.put( "1", "RUINS_FLOOR NPC UNIDED_CLARA" );

		unleashers = new Unleasher[ ]
		{ new Clara1( ), };
	}

	public String getDescription( )
	{
		return "Hall";
	}

	public String getMapKey( )
	{
		return "RUINS";
	}

	public String getMusicKeyMorning( )
	{
		return "";
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

}