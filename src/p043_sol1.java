/*
Multiply Strings

Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/


/**
 * This is a pure math problem
 * We have to mimic the operations that we used to manually calculate the multiplication
 * I have tried to solve this problem on my own but did not succeed.
 * 
 * This sol1 solution uses a very clever trick to do the operation, its idea is based on following observations:
 * 1) the total digits of result will not exceed the sum of total digits of two inputs. Based on this fact, we can create
 * an array to store our result. Example: 99 * 99 < 99 * 100, only 99*100 can make up to 5 digits. So 99*99 will only up to 4 digits
 * 2) we can reverse the inputs so we can fill the array with reverse inputs which means the next index in array is acutally 
 * the previous index in the result. It will help us to deal with carries later
 * 3) we can firstly store raw multiplication result in the array, then deal with carry and mod
 * Example:
 *    99
 *  * 98
 *  is actually can be divided into three parts
 *  9*8 at index 0, (8*9 + 9*9) at index 1 and 9 * 8 at index 2
 * 4) Then we can deal with mod and carry
 * Each value in the array:
 * mod will give us the remaining value in this index, we will add this into result
 * divide will give us the carry value that should be pass to next index, we will pass this to next index
 * 5) Since we always get the digits from tail to head, we have to always insert the value in front of result
 * 6) we may have 0s in remaing part of array, whether because we may not use those extra indexes or may because one of 
 * our inputs is 0 so that we will only get 0 in the results. To deal with those situations, we have to check the result
 * and remove those unnecessary 0s
 * 
 * This solution is very clean and clever. Best solution!
 * @author hpPlayer
 * @date Aug 6, 2015 1:16:51 AM
 */
public class p043_sol1 {
	public static void main(String[] args){
		System.out.println(new p043_sol1().multiply("98", "9"));
	}
    public String multiply(String num1, String num2) {
    	//the final digitis of result will not exceed len(num1) * len(num2) ex:99 * 99, 999 * 999, etc.
    	int[] sum = new int[num1.length() + num2.length()];
    	StringBuilder result = new StringBuilder();
    	//reverse num1 and num2, so we can easily deliver carry to next digits which is actually previous digits in result
    	num1 = new StringBuilder(num1).reverse().toString();
    	num2 = new StringBuilder(num2).reverse().toString();
    	
    	/*
    	 * calculating sum of each digits in result. Put values of same index together like:
    	 *    9 9
    	 *  * 9 8
    	 *  sum[0] = 9 * 9
    	 *  sum[1] = 9* 8 + 9 * 9 
    	 *  sum[2] = 9 * 8
    	 *  we may leave last cell in array blank since we have not start to deal with carry
    	 */
    	for(int i = 0; i < num1.length(); i++){
    		for(int j = 0; j < num2.length(); j++){
    			sum[i+j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
    		}
    	}
    	

    	//start with the last digits, now we need to deal with carry
    	for(int i = 0; i < sum.length; i++){
    		int mod = sum[i]%10;
    		int carry = sum[i]/10;
    		if (i < sum.length -1 ) sum[i+1] += carry;
    		result.insert(0, mod);//insert front
    	}
    	
    	//in case we dont have values in last cell in array (0)
    	//acutally, if one input is 0, we will have all values in cells to be 0s, and we dont want to output like 0000,
    	//so we will remove 0s from head until tail - 1
    	while(result.charAt(0) == '0' && result.length() > 1){
    		result.deleteCharAt(0);
    	}
    	
    	return result.toString();
    }
}
