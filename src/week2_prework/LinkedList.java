package week2_prework;

public class LinkedList {
	class ListNode {
		     public int val;
		     public ListNode next;
		     ListNode(int x) { val = x; next = null; } 
	}

	// Problem 1: https://www.interviewbit.com/problems/list-cycle/
	// Category : List cycle
	
	public ListNode detectCycle(ListNode a) {
	    if (a == null || a.next == null) {
            return null;
        }
        ListNode slow = a;
        ListNode fast = a;

        while (fast != null && fast.next != null && slow != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                slow = a;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            } 
        }
        return null;
	}
	
	// Problem 2: Remove duplicates from sorted list
	// Category: List 2 pointer
	  public ListNode deleteDuplicates(ListNode A) {
	        ListNode cur = A;
	        
	        while (cur != null) {
	            ListNode nextNode = cur.next;
	            while (nextNode != null && cur.val == nextNode.val) {
	               ListNode temp = nextNode.next;
	               cur.next = nextNode.next;
	               nextNode = temp;
	            }
	            cur = cur.next;
	        }
	    return A;
	  }
}
