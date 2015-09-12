import java.util.*;

/**
 * BFS solution, the basic idea is same with BFS, but now we add a variable depth to track the depth we visit
 * Each while loop is for one layer
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 9:19:45 PM
 */
public class p104_sol4 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        int depth = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            depth ++;//each while loop is one layer
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
        }
        
        return depth;
    }
}
