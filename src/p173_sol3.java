import java.util.Stack;

/**
 * slightly modified sol2 to make it more clean and understandable
 * @author hpPlayer
 * @date Apr 10, 2015 4:58:11 PM
 */
public class p173_sol3 {
	
	public static class TreeNode {
	    int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	  }

    public static void main(String[] args){
    	TreeNode a = new TreeNode(2);
    	TreeNode b = new TreeNode(1);
    	TreeNode c = new TreeNode(3);
    	a.left = b;
    	a.right = c;

    	BSTIterator sol = new BSTIterator(a);
    	while(sol.hasNext()){
    		System.out.println(sol.next());
    	}
    }
    
	public static class BSTIterator {
		
		
	    Stack<TreeNode> stack;
	    public BSTIterator(TreeNode root) {
	        stack = new Stack<TreeNode>();
	        if(root == null) return;
	        while(root != null){//add nodes in left subtree and node itself
	            stack.push(root);
	            root = root.left;
	        }
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        return !stack.isEmpty();
	    }

	    /** @return the next smallest number */
	    public int next() {
	        TreeNode temp = stack.pop();
	        int result = temp.val;
	        temp = temp.right;//start search on the right tree(also in-order traveral, left->mid->right)
	        while(temp != null){
	            stack.push(temp);
	            temp = temp.left;
	        }
	        
	        System.out.println(stack.size());
	        return result;
	    }
	}
}
