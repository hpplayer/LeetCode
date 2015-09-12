import java.util.*;
/**
 * Here is another my original AC solution
 * Now I use DFS solution, which needs a depth parameter to tell the recursion which layer it is now
 * The basic idea is similar to p102, but now we need to insert each next layer in front of result list.
 * Accordingly, when we retrieve the current layer from result list, we need use result.size() - depth - 1
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 4:13:16 PM
 */


public class p107_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        DFS(0, root, result);
        return result;
    }
    
    public void DFS(int depth, TreeNode root, List<List<Integer>> result){
        if(root == null) return;
        
        if(result.size() == depth){
            result.add(0, new ArrayList<Integer>());
        }
        
        List<Integer> lst = result.get(result.size() - depth - 1);
        lst.add(root.val);
        
        DFS(depth +1, root.left, result);
        DFS(depth +1, root.right, result);
    }
}
