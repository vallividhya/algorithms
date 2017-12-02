package week1_assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Arrays {
	
	// Problem 1
	// https://www.interviewbit.com/problems/add-one-to-number/
	// Category Bucket 3 : Array Math
	// This solution has O(n) time complexity and O(n) space
	
	 public static int[] plusOne(int[] A) {
	        if (A.length == 1 && A[0] == 0) {
	            A[0] = 1;
	            return A;
	        }
	        int m = 0;
	        while (A[m] == 0) {
	        	m++;
	        }
	        
	        int[] res = new int[A.length - m];
	        for (int k=0; k < res.length; k++) {
	        	res[k] = A[k + m];
	         }
	        
			int carry = 0;
	        for (int i = res.length - 1; i >= 0; i--) {
	        	int sum = res[i] + carry;
	        	if (i == res.length - 1) {
	        		 sum = res[i] + 1 + carry;
	        	}
	        	
	        	if (sum > 9) {
	        		res[i] = sum % 10;
	        		carry = sum / 10;
	        	} else {
	        		res[i] = sum;
	        		carry = 0;
	        		return res;
	        	}
	        }
	        
	        int[] result = new int[res.length + 1];
	        if (carry > 0) {
	        	
	        	result[0] = carry;
	        	for (int j = 0; j < res.length; j++) {
	        		result[j + 1] = res[j];
	        	}
	        	
	        } 
	        return result;
	    }
	
	// Problem 2 - Overlapping intervals
	// Category Bucket 4:  Value ranges
	// https://www.interviewbit.com/problems/merge-overlapping-intervals/
	 
	 public static class Interval {
	     int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	      
    }
	 
	 
	 public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		Collections.sort(intervals, new IntervalComparator());
		
		ArrayList<Interval> resultList = new ArrayList<Interval> ();
		for (Interval i : intervals) {
			if (resultList.isEmpty() || resultList.get(resultList.size() - 1).end < i.start) {
				resultList.add(i); 
			} else {
				resultList.get(resultList.size() - 1).end = Math.max(i.end, resultList.get(resultList.size() - 1).end);
			}
		}
		
		return resultList;
	        
    }
    
    static class IntervalComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval i1, Interval i2) {
			if (i1.start < i2.start) {
				return -1;
			} else if (i1.start == i2.start) {
				return 0;
			} 
			return 1;
		}
        
    }

    public static void main(String[] args) {
		Interval i1 = new Interval(1, 3);
		Interval i2 = new Interval(2, 6);
		Interval i3 = new Interval(8, 10);
		Interval i4 = new Interval(15, 18);
		Interval i5 = new Interval(16, 20);
		ArrayList<Interval> l = new ArrayList<>();
		l.add(i1);
		l.add(i2);
		l.add(i3);
		l.add(i4);
		l.add(i5);
		ArrayList<Interval> l1 = Arrays.merge(l);
		for (Interval i : l1) {
			System.out.println("[" + i.start + " , " + i.end + "]");
		}
		
	}
}
