/**
 * Another solution uses the similar idea as sol1
 * @author hpPlayer
 * @date Mar 20, 2015 1:50:49 AM
 */

public class p124_sol2 {
	  public class TreeNode {
		    int val;
		     TreeNode left;
		     TreeNode right;
		    TreeNode(int x) { val = x; }
		}
	  int maxValue = Integer.MIN_VALUE;
	   public int maxPathSum(TreeNode root) {
	        DFS(root);
	        return maxValue;
	    }
	   
	   public int DFS(TreeNode root){
		   if(root == null) return 0;//return 0, more convenient for sum calculation
		   int left = DFS(root.left);
		   int right = DFS(root.right);
		   int currentSum = root.val;
		   if(left > 0) currentSum += left;//we calculate subtree here, so we must return positive value not 0
		   if(right > 0) currentSum += right;
		   maxValue = Math.max(currentSum, maxValue);//if current max value > global max value ? update if necessary, we compare the max subtree here
		   //for value that we need return, it can only take one helper value either from left or right (assume both positive) 
		   return Math.max(left, right) + root.val > 0 ? root.val +Math.max(left, right) : 0;//return if current subtree can help + value
	   }
}
