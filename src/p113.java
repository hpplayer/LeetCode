import java.util.*;
/**
 * Similar question to p112, a classic DFS problem 
 * This is my AC solution without help(actually, I did optimized the code based on others result, see comment below)
 * 
 * @author hpPlayer
 * @date Apr 5, 2015 9:21:54 PM
 */
public class p113 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	  public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        DFS(new ArrayList<Integer>(), result, sum, root);
	        return result;
	    }
	    
	    public void DFS(List<Integer> Path, List<List<Integer>> result, int sum, TreeNode root){
	        if (root == null) return;
	        if(root.left == null && root.right == null && (sum - root.val) == 0){
	            Path.add(root.val);
	            result.add(new ArrayList<Integer>(Path));
	            Path.remove(Path.size()-1);
	            return;
	        }
	        if(root.left == null && root.right == null){
	            return;
	        }
	        
	        Path.add(root.val);
	        
	        /*
	         * Here will come a problem, if we done DFS on left, then probably the Path will include all nodes in left path 
	         * then it will pass this path to right node, which will not be right
	         * so we need creat new List everytime when pass the path like :
	         * DFS(new ArrayList<Integer>(Path), result, sum-root.val, root.left);
	         * DFS(new ArrayList<Integer>(Path), result, sum-root.val, root.right);
	         * or we can simply delete each node when done DFS on this node like 
	         * add Path.remove(Path.size()-1) in the end to clean the node in the path
	         */
	        DFS(Path, result, sum-root.val, root.left);
	        DFS(Path, result, sum-root.val, root.right);
	        Path.remove(Path.size()-1);
	    }
}
