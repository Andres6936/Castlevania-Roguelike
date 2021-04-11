package sz.util;

import co.castle.system.FileLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertyFilters
{
	public static Color getColor( String rgba )
	{
		String[ ] components = rgba.split( "," );
		if ( components.length == 4 )
			return new Color( inte( components[ 0 ] ), inte( components[ 1 ] ),
					inte( components[ 2 ] ), inte( components[ 3 ] ) );
		else
			return new Color( inte( components[ 0 ] ), inte( components[ 1 ] ),
					inte( components[ 2 ] ) );
	}

	public static double getDouble( String n )
	{
		return Double.parseDouble( n );
	}

	public static Font getFont( String fontName, String size )	throws FontFormatException,
																FileNotFoundException,
																IOException
	{
		return Font
				.createFont(Font.TRUETYPE_FONT,
						FileLoader.getFileInputStream(new File(fontName)))
				.deriveFont( Font.PLAIN, inte( size ) );
	}

	public static BufferedImage getImage(	String fileName,
											String bounds ) throws Exception
	{
		Rectangle r = getRectangle( bounds );
		return ImageUtils.crearImagen( fileName, r.x, r.y, r.width, r.height );
	}

	public static Position getPosition( String coord )
	{
		String[ ] components = coord.split( "," );
		return new Position( inte( components[ 0 ] ), inte( components[ 1 ] ) );
	}

	public static Rectangle getRectangle( String bounds )
	{
		String[ ] components = bounds.split( "," );
		return new Rectangle( inte( components[ 0 ] ), inte( components[ 1 ] ),
				inte( components[ 2 ] ), inte( components[ 3 ] ) );
	}

	public static int inte( String n )
	{
		return Integer.parseInt( n );
	}
}
