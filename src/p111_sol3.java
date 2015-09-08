import java.util.LinkedList;
import java.util.Queue;
/**
 * BFS solution. Unlike DFS, here we will stop the visit immediately when current node is the leaf node
 * The BFS search itself is like most BFS search where we use queue to keep each layer of nodes
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 11:59:42 PM
 */

public class p111_sol3 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		
		System.out.println(new p111_sol3().minDepth(a));
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	   public int minDepth(TreeNode root) {
	        if(root == null) return 0;
	        Queue<TreeNode> que = new LinkedList<TreeNode>();
	        que.offer(root);
	        int depth = 0;
	        while(!que.isEmpty()){
	        	
	            depth ++;
	            int size = que.size();
	            for(int i = 0; i < size; i++){
	                 TreeNode node = que.poll();
	                 if(node.left == null && node.right == null){
	                     return depth;
	                 }
	                 if(node.left != null){
	                     que.offer(node.left);
	                 }
	                 if(node.right != null){
	                     que.offer(node.right);
	                 }
	            }
	            
	        }
	        
	        return depth;
	    }
}
