/*
Lowest Common Ancestor of a Binary Search Tree 

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T
that has both v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
since a node can be a descendant of itself according to the LCA definition.

*/
/**
 * This is my original AC solution without help
 * The basic idea is as following:
 * 1) Since it is a binary search tree, left children will always smaller than current node, while right children will always larger 
 * than or equal to current Node. So The first Node we found that is large than one target and smaller than other target will be the 
 * lowest common ancestor
 * 2) Since we don't know the value of input p and q, it is safe to use Math.min and Math.max to get the value
 * 3) if current Node's value is larger than p and q, means we need search left subtree which has smaller value
 * else if current Node's value is smaller than p and q, means we need search right subtree which has larger value
 * 4)For search in subtree, here we simply use recursion on its children
 * 5)lowestCommonAncestor() is my original version, since we will return root anyway, we can simply add an else statement to return root
 * 6)I also see some solutions use a trick to check the result condition by checking if (root.val - p.val)*(root.val-q.val)<0, if it is then
 * we found our target node, else we have to search deeper
 * 
 * sol1 here is my recursive solution
 * sol2 is my iterative solution with stack
 * sol3 is my iterative solution without stack
 * sol4 is python version of sol1
 * sol5 is python version of sol3
 * @author hpPlayer
 * @date Aug 11, 2015 10:38:47 PM
 */
public class p235_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val <Math.min(p.val, q.val)){
             return lowestCommonAncestor(root.right, p, q);
        }
        if (root. val > Math.max(p.val, q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }
        
        if (root.val >= Math.min(p.val, q.val) && root.val <= Math.max(p.val, q.val)){
            return root;
        }
        
        return root;
    }
    
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val <Math.min(p.val, q.val)){
             return lowestCommonAncestor(root.right, p, q);
        }else if (root. val > Math.max(p.val, q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }else{
            return root;
        }

    }
}
