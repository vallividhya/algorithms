package week2_assignment;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListProblems {

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
	  
	// Problem 3 : Swap List Nodes in Pairs
	// Category: Pointer move
	  public ListNode swapNodesInPair (ListNode head) {
		  if (head == null || head.next == null) {
			  return head;
		  }
		  ListNode newHead = head.next;
		  ListNode odd = head;
		  ListNode cur = null;
		  ListNode even = null;
		  ListNode temp = null;
		  while (odd != null) {
			  even = odd.next;
			  if (even != null) {
				 temp = even.next;
				  even.next = odd;
				  if (cur != null) {
					  cur.next = even;
				  }  
			  } else {
				  if (cur != null) {
					 // temp = null;
					  cur.next = odd;
					  break;
				  }
			  }
			  cur = odd;
			  odd = temp;
		  }
		  cur.next = temp;
		  return newHead;
	  } 
	  
	  // Problem: 4 Partition List
	  // Category : List Sort
	  
	  public ListNode partition(ListNode A, int B) {
      if (A == null || A.next == null) {
          return A;
      }
      ListNode pre = null;
      ListNode cur = A;
      Queue<ListNode> queue = new LinkedList<>();
      ListNode newHead = A;
      
      while (cur != null) {
          if (cur.val < B) {
              if (pre != null) {
                  pre.next = cur;
              } else {
              	if (cur != null) {
              		newHead = cur;
              	}
              }
              if (cur != null) {
              	pre = cur;
              }
          } else {
              //Add to queue
              queue.add(cur);
          }
          cur = cur.next;
      }
      while (!queue.isEmpty()) {
          if (pre != null) {
              pre.next = queue.poll();
              pre = pre.next;
          } else {
              pre = queue.poll();
          }
      }
      pre.next = null;
      return newHead;
  }
	  
	  // Helper method to print values of nodes in the list
	  
	  public static void printList(ListNode head) {
		  ListNode cur = head;
		  while (cur != null) {
			  System.out.print(cur.val + "->");
			  cur = cur.next;
		  }
		  System.out.print("null");
	  }
	  
	  //Helper method to create a new linked list from an array of values
	  
	  public static ListNode makeList(int[] array) {
		  if (array.length > 1) {
			  ListNode head = new ListNode(array[0]);
			  ListNode cur = head;
			  int i = 1;
			  while (i < array.length) {  
				  cur.next = new ListNode(array[i]);
				  cur = cur.next;
				  i++;
			  }
			  return head;
		  } else {
			  return new ListNode(array[0]);
		  }
		 
	  }
	  
	  
	  public static void main(String[] args) {
//	  	ListNode n5 = new ListNode(5, null);
//		ListNode n4 = new ListNode(4, null);
//		ListNode n3 = new ListNode(3, n4);
//		ListNode n2 = new ListNode(2, n3);
//		ListNode n1 = new ListNode(1, n2);
//		LinkedList list = new LinkedList();
//		ListNode nh = list.swapNodesInPair(n1);
//		printList(nh);
		
		int[] a = {31, 401, 143, 654, 910, 559, 582, 332, 26, 914, 611, 13, 160, 883, 212,
				100, 341, 999, 436, 95, 969, 210, 181, 318, 330, 753, 422, 180, 271, 127, 299, 10};
		int[] a1 = {1,2,3,4};
		int[] a2 = {14, 13, 12, 10, 15, 9, 4, 3, 1, 11};
		ListNode l = makeList(a);
		printList(l);
		System.out.println("Partition: ");
		LinkedListProblems list = new LinkedListProblems();
		ListNode nh = list.partition(l, 88);
		printList(nh);
	}
}
