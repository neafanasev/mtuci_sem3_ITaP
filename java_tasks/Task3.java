package java_tasks;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Task3 {
	public static void main (String[] args) {
		int task = Integer.parseInt(args[0]);
		
		if (task == 1) {
			System.out.println(solutions(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		} else if (task == 2) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length - 1; i++) {
				str.append(args[i+1]+" ");
			}
			
			System.out.println(findZip(str.toString()));
		} else if (task == 3) {
			System.out.println(checkPerfect(Integer.parseInt(args[1])));
		} else if (task == 4) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length - 1; i++) {
				str.append(args[i+1]+" ");
			}
			String s = str.toString().substring(0, str.length() - 1);
			System.out.println(flipEndChars(s));
		} else if (task == 5) {
			System.out.println(isValidHexCode(args[1]));
		} else if (task == 6) {
			int indNext = Arrays.asList(args).indexOf("next");
			int[] inp1 = new int[indNext - 1];
			int[] inp2 = new int[args.length - indNext];
			inp1 = Arrays.stream(Arrays.copyOfRange(args, 1, indNext)).mapToInt(Integer::parseInt).toArray();
			inp2 = Arrays.stream(Arrays.copyOfRange(args, indNext + 1, args.length)).mapToInt(Integer::parseInt).toArray();
			System.out.println(same(inp1, inp2));
		} else if (task == 7) {
			System.out.println(isKaprekar(Integer.parseInt(args[1])));
		} else if (task == 8) {
			System.out.println(longestZero(args[1]));
		} else if (task == 9) {
			System.out.println(nextPrime(Integer.parseInt(args[1])));
		} 	else if (task == 10) {
			System.out.println(rightTriangle(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
		}
	}
	
	// 1. count of roots of square equation 
	public static int solutions(int a, int b, int c) {
		double D = b*b - 4*a*c;
		if (D > 0) return 2; else if (D == 0) return 1; else return 0;
	}
	
	// 2. position of second entry of 'zip'
	public static int findZip(String s) {
		if (s == null || s.length() == 0 || s.split("zip", -1).length - 1 < 2) return -1;
		return s.indexOf("zip", s.indexOf("zip")+1);
	}
	
	// 3. check perfect
	public static boolean checkPerfect(int n) {
		int s = 1;
		int k;
		for (int i = 2; i*i <= n; i++) {
			if (n % i == 0) {
				s += i;
				k = n / i;
				if (i != k) s += k;
			}
		}
		
		return s == n;
	}
	
	// 4. switch the first and the last symbol
	public static String flipEndChars(String s) {
		if (s.length() < 2) return "Incompatible.";
		if (s.charAt(0) == s.charAt(s.length()-1)) return "Two's a pair.";
		return s.substring(s.length()-1) + s.substring(1, s.length()-1) + s.substring(0, 1);
	}
	
	// 5. is hex code valid
	public static boolean isValidHexCode(String s) {
		if (s.length() != 7 || s.charAt(0) != '#') return false;
		String hex = "0123456789ABCDEFabcdef";
		for (char c: s.substring(1).toCharArray()) {
			if (hex.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}
	
	// 6. is arrays has equal count of unique elements
	public static boolean same(int[] arr1, int[] arr2) {
		Set<Integer> set1 = new HashSet<>();
		for (int i: arr1) {
			set1.add(i);
		}
		Set<Integer> set2 = new HashSet<>();
		for (int i: arr2) {
			set2.add(i);
		}
		return set1.size() == set2.size();
	}
	
	// 7. 
	public static boolean isKaprekar(int n) {
		if (n == 0 || n == 1) return false;
		String ns = Integer.toString(n*n);
		int ns1 = Integer.parseInt(ns.substring(0, ns.length()/2));
		int	ns2 = Integer.parseInt(ns.substring(ns.length()/2, ns.length()));
		return ns1 + ns2 == n;
			
	}
	
	// 8. longest sequence of 0
	public static String longestZero(String s) {
		int max = 0;
		int maxTemp = 0;
		if (s.charAt(s.length()-1) == '0') {
			maxTemp = 1;
		}

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i-1) == '0') {
				maxTemp = Math.max(maxTemp, 1);
				if (s.charAt(i) == '0') {
					maxTemp += 1;
				} else {
					max = Math.max(maxTemp, max);
					maxTemp = 1;
				}
			}
		}
		max = Math.max(max, maxTemp);
		return "0".repeat(max);
	}
	
	// 9. 
	public static boolean isPrime(int n) {
		if (n <= 1) return false;
		
		for (int i = 2; i*i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int nextPrime(int n) {
		while (!isPrime(n)) {
			n += 1;
		}
		return n;
	}

	// 10. is right triangle
	public static boolean rightTriangle(int a, int b, int c) {
		int a1 = Math.max(a, Math.max(b, c));
		int c1 = Math.min(a, Math.min(b, c));
		int b1 = a + b + c - a1 - c1;
		return b1*b1 + c1*c1 == a1*a1;
		
	}
}

