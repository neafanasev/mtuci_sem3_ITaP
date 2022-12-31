package java_tasks;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Task5 {
	// 1. 
	public int[] encrypt(String s) {
		int[] arr = s.chars().toArray();
		
		for (int i = arr.length-1; i > 0; i--) {
			arr[i] -= arr[i-1];
		}
		
		return arr;
	}
	
	public String decrypt(int[] arr) {
		String str = "";
		str += (char) arr[0];

		for (int i = 1; i < arr.length; i++) {
			arr[i] += arr[i-1];
			str += (char) arr[i];
		}
		
		return str;
	}
	
	
	@Test
	public void test1() {
		assertArrayEquals(encrypt("Hello"), new int[] { 72, 29, 7, 0, 3 });
		assertEquals(decrypt(new int[] { 72, 33, -73, 84, -12, -3, 13, -13, -68 }), "Hi there!");
		assertArrayEquals(encrypt("Sunshine"), new int[] { 83, 34, -7, 5, -11, 1, 5, -9 });
	}
	
	// 2. 
	public boolean canMove(String f, String start, String end) {
		ChessBoard from = new ChessBoard(start);
		ChessBoard to = new ChessBoard(end);
		from.offsetOf(to);

		switch (f) {
			case "Pawn": return from.x == 0 && from.y == 1;
			case "Knight": return from.x == 2 && from.y == 1 || from.x == 1 && from.y == 2;
			case "Bishop": return from.x == from.y;
			case "Rook": return from.x == 0 || from.y == 0;
			case "Queen": return from.x == 0 || from.y == 0 || from.x == from.y;
			case "King": return (from.x + from.y == 1) || (from.x == 1 && from.y == 1);
			default: return false;
		}
	}
	
	class ChessBoard {
		int x, y;
		
		public ChessBoard() {
			this.x = 1;
			this.y = 1;
		}
		
		public ChessBoard(String s) {
			this.y = Character.getNumericValue(s.charAt(1));
			switch (s.charAt(0)) {
				case 'A': this.x = 1; break;
				case 'B': this.x = 2; break;
				case 'C': this.x = 3; break;
				case 'D': this.x = 4; break;
				case 'E': this.x = 5; break;
				case 'F': this.x = 6; break;
				case 'G': this.x = 7; break;
				case 'H': this.x = 8; break;
			}
		}
		
		public void offsetOf(ChessBoard o) {
			this.x = Math.abs(this.x - o.x);
			this.y = Math.abs(this.y - o.y);
		}
	}
	
	@Test
	public void test2() {
		assertEquals(canMove("Rook", "A8", "H8"), true);
		assertEquals(canMove("Bishop", "A7", "G1"), true);
		assertEquals(canMove("Queen", "C4", "D6"), false);
	}
	
	// 3
	public boolean canComplete(String s1, String s2) {
		List<Character> c1 = new ArrayList<>();
		for (char c : s1.toCharArray()) c1.add(c);
		List<Character> c2 = new ArrayList<>();
		for (char c : s2.toCharArray()) c2.add(c);

		while (c1.size() != 0 && c2.size() != 0) {
			
			if (c1.get(0) == c2.get(0)) {
				c1.remove(0);
			}
			c2.remove(0);
		}
		
		if (c1.size() == 0) {
			return true;
		}
		return false;
	}
	
	@Test
	public void test3() {
		assertEquals(canComplete("butl", "beautiful"), true);
		assertEquals(canComplete("butlz", "beautiful"), false);
		assertEquals(canComplete("tulb", "beautiful"), false);
		assertEquals(canComplete("bbutl", "beautiful"), false);
	}
	
	// 4. 
	public int sumDigProd(int... arr) {
		int a = prodOfDigits(Arrays.stream(arr).sum());
		while (a > 9) {
			a = prodOfDigits(a);
		}
		return a;
	}
	
	public int prodOfDigits(int n) {
		if (n == 0) return 0;
		int s = 1;
		while (n != 0) {
			s *= n % 10;
			n /= 10;
		}
		return s;
	}

	@Test
	public void test4() {
		assertEquals(sumDigProd(16, 28), 6);
		assertEquals(sumDigProd(0), 0);
		assertEquals(sumDigProd(1, 2, 3, 4, 5, 6), 2);
	}
	
	// 5. 
	public String[] sameVowelGroup(String[] arr) {
		List<String> newList = new ArrayList<>();
		newList.add(arr[0]);
		Set<Character> set1 = getVowels(arr[0]);
		
		for (int i = 1; i < arr.length; i++) {
			if (set1.equals(getVowels(arr[i]))) {
				newList.add(arr[i]);
			}
		}

		return newList.toArray(new String[0]);
	}

	public Set<Character> getVowels(String s) {
		Set<Character> sc = new HashSet<>();
		
		for (char c : s.toCharArray()) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				sc.add(c);
			}
		}
		
		return sc;
	}
	
	

	@Test
	public void test5() {
		assertArrayEquals(sameVowelGroup(new String[] { "toe", "ocelot", "maniac" }), new String[] { "toe", "ocelot" });
		assertArrayEquals(sameVowelGroup(new String[] { "many", "carriage", "emit", "apricot", "animal" }), new String[] { "many" });
		assertArrayEquals(sameVowelGroup(new String[] { "hoops", "chuff", "bot", "bottom" }), new String[] { "hoops", "bot", "bottom" });
	}
	
	// 6. 
	public boolean validateCard(String n) {
		if (n.length() < 14 || n.length() > 19) return false;
		
		List<Integer> nArr = new ArrayList<>();
		for (char c : n.toCharArray()) nArr.add(Character.getNumericValue(c));
		
		Collections.reverse(nArr);
		int controlD = nArr.remove(0);		
		int s = 0;
		
		for (int i = 0; i < nArr.size(); i++) {
			int newD = nArr.get(i);
			newD = (i % 2 == 0) ? (newD*2 < 9 ? newD*2 : newD*2 % 10 + newD*2 / 10) : newD;
					
			s += newD;
			nArr.set(i, newD);
		}
				
		return controlD == 10 - s % 10;
		
	}

	@Test
	public void test6() {
		assertEquals(validateCard("1234567890123456"), false);
		assertEquals(validateCard("1234567890123452"), true);
	}
	
	// 7. 
	String digits[] = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	String dozens[] = new String[] { "twenty ", "thirty ", "fourty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};

	public String numToEng(int n) {
		if (n == 0) return "zero";
		
		String s = "";
		int n1 = n / 100;
		int n2 = n - n1*100;
		int n3 = n % 10;
		
		s = n1 != 0 ? digits[n1-1] + " hundred " : s;
		s = s + (n2 < 20 ? digits[n2 - 1] : (dozens[n2/10-2] + (n3 != 0 ? digits[n3-1] : "")));
		s = (s.charAt(s.length()-1) == ' ') ? s.substring(0, s.length()-1) : s;
		
		return s;
	}

	@Test
	public void test7() {
		assertEquals(numToEng(0), "zero");
		assertEquals(numToEng(18), "eighteen");
		assertEquals(numToEng(126), "one hundred twenty six");
		assertEquals(numToEng(909), "nine hundred nine");
	}

	// 8. 
	public String getSha256Hash(String s) {
		return DigestUtils.shaHex(s);
	}

	@Test
	public void test8() {
		assertEquals(getSha256Hash("password123"), "cbfdac6008f9cab4083784cbd1874f76618d2a97");
		assertEquals(getSha256Hash("Fluffy@home"), "c53a35532cb2c3977866197bddc99b41606051a6");
		assertEquals(getSha256Hash("Hey dude!"), "fc1efb2e65b3bab888ddbd2076fbe5f52238b8a4");
	}

	
	// 9. 
	public String correctTitle(String s) {
		s = s.toLowerCase();
		String[] sArr= s.split(" ");
		String newStr = "";
		
		for (String sn : sArr) {
			String newW = "";
			
			if (!(sn.equals("in") || sn.equals("the") || sn.equals("of") || sn.equals("and"))) {
				newW = Character.toUpperCase(sn.charAt(0)) + sn.substring(1, sn.length()); 
			} else {
				newW = sn;
			}
			
			newStr += newW + " ";
		}
				
		newStr = (newStr.charAt(newStr.length()-1) == ' ') ? newStr.substring(0, newStr.length()-1) : newStr;
		
		return newStr;
	}

	@Test
	public void test9() {
		assertEquals(correctTitle("jON SnoW, kINg IN thE noRth."), "Jon Snow, King in the North.");
		assertEquals(correctTitle("sansa stark, lady of winterfell."), "Sansa Stark, Lady of Winterfell.");
		assertEquals(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."), "Tyrion Lannister, Hand of the Queen.");
	}


	// 10. 
	public String hexLattice(int k) {
		double nd = (3 + Math.sqrt(12*k - 3))/6.0;
		String s = "";
		if (nd - (int) nd != 0) {
			return "Invalid";
		} else {
			int n = (int) Math.ceil(nd);
			int c = (n*2-1)/2;

			// Строчки до средней включая её
			for (int i = 0; i < c + 1; i++) {
				s = s + latticeRow(n, i) + "\n";
			}

			// Строчки после средней
			for (int i = c - 1; i >= 0; i--) {
				s = s + latticeRow(n, i) + "\n";
			}

			String resS = s.substring(0, s.length() - 1);
			return resS;
		}

	}

	public String latticeRow(int n, int i) {
		String ts = " ".repeat(n-i);
		for (int j = 0; j < n + i; j++) ts += "o ";
		ts += " ".repeat(n-i-1);
		return ts;
	}
	
	@Test
	public void test10() {
		assertEquals(hexLattice(1), " o ");
		assertEquals(hexLattice(7), "  o o  \n o o o \n  o o  ");
		assertEquals(hexLattice(19), "   o o o   \n  o o o o  \n o o o o o \n  o o o o  \n   o o o   ");
		assertEquals(hexLattice(21), "Invalid");
	}
	
	
}


