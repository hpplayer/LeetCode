/*
Count Complete Tree Nodes 


Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
*/

/**
 * This problem is actually not a hard problem.
 * My naive DFS recursive got LTE error, since it simply visit each node without using the property of nodes = 2^height -1
 * Then I thought this problem may be solved by some math techniques, I even thought about revising the TreeNode structure to build 
 * the B tree like we have pointer in the leaf node. But all failed.
 * 
 * So here are two smart solutions towards this problem.
 * sol1 and sol2 are recursive and iterative version of solution 1
 * sol3 is the solution2
 * 
 * 
 * What is solution 1?
 * We observe that a complete tree can be composed of two subtrees. The end of leaf node will always be in one of those subtrees, so we will get one 
 * full subtree and one non-full subtree. Then comes a very important observation:
 * if the leftmost height is same for two subtrees, then the non-full tree must be in right subtree. Otherwise, the non-full tree must be in 
 * the left subtree. 
 * So in sol1, we will check the leftmost height for the left and right subtree. If they are same, we can easily calculate the nodes in 
 * left subtree by 2^h-1 (h starts from 1), then we need run the same program of right subtree to do the same thing. In other aspect, if the
 * height are not same, that means the non-full subtree is in left subtree, we will easily calculate the nodes in right subtree by 2^h -1,
 * then we need run the same program of left subtree to do the same thing. So from above, we found what we do is checking two subtrees,
 * we then compute the nodes in full subtree with 2^h -1, and run program on the non-full tree until we have reach end of leaf node, and return 1
 * 
 * Iterative and recursive solutions are very similar as they both run the checkHeight() on leftmost node of two subtrees, compare heights, then
 * calculate the nodes in full tree and run program on non-full tree
 * 
 * Remark:
 * 1) In our countHeight() function, the height starts from 1 if input root is not null
 * 2) when we return the total nodes rooted in root Nodes, we will get:
 * 2^h-1 from full subtree + countNodes(non-full subtree) + 1 from root itself
 * 3) It is MUCH FASTER to use the bit manipulation to get 2 power of n, simply by 1<<n, compared with Math.pow(2, n)
 * 4) When doing the bit shift, remember use parentheses(), otherwise we will get error. EX: 1<<2 + 3, will actually shift like 1 <<5
 * not 1 << 2, so we need rewrite as (1<<2) + 3 !!!!!!!!!!!!!!!! THIS IS VERY IMPORTANT, BE CAREFUL!!!!
 * 5) Time complexity: O(log(n)^2), check each subtree costs log(n), when checking each subtree, the countHeight() costs log(n)
 * 
 * sol1 is the recursive version with idea 1 
 * sol2 is the iterative version with idea 1
 * sol3 is the solution with idea 2
 * sol4 is the python version of sol1
 * sol5 is the python version of sol2
 * sol6 is the python version of sol3
 * Based on the easiness and clearness of the solution, sol1 and sol2 are more recommended
 * @author hpPlayer
 * @date Aug 21, 2015 5:54:51 PM
 */
public class p222_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.left = b;
		a.right = c;
		System.out.println(new p222_sol1().countNodes(a));
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int countHeight(TreeNode root) {
		int height = 0;
		//if the input root is not null
		//then the height starts from 1
		while (root != null) {
			root = root.left;
			height++;
		}
		return height;
	}

	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		int left = countHeight(root.left);
		int right = countHeight(root.right);
		if (left == right) {// two subtrees same height, we need search right subtree to find the end leaf node
			//System.out.println(countNodes(root.right));
			//System.out.println(1 << left);
			//1 << left - 1 due to the defintion of tree nodes 2^h -1 (h starts from 1), + countNodes(root.right) due to calculating the right subtree
			//+ 1 due to the current root node
			//so it can be simplified to (1 << left ) + countNodes(root.right)
			return (1 << left) + countNodes(root.right); //dont forget () around the bit shift!!!!
		} else {// two subtree different height, it means we need search left subtree to find the end leaf node
			return (1 << right) + countNodes(root.left);
		}
	}
}
