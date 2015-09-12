import java.util.*;

/*
Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
*/		
		

/**
 * This is my original AC solution without help
 * Similar to Binary Tree Level Order Traversal (p102). But now we just need to insert each layer in front of result list
 * Hence the Linkedlist will be perfect data structure as it takes O(1) to insert front
 * @author hpPlayer
 * @date Sep 12, 2015 4:05:43 PM
 */
public class p107_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null) return result;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> lst = new LinkedList<Integer>();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                lst.add(curr.val);
                if(curr.left!= null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
            result.add(0, lst);
        }
        
        return result;
    }
}
