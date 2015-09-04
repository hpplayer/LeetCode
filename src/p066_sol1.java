/*
Plus One 

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

/**
 * This is my original AC solution without help.
 * Based on my understanding, the problem only requires us to calculate the values after plus one, that means our overflow 
 * will only have additional one bit. Unlike other cases like we may add 999, etc.
 * Then things become much easier 
 * 
 * The first thing comes to my mind is that how can we check if we will have an additional one in front?
 * My trick here is changing values in digits[], if we don't need additional one, then just return digit[]
 * Otherwise, we will create a new array, and copy values from digits[], finally add one in front
 * 
 * Remark:
 * when we are filling the result[], which has additional bit, we need to care about index. 
 * The index should all be + 1 for result since it has an additional bit in front
 * 
 * 
 * Although I believe my code work correctly, there are still several observations that I missed
 * Like: 
 * 1) when will us have the extra 1 in front? only when the digits[] is 99999
 * So actually, we don't need copy the array, instead we can simply create a new array with length of len + 1,
 * then simply assign first bit to 1, then we we are done.
 * 2) Also, if at some index, the value + 1 will not cause overflow, then we can simply stop there and exit loop immediately  
 * 
 * Sol2 is a such solution with good observations
 * 
 * Compare sol1 and sol2, sol2 is more clever and clean, so sol2 is more recommended
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 10:06:20 PM
 */
public class p066_sol1 {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int one = 1;
        for(int i = len - 1; i >= 0; i--){
            digits[i] += one;
            if(digits[i] == 10){
                digits[i] = 0;
            }else{
                one = 0;
            }
        }
        
        if(one != 0){
            int[] result = new int[len+ 1];
            for(int i = 0; i < len; i++){
                result[i+1] = digits[i];
            }
            result[0] = 1;
            return result;
        }
        
        return digits;
    }
}
