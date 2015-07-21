import java.util.Stack;

/**
 * This is my original solution without help.
 * This is iteration version.
 * Since stack supports null, we can easily handle "handle" case
 * we could not retrieve children of null node, so we need check null everytime when we swap its children
 * 
 * The basic idea is almost same with p226.
 * Swap child, do recursion/iteration to its children
 * @author hpPlayer
 * @date Jul 21, 2015 3:10:15 PM
 */
public class p226_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node == null)
				continue;
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			stack.push(node.left);
			stack.push(node.right);
		}
		return root;
	}
}
