import java.util.Arrays;
/**
 * This problem, to me, is hard.
 * This simple solution is purely bit manipulation, which is not so familiar to me
 * 
 * the idea is like this:
 * since each number can only have two kinds of appearance 1 or 3,
 * and every integer is represented by 32 bit binary number finally,
 * so we can just count the 1 in ith bit, and %3, if the remainder is 1, then it means 
 * the single number has 1 bit here.
 * 
 * we use an 32 length array to record the count
 * 
 * This solution uses two loops.
 * First nested loop used to fill the array
 * Second loop used to create the result;
 * 
 * Remark:
 * Bit manipulation:
 * ((A[i]>>j) & 1) ==1, at first I wrote A[i]>>j == 1, it is not right &1 and ==1 is not same 
 * 
 * result |= (bitAry[j] % 3) << j, we need | operation, otherwise we will update whole result
 * 
 * Understanding this mechanism, we can actually combine these two loops, see sol2
 * @author hpPlayer
 * @date Apr 5, 2015 2:30:57 AM
 */

public class p137_sol1 {
	public static void main(String[] args){
		int[] ary = {2,2,3,2};
		System.out.println(singleNumber(ary));
	}
	 public static int singleNumber(int[] A) {
		int[] bitAry = new int[32];//32 bits for each number
		int result = 0;
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < bitAry.length; j++){
				//System.out.println(A[i] >> j);
				if(((A[i]>>j) & 1) ==1 ){//In bit manipulation, if we want to know the number we need first & 1 then == 1, not directly == 1
					bitAry[j]++;
				}
			}
		}
		
		for(int j = 0; j < bitAry.length; j++){
			result |= (bitAry[j] % 3) << j;
		}
		System.out.println(Arrays.toString(bitAry));
		 return result;
	}
}
