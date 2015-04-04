/**
 * Very smart iterative way
 * Basically, we only look at current node's left and right subtree
 * we need insert left subtree into mid between current node and right subtree
 * so we iteratively find the rightmost node (R) in left subtree, and make current node's right child 
 * become the right child of R. and let current node's left child become the current node's right child
 * Since we have moved left subtree into right hand, we can safely set left subtree = null
 * After done these operations, we have flatten one left subtree, we just need do same operations to all
 * left subtree, then we are done.
 * 
 * I think this approach is more concise and pretty
 * Based on this one, we can apply similar idea (find the rightmost node, and attach current right node to that node) in another recursive way
 * @author hpPlayer
 * @date Apr 3, 2015 8:12:18 PM
 */
public class p114_sol2 {
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x){
			val = x;
		}
	}
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){//no left subtree, skip over
                root = root.right;
            }else{//has subtree, we need insert subtree into right subtree
                TreeNode temp = root.left;
                while(temp.right != null){//find rightmost node in left subtree
                    temp = temp.right;
                }
                temp.right = root.right;//attach current right node to the rightmost node in left subtree
                root.right = root.left;//update right subtree, let curreNode's right node become its left node
                root.left = null;//set left subtree to null, so we final structure will not be a mess
            }
        }
    }
}
