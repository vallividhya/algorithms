package week1_prework;

public class Strings {

	// https://www.interviewbit.com/problems/length-of-last-word/
	  public int lengthOfLastWord(final String A) {
	        String b = A.trim();
	        char[] letters = b.toCharArray();
	        
	        for (int i = letters.length - 1; i >= 0; i--) {
	            if (letters[i] == ' ') {
	                return letters.length - i - 1;
	            }
	        }
	        return b.length();
	  }
	  
	  // https://www.interviewbit.com/problems/palindrome-string/
	  
	  public static int isPalindrome(String A) {
		 if (A == null || A.isEmpty()) {
			 return 1;
		 }  
		 int i = 0, j = A.length() - 1;
		 char[] chars = A.toCharArray();
		 while (i < j) {
			
			 while(i <  chars.length && (chars[i] == ' ' || !Character.isLetterOrDigit(chars[i]))) {
				 i++;
			 }
			 while(j > 0 && (chars[j] == ' ' || !Character.isLetterOrDigit(chars[j]))) {
				 j--;
			 }

			 if (Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) {
				 i++;
				 j--;
			 } else {
				 return 0;
			 }
		 }
		 return 1;
	  }
	  
	  public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		System.out.println(Strings.isPalindrome(s));
		String s1 = "As man, a plan, a sdfcanal: Panama";
		System.out.println(Strings.isPalindrome(s1));
	}
}
