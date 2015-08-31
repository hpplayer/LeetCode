import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This is another DFS solution, which does not use external class
 * But to achieve the performance of sol1, we use three structures.
 * Finished[] is used to speedup the search and skip previously visited node
 * visited[] is used to help track the path of each node and detect loops
 * hs is used to record the prerequisites of each node
 * 
 * Remark:
 * it is possible that some courses will not have prerequisites, so its hash value will be empty
 * In this case, we simply report no cycle in path rooted at this node (this is like single node case)
 * 
 * 
 * @author hpPlayer
 * @date Aug 30, 2015 7:51:38 PM
 */

public class p207_sol2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < prerequisites.length; i++){
            //if hs does not contain current course
            if(!hs.containsKey(prerequisites[i][0])) hs.put(prerequisites[i][0], new LinkedList<Integer>());
            //add pre to the pre list
            hs.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        
        boolean[] Finished = new boolean[numCourses];
        
        for(int i = 0; i < numCourses; i++){
            if(Finished[i]) continue;//if we have done this before
            if(hasCycle(hs, new boolean[numCourses], Finished, i)) return false;
        }
        
        return true;
    }
    
    //Finished[] is the course with no cycle, visited[] is the course we visited in current path
    public boolean hasCycle(HashMap<Integer, List<Integer>> hs, boolean[] visited, boolean[] Finished, int n){
        List<Integer> pres = hs.get(n);
        if(pres == null) return false;//no pres for this course, return false
        for(int i = 0; i < pres.size(); i++){
            if(visited[pres.get(i)]) return true;//cycle detected
            visited[pres.get(i)] = true;
            if (hasCycle(hs, visited, Finished, pres.get(i))){
                return true;
            }
            visited[pres.get(i)] = false;//reset after backtracing is done
        }
        
        Finished[n] = true;
        return false;
    }
}
