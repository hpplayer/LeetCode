/*
Sum Root to Leaf Numbers

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

/**
 * This is my original AC solution without help
 * I firstly try to solve the problem with recursion, this problem costs me a lot of trouble.
 * When can we start to count the sum? bottom-up or up-bottom?
 * Well, up-bottom is more preferable, since each recursion, we simply 10 times previous sum, then add current value
 * If we use bottom-up,then we backtrack, we have to find how many digits from bottom layer, then 10 times num of digits, to append current value
 * in front. If current node is null, then we simply return original pass sum, if not we append current node's value in the end, and pass it to
 * next valid child for recursion
 * 
 * code in DFS() can be further optimized to DFS2(), where we only need to check if current node is in leaf layer 
 * 
 * Sol1 is my recursive solution while sol2 is an iterative solution
 * Both are recommended
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 5:39:27 PM
 */

public class p129_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(1);
		TreeNode c = new TreeNode(4);
		TreeNode d = new TreeNode(7);
		TreeNode e = new TreeNode(4);
		TreeNode f = new TreeNode(8);
		TreeNode g = new TreeNode(3);
		TreeNode h = new TreeNode(6);
		TreeNode i = new TreeNode(4);
		TreeNode j = new TreeNode(7);
		a.left = b;
		b.left = c;
		c.left = d;
		d.left = e;
		e.left = f;
		f.left = g;
		g.left = h;
		h.left = i;
		i.left = j;
		
		System.out.println(new p129_sol1().sumNumbers(a));
		
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    public int sumNumbers(TreeNode root) {
        return DFS(root, 0);
    }
    
    public int DFS(TreeNode root, int sum){
        if(root == null) return sum;
        int newSum = sum*10 + root.val;
        if(root.left == null && root.right == null) return newSum;
        if(root.left != null && root.right != null) return DFS(root.left, newSum) + DFS(root.right, newSum);
        if(root.left != null) return DFS(root.left, newSum);
        return DFS(root.right, newSum);
    }
    
    public int DFS2(TreeNode root, int sum){
        if(root == null) return 0;
        int newSum = sum*10 + root.val;
        if(root.left == null && root.right == null) return newSum;
        return DFS(root.left, newSum) + DFS(root.right, newSum);
    }
}
