import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * This is my original AC solution without help.
 * This problem should be easier with BFS since we search layer by layer in BFS while in DFS we search node by node
 * And this search pattern is exactly how we fill the result array. We want fill the array layer by layer.
 * But the problem is in BFS our search starts with nodes without incoming edges while the problem requires us put such high-level 
 * course in the end of array. So in our BFS search, we have to fill the array backward
 * 
 * The main program is similar to the BFS version in p207, except now we add the feature to fill the array
 * 
 * @author hpPlayer
 * @date Aug 31, 2015 9:17:06 PM
 */

public class p210_sol2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int[] depth = new int[numCourses];
        Queue<Integer> que = new LinkedList<Integer>();
        HashMap<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < numCourses; i++){
            hs.put(i, new ArrayList<Integer>());
        }
        for(int[] pair: prerequisites){
            depth[pair[1]] ++;//increase incoming edges
            hs.get(pair[0]).add(pair[1]);
        }
        
        for(int i = 0; i < depth.length; i++){
            if(depth[i] == 0){
                que.offer(i);
            }
        }
        
        int counter = numCourses - 1;
        while(!que.isEmpty()){
            int c = que.poll();
            result[counter] = c;
            counter--;
            for(int neighbor : hs.get(c)){
                depth[neighbor] --;
                if(depth[neighbor] == 0){
                    que.offer(neighbor);
                }
            }
        }
        if(counter != -1) return new int[0];
        return result;
    }
}
