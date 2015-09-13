import java.util.*;

/**
 * My sol1 is so smooth that I didn't realize inserting 1 in the front and right shift the array give use a bonus point
 * if we keep the index of each layer as the previous layer, and always 1 in the end, then we will calculate each value as 
 * index j's value = index j-1's value + index j's value
 * Thus after we change j's value, then the next j+1's value will be affected as j+1's previous value j's has been updated
 * To avoid such mistake, we can still insert the 1 in the end, but update the array backward, then we will always update j's value and keep
 * j-1's value same as before, thus we will not affect the next value j-1's value
 * 
 * Since we always insert 1 in the end which costs O(1) compare with insert 1 in the front costs O(n), it should be much faster than sol1
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 11:25:40 PM
 */
public class p119_sol2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        
        //our rowIndex is 0 based, so we have too let the loop goes rowIndex + 1 times
        for(int i = 0; i < rowIndex+1; i++){
            result.add(1);//here, we always change each list backward
            for(int j = i - 1; j > 0; j--){
                //we will not change the value at index 0, as it is always 1
                //i-1 is always the index before the last index, which is the end of non 1 value
                result.set(j, result.get(j) + result.get(j-1));//since we add 1 in the end, thus the index is not rightshifted, still 0 based 
            } 
        }
        
        return result;
    }
}
