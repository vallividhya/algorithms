package checkpoint2;

public class Solution {
	
	public static int[][] prettyPrint(int A) {
        int size = 2 * A - 1;
        int[][] spiral = new int[size][size];
        int n = A, minRow = 0, minCol = 0, maxRow = size - 1, maxCol = size - 1;
        
        while (n >= 1) {
        	for (int i = minCol; i <= maxCol; i++) {
        		spiral[minRow] [i] = n;
        	}
        	for (int j = minRow + 1; j  <= maxRow; j++) {
        		spiral[j] [maxCol] = n; 
        	} 
        	for (int i = maxCol - 1; i >= minCol; i--) {
        		spiral[maxRow] [i] = n;
        	}
        	for (int j = maxRow - 1; j >= minRow; j--) {
        		spiral[j][minCol] = n; 
        	}
        	minRow++;
        	minCol++;
        	maxRow--;
        	maxCol--;
        	n--;
        }
        return spiral;
    }
}
