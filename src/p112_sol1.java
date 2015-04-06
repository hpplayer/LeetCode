/**
 * simple question, just do normal DFS
 * Remark:
 * problem requires path start from root and end at leaves, so the true return condition is 
 * sum - root.val == 0 && root is leaf node
 * @author hpPlayer
 * @date Apr 5, 2015 7:41:30 PM
 */
public class p112_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		if ((sum - root.val) == 0 && (root.left == null && root.right == null)) return true;
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}
}
