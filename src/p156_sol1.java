/*
Binary Tree Upside Down

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/

/**
 * First of all, let's understand the structure of the tree.
 * It's a tree nearly to LinkedList, but has at most a right Node, Like:
 *   /\
 *  /\
 * /
 * This problem is very similar to reverse LinkedList, but now each "ListNode" has a right Node
 * We just need to attach each node's right Node to next node's left. In reverse LinkedList we have a prev pointer points to the previous node,
 * similarly here we have a previous pointer points to parent Node, but we also need a pointer points to parentNode's right node. Here, we
 * call it "parentRight". One "next" node here is the "left" node of current node. After our current Node reach bottom, we just need to 
 * return prev node which points to the leftmost node
 * 
 * Similar to reverse LinkedList, we can solve it by iterative and recursive solution
 * 
 * Remark:
 * The official solution can be found in CleanCode HandBook
 * 
 * Sol1 is iterative solution
 * Sol2 is recursion solution
 * 
 * Both are beautiful solution, so both are recommended!
 * @author hpPlayer
 * @date Sep 17, 2015 10:52:12 PM
 */

public class p156_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		System.out.println(new p156_sol1().upsideDownBinaryTree(a).left.val);
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode parent = null, parentRight = null, curr = root;
        while(curr != null){
            TreeNode left = curr.left;//next curr Node
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }
        
        return parent;
    }
}
