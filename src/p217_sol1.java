import java.util.HashMap;
/*
Contains Duplicate

Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.
*/

/**
 * This is my AC solution without help
 * At first I thought the integer only goes from 0 to 9, so I use an array to do it
 * But later I found it may also contains negatives or number > 10, so I changed it to HashMap
 * But actually, we dont need to do hashMap, as we dont need to go through all numbers.
 * We can return "true" as long as we found some values already got duplicate.
 * So hashset could come and help
 * 
 * This version is O(2*n) time complexity while hashSet version is O(n) time complexity
 * See sol2 for hashSet version
 * @author hpPlayer
 * @date Aug 7, 2015 7:40:58 PM
 */
public class p217_sol1 {
	public static void main(String[] args){
		int[] a = {3,3};
		System.out.println(new p217_sol1().containsDuplicate(a));
	}
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for(int i : nums){
            if (!hs.containsKey(i)){
            	hs.put(i, 0);
            }else{
            	hs.put(i, hs.get(i) + 1);
            }
        }
        
        for(int i : hs.values()){
            if (i >= 1) return true;
        }
        
        return false;
    }
}
