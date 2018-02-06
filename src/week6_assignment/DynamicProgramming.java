package week6_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DynamicProgramming {

	// Before Session 

	// Problem 1 - Min Sum Path in Triangle

	public int minimumTotal(List<List<Integer>> a) {
		if (a == null || a.isEmpty()) {
			return 0;
		} 
		List<Integer> prevRow = a.get(0);
		for (int i = 1; i < a.size(); i++) {
			List<Integer> currRow = a.get(i);
			currRow.set(0, currRow.get(0) + prevRow.get(0));
			for (int j = 1; j < currRow.size() - 1; j++) {
				currRow.set(j, currRow.get(j) + Math.min(prevRow.get(j), prevRow.get(j-1)));
			}
			currRow.set(currRow.size() - 1, currRow.get(currRow.size() - 1) + 
					prevRow.get(prevRow.size() - 1));
			prevRow = currRow;
		}
		return Collections.min(prevRow);
	}


	// Problem 2 - Stairs

	public int climbStairs(int n) {
		return findNumberOfWays(n, 2, new int[n + 1]); 
	}

	int findNumberOfWays(int totalSteps, int stepSize, int[] numOfWays) {
		if (totalSteps <= 1) {
			return 1; 
		}
		if (numOfWays[totalSteps] == 0) {
			for (int i = 1; i <= stepSize && totalSteps - i >= 0; i++) {
				numOfWays[totalSteps] += findNumberOfWays(totalSteps - i, stepSize, numOfWays);
			}    
		}

		return numOfWays[totalSteps];
	}

	// Problem 3 Longest Increasing Subsequence
	// https://www.interviewbit.com/problems/length-of-longest-subsequence/

	public int lis(final List<Integer> A) {
		Integer[] maxLength = new Integer[A.size()];
		Arrays.fill(maxLength, 1);
		for (int i = 1; i < A.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (A.get(i) > A.get(j)) {
					maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
				}
			}
		}
		return Collections.max(Arrays.asList(maxLength));
	}

	// Problem 4 - Best Time to Buy and Sell Stocks I
	// https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-i/

	public int maxProfit(final List<Integer> A) {
		int maxProfit = 0; 
		int minPrice = Integer.MAX_VALUE;

		for (Integer price : A) {
			maxProfit = Math.max(price - minPrice, maxProfit);
			minPrice = Math.min(minPrice, price);
		}
		return maxProfit;
	}
	
	// Problem 5 - Best Time to Buy and Sell Stocks III
	
	public int maxProfit(final int[] A) {
        if (A.length < 2) {
            return 0;
        }
        int maxProfit = 0, minPriceSoFar = Integer.MAX_VALUE, maxPriceSoFar = Integer.MIN_VALUE;
        int[] profit = new int[A.length];
        
        for (int i = 0; i < A.length; i++) {
            // To get the max Profit, find the min Price
            minPriceSoFar = Math.min(minPriceSoFar, A[i]);
            maxProfit = Math.max(maxProfit, A[i] - minPriceSoFar);
            profit[i] = maxProfit;
        }
        if (A.length == 2) {
            return maxProfit; 
        } else {
          for (int j = A.length - 1; j > 0; j--) {
            // To get maxPrice
            maxPriceSoFar = Math.max(maxPriceSoFar, A[j]);
            // Current profit + previous profit from the 1st transaction 
            // which should be in the prev day
            maxProfit = Math.max(maxProfit, maxPriceSoFar - A[j] + profit[j - 1]);
          }
          return maxProfit;  
        }
    }
	
	// Problem 6 - Max Sum Without Adjacent Elements
	
	public int adjacent(int[][] grid) {
      int incl = Math.max(grid[0][0], grid[1][0]);
    
      // Not including first column's element
      int excl = 0, excludeNew;
    
      // Traverse for further elements
      for (int i = 1; i < grid[0].length; i++ )
      {
          // Update max_sum on including or 
          // excluding of previous column
          excludeNew = Math.max(excl, incl);
    
          // Include current column. Add maximum element
          // from both row of current column
          incl = excl + Math.max(grid[0][i], grid[1][i]);
    
          // If current column doesn't to be included
          excl = excludeNew;
      }
    
      // Return maximum of excl and incl
      // As that will be the maximum sum
      return Math.max(excl, incl);
  }
	
	// Problem 7 - Word Break
	
	public int wordBreak(String s, ArrayList<String> wordList) {
        Map<String, Boolean> stash = new HashMap<>();
        Set<String> dict = new HashSet<String>();
        for (int i =0; i < wordList.size(); i++) {
            dict.add(wordList.get(i));
        }    
        return helper(s, dict, stash) ? 1 : 0;
        
    }
    private boolean helper(String s, Set<String> dict,  Map<String, Boolean> stash) {
        if (stash.containsKey(s)) {
            return stash.get(s);
        }
        boolean result = false;
        if (s.isEmpty() || dict.contains(s)) {
            result = true;
        } else {
            for (int i = 1; i < s.length(); i++) {
                boolean firstHalf = dict.contains(s.substring(0, i));
                boolean secondHalf = firstHalf && helper(s.substring(i), dict, stash);
                if (secondHalf) {
                    result = true;
                    break;
                }
            }
        }
        stash.put(s, result);
        return result;
    }
    
    // Problem 8 - Coins in a line 
    

    private int coinsHelper(ArrayList<Integer> coins, int startIndex, int endIndex, Map<Result, Integer> map) {
        if (startIndex < 0 || endIndex > coins.size() - 1) {
            return 0;
        } else {
            int val1 = 0, val2 = 0;
            if (startIndex + 2 < endIndex) {
                if (map.containsKey(new Result(startIndex + 2, endIndex))) {
                    val1 = map.get(new Result(startIndex + 2, endIndex));
                } else {
                    val1 = coinsHelper(coins, startIndex + 2, endIndex, map);
                    map.put(new Result(startIndex + 2, endIndex), val1);
                }
                
            }
            if (startIndex + 1 < endIndex && endIndex - 1 > startIndex) {
                if (map.containsKey(new Result(startIndex + 1, endIndex - 1))) {
                    val2 = map.get(new Result(startIndex + 1, endIndex - 1));
                } else {
                    val2 = coinsHelper(coins, startIndex + 1, endIndex - 1, map);
                    map.put(new Result(startIndex + 1, endIndex - 1), val2);
                }
                
            }
            
           
            int profitFromFront = coins.get(startIndex) + Math.min(val1, val2);
            int val4 = 0;
            if (endIndex - 2 > startIndex) {
                if (map.containsKey(new Result(startIndex, endIndex - 2))) {
                    val4 = map.get(new Result(startIndex, endIndex - 2));
                } else {
                   val4 = coinsHelper(coins, startIndex, endIndex - 2, map);
                   map.put(new Result(startIndex, endIndex - 2), val4);
                }
                 
            }
           
            int profitFromBack = coins.get(endIndex) + Math.min(val2, val4);
            return Math.max(profitFromFront, profitFromBack);   
        }
    }
    
    class Result {
        public int startIndex;
        public int endIndex;
        
        public Result(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        @Override
       public int hashCode()
       {
          return Objects.hash(
             this.startIndex, this.endIndex);
       }
        @Override
        public boolean equals(Object obj) {
          if (obj == null)
          {
             return false;
          }
          if (getClass() != obj.getClass())
          {
             return false;
          }
          final Result other = (Result) obj;
          return Objects.equals(this.startIndex, other.startIndex)
                 && Objects.equals(this.endIndex, other.endIndex);
        }
    }
    
    public static void main(String[] args) {
		
	}
}
