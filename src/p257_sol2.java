import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * This is my iterative version. The little trick here is using the second stack to provide the strings related with current node
 * The main idea is same to recursive version, in which we will recursively visit children as long as current node is not leaf node
 * @author hpPlayer
 * @date Aug 23, 2015 12:38:21 PM
 */

public class p257_sol2 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.left = b;
		a.right = c;
		for (String str : new p257_sol2().binaryTreePaths(a)){
			System.out.println(str);
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
	   public List<String> binaryTreePaths(TreeNode root) {
	        List<String> result = new LinkedList<String>();
			   if (root == null) return result;
	        Stack<TreeNode> nodes = new Stack<TreeNode>();
	        Stack<String> strs = new Stack<String>();
	        nodes.push(root);
	        String temp = root.val + "";
	        strs.push(temp);
	        while(!nodes.isEmpty()){
	            TreeNode node = nodes.pop();
	            String str = strs.pop();
	            //if leaf node
	            if(node.left == null && node.right == null){
	                result.add(str);
	                continue;
	            }
	            
	            //by using str + "->" and push to strs, we can avoid modifying str itself so avoid affecting the operation on node.right if not null
	            if(node.left != null){
	                nodes.push(node.left);
	                strs.push(str + "->" + node.left.val);
	            }
	            if(node.right != null) {
	                nodes.push(node.right);
	                strs.push(str + "->" + node.right.val);
	            }
	        }
	        
	        return result;
	    }
}
