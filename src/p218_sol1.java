import java.util.Arrays;
import java.util.LinkedList;
/*
 * 
 * The Skyline Problem
 * 
 * 
* A city's skyline is the outer contour of the silhouette formed by all the buildings 
* in that city when viewed from a distance. Now suppose you are given the locations and 
* height of all the buildings as shown on a cityscape photo (Figure A), write a program 
* to output the skyline formed by these buildings collectively (Figure B).
* 
*  ^                                        ^                                                                   
*  |                                        |                                                                   
*  |                                        |                                                                   
*  |    +-----+                             |    O-----+                                                        
*  |    |     |                             |    |     |                                                        
*  |    |     |                             |    |     |                                                        
*  |    |  +--+------+                      |    |     O------+                                                 
*  |    |  |         |                      |    |            |                                                 
*  |  +-+--+----+    |   +------+           |  O-+            |   O------+                                      
*  |  |         |    |   |      |           |  |              |   |      |                                      
*  |  |         |    |   |    +-+--+        |  |              |   |      O--+                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  +--+---------+----+---+----+----+--->    +--+--------------O---+---------O--->                               
*  
*   https://leetcode.com/static/images/problemset/skyline1.jpg  
*   https://leetcode.com/static/images/problemset/skyline2.jpg  
* 
* The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
* where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
* and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 , and Ri - Li > 0. 
* You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
* 
* For instance, the dimensions of all buildings in Figure A are recorded as: 
*  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
* 
* The output is a list of "key points" (red dots in Figure B) in the format of 
* [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
* A key point is the left endpoint of a horizontal line segment. 
*
* Note that the last key point, where the rightmost building ends, is merely used to mark 
* the termination of the skyline, and always has zero height. Also, the ground in between 
* any two adjacent buildings should be considered part of the skyline contour.
* 
* For instance, the skyline in Figure B should be represented as:
*  [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
* 
* Notes:
* 
*  - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
*  - The input list is already sorted in ascending order by the left x position Li. 
*  - The output list must be sorted by the x position. 
*  - There must be no consecutive horizontal lines of equal height in the output skyline. 
*    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
*    the three lines of height 5 should be merged into one in the final output as such: 
*    [...[2 3], [4 5], [12 7], ...]
* 
*/


/**
 * the input is a matrix, so the given input may looks like:
 * [
 * [0, 1, 3],
 * [2, 3, 4],
 * ...
 * ]
 * i.e. each row is a building, first value is left end, second value is right end, third value is height 
 * 
 * the output is a matrix too(or List[array]), and it may looks like:
 * [
 * [1,2],
 * [3,4],
 * ..
 * ]
 * i.e. each row is the start point of one segment of skyline, first value is start point, second value is height
 * 
 * This is maybe one of the most hardest problems in LeetCode. Not only the problem description is long, but also it is hard 
 * to find a learned technique to solve it.
 * I didn't solve it by myself.
 * 
 * There are two ways to solve the problem
 * sol1 is the technique with divide-and-conquer
 * 
 * This is a divide-and-conquer solution, it may looks hard at first glance, but it is actually not so difficult to understand
 * To be more specific, this algorithm is very similar to merge-sort algorithm. What you do is firstly using recursive method 
 * to get the skyline of each edge or called building keypoint, then you will merge them to form your result.
 * Basic idea:
 * 1) Like merge sort, the first thing we do to the buildings is splitting from top to bottom.
 * here we always cut the input buildings in middle with left
 * and right pointers, we will recursively cut it until left pointer meets right pointer. Then we simply create the skyline for current
 * building. Each building has two keypoints i.e. the left and right edge.
 * 2) After splitting buildings, the next thing we do is merging. So how can we merge those buildings? Our merge will be mainly based on
 * x axis. Say now we have two Lists of buildings s1 and s2. Ex:
 * s1:(0,3), (4,0)  //(0,3) is the start edge of building (4,0) is the end edge of building
 * s2:(1,2), (4,0)  //(1,2) is the start edge of building (4,0) is the end edge of building
 * 
 * We firstly compare the x axis, we found s1 is smaller, so (0,3) comes to consideration and it is the first element in result
 * so we add (0,3) to result and remove (0,3) from s1
 * Then we continuously compare the x axis, we found s2 is smaller, so (1,2) comes to consideration, we compare height from previous s1
 * and current s2 height, we found s1's height is higher, so current edge should also has height 3. But this height is same to previous height
 * in result, that means we don't need add this building, we just skip it and remove (1,2) from s2
 * Then we have (4,0) left in both inputs, they have same x axis and same height, we simply add(4, 0) to result, and remove both of 
 * them, then we are done. However if we have following case like 
 * (4, 1)
 * (4, 2) 
 * 
 * then we should include (4,2) into our consideration and compare height 2 with last building in result to see if the height is different
 * If it is, then add (4,2) into our results
 * 
 * Now two input lists are empty, we simply return our result.
 * 
 * 3) Couple things that needs to be noticed
 * - Here we prefer use LinkedList data structure as we can simply retrieve/remove/add to the first and last (java implements it with 
 * double linkedlist).
 * - We need always compare the height of s1 and s2 even though we may have smaller x axis in one of them. That is because the previous
 * x axis in other input may have a higher height and still not end, so it will cover current building
 * - For each building, we need add two key points, one in the left and the other one in right with height 0
 * - It is like merge sort but change the way to compare value, so the total time complexity should still be O(nlogn)
 * 
 * 
 * sol2 is the sweep line solution with Heap, but it is easy to make error in that solution since it operators on whole array
 * also regarding the speed sol1 is stable and fast
 * 
 * So so1 is the best solution!
 * sol3 is python version of sol1
 * sol4 is python version of sol2, though the conversion is complete, the submission to LeetCode reports LTE error. This may because 
 * search, remove in heap is linear time, and we need remove() and heapify() on each right edge, so get LTE error.
 * Also in sol4, I used inner class, define complicate key function, etc.
 * sol5 is another python solution using heap
 * 
 * @author hpPlayer
 * @date Aug 12, 2015 12:21:33 AM
 */



