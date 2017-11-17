package checkpoint4;

public class Solution {
	
	//Definition for singly-linked list Node.
	  class ListNode {
	      public int val;
	      public ListNode next;
	     ListNode(int x) { val = x; next = null; }
	  }
	
	public ListNode subtract(ListNode A) {
        if (A == null || A.next == null) {
    		return A;
    	}

    	ListNode slow = A;
    	ListNode fast = A;
    	ListNode pre = null;
    	int count = 0;
        while (slow != null && fast != null && fast.next != null) {
        	pre = slow;
        	slow = slow.next;
        	count++;
        	fast = fast.next.next;
        }	
    	// At this point, we will have slow just after the midpoint
        ListNode halfHead = slow;
        ListNode reverseListHead = reverseList(halfHead);
        pre.next = reverseListHead;
        
        ListNode current = A;
        
        slow = reverseListHead;
        while (count > 0 && slow != null) {
        	current.val = slow.val - current.val;
        	count--;
        	slow = slow.next;
        	current = current.next;
        }
    	pre.next = reverseList(reverseListHead);
        
		return A;
    }
    
    private static ListNode reverseList (ListNode head) {
    	ListNode current = head;
    	ListNode pre = null;
    	ListNode temp = null; 
    	while (current != null) {
    		temp = current.next;
    		current.next = pre;
    		pre = current;
    		current = temp;
    	}
    	return pre;
    }
}
