/*
Inorder Successor in BST

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
*/

/**
 * I solved this problem by myself. But I treat it as a binary tree instead of binary search tree. So I did not use property of BST.
 * The basic idea is so split problen into two cases: 1) p has right child 2) p does not has right child
 * 
 * The logic is like this:
 * If p node itself has right subtree, then its successor, must be the leftmost node in right tree, we can just search for that
 * If p node itself does not has not right subtree, then its successor may be null or maybe an ancestor that leads us reach p
 * when we are visiting subtrees, we always update this ancestor candidate when visiting left subtree. Because when p is in some left tree,
 * the ancestor candidate must be the root of this left subtree. If we are visiting right subtree, then we don't need to update the 
 * ancestor candidate since after we finish right subtree we will still jump to the ancestor (not necessary parent, if root of right tree
 * is right child of its parent) of root of right subtree. So if p is in left subtree, root might be the candidate, if p is in right tree,
 * then ancestor of root might be the candidate.
 *
 * Remark:
 * We always try to find the first number that is larger than p.val.
 * If p has child, we will find the leftmost node in right tree
 * If p does not has child, then we will find the lowest ancestor that has value > p.val
 * 
 * 
 * Sol1 is iterative solution
 * while Sol2 is recursive solution with another idea
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 8:49:46 PM
 */
		
public class p285_sol1 {
	 public class TreeNode {
		      int val;
		      TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
	 }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right != null){//if p itself has successor 
            p = p.right;
            while(p.left != null){
                p = p.left;
            }
            return p;
        }else{//if p does not successor, then the successor must be the ancestor node
            TreeNode ancestor = null;
            //we use root as the scanner
            while(root != p){
                if(root.val > p.val){
                    //we are going left tree, then current root may be the successor node, because its value is larger than p.val
                    ancestor = root;
                    
                    root = root.left;
                }else if(root.val < p.val){
                    //we are going right tree, thus current root must not be the successor node, because its value is smaller than p.val
                    //the successor node is supposed to be the ancestor node of current root that 
                    //we record before
                    root = root.right;
                }
            }
            
            return  ancestor;
       }
    }
}
