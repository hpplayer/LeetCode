import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * This is my iterative solution.
 * In order to follow the path of each number, I create a helper class, which is able to record the path, the sum and the k
 * Of course if we will, we can use an extra variable to record the last number we used in the path
 * 
 * Firstly, we create an initial node, which has sum 0, we then push it into the stack
 * Then we use a while loop on this stack, we pop the top node from the stack
 * if this node's k and sum have met our requirements, we add the path into our result list
 * if the node's k or sum have exceeded our requirements, we simply skip it 
 * Then we use an inner for loop to create new nodes based on current pop node, and push those new nodes into our stack
 * So this process is like we pop a node, found the last number used in the path, then create new nodes, in each new node, we will insert 
 * a new number after the last number in the path and push it to the stack
 * 
 * Based on the leetcode running results, the speed is similar to sol1 and sol2
 * @author hpPlayer
 * @date Aug 23, 2015 2:58:58 PM
 */

public class p216_sol2 {
	public static void main(String[] args){
		for (List<Integer> list : new p216_sol2().combinationSum3(3, 7)){
			System.out.println(list);
		}
	}
	

    private class Node{
        List<Integer> path;
        int sum;
        int k;
        public Node(int sum, int k){
            this.sum = sum;
            this.k = k;
            this.path = new ArrayList<Integer>();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        Stack<Node> stack = new Stack<Node>();
        Node init = new Node(0, 0);
        stack.push(init);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        while(!stack.isEmpty()){
            Node temp = stack.pop();
            if(temp.k == k && temp.sum == n){
                 result.add(temp.path);
                 continue;
            }
            if(temp.k >= k || temp.sum >= n){
                continue;
            }
            int end = temp.path.isEmpty()? 0 : temp.path.get(temp.k-1);
            for(int i = end + 1; i < 10; i++){
            	if(temp.sum + i > n) break;
                Node newNode = new Node(temp.sum + i, temp.k + 1);
                newNode.path.addAll(temp.path);
                newNode.path.add(i);
                stack.push(newNode);
            }
        }
        
        return result;
    }
}
