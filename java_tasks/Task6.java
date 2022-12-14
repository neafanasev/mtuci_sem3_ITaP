package java_tasks;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class Task6 {
	// 1.
	public int stirling(int n, int k) {
		if (n == 0 && k == 0) return 1;
		if (n > 0 && k == 0 || k > n) return 0;
		
		return stirling(n-1, k-1) + k*stirling(n-1, k);
	}
	
	public int bell(int n) {
		int s = 0;
		
		for (int i = 0; i <= n; i++) {
			s += stirling(n, i);
		}
		
		return s;
	}
	
	@Test
	public void test1() {
		assertEquals(bell(1), 1);
		assertEquals(bell(2), 2);
		assertEquals(bell(3), 5);
		assertEquals(bell(6), 203);
	}

	// 2.
	public String translateWord(String s) {
		if (s == "") return "";
		var newStr = new StringBuilder(s.toLowerCase());
		char tc = '&';
		while (newStr.charAt(0) != 'a' && newStr.charAt(0) != 'e' && newStr.charAt(0) != 'i' && newStr.charAt(0) != 'o' && newStr.charAt(0) != 'u') {
			tc = newStr.charAt(0);
			newStr.deleteCharAt(0);
			newStr.append(tc);
		}
		
		if (tc == '&') return newStr.append("yay").toString();
		return newStr.append("ay").toString();
	}
	
	public String translateSentence(String s) {
		String[] strArr = s.split(" ");
		String newStr = "";
		for (String n : strArr) {
			newStr = newStr + translateWord(n) + " "; 
		}
		
		newStr = (newStr.charAt(newStr.length()-1) == ' ') ? newStr.substring(0, newStr.length()-1) : newStr;
		return newStr;
	}
	
	@Test
	public void test2() {
		assertEquals(translateWord("flag"), "agflay");
		assertEquals(translateWord("Apple"), "appleyay");
		assertEquals(translateWord("button"), "uttonbay");
		assertEquals(translateWord(""), "");
		assertEquals(translateSentence("i like to eat honey waffles"), "iyay ikelay otay eatyay oneyhay afflesway");
	}
	
	// 3.
	public boolean validColor(String s) {
		if (s.charAt(0) != 'r' || s.charAt(1) != 'g' || s.charAt(2) != 'b') return false;
		String[] strArr;
		if (s.charAt(3) == 'a') {
			strArr = s.substring(5, s.length()-1).split(",");
			
			if (strArr[0] == "") return false;
			int n1 = Integer.parseInt(strArr[0]);
			
			if (strArr[1] == "") return false;
			int n2 = Integer.parseInt(strArr[1]);
			
			if (strArr[2] == "") return false;
			int n3 = Integer.parseInt(strArr[2]);
			
			if (strArr[3] == "") return false;
			double n4 = Double.parseDouble(strArr[3]);
			
			if (n1 >= 0 && n1 <= 255 && n2 >= 0 && n2 <= 255 && n3 >= 0 && n3 <= 255 && n4 >= 0 && n4 <= 1) return true;
			return false;
		} else {
			strArr = s.substring(4, s.length()-1).split(",");
			
			if (strArr[0] == "") return false;
			int n1 = Integer.parseInt(strArr[0]);
			
			if (strArr[1] == "") return false;
			int n2 = Integer.parseInt(strArr[1]);
			
			if (strArr[2] == "") return false;
			int n3 = Integer.parseInt(strArr[2]);
			
			if (n1 >= 0 && n1 <= 255 && n2 >= 0 && n2 <= 255 && n3 >= 0 && n3 <= 255) return true;
			return false;
		}
	}
	
	@Test
	public void test3() {
		assertEquals(validColor("rgb(0,0,0)"), true);
		assertEquals(validColor("rgb(0,,0)"), false);
		assertEquals(validColor("rgb(255,256,255)"), false);
		assertEquals(validColor("rgba(0,0,0,0.123456789)"), true);

	}
	
	// 4.
	
	public String stripUrlParams(String url, String[] arr) {
		if (url.contains("?")) {
			String newUrl = url.split("\\?")[0] + "?";
			String[] pArr = url.split("\\?")[1].split("\\&");
			
			Map<String, Integer> pMap = new HashMap<String, Integer>();
			for (String p : pArr) {
				String[] t = p.split("\\=");
				
				boolean pass = false;
				for (String sep: arr) {
					if (t[0].equals(sep)) pass = true;
				}
				
				if (pass == true) continue;
				pMap.put(t[0], Integer.parseInt(t[1]));
			}
			
			for(Map.Entry<String, Integer> item : pMap.entrySet()){
				newUrl += item.getKey() + "=" + item.getValue() + "&";
			}

			newUrl = (newUrl.charAt(newUrl.length()-1) == '&') ? newUrl.substring(0, newUrl.length()-1) : newUrl;
			return newUrl;
		} else return url;
	}
	
	public String stripUrlParams(String url) {
		return stripUrlParams(url, new String[] {""});
	}
	
	
	@Test
	public void test4() {
		assertEquals(stripUrlParams("https://edabit.com?a=1&b=2&a=2"), "https://edabit.com?a=2&b=2");
		assertEquals(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}), "https://edabit.com?a=2");
		assertEquals(stripUrlParams("https://edabit.com", new String[] {"b"}), "https://edabit.com");
	}
	
	// 5.
	public String getLongestWord(List<String> s) {
		String longest = "";
		
		for (String word: s) {
			if (word.length() > longest.length()) longest = word;
		}
		
		return longest;
	}
	
	public Object[] getHashTags(String s) {
		List<String> strArr= new ArrayList<String>(Arrays.asList(s.split(" ")));
		List<String> longestWords = new ArrayList<String>();
		
		String s1 = getLongestWord(strArr);
		
		for (int i = 0; i < 3; i++) {
			s1 = getLongestWord(strArr);
			longestWords.add("#" + s1.toLowerCase());
			strArr.remove(s1);
		}
		
		return longestWords.toArray();
	}
	
	@Test
	public void test5() {
		assertArrayEquals(getHashTags("How the Avocado Became the Fruit of the Global Trade"), new Object[]{"#avocado", "#became", "#global"});
		assertArrayEquals(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"), new Object[]{"#christmas", "#probably", "#will"});
		assertArrayEquals(getHashTags("Hey Parents Surprise Fruit Juice Is Not Fruit"), new Object[]{"#surprise", "#parents", "#fruit"});
	}
	
	// 6.
	public int ulam(int k) {
		TreeSet<Integer> ulamList = new TreeSet<Integer>();
		ulamList.add(1);
		ulamList.add(2);
		
		for (int i = 3; i <= 2000; i++) {
			Iterator<Integer> iter1 = ulamList.iterator();
			
			int c = 0;
			
			while (iter1.hasNext()) {
				int el1 = iter1.next();
				Iterator<Integer> iter2 = ulamList.iterator();
				
				while (iter2.hasNext()) {	
					int el2 = iter2.next();
					
					if (el1 != el2 && el1+el2 == i) {
						c += 1;
					}
				}
			}
			
			if (c == 2) ulamList.add(i);
		}
		
		Iterator<Integer> iter = ulamList.iterator();
		for (int i = 0; i < k; i++) {
			int next = iter.next();
			if (i == k-1) return(next);
		}
			
		return 1;
	}
	
	@Test
	public void test6() {
		assertEquals(ulam(4), 4);
		assertEquals(ulam(9), 16);
		assertEquals(ulam(206), 1856);
	}

//	public String longestNonrepeatingSubstring(String s) {
//		return s;
//	}
//	
//	
////	@Test
////	public void test7() {
////		assertEquals(longestNonrepeatingSubstring("abcabcbb"), "abc");
////		assertEquals(longestNonrepeatingSubstring("aaaaaa"), "a");
////		assertEquals(longestNonrepeatingSubstring("abcde"), "abcde");
////		assertEquals(longestNonrepeatingSubstring("abcda"), "abcd");
////	}
	
	// 8.
	
	public String convertToRoman(int n) {
		String s = "";
		
        int m1 = n / 1000;
        
        for (int i = 0; i < m1; i++) s += "M";
        
        int m2 = n % 1000;

                
        int c1 = m2 / 100;
        
        if (c1 >= 5) {
        	if (c1 == 9) s += "CM";
            else if (c1 >= 5) s += "D";
        } else {
        	if (c1 == 4) s += "CD";
            else if (c1 == 0) s += "";
            else for (int i = 0; i < c1; i++) s += "C";
        }

        int c2 = m2 % 100;
        
        
        int x1 = c2 / 10;
        
        if (x1 >= 5) {
        	if (x1 == 9) s += "XC";
            else if (x1 >= 5) s += "L";
        } else {
        	if (x1 == 4) s += "XL";
            else if (x1 == 0) s += "";
            else for (int i = 0; i < x1; i++) s += "X";
        }
 
        int x2 = c2 % 10;

        String[] a = {"", "I", "II", "III","IV", "V", "VI", "VII", "VIII", "IX"};
        s += a[x2];
        
        return s;
	}    
	
	@Test
	public void test8() {
		assertEquals(convertToRoman(2), "II");
		assertEquals(convertToRoman(12), "XII");
		assertEquals(convertToRoman(16), "XVI");
		assertEquals(convertToRoman(1949), "MCMXLIX");
	}
	
	// 9.
	public boolean formula(String s) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js"); 
		boolean result = false;
		try {
			result = (boolean) engine.eval(s);
		} catch (ScriptException a) {
			System.out.println("Script exception");
		}
		finally {}
		return result;
	}
	
	// 10.
	public String getDescendant(String n) {
		String newStr = "";
		
		for (int i = 0; i < n.length()/2; i++) {
			int newI = Character.getNumericValue(n.charAt(i*2)) + Character.getNumericValue(n.charAt(i*2+1)); 
			newStr += Integer.toString(newI);
		}
		
		return newStr;
	}
	
	public boolean isPalindrome(String s) {
		int length = s.length();
        for (int i = 0; i < length/2; i++) {
            if (s.charAt(i) != s.charAt(length-i-1)) {
                return false;
            }
        }
        return true;
	}
	
	public boolean palindromeDescendant(int n) {
		String s = Integer.toString(n);
		
		while (s.length() >= 2) {
			if (isPalindrome(s)) return true;
			s = getDescendant(s);
		}
		
		return false;
	}
	
	
	@Test
	public void test10() {
		assertEquals(palindromeDescendant(11211230), true);
		assertEquals(palindromeDescendant(13001120), true);
		assertEquals(palindromeDescendant(23336014), true);
		assertEquals(palindromeDescendant(11), true);
	}
	
	
}
