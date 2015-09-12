/*
Convert Sorted Array to Binary Search Tree


Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/


/**
 * My original AC solution without help
 * My experience with building trees from array is to use two pointers and build subtree recursively
 * Here is my algorithm:
 * We use two pointers to locate the range of left subtree and right subtree
 * Since the problem requires us build a balanced sorted tree, we simply locate the mid by start and end pointer, then build a new 
 * TreeNode, then build its left and right subtree through recursive calls, the left subtree's range is left, mid - 1, the right 
 * subtree's range is mid + 1, right
 * The boundary case is start == end, when we reach the leaf node of start > end, where we don't have valid nodes just return null
 * 
 * sol1 is my own recursive solution which is very simple
 * sol2 is my iterative solution which is a little bit tricky
 * 
 * Both sol1 and sol2 are recommended
 * @author hpPlayer
 * @date Sep 12, 2015 4:33:17 PM
 */
public class p108_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public TreeNode sortedArrayToBST(int[] nums) {
        return DFS(nums, 0, nums.length -1);
    }
    
    public TreeNode DFS(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end ) return new TreeNode(nums[start]);
        
        int mid = end + (start - end) /2;
        TreeNode curr = new TreeNode(nums[mid]);
        
        curr.left = DFS(nums, start, mid - 1);
        curr.right = DFS(nums, mid+1, end);
        
        return curr;
    }
}
