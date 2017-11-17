package checkpoint5;

import java.util.HashSet;

public class Solution {
	
	public int longestConsecutive(final int[] A) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            hashSet.add(A[i]);
        }
        
        int lengthOfSequence = 0;
        for (int j=0; j < A.length; j++) {
            if (!hashSet.contains(A[j]-1)) {
                // This is the first element in the sequence
                // Find other elements in the sequence
                int num = A[j];
                while (hashSet.contains(num)) {
                    num++;
                }
                if (lengthOfSequence < (num - A[j])) {
                    lengthOfSequence = num - A[j];
                }
            }
        }
        return lengthOfSequence;
    }
	
}
