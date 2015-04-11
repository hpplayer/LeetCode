import java.util.*;
/**
 * This is another iterative way.
 * It changes the structure a little bit,
 * we only need keep one stack for right nodes
 * for current nodes and its left path,
 * we simply add them to stack and path in order, like:
 * curret node: a, left node: b, right node c
 * path.add(a) stack.push(c), path.add(b)
 * if b is leaf, then pop c, path.add(c).
 * 
 * I think this approach is not easy to come up in mind in first time
 * but it is a good approach
 * @author hpPlayer
 * @date Apr 11, 2015 1:31:02 AM
 */

public class p144_sol2 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		b.right = c;
		b.left =a ;
		a.right = d;
		//c.right = d;
		System.out.println(preorderTraversal(b));
	}
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		Stack<TreeNode> rights = new Stack<TreeNode>();
		TreeNode curr = root;
		while(curr != null){
			path.add(curr.val);
			if(curr.right != null){
				rights.push(curr.right);
			}
			curr = curr.left;
			if(curr == null && !rights.isEmpty()){
				curr = rights.pop();
			}
		}
		return path;
	}
}
