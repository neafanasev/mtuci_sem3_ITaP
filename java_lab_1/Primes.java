package java_lab_1;

public class Primes {
	
	public static boolean isPrime(int n) {
		int c = 2;
		// creating variable with square of n for to not calculate it
		// every time in while
		double n_sqrt = Math.sqrt(n) + 1;
		while (c < n_sqrt) {
			if (n % c == 0) {
				return false;
			}
			c++;	
		};
		
		return true;
	}
	
	public static void main(String[] args) {
		
		for (int i = 2; i <= 100; i++) {
			if (!isPrime(i)) {
				System.out.println(i);
			}
		}
		
		
	};
	
	
}
