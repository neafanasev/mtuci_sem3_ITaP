package java_lab_4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;


public class FractalExplorer {
	private static int wh;
	private static JImageDisplay display;
	private static FractalGenerator generator;
	private static Rectangle2D.Double range;
	
	
	FractalExplorer (int wh, FractalGenerator generator) {
		this.wh = wh;
		this.generator = generator;
		range = new Rectangle2D.Double();
		generator.getInitialRange(range);
	}
	
	private void createAndShowGUI() {
		
		class ImageMouseListener implements MouseListener {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, wh, e.getX());
				double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, wh, e.getY());
				generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
				drawFractal();
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		}

		class ResetListener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generator.getInitialRange(range);
				drawFractal();
			}

		}
		
		// creating graphic interface
		JFrame frame = new JFrame("FractalExplorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		display = new JImageDisplay(wh, wh);
		display.addMouseListener(new ImageMouseListener());
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetListener());
		
		
		// adding window's content
		contentPane.add(display, BorderLayout.CENTER);
		contentPane.add(resetButton, BorderLayout.SOUTH);
		
		
		// setting correct configuration for showing content
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	private void drawFractal() {
		double xCoord, yCoord;
		int numIters, rgbColor;
		float hue;
		
		for (int x = 0; x < wh; ++x) {
			xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, wh, x);
			
			for (int y = 0; y < wh; ++y) {
				yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, wh, y);
				
				numIters = generator.numIterations(xCoord, yCoord);
				
				if (numIters == -1) {
					rgbColor = 0;
				} else {
					hue = 0.7f + (float) numIters / 200f;
					rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
				}
				
				display.drawPixel(x, y, rgbColor);
			}
		}
		
		display.repaint();
	}
	
	
	public static void main(String[] args) {
		FractalExplorer fractalExplorer = new FractalExplorer(800, new Mandelbrot());
		fractalExplorer.createAndShowGUI();
		fractalExplorer.drawFractal();
	}
}
