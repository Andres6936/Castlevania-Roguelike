package sz.webtools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import sz.util.FileUtil;

public class Converter
{
	public static void main( String[ ] args )
	{
		File[ ] files = new File( args[ 0 ] ).listFiles( );

		for ( int i = 0; i < files.length; i++ )
		{
			try
			{
				if ( files[ i ].isDirectory( ) )
					continue;
				BufferedReader r = FileUtil.getReader( files[ i ].getAbsolutePath( ) );
				BufferedWriter w = FileUtil.getWriter( files[ i ].getParent( ) + "/html/"
						+ files[ i ].getName( ) + ".html" );
				w.write( "<table width = \"80%\">" );
				w.newLine( );
				w.write( "<tr><th width = \"70%\">Game</th><th width = \"30%\">Author</th></tr>" );
				w.newLine( );
				String line = r.readLine( );
				while ( line != null )
				{
					System.out.println( line );
					String[ ] tokens = line.split( "," );
					w.write( "<tr>" );
					w.newLine( );
					if ( tokens[ 1 ].trim( ).equals( "LOST" ) )
					{
						w.write( "<td nowrap>" + tokens[ 0 ].trim( ) + "</td>" );
						w.newLine( );
					}
					else
					{
						w.write( "<td nowrap><a href=\"" + tokens[ 1 ].trim( ) + "\">"
								+ tokens[ 0 ].trim( ) + "</a></td>" );
						w.newLine( );
					}
					w.write( "<td nowrap>" + tokens[ 2 ].trim( ) + "</td>" );
					w.newLine( );
					w.write( "</tr>" );
					w.newLine( );
					line = r.readLine( );
				}
				w.write( "</table>" );
				w.newLine( );
				w.close( );
				r.close( );
			}

			catch ( IOException ieo )
			{
				ieo.printStackTrace( );
			}

		}
	}
}
