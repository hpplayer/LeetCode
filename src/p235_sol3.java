/**
 * Since the problem can be converted to pure math problem, we can simply use a while loop and if statements to search LCA
 * @author hpPlayer
 * @date Aug 11, 2015 10:52:02 PM
 */
public class p235_sol3 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(true){
            if(root.val < Math.min(p.val, q.val)){
                root = root.right;
            } else if (root.val > Math.max(p.val, q.val)){
                root = root.left;
            }else{
                return root;
            }
        }
    }
}
