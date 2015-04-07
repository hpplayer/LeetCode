import java.util.*;
/**
 * BFS approach
 * here I used ArrayDeque to implement queue
 * ArrayDeque cannot contain null, so we need check node == null before inserting into queue
 * also, if we find some nodes cant be inserted into queue, then except for both are null, other cases should all be false
 * firstly pop left node, then pop right node
 * Finally, return if both ques are empty after loop exited.
 * 
 * Remark:
 * Deque is an interface.
 * 
 * @author hpPlayer
 * @date Apr 6, 2015 3:17:10 PM
 */

public class p100_sol2 {
	public static void main(String[] args){
		TreeNode a= new TreeNode(0);
		TreeNode b= new TreeNode(0);
		System.out.println(isSameTree(a, b));
	}
	public static class TreeNode {
		  int val;
		 TreeNode left;
		 TreeNode right;
		  TreeNode(int x) { val = x; }
		}
	 public static boolean isSameTree(TreeNode p, TreeNode q) {
	        if(p == null && q == null) return true;
	        if(p== null || q == null || p.val != q.val){
	            return false;
	        }
	        //deque does not support null, so we need filter those null out
	        //Queue only strore valid TreeNode
	        Queue<TreeNode> pque = new ArrayDeque<TreeNode>();//use Deque instead of linkedlist
	        Queue<TreeNode> qque = new ArrayDeque<TreeNode>();
	        pque.add(p);
	        qque.add(q);
	        while(!pque.isEmpty() && !qque.isEmpty()){
	            TreeNode nodeP = pque.poll();
	            TreeNode nodeQ = qque.poll();
	            if(nodeP.left != null && nodeQ.left != null && nodeP.left.val == nodeQ.left.val){
	                pque.add(nodeP.left);
	                qque.add(nodeQ.left);
	            }else if (!(nodeP.left==null && nodeQ.left == null)){
	                return false;
	            }
	            /*else if ((nodeP.left== null && nodeQ.left != null) || (nodeP.left != null && nodeQ.left == null) ||
	            		(nodeP.left != null && nodeQ.left != null && nodeP.left.val != nodeQ.left.val)){
	                return false;
	            }
	            */
	            if(nodeP.right != null && nodeQ.right != null && nodeP.right.val == nodeQ.right.val){
	                pque.add(nodeP.right);
	                qque.add(nodeQ.right);
	            }else if (!(nodeP.right==null && nodeQ.right == null)){
	                return false;
	            }
	            /*else if ((nodeP.right== null && nodeQ.right != null) || (nodeP.right != null && nodeQ.right== null) ||
	            		(nodeP.right != null && nodeQ.right != null && nodeP.right.val != nodeQ.right.val)){
	                return false;
	            }
	            */
	        }
	        return pque.isEmpty() && qque.isEmpty();
	    }
}
