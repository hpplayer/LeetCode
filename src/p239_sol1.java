import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
/*
Sliding Window Maximum 

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ¡Ü k ¡Ü input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
		
Hint:

1. How about using a data structure such as deque (double-ended queue)?
2. The queue size need not be the same as the window¡¯s size.
3. Remove redundant elements and the queue should store only elements that need to be considered.
*/

/**
 * This is my original AC solution without help
 * I used maxHeap implemented with PriorityQueue
 * I create a maxHeap for the first k elements, fill the result array with the top max value
 * Then move the window, remove the first element in the windows from heap and add the new element in the window to the heap
 * Repeat above steps until our result array get filled up.
 * 
 * Remark:
 * 1) I observed there will be len - k + 1 windows in the array, so we can create a result array with such length  
 * 2) Remove(object) in maxHeap is linear time
 * But add and remove(head) in maxHeap is O(log(n)) time
 * So the total running time should be the combination of them and may exceed O(n)
 * But fortunately it got accepted.
 * 
 * More fantastic solution will come tomorrow!
 * 
 * Update:
 * sol2 is updated, which uses a fantastic deque solution with O(n) time complexity
 * sol3 is the python version of sol2
 * @author hpPlayer
 * @date Aug 10, 2015 1:08:23 AM
 */
public class p239_sol1 {
	public static void main(String[] args){
		int[] nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		
		System.out.println(Arrays.toString(new p239_sol1().maxSlidingWindow(nums, k)));
	}
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if (nums.length == 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        
        PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
     
        for(int i = 0; i < k; i++){
            maxheap.add(nums[i]);
        }
        
        result[0] = maxheap.peek();
        for(int i = 1; i < result.length; i++){
            maxheap.remove(nums[i-1]);
            maxheap.add(nums[k-1+i]);
            result[i] = maxheap.peek();
        }
        
        return result;
    }
}
