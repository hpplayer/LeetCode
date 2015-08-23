import java.util.ArrayList;
import java.util.List;

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
