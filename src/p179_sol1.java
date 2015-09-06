import java.util.Arrays;
import java.util.Comparator;

/*
Largest Number 

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
*/


/**
 * This is my original AC solution without help
 * My first thought is to find some useful observations.
 * Then I found we can compare digit by digit, then sort the array, like radix sort
 * However, things are not as easy as I think it is.
 * For example:
 * 12 121, 
 * 128 12
 * If our inputs have different length, then how we deal with them? we may have to compare the digits in one input, or something more complex
 * Also the code would be lengthy.
 * 
 * So then, I recheck my code and find there is a easier way...
 * We can directly build two strings that one is i1 + i2, the other one is i2 + i1. We do the same thing that is checking each digit in 
 * two strings and decide whether we need to swap
 * 
 * Remark:
 * 1) Be careful about convert int to string. We can directly build string like i1 + i2 + "" since it will calculate sum first then convert.
 * The right way to do is i1 + "" + i2 ""
 * 2) we can use build-in function compareTo() to compare two strings, instead of manually compare two strings digit by digit
 * 3) the raw input is int[], array does not belong to collections, so we can't use Collections.sort(), also int is primitive type, so we can't
 * use Arrays.sort(). My solutions here convert int[] to integer[] then pass to the comparator
 * 
 * I checked solutions online, it seems people all use similar idea as mine, i.e. build two strings with two possible order, then compare them
 * and decide whether we need to do the swap
 * 
 * Sol2 uses the similar idea as so1, but with some optimizations
 * 
 * sol1 is my original solution, which is already a great solution
 * but sol2 applies several optimizations
 * 
 * So both sol1 and sol2 are recommended!
 * 
 * @author hpPlayer
 * @date Sep 6, 2015 4:53:00 PM
 */
public class p179_sol1 {
	public static void main(String[] args){
		int[] nums = {121,12};
		System.out.println(new p179_sol1().largestNumber(nums));
	}
	public String largestNumber(int[] nums) {
		Integer nums2[] = new Integer[nums.length];
		for(int i = 0; i < nums.length; i++){
			nums2[i] = nums[i];
		}
		Arrays.sort(nums2, new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				//don't use int1 + int2 + "", since it will automatically calculate int1 + int2, then convert to string
				String temp1 = int1 +"" + int2 + "";
				String temp2 = int2 +"" + int1 + "";
				
				return temp2.compareTo(temp1);
				/*
				for(int i = 0; i < temp1.length(); i++){
					if(temp1.charAt(i) < temp2.charAt(i)){
						return 1;
					}
					
					if(temp1.charAt(i) > temp2.charAt(i)){
						return -1;
					}
					
				}
				return 0;
				*/
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int num : nums2) {
			sb.append(num);
		}
		
		while(sb.length() > 1 && sb.charAt(0) == '0'){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
}
