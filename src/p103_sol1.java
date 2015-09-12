import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/**
 * This is my original AC solution without help
 * The basic idea is still using the BFS, but we need able to read queue backward, so the order of next layer will be reversed
 * Dequeue thus become a perfect data structure for this problem. We insert nodes into the deque follow forward order,then read it 
 * follow backward order. But we need be careful about inserting nodes into the deque. Because when we insert nodes into the deque with 
 * forward order, then left child will be firstly insert then right child. However, when we insert nodes into the deque with backward order,
 * the right child should be firstly insert then left child, which means we still need an indicator tell us which order we are currently 
 * following. Except that, our algorithm is perfect!
 * 
 * Actually, deque is such a perfect data structure for this problem that we can even finish whole program only using dequeue
 * Sol2 provides a different solution only using deq but still BFS
 * Sol3 provides a DFS solution with recursion
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 5:49:04 PM
 */
public class p103_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> que = new LinkedList<TreeNode>();
		que.add(root);
		boolean isForward = false;
		while (!que.isEmpty()) {
			int size = que.size();
			List<Integer> lst = new ArrayList<Integer>();
			Deque<TreeNode> deq = new LinkedList<TreeNode>();
			for (int i = 0; i < size; i++) {
				TreeNode curr = que.poll();
				lst.add(curr.val);
				if(curr.left != null && curr.right != null){
				    if(!isForward){
				        deq.addLast(curr.left);
				        deq.addLast(curr.right);
				    }else{
				        deq.addLast(curr.right);
				        deq.addLast(curr.left);				        
				    }
				    
				}else if(curr.left != null){
				    deq.addLast(curr.left);
				}else if(curr.right != null){
				    deq.addLast(curr.right);
				}
			}
			while (!deq.isEmpty()) {
				que.add(deq.pollLast());
			}

			if (isForward) {
				isForward = false;
			} else {
				isForward = true;
			}

			result.add(lst);
		}
		return result;
	}
}
