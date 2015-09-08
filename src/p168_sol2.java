/**
 * The code below is short, but hard to understand
 * Let's explain here:
 * As discussed in sol1, we have XYZ = x * 26^2 + y * 26^1 + z * 26^0 for 26 hex
 * Our current numbers is 26 hex with 1 based, so the range become from 1-26, If we treat it as a normal 26 hex with 0 based, we can just minus
 * 1 from each values, so the range will become 0-25, which would be correct.
 * 
 * XYZ = x * 26^2 + y * 26^1 + z * 26^0
 * if we left shift by 1, then we can no longer worry about the corner case 26
 * XYZ - 1 = x * 26^2 + y * 26^1 + z * 26^0 - 1
 * then right shift y to right is just (XYZ - 1)/26 
 * 
 * Let's take some example:
 * 27 (AA):
 * for 27, we firstly get (27-1)%26 = 0, which is 'A', this is the last digit, we then get (27-1)/26 = 1, which will be passed to next loop
 * and get (1-1)/26 = 0, which is 'A', so our result would be AA
 *  
 * 52 (AZ):
 * for 52, we firstly get (52-1)%26 = 25, which is 'Z', this is the last digit, we then get (52-1)/26 = 1, which will be passed to next loop
 * and get (1-1)/26 = 0, which is 'A', so our result would be AZ
 * Here we use the trick of pass (n-1)/26 to next loop instead of n/26, if we use n/26 then we will get 2, which will be passed to next loop
 * and get (2-1)/26 = 1, which is 'B'.
 * 
 * This pass (n-1)/26 will save us from 1-based index problem, but is not easy to come up with!
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 10:35:27 PM
 */
public class p168_sol2 {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            //n-1 to convert index from 1-26 to 0-25, then we can get correct char by (n-1)%26 + 'A'
            sb.insert(0, (char) ((n-1)%26 + 'A'));
            //n-1 here means each digit is differed by multiples of 26 + 1
            n = (n-1)/26;
        }
        
        return sb.toString();
    }
}
