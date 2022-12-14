package java_lab_4;

public class Complex {
	public double Re;
	public double Im;

	Complex (double Re, double Im){
		this.Re = Re;
		this.Im = Im;
	}

	Complex (){
		this(0, 0);
	}
	
	public void add(Complex Z) {
		this.Re += Z.Re;
		this.Im += Z.Im;
	}

	public void multiply(Complex Z) {
		double newRe = this.Re*Z.Re - this.Im*Z.Im;
		double newIm = this.Re*Z.Im + this.Im*Z.Re;
		
		this.Re = newRe;
		this.Im = newIm;
	}

	double absSquared() {
		return Re*Re + Im*Im;
	}
	
	public static void main(String[] args) {
		Complex c1 = new Complex(1, -1);
		Complex c2 = new Complex(3, 6);
		c1.multiply(c2);
	}
}
