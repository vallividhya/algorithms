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
	
	// Problem 2 : 
}
