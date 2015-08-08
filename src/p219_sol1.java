import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
Contains Duplicate II 

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that
nums[i] = nums[j] and the difference between i and j is at most k.
*/

/**
 * My original AC solution without help.
 * This problem if using hashMap will not be so hard
 * The key point is keeping an array of integers in the hashmap
 * the key for hashmap is the value
 * the value for hashmap is the list containing all indexes that have this value
 * When we get new value, we check our hashmap, if not contains this value just adding it,
 * if contains this value, we scan the list and compute the difference of indexes
 * 
 * But actually, we dont need to keep the list of indexes since if we found current duplicate value has a distance longer 
 * than previous index, then there is no way future indexes with same value will be in range with this previous index
 * so keeping one value is enough
 * 
 * sol2 uses this idea
 * sol3 uses the sliding window idea
 * sol4 uses the similar idea as window idea but without considering the left boundary
 * sol5 is the python version of sol2
 * sol6 is the python version of sol3
 * My fav sol is sol3 which uses the sliding window as it is easy to understand and won't occupy much space
 * @author hpPlayer
 * @date Aug 7, 2015 8:23:40 PM
 */
public class p219_sol1 {
	public static void main(String[] args){
		int[] a = {1,0,1,1};
		System.out.println(new p219_sol1().containsNearbyDuplicate(a, 1));
	}
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();//key is value, value is index 
        for(int i = 0; i < nums.length; i++){
            if(!hs.containsKey(nums[i])){
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                hs.put(nums[i], temp);
            }else{
                for(int j = 0; j < hs.get(nums[i]).size(); j++){
                    if(i - hs.get(nums[i]).get(j) <= k){
                        return true;
                    }
                }
                List<Integer> temp = hs.get(nums[i]);
                temp.add(i);
                hs.put(nums[i],temp);
            }
        }
        
        return false;
    }
}
