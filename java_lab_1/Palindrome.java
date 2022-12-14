package java_lab_1;

public class Palindrome {
	public static void main (String[] args) {
		for (int i=0; i < args.length; i++) {
			if (isPalindrome(args[i].toLowerCase())) {
				System.out.println(args[i]);
			}
			
		}
	}
	public static boolean isPalindrome(String s) {
		return s.equals(new StringBuilder(s) // создаем массив из строки
				.reverse()       // разворачиваем его
				.toString());     // делаем снова строкой

	}
};