/**
 * Single function without extra DFS()
 * we will start count depth when reach leaf, each non null layer will contribute 1 depth.
 * We do recursion on left and right nodes, and take the max one and +1, then return the updated depth value
 * So the top  recursion will have the max depth value
 * 
 * maxDepth() is the short version
 * maxDepth2() is the long version, which may be more clear
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 9:01:23 PM
 */
public class p104_sol2 {
    public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	    TreeNode(int x) { val = x; }
    }
    
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    public int maxDepth2(TreeNode root) {
        //extreme case when input root is null
        if(root == null) return 0;
        
        //reach leaf node
        if(root.left == null && root.right == null){
            return 1;
        }
        
        //current layer contributes depth 1
        int left = 1, right = 1;
        
        if(root.left != null) left += maxDepth(root.left);
        if(root.right != null) right += maxDepth(root.right);
        
        return Math.max(left, right);
    }
}
