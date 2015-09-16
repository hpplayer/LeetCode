/**
 * Similar idea as sol2, but implemented in recursive way.
 * The idea is similar to sol2, where we use binary search to find the local peak for last half array.
 * We compare nums[mid] with nums[mid+1], if smaller, then we are in uphill and skip mid, if larger, then we are in downhill and search left part
 * including the mid
 * 
 * @author hpPlayer
 * @date Sep 15, 2015 6:49:55 PM
 */
public class p162_sol3 {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1,  nums);
    }
    
    public int binarySearch(int start, int end, int[] nums){
        if(start == end) return start;
        if(start > end) return -1;//we will stop when start == end, so it will not happen in reality
        int mid = start + (end - start)/2;
        
        //if we are in downhill
        if(nums[mid] > nums[mid+1]){
            //mid may be the peak, so we keep mid, search left part for peak
            return binarySearch(start, mid, nums);
        }else{
            //if we are in uphill, mid will be no way the peak, so we skip peak and search right part
            return binarySearch(mid+1, end, nums);
        }
    }
}
