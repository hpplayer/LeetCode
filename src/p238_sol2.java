import java.util.Arrays;
/**
 * This beautiful solution solves the problem with two loops
 * The first loop calculates product of left part
 * The second loop calculates product of right part and add it into the result array.
 * 
 * In first loop, for each result[i], we can simply use the result from previous loop( which calculates the product from nums[0]
 * to nums[i-2] )and multiply nums[i-1]
 * In second loop, from right to left, since result[i+1] already contains nums[i], we can't use result from result[i+1]. Instead
 * we need uses a new variable temp to record the product
 * 
 * This algorithm is not hard to understand but is also not easy to come up with. Keep practice!
 * This algorithm uses constant space and solves the problem in O(n) time.
 * 
 * productExceptSelf() is my favorite solution towards this problem
 * productExceptSelf2() also uses temp in the first loop.
 * productExceptSelf3() combines these two loops together but needs Arrays.fill() to initialize array default value to 0. So, the 
 * speed is actually not improved much. Plus this combined one loop solution is not so easy to understand as the other is.
 * 
 * So productExceptSelf() is the best solution!
 * @author hpPlayer
 * @date Aug 9, 2015 11:33:06 PM
 */

public class p238_sol2 {
	public static void main(String[] args){
		int[] nums = {1,0};
		System.out.println(Arrays.toString(new p238_sol2().productExceptSelf2(nums)));
	}
    
    
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        
        //inital case, index 0 will only get result from second loop from right to left, so here we can just set it to 1 to help the 
        //loop  from left to right
        result[0] = 1;
        //first loop, from left to right, prev part
        for(int i = 1; i < nums.length; i++){
            result[i] = result[i-1] * nums[i-1];
        }
        
        //second loop, from right to left, next part
        //until here, result[i+1] already contain value from num[i], so we can't simply use result = result[i+1] * num[i-1]
        //like we did for first loop. Here we uses a temp to start a new product counter
        int temp = 1;
        for(int i = nums.length - 2; i >= 0; i--){
        	temp *= nums[i+1];
            result[i] *= temp;
        }
        
        return result;
    }
    
	
    public int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        
        //inital case, index 0 will only get result from second loop from right to left, so here we can just set it to 1 to help the 
        //loop  from left to right
        int temp = 1;
        result[0] = 1;
        //first loop, from left to right, prev part
        for(int i = 0; i < nums.length; i++){
            temp *= nums[i-1];
            result[i] = temp;
        }
        
        //second loop, from right to left, next part
        temp = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            temp *= nums[i+1];
            result[i] *= temp;
        }
        
        return result;
    }
    
    public int[] productExceptSelf3(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, 1);
        //result[0] = 1;
        //result[len-1] = 1; // or use 
        int left = 1, right = 1;
        for(int i = 0; i < len; i++){
            result[i] *= left;
            result[len-1-i] *= right;
            left *= nums[i];
            right *= nums[len-1-i];
        }
        
        return result;
    }
}
