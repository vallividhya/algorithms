package week3_assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {
	

	// Problem 1: Identical Binary Trees
	
	public int isSameTree(TreeNode A, TreeNode B) {
		if (A == null && B == null) {
			return 1;
		}
		if (A == null || B == null) {
			return 0;
		}
		int v = 0;
		if (A.val == B.val) {
			v = 1;
		}
		return v & isSameTree(A.left, B.left) & isSameTree(A.right, B.right);
	}
	
	// Problem 2 : Max Depth of binary tree
	
	public int maxDepth(TreeNode A) {
        if (A == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(A.left), maxDepth(A.right));
        }
    }
	
	// Problem 3 : Inorder traversal (without recursion)
	
	public int[] inorderTraversal(TreeNode A)  {
		if (A == null) {
			return null;
		}
		
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = A;
		while (current != null) {
			stack.push(current);
			current = current.left;
		}
		
		while (!stack.isEmpty()) {
			
			TreeNode n = stack.pop();
			resultList.add(n.val);
			if (n.right != null) {
				n = n.right;
			}
			while (n != null) {
				stack.push(n);
				n = n.left;
			}
			
		}
		int[] result = new int[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			result[i] = resultList.get(i);
		}
		return result;
	}
	
	
	// Problem : 4 - Invert Tree
	
	public TreeNode invertTree(TreeNode A) {
        if (A == null) {
            return A;
        } else {
            TreeNode temp = A.left;
            A.left = A.right;
            A.right= temp;
            invertTree(A.left);
            invertTree(A.right);
            return A;
        }
       
    }
	
	// Problem: 5 - Path Sum
	
	public int hasPathSum(TreeNode A, int B) {
        if (A == null) {
            return 0;
        } else if (A != null && A.left == null && A.right == null) {
           return A.val == B ? 1 : 0; 
        } else {
            int diff = B - A.val;
            int leftVal = hasPathSum(A.left, diff);
            int rightVal = hasPathSum(A.right, diff);
            return leftVal | rightVal;
        }
    }
	
	//Problem: 6 - Symmetric Binary Tree
	
	public int isSymmetric(TreeNode A) {
        if (A == null) {
            return 1;
        }
        if (A.left == null && A.right != null) {
            return 0;
        }
        if (A.right == null && A.left != null) {
            return 0;
        }
        if (A.left != null && A.right != null) {
            return A.left.val == A.right.val? 1 : 0;
        } 
        return isSymmetric(A.left) & isSymmetric(A.right);
    }
	
	public boolean hasPathSumBFS(TreeNode root, int sum) {
		 Queue<TreeNode> queue = new LinkedList<TreeNode>();
		 Queue<Integer> sumQ = new LinkedList<Integer>();
			
			if (root != null) {
				queue.add(root);
				sumQ.add(root.val);
			}
			
			while (!queue.isEmpty()) {
				TreeNode n = queue.poll();
				int sumInQ = sumQ.poll();
				if (n.left == null && n.right == null && sumInQ == sum) {
					return true;
				}
				if (n.left != null) {
					queue.add(n.left);
					sumQ.add(sumInQ + n.left.val);
				}
				if (n.right != null) {
					queue.add(n.right);
					sumQ.add(sumInQ + n.right.val);
				}
				
			}
			return false;
	}
	
	// Insert a new node in the tree, given the root of the tree and return the root of the tree
		public TreeNode insert(TreeNode node, int val) {
			if (node == null) {
				node = new TreeNode(val);
			} else {
				if (val < node.val) {
					node.left = insert(node.left, val);
				} else {
					node.right = insert(node.right, val);
				}
			}
			return node;
		}
	
		public void printTreePreOrder(TreeNode root) {
			if (root == null) {
				return;
			} else {
				System.out.print(root.val + " ");
				printTreePreOrder(root.left);
				printTreePreOrder(root.right);
				
			}
		}
		
		public void printBFS(TreeNode root) {
			if (root == null) {
				return;
			} else {
				Queue<TreeNode> queue = new LinkedList<TreeNode>();
				queue.add(root);
				
				while (!queue.isEmpty()) {
					TreeNode node = queue.poll();
					System.out.print(node.val + " ");
					if (node.left != null) {
						queue.add(node.left);	
					} 
					if (node.right != null) {
						queue.add(node.right);
					}	
				}
			}
		}
		
	public static void main(String[] args) {
		BinaryTree bst = new BinaryTree();
		TreeNode root = bst.insert(null, 4);
		System.out.println(root.val);
		bst.insert(root, 2);
		bst.insert(root, 12);
		bst.insert(root, 1);
		
		bst.insert(root, 3);
		bst.insert(root, 7);
		bst.insert(root, 9);
		bst.insert(root, 15);
		bst.printBFS(root);
		System.out.println("Has path sum 9? " + bst.hasPathSumBFS(root, 9));
	}
}
