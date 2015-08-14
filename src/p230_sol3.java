/**
 * I first found the idea from a cpp program, that program changes k directly.
 * But it would not work on java, since java is pass-by-value for integers
 * So here we have to declare a class variable count to replace k.
 * The basic idea is still inorder traversal.
 * First search left subtree, if kth smallest number is still not found, then k--, and check current Node
 * if still not found, search right subtree instead
 * @author hpPlayer
 * @date Aug 14, 2015 2:45:38 PM
 */
public class p230_sol3 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(1);
		a.left = b;
		//b.right = a;
		System.out.println(new p230_sol3().kthSmallest(a, 1));
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	int count = 0;
	public int kthSmallest(TreeNode root, int k) {
		count = k;
		return DFS(root);
	}
    public int DFS(TreeNode root) {
    	//we don't care about what we return if reach leaves, we only care about if k has decreased to 0
    	//we will start care about the return value once k becomes 0
    	if (root == null) return 0;
    	
    	//we will decrease k during the visit of left subtree
    	int result = DFS(root.left);
    	if (count==0) return result;
    	--count;
    	//if current Node is the kth smallest node
    	if(count==0) return root.val;
    	//otherwise visit right subtree
    	return DFS(root.right);
    }
}
