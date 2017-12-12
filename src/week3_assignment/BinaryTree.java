package week3_assignment;

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
		return null;
	}
}
