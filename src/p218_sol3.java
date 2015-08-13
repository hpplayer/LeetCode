import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Ok, this is another solution that using heap
 * But differed from sol2 where we sort all edges, then scan edges, then use linear time to remove edges, which costs LTE in python
 * version, here we only use pop() and push() of heap.
 * 
 * What wo do is using a heap to keep live buildings. What is that? That means our buildings in the heap will always be covered by
 * some taller buildings, if we reach a gap where a new building start after the live buildings end, all those alive building will be dead
 * at least those who has shorter length than current highest building will die. We check our que, and kick out all buildings whose 
 * end point is before our current highest building's end point(which will also be kicked out at the same time)
 * 
 * Then we will search our second highest building under cover among those buildings in heap(our new building will be added to the heap
 * either when heap is cleared up or when some of our alive heap which extend beyond new building has come to que's top)
 * 
 * After that, we will add our new building into heap, actually that means add all buildings that start from here
 * 
 * Every time we done a clean-up or add-in, we will check new que's top and compare with the height of last building in result which 
 * is newly added, if not same, we found a new keypoint, add it into our result
 * 
 * If we found a tie between current building's height and que.top's height, then we need add it into our que instead of removing 
 * que's top. That is because by doing that we avoid the case that clean-up may add a new result if found there is no longer highest 
 * height in the que, but actually we have which is our current building
 * 
 * This algorithms scan all buildings, and only doing pop() and push() to heap which costs O(logn) in each scan
 * So the total running time is guaranteed to be O(nlogn) which is better than sol2
 * @author hpPlayer
 * @date Aug 13, 2015 1:16:15 AM
 */
public class p218_sol3 {
	public static void main(String[] args){
		int[] a = {1,3,1};
		int[] b = {1,3,2};
		int[] c = {1,3,3};
		//int[] c = {1,2,3};
		int[][] buildings = {a, b,c };
		for(int[] temp : new p218_sol3().getSkyline(buildings)){
			System.out.println(Arrays.toString(temp));
		}
	}
	   public static class Edge{
	        int x;//means end point
	        int h;
	        public Edge(int x, int h){
	            this.x = x;
	            this.h = h;
	        }
	    }
	    public List<int[]> getSkyline(int[][] buildings) {
	       List<int[]> result = new LinkedList<int[]>();
	       int i = 0, len = buildings.length, x = 0;

	       PriorityQueue<Edge> pq = new PriorityQueue<Edge>(10,
	    	   new Comparator<Edge>(){
	           public int compare(Edge e1, Edge e2){
	               //first compare height
	               if(e1.h != e2.h){
	                   //descending order
	                   return e2.h - e1.h;
	               }else{
	                   //ascending order
	                   return e1.x - e2.x;
	               }
	           }
	    	   });
	       //even if i reach len, we may still have buildings left in que. No new buildings will be added, but we can pop old buildings
	       while (i < len || !pq.isEmpty()){
	           //when pq is empty or current building's start x is before current highest building's end x, we can add house to our que
	           if(pq.isEmpty() || ((i < len ) && buildings[i][0] <= pq.peek().x )){
	               x = buildings[i][0];//x updated as the start point of current building
	               //add all buildings start at this x, even if these building may not be taller current as we may use them in future
	               while (i < len && buildings[i][0] == x){
	                   pq.add(new Edge(buildings[i][1], buildings[i][2]));
	                   i++;
	               }
	           }else{//current building's start x is after current highest building's end x, we need clean que
	               x = pq.peek().x;//x updated as the end point of highest buildings' end point(new keynote)
	               //since currnet highest building will be removed we have to remove all buildings that is covered by this highest building
	               //but we will leave lower building if that are longer than highest building, and the highest building among those buildings
	               //will become our next heap.top()
	               while (!pq.isEmpty() && pq.peek().x <= x){//when building's end point is before highest building
	                   pq.poll();
	               }
	           }
	           
	           //check pq.top() and update result if necessary
	           if(!pq.isEmpty()){
	               int height = pq.peek().h;
	               if(result.isEmpty() || result.get(result.size() -1)[1] != height){
	                   //height is the highest height in current x 
	                   result.add(new int[]{x, height});
	               }
	           }else{//que empty, we reach a gap or tail, add height = 0
	        	   result.add(new int[]{x, 0});
	           }
	       }
	       
	       return result;
	    }
}
