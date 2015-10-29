import java.util.Arrays;
/**
 * This is radix sort solution. The main part is implementing radix sort.
 * Where we scan all values digit by digit, and use a count[] to record how many numbers are before a specific number i
 * Then we put input number with number i at this digit accordingly to our temp array, which stores all numbers sorted at current digit so far
 * Then we update original array, and do the same thing until whole array has been sorted on all digits
 * Then we just return the sorted array.
 * 
 * This algorithm will work if the input digit has constant length of digits, not like n digits, then we are good.
 * The time complexity is O(kn), where n is the length of digits
 * 
 * Details has been added below.
 * @author hpPlayer
 * @date Sep 16, 2015 1:53:42 AM
 */

public class p164_sol2 {
	public static void main(String[] args){
		//int[] a = {15252,16764,27963,7817,26155,20757,3478,22602,20404,6739,16790,10588,16521,6644,20880,15632,27078,25463,20124,15728,30042,16604,17223,4388,23646,32683,23688,12439,30630,3895,7926,22101,32406,21540,31799,3768,26679,21799,23740};
		int[] a = {21,12,312};
		//radix(a);
		System.out.println(Arrays.toString(a));
	}
    public int maximumGap(int[] nums) {
        radix(nums);
        
        int result = 0;
        for(int i = 1; i < nums.length; i++){
            result = Math.max(result, nums[i] - nums[i-1]);
        }
        
        return result;
    }
    
    public void radix(int[] nums){
        int[] newNums = new int[nums.length];//we need this array to help update nums digit by digit
        
        int exp = 1;//help stop the loop
        int max = 0;//get the max number in nums which can tell us where to stop
        for(int num : nums){
            max = Math.max(num, max);
        }
        
        while(max/exp >0){//while we still have digits left (most significant digits)
            int[] count = new int[10];//the array to sort each digit
            
            for(int i = 0; i < nums.length; i++){
                count[nums[i]/exp%10] ++;//count how many times one number appear
            }
            
            for(int i = 1; i < 10; i++){
                count[i] += count[i-1];//build index for each number, (rightshift i by nums  of i-1 in front)
            }
            
            //we have to update the array backward, why? Since the earlier the num get index, the higher 
            //index it will get, which means it is larger, so of course, we want put larger number with higher
            //index thus we want to visit them first
            for(int i = nums.length -1; i >= 0; i--){
                //assign value at index i to a new place. The new place is based on the index we updated in count
                //since index is 0 based, while our count start from 1, we have to let deduct index by 1 first
                //ex: count[0] = 1, which means we will place 0 in first place, i.e. index 0
                newNums[--count[nums[i]/exp%10]] = nums[i];
            }
            
            for(int i = 0; i < nums.length; i++){
                nums[i] = newNums[i];//update nums accordingly based on sort of current digit
            }
            
            exp *= 10;//finish current digit, move leftward
        }
        
        
    }
}
