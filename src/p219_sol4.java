import java.util.HashSet;
/**
 * This solution uses the observation that after i > k, we will remove the first element in the set out before we can check it
 * so the size of hs will always be maintained like slide window approach
 * 
 * It also uses the trick that if hs.add(n) return false, that means we already got duplicate value in this set, so we can return true
 * otherwise hs.add(n) will add n into the hashset
 * @author hpPlayer
 * @date Aug 7, 2015 8:45:07 PM
 */

public class p219_sol4 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	HashSet<Integer> hs = new HashSet<Integer>();
    	for(int i = 0; i < nums.length; i++){
    		if (i > k){//since i > k we need remove the first element before check current value 
    			hs.remove(nums[i-k-1]);//think this, the first i > k is when i = k + 1, and we should remove num[0] from set
    		}
    		if(!hs.add(nums[i])) return true;
    	}
    	return false;
    }
}
