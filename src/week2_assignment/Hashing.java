package week2_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Hashing {

	// Problem 1 : Anagrams
	// Category: KeyFormation
	
	public int[][] anagrams(final String[] A) {
		HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		
		for (int i = 0; i < A.length; i++) {
			char[] cA = A[i].toCharArray();
			Arrays.sort(cA);
			String s = new String(cA);
			ArrayList<Integer> list = map.get(s);
			if (list == null) {
				list = new ArrayList<>();
				map.put(s, list);
			}
			list.add(i+1);
		}
		
		int[][] result = new int[map.size()][];
		int j = 0;
		for (Entry<String, ArrayList<Integer>> e : map.entrySet()) {
			ArrayList<Integer> list = e.getValue();
			int[] r = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				r[i] = list.get(i);
			}
			result[j] = r; 
			j++;
        }
		return result;
    }
    
    // Problem 2 : 2 Sum
	// Category : Hash search
	
    public int[] twoSum(final int[] A, int B) { 
       HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
       int[] result = new int[2];
       result[0] = Integer.MAX_VALUE;
       result[1] = Integer.MAX_VALUE;
       
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(B - A[i])) {
                //Update Index
            	int index1 = (map.get(B - A[i]) < i ? map.get(B - A[i]) : i) + 1;
            	int index2 = (map.get(B - A[i]) > i ? map.get(B - A[i]) : i) + 1;	
            	if (index2 < result[1]) {
            		result[1] = index2;
            		result[0] = index1;
            	} else if (index2 == result[1]) {
            		if (index1 < result[0]) {
            			result[1] = index2;
            			result[0] = index1;
            		}
            	}	
            } else {
            	if (!map.containsKey(A[i])) {
            		map.put(A[i], i);
            	}
            }
        }
        
        if (result[0] == Integer.MAX_VALUE) {
        	return new int[0];
        }
        return result;
    }
    
    // Problem 3 : Longest Substring Without Repeat
    
    public int lengthOfLongestSubstring(String A) {
    	int start = 0;
    	int end = 1;
    	Map<Character, Integer> indices = new HashMap<>();
		indices.put(A.charAt(start), start);
		int maxSofar = Integer.MIN_VALUE;
    	while (end < A.length()) {
    		Integer index = indices.get(A.charAt(end));
    		if (index != null && index >= start) {
    			int max = end - start;
    			if (max > maxSofar) {
    				maxSofar = max;
    			}
    			start = index + 1;
    		}
			indices.put(A.charAt(end), end);
			end++;
    	}

		int max = end - start;
		if (max > maxSofar) {
			maxSofar = max;
		}
    	return maxSofar;
    }

    
    public HashMap<String, Integer> countWords(String input) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
       
       if (input == null || input.isEmpty()) {
         return map;
       }
       
       String[] words = input.split(" ");
       
       for (int i = 0; i < words.length; i++) {
         StringBuilder sb = new StringBuilder();
         
         char[] a = words[i].toLowerCase().toCharArray();
         for (int j = 0; j < a.length; j++) {
           if (Character.isLetter(a[j])) {
             sb.append(a[j]);
           }
         }
         String s = sb.toString();
         
         Integer count = map.get(s);
         if (count == null) {
           map.put(s, 1);
         } else {
           map.put(s, ++count);
         }
       }
       
       for (Entry<String, Integer> e : map.entrySet()) {
    	   System.out.println(e.getKey() + " -> " + e.getValue());
       }
       return map;
     }
    
    // Problem 4 : Points on the straight line
    
    public int maxPointsOnAStraightLine(List<Integer> a, List<Integer> b) {
    	if (a == null || b == null || a.size() != b.size() || a.isEmpty()) {
    		return 0;
    	} 
    	if (a.size() <= 2) {
    		return a.size();
    	}
    	int maxPoints = 0;
    	 for (int i = 0; i < a.size(); i++) {
    		 HashMap<Double, Integer> slopePointsMap = new HashMap<Double, Integer>();
    		 int infinitySlopes = 1;
    	     int repeatedPoint = 0;
 	         for (int j = 0; j < a.size(); j++) {
 	        	 if (j != i) {
 	        	   double ydiff = (b.get(j) - b.get(i));
  	               double xdiff = (a.get(j) - a.get(i));
  	               if (ydiff == 0 && xdiff == 0) {
  	            	  repeatedPoint++;
  	               }
  	               if (xdiff == 0) {
  	            	  infinitySlopes++;
  	               } else {
  	                 double slope = ydiff / xdiff;
    	               Integer numOfPoints = slopePointsMap.get(slope);
    	               if (numOfPoints == null) {
    	            	   slopePointsMap.put(slope, 2);
    	               } else {
    	            	   slopePointsMap.put(slope, numOfPoints + 1);
    	               }
    	               maxPoints = Math.max(maxPoints, slopePointsMap.get(slope) + repeatedPoint);
  	               }
  	             
 	        	 }
        maxPoints = Math.max(maxPoints, infinitySlopes); 
 	        }
    	 }
    	
    	return maxPoints;
    }
    

    
    public static void main(String[] args) {
		Hashing h = new Hashing();
		//System.out.println("is anagra? " +h.isAnagram("catd", "catc"));
//		String[] a = {"abbbaabbbabbbbabababbbbbbbaabaaabbaaababbabbabbaababbbaaabbabaabbaabbabbbbbababbbababbbbaabababba", 
//				"abaaabbbabaaabbbbabaabbabaaaababbbbabbbaaaabaababbbbaaaabbbaaaabaabbaaabbaabaaabbabbaaaababbabbaa", 
//				"babbabbaaabbbbabaaaabaabaabbbabaabaaabbbbbbabbabababbbabaabaabbaabaabaabbaabbbabaabbbabaaaabbbbab", 
//				"bbbabaaabaaaaabaabaaaaaaabbabaaaabbababbabbabbaabbabaaabaabbbabbaabaabaabaaaabbabbabaaababbaababb", 
//				"abbbbbbbbbbbbabaabbbbabababaabaabbbababbabbabaaaabaabbabbaaabbaaaabbaabbbbbaaaabaaaaababababaabab", 
//				"aabbbbaaabbaabbbbabbbbbaabbababbbbababbbabaabbbbbbababaaaabbbabaabbbbabbbababbbaaabbabaaaabaaaaba", 
//				"abbaaababbbabbbbabababbbababbbaaaaabbbbbbaaaabbaaabbbbbbabbabbabbaabbbbaabaabbababbbaabbbaababbaa",
//				"aabaaabaaaaaabbbbaabbabaaaabbaababaaabbabbaaaaababaaabaabbbabbababaabababbaabaababbaabbabbbaaabbb"};
//		String[] a1 = {"caat", "taac", "dog", "god", "acta"};
//		
//		int[][] result = h.anagrams(a);
//		for (int i=0; i<result.length; i++) {
//			System.out.print("[");
//			for(int j = 0; j < result[i].length; j++) {
//				System.out.print(result[i][j] + " ");
//			}
//			System.out.print("]");
//		}
		//int[] ar = { -5, 1, 4, -7, 10, -7, 0, 7, 3, 0, -2, -5, -3, -6, 4, -7, -8, 0, 4, 9, 4, 1, -8, -6, -6, 0, -9, 5, 3, -9, -5, -9, 6, 3, 8, -10, 1, -2, 2, 1, -9, 2, -3, 9, 9, -10, 0, -9, -2, 7, 0, -4, -3, 1, 6, -3 };
//		int[] ar = {4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4, -7, 7, 9, -4, 4, -8};
//		int sum = -3;
//	    int[] result = h.twoSum(ar, sum);
//	    System.out.println("Indices containing numbers = " + result[0] + ", "+ result[1]);
//		System.out.println(h.lenOfLongestSubstring("abcabcd"));
		//System.out.println(h.lenOfLongestSubstring("abcabcd"));
//		System.out.println(h.lenOfLongestSubstring("emkt3sb1lrd2WwElx258DYi3JaRWtpaCt4O8bygIY92EH1iSRrgcpq1T525OGQL582Bi6ivacJNqL3xjRfv6JQvkwBikd9va7h79mjeu4e4ReuVR2lahYRJQ03obGGfgZa2aCSBwjifEpXYXikxgmG1Ols6fXtbSw2YzcOPceahSr4pf6hA1o1sfHqwbywXLbHV1jNvwUL760WWlmeutUr9xy8Pza9EPGrlW8g9Un32Nk2MJobx4np7cvkAHtLX5EjoZOGZfxKTZPUyoMaTzaoivUAsFOZQm3Fr1bmEbCZOcm1l3y8YQFz1MawwvamF17gDhYEUAodtyHP2PlBQ5qEizPJifTsZaex3kPyyubyfMJfnFtpkkuL5lxI5qVJtzTOTGiId2ju6VX9cBm3SgQlhID7LpK7Y1BXhRMdfdyxjkExhUMmvMLyfU44sVwkPYqW6HHzy4XZhcmVdl6mTz2ph3SO0z2X2BVu7Ikl5q06AhiqhznuExSxiETwJp07wAyhIIM3EwiVj91KHGhwpS463kffPnue5XMHlb015zTaORJc3Td3u6fqI1lU4TodT49LrCpE9G2UwaBcSiXrkmWZp1LQFD8ItC2t4Zx6jX1eo2ZTOSvmXutvsMvuSQtOBcC0UikGoBUGdd7SyWZpoAXNEKChrv0JToJIGYvK79Eyz7rvWZ9fbRrwj3PCUIzeU1pFexC4mTh7mjQzOqr0KCJOJUj5izEoHuYTqect8FWtyTON7fikPnJT059XUJ0Otb8UzPtK2F8ETk95iCCpUG10PrJOdnmLs8u2lgmBJglmHdKZ15jVbOZxeK1qsdwH0b4ui5jvKgtEPrfamRmq0WOwd4z1whv70tselWoV5rAYBfxDEPgoHQ90ARJJahrJSuRUCk5ieWQ2GUtuM7LFTuaLgtqNhk3ugVyBSmmQRQaiCJG2uPlWRqaA"));
		//h.countWords("to be or! not to be");
//		Integer[] x = {-6, 5, -18, 2, 5, -2};
//		List<Integer> al = Arrays.asList(x);
//		Integer[] y = {-17, -16, -17, -4, -13, 20};
//		List<Integer> bl = Arrays.asList(y);
//		System.out.println(h.maxPointsOnAStraightLine(al, bl));;
//		String s = "He	;has.	has,not; any	 food 	at	all";
//		String[] sa = s.split("[\\s\\t\\.,:\\-;]");
//		for (String sas : sa) {
//			System.out.println(sas);
//		}
		Integer[] x = {-6, 5, -18, 2, 5, -2};
		List<Integer> al = Arrays.asList(x);
		Integer[] y = {-17, -16, -17, -4, -13, 20};
		List<Integer> bl = Arrays.asList(y);
		System.out.println(h.maxPointsOnAStraightLine(al, bl));
		// -6 -17 5 -16 -18 -17 2 -4 5 -13 -2 20
    }
}
