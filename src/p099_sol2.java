import java.util.Stack;
/**
 * This is an iterative solution and it is a little bit tricky
 * The basic idea is same with sol1, but here we use stack.
 * We firstly recursively visit the left path, if reach end, then start backtrack
 * During the backtrack, if our prev is not null, we compare prev with curr, if our prev is null, then, we set prev = curr, then let 
 * curr = curr.right and start visit the right subtree
 * 
 * Remark:
 * 1) remember to set prev only after we have done visit of left path, we won't check any node during the visit on left path
 * 2) So all checks and update prev will occur when we done left path and start pop from stack
 * 
 * if the tree is like 
 *    2
 *     \
 *   	3
 *     /
 *    1
 *  our algorithm will set 2 as prev, then push 3, 1 to stack,
 *  so prev 2 will firstly compare with 1, we found prev > curr, then they will be recorded as first and second
 *  then prev = 1, and visit 3, 1 < 3, they follow order, so we finally swap 2 and 1, and it would be our solution
 *  
 * 3) recoverTree() is my own solution, while recoverTree()2 is other's solution
 * I like my solution better since it keeps consistent with solution of my inorder traversal
 * @author hpPlayer
 * @date Sep 11, 2015 12:30:54 AM
 */
public class p099_sol2 {
	public static void main(String[] args) {
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(1);
		a.right = b;
		b.left = c;
		/*
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(2);
		TreeNode d = new TreeNode(6);
		TreeNode e = new TreeNode(4);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		 */
		new p099_sol2().recoverTree(a);
		System.out.println(a.val);
		
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	  public void recoverTree(TreeNode root) {
	        if(root == null) return;
	        
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode p = root;
	        TreeNode prev = null;
	        TreeNode first = null;
	        TreeNode second = null;
	        while(!stack.isEmpty() || p != null){
	            if(p != null){
	                stack.push(p);
	                p = p.left;
	            }else{
	                TreeNode t = stack.pop();
	                if(prev != null && prev.val > t.val){
	                    if(first == null) first = prev;
	                    if(first != null) second = t;
	                }
	                prev = t;
	                p = t.right;
	            }
	        }
	        
	        int temp = first.val;
	        first.val = second.val;
	        second.val = temp;
	    }
	  
    public void recoverTree2(TreeNode root) {
        if(root == null) return;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;
        while(!stack.isEmpty() || p != null){
            while(p != null){
            	stack.push(p);
            	p = p.left;
            }
            
            //if(stack.isEmpty()) break;
            
            TreeNode curr = stack.pop();
            
            if(prev != null){
            	if(prev.val > curr.val){
            		if(first == null) first = prev;
            		if(first != null) second = curr;
            	}
            }
            
            prev = curr;
            p = curr.right;
            
        }
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
