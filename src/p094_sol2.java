import java.util.*;
/**
 * My original recursive solution, which is one pass AC.
 * However, the problem requires using iterative...
 * iterative is not so easy.
 * @author hpPlayer
 * @date Apr 6, 2015 7:27:53 PM
 */

public class p094_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        DFS(result, root);
        return result;
    }
    
    public void DFS(List<Integer> result, TreeNode root) {
        if(root == null) return;
        DFS(result, root.left);
        result.add(root.val);
        DFS(result, root.right);
    }
}