public class p218_sol1 {
	public static void main(String[] args){
		int[] a = {1,3,1};
		int[] b = {1,3,2};
		int[] c = {1,3,3};
		//int[] c = {1,2,3};
		int[][] buildings = {a, b,c };
		for(int[] temp : new p218_sol1().getSkyline(buildings)){
			System.out.println(Arrays.toString(temp));
		}
	}
    public LinkedList<int[]> getSkyline(int[][] buildings) {
    	if(buildings.length == 0) return new LinkedList<int[]>();
        return mergeSort(buildings, 0, buildings.length-1);
    }
    
    public LinkedList<int[]> mergeSort(int[][] buildings, int left, int right){
    	//can still be split
    	if (left < right){
    		int mid = (left + right)/2;
    		/*
    		LinkedList<int[]> l1 = mergeSort(buildings, left, mid);
    		LinkedList<int[]> l2 = mergeSort(buildings, mid+1, right);
    		for(int[] temp: l1){
    			System.out.println("l1: " + Arrays.toString(temp));
    		}
    		for(int[] temp: l2){
    			System.out.println("l2: " + Arrays.toString(temp));
    		}
    		*/
    		//we must pair left and mid, mid+1 and right, otherwise we will get stackoverflow
    		//ex: left: 0, right: 1, mid: 0, if we pair left and mid (0,0) pair mid + 1 and right (0+1, 1) we will get our ideal result
    		//but if we pair left and mid-1 (0, -1) pair mid and right (0, 1), we will get infinite loop...
    		//so be careful!!!
    		return merge(mergeSort(buildings, left, mid), mergeSort(buildings, mid+1, right));
    	}else{//reach boundary case that we only have one building now, create skyline for this building
    		LinkedList<int[]> temp = new LinkedList<int[]>();
    		temp.add(new int[]{buildings[left][0], buildings[left][2]});//add key point (left end point)
    		temp.add(new int[]{buildings[left][1], 0});//add right end point with height 0
    		return temp;
    		}
    }
    
    public LinkedList<int[]> merge(LinkedList<int[]> s1,LinkedList<int[]> s2){
    	LinkedList<int[]> result = new LinkedList<int[]>();
    	//store current height of two skylines
    	int h1 = 0, h2 = 0;
    	//while two lists are not empty, we can compare then add to the result
    	while(s1.size() > 0 && s2.size() > 0){
    		int newH= 0, newX = 0;
    		//if s1's x axis if smaller
    		if(s1.getFirst()[0] < s2.getFirst()[0]){
    			//Notice: input is skyline, which has length 2 ([0] is index [1] is height)
    			newX = s1.getFirst()[0];
    			h1 = s1.getFirst()[1];//update h1
    			newH = Math.max(h1, h2);//compare old h2 and new h1
    			s1.removeFirst();
    		}else if (s1.getFirst()[0] > s2.getFirst()[0]){//if s1's x axis is larger
    			//Notice: input is skyline, which has length 2 ([0] is index [1] is height)
    			newX = s2.getFirst()[0];
    			h2 = s2.getFirst()[1];
    			newH = Math.max(h2, h1);//compare old h1 and new h2
    			s2.removeFirst();
    		}else{//axis are same from s1 and s2
    			//if we have multiple skyline that has same axis, then they will be combined here
    			//we will define the final height to be the highest one
    			newX = s1.getFirst()[0];//x can be either s1 or s2
    			h1 = s1.getFirst()[1];
    			h2 = s2.getFirst()[1];
    			newH = Math.max(h1, h2);
    			s1.removeFirst();
    			s2.removeFirst();
    		}
    		
    		if (result.isEmpty() || result.getLast()[1] != newH){
    			result.add(new int[]{newX, newH});
    		}
    	}
    	
    	//if s2 has come to end but s1 still have elements left
    	if(!s1.isEmpty()) result.addAll(s1);
    	//if s1 has come to end but s2 still have elements left
    	if(!s2.isEmpty()) result.addAll(s2);
    	
    	return result;
    }
}
