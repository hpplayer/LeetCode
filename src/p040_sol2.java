import java.util.*;
/**
 * Iterative way 
 * 
 * We need a wrapper class which contains index, sum and current path,
 * so that by using this index and path we can add next node if it can help us get target
 * Basically, in each loop, we first pop a node from stack and check if its sum == target
 * if yes, then add current path to result, if no, since our array is sorted at first, so no later nodes can help us get to the target
 * so we just continue the loop(we may have several loops, we can break loop, but we can continue the loop)
 * 
 * Since each node can only be used once, so when we add next node, the index should be current Node's index + 1
 * then create a new Node and add it to the stack, so each combination of subset can be added to the stack
 * 
 * Remark:
 * I listed two versions here.
 * combination3() add the first node to the stack(index: 0, path:nu[1]), so each later path must start with first node
 * in order to list all kinds of paths, we have to repeat insert root node whenever stack is empty.
 * 
 * But we can use a trick here.
 * combination2() add a dummy node to the stack(index: -1, path = 0), so each later path can start with all nodes after dummy node 
 * when in the for loop insides the while loop. This trick can make our code shorter by avoiding add new root nodes in an extra if block
 * in combiantion3()
 * 
 * @author hpPlayer
 * @date Apr 10, 2015 11:48:27 AM
 */

public class p040_sol2 {
	public static void main(String[] args) {
		//int[] candidates = {10,1,2,7,6,1,5};
		int[] candidates = {1,1};
		List<List<Integer>> result = combinationSum2(candidates,1);
		for (List<Integer> ary : result) {
			System.out.println(ary);
		}

	}
	
	  public static class Node{
	        List<Integer> Path;
	        int index;
	        int sum;
	        public Node(int index, int sum){
	            this.sum = sum;
	            this.index = index;
	            Path = new ArrayList<Integer>();
	        }
	    }
	    public static List<List<Integer>> combinationSum2(int[] num, int target) {
	    	HashSet<List<Integer>> hs = new HashSet<List<Integer>>();//since the problem says no duplicates path, so we need a hashset
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(num == null || num.length == 0) return result;
	        Arrays.sort(num);
	        Stack<Node> stack = new Stack<Node>();
	        Node startNode = new Node(-1, 0);
	        //startNode.Path.add(num[0]);
	        stack.push(startNode);
	        while(!stack.isEmpty()){
	            Node temp = stack.pop();
	            /*
	            if(stack.isEmpty() && temp.index < num.length -1){//lets see if we can add new startNode after stack is empty
	                if(num[temp.index+1] <= target){
	                    Node newStartNode = new Node(temp.index+1, num[temp.index+1]);//start as first node in the path
	                    newStartNode.Path.add(num[temp.index+1]);
		                stack.push(newStartNode);
	                }
	            }
	            */
	            if(temp.sum == target){
	            	//System.out.println("im here");
	            	if(!hs.contains(temp.Path)){
	            		hs.add(temp.Path);
		                result.add(temp.Path);	
	            	}
	            	 continue;
	            }
	            for(int i = temp.index+1; i < num.length; i++){
	                if(temp.sum+num[i] > target) break;
	                System.out.println(temp.Path);
	                Node newNode = new Node(i, temp.sum+num[i]);//start as first node in the path
	                newNode.Path.addAll(temp.Path);
	                newNode.Path.add(num[i]);
	                System.out.println(newNode.Path);
	                stack.push(newNode);
	            }
	        }
	        return result;
	    }
	    
	    public static List<List<Integer>> combinationSum3(int[] num, int target) {
	    	HashSet<List<Integer>> hs = new HashSet<List<Integer>>();//since the problem says no duplicates path, so we need a hashset
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(num == null || num.length == 0) return result;
	        Arrays.sort(num);
	        Stack<Node> stack = new Stack<Node>();
	        Node startNode = new Node(0, num[0]);
	        startNode.Path.add(num[0]);
	        stack.push(startNode);
	        while(!stack.isEmpty()){
	            Node temp = stack.pop();
	            if(stack.isEmpty() && temp.index < num.length -1){//lets see if we can add new startNode after stack is empty
	                if(num[temp.index+1] <= target){
	                    Node newStartNode = new Node(temp.index+1, num[temp.index+1]);//start as first node in the path
	                    newStartNode.Path.add(num[temp.index+1]);
		                stack.push(newStartNode);
	                }
	            }
	   
	            if(temp.sum == target){
	            	//System.out.println("im here");
	            	if(!hs.contains(temp.Path)){
	            		hs.add(temp.Path);
		                result.add(temp.Path);	
	            	}
	            }
	            for(int i = temp.index+1; i < num.length; i++){
	                if(temp.sum+num[i] > target) break;
	                System.out.println(temp.Path);
	                Node newNode = new Node(i, temp.sum+num[i]);//start as first node in the path
	                newNode.Path.addAll(temp.Path);
	                newNode.Path.add(num[i]);
	                System.out.println(newNode.Path);
	                stack.push(newNode);
	            }
	        }
	        return result;
	    }
	  
}
