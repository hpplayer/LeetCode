/**
 * This is maybe another solution towards this problem.
 * The basic idea is that: Each time we will check the leftmost and rightmost height, if they are same, then current tree is a full tree
 * we simply return 2^h - 1. if they are not same, which must be left height taller based on the definition of complete tree, we will do 
 * the recursive calls on left and right subtree respectively. 
 * So this algorithm is very similar to naive DFS solution. But here we optimize the recursion by only doing recursion on non-full subtree
 * For full subtree, we will simply get the number of nodes by 2 ^h -1.
 * Thus this algorithm is faster than naive approach
 * We will do recursive calls only on non-full subtree, in each recursion, we will simply comparing the height of leftmost and rightmost height
 * so the running time should be still around O((logn) ^ 2)
 * 
 * Again here, we use bit manipulation to get the 2 power of n, which will be faster than Math.pow(2, n)
 * @author hpPlayer
 * @date Aug 21, 2015 6:25:29 PM
 */
public class p222_sol3 {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root, right = root;
        int height = 0;//height counter
        while (right != null){
            left = left.left;
            right = right.right;
            height ++;
        }
        //calculating the nodes in full tree is simply 2 ^ h -1
        if(left == null) return (1<<height) - 1;//if full tree
        //1 for root
        return 1 + countNodes(root.left) + countNodes(root.right);//if non-full tree
    }
}
