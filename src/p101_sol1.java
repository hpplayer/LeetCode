import java.util.*;
/**
 * My original AC solution, without help.
 * This problem i very similar to same Tree p100
 * Here I used BFS and ArrayDeque to implement Queue
 * They are almost same, except that here we need add nodes in right backward and add nodes in left forward, due to the mirror property
 * A trick here to test child == null case is 
 * if(root.left == null || root.right == null) return root.left == root.right;
 * 
 * see code below for comparsion
 * 
 * Sol2 is DFS approach
 * @author hpPlayer
 * @date Apr 6, 2015 6:16:53 PM
 */

public class p101_sol1 {
	public static void main(String[] args){
		TreeNode a= new TreeNode(1);
		TreeNode b= new TreeNode(2);
		TreeNode c= new TreeNode(3);
		a.left = b;
		a.right = c;
		System.out.println(isSymmetric(a));
	}
	public static class TreeNode {
		  int val;
		 TreeNode left;
		 TreeNode right;
		  TreeNode(int x) { val = x; }
		}
	
	 public static boolean isSymmetric(TreeNode root) {
	        if(root == null) return true;
	        if(root.left == null || root.right == null) return root.left == root.right;
	        /*
	        if(root.left == null && root.right == null) return true;
	        if(root.left == null && root.right != null) return false;
	        if(root.right == null && root.left != null) return false;
	        */
	        Queue<TreeNode> leftQ = new ArrayDeque<TreeNode>();//only stored nodes in left
	        Queue<TreeNode> rightQ = new ArrayDeque<TreeNode>();
	        if(root.left.val != root.right.val) return false;
	        leftQ.add(root.left);
	        rightQ.add(root.right);
	        while(!leftQ.isEmpty() && !rightQ.isEmpty()){//BFS, but we add nodes in que in reverse order
	            int leftQsize = leftQ.size();
	            int rightQsize = rightQ.size();
	            if(leftQsize != rightQsize) return false;
	           // for(int i = 0; i < leftQsize; i++){
	                TreeNode leftNode = leftQ.poll();
	                TreeNode rightNode = rightQ.poll();
	                if(leftNode.left != null && rightNode.right != null && leftNode.left.val == rightNode.right.val){
	                    leftQ.add(leftNode.left);
	                    rightQ.add(rightNode.right);
	                }else if (!(leftNode.left == null && rightNode.right == null)){
	                    return false;
	                }
	                
	                if(leftNode.right != null && rightNode.left != null && leftNode.right.val == rightNode.left.val){
	                    leftQ.add(rightNode.left);
	                    rightQ.add(leftNode.right);
	                }else if (!(leftNode.right == null && rightNode.left == null)){
	                    return false;
	                }
	          //  }
	            
	        }
	        
	        return leftQ.isEmpty() && rightQ.isEmpty();
	    }
}
