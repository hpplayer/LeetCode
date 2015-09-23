/**
 * Recursive solution
 * 
 * By using recursion, we can avoid checking the children of p. We always looking for the first node in tree that has value > p.val
 * 
 * If p has child, we will recursively find its leftmost node in right tree, which can also be treated as the first value that is larger 
 * than p.val
 * If p does not has child, then we will recursively find the root or ancestor which is the first node.val >= p.val. So during backtrack,
 * it p.val >= current node's val we will just return null, but if we meet the first node that has value > p, then we will return its node,
 * and later nodes that have larger value than p will not reassign left as we have found such node.
 *  
 * Remark:
 * we are actually looking for the first node that its value is larger than p.val. So, in case "root.val <= p.val",
 * we will just return the result, but in case "root.val > p.val", we will assign the return value to be the first node that its value > p.val.
 * In later backtrack, if we found the return value has been assigned to a non-null value, we will keep return it even its value is larger than p.val.
 * @author hpPlayer
 * @date Sep 22, 2015 9:33:06 PM
 */
public class p285_sol2 {
	 public class TreeNode {
	      int val;
	      TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
}
	 
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;
        
        //if p is in right tree of root, then root and its subtree will not be successor, we can just search
        //in right subtree
        if(root.val <= p.val){
            return inorderSuccessor(root.right, p);
        }else{
            TreeNode left = inorderSuccessor(root.left, p);
            //if we found successor in left tree, then just return it
            //if we have not found it, then it means p is the rightmost node in left tree,
            //so we just need to return root
            return left == null?  root : left;
        }
    }
}
