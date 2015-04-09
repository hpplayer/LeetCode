import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Binary Search Tree Iterator
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

/**
 * My original AC solution without help.
 * The basic idea is similar to problem Binary Tree Inorder Traversal (p94)
 * I use a min Queue to keep the value in order.
 * When a node have no left child, pop current node, add to min que(in p94, its operation is adding to path), 
 * then move its just begin search start at right child,
 * 
 * So in future, I just need to dequeue to get the min value
 * 
 * The issue here is that we use an extra stack to keep TreeNode, so maybe it is not memory-friendly
 * See sol2 for a better solution
 * 
 * @author hpPlayer
 * @date Apr 8, 2015 7:16:55 PM
 */
public class p173_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		b.left = a;
		b.right = c;
		p173_sol1 bb = new p173_sol1(b);
		while (bb.hasNext()){
			System.out.println((bb.next()));
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	TreeNode currNode;
	Queue<Integer> minQue;
	Stack<TreeNode> stack;
	public p173_sol1(TreeNode root) {
		if(root == null) return;
		this.currNode = root;
		minQue = new LinkedList<Integer>();
		stack = new Stack<TreeNode>();
		TreeNode temp = root;
		while(!stack.isEmpty() || temp != null){
			if(temp != null){
				stack.add(temp);
				temp = temp.left;
			}else{
				TreeNode temp2 = stack.pop();
				minQue.add(temp2.val);
				temp = temp2.right;
			}
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		if(minQue == null || minQue.isEmpty()) return false;
		return true;
	}

	/** @return the next smallest number */
	public int next() {
		return minQue.poll();
	}
}
