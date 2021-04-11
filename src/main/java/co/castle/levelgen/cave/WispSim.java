package co.castle.levelgen.cave;

public class WispSim
{
	private static Wisp lastWisp;
	private static int[ ][ ] level;
	private static Wisp wisp1, wisp2;

	public static void run( int[ ][ ] map )
	{
		wisp1.setLevel( map );
		wisp2.setLevel( map );
		level = map;
		while ( !wisp1.getPosition( ).equals( wisp2.getPosition( ) ) )
		{
			if ( lastWisp == wisp1 )
			{
				wisp2.go( );
				lastWisp = wisp2;
			}
			else
			{
				// wisp2.go();
				wisp1.go( );
				lastWisp = wisp1;
			}
		}
	}

	public static void setWisps( Wisp w1, Wisp w2 )
	{
		wisp1 = w1;
		wisp2 = w2;
		w1.setFriend( w2 );
		w2.setFriend( w1 );
		lastWisp = w1;
	}
}
