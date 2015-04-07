import java.util.*;
/**
 * Iterative solution.
 * I did not make it, actually I was in mess when trying to solve it with iterative approach
 * This solution is a very nice one, the idea is that 
 * we use a stack to store all treenodes
 * when filling the stack, we aggresively insert all left nodes,
 * if there is no more left nodes that means we have done half part of this node's in-order traversal:
 * we use a temp node to keep track of traversal
 * logic is like 
 * {1,2,3} 1 is root, 2 is left child, 3 is right child
 * we add 1 temp =1 , then 2 temp = 2, 
 * when pop 2, let temp = 2.right, which is null
 * so we continue pop 1, let temp = 1.right which is 3
 * then we push 3 to stack
 * so our final output will follow this order:
 * 2->1->3
 * 
 * The key is to remember whenever our temp == null, it means we have done traversal to its left and mid,
 * the next step is visiting right
 * 
 * Since if there is no left child, we are keeping pop stack, our stack may become empty several times during traversal
 * like 1->2->3, 1.right = 2, 2.right =3, the process is like push(1), pop(1), push(2), pop(2), push(3), pop(3)
 * so our loop shall not stop when stack is empty, that is while(!stack.isEmpty() || temp != null){
 * @author hpPlayer
 * @date Apr 6, 2015 7:14:49 PM
 */
public class p094_sol1 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> Path = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode temp = root;
        while(!stack.isEmpty() || temp != null){//our inital stack is empty, so we have to let add one more condition to let loop roll that is temp!= null
            if(temp != null){
                stack.push(temp);
                temp = temp.left;
            }else{//no more left nodes, time to create path
                TreeNode currNode = stack.pop();
                Path.add(currNode.val);//this is like we dont have left child, now we add current node, next step is adding right child
                temp = currNode.right;//we should already push parent node to the list, 
            }
        }
        return Path;
    }
}
