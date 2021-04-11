package sz.util;

import co.castle.system.FileLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil
{
	public static boolean fileExists( String filename ) {
		return FileLoader.getResourceFile(filename).exists();
	}

	public static BufferedWriter getWriter( String fileName ) throws IOException
	{
		Debug.enterStaticMethod( "FileUtil", "getWriter" );
		BufferedWriter x = new BufferedWriter( new FileWriter( fileName ) );
		Debug.exitMethod( x );
		return x;
	}

}