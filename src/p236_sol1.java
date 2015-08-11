/*
 Lowest Common Ancestor of a Binary Tree 
 
 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T
that has both v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
since a node can be a descendant of itself according to the LCA definition.
 */

/**
 * This is my original AC solution without help
 * Due to server maintaince, I can double check my solution before submitting it
 * The result is accepted in the first run
 * 
 * The basic idea in the algorithm is:
 * 1) we do recursion on each node.left and node.right
 * 2) if both of node.left and node.right can return non-null value, then we know current node is the lowest parent node
 * 3) if we get non null result from one child, that means this child contains at least one target node, since current node has been checked
 * before as non parent node, we can simply return this non-null value to up level as the parent node must be in up levels. If in some upper level
 * it gets non null value from both left and right children, then that node must be the lowest parent node
 * 4) if both children only return null value that means current node is not in the valid subtree that contains target node, so we just return
 * null to upper level.
 * 
 * Since this problem requires us to return TreeNode not boolean, so we dont need write a function that search node and return boolean
 *
 * sol2 is the iterative solution with deque data structure
 * sol3 is python version of sol1
 * sol4 is python version of sol2 
 * @author hpPlayer
 * @date Aug 11, 2015 2:56:28 PM
 */

public class p236_sol1 {
	
	public static void main(String[] args){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(5);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(6);
		TreeNode e = new TreeNode(2);
		TreeNode f = new TreeNode(7);
		TreeNode g = new TreeNode(4);
		TreeNode h = new TreeNode(0);
		TreeNode i = new TreeNode(8);
		
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		e.left = f;
		e.right = g;
		c.left = h;
		c.right = i;
		
		p236_sol1 test = new p236_sol1();
		System.out.println(test.lowestCommonAncestor(a, d, e).val);
				
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;
		//found match
		if (root == p || root == q)
			return root;
		
		//search left part and search right part
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		
		//return root if root is the parent, i.e. both left and right are not null
		if(left != null && right != null) return root;
		/*
		boolean left = searchNode(root.left, p) && searchNode(root.left, q);
		boolean right = searchNode(root.right, p) && searchNode(root.right, q);

		if (left && right) {
			return root;
		}
		*/
		
		//if current Node is not the parent, but some node in left part is the parent or p||q in left part
		if (left != null) return left;
		//if current Node is not the parent, but some node in right part is the parent or p||q in right part
		if (right != null) return right;
		//this subtree does not contain the parent
		return null;
		
	}
	
	/*
	public boolean searchNode(TreeNode root, TreeNode n) {
		if (root == null)
			return false;
		if (root == n) 
			return true;
		return searchNode(root.left, n) || searchNode(root.right, n);
	}
	*/
}
