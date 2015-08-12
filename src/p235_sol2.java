import java.util.Stack;
/**
 * My second AC solution without help. This is iterative solution
 * The basic idea is similar, but here we use a stack to store TreeNode
 * Since we only return result based on value, differed from p236, we don't need to record the path
 * Actually we even don't need stack, see sol3 for iterative solution without stack
 * @author hpPlayer
 * @date Aug 11, 2015 10:46:14 PM
 */

public class p235_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.val <= Math.min(p.val, q.val)) stack.push(temp.right);
            if(temp.val >= Math.max(p.val, q.val)) stack.push(temp.left);
            if(temp.val >= Math.min(p.val, q.val) && temp.val <= Math.max(p.val, q.val)) return temp;
        }
        
        return root;
    }
    
}
