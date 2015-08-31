import java.util.LinkedList;
import java.util.List;

/*Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to 
 * finish all courses?
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So it is possible.
 * 
 *      2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic 
 *    concepts of Topological Sort. (https://class.coursera.org/algo-003/lecture/52)
 *
 *  - Topological sort could also be done via BFS. (http://en.wikipedia.org/wiki/Topological_sorting#Algorithms)
 * 
 */


/**
 * This problem can be solved with DFS and BFS. As described in the given hint, it is actually a topological sort problem.
 * A correct topological sort should have no loops. Here, if we found a loop in the sort, then we can report this course schedule 
 * cannot be done. Both BFS and DFS can be used to detect cycles in graph.
 * From my algorithm, I am familiar with using DFS to detect cycle, in which we will mark each node as visited after visiting this node, in
 * later DFS if we found the path direct us to a visited node, then there must be a loop. For BFS, we start from nodes without incoming edges
 * we will go layer by layer after remove all outgoing edges from above layer, after this process, if some nodes in current layer still have 
 * incoming edges, then those edges must coming from below edges. We are scanning our graph in topological order, so such case indicates there 
 * are loops in our graph. more details can be found in sol3
 * 
 * Sol1 is a DFS solution with external class.
 * So this solution is more like a graph representation.
 * 
 * Each node(course) has a list to record the path (sequence of prerequisites) that roots at this node.
 * In topological sort, the root is the node without incoming edge, which means no other nodes will depend on this node, so in our case
 * the root is the current node, and all nodes below it are its prerequisites!!!!!!!!!!!!!!!!!
 * Each node(course) also has a parameter to indicate the visited status of this node, this parameter is used to detect cycle
 * To speed up the search, we will memorize the search of each node, if we have previously visited this node and no cycles detected, then
 * we will immediately get the result.
 * The most important part in the node is the noCycle() method, which can be used to track its prerequisites and detect loops
 * 
 * For the main program, our main task is to build each node and add its prerequisites
 * The total number of nodes is given, which is "numCourses", the edges are also given by "prerequisites" matrix.
 * 
 * Sol1 is DFS solution with inner class
 * Sol2 is DFS solution without inner class
 * Sol3 is BFS solution 
 * 
 * All solutions are recommended
 * 
 * Remark:
 * 1) The representation of the edge is as below:
 * [
 * [0, 1],
 * [2, 3],
 * ...
 * ]
 * the matrix has multiple rows, each row has two columns
 * The first value here is the course number
 * The second value here is number of one of the course's prerequisites
 * One course may have multiple prerequisites
 * 
 * 2) From now on, I will stop update the python solutions since I need save time and my progress in learning python has reached
 * the bottleneck. I think translate Java to Python will not help break this bottleneck.
 * 
 * @author hpPlayer
 * @date Aug 30, 2015 6:30:05 PM
 */
public class p207_sol1 {
	private class Course{
		boolean Finished;
		boolean Visited;
		List<Course> pre = new LinkedList<Course>();
		public void add(Course course){
			pre.add(course);
		}
		
		public boolean noCycle(){
			if(Finished) return true;
			if(Visited) return false;//visit a visited course, loop found	
			
			Visited = true; //mark visited
			
			//check each course in pre list
			for(Course course : pre){
				if(!course.noCycle()) return false;
			}
			
			Finished = true;
			return true;
		}
	}
	
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courseList = new Course[numCourses];
        for(int i = 0; i < numCourses; i++){
        	courseList[i] = new Course();
        }
        
        for(int i = 0; i < prerequisites.length; i++){
        	//add pre course to current course
        	courseList[prerequisites[i][0]].add(courseList[prerequisites[i][1]]);
        }
        
        //check all courses
        for(Course course : courseList){
        	if(!course.noCycle()) return false;
        }
        return true;
    }
}
