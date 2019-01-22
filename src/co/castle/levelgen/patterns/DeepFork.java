package co.castle.levelgen.patterns;

public class DeepFork extends StaticPattern
{
	public DeepFork( )
	{
		cellMap = new String[ ][ ]
		{
			{	"#####S#####", "###--.--###", "##--.....##", "#--......i#", "#--c-.-c..#",
				"1---.*.-.i#", "#--c-.-c..#", "#--......i#", "##--...--##", "###--.--###",
				"###W#E#W###",

			} };

		charMap.put( "#", "CAVE_WALL" );
		charMap.put( ".", "CAVE_FLOOR" );
		charMap.put( "i", "CAVE_FLOOR FEATURE CANDLE" );
		charMap.put( "-", "CAVE_WATER" );
		charMap.put( "*", "CAVE_FLOOR FEATURE TELEPORT" );
		charMap.put( "c", "CAVE_FLOOR" );
		charMap.put( "W", "CHURCH_STAINED_WINDOW" );
		charMap.put( "S", "CAVE_FLOOR EXIT _BACK" );
		charMap.put( "E", "CAVE_FLOOR EXIT _NEXT" );
		charMap.put( "1", "CAVE_WATER EXIT RESERVOIR0" );
	}

	public String getDescription( )
	{
		return "???";
	}

	public String getMapKey( )
	{
		return null;
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