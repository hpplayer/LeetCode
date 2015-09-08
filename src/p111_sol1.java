/*
Minimum Depth of Binary Tree 

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/


/**
 * All sol1, sol2 and sol3 are my original AC solution without help!
 * This is my recursive solution
 * If we found a leaf node, then we compare current depth with global min depth
 * As long as current node is not leaf node, we will recursively visit its child and increase depth by 1
 * Of course, we can directly do recursion on root without global min depth. minDepth2() below implemented a such solution with beautiful 
 * handler of depth returned from children
 * 
 * Sol1 is recursive DFS solution
 * Sol2 is iterative DFS solution
 * Sol3 is BFS solution
 * 
 * For this problem, BFS should be faster as it stops as soon as we have found a leaf node. For DFS we have to visit all nodes in the tree
 * So sol2 is recommended, but my sol1 and sol2 are also great solutions and can be used as reference
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 11:45:18 PM
 */

public class p111_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int minDepth(TreeNode root) {
		DFS(root, 1);
		return depth;
	}

	int depth = Integer.MAX_VALUE;

	public void DFS(TreeNode root, int lvl) {
		if (root == null) {
			depth = 0;
			return;
		}

		if (root.left == null && root.right == null) {
			depth = Math.min(depth, lvl);
		}

		if (root.left != null) {
			DFS(root.left, lvl + 1);
		}

		if (root.right != null) {
			DFS(root.right, lvl + 1);
		}
	}
	
	
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        //a beautiful trick is used here to sum the depth 
        return (left == 0 || right == 0)? left + right + 1 : Math.min(left, right) + 1;
    }
}
