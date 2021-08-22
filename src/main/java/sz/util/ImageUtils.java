package sz.util;

import co.castle.system.FileLoader;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtils
{
	public static BufferedImage crearImagen(	BufferedImage tempImage, int x, int y,
												int width, int height ) throws Exception
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment( );
		GraphicsConfiguration gc = ge.getDefaultScreenDevice( )
				.getDefaultConfiguration( );

		int transparency = tempImage.getColorModel( ).getTransparency( );

		BufferedImage ret = gc.createCompatibleImage( width, height, transparency );
		Graphics2D retGC = ret.createGraphics( );

		retGC.drawImage( tempImage, 0, 0, width, height, x, y, x + width, y + height,
				null );
		retGC.dispose( );
		return ret;
	}

	public static Image crearImagen( String filename, Component tracker ) throws Exception
	{
		if ( !FileUtil.fileExists( filename ) )
		{
			Exception e = new Exception( "Archivo Inexistente " + filename );
			Debug.exitExceptionally( e );
			throw e;
		}
		Image image = Toolkit.getDefaultToolkit( ).getImage( filename );
		MediaTracker mediaTracker = new MediaTracker( tracker );
		mediaTracker.addImage( image, 0 );
		try
		{
			mediaTracker.waitForID( 0 );
		}
		catch ( InterruptedException ex )
		{
			System.out.println( ex.getMessage( ) );
		}
		return image;
	}

	public static BufferedImage crearImagen(	String filename, int x, int y, int width,
												int height ) throws Exception
	{
		BufferedImage tempImage = createImage( filename );
		return crearImagen( tempImage, x, y, width, height );
	}

	public static BufferedImage createImage( String filename ) throws Exception {
		return ImageIO.read(FileLoader.getInputStream(filename));
	}

	public static BufferedImage hFlip( BufferedImage image )
	{
		AffineTransform tx = AffineTransform.getScaleInstance( 1, -1 );
		tx.translate( 0, -image.getHeight( null ) );
		AffineTransformOp op = new AffineTransformOp( tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR );
		return op.filter( image, null );
	}

	public static BufferedImage rotate( BufferedImage bufferedImage, double radians )
	{
		AffineTransform tx = new AffineTransform( );
		tx.rotate( radians, bufferedImage.getWidth( ) / 2,
				bufferedImage.getHeight( ) / 2 );
		AffineTransformOp op = new AffineTransformOp( tx,
				AffineTransformOp.TYPE_BILINEAR );
		return op.filter( bufferedImage, null );
	}

	public static BufferedImage vFlip( BufferedImage image )
	{
		AffineTransform tx = AffineTransform.getScaleInstance( -1, 1 );
		tx.translate( -image.getWidth( null ), 0 );
		AffineTransformOp op = new AffineTransformOp( tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR );
		return op.filter( image, null );
	}
}
