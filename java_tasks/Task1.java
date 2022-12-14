package java_tasks;

public class Task1 {
	public static void main (String[] args) {
		int task = Integer.parseInt(args[0]);
		
		if (task == 1) {
			System.out.println(remainder(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
		} else if (task == 2) {
			System.out.println(triArea(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
		} else if (task == 3) {
			System.out.println(animals(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		} else if (task == 4) {
			System.out.println(profitableGamble(Double.parseDouble(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		} else if (task == 5) {
			System.out.println(operation(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		} else if (task == 6) {
			System.out.println(ctoa(args[1].charAt(0)));
		} else if (task == 7) {
			System.out.println(addUpTo(Integer.parseInt(args[1])));
		} else if (task == 8) {
			System.out.println(nextEdge(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
		} else if (task == 9) {
			int[] a = new int[args.length-1];
			for (int i = 0; i < args.length-1; i++) {
				a[i] = Integer.parseInt(args[i+1]);
			}
			System.out.println(sumOfCubes(a));
		} else if (task == 10) {
			System.out.println(abcmath(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		}
		
	}
	//1. remainder of division
	public static int remainder (int a, int b) {return a % b;}
	//2. get triangle area by base and height
	public static double triArea (int a, int h) {return a*h/2.0;}
	//3. count legs
	public static int animals (int chickens, int cows, int pigs) {return chickens*2 + cows*4 + pigs*4;}
	//4. profitable gamble
	public static boolean profitableGamble(double prob, int prize, int pay) {return prob*prize>pay;}
	//5. what operation needed to get number
	public static String operation(int a, int b, int c) {
		if (a + b == c) {
			return "Add";
		} else if (a - b == c) {
			return "Substract";
		} else if (a * b == c) {
			return "Multiply";
		} else if (a / b == c) {
			return "Devide";
		} else {return "None";}
	}
	//6. to ASCII
	public static int ctoa(char a) {return a;}
	//7. factorial but using add
	public static int addUpTo(int a) {
		int s = 0;
		for (int i = 1; i <= a; i++) {
			s += i;
		}
		return s;
	}
	//8. find max value of 3rd side (1st + 2nd - 1)
	public static int nextEdge(int a, int b) {return a + b - 1;}
	//9. sum of cubes
	public static int sumOfCubes(int[] n) {
		int s = 0;
		for (int i = 0; i < n.length; i++) {
			s += Math.pow(n[i], 3);
		}
		return s;
	}
	//10. add A to itself B times and check if that multiple of C
	public static boolean abcmath(int a, int b, int c) {return (a*Math.pow(b, 2) % c == 0);}
	
}
