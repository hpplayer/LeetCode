/**
 * This is my original one-pass AC solution without help
 * we recursively visit the child of current node
 * As long as the node is not null, we will let compare current recursion's depth with maxDepth we have recorded so far
 * 
 * We can use the recursion with only one function, see sol2
 * 
 * 
 * Sol1 is my own recursive DFS solution which uses two functions
 * Sol2 is recursive DFS solution which use only one function
 * Sol3 is iterative DFS solution which use stack and HashMap
 * Sol4 is iterative BFS solution which use queue
 * 
 * For me, I think sol2 and sol4 is more recommended.
 * Because sol1() is lengthy, sol2 is shorter, sol2 uses external HashMap, sol4 only uses queue
 * 
 * The time complexity is all O(n), because we don't whehter we have reached the maximum depth until we finish whole tree
 * @author hpPlayer
 * @date Sep 11, 2015 8:54:32 PM
 */

public class p104_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int maxDepth(TreeNode root) {
		DFS(root, 1);
		return max;
	}

	int max = 0;

	public void DFS(TreeNode root, int depth) {
		// we only compare valid node
		if (root == null)
			return;

		max = Math.max(depth, max);

		DFS(root.left, depth + 1);
		DFS(root.right, depth + 1);
	}
}
