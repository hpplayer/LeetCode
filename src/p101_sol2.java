/**
 * My original AC approach without help
 * It is not hard to write,
 * the only error I made is I carelessly add left.right and right.left twice instead of right.right and left.left
 * 
 * Again, here is a trick to make code shorter when deal with null case 
 * if(left == null || right == null}
 * return left == right, see code below
 * @author hpPlayer
 * @date Apr 6, 2015 6:19:40 PM
 */
public class p101_sol2 {
	public static class TreeNode {
		  int val;
		 TreeNode left;
		 TreeNode right;
		  TreeNode(int x) { val = x; }
		}
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return DFS(root.left, root.right);
    }
    
    public boolean DFS(TreeNode left, TreeNode right){//left and right must equal
        if(left == null || right == null){
            return left == right;
        }
       // if(left == null && right == null) return true;
        //if((left == null && right != null) || (left != null && right == null)) return false;
        if(left.val == right.val){
            if(DFS(left.right, right.left) && DFS(right.right, left.left)) return true;
        }
        return false;
    }
}
