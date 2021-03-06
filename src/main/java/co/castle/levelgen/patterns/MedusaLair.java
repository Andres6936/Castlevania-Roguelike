package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;
import sz.util.Position;

public class MedusaLair extends StaticPattern
{
	public MedusaLair( )
	{
		cellMap = new String[ ][ ]
		{
			{	",,,,,,,,,,,,,,,,,,,,,,,,,,,,###+#########+####,,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,,,##................##,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,,##..........$.......#####,,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,##..........###..........##,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,##..o........###.........o.###,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#............................###,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#..............................###,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#................................###,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,##...o.........o..........o.........###,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,##.....................................###,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,##........................................###,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,##.............c.....c.......................###+##+###",
				",,,,,,,,,,,,,,,,,,,##.......o........###.........o....................c...#",
				"####+#####+####+####.................###..................................#",
				"#......c....c....c........................................................#",
				"S.........................................................................E",
				"#......c....c....c........................................................#",
				"####+#####+####+####.................###..................................#",
				",,,,,,,,,,,,,,,,,,,##.......o........###.........o....................c...#",
				",,,,,,,,,,,,,,,,,,,,##...........................................###+##+###",
				",,,,,,,,,,,,,,,,,,,,,##............c.....c.....................###,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,##.....................................###,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,##...o.........o..........o.........###,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#................................###,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#..............................###,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,#............................###,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,##..o........###.........o.###,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,##..........###..........##,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,,##..........$.......#####,,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,,,##................##,,,,,,,,,,,,,,,,,,,,,,,,,,,,",
				",,,,,,,,,,,,,,,,,,,,,,,,,,,,###+#########+####,,,,,,,,,,,,,,,,,,,,,,,,,,,,," } };

		charMap.put( "o", "GRAY_COLUMN" );
		charMap.put( ".", "RED_FLOOR" );
		charMap.put( ",", "VOID" );
		charMap.put( "#", "RED_WALL" );
		charMap.put( "+", "SMALL_WINDOW" );
		charMap.put( "E", "RED_FLOOR EOL MAGIC_DOOR COST 1" );
		charMap.put( "S", "RED_FLOOR EXIT _BACK" );
		charMap.put( "$", "RED_FLOOR FEATURE TREASURE_SPAWNER 30" );
		charMap.put( "c", "RED_FLOOR FEATURE CANDLE 70" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{ new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.BORDER, 70 ) };
	}

	public String getBoss( )
	{
		return "MEDUSA";
	}

	public Position getBossPosition( )
	{
		return new Position( 60, 16 );
	}

	public String getDescription( )
	{
		return "Medusa Lair";
	}

	public String getMapKey( )
	{
		return "LAB";
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