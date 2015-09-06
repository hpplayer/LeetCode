import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * This is a very elegant BFS solution.
 * We actually just want the rightmost node in each layer
 * 
 * We use a queue to include all nodes in one layer
 * Since we are using queue, the top of que is always the firstly inserted node
 * if we are visiting the layer from left to right, then the rightmost node is on the bottom of queue. Thus we will insert the last node 
 * of each layer into the result list
 * 
 * Remark:
 * Since we are changing que during the BFS, we have to record the size of each layer before visiting this layer
 * 
 * The space complexity is just O(n) and time complexity is also O(n)
 * @author hpPlayer
 * @date Sep 5, 2015 9:02:26 PM
 */

public class p199_sol3 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        //BFS
        while(!que.isEmpty()){
            int size = que.size();//the length of current layer
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                // we are using que, so the one on top should be the leftmost node in current layer
                //and the rightmost node is the last node in current layer
                if(i == size - 1) result.add(curr.val);
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
        }
        
        return result;
    }
}
