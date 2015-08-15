/**
 * This is divide-and-conquer technique
 * each time we split current input into two halves, left and right
 * if max value from left == max value from right, we will return this value
 * if max value is differed from left and right, we will check the real occurrences of each element in the sub array
 * Since our majority appears more than (n/2) times, we can guarantee it will win this competition
 * 
 * The runtime complexity, T(n) = T(n/2) + 2n = O(n log n)
 * @author hpPlayer
 * @date Aug 14, 2015 11:47:26 PM
 */
public class p169_sol3 {
	public static void main(String[] args){
		int nums[] ={3, 2,3};
		System.out.println(new p169_sol3().majorityElement(nums));
		
	}
    public int majorityElement(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }
    
    public int divide(int[] nums, int left, int right){
    	if(left == right) return nums[left];
    	int mid = right + ((left -right) >> 1);//dont forget () around bit shift
    	//why must left: left, mid, right: mid+1, right?
    	//since mid = (left+right)/2, suppose now left = 0, right = 1
    	//then mid = 0, if we return left: 0, -1 (error code, right < left), right 0, 1 (stay same, infinite loop)
    	//we definitly we get wrong results
    	int leftM = divide(nums, left, mid);
    	int rightM = divide(nums, mid+1, right);
    	if(leftM == rightM) return leftM;
    	int countLeft = 0, countRight =0;
    	for(int i = left; i <= mid; i++){
    		if(nums[i] == leftM) countLeft++;
    	}
    	for(int i = mid + 1; i<=right; i++){
    		if(nums[i] == rightM) countRight ++;
    	}
    	
    	return countLeft > countRight? leftM : rightM;
    }
}
