public class p066_sol2 {
/**
 * This is a very quick solution with several good observations
 * The main idea is similar to sol1, that we scan the digits backward
 * Those observations include
 * 1) as long as we found a place in digits that can carry 1, then we are done, simply plus on in that index, and return digits
 * 2) if current place can't carry 1, it means the value is 9 in current place, so we just reset value to 1, and let loop continue
 * 3) if we still not return digits after loop is done, it means we need put the one in front. This case will only happen when our
 * digits is 99999, so we can simply assign a new array with len + 1, then put 1 in front. Then the array will automatically become
 * 100000, which is 99999 + 1
 * @param digits
 * @return
 */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >=0; i --){
            if(digits[i]  + 1<= 9){//we found a place to carry the 1
                digits[i]++;
                return digits;
            }else{//current place can't carry 1, we still need search front
                digits[i] = 0;//since we only plus one to the number, all overflow bit will be 0
            }
        }
        
        //if we reach here, it means we can't find a place in digits to carry the one, then
        //we have no other choices, but create a longer array and put 1 in front
        //this case will only happen when we have 999, so the new array must be 1 followed by several 0s
        int[] result = new int[len+1];
        result[0] = 1;
        return result; 
    }
}
