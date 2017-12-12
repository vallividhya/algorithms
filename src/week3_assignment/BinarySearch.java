package week3_assignment;

import java.util.ArrayList;
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
}
