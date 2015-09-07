/**
 * This is my original AC solution without help
 * This problem does cost some troubles to me, and it does not as easy as I thought it would be
 * My solution works in this way:
 * We keep tracking the depth of two subtrees. We will recursively search the left and right child.
 * After we reach bottom, we will begin backtrack and return the depth.
 * If the depth returned by left and right child is differed more than 1, we will set the global parameter to false and report this.
 * So this is my algorithm.
 * 
 * Of course, and we found such a difference, we no longer care about the rest of DFS search, and we just need return back to our main isBalanced()
 * So, we can slightly modify the code to speed up the program. See my DFS2() which applies such optimization.
 * Since there is no way that we can get a depth < 0, the use of -1 as the flag is guaranteed to be working
 * 
 * After I checked my solution, I found actually my solution is one of the best solutions, cheers!
 * 
 * Sol1 is my own recursive solution, which is very clean and efficient
 * Sol2 is a very beautiful iterative solution, which is also a great solution, in my view, it is even more beautiful than my solution
 * 
 * Anyway, both sol1 and sol2 are recommended
 * @author hpPlayer
 * @date Sep 7, 2015 2:01:52 AM
 */

public class p110_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(2);
		a.right = b;
		b.right = c;
		
		System.out.println(new p110_sol1().isBalanced(a));
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    public boolean isBalanced(TreeNode root) {
        DFS(root);
        return result;
    }
    
    boolean result = true;
    public int DFS(TreeNode root){
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return 1;
        }
        
        int left = DFS(root.left);
        int right = DFS(root.right);
        
        if(Math.abs(left - right) > 1) result = false;
        
        return Math.max(left, right) + 1;
        
    }
    
    
    public int DFS2(TreeNode root){
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return 1;
        }
        
        int left = DFS(root.left);
        if(left == -1) return -1;
        int right = DFS(root.right);
        if (right == -1) return -1;
        
        if(Math.abs(left - right) > 1){
            result = false;
            return -1;//we have found such a subtree, we don't care about remaining part, just return back
        } 
        
        return Math.max(left, right) + 1;
        
    }
}
