/**
 * Another solution that may be easy to understand
 * ones, two, threes represent if one value appear one, two or three times
 *    4     4    4    7
 *    100, 100, 100, 111
 * 3: 000, 000, 100, 000
 * 2: 000, 100, 000, 000
 * 1: 100, 000, 000, 111
 * Details see code below
 * 
 * Or see singleNumber2() for more explanation
 * @author hpPlayer
 * @date Apr 5, 2015 4:12:11 PM
 */
public class p137_sol4 {
	public int singleNumber(int[] A) {
		int ones = 0, twos = 0, threes = 0;
		for (int i : A) {
			//threes & (~i), reset 1 to 0, if we our stored threes value has 1 in the place that new incoming input also has 1 in that place
			//(twos & i), if we encounter a value third times
			threes = (threes & (~i)) | (twos & i);
			//twos & (~i), reset 1 to 0, if we our stored twos value has 1 in the place that new incoming input also has 1 in that place
			//(ones & i), if we encounter a value second times
			twos = (twos & (~i)) | (ones & i);
			//ones ^ i, reset to 0, if that value already stored in ones, otherwise store that value
			//~threes, if we have stored the value in threes(encounter three times), then don't update it
			ones = (ones ^ i) & (~threes);//if we still use form like ones & (~i), then ones will never be updated as long as ones = 0
		}
		return ones;
	}
	
	
    public int singleNumber2(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for(int i = 0; i < A.length; i++){
            threes = (threes & ~A[i]) | (twos & A[i]); //twos & A[i], store value if appear 3rd times, three & ~[A], reset to 0, if needed(like new input has 1 in the same places)
            twos = (twos & ~A[i]) | (ones&A[i]);//smilar to threes
            ones = (ones ^ A[i]) & (~threes);//if three = 000, then &111 if pick it self
        }
        return ones;
    }
}
