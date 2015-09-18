/**
 * Another beautiful solution.
 * In iterative solution, we change the tree from top to bottom
 * In recursive solution, we change the tree from bottom to top
 * 
 * Like reverse LinkedList, after we found the tail node, or leftmost node here, each recursion will finally return this node.
 * Here, we will stop recursion only when we meet null, why? because, our new root node should has its new right and new left node,
 * we don't want skip it. So we treat the leftmost node as other left nodes, and still call recursion on it, until we meet null.
 * Then, we simply change current node's left to be parent's right node, and current node's right node to be parent. Then return our newRoot
 * 
 * @author hpPlayer
 * @date Sep 17, 2015 11:12:52 PM
 */

public class p156_sol2 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null) return root;
        
        return reverse(root, null);
    }
    
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public TreeNode reverse(TreeNode curr, TreeNode parent){
        
        if(curr  == null){
            //we found the leftmost node, just return it
            //why we don't return when curr.left == null? Because it means we will skip the leftmost node 
            //and don't attach parent'right node and parent node on it
            return parent;
        }
        TreeNode newRoot = reverse(curr.left, curr);
        curr.left = parent == null ? null : parent.right;//inital case, when we look at root, there is no parent
        curr.right = parent;
        
        return newRoot;
    }
}
