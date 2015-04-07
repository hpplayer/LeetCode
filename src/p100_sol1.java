/**
 * My original approach without help
 * DFS approach,
 * I forgot the case that both p== null and q== null should return true
 * also we need check value after checking if any one node contains null
 * 
 * BFS approach see sol2
 * @author hpPlayer
 * @date Apr 6, 2015 3:37:37 PM
 */
public class p100_sol1 {
	public class TreeNode {
		  int val;
		 TreeNode left;
		 TreeNode right;
		  TreeNode(int x) { val = x; }
		}
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(q == null && p != null) return false;
        //here both p and q have values
        if(p.val != q.val) return false;
        if(isSameTree(p.left, q.left)){
            if(isSameTree(p.right, q.right)){
                return true;
            }
        }
        return false;
    }
}
