import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Binary Tree Right Side View

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/		
		

/**
 * This is my original AC solution without help
 * I use two stacks to follow the level of each node.
 * The visit order is current -> right ->....-> current -> left, 
 * So it is very similar to in-order traversal but with right child first
 * 
 * I use "bar" to indicate the highest level that already been visited 
 * I initially set the bar to be -1, so root node can be inserted into the result list
 * We will only insert the node.val into the result list when current level is higher than bar
 * Then we trying to visit the rightmost node in current path, so we assign current = current.next, and let rank be rank + 1 to indicate that
 * its child is one level higher than current node
 * 
 * If at sometime, we visited a null node, then we just need to pop the node from stack, and visit its left node
 * At the same time, we also pop the rank from rank stack, and plus one to indicate its child is one level higher than current node
 * 
 * 
 * We will visit each node twice to check its right child then left child, so the running time should be O(2n) = O(n)
 * But my algorithm is not space efficient as it uses a stack to follow the level of current node
 * I think O(2n) is a must for DFS, since we have to backtrack to continue our search
 * I translate this solution to sol2 with recursive way
 * 
 * 
 * O(n) algorithm can be found with BFS, an elegant BFS solution can be found in sol3
 * 
 * Remark:
 * 1) we can get rid of global bar by checking with the size of result, but then the level should start with 1
 * 2) Here I use two stacks, but we can also use one stack and an inner class which can record the level to replace the use two stacks
 * 
 * 
 * Sol1 is the iterative DFS solution
 * Sol2 is the recursive DFS solution
 * Sol3 is the iterative BFS solution
 * 
 * Three solutions are all very elegant and smart, all are recommended;
 * @author hpPlayer
 * @date Sep 5, 2015 8:15:48 PM
 */
public class p199_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		b.left = c;
		a.left = b;
		a.right = d;
		for(int i : rightSideView(a)){
			System.out.println(i);
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        int bar = -1;//we can get rid of it with result.size()
        Stack<Integer> rank = new Stack<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        int i = 0;//if use result.size(), then we need start i with 1, i.e. i = 1
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                if(i > bar){//can be replaced with if ( i > result.size())
                	bar ++;//can be removed if use result.size()
                	result.add(curr.val);
                }
                stack.push(curr);
                rank.push(i);
                i = rank.peek() + 1;
                curr = curr.right;
            }else{
                curr = stack.pop().left;
                i = rank.pop() + 1;
            }
        }
        
        return result;
    }
}
