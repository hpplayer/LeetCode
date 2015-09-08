import java.util.HashMap;
import java.util.Stack;

/**
 * This problem is very similar to Balanced Binary Tree (p110)
 * I use the similar trick as used in p110
 * We don't know current node's depth until we have visited both of its subtrees
 * So it is a post-order traversal. To track the depth of each node, I used HashMap
 * 
 * In this problem the depth is defined as the shortest path from the root node down to the nearest leaf node.
 * So our DFS will stop only when we reach leaf node, which does not have left and right child.
 * We will put current node only when both subtrees have been visited which means both children have been in HashMap, or one in Map another one 
 * is null
 * 
 * Because we are using DFS, we don't know if current depth is the global min depth until we have visited each node
 * So we will return the result after we have visited each node
 * @author hpPlayer
 * @date Sep 8, 2015 12:00:46 AM
 */

public class p111_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashMap<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if(left == null && right == null){
                hs.put(node, 1);
                continue;
            }
            //done visit of two subtrees
            if((left == null || hs.containsKey(left)) && (right == null ||hs.containsKey(right))){
                int l = left == null? Integer.MAX_VALUE : hs.get(left);
                int r = right == null? Integer.MAX_VALUE : hs.get(right);
                hs.put(node, Math.min(l, r) + 1);
                continue;//must put continue here otherwise we will enter if below
            }
            if(left != null && !hs.containsKey(left)){
                stack.push(node);
                stack.push(left);
            }else if(right != null && !hs.containsKey(right)){//or simply else{
                stack.push(node);
                stack.push(right);
            }
        }
        
        return hs.get(root);
    }
}
