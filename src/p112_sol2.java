import java.util.LinkedList;
import java.util.Queue;
/**
 * BFS approach, since we need use some data structure to keep track of current sum,
 * so here we uses two queues, one for keep track of TreeNode, one for keep track of current Sum
 * each time, we poll data from two queues, and check if the TreeNode is leaf && current sum == sum
 * if yes, then return true
 * otherwise, we need enqueue the left child and its value + current sum
 * and enqueue the right child and its value + current sum 
 * 
 * if q is empty(means we have visited all nodes), and we still can't return true,
 * then it means there is no such path, we just return false;
 * 
 * Remark:
 * dont remember consider the case that input root is null, then we cant enquee it into our que,
 * in such case, we directly return false
 * @author hpPlayer
 * @date Apr 5, 2015 9:31:17 PM
 */

public class p112_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return false;
		Queue<TreeNode> nodes = new LinkedList<TreeNode>();
		Queue<Integer> currSums = new LinkedList<Integer>();
		nodes.add(root);
		currSums.add(root.val);
		while(!nodes.isEmpty()){
			TreeNode currNode = nodes.poll();
			Integer currSum = currSums.poll();
			if(currNode.left == null && currNode.right == null && currSum == sum){
				return true;
			}
			
			if(currNode.left != null){
				nodes.add(currNode.left);
				currSums.add(currSum + currNode.left.val);
			}
			
			if(currNode.right!= null){
				nodes.add(currNode.right);
				currSums.add(currSum + currNode.right.val);
			}
		}
		return false;
	} 
}
