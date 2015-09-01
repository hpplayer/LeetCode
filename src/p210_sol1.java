import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Course Schedule II 
 * 
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses 
 * you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to 
 * finish all courses, return an empty array.
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So the correct course order is [0,1]
 * 
 *      4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
 * Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining 
 *    the basic concepts of Topological Sort.
 *
 *  - Topological sort could also be done via BFS.
 * 
 *               
 */


/**
 * So basically this problem is not hard if you have finished and understood Course Schedule 1(p207).
 * The main program is similar to p207.
 * This is the DFS solution with inner class
 * The key part is how to fill the result array.
 * Here we use a good tricky that using the global index
 * When we do DFS search on some nodes, we will let the search going until we reach the bottom.
 * Then we pass the value from bottom up, we will begin fill our result array, so the order will must follow the given requirement
 * Since our each node has its own prerequisite list, we search must be a complete search on each node
 * 
 * So here may be a question, what if several questions share a same prerequisite?
 * Since the problem requires us put prerequisites in front, and this is exactly how we fill the result array, so there should be 
 * no problem
 * 
 * @author hpPlayer
 * @date Aug 31, 2015 9:05:30 PM
 */

public class p210_sol1 {
	public static void main(String[] args){
		//int[] a = {0,1};
		int[] b = {1,0};
		int[][] matrix = {b};
		System.out.println(Arrays.toString(new p210_sol1().findOrder(2, matrix)));
	}
	public int index = 0;
    public class Course{
    	int num;
        boolean hasFinished;
        boolean isVisited;
        boolean isAdded;
        List<Course> pres = new ArrayList<Course>();
        void insert(Course pre){
            pres.add(pre);
        }
        
        boolean hasCycle(int[] result){
            if (hasFinished) return false;
            if (isVisited) return true;
            isVisited = true;
            for(Course pre : pres){
                if(pre.hasCycle(result)) return true;
            }
            result[index++] = num;//reach bottom, then we will start fill the array
            hasFinished = true;
            return false;
        }
        
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        int[] result = new int[numCourses];
        for(int i= 0; i < numCourses; i++){
            courses[i] = new Course();
            courses[i].num = i;
        }
        
        for(int[] pair : prerequisites){
            Course c1 = courses[pair[0]];
            Course c2 = courses[pair[1]];
            c1.insert(c2);
        }
        
        for(Course c : courses){
            if(c.hasCycle(result)) return new int[0];
        }
        

        
        return result;
    }
}
