import java.util.Stack;
/**
 * This is my new solution to this problem when I am doing it in 2 round.
 * I create a copy of each node, whose left and right child will be set to null.
 * Then I insert original node's right, then copy, then left. The problem is this approach
 * will insert full subtree into the stack, instead of O(h).
 * 
 * But I still want to keep it here as I like the idea though the memory need may be large
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 8:29:12 PM
 */

public class p173_sol4 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public p173_sol4(TreeNode root) {
    	if(root != null) stack.push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
         TreeNode curr = stack.pop();
   
         while(curr != null){
        	 TreeNode newNode = new TreeNode(curr.val);
        	 if(curr.right != null) stack.push(curr.right);
        	 stack.push(newNode);
        	 //if(curr.left != null) stack.push(curr.left);
        	 curr = curr.left;
         }
         return stack.pop().val;
    }
}
