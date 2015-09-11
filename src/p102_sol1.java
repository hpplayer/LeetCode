import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Binary Tree Level Order Traversal


Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
*/		
	
/**
 * This is my original AC solution without help
 * a general BFS solution is perfect to finish this problem.
 * Here I use a queue to finish BFS.
 * 
 * Sol1 is my own BFS solution with queue
 * Sol2 is DFS solution done with depth argument
 * 
 * BFS is more suitable for this problem, but sol2 provides a new way to do it.
 * Both are recommended
 * @author hpPlayer
 * @date Sep 11, 2015 5:15:52 PM
 */
public class p102_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> que = new LinkedList<TreeNode>();
		que.offer(root);

		while (!que.isEmpty()) {
			int size = que.size();
			List<Integer> lst = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode curr = que.poll();
				lst.add(curr.val);
				if (curr.left != null)
					que.offer(curr.left);
				if (curr.right != null)
					que.offer(curr.right);
			}
			result.add(lst);
		}

		return result;
	}
}
