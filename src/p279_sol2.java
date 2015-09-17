import java.util.LinkedList;
import java.util.Queue;
/**
 * This is a BFS solution. Each number can be treated as a Node, the neighbor of each node is the node we can reach from current node
 * by just adding one perfect square. So in BFS, the level that we can meet our target value is the number of nodes we need to get it
 * We firstly push 0 to the queue, then iteratively add perfect squares to get neighbors until the sum > target value.
 * We will then search the neighbors of those neighbors again. There will be many duplicate cases like Node 1 can reach Node 5, by adding
 * perfect square 4, while Node 4 can reach Node 5 as well by adding perfect square 1. Can we just start search from perfect square that
 * is larger than Node.val? No, like Node 4 can reach Node 8, by adding perfect square 4, we have no other Nodes can reach it.
 * Since for each Node in queue, we will calculate perfect squares and search all its neighbors, we don't want repeat work. So we build
 * a visited array, if we have previously inserted this node into the queue i.e. we have done work on this Node before, then we just skip it
 * It will help us save a lot of time. Finally when we reach the target Node, we just return the level, and this is our answer!
 * 
 * @author hpPlayer
 * @date Sep 16, 2015 8:25:22 PM
 */

public class p279_sol2 {
	public static void main(String[] args){
		System.out.println(new p279_sol2().numSquares(7168));
	}
    public int numSquares(int n) {
        if(n <= 0) return 0;
        Queue<Integer> que = new LinkedList<Integer>();
        int level = 0;
        que.offer(0);
        //create a table to remove duplicate calculations
        //this table make sure each number is inserted into que only once, thus we will do the inner loop only once
        boolean visited[] = new boolean[n+1];
        
        while(!que.isEmpty()){
            int size = que.size();
            level ++;
            for(int i = 0; i < size; i++){
                int Node = que.poll();
                for(int j = Node; j*j + Node <= n; j++){
                    if(j*j + Node == n) return level;
                    if(visited[j*j+Node]) continue;
                    que.offer(Node + j*j);
                    visited[Node+j*j] = true;
                }
            }
        }
        
        return -1;
    }
}
