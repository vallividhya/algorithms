package week5_assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

	// Problem 4 - Word Ladder I

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

	// Problem : 5 Capture Regions on Board
	
	public void solve(ArrayList<ArrayList<Character>> a) {

		if (a == null || a.size() == 0) {
			return;
		}

		for (int j = 0; j < a.size(); j++) {
			if(a.get(j).get(0) == 'O') {
				// check its neighbor '0's.
				findOsNearBoundary(a, j, 0);
			}
			if(a.get(j).get(a.get(j).size() - 1) == 'O') {
				// check if it has neighbor '0's.
				findOsNearBoundary(a, j, a.get(j).size() - 1);
			}
		}

		for (int i = 0; i < a.get(0).size(); i++) {
			if(a.get(0).get(i) == 'O') {
				// check its neighbor '0's.
				findOsNearBoundary(a, 0, i);
			}
			if (a.get(a.size() - 1).get(i) == 'O') {
				// check its neighbor '0's.
				findOsNearBoundary(a, a.size() - 1, i);
			}
		}

		// Capture regions that are enclosed
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < a.get(i).size(); j++) {
				//a.get(i).set(j, a.get(i).get(j) != 'V' ? 'X' : 'O');
				if(a.get(i).get(j) == 'V') {
					a.get(i).set(j, 'O');
				} else if (a.get(i).get(j) != 'V') {
					a.get(i).set(j, 'X');
				}
			}
		}
	}

	class Position {
		public int x;
		public int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private void findOsNearBoundary(ArrayList<ArrayList<Character>> a, int i, int j) {
		Queue<Position> q = new LinkedList<Position>();
		q.add(new Position(i, j));
		while(!q.isEmpty()) {
			Position p = q.poll();
			if (p.x >= 0 && p.x < a.size() && 
					p.y >= 0 && p.y < a.get(p.x).size() 
					&& (a.get(p.x).get(p.y) == 'O')) {
				// Mark this O and get neighbors that are O. 
				a.get(p.x).set(p.y, 'V');
				q.add(new Position(p.x - 1, p.y));
				q.add(new Position(p.x + 1, p.y));
				q.add(new Position(p.x, p.y - 1));
				q.add(new Position(p.x, p.y + 1)); 
			}
		}

	}

	// Problem : 6 - BlackShapes

	public int black(String[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length(); j++) {
				if(A[i].charAt(j) == 'X') {
					count += search(i, j, A);
				}
			}
		}
		return count; 
	}

	private int search(int i, int j, String[] a) {
		Queue<Position> q = new LinkedList<Position>();
		q.add(new Position(i, j));
		while(!q.isEmpty()) {
			Position p = q.poll();

			if (isValid(p.x, p.y, a) && a[p.x].charAt(p.y) == 'X' && a[p.x].charAt(p.y) != '1') {
				String s = a[p.x];
				s = s.substring(0, p.y) + '1' + s.substring(p.y + 1);
				q.add(new Position(p.x + 1, p.y));
				q.add(new Position(p.x - 1, p.y));
				q.add(new Position(p.x, p.y + 1));
				q.add(new Position(p.x, p.y - 1));
			} 
		}
		return 1;
	}

	private boolean isValid(int x, int y, String[] a) {
		return x >= 0 && y >= 0 && x < a.length && y < a[0].length();
	}


	// Problem 7 - Possibility of finishing all courses given pre-requisites
	
	
	public static int solve(int n, List<Integer> preReqList, List<Integer> courseList) { 
		boolean[] visited = new boolean[n];
		boolean[] processed = new boolean[n];
		
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < preReqList.size(); i++) {
			List<Integer> neighbors = graph.get(preReqList.get(i));
			if (neighbors == null) {
				 neighbors = new ArrayList<>();	
			} 
			neighbors.add(courseList.get(i));
			graph.put(preReqList.get(i), neighbors);
		}
		
		for (Entry<Integer, List<Integer>> e : graph.entrySet()) {
			if (!processed[e.getKey() - 1]) {
				return searchDfs(graph, processed, visited, e.getKey()) ? 1 : 0;
			}
		} 
		return 1;
		
	}
	
	private static boolean searchDfs(Map<Integer, List<Integer>> graph, boolean[] processed, boolean[] visited, int cur) {
		if (visited[cur - 1]) {
			return false;
		} 
		if (processed[cur - 1]) {
			return true;
		}
		if (!processed[cur - 1]) {
			visited[cur - 1] = true;
			// Process for all of current's immediate children 
			List<Integer> neighbors = graph.get(cur);
			if (neighbors != null) {
				for (int i = 0; i < neighbors.size(); i++) {
					if (!searchDfs(graph, processed, visited, neighbors.get(i))) {
						return false;
					}
				}
			} 
			processed[cur - 1] = true;
		} 
		return true;
	}

	// Problem 8 Clone graph

	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null ) {
			return null;
		}

		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		Queue<UndirectedGraphNode> q = new LinkedList<>();
		q.add(node);
		map.put(node, new UndirectedGraphNode(node.label));
		while(!q.isEmpty()) {
			UndirectedGraphNode n = q.poll();
			List<UndirectedGraphNode> neighborList = n.neighbors;
			for (UndirectedGraphNode neighbor : neighborList) {
				if (!map.containsKey(neighbor)) {
					map.put(neighbor, new UndirectedGraphNode(neighbor.label));
					q.add(neighbor);
				} 
				map.get(n).neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}

	public static void main(String[] args) {
		List<Integer> preReqList = new ArrayList<Integer>();
		List<Integer> courseList = new ArrayList<Integer>();

		preReqList.add(1);
		courseList.add(2);
		preReqList.add(1);
		courseList.add(3);
		preReqList.add(2);
		courseList.add(4);
		//System.out.println(solve(4, preReqList, courseList));
		preReqList.add(4);
		courseList.add(2);
		System.out.println(solve(4, preReqList, courseList));
	}
}
