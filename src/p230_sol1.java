/*
Kth Smallest Element in a BST 

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
*/

/**
 * This is my original AC solution without help
 * What I do is in-order traversal
 * First check left subtree, then TreeNode itself, then check right subtree
 * We use a trick here that change int to Integer, so that if we reach the leaves, we can simply return null
 * So, if subtree does not return null, that means we should keep searching, but if the subtree return some values, that means 
 * we found the kth smallest elements in BST tree, so we stop search and return result
 * 
 * Iterative version and more solutions will be updated soon
 * 
 * Update:
 * Remark:
 * Instead of creating a variable to compare with k, we can actually let k-- in each recursive step
 * 
 * sol2 is the iterative solution
 * sol3 is another recursive solution with short code but using additional helper method
 * sol4 is python version of sol3 
 * sol5 is python version of sol2
 * @author hpPlayer
 * @date Aug 14, 2015 2:05:00 AM
 */
public class p230_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	int count = 0;

	public Integer kthSmallest(TreeNode root, int k) {
		if (root == null) {
			return null;
		}

		Integer temp = kthSmallest(root.left, k);
		if (temp != null) {
			return temp;
		}

		count++;
		if (k == count)
			return root.val;
		temp = kthSmallest(root.right, k);
		if (temp != null) {
			return temp;
		}
		return null;
	}
}
