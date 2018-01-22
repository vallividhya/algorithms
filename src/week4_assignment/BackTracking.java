package week4_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BackTracking {

	// Problem 1
	
	public static int[][] permute(int[] A) {
	       StringBuilder sb = new StringBuilder();
	       for (Integer j : A) {
	           sb.append(j);
	       }
	       String s = sb.toString();
	       List<Integer[]> resultList = new ArrayList<>();
	       permuteHelper("", s, resultList);
	       int[][] r = new int[resultList.size()][];
	       for (int k = 0; k < resultList.size(); k++) {
	           Integer[] sub = resultList.get(k);
	           int[] subArray = new int[sub.length];
	           for(int m = 0; m < subArray.length; m++) {
	        	   subArray[m] = sub[m].intValue();
	           }
	           r[k] = subArray;
	       }
	       return r;
	    }
	    
	    static void permuteHelper(String ans, String remaining, List<Integer[]> result) {
	        int n = remaining.length();
	        if (n == 0) {
	        	//System.out.println(ans);
	            Integer[] subRes = new Integer[ans.length()];
	            for (int j = 0; j < ans.length(); j++) {
	                subRes[j] = Character.getNumericValue(ans.charAt(j));
	            }  
	            result.add(subRes);
	            return;
	        } else {
	            for (int i = 0; i < remaining.length(); i++) {
	                permuteHelper(ans + remaining.charAt(i), 
	                    remaining.substring(0, i) + remaining.substring(i+1, n), result);
	                
	            }
	        }
	    }

	    public static int[][] subSet(int[] A) {
	    	Arrays.sort(A);
	    	List<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
	    	List<Integer> inputList = new ArrayList<Integer>();
	    	for (int i = 0; i < A.length; i++) {
	    		inputList.add(A[i]);
	    	}

	    	resultList.add(new ArrayList<Integer>());
	    	helper(inputList, resultList, 0);
	    	//System.out.println(resultList.size());
	    	int[][] r = new int[resultList.size()][];
	    	for (int m = 0; m < r.length; m++) {
	    		List<Integer> l = resultList.get(m);
	    		int[] subA = new int[l.size()];
	    		for (int j = 0; j < l.size(); j++) {
	    			subA[j] = l.get(j);
	    		}
	    		r[m] = subA;
	    	}
	    	return r;
	    }

	    private static void helper(List<Integer> inputList, List<ArrayList<Integer>> resultList, int currentIndex) {
			if (currentIndex == inputList.size()) {
				return;
			}
			int size = resultList.size();
			ArrayList<Integer> newList;
			for (int i = 0; i < size ; i++) {
				newList = cloneList(resultList.get(i));
				newList.add(inputList.get(currentIndex));
				resultList.add(newList);
			}
			helper(inputList, resultList, currentIndex+1);
		}

		private static ArrayList<Integer> cloneList(ArrayList<Integer> arrayList) {
			ArrayList<Integer> list = new ArrayList<>();
			list.addAll(arrayList);
			return list;
		}

//	    public static int[][] subSet(int[] A) {
//	    	Arrays.sort(A);
//	    	StringBuilder sb = new StringBuilder();
//		       for (Integer j : A) {
//		           sb.append(j);
//		       }
//		       String s = sb.toString();
//		   
//		       List<String> sList = new ArrayList<>();
//		       sHelper("", s, sList);
//		       Collections.sort(sList);
//		       int[][] r = new int[sList.size()][];
//		       for (int k = 0; k < sList.size(); k++) {
//		           String subSt = sList.get(k);
//		           int[] subRes = new int[subSt.length()];
//		            for (int j = 0; j < subSt.length(); j++) {
//		                subRes[j] = Character.getNumericValue(subSt.charAt(j));
//		            }  
//		           r[k] = subRes;
//		       }
//		       return r;       
//		}
//	    
//	    public static void sHelper(String ans, String remaining, List<String> strList) {
//	        int n = remaining.length();
//	        if (n == 0) { 
//	            strList.add(ans);
//	      
//	            return;
//	        } else {
//	            sHelper(ans + remaining.charAt(0), 
//	                    remaining.substring(1), strList);
//	            sHelper(ans, 
//		                    remaining.substring(1), strList);
//	        }
//	    }
    
    
	public static int[][] findPermutation(int[] a) {
		  StringBuilder sb = new StringBuilder();
		  for (int j = 0; j < a.length; j++) {
		    sb.append(a[j]);
		  }
		  String input = sb.toString();
		  
		  List<String> resultStrings = new ArrayList<String>();
		  permutationHelper("", input, resultStrings);
		  int[][] resultArray = new int[resultStrings.size()][];
		  
		  for (int k = 0; k < resultStrings.size(); k++) {
		    String m = resultStrings.get(k);
		    int[] subArray = new int[m.length()];
		    for (int l = 0; l < m.length(); l++) {
		      int intv = Character.getNumericValue(m.charAt(l));
		      subArray[l] = intv;
		    }
		    resultArray[k] = subArray;
		  }
		  return resultArray;
		}

		public static void permutationHelper(String result, String input, List<String> resultStrings) {
		  if (input.length() == 0) {
		    resultStrings.add(result);
		    //return;
		  } else {
		    for (int i = 0; i < input.length(); i++) {
		      permutationHelper(result + input.charAt(i), 
		                        input.substring(0, i) + input.substring(i+1, input.length()),
		                        resultStrings);
		    }
		    
		  }
		}

		// Problem 2
		
		public static void combine (int n, int k) {
			List<Integer> inputList = new ArrayList<>();
			for (int i = 1 ; i <= n; i++ ) {
				inputList.add(i);
			} 
			List<Integer> chosen = new ArrayList<>();
			List<int[]> resultList = new ArrayList<>();
			combinationHelper(chosen, inputList, resultList, k, 0);
			for (int j = 0; j < resultList.size(); j++) {
				System.out.print("{");
				int[] subA = resultList.get(j);
				for (int k1 = 0; k1 < subA.length; k1++) {
					System.out.print(subA[k1] + " ");
				}
				System.out.print("}");
			}
			System.out.println("");


		}

		public static void combinationHelper(List<Integer> chosen, List<Integer> input, List<int[]> resultList,
				int k, int start) {
			if (chosen.size() == k) {
				int[] subA = new int[k];
				for (int j = 0; j < k; j++) {
					subA[j] = chosen.get(j);
				}
				resultList.add(subA);
				return; 
			} else {
				for (int i = start; i < input.size() ; i++) {
					// Choose
					chosen.add(input.get(i));
					//input.remove(i);
					// Explore
					combinationHelper(chosen, input, resultList, k, i + 1);
	
					// Unchoose
	
					//input.add(i, chosen.get(chosen.size() - 1));
					chosen.remove(chosen.size() - 1);	

				}


			}
		}

//		// n choose k
//		
//		public static long nChooseK (int n, int k) {
//			if (n == k || k == 0) {
//				return 1;
//			} else if (k > n) {
//				return 0;
//			} else {
//				return nChooseK (n - 1, k - 1) + nChooseK(n - 1, k);
//			}
//		}

		// Problem 3
		
	public static void letterPhoneHelper(String[] input, String soFar, int index, List<String> result) {
		if (index == input.length) {
			result.add(soFar);
			//System.out.println(soFar);
		} else {
			String s = input[index];
			for (int i = 0; i < s.length(); i++) {
				letterPhoneHelper(input, soFar + s.charAt(i), index + 1, result);
			}
		}
	}

	// Problem 4
	public static String[] generateParenthesis(int n) {
        List<String> sL = new ArrayList<String>();
		generateParantheses(n,"", sL, n, n, 0);
		String[] sArray = new String[sL.size()];
		sL.toArray(sArray);
		return sArray;
    }
	
	public static String[] letterPhone(String digitString) {
		if (digitString == null) {
			return null;
		}
		if (digitString.isEmpty()) {
			return new String[]{};
		}
		
		String[] array = new String[digitString.length()];
		Map<Character, String> digitMap = new HashMap<Character, String>();
		digitMap.put('0', "0");
		digitMap.put('1', "1");
		digitMap.put('2', "abc");
		digitMap.put('3', "def");
		digitMap.put('4', "ghi");
		digitMap.put('5', "jkl");
		digitMap.put('6', "mno");
		digitMap.put('7', "pqrs");
		digitMap.put('8',"tuv");
		digitMap.put('9', "wxyz");
		for (int c = 0; c < digitString.length(); c++) {
			String letters = digitMap.get(digitString.charAt(c));
			array[c] = letters;
		} 
		List<String> result = new ArrayList<>();
		letterPhoneHelper(array, "", 0, result);	
		String[] resultStrings = new String[result.size()];
		resultStrings = result.toArray(resultStrings);
		return resultStrings;
	}
	
	public static void generateParantheses(int n, String soFar, List<String> strings, int open, int close, int flag) {
		if (soFar.length() == 2 * n) {
			strings.add(soFar);
			return;
		} 
		if (open > 0 && flag >= 0) {
			generateParantheses(n, soFar + "(", strings, open - 1, close, flag + 1);
		} 
		if (close > 0 && flag >= 0) {
			generateParantheses(n, soFar + ")", strings, open , close - 1, flag - 1);
		} 
	
		
	}
	
	public static void main(String[] args) {
//		int[] a = {12, 13};
//		int[][] r = subSet(a);
//		for (int i = 0; i < r.length; i++) {
//			System.out.print("[");
//			int[] m = r[i];
//			if (m != null && m.length > 0) {
//				for (int j = 0; j < m.length; j++) {
//					System.out.print(m[j] + " ");
//				}
//			}
//			
//			System.out.print("]");
//			System.out.println();
		//}

//		
//		int[] a = {1, 2, 3};
//	    int[][] re = findPermutation(a);
//	    for (int i = 0; i < re.length; i++) {
//	      System.out.print("{");
//	      int[] subA = re[i];
//	      for (int j = 0; j < subA.length; j++) {
//	        System.out.print(subA[j] + " ");
//	      }
//	      System.out.print("}");
//	    }
//	    System.out.println("");
		//combine(4, 2);
		
//		
//		String[] m = letterPhone("10");
//		for (String s : m) {F;GH                    ¶¶∑ŒASJK
//			System.out.println(s);  
//		}
		List<String> sL = new ArrayList<String>();
		generateParenthesis(4);
		System.out.println("total = " + sL.size());
		for (String s : sL) {
			System.out.println(s);
		}
	}
	
}

