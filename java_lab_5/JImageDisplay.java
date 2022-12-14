package java_lab_5;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class JImageDisplay extends JComponent {
	private BufferedImage bufferedImage;
	
	JImageDisplay (int width, int height) {
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		super.setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(this.bufferedImage, 0, 0, this.bufferedImage.getWidth(), this.bufferedImage.getHeight(), null);
	}
	
	public void clearImage() {
		this.bufferedImage.flush();		
	}
	
	public void drawPixel(int x, int y, int rgbColor) {
		this.bufferedImage.setRGB(x, y, rgbColor);
	}
	
	public BufferedImage getImage() {
		return this.bufferedImage;
	}
	
}