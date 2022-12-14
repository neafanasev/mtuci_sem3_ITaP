package java_lab_5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


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

		// creating graphic interface
		JFrame frame = new JFrame("FractalExplorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		display = new JImageDisplay(wh, wh);

		JPanel choosePanel = new JPanel();
		choosePanel.add(new JLabel("Fractal:"));
		
		var chooseDropdown = new JComboBox<FractalGenerator>();
		chooseDropdown.addItem(new Mandelbrot());
		chooseDropdown.addItem(new Tricorn());
		chooseDropdown.addItem(new Ship());
		chooseDropdown.setActionCommand(Button.CHOOSE.toString());
		
		JPanel buttonsPanel = new JPanel();
		JButton resetButton = new JButton("Reset");
		resetButton.setActionCommand(Button.RESET.toString());
		JButton saveButton = new JButton("Save");
		saveButton.setActionCommand(Button.SAVE.toString());
		
		JFileChooser jFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		jFileChooser.setFileFilter(filter);
		jFileChooser.setAcceptAllFileFilterUsed(false);
		
		class ResetListener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (Button.valueOf(e.getActionCommand())) {
				case CHOOSE:
					generator = (FractalGenerator) chooseDropdown.getSelectedItem();
				case RESET:
					display.clearImage();
					display.repaint();
					generator.getInitialRange(range);
					drawFractal();
					break;
				case SAVE:
					String errMsg = null;
					
					try {
						if (jFileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
							var file = jFileChooser.getSelectedFile();
							
							if (!file.exists()) file.createNewFile();
							
							if (file.canWrite())
								ImageIO.write(display.getImage(), "png", file);
							else {
								errMsg = "Can't write to this path";
							}
						}
					} catch (Exception err) {
						errMsg = err.getMessage();
					} finally {
						if (errMsg != null)
							JOptionPane.showMessageDialog(frame, errMsg, "Can't save the image", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		}
		
		var resetListener = new ResetListener();
		
		//adding listeners
		display.addMouseListener(new ImageMouseListener());
		resetButton.addActionListener(resetListener);
		saveButton.addActionListener(resetListener);
		chooseDropdown.addActionListener(resetListener);

		
		// adding window's content
		buttonsPanel.add(saveButton);
		buttonsPanel.add(resetButton);
		choosePanel.add(chooseDropdown);
		contentPane.add(display, BorderLayout.CENTER);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		contentPane.add(choosePanel, BorderLayout.NORTH);
		
		
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

enum Button {
	RESET, CHOOSE, SAVE
}