import java.util.Stack;
/**
 * Another approach only uses stack.
 * Basically, it mimic the operation as we did in sol1, in which we firstly push all left nodes to the stack
 * so the top of stack will be current min value
 * after we pop current node, we need add all right nodes to the stack(even they are right nodes, they are still smaller than 
 * current node's parent node). The order we add to the stack follow the in-order traversal order, where we firstly add all left node 
 * then swtich to right nodes)
 * 
 * the boundary case is that we may have no nodes at all(null input)
 * or no left nodes(we only add current nodes) or normal cases where we have left nodes(we add current node -> left nodes -> left.left node)
 * 
 * @author hpPlayer
 * @date Apr 8, 2015 7:33:23 PM
 */


public class p173_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	Stack<TreeNode> stack = new Stack<TreeNode>();
    public p173_sol2 (TreeNode root) {
        if(root == null) return;
        if(root.left != null){
    	     while(root != null){
    	    	stack.push(root);
    	        root = root.left;
    	      }
    	}else{
    		stack.push(root);
    	}
   
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(stack.isEmpty()) return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {
    	TreeNode topNode = stack.pop();
    	int result = topNode.val;
       TreeNode currNode = topNode.right;
    	while(currNode != null){
    		stack.push(currNode);
    		currNode = currNode.left;
        }
    	return result;
    }
}
