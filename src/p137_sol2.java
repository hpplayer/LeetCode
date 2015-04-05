/**
 * Similar idea to sol1, but combine the loops together
 * Instead of using A[] as outer loop, we use bitAry[] as outer loop,
 * so each outer loop we can get ith digit of result
 * 
 * But sol1 and sol2 both uses extra space, 
 * If we want solve it without extra space, we need state machine, see sol3
 * @author hpPlayer
 * @date Apr 5, 2015 2:37:12 AM
 */
public class p137_sol2 {
    public int singleNumber(int[] A) {
        int bitAry[] = new int[32];//32 bit
        int result = 0;
        for(int i = 0; i < bitAry.length; i++){//get 1 bit of result 1 time
            for(int j = 0; j < A.length; j++){
                if((A[j]>>i & 1) == 1){//if at this index we get 1
                    bitAry[i] ++;
                }
            }
            result |= (bitAry[i] % 3) << i;//need | operation to update that bit, otherwsie we are updating whole result
        }
        return result;
    }
}
