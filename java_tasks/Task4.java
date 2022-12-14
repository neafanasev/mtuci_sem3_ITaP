package java_tasks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Task4 {
	public static void main (String[] args) {
		int task = Integer.parseInt(args[0]);
		
		if (task == 1) {
			textProcessor(10, 7, "hello my name is Bessie and this is my essay");
		} else if (task == 2) {
			split("((())())(()(()()))");
			split("((()))(())()()(()())");
		} else if (task == 31) {
			toCamelCase("is_modal_open");
		} else if (task == 32) {
			toSnakeCase("getMainColor");
		} else if (task == 4) {
			List<Double> list = Arrays.asList(13.25, 15.0, 30.0, 1.5);
			overTime(list);
		} else if (task == 5) {
			BMI("205 pounds", "73 inches");
			BMI("55 kilos", "1.65 meters");
			BMI("154 pounds", "2 meters");
		} else if (task == 6) {
			System.out.println(39);
			System.out.println(bugger(39, 0));
		} else if (task == 7) {
			toStarShorthand("77777geff");
		} else if (task == 8) {
			doesRhyme("Sam I em!", "Green eggs and ham.");
		} else if (task == 9) {
			System.out.println(trouble("451999277", "41177722899"));
		} 	else if (task == 10) {
			countUniqueBooks("$AA$BBCATT$C$$B$", '$');
			countUniqueBooks("ZZABCDEF", 'Z');
			countUniqueBooks("AZYWABBCATTTA", 'A');
		}
	}
	
	// 1. 
	public static void textProcessor(int n, int k, String s) {
		System.out.println(s);
		
		List<String> strArr = new ArrayList<>(Arrays.asList(s.split(" ")));

		while (n != 0) {
			String raw = "";
			int rK = k;
			while (n != 0 && strArr.get(0).length() <= rK) {
				raw += strArr.get(0) + ' ';
				rK -= strArr.get(0).length();
				strArr.remove(0);
				n -= 1;
			}
			System.out.println(raw);
		}
	}
	
	// 2. 
	public static void split(String s) {
		System.out.println(s);
		
		ArrayList<String> arr = new ArrayList<String>();
		int c = 0;
		String t = "";
		
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				c += 1;
				t += "(";
			} else if (ch == ')') {
				c -= 1;
				t += ")";
				
				if (c == 0) {
					arr.add(t);
					t = "";
				}
			}
		}
		
		System.out.println(arr.toString());
	}
	
	// 3.1 
	public static void toCamelCase(String s) {
		System.out.println(s);
		
		ArrayList<String> strArr = new ArrayList<String>(Arrays.asList(s.split("_")));
		String newStr = strArr.get(0);
		
		for (int i = 1; i < strArr.size(); i++) {
			String word = strArr.get(i);
			newStr += String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1, word.length());
		}
		
		System.out.println(newStr);
	}
	
	// 3.2
	public static void toSnakeCase(String s) {
		System.out.println(s);
		
		String newStr = "";
		char t;
		
		for (int i = 0; i < s.length(); i++) {
			t = s.charAt(i);
			
			if (Character.isUpperCase(t)) {
				newStr += "_";
			}
			
			newStr += String.valueOf(Character.toLowerCase(t));
		}
		
		System.out.println(newStr);
	}
	
	// 4. 
	public static void overTime(List<Double> arr) {
		System.out.println(arr);
		
		double t = 0.0;
		
		if (arr.get(0) < 17) {
			if (arr.get(1) > 17) {
				t = (17 - arr.get(0)) + (arr.get(1) - 17)*arr.get(3);
			} else {
				t = arr.get(1) - arr.get(0);
			}
		} else {
			t = (arr.get(1) - arr.get(0))*arr.get(3);
		}
		
		t = Math.round(t*arr.get(2)*100)/100.0;
		
		System.out.println("$" + Double.toString(t));
	}
	
	// 5. 
	public static void BMI(String w, String h) {
		System.out.println(w + " " + h);
		
		double ind = 0.0;
		
		var strW = w.split(" ");
		double dW = Double.parseDouble(strW[0]);
		dW *= "kilos".equals(strW[1]) ? 1 : 0.453592;
		
		var strH = h.split(" ");
		double dH = Double.parseDouble(strH[0]);
		dH *= "meters".equals(strH[1]) ? 1 : 0.0254;
			
		ind = Math.round(dW/(dH*dH)*10)/10.0;
		
		if (ind < 18.5) {
			System.out.println(Double.toString(ind) + " Underweight");
		} else if (ind > 25) {
			System.out.println(Double.toString(ind) + " Overweight");
		} else {
			System.out.println(Double.toString(ind) + " Normal weight");
		}
	}
	
	// 6. 
	public static int bugger(int x, int acc) {
		int a = 1;
		
		if (x <= 9) {
			return acc;
		} else {
			String s = Integer.toString(x);
			for (char c: s.toCharArray()) {
				a *= Character.getNumericValue(c);
			}
			return bugger(a, acc+1);
		}
	}
	
	// 7. 
	public static void toStarShorthand(String s) {
		System.out.println(s);
		
		String newStr = "";
		char a = s.charAt(0);
		char x;
		int c = 1;
		
		for (int i = 1, n = s.length(); i < n; i++) {
			x = s.charAt(i);
			if (a == x)
				c++;
			else {
				newStr += a;
				
				if (c != 1) {
					newStr += "*";
					newStr += c;
				}
				
				c = 1;
				a = x;
			}
		}
		
		newStr += a;
		
		if (c != 1) {
			newStr += "*";
			newStr += c;
		}
		
		System.out.println(newStr);
	}

	
	// 8. 
	public static void doesRhyme(String s1, String s2) {
		System.out.println(s1 + " " + s2);
		StringBuilder s1r = new StringBuilder(s1);
		s1r.reverse();
		char s1e = 'a';
		
		for (char c: s1r.toString().toCharArray()) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				s1e = c;
				break;
			}
		}
		
		StringBuilder s2r = new StringBuilder(s2);
		s2r.reverse();
		char s2e = 'a';
		
		for (char c: s2r.toString().toCharArray()) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				s2e = c;
				break;
			}
		}
		
		System.out.println(s1e == s2e);
		
	}
	
	// 9. 
	public static Set<Integer> count(String n, int maxC) {
		Set<Integer> arrN = new HashSet<Integer>();
		
		char cur = n.charAt(0);
		int curC = 0;
		
		for (char c: n.toCharArray()) {
			if (c == cur) {
				curC += 1;
			} else {
				if (curC == maxC) {
					arrN.add(Character.getNumericValue(cur));
				}
				
				curC = 1;
				cur = c;
			}
		}
		
		if (curC == maxC) {
			arrN.add(Character.getNumericValue(cur));
		}
		
		return arrN;
	}
	
	public static boolean trouble(String n1, String n2) {
		System.out.println(n1 + " " + n2);
		Set<Integer> set1 = count(n1, 3);
		Set<Integer> set2 = count(n2, 2);
		
		Iterator<Integer> setI = set1.iterator();
		
		while (setI.hasNext()) {
			if (!set2.contains(setI.next())) {
				return false;
			}
		}
		return true;
	}


	// 10. 
	public static void countUniqueBooks(String s, char sep) {
		System.out.println(s);
		Set<Character> curArr = new HashSet<Character>();
		boolean open = false;
		
		for (char c: s.toCharArray()) {
			if (c == sep) {
				open = !open;
			} else {
				if (open) {
					curArr.add(c);
				}
			}
		}
		
		System.out.println(curArr.size());
	}

}


