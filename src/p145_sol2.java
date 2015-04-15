import java.util.*;
/**
 * This is an interesting approach, that we add nodes in the front of path,
 * which means we start from last node in path (root)
 * then add the rightmost node before the root
 * then add the add the siblings of rightmost node before the rightmost node,
 * ....
 * until we add the leftmost node to the path
 * then just return the path
 * 
 * Remark:
 * Cuz we are using the stack, so the order of adding to the path should be:
 * current node->right node ->left node
 * The order we add to the stack should be:
 * current node(pop out immediately in the next loop)->left node -> right node
 * 
 * The problem of this algorithm is that, it always add the node in front of arraylist,
 * so the time will be O(n) for each addition
 * @author hpPlayer
 * @date Apr 14, 2015 9:53:46 PM
 */
public class p145_sol2 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(4);
		//TreeNode d = new TreeNode(1);
		a.left = b;
		a.right = c;
		//c.left = d;
		for(Integer i : postorderTraversal(a)){
			System.out.println(i);
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		if(root == null) return path;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		TreeNode temp = null;
		while(!stack.isEmpty()){
			 temp= stack.pop();
			 path.add(0, temp.val);
			 if(temp.left != null) stack.push(temp.left);
			 if(temp.right != null) stack.push(temp.right);
		}
		return path;
	}
}
