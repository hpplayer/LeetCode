import java.util.HashMap;

/**
 * Similar idea as sol1, but we simply store one index in the hashMap, which makes the code shorter 
 * @author hpPlayer
 * @date Aug 7, 2015 8:32:43 PM
 */
public class p219_sol2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();//key is value, value is index
        for(int i = 0; i < nums.length; i++){
            if(!hs.containsKey(nums[i])){
                hs.put(nums[i], i);
            }else{
                if (i - hs.get(nums[i]) <= k) return true;
                hs.put(nums[i], i);
            }
        }
        
        return false;
    }
}
