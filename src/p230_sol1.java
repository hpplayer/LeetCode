/**
 * This is my original AC solution without help
 * What I do is in-order traversal
 * First check left subtree, then TreeNode itself, then check right subtree
 * We use a trick here that change int to Integer, so that if we reach the leaves, we can simply return null
 * So, if subtree does not return null, that means we should keep searching, but if the subtree return some values, that means 
 * we found the kth smallest elements in BST tree, so we stop search and return result
 * 
 * Iterative version and more solutions will be updated soon
 * @author hpPlayer
 * @date Aug 14, 2015 2:05:00 AM
 */
public class p230_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	int count = 0;

	public Integer kthSmallest(TreeNode root, int k) {
		if (root == null) {
			return null;
		}

		Integer temp = kthSmallest(root.left, k);
		if (temp != null) {
			return temp;
		}

		count++;
		if (k == count)
			return root.val;
		temp = kthSmallest(root.right, k);
		if (temp != null) {
			return temp;
		}
		return null;
	}
}
