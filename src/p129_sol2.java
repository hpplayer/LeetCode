import java.util.Stack;

/**
 * This is an iterative solution. Like sol1, we still do the top-bottom way to make calculation more convenient.
 * So we need a method to tell us the sum of path we have visited so far. We can do this in several ways:
 * 1) create an inner class which contains TreeNode and pathSum 2) change the value of currentNode, so node self has the sum information
 * 3) use multiple stacks to keep sum as well as TreeNodes.
 * here I choose method 1), so we can use one stack without breaking the tree structure
 * 
 * Idea is simple:
 * We can create a MyNode for each TreeNode, where we can have the sum of path we visited so far, then we simply add current TreeNode's val to
 * the path Sum, and push it into stack. After we done the visit of one path, we will store this whole integer to our sum. So compare with sol1,
 * we sum the value as well as we backtrack, here we sum the value after we have done one path
 * 
 * For the traversal, we have to tell children the path sum so far, so it is preorder traversal
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 6:06:22 PM
 */
public class p129_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    private class MyNode{
        TreeNode node;
        int sumSoFar;
        private MyNode(TreeNode node, int sumSoFar){
            this.node = node;
            this.sumSoFar = sumSoFar;
        }
    }
    public int sumNumbers(TreeNode root) {
        Stack<MyNode> stack = new Stack<MyNode>();
        if(root == null) return 0;
        MyNode node = new MyNode(root, root.val);
        stack.push(node);
        int sum = 0;
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            if(curr.node.left != null){
                stack.push(new MyNode(curr.node.left, curr.sumSoFar*10 + curr.node.left.val));
            }
            if(curr.node.right != null){
                stack.push(new MyNode(curr.node.right, curr.sumSoFar*10 + curr.node.right.val));
            }
            //if leaf node
            if(curr.node.left == null && curr.node.right == null) sum += curr.sumSoFar;
        }
        
        return sum;
    }
}
