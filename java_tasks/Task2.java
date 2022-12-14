package java_tasks;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.math.BigDecimal;
import java.util.Scanner;

public class Task2 {
	public static void main (String[] args) {
		int task = Integer.parseInt(args[0]);
		
		if (task == 1) {
			System.out.println(repeat(args[1], Integer.parseInt(args[2])));
		} else if (task == 2) {
			int[] inp = new int[args.length-1];
			for (int i = 1; i < args.length; i++) {
				inp[i-1] = Integer.parseInt(args[i]);
			}
			System.out.println(differenceMaxMin(inp));
		} else if (task == 3) {
			int[] inp = new int[args.length-1];
			for (int i = 1; i < args.length; i++) {
				inp[i-1] = Integer.parseInt(args[i]);
			}
			System.out.println(isAvgWhole(inp));
		} else if (task == 4) {
			int[] inp = new int[args.length-1];
			for (int i = 1; i < args.length; i++) {
				inp[i-1] = Integer.parseInt(args[i]);
			}
			System.out.println(Arrays.toString(cumulativeSum(inp)));
		} else if (task == 5) {
			System.out.println(getDecimalPlaces(args[1]));
		} else if (task == 6) {
			System.out.println(Fibonacci(Integer.parseInt(args[1])));
		} else if (task == 7) {
			System.out.println(isValid(args[1]));
		} else if (task == 8) {
			System.out.println(isStrangePair(args[1], args[2]));
		} else if (task == 91) {
			System.out.println(isPrefix(args[1], args[2]));
		} else if (task == 92) {
			System.out.println(isSuffix(args[1], args[2]));
		} 	else if (task == 10) {
			System.out.println(boxSeq(Integer.parseInt(args[1])));
		}
	}
	
	// 1. every symbol in s is repeated by c times
	public static String repeat(String s, int c) {
		String a = "";
		for (char l : s.toCharArray()) {
			a += Character.toString(l).repeat(c);
		}
		return a;
	}
	
	// 2. find the difference between min and max in array of ints
	public static int differenceMaxMin(int[] arr) {
		IntSummaryStatistics stat = Arrays.stream(arr).summaryStatistics();
        return stat.getMax() - stat.getMin();
	}
	
	// 3. is average of array of ints is int
	public static boolean isAvgWhole(int[] arr) {
		return ((double) Arrays.stream(arr).sum() / arr.length) % 1 == 0;
	}
	
	// 4. create array contains sums of previous elements in inputed arr
	public static int[] cumulativeSum(int[] arr) {
		int[] newArr = new int[arr.length];
		int sumOfPrev = 0;
		for (int i = 0; i < arr.length; i++) {
			sumOfPrev += arr[i];
			newArr[i] = sumOfPrev;
		}
		return newArr;
	}
	
	// 5. get count of numbers after dot 
	public static int getDecimalPlaces(String s) {
		return new BigDecimal(s).scale();
	}
	
	// 6. F(n) = F(n-1) + F(n-2)
	public static int Fibonacci(int n) {
		if (n == 0) return 0;
		int f1 = 1;
		int f2 = 1;
		int k = 0;
		
		for (int i = 1; i < n; i++) {
			k = f2;
			f2 = f1 + f2;
			f1 = k;
		}
		
		return f1;
	}
	
	// 7. is string length is 5 and contains only digits
	public static boolean isValid(String s) {
		return s.length() == 5 && new Scanner(s).hasNextInt();
	}
	
	// 8. first letter of s1 must be last letter of s2 and vice versa
	public static boolean isStrangePair(String s1, String s2) {
		if (s1 == "" && s2 == "") return true; else if (s1 == "" || s2 == "") return false;
		
		return s1.charAt(0) == s2.charAt(s2.length() - 1) && s2.charAt(0) == s1.charAt(s1.length() -1);
	}
	
	// 9.1. is word stars with prefix
	public static boolean isPrefix(String word, String prefix) {		
		return word.startsWith(prefix.substring(0, prefix.length()));
	}
	
	// 9.2. is word ends with suffix
	public static boolean isSuffix(String word, String suffix) {
		return word.endsWith(suffix.substring(0, suffix.length()));
	}
	
	// 10. 1st step is to add 3, 2nd step is to subtract 1
	public static int boxSeq(int n) {
		if (n == 0) return 0; else return 3*(n / 2 + n % 2) - (n / 2);
	}
}
