package checkpoint3;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Solution {

	 public static int kthsmallest(final int[] A, int B) {
	        PriorityQueue<Integer> heap = new PriorityQueue(A.length);
	        for (int i=0; i < A.length; i++) {
	            heap.add(A[i]);
	        }
	        Iterator<Integer> iterator = heap.iterator();
	        while (iterator.hasNext() && B > 1) {
	            B--;
	            heap.remove();
	        }
	    return heap.poll();    
	 }
	 
	 public int numRange(int[] A, int B, int C) {
	        int countOfSubSequences = 0;
	        int start = 0; 
	        int curSum = A[0];
	        for (int i = 1; i <= A.length; i++) {
	            while (curSum > C && start < i-1) {
	                curSum = curSum - A[start];
	                start++;
	            }
	            int tempSum = curSum;
	            int tempStart = start;
	            while (tempSum >= B && tempSum <= C && tempStart < i) {
	                countOfSubSequences++;
	                tempSum = tempSum - A[tempStart];
	                tempStart++;
	            }
	            if (i < A.length) {
	                curSum = curSum + A[i];
	            }
	        }
	        return countOfSubSequences;
	    }
	
	
}
