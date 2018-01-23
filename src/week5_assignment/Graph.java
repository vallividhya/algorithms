package week5_assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Graph {

	// Problem 1 - Level Order 

	public int[][] levelOrder(TreeNode A) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		List<int[]> resultList = new ArrayList<>();

		if (A != null) {
			q.add(A);
		}

		while(!q.isEmpty()) {
			int count = q.size();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < count; i++) {
				TreeNode n = q.poll();
				list.add(n.val);
				if (n.left != null) {
					q.add(n.left);  
				} 
				if (n.right != null) {
					q.add(n.right);
				}
			}
			int[] levelArr = new int[list.size()];
			for (int j = 0; j < list.size(); j++) {
				levelArr[j] = list.get(j);
			}
			resultList.add(levelArr);
		}
		int[][] result = new int[resultList.size()][];
		for (int k = 0; k < result.length; k++) {
			result[k] = resultList.get(k);
		}
		return result;
	}

	// Problem 2 - Sorted Linked List to BST

	TreeNode makeBST(ListNode head, List<Integer> list, int index) {
		if (head == null || list.isEmpty()) {
			return null;
		} else {
			TreeNode root = new TreeNode(list.get(index));
			List<Integer> leftList = list.subList(0, index - 1); 
			int leftIndex = leftList.size() / 2;
			root.left = makeBST(head, leftList, leftIndex);

			List<Integer> rightList = list.subList(index + 1, list.size() - 1); 
			int rightIndex = rightList.size() / 2;
			root.right = makeBST(head, rightList, rightIndex);
			return root;
		}
	}


	class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
	}

	public TreeNode sortedListToBST(ListNode a) {
		ListNode cur = a;
		List<Integer> list = new ArrayList<>();
		while (cur != null) {
			list.add(cur.val);
			cur = cur.next;
		} 

		int index = list.size() / 2;
		return makeBST(a, list, index);
	}

	//Problem 3 - Stepping Numbers within the given range
	
	public int[] stepnum(int A, int B) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = A; i <= B; i++) {
           if (stepNum(i)) {
               list.add(i);
           }
        }
        int[] res = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            res[j] = list.get(j);
        }
        return res;
    }
   
    boolean stepNum(int x) {
        if (x < 10 && x >= 0) {
            return true;
        }
        int digit1 = -1;
        while (x > 0) {
            int digit2 = x % 10;
            if (digit1 != -1) {
                if (Math.abs(digit1 - digit2) != 1) {
                    return false;
                }
            }
            x /= 10; 
            digit1 = digit2;
        }
        return true;
    } 
    
    // Problem 4
	
	public int ladderLength(String start, String end, ArrayList<String> dictV) {
		if (dictV == null || dictV.size() == 0) {
			return 0;
		}
		Set<String> dict = new HashSet<>(dictV);
		if (!dict.contains(end)){
			return 0;
		}
		if (start.equals(end)) { 
			return 1;

		}
		Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
		q1.add(start);
		dict.remove(start);
		q2.add(end);
		dict.remove(end);
		return bfs(q1, q2, dict, 2);
	}

	private int bfs(Set<String> q1, Set<String> q2, Set<String> dic, int len) {
		if (q1.isEmpty() || q2.isEmpty()){
			return 0;    
		} 
		if (q1.size() > q2.size()) {
			return bfs(q2, q1, dic, len);
		}

		Set<String> temp = new HashSet<>();
		for (String word : q1) {
			char[] chArr = word.toCharArray();
			for (int i = 0; i < chArr.length; ++i) {
				char c = chArr[i];
				for (char newC = 'a'; newC <= 'z'; ++newC) {
					chArr[i] = newC;
					String next = new String(chArr);
					if (q2.contains(next)) return len;
					if (dic.contains(next)) {
						temp.add(next);
						dic.remove(next);
					}
				}
				chArr[i] = c;
			}
		}
		return bfs(temp, q2, dic, len + 1);
	}

	public static void main(String[] args) {

	}
}
