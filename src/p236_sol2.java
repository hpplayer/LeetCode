import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
/**
 * This is the iterative solution to solve the problem
 * Basic idea:
 * 1) We will do separate search to p and q and record the path
 * 2) return the path when we found p/q
 * 3) In the main program, start from root, we search two paths and find the node in both paths, and paths below it will be separated
 * 4) In the iterative search of DFS, we need record the path during the search, not only because we need the path to find the parent, but
 * also because we need the path to track the search progress, if we find current subtree does not contain target node, then we will remove
 * the root of this subtree from path
 * 5) We need append node to the rightmost during search and check leftmost node when search the lowest common parent, so double-
 * end-queue (Deque) is a very good data structure candidate
 * 
 * Remark:
 * Because we may have duplicate search in the DFS and we need to start from root for each search,
 * the running time is not as fast as recursive version. But the advantange is iterative solution will reduce the stackoverflow problem
 * @author hpPlayer
 * @date Aug 11, 2015 4:31:35 PM
 */
public class p236_sol2 {
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
		
		p236_sol2 test = new p236_sol2();
		System.out.println(test.lowestCommonAncestor(a, d, g).val);
				
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
		Deque<TreeNode> left = DFS(root, p);
		Deque<TreeNode> right = DFS(root, q);
		TreeNode ans = root;

		//our return path should  be like root->..->p and root->...->q
		//so we can start remove node from left until we reach the node that separates these two nodes and return it
		//otherwise return root node
		while(!left.isEmpty() && !right.isEmpty() && left.peekFirst() == right.pollFirst()){
			ans = left.pollFirst();
		}
		/* above code can also be written as
		 * 	while(!left.isEmpty() && !right.isEmpty()){
		 *   	if(left.peekFirst() != right.peekFirst()) break;
		 *   	ans = left.pollFirst();
		 *   	right.pollFirst();
		 *	}
		 * 
		 */
		return ans;
	}
	
	public Deque<TreeNode> DFS(TreeNode root, TreeNode n){
			Stack<TreeNode> stack = new Stack<TreeNode>();	
			Deque<TreeNode> path = new ArrayDeque<TreeNode>();
			stack.push(root);
			
			while(!stack.isEmpty()){
				TreeNode temp = stack.pop();
				/*
				 * We need have the feature that removes the path if target node is not in current subtree
				 * Otherwise our path will include all nodes in the tree and become meaningless
				 * We need search left and right subtree before we can judge whether current node can be potential 
				 * parent node, so post-order search is preffered here  
				 */
				//now we are returning back and see this node again, since we are doing post-order search, its time to remove it from path
				if (temp == path.peekLast()){
					path.removeLast();
				}else{
					path.offerLast(temp);
					if (temp == n) return path;
					//post-order search in stack, Last search Node in first, first search Node in last
					stack.push(temp);
					if (temp.right != null) stack.push(temp.right);
					if (temp.left != null) stack.push(temp.left);
				}

			}
			
			return path;
	}
}
