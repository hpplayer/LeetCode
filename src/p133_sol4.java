import java.util.*;

/**
 * This is BFS solution, and it is very similar to sol3
 * We need create a copy of input node first, so that we can return it in the end, also we can have a copy of node that we can start to add copies
 * of its neighbors. The logic and flow is very similar to sol3, but here we use queue, which means we will deal with neighbors of all nodes in
 * same distance, while sol3 will finish neighbors from different distance one node, then go to next node in same distance.
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 9:51:09 PM
 */

public class p133_sol4 {
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return node;
        Queue<UndirectedGraphNode> que = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hs = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        //create root, where we can start copy graph, then return
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        que.offer(node);
        hs.put(node, root);
        
        while(!que.isEmpty()){
            UndirectedGraphNode curr = que.poll();
            UndirectedGraphNode copy = hs.get(curr);
            for(UndirectedGraphNode temp : curr.neighbors){
                if(!hs.containsKey(temp)){
                    UndirectedGraphNode newNode = new UndirectedGraphNode(temp.label);
                    hs.put(temp, newNode);
                    copy.neighbors.add(newNode);
                    que.offer(temp);//visit it later
                }else{
                    copy.neighbors.add(hs.get(temp));
                }
            }
        }
        
        return root;
    }
}
