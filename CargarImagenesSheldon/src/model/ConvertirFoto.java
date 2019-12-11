package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class ConvertirFoto {

	public static Image iconToImage(Icon icon) {
		
		int w = icon.getIconWidth();
		int h = icon.getIconWidth();
		
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		
		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
		
		BufferedImage bufferedImage = graphicsConfiguration.createCompatibleImage(w, h);
		
		Graphics2D graphics2d = bufferedImage.createGraphics();
		
		icon.paintIcon(null, graphics2d, 0, 0);
		
		graphics2d.dispose();
		
		return bufferedImage;
	}
	
	public static byte[] imageToByte(Image image) {
				
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = bufferedImage.getGraphics();
		
		graphics.drawImage(image, 0, 0, null);
		
		graphics.dispose();
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(bufferedImage, "JPG", stream);
		} 
		catch (IOException e) {
			System.out.println("Error al convertir imagen a Byte: " + e.getMessage());
		}
		return stream.toByteArray();
	}
	
}
