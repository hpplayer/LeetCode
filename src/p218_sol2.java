import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * This is sweep line with max-heap algorithm
 * It adds value to result on-fly so we have to consider several boundary case
 * The basic idea is as following:
 * 1) we create a list that contains all edges
 * 2) then we sort edges based on this rule:
 * value of x axis -> smaller placed first
 * if same x axis:
 * 	if all left edges:
 * 		higher edge placed first, so we can add highest edge in this x into the queue first and avoid add other edges into the result
 * 		notice: we add current edge to result if current edge's height > que.top() before insertion
 * if all right edges:
 * 		lower edge placed first, so we can remove lowest edge in this x from the queue first and avoid add other edges into the result
 *		notice: we add que.top to result if current edge's height < que.top() after deletion
 * if one is right edge and the other one is left edge:
 *		left edge placed first, so we may avoid inserting unexpected values into result when heights are same
 * 3) We also create a heap so that we can know current highest height when scanning the edges
 * 4) We add values to our results when our current edge's height is differed from the que.top()
 * 
 * Remark:
 * Due to the implementation of heap in java, the deletion costs O(n), so the total running time should be in the range of 
 * O(nlogn) - O(n^2)
 * 
 * 
 * @author hpPlayer
 * @date Aug 12, 2015 9:29:44 PM
 */
public class p218_sol2 {
	public static void main(String[] args){
		/*
		int[] a = {2,13,10};
		int[] b = {10,17,25};
		int[] c = {12,20,14};
		*/
		int[] a = {1,2,1};
		int[] b = {1,2,2};
		int[] c = {1,2,3};
		//int[] c = {1,2,3};
		//int[][] buildings = {a, b,c };
		int[][] buildings = {{2,4, 70}, {3, 8, 30}, {6, 100, 41}, {7, 15, 70}, {10, 30, 102}, {15, 25, 76}, {60, 80, 91}, {70, 90, 72}, {85, 120, 59}};
		for(int[] temp : new p218_sol2().getSkyline(buildings)){
			System.out.println(Arrays.toString(temp));
		}
	}
    private class Edge{
        int x;
        int h;
        boolean isLeft;
        public Edge(int x, int h, boolean isLeft){
            this.x = x;
            this.h = h;
            this.isLeft = isLeft;
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if(buildings == null || buildings.length == 0) return result;
        
        //add edges to our priority queue
        LinkedList<Edge> edges = new LinkedList<Edge>();
        for(int[] building : buildings){
        	Edge leftEdge = new Edge(building[0], building[2], true);
        	Edge rightEdge = new Edge(building[1], building[2], false);
        	edges.add(leftEdge);
        	edges.add(rightEdge);
       }
       Collections.sort(edges, new Comparator<Edge>(){
			public int compare(Edge e1 , Edge e2) {
				if(e1.x != e2.x){
					//do we need switch e1 and e2? if yes return positive, if no return negative
					//here it's ascending
					return e1.x - e2.x;
				}else{
					//if same x axis, do we need switch e1 and e2? if yes return positive, if no return negative
					//here it's descending
					//for left edges, we want add the higher edge as faster as possible, so we can see the difference in height immediately
					//also by adding the highest left in the first place, we can guarantee in later height queue insertion, we will insert
					//the highest left edge into queue in the first place, so avoid inserting other heights into our result since all other
					//heights in same x will always shorter < que.top
					if(e1.isLeft && e2.isLeft) {
						return e2.h - e1.h; 
					}
					//her it's ascending
					//for right edges, we want add the higher edge as slower as possible,, so we can remove the difference in height slowly
					//also by adding highest right in last place, we can guarantee in later queue deletion, we will remove the highest right
					//in last place so avoid the case we add other right heights in the same x into out result
					//since highest right is in last place otherwise if we remove highest right firstly,
					//then current edge's height will > que.top, then we will add que.top to our result
					if(!e1.isLeft && !e2.isLeft){
						return e1.h - e2.h;
					}

					//one of e1,e2 is left, and the other is right
					//if e1 is left, then we should keep e1 front, otherwise keep e2 behind
					//this is important, why? if we have two buildings that are consecutive like 
					//[0,2,3] [2,5,3], if we don't consider the order of left and right, then 
					//our order is left, right, left, right, which means we will treat second left as we first seen it, and add it 
					//into our result
					//what we do is we should avoid that by simply add left twice before remove it, so that the first right will not
					//affect the way we see it
					return e1.isLeft? -1 : 1;
					
					//return e2.h-e1.h;
				}
			}

    	});
       
       //record current height with descending order
       PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, Collections.reverseOrder());
       
       for(Edge edge : edges){
    	 //System.out.println(edge.x + " " + edge.h + " " + edge.isLeft);
    	  // System.out.println(pq);
    	   if(edge.isLeft){//if it is left, then push it into the queue
    		   if(pq.isEmpty() || edge.h > pq.peek()){
    			   result.add(new int[]{edge.x, edge.h});
    		   }
    		   pq.add(edge.h);
    	   }else{//if it is right, then remove this building from 
    		   pq.remove(edge.h);
    		   System.out.println(pq);
    		   if(pq.isEmpty()){//we face a space or reach the tail
    			   result.add(new int[]{edge.x, 0});
    		   }
    		 //we removed the highest edge, so we need add the new lower key point, which is the 
			 //second highest height there 
    		   if(!pq.isEmpty() && edge.h > pq.peek()){
    			   result.add(new int[]{edge.x, pq.peek()});
    		   }
    	   }
       }
       return result;
    }
}
