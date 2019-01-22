package co.castle.deploy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import sz.util.FileUtil;

public class ItemLoader
{

	public static void main( String[ ] args )
	{
		try
		{
			System.out.println( "Beginning" );
			// BufferedWriter bw =
			// FileUtil.getWriter("D:/Slash/Proyectos/CastlevaniaRL/src/crl/data/Items.java");
			BufferedWriter bw = FileUtil.getWriter( "Items.java" );
			// BufferedReader br =
			// FileUtil.getReader("D:/Slash/Proyectos/CastlevaniaRL/items.csv");
			BufferedReader br = FileUtil.getReader( "items.csv" );
			bw.write( "package crl.data;\n\nimport crl.item.*;\npublic class Items {\n" );

			bw.write(
					"\t\tprivate final static ItemDefinition [] defs = new ItemDefinition[]{\n" );
			String line = br.readLine( );
			line = br.readLine( );
			while ( line != null )
			{
				String[ ] tokens = line.split( ";" );
				bw.write( "\t\t\tnew ItemDefinition (" );
				for ( int i = 0; i < tokens.length; i++ )
				{
					if ( tokens[ i ].equals( "" ) )
						bw.write( "\"\"" );
					else if ( tokens[ i ].equals( "true" )
							|| tokens[ i ].equals( "false" ) || isNumber( tokens[ i ] ) )
						bw.write( tokens[ i ] );
					else
						bw.write( "\"" + tokens[ i ] + "\"" );
					if ( i < tokens.length - 1 )
						bw.write( "," );
				}
				bw.write( ")" );
				line = br.readLine( );
				if ( line != null )
					bw.write( ",\n" );
				else
					bw.write( "\n" );
			}
			bw.write( "\t};\n" );
			bw.write( "\tpublic static ItemDefinition[] getItemDefinitions() {\n" );
			bw.write( "\t\treturn defs;\n}\n}\n" );
			bw.close( );
			br.close( );
			System.out.println( "Finish" );
		}
		catch ( IOException ioe )
		{
			System.out.println( "Parsing process failed " + ioe.getMessage( ) );
		}
	}

	private static boolean isNumber( String x )
	{
		try
		{
			Integer.parseInt( x );
		}
		catch ( NumberFormatException nfe )
		{
			return false;
		}
		return true;

	}
}