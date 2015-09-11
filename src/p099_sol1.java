/*
Recover Binary Search Tree 


Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/		
		


/**
 * First of all we should be clear that all nodes in left subtree should be smaller than any values in right subtree
 * 
 * Sol1 shows a solution with in-order traversal
 * Why? Because with in-order traversal we can create an ordered path
 * We recursively visit the left path, then when reach leaves and start back track, our returned node.val 
 * should be smaller than current node.val, then we recursively visit the right subtree (subtree not path), then the node.val from
 * caller should be smaller than current node.val. We don't start check nodes' values during recursively visiting left subtree,
 * because now we can have a sorted order:
 * left child.val < current.val < right child.val
 * 
 * We are told only two nodes are swapped. So we keep two variables "first" and "second" to record incorrect nodes
 * To keep our code formatted, we need a variable "prev" to track the node from previous visit, "root" is our current node
 * prev can either be node from left subtree or the parent node, in either case, we are supposed to have prev.val < curr.val
 * 
 * If we found case that prev.val > curr.val, then we know we have found a such pair. Which one should be in correct?
 * If it is the first time we found such case, then since previous paths have been checked without problem, the prev node should be 
 * incorrect placed, which means we got a larger value. 
 * If it is the second time we found such case, then we need to find a smaller value that can be used to replace incorrect placed
 * larger value, so now curr should be the incorrect placed (we treat root as curr)
 * 
 * So the basic flow of this algorithm is:
 * we firstly visit left sub path, then start record prev node in the backtracking
 * After reach current layer, we then reset prev to current node, then start visit subtree
 * During the traversals, we will record two nodes that are incorrectly placed, the first one should be prev, the next one should be
 * curr. After we done the traversal, we simply swap values between these two nodes
 * 
 * We are building stack during the traversal, each stack will have max size of O(lgn) where we visiting the left path. So
 * this is not constant space solution. But the clearness and simpleness of this algorithm still makes sol1 a very great solution
 * 
 * Sol1 provides a recursive solution, but use O(lgn) space
 * Sol2 provides an iterative solution, which is very similar to sol1
 * Sol3 provides a solution with Morris traversal, which only use constant space
 * 
 * All of them are recommended
 * @author hpPlayer
 * @date Sep 10, 2015 11:39:19 PM
 */

public class p099_sol1 {
		public static void main(String[] args) {
			TreeNode a = new TreeNode(0);
			TreeNode b = new TreeNode(1);
			a.left = b;
			/*
			TreeNode a = new TreeNode(5);
			TreeNode b = new TreeNode(3);
			TreeNode c = new TreeNode(2);
			TreeNode d = new TreeNode(6);
			TreeNode e = new TreeNode(4);
			a.left = b;
			a.right = c;
			b.left = d;
			b.right = e;
			 */
			new p099_sol1().recoverTree(a);
			System.out.println(a.val);
			
		}
	
		public static class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;
	
			TreeNode(int x) {
				val = x;
			}
		}


	   public void recoverTree(TreeNode root) {
	        inOrder(root);
	        
	        int temp = first.val;
	        first.val = second.val;
	        second.val = temp;
	    }
	    
	    private TreeNode first = null;
	    private TreeNode second = null;
	    private TreeNode prev = null;
	    
	    private void inOrder(TreeNode root){
	        if(root == null) return;
	        
	        //in order traversal, we first visit left subtree
	        inOrder(root.left);
	        
	        //we won't have prev when visiting leftmost path
	        if(prev != null){
	            //if it is backtrack from left path, prev would be the left child node, root is current node
	            //or if it is visiting right path, prev would be the parent node, root is right node
	            //In short words, left -> curr -> right, prev is always the previous node, root is current node
	            //In binary tree, we should have left.val < curr.val < right.val
	            //so if prev.val > curr.val, then we found the wrong node
	            if(prev.val > root.val){
	                //we will not touch first once it is assigned
	                //when we first found an ordered pair, prev must from the following paths
	                //cuz previous paths have been checked without problem
	                if(first == null) first = prev; 
	                //if the first has been set, it means we have to find a smaller number to replace with first
	                //now root < prev, so we replace first with root
	                if(first != null) second = root;
	            }
	        }
	        
	        prev = root;//now visiting right subtree, so prev become current node
	        inOrder(root.right);
	    }
}
