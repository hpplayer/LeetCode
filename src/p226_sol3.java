
/**
 * a more concise recursive solution.
 * Just return root without helper()
 * 
 * @author hpPlayer
 * @date Jul 21, 2015 3:29:33 PM
 */
public class p226_sol3 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}
