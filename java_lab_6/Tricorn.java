package java_lab_6;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
	public static final int MAX_ITERATIONS = 2000;

	@Override
	public void getInitialRange(Rectangle2D.Double range) {
		range.x = -2.0;
		range.y = -2.0;
		range.width = 4.0;
		range.height = 4.0;
	}

	@Override
	public int numIterations(double x, double y) {
		Complex z = new Complex();
		Complex c = new Complex(x, y);
		
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			z.conjugate();
			z.multiply(z);
			z.add(c);
			
			if (z.absSquared() >= 4) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public String toString() {
		return "Tricon";
	}

}