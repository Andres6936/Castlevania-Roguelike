package co.castle.levelgen.patterns;

import co.castle.cuts.Unleasher;
import co.castle.cuts.ingame.badBelmont.BadBelmont1;
import co.castle.cuts.ingame.badBelmont.BadBelmont2;
import co.castle.levelgen.MonsterSpawnInfo;
import sz.util.Position;

public class BadBelmontLair extends StaticPattern
{
	public BadBelmontLair( )
	{
		cellMap = new String[ ][ ]
		{
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"w.................................w",
				"w.................................w",
				"w..ww...ww...ww.....ww...ww...ww..w",
				"w..ww....w...w.......w...w....ww..w",
				"w.................................w",
				"w.................................w",
				"w.......c.....c.....c.....c.......w",
				"w.................................w",
				"S.................................E",
				"w.................................w",
				"w........c...c.......c...c........w",
				"w.................................w",
				"w.................................w",
				"w..ww...w.....w.....w.....w...ww..w",
				"w..ww...ww...ww.....ww...ww...ww..w",
				"w.................................w",
				"w.................................w",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",

			} };

		charMap.put( ".", "DUNGEON_FLOOR" );
		charMap.put( "w", "DUNGEON_WALL" );
		charMap.put( "E", "DUNGEON_FLOOR EOL MAGIC_DOOR COST 1" );
		charMap.put( "S", "DUNGEON_FLOOR EXIT _BACK" );
		charMap.put( "c", "DUNGEON_FLOOR FEATURE CANDLE 110" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "CAGNAZOO", MonsterSpawnInfo.UNDERGROUND, 50 ) };

		unleashers = new Unleasher[ ]
		{ new BadBelmont1( ), new BadBelmont2( ) };
	}

	public String getBoss( )
	{
		return "BADBELMONT";
	}

	public Position getBossPosition( )
	{
		return new Position( 6, 9 );
	}

	public String getDescription( )
	{
		return "Castle Dungeon";
	}

	public String getMapKey( )
	{
		return "DUNGEON";
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