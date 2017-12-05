package week2_prework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Hashing {

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
    
    private boolean isAnagram (String s1, String s2) {
    	if (s1.length() != s2.length()) {
    		return false;
    	}
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
        	if (map.containsKey(s1.charAt(i))) {
        		int count = map.get(s1.charAt(i)) + 1;
        	    map.put(s1.charAt(i), count);
        	} else {
        		map.put(s1.charAt(i), 1);
        	}
           
        }
        for (int j = 0; j < s2.length(); j++) {
        	if (map.containsKey(s2.charAt(j))) {
        		int count = map.get(s2.charAt(j)) - 1;
        	    map.put(s2.charAt(j), count);
        	} else {
        		return false;
        	}
        }
       
        for (Entry<Character, Integer> e : map.entrySet()) {
        	if (e.getValue() != 0) {
        		return false;
        	}
        }
        return true;
    }
    
    private class Pair {
        int index1; 
        int index2;
        
        Pair(int i, int j) {
        	index1 = i;
        	index2 = j;
        }
        Pair (int i) {
        	index1 = i;
        }
    }
   
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
    
    public static void main(String[] args) {
		Hashing h = new Hashing();
		//System.out.println("is anagra? " +h.isAnagram("catd", "catc"));
		String[] a = {"abbbaabbbabbbbabababbbbbbbaabaaabbaaababbabbabbaababbbaaabbabaabbaabbabbbbbababbbababbbbaabababba", 
				"abaaabbbabaaabbbbabaabbabaaaababbbbabbbaaaabaababbbbaaaabbbaaaabaabbaaabbaabaaabbabbaaaababbabbaa", 
				"babbabbaaabbbbabaaaabaabaabbbabaabaaabbbbbbabbabababbbabaabaabbaabaabaabbaabbbabaabbbabaaaabbbbab", 
				"bbbabaaabaaaaabaabaaaaaaabbabaaaabbababbabbabbaabbabaaabaabbbabbaabaabaabaaaabbabbabaaababbaababb", 
				"abbbbbbbbbbbbabaabbbbabababaabaabbbababbabbabaaaabaabbabbaaabbaaaabbaabbbbbaaaabaaaaababababaabab", 
				"aabbbbaaabbaabbbbabbbbbaabbababbbbababbbabaabbbbbbababaaaabbbabaabbbbabbbababbbaaabbabaaaabaaaaba", 
				"abbaaababbbabbbbabababbbababbbaaaaabbbbbbaaaabbaaabbbbbbabbabbabbaabbbbaabaabbababbbaabbbaababbaa",
				"aabaaabaaaaaabbbbaabbabaaaabbaababaaabbabbaaaaababaaabaabbbabbababaabababbaabaababbaabbabbbaaabbb"};
		String[] a1 = {"caat", "taac", "dog", "god", "acta"};
		
		int[][] result = h.anagrams(a);
		for (int i=0; i<result.length; i++) {
			System.out.print("[");
			for(int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.print("]");
		}
		//int[] ar = { -5, 1, 4, -7, 10, -7, 0, 7, 3, 0, -2, -5, -3, -6, 4, -7, -8, 0, 4, 9, 4, 1, -8, -6, -6, 0, -9, 5, 3, -9, -5, -9, 6, 3, 8, -10, 1, -2, 2, 1, -9, 2, -3, 9, 9, -10, 0, -9, -2, 7, 0, -4, -3, 1, 6, -3 };
//		int[] ar = {4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4, -7, 7, 9, -4, 4, -8};
//		int sum = -3;
//	    int[] result = h.twoSum(ar, sum);
//	    System.out.println("Indices containing numbers = " + result[0] + ", "+ result[1]);
	}
}
