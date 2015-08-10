import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
/**
 * This is a clever deque solution.
 * The algorithm works in following way:
 * 1)we keep a deque to store the index in the input array, the index is following input order but may not consecutive
 * we only store usable and potential cadidate's index in the deque. What is that? when we scanning a new value in the input,
 * we will check the deque to see if there is any index in the deque that has corresponding value smaller than input, then we will
 * throw it away. Why? since nums[i] now is a better candidate who has larger value and closer index in current and subsequent windows
 * Throwing those smaller indexes will not affect our future work.
 * By doing this, our deque will maintain a descending order, and the first index in the deque will always has the largest value's index
 * in current window
 * 2) Why store index instead of values? Since it is possible our deque size will be smaller than window as we only keep better potential 
 * candidate. If we only keep the values, there is now way that we can know the index of the values that needs to be removed. So keeping 
 * the index will be more convient
 * 3) This algorithm runs in O(n) time and is a good example to implement Deque, nice solutiuon!
 * 
 * @author hpPlayer
 * @date Aug 10, 2015 4:12:23 PM
 */

public class p239_sol2 {
	public static void main(String[] args){
		int[] nums = {1, 3, 1, 2, 0, 5};
		int k = 3;
		System.out.println(Arrays.toString(new p239_sol2().maxSlidingWindow(nums, k)));
	}
	
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if (nums.length == 0 || k == 0) return new int[0];
    	//dequeue needs to store index, so we can look up index and value in nums, otherwise we will lose the information where the value is 
        Deque<Integer> deq = new ArrayDeque<Integer>();
        int[] result = new int[nums.length - k +1];
        
        for(int i = 0; i < nums.length; i++){
            if(!deq.isEmpty() && deq.peek() < i-k+1){//i have increased out of the window size
                deq.poll();
            }
            
            //disgard any previous inserted index that has value smaller than current value, since current value has larger value 
            //and closer index, there is no need to keep thouse worse previous values
            //Since our deque is from large to small and following the index, we should scan from tail
            //if nums[i] is same with current last element, then we can keep it in case we will use this number twice in future
            while(!deq.isEmpty() && nums[deq.peekLast()] < nums[i]){
                deq.pollLast();
            }
            
            //adding current index to the rightmost 
            deq.offer(i);
            
            if(i >= k-1){//if we reach enough window size, then fill result array
                result[i-k+1] = nums[deq.peek()];
            }
        }
        
        return result;
    }
}
