import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * BFS solution, the idea is differed from DFS solution.
 * In DFS, we will look each node one by one after the graph is built.
 * In BFS, we will look node layer by layer starting from nodes without incoming edges.
 * 
 * The algorithm below is pasted from wiki:

L ¡û Empty list that will contain the sorted elements
S ¡û Set of all nodes with no incoming edges
while S is non-empty do
    remove a node n from S
    add n to tail of L
    for each node m with an edge e from n to m do
        remove edge e from the graph
        if m has no other incoming edges then
            insert m into S
if graph has edges then
    return error (graph has at least one cycle)
else 
    return L (a topologically sorted order) 
    
 *We can find that we will scan nodes layer by layer.
 *After done one node in current layer, we will remove all its outgoing edges to next layer
 *After we done such process to all nodes in current layer, all nodes in next layer are supposed to have zero incoming edges since we are 
 *searching in topological order. If we some nodes in next layer still have incoming edges, then there be cycles in the graph
 *We only add nodes without incoming edges into our Queue
 *In this solution, we use the variable count to follow the BFS, at last if count is not same as our given numCourses, that means some nodes 
 *are not inserted into our queue, in other words those nodes are in some cycles.
 *
 *degree[] is to track the number of incoming edges
 *hs is used to track the neighbor nodes
 * @author hpPlayer
 * @date Aug 30, 2015 7:56:00 PM
 */

public class p207_sol3 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        int[] degree = new int[numCourses];//degree array, record num of incoming edges
        
        Queue<Integer> que = new LinkedList<Integer>();
        int count = 0;
        
        for(int i = 0; i < numCourses; i++){
            hs.put(i, new LinkedList<Integer>());
        }
        
        for(int[] pair : prerequisites){
            hs.get(pair[0]).add(pair[1]);
            degree[pair[1]] ++;//we care about incoming edge, so it is the degree of pair[1] ++
        }
        
        //start our BFS search
        
        for(int i = 0; i < numCourses; i++){
            if (degree[i] == 0){
                que.offer(i);//we only add node without incoming edges into the queue
            }
        }
        
        while(!que.isEmpty()){
            int curr = que.poll();
            //check curr's adjacent edges
            for(int neighbor : hs.get(curr)){
            	//remove incoming edge from curr and see if this neighbor's degree become 0
                if (--degree[neighbor] == 0){ 
                    que.offer(neighbor);
                }
            }
            count ++;//we have checked one course
        }
        
        return count == numCourses;//if each node has been checked, and all incoming edges can be removed
    }
}
