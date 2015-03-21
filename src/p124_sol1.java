/**
 * This problem I solved 90%, but failed in the last step. This problem is asking the maximum sum path
 * Path is from one node to another node and cannot include the whole tree, if one of root's children has more than one leaf
 * So when we are returning values from a subtree, we can only return the max value either from left + node, right + node, node
 * But when we are recording the global max sum path, we may also include the inner path in the subtree which includes: left + node +right
 * I did not think of this idea, so I failed, but other thoughts, like how to do the DFS, set the global class variables, I almost do
 * the same. 
 * So the basic idea is:
 * Do DFS for each node,
 * If current subtree has sum > global sum, then update the sum
 * return the max valid path value to its parent node( left + node, right + node, node itself)
 *  
 * Remark:
 * I used to think of Integer and null(maybe helpful for validating binary tree), but it is inconvenient for the sum calculation,
 * and return 0 for boundary case is more convenient
 * @author hpPlayer
 * @date Mar 20, 2015 1:23:38 AM
 */
public class p124_sol1 {
	  public class TreeNode {
		    int val;
		     TreeNode left;
		     TreeNode right;
		    TreeNode(int x) { val = x; }
		}
	   int maxResult = Integer.MIN_VALUE;
	    public int maxPathSum(TreeNode root) {
	        DFS(root);
	        return maxResult;
	    }
	    public int DFS(TreeNode root){
	        if(root == null) return 0;
	        int midMAX = root.val;
	        Integer left = DFS(root.left);
	 
	        Integer right = DFS(root.right);

	         maxResult = Math.max(maxResult, left+midMAX+right);//we compare current subtree with whole tree here
	         
	         midMAX += Math.max(left,right);//current Max value is here

	        return midMAX>0? midMAX : 0;//we can push max value, even 0, to the top layer since we can compare current subtree even
	        //if the return value is 0 
	    }
}
