import java.util.*;
/**
 * This is iterative solution. Well I think it is a little tricky and needs more attention.
 * First of all, we need manually make a copy of input node, so that we can return this copy after we are done with clone
 * Then, in sol2, the recursive solution, each DFS is either retrieve a created copy or create a new copy. But we need notice 
 * why we are doing this? Because we want to insert new neighbors into our start node. So similar, here our start node is root/node,
 * we want to check all node's neighbors to see if we need create a new copy or get an old copy. Either way, we will insert a node 
 * into root.neighbors. By understanding this logic, we have overcome the biggest challenges in this problem. If the neighbor is created 
 * before and stored in hashMap, then we just retrieve it and attach it to our root, if not, then we create a new node, store it to HashMap
 * push it to stack, so we can further visit neighbors of this new node, finally we will also attach this node node to our root
 * 
 * After stack is empty, it means we have visited all nodes in graph, then we simply return root as our solution
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 9:37:46 PM
 */

public class p133_sol3 {
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hs = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
        //build the node copy, which will be the root of copy graph
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        stack.push(node);
        hs.put(node, root);
        
        while(!stack.isEmpty()){
            UndirectedGraphNode curr = stack.pop();
            //we guarantee each curr has its copy in hs, since we have inserted all copies before start next round
            UndirectedGraphNode copy = hs.get(curr);
            //we have copy the curr Node, now we need copy its neighbors
            for(UndirectedGraphNode temp : curr.neighbors){
                //if we have not got the copy of temp, which means we have not visited temp
                if(!hs.containsKey(temp)){
                    //create a new copy
                    UndirectedGraphNode newNode = new UndirectedGraphNode(temp.label);
                    hs.put(temp, newNode);//put copy in hs
                    copy.neighbors.add(newNode);//add to its neighbors
                    stack.push(temp);//visit this temp later
                }else{
                    //if we already got a copy, then retrieve it and insert into neighbors
                    copy.neighbors.add(hs.get(temp));
                }
            }
        }
        
        
        return root;
    }
}
