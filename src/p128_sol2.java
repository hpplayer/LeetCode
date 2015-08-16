import java.util.HashMap;
/**
 * This is another solution using HashMap.
 * They key is num in nums, values is the longest sequence length contains it.
 * The algorithm is each time we got a new input num,
 * we check if we have num - 1 and num + 1 in the HashMap
 * Update and store values in hashMap accordingly
 * 
 * The trick part is how can we update other values in the Map accordingly?
 * Our current input num may connect two separated array before it and after it, so 
 * will affect the length of all elements in this sequence.
 * But the most important thing is that we will never touch any values in the sequence later.
 * Why? since we will not revisit elements that has been previously visited. Also we will only 
 * check num - 1 and num + 1 in each search, that means we will only touch the head and tail 
 * of the sequence in the future not any element in the mid. So there is no meaning to update values 
 * in the mid of sequences, we only need to update the head and tail node. This is the most beautful
 * part of this algorithm.
 * 
 * But this algorithm will include many duplicate searchs, like 1->2->3
 * we will do search on each element in the array by checking num- 1 and num +1 
 * which actually is not necessary, since we can do linear search one time then skip remainings elements
 * if they have been included in previous search
 * 
 * So sol1 is much better and faster
 * @author hpPlayer
 * @date Aug 15, 2015 11:34:30 PM
 */
public class p128_sol2 {
	public static void main(String[] args){
		int[] nums = {1,2,0,1};
		System.out.println(new p128_sol2().longestConsecutive(nums));
	}
	   public int longestConsecutive(int[] nums) {
	        int result = 0;
	        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
	        for(int num : nums){
	            if(hs.containsKey(num)) continue;//in case we get duplicate, we dont want add extra values in it
	            int left = (hs.containsKey(num-1))? hs.get(num-1) : 0;
	            int right = (hs.containsKey(num+1))? hs.get(num+1) : 0;
	            int sum = left + 1 + right;
	            result = Math.max(sum, result);
	            hs.put(num, sum); //in case the sequence only has num itself, no num -1 and num + 1 
	            
	            hs.put(num - left, sum);
	            hs.put(num + right, sum);
	        }
	        
	        return result;
	    }
}
