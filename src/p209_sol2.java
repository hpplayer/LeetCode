/**
 * Finally I got understand the flow of this solution
 * This solution uses binary search technique to find the range, how?
 * Although the array itself is unsorted, the sum of array is sorted in increasing order due to only positive integers in array
 * Our algorithm is searching the first cumulative sum that is larger than sum[i] - s
 * To represent in the graph:
 * [    sum - s   |    s    ]  <--sum
 * We want find the minimum length of subarray that has cumulative sum >= s
 * In other words, the cumulative sum of part (sum - s) will have cumulative sum that is <= sum - s
 * We want to find the first cumulative sum that is greater than sum -s which will tell us the boundary of part (sum - s),
 * and thus we can calculate length of s by simply the boundary of sum part - the boundary of (sum - s) part
 * 
 * As described above, the cumulative sum is sorted in increasing order, so we can use binary search to find the boundary of 
 * (sum - s) part
 * 
 * Remark:
 * 1)Since our goal is to find the first cumulative sum that is greater than sum - s in the binary search, we need to be careful
 * when dealing with equal case, in other words, we need to skip all values <= sum - s, and find the first value > sum - s
 * Here we use left pointer to search such value, so when we face equal case, we will let left pointer move, thus in the end,
 * the left pointer should points to the value larger than sum -s. 
 * 2)Is it possible we will not find such value in the binary search?
 * No, that's impossible, since our binary search starts only when we face such a cumulative sum >= sum, so our search range will
 * include this cumulative sum which means at least we will return this cumulative sum if no previous sum meets the requirement
 * 3)We use binary search on each cumulative sum, so the running time should be O(nlogn)
 *  
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 12:10:40 AM
 */

public class p209_sol2 {
	public static void main(String[] args){
		int nums[] = {1,4};
		System.out.println(new p209_sol2().minSubArrayLen(5, nums));
	}
	
	   public int minSubArrayLen(int s, int[] nums) {
	        if (nums.length == 0) return 0;
	    	int sum[] = new int[nums.length];
	    	sum[0] = nums[0];
	        for(int i = 1; i < sum.length; i++){
	        	sum[i] = sum[i-1] + nums[i];
	        }
	        //System.out.println(Arrays.toString(sum));
	        int result = Integer.MAX_VALUE;
	        for(int i = 0; i < sum.length; i++){
	        	//we only search if current cumulative sum is greater than s
	        	if(sum[i] >= s){
	        		int index = binarySearch(0, i, sum[i] - s, sum);//index is the index of sum[]
	        		result = Math.min(result, i - index + 1);
	        		}
	        	}
	        
	      //actually, we can get avoid this check, since we know the total sum in the last cell of sum array
	      //so we know the no-solution case will not happen if the value in that cell is >= given sum
	        return result == Integer.MAX_VALUE? 0 : result; 
	        }
	        
	    
	    public int binarySearch(int left, int right, int target, int[] sum){
	    	while (left <= right){
	    		int mid = (left + right) /2;
	    		if(sum[mid] > target){
	    			right = mid -1;	
	    		}else{
	    			//we let left pointer move when sum[mid] == target, so our left pointer will finally points to the value > target
	    			left = mid + 1;
	    			
	    		}
	    	}

	    	return left;//left points to the target or the first value > target
	    }
    
    
}
