package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.dracula.Dracul2;
import co.castle.cuts.dracula.Dracula0;
import co.castle.cuts.dracula.Dracula1;
import sz.util.Position;

public class Keep extends StaticPattern
{
	public Keep( )
	{
		cellMap = new String[ ][ ]
		{
			{	"            wwwwwwwwwoooowwwwwwwwwoooowwwww                                                             ",
				"         wwww.........=========...........wwww                                                          ",
				"   wwwwwww..........======{======............wwwww                                                      ",
				"  ww......s.........======E======.........s......ww                                                     ",
				" ww.................=s=========s=..................w                                                    ",
				" o..................=============................w.|                                                    ",
				" o..................=============................w.|                                                    ",
				" w....................=========..................ww|                                                    ",
				"ww.....................=======...................www                                                    ",
				"w......................=======....................wwwwwwooowwwwwooowwwwwooowwwwwwww                     ",
				"o....i...i...i...i....=========....i...i...i......w...............................www                   ",
				"o=================================================w.....i........i........i.......www                   ",
				"w=================================================================================wxxxxxxxxxxxxxxxxxxxxw",
				"w==================================================================================xxxxxxxxxxxxxxxxxxxxS",
				"w=================================================================================wxxxxxxxxxxxxxxxxxxxxw",
				"o=================================================w.....i........i........i.......www                   ",
				"o....i...i...i...i.................i...i...i......w...............................www                   ",
				"w.................................................wwwwwwooowwwwwooowwwwwooowwwwwwww                     ",
				"ww........s.......s.......s.......s.......s......www                                                    ",
				" w...............................................ww|                                                    ",
				" o...............................................w.|                                                    ",
				" o...............................................w.|                                                    ",
				" ww...................i...............i............w                                                    ",
				"  ww......s.......s.......s.......s.......s......ww                                                     ",
				"   wwwwwww...................................wwwww                                                      ",
				"         wwww.............................wwww                                                          ",
				"            wwwwwwwwwoooowwwwwwwwwoooowwwww                                                             ", } };

		charMap.put( ".", "KEEP_FLOOR" );
		charMap.put( "w", "KEEP_WALL" );
		charMap.put( "=", "KEEP_CARPET" );
		charMap.put( "E", "DRACULA_THRONE2 EXIT #DRACPOS" );
		charMap.put( "i", "KEEP_FLOOR FEATURE CANDLE" );
		charMap.put( "S", "KEEP_FLOOR EXIT _START" );
		charMap.put( "s", "GODNESS_STATUE" );
		charMap.put( "o", "BARRED_WINDOW" );
		charMap.put( "{", "DRACULA_THRONE1" );
		charMap.put( "[", "DRACULA_THRONE2" );
		charMap.put( "|", "BALCONY" );
		charMap.put( "x", "STONE_STAIRWAY" );
		charMap.put( "c", "CLOCK_TOWER" );

		unleashers = new Unleasher[ ]
		{ new Dracula0( ), new Dracula1( ), new Dracul2( ) };
	}

	public String getBoss( )
	{
		return "DRACULA";
	}

	public Position getBossPosition( )
	{
		return new Position( 17, 13 );
	}

	public String getDescription( )
	{
		return "Castle Keep";
	}

	public String getMapKey( )
	{
		return "KEEP";
	}

	public String getMusicKeyMorning( )
	{
		return "KEEP"; /* Turns to DRACULA when the battle starts */
	}

	public String getMusicKeyNoon( )
	{
		return null;
	}

}