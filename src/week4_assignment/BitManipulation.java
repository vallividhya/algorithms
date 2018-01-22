package week4_assignment;

import java.util.Arrays;

public class BitManipulation {

	// Min XOR
	public static int findMinXor(int[] A) {
        int xor = 0;
        int minVal = Integer.MAX_VALUE;
        Arrays.sort(A);
        for (int j = 1; j < A.length; j++) {
            xor = A[j - 1] ^ A [j];
            minVal = Math.min(xor, minVal);
        }
        return minVal; 
    }
	
	// Number of 1 bits 
	
	public int numSetBits(long a) {
	    int numOfOnes = 0;
	    while (a > 0) {
	       if ((a & 1) == 1) {
	           numOfOnes++;
	       }
	       a >>= 1;
	    }
	    return numOfOnes;
	}
	
	// Single Number among duplicates
	
	public int singleNumber(final int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        return xor;
    }
	
	// Single Number among triplets
	
	public int singleNumber2(final int[] A) {
        int ones = 0;
        int twos = 0;
        int notThrees;
        
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            notThrees = ~(ones & twos);
            ones &= notThrees;
            twos &= notThrees;
        }
        return ones; 
    }
	
	public static void main(String[] args) {
		int[] a = {15, 5, 1, 10, 2};
		findMinXor(a);
	}
}
