import java.util.*;

/*
Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/        
         

/**
 * This is my original AC solution without help.
 * The tricky part of this problem is how to get avoid of falling into self loop.
 * My final solution is to use a HashMap, if we have met a node before, we will not call DFS() again, instead, we just retrieve the node from
 * hashMap. Otherwise, we will create a copy of this new node, put it into the Map, and do DFS on it. Each DFS will check the neighbors array 
 * and try to create a new copy or retrieve an old copy from HashMap. We will insert those copies into the neighbors of input copy node
 * 
 * I searched online solutions and found HashMap is necessary in getting avoid of cycle.
 * A better recursive solution can be found in sol2
 * 
 * Remark:
 * 1) here I use the node label as HashKey, which may be incorrect, since we may have two different nodes but with same label
 * So better use Node as HashKey
 * 
 * Sol1 is my own recursive DFS solution
 * Sol2 is another recursive DFS solution with more clear logic
 * Sol3 is iterative DFS solution which is very beautiful
 * Sol4 is iterataive BFS solution which is very similar to sol3, but use queue instead
 * 
 * sol2, sol3 and sol4 are recommended, but of course my own solution sol1 is also a good solution
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 8:37:24 PM
 */
public class p133_sol1 {
	public static void main(String[] args){
		UndirectedGraphNode a = new UndirectedGraphNode(0);
		UndirectedGraphNode b = new UndirectedGraphNode(1);
		UndirectedGraphNode c = new UndirectedGraphNode(2);
		a.neighbors.add(b);
		a.neighbors.add(c);
		b.neighbors.add(c);
		c.neighbors.add(c);
		
		UndirectedGraphNode root = new p133_sol1().cloneGraph(a);
		System.out.println(root.neighbors);
	}
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
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        HashMap<Integer, UndirectedGraphNode> visited = new HashMap<Integer, UndirectedGraphNode>();
        visited.put(node.label, root);
        DFS(node,  root, visited);
        return root;
    }
    
    public void DFS(UndirectedGraphNode ori, UndirectedGraphNode copy, HashMap<Integer, UndirectedGraphNode> visited){
        
        for(UndirectedGraphNode temp : ori.neighbors){
            if(visited.containsKey(temp.label)){
                UndirectedGraphNode n = visited.get(temp.label);
                copy.neighbors.add(n);
            }else{
                UndirectedGraphNode n = new UndirectedGraphNode(temp.label);
                visited.put(temp.label, n);
                DFS(temp, n, visited);
                copy.neighbors.add(n);
            }
        }
    }
}
