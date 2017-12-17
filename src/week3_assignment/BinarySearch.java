package week3_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
	
	// Problem 1 : Search for a range

	public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    
	    int low = 0;
	    int high = a.size() - 1;
	    int mid;
	    
	    while (low < high) {
	        mid = (low + high) / 2;
	        if (a.get(mid) < b) {
	            low = mid + 1;
	        } else {
	            high = mid;
	        }
	    }
	    if (a.get(low) != b) {
	        result.add(-1);
	        result.add(-1);
	        return result;
	    } else {
	        result.add(low);
	    }
	    high = a.size() - 1;
	    while (low < high) {
	        mid = ((low + high) / 2) + 1;
	        if (a.get(mid) > b) {
	            high = mid - 1;
	        } else {
	            low = mid;
	        }
	    }
	    result.add(high);
	    return result;
	}
	
	// Problem 2 : Rotated sorted array search
	
	public int search(List<Integer> a, int b) {
		int low = 0;
        int high = a.size() - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if (a.get(mid) == b)
                return mid;
        
            if (a.get(low) <= a.get(mid)){
                 if (b < a.get(mid) && b >= a.get(low)) {
                    high = mid - 1;
                 } else {
                    low = mid + 1;
                 }
            } else {
                if (b > a.get(mid) && b <= a.get(high)) {
                   low = mid + 1; 
                } else {
                    high = mid - 1; 
                }
            }
        
           
        }
        return -1;
    }
	
	
	// Problem 3 : Square root of a number
	
		public int sqrt(int a) {
	    if (a == 0 || a == 1) 
	        return a;
	        
	    int low = 1;
		    int high = a / 2;
		    while (low <= high) {
		        int mid = low + (high - low) / 2;
		        if (mid > a / mid) {
	            high = mid - 1;
	        }
	        else if (mid + 1 <= a / (mid + 1)) {
	            low  = mid + 1;
	        }
	        else {
	            return mid;
	        }
	    }
	    return -1;
	}
		
		// Problem : 4 Power
		
		public int pow(int x, int n, int d) {
			   
	        long a = x;
	        long res = 1L;
	    
	        while (n > 0) {
	          if (n % 2 == 1) {
	            res *= a;
	            res %= d;
	          }
	    
	          a *= a;
	          a %= d;
	          n = n >> 1;
	        }
	    
	        res = (res + d) % d;
	    
	        return (int) res; 	
		}
		
		
	// Count for each value in maxes, how many numbers are less than or equal to the value in maxes
	// Return result as array of counts for each maxes value. 
	
    static int[] counts(int[] nums, int[] maxes) {
        int[] result = new int[maxes.length];
        Arrays.sort(nums);
        
       
        for (int i = 0; i < maxes.length; i++) {
        	 int low = 0;
             int high = nums.length - 1;
             int target = maxes[i] + 1;
            // target is each val in maxes + 1;
            while (low < high) {
                int mid = (low + high) / 2;
                if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (nums[low] < target) {
                result[i] = nums.length;
            } else if (nums[low] >= target) {
                if ((low - 1) >= 0) {
                    result[i] = low;
                } else {
                    result[i] = 0;
                }
            }
            
        }
        for (Integer i : result) {
        	System.out.print(i + " ");
        }
        return result;
    }
    
	public static void main(String[] args) {
		//System.out.println(pow(-1, 1, 20));
		//System.out.println(pow(0, 0, 1));
		//System.out.println(pow(5, 55, 221));
//		System.out.println(pow(2, 5, 2));
//		System.out.println(pow(71045970,41535484, 64735492));
		//System.out.println(1 % 1);
//		int[] a = {1, 4, 2, 4};
//		int[] b = {3, 5};
		int[] a = {2, 10, 5, 4, 8};
		int[] b = {3, 1, 7, 8};
		counts(a, b);
	}
}
