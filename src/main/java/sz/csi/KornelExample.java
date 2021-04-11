package sz.csi;

import sz.csi.jcurses.JCursesConsoleInterface;

public class KornelExample
{

	public static void main( String[ ] args )
	{
		ConsoleSystemInterface csi = null;
		try
		{
			csi = new JCursesConsoleInterface( );
			// csi = new WSwingConsoleInterface(); This one is pretty old and forgotten,
			// dunnno how well it is working ;)
		}
		catch ( ExceptionInInitializerError eiie )
		{
			System.out.println( "Fatal Error Initializing JCurses" );
			eiie.printStackTrace( );
			System.exit( -1 );
		}
		csi.print( 1, 1, "Hello, Hello" );
	}

}
