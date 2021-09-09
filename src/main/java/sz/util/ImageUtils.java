package sz.util;

import co.castle.system.FileLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageUtils
{
	public static BufferedImage createImage(BufferedImage tempImage, int x, int y, int width, int height) {
		var graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		var defaultConfiguration = graphicsEnvironment.getDefaultScreenDevice().getDefaultConfiguration();
		int transparency = tempImage.getColorModel().getTransparency();
		BufferedImage compatibleImage = defaultConfiguration.createCompatibleImage(width, height, transparency);
		Graphics2D graphics = compatibleImage.createGraphics();
		graphics.drawImage(tempImage, 0, 0, width, height, x, y, x + width, y + height, null);
		graphics.dispose();
		return compatibleImage;
	}

	public static BufferedImage createImage(String filename, int x, int y, int width, int height) throws Exception {
		return createImage(ImageIO.read(FileLoader.getInputStream(filename)), x, y, width, height);
	}

	public static BufferedImage createImage(String filename) throws Exception {
		return ImageIO.read(FileLoader.getInputStream(filename));
	}

	public static BufferedImage hFlip(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -image.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter( image, null );
	}

	public static BufferedImage rotate( BufferedImage bufferedImage, double radians ) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(radians, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(bufferedImage, null);
	}

	public static BufferedImage vFlip( BufferedImage image )
	{
		AffineTransform tx = AffineTransform.getScaleInstance( -1, 1 );
		tx.translate( -image.getWidth( null ), 0 );
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter( image, null );
	}
}
