package week1_prework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arrays {
	
	// Problem 1 - Max Non Negative Array
	// Category Bucket 1: Simulation Array
	// https://www.interviewbit.com/problems/max-non-negative-subarray/
	
	public static int[] maxset(int[] A) {
		long sum = 0;
		long maxSum = 0;
		int minIndex = -1;
		int maxIndex = -1;

		int i =0, j = i;
		while(i < A.length && j < A.length) {
			if (A[j] >= 0) {
				sum += A[j];
				j++;
			} else {
				if (sum > maxSum) {
					// Update indices;
					minIndex = i;
					maxIndex = j-1;
					maxSum = sum;

					// System.out.println("1[" + minIndex + " - " + maxIndex + "]" );
				} else if (sum == maxSum) {
					if ((maxIndex - minIndex + 1) < (j - i)) {
						// Update the index;
						minIndex = i;
						maxIndex = j - 1;
						// System.out.println("2[" + minIndex + " - " + maxIndex + "]" );
					} 

				}
				sum = 0;
				i = j;
				i++;
				j++;
			}
		}
		if (sum > maxSum) {
			// Update indices;
			minIndex = i;
			maxIndex = j - 1;
			//System.out.println("3[" + minIndex + " - " + maxIndex + "]" );
		} else if (sum == maxSum) {
			if ((maxIndex - minIndex) < (j - i)) {
				// Update the index;
				minIndex = i;
				maxIndex = j - 1;
				// System.out.println("4[" + minIndex + " - " + maxIndex + "]" );
			} 
		}
		if (minIndex >= 0 && maxIndex >= 0) {
			int[] res = new int[maxIndex - minIndex + 1];
			int m = 0;
			for (int k = minIndex; k <= maxIndex; k++) {
				res[m] = A[k];
				m++;  

			}
			return res;
		} else {
			return new int[0];
		}
	}
	
	// Problem 2 - Find Duplicate in Array
	// Category Bucket 2 :  Bucketing and Sorting
	// https://www.interviewbit.com/problems/find-duplicate-in-array/
	
	public int repeatedNumber(final List<Integer> a) {
		
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    
	    for (Integer i : a) {
	        if (map.containsKey(i)) {
	            return i;
	        } else {
	            map.put(i, 1);
	        }
	    }
	    return -1;
	}

	// Problem 3 - Max Sum Contiguous SubArray
	// https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
	
	public int maxSubArray(final int[] A) {
        int maxSum = Integer.MIN_VALUE, maxSoFar = 0;
        for (int i = 0; i < A.length; i++) {
            maxSoFar = Math.max(maxSoFar + A[i], A[i]);
            maxSum = Math.max(maxSum, maxSoFar);
        }
        return maxSum; 
    }
	
    public static void main(String[] args) {
//    	int[] A = {  24831, 53057, 19790, -10679, 77006, -36098, 75319, -45223, -56760, -86126, -29506, 76770, 29094, 87844, 40534, -15394, 18738, 59590, -45159, -64947, 7217, -55539, 42385, -94885, 82420, -31968, -99915, 63534, -96011, 24152, 77295 
// };
//    	int[] r = Arrays.maxset(A);
//    	for (int i = 0; i < r.length; i++) {
//    		System.out.print(r[i] + " ");
//    	}
    	ArrayList<Integer> al = new ArrayList<Integer>();
    	int[] a = {};
    	
    	al.add(3);
    	al.add(5);
    	al.add(5);
    	al.add(7);
    	al.add(8);
    	al.add(9);
    	al.add(9);
    	
    	//System.out.println(Arrays.removeDuplicates(al));
    	//Arrays.removeDuplicates(al);
    	
    	int[] n = { 1, 9};
    	//int[] res = Arrays.plusOne(n);
    	
    }
}
