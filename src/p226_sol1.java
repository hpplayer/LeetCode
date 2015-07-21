/*
 * Invert Binary Tree
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 */



/**
 * This is my original solution without help
 * Although I made an error in dealing with "null"
 * When facing case that one child is null, we could also swap the left and right child.
 * This is the recursion version
 * @author hpPlayer
 * @date Jul 21, 2015 3:05:53 PM
 */
public class p226_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode invertTree(TreeNode root) {
		helper(root);
		return root;
	}

	public void helper(TreeNode root) {
		if (root == null)
			return;
		if (root.left != null) {
			helper(root.left);
		}
		if (root.right != null) {
			helper(root.right);
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}
}
