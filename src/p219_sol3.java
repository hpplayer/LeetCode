import java.util.HashSet;

/**
 * This is the slide window approach
 * We use a pointer indicate the left bound of the window, and the moving pointer is the right bound of window
 * so the size of our set will always be maintained, any value in it is a legal value that is in range
 * If we cant match of current value, we need to remove the first value in the set and add current value into the set
 * So we will have a fix size window(set) 
 * 
 * 
 * Remark:
 * hs.remove(nums[left++])
 * can be translated to 
 * hs.remove(nums[left])
 * left ++
 * 
 * left ++, the value is evaluated before increments
 * ++ left, the value is evaluated after increments
 * 
 * also we must add current value into the set before we remove old value from set since we may got k = 0, which means 
 * we have to remove values when right = left = 0, if there is not value in the set yet, we will get error
 * @author hpPlayer
 * @date Aug 7, 2015 8:33:37 PM
 */
public class p219_sol3 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            if(hs.contains(nums[right])){
                return true;
            }else{
                hs.add(nums[right]);
                if(right-left == k){
//++i increments the number before the current expression is evaluted, whereas i++ increments the number after the expression is evaluated.
                    hs.remove(nums[left++]);
                }
            }
        }
        return false;
    }
}
