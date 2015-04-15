import java.util.*;

/**
 * This approach let us add node as there is no requirement for post order traversal
 * But in order to let us add node to path follow post order traversal
 * we need use a variable to compare current node(top node in stack) and previous visited node
 * That is, we may have following relationship between current visited node and previous visited node:
 * previous node is current node's parent node or previous node == null(start of the loop)
 * then it is first time we visit this subtree, so we need firstly push left node, after several rounds when we meet current node again
 * we then push right node (current node is in stack until left and right nodes has been pop from stack)
 * so when we add the path from stack pop the order would be left -> right -> current 
 * This path can keep the size of stack = the max height of tree which would be smaller than two stacks approach, in which one stack
 * will keep all nodes in the tree
 * @author hpPlayer
 * @date Apr 14, 2015 10:53:40 PM
 */
public class p145_sol3 {
	public static void main(String[] args){
	TreeNode a = new TreeNode(1);
	TreeNode b = new TreeNode(2);
	TreeNode c = new TreeNode(3);
	//TreeNode d = new TreeNode(1);
	a.left = b;
	a.right = c;
	//c.left = d;
	for(Integer i : postorderTraversal(a)){
		System.out.println(i);
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
	
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		if(root == null) return path;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		TreeNode prev = null;//or we can let prev = root, but we need let prev == root in the first if statemetn below
		TreeNode curr = null;//or we can let curr = root, but we need let prev == root in the first if statemetn below
		while(!stack.isEmpty()){
			curr = stack.peek();//dont pop curr now, since we may have other nodes needed to be visited before add curr node
			if(prev == null || prev.left == curr || prev.right == curr){//if prev is the top of curr, we need firstly add curr
				if(curr.left != null){
					stack.push(curr.left);
				}else if (curr.right != null){
					stack.push(curr.right);
				}else{//curr is leaf, add it to the path
					path.add(curr.val);
					stack.pop();
				}
			}else if (curr.left == prev){//we have done traversal on left, now need visti right
				if(curr.right != null){
					stack.push(curr.right);
				}else{//we have done left and right traversal, can add current node to path
					path.add(curr.val);
					stack.pop();
				}
			}else if (curr.right == prev){//or we can use else{
				path.add(curr.val);
				stack.pop();
			}
			prev =curr;
		}
		return path;
	}
}
