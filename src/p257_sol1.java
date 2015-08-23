import java.util.ArrayList;
import java.util.List;
/*
Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/		
		
/**
 * This is my original AC solution without help
 * This problem is trivial and I don't see any astonishing solutions from others
 * Sol1 is my recursive solution, which recursively build string on its children as long as the node is not leaf node
 * Sol2 is my iterative version.
 * Sol3 is a little improved recursive solution, but the main idea is same
 * sol4 is python version of sol3
 * sol5 is python version of sol2
 * sol6 is a python recursive version using list comprehension
 * 
 * For this problem, sol2 and sol3 are recommended.
 * @author hpPlayer
 * @date Aug 23, 2015 12:35:38 PM
 */
public class p257_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<String>();
		DFS(root, result, "");
		return result;
	}

	public void DFS(TreeNode root, List<String> result, String temp) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {// leaf node
			if (temp.equals("")) {
				temp += root.val;
			} else {
				temp += "->" + root.val;
			}
			result.add(temp);
			return;
		}

		if (temp.equals("")) {
			temp += root.val;
		} else {
			temp += "->" + root.val;
		}
		String temp2 = temp;
		DFS(root.left, result, temp);
		DFS(root.right, result, temp2);

	}
}
