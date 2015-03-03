/**
 * This algorithm firstly recursively search left, then travel back, so the last_printed value will be updated from bot to top,
 * for all nodes above or in the right side of the nodes that give the last_printed value, they all should be larger than it
 * so in the end we guarantee all nodes are visited and all conditions are checked!
 * 
 *DFS-approach!!!! 
 *Basic idea: we recursively search left then itself then right, and we use a temp value to store the previous node.
 *so we get the property that if itself is smaller than the previous node (bounce from left), then the tree should 
 *be false. Then we update the previous value to current Node's value and search right, do similar thing..
 * 
 * Remark: this is DFS algorithm!
 * 
 * check CtCI CH4, 4.5
 * @author hpPlayer
 * @date Mar 3, 2015 1:04:34 AM
 */

public class p098_sol2 {
    public static Integer prevNodeVal = null;
    public static boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(!isValidBST(root.left)) return false;
        if(prevNodeVal != null && root.val <= prevNodeVal) return false;
        prevNodeVal = root.val;
        if(!isValidBST(root.right)) return false;
        return true;
    }
    
    public static class TreeNode {
	    int val;
	      TreeNode left;
	   TreeNode right;
	   TreeNode(int x) { val = x; }
	  }
  
  public static void main(String args[]){
	  /*
	  TreeNode a = new TreeNode(10);
	  TreeNode b = new TreeNode(5);
	  TreeNode c = new TreeNode(15);
	  TreeNode e = new TreeNode(6);
	  TreeNode f = new TreeNode(20);
	  a.left = b;
	  a.right = c;
	  c.left = e;
	  c.right = f;
	  */
	  TreeNode a = new TreeNode(0);
	  TreeNode b = new TreeNode(-1);
	  TreeNode c = new TreeNode(20);
	  TreeNode d = new TreeNode (10);
	  a.left = b;
	  a.right = c;
	  d.left = a;
	  System.out.println(isValidBST(d));
  }
}
