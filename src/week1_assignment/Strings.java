package week1_assignment;

import java.util.HashMap;
import java.util.Map;

public class Strings {

	// Problem 1: https://www.interviewbit.com/problems/reverse-the-string/
	// Category bucket 1 - Words
	
	public String reverseWords(String a) {
	    String[] words = a.split(" ");
	    for (int i = 0; i < words.length; i++) {
	        String s = words[i].trim();
	        StringBuilder sb = new StringBuilder(s);
	        words[i] = sb.toString();
	    }
	    
	    int j = 0, k = words.length - 1;
	    
	    while (j < k) {
	        String temp = words[j];
	        words[j] = words[k];
	        words[k] = temp;
	        j++;
	        k--; 
	    }
	    
	    StringBuilder sb1 = new StringBuilder();
	    for (int m = 0; m < words.length; m++) {
	        sb1.append(words[m]);
	        sb1.append(" ");
	    }
	    return sb1.toString().trim();
	}
	
	// PRoblem 2 : https://www.interviewbit.com/problems/valid-number/
	// Category bucket 2 - String Parsing
	
    public static int isNumber(final String A) {
        String s = A.trim();
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        if (s.length() == 1 && !Character.isDigit(s.charAt(0))) {
            return 0;
        }
       // If the 1st char is not a + or - or . or a digit
        char c0 = s.charAt(0);
        
        if (!Character.isDigit(c0) && c0 != '+' && c0 != '-' && c0 != '.') {
            return 0;
        }
        
        
        boolean isDotOrEFound = false;
        
        for (int i = 1; i < s.length(); i++) {
           char c =  s.charAt(i);
         
           if (!Character.isDigit(c) && c != '.' && c != 'e' && c != '+' && c != '-') {
               return 0;
           }
           if (c == '.') {
               
                // An e or . appeared once before this. So, not valid
               if (isDotOrEFound) {
                   return 0;
               }
               // Check if there is a char after .
               if (s.length() <= i+1) {
                   return 0;
               }
               //Check if the char after . is a digit
               if (!Character.isDigit(s.charAt(i + 1))) {
                   return 0;
               } 
           } else if (c == 'e') {
               isDotOrEFound = true;
                // Check if there is a char after e
               if (s.length() <= i+1) {
                   return 0;
               }
               
               //Check if the char before e is a digit
               if (!Character.isDigit(s.charAt(i - 1))) {
                   return 0;
               } 
               
               if (s.charAt(i + 1) != '+' && s.charAt(i + 1) != '-' && !Character.isDigit(s.charAt(i + 1))) {
                   return 0;
               }
           }
        }
        return 1;
    }
    
    // Category Bucket -  String math
    // Problem: 3 - https://www.interviewbit.com/problems/roman-to-integer/
    
    public int romanToInt(String A) {
        if (A == null || A.isEmpty()) {
            return -1;
        }
        Map<Character, Integer> hashMap = new HashMap<Character, Integer>();
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);
        
        A = A.toUpperCase();
        int sum = 0;
        boolean reachedEnd = false;
        for(int i = 0; i < A.length() - 1; i++) {
            int valueAtI = hashMap.get(A.charAt(i));
            int valueAtIPlus1 = hashMap.get(A.charAt(i+1));
            if(valueAtI < valueAtIPlus1) {
                sum +=  valueAtIPlus1 - valueAtI;
                if (i == A.length() - 2) {
                    reachedEnd = true;
                }
                i++;
            } else {
                sum += valueAtI ;
            }
         }
        if (!reachedEnd) {
            sum += hashMap.get(A.charAt(A.length() - 1));
        }
        
      return sum;
    }
    
    public static void main(String[] args) {
		System.out.println(Strings.isNumber("2e10"));
		//System.out.println("Is space digit = "+ Character.isDigit(' '));
	}
	
}
