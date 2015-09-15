import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Here we use a similar idea as sol1, each DFS() will either return a copy of node or create a copy of node.
 * To avoid loop and unnecessary redundant nodes, HashMap needs to be used
 * 
 * Start with root node, this is initial case, so definitely we don't have its copy in HashMap, then we scan all neighbors of root
 * and do DFS on each node to get copies. We will recursively call DFS() on each neighbor and get copies of them. So this is DFS solution
 * To avoid create unnecessary copy of current Node, we should put current Node in HashMap before scanning its neighbors
 * After our DFS has done, we will return updated copy of root node as our answer
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 9:02:29 PM
 */
public class p133_sol2 {
	
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
        return DFS(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
    }
    
    public UndirectedGraphNode DFS(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> hs){
        //if we have done DFS on this node before, so we already saved a copy of this node in hs
        //this copy may not be completed if it is a self-cycle and has remaining unvisited neighbors
        //but the object is same, so it will be updated automatically in HashMap
        if(hs.containsKey(node)){
            return hs.get(node);
        }
        
        ///create a new copy node
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        
        //put node into HashMap, so we will not create an extra copy of this node
        hs.put(node, newNode);
        
        //do same thing to all its children
        for(UndirectedGraphNode temp : node.neighbors){
            //we try to get a copy from temp, either by creating a new one or retrieve an old one
            newNode.neighbors.add(DFS(temp, hs));
        }
        
        return newNode;//return old copy
    }
}
