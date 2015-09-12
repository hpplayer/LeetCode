import java.util.*;

/**
 * Iterative DFS solution. To track the depth of each node, we can either create an inner class
 * or use two stacks or use HashMap
 * 
 * Here I use hashMap to record each node and its depth
 * 
 * For the traversal, I simply choose pre-order traversal
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 9:16:25 PM
 */
public class p104_sol3 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        HashMap<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int max = 0;
        
        stack.push(root);
        hs.put(root, 1);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            max = Math.max(max, hs.get(curr));
            if(curr.left != null){
                stack.push(curr.left);
                hs.put(curr.left, hs.get(curr) + 1);
            }
            if(curr.right != null){
                stack.push(curr.right);
                hs.put(curr.right, hs.get(curr) + 1);                
            }
        }
        
        return max;
    }
}
