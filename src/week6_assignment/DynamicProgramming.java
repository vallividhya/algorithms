package week6_assignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
}
