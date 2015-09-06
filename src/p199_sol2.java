import java.util.ArrayList;
import java.util.List;

/**
 * recursive solution is much easier than iterative solution
 * We set a global bar, if current level is higher than global bar then we add it into our list
 * then we do recursive call on right first then on left
 * 
 * 
 * Remark:
 * @author hpPlayer
 * @date Sep 5, 2015 8:38:27 PM
 */

public class p199_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        DFS(0, root, result);
        return result;
    }
    int bar = -1;
    public void DFS(int level, TreeNode root, List<Integer> result){
        if(root == null) return;
        if(level > bar){
            result.add(root.val);
            bar ++;
        }
        
        DFS(level + 1, root.right, result);
        DFS(level + 1, root.left, result);
    }
}
