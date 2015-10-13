import java.util.HashMap;
import java.util.Map;
/**
 * This is a very brilliant solution, which uses the concept of bucket
 * The algorithm goes this way:
 * 1) we create a hashMap, key in hashmap is the bucket number, the value in hashmap is the value in this bucket 
 * If the bucket size is 5, then we may contain 5 values but if we found any two values appears in same bucket that means we found
 * the duplicate pair. So as long as our loop continue, we should have only one value in each bucket in the hashmap
 * 2) we may also have duplicate value in next or previous bucket like bucket size is 5 and distance is also 5, then the total range 
 * should be -4 in previous bucket 5 in current bucket and +4 in next bucket. Thus we may also got same values in previous and next
 * buckets but out of this -4,5,4 range, so for these two buckets we need check the value, but for current bucket the value is guaranteed
 * to be duplicate
 * 3) The key point is how to get the correct bucket size, and assigning values into those buckets. For this problem, this bucket size must
 * only be t or t + 1
 * if t+2, then one bucket may contain 2 numbers; if t-1, then bucket[i] and bucket[i+2] may contain a pair of number, whose distance is t
 * we choose t + 1, so that when we calculate the bucket number by n/(t+1) and avoid the case t = 0
 * 
 * Remark:
 * There are also several points that needs to be pay attention to:
 * 1) we need to be careful about postive and negative case, since same value with different sign may be assigned to same bucket
 * even they are not in t range:
 * like n = 3 and n = -3, t = 4, their value distance is 6, but when calculate the bucket number by n/(t+1) they are in same bucket[0]
 * Our solution here is convert each value to n = n-Int_MIN_VALUE
 * 2) for overflow problem, we must cast all int type to long type 
 * 3) for duplicate values, as I described in sol1:
 * 
 * Don't worry about the duplicates, because the problem asks us to find the pair that has at most t difference
 * so if we got duplicates:
 * i. if they are in the range k, then we should find them and return true
 * ii.if they are not in the range k, then the previous duplicate will be removed after our window has pass that index
 * 
 * @author hpPlayer
 * @date Aug 8, 2015 12:30:09 AM
 */

public class p220_sol2 {
	public static void main(String[] args){
		/*
		int[] nums = {-3, 3};
		int k = 2;
		int t = 4;
		*/
		int[] nums = {1, 3, 1};
		int k = 1;
		int t = 1;
		System.out.println(new p220_sol2().containsNearbyAlmostDuplicate(nums, k, t));
	}
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		Map<Long, Long> hs = new HashMap<Long, Long>();
		//the difference between value could be smaller than 0, the difference between index could be smaller than 1 (which is itself)
		if(t < 0 || k < 1) return false;
		for(int i = 0; i < nums.length; i++){
			if (i > k){
				//my wrong code, since num - Int_min will overflow, so cast long after overflow would not help
				//long lastBucket = ((long) (nums[i-k-1] - Integer.MIN_VALUE)) / ((long) t + 1);
				//below is correct code, we cast one to long, and the caluculation result will be long
				long lastBucket = ((long) nums[i - k -1 ] - Integer.MIN_VALUE) / ((long) t + 1);
				hs.remove(lastBucket);
			}
			
			long temp = (long) nums[i] - Integer.MIN_VALUE;//help convert every int to positive to avoid collision
			//current value / bucket size will give us the numbers of bucket before us i.e. current bucket number 
			long bucket = temp / ((long) t + 1);//+1 to avoid t he case t = 0 and make the divide error
			if(hs.containsKey(bucket) || (hs.containsKey(bucket -1) && hs.get(bucket - 1) + t >= temp)
				||( hs.containsKey(bucket + 1) && hs.get(bucket + 1 ) -t <= temp)){
				return true;
			}
			
			hs.put(bucket, temp);
		}
		
		return false;
	}
	
}
