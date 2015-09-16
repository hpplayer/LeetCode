/**
 * A very smart binary search. Our goal is to find any of peaks. Well, peak is the place where front and after numbers will all smaller
 * than it. Also this problem assumes that nums[i] != nums[i+1]. So we can just use binary search to find the local peak. Our algorithm
 * will compare nums[mid] with nums[mid+1]. Why not nums[mid] with nums[mid-1]? That's because our calculation of mid will always takes
 * flooring, thus if by change the index is 0, we will get error. If we found nums[mid] > nums[mid+1], then mid may be the index of peak,
 * or some points below peak but above current end (downhill), so we will search the park between start and mid. Why not mid -1? Like I said,
 * mid may be the peak, if we let end = mid -1, then we will miss this peak. Thus this algorithm is a little differed from original binary
 * search, where we always move with mid + 1 or mid -1. By contrast, if nums[mid] < nums[mid+1], then we know mid is some point in uphill,
 * thus we will seach right part by start = mid + 1. Why we can move start like mid +1? because we are sure there are still points after
 * node that has a larger value, thus we can skip mid point. Since we always search on a half array, we will finally stop the search when
 * start meets end, well the ending point may not be the maximum point globally, but it will be definitely the peak point of last half
 * array, which is still a peak.
 * 
 * Binary search costs O(logN) which meets the requirements
 * 
 * @author hpPlayer
 * @date Sep 15, 2015 6:25:11 PM
 */
public class p162_sol2 {
	public static void main(String[] args){
		int[] nums = {1,3,1,4,1,5,1};
		System.out.println(findPeakElement(nums));
	}
    public static int findPeakElement(int[] nums) {
        int start = 0, end = nums.length -1;
        //normally, I will use start <= end, but since we need to compare mid and mid + 1, thus we don't
        //want the index be out of boundary
        while(start < end){
            int mid = start + (end - start)/2;
            if(nums[mid] < nums[mid+1]){
                start = mid + 1;
            }else{
                end = mid;//mid itself may be the peak, so we will keep mid if nums[mid] >= nums[mid+1]
            }
        }
        
        //we do not jump out of loop even we meet peak, where we will keep "end" on it, and make
        //start move close to end, we will keep doing this until "start" meets "end"
        //so we can either return start or end
        return start;
    }
}
