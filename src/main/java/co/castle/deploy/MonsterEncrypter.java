package co.castle.deploy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import co.castle.system.FileLoader;
import sz.crypt.DESEncrypter;

public class MonsterEncrypter
{
	public static void main( String[ ] args )
	{
		try {
			System.out.println("Writing encrypted file");
			DESEncrypter encrypter = new DESEncrypter("65csvlk3489585f9rjh");
			encrypter.encrypt(FileLoader.getFileInputStream("data/monsters.xml"),
					new FileOutputStream("data/monsters.exml"));
			encrypter.encrypt(FileLoader.getFileInputStream("data/monsters.csv"),
					new FileOutputStream("data/monsters.ecsv"));
			System.out.println("File written");
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace( );
		}
	}
}
