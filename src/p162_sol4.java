/**
 * A very smart linear algorithm.
 * We search peak by finding the first descending index. if none is descending, then the last index will be the peak
 * @author hpPlayer
 * @date Sep 15, 2015 6:54:55 PM
 */
public class p162_sol4 {
    public int findPeakElement(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            //search each index i- 1, if i is smaller than i-1, return i-1 immediately
            //which means i-1 is peak and we start downhill
            if(nums[i] < nums[i-1]) return i-1;
        }
        
        //if all previous index are asecending, then the last index will be peak
        return nums.length -1;
    }
}
