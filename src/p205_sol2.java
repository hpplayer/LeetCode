/**
 * This solution is a very neat and smart solution, here is the explanation:
 * We firstly assume character in input string is only ASCII character, which has 256 in total
 * We then create two array of length 256 for two input strings
 * Then we treat the pair of characters in the input string as same. We will store the index in its corresponding array cell.
 * So next time, if we visit the same characters, we can check the index of last appearance. In previous storage, we treat
 * two characters as same, so if we encounter the occurrence of same pair, the index stored there should also be the same.
 * If not same, we will return false, as now we have another pair but mapping to a previously used character
 * 
 * Remark:
 * To get rid of the confusion about index 0 and initial value 0 in the array, we simply let the index be index + 1,
 * so index 0 will become 1 and no longer equal to the initial value in the array
 * Example:
 * s: a, a
 * t: b, a
 * 
 * ary1 = [ 0, 0]
 * ary2 = [ 0, 0]
 * When we scan index 0,
 * we found cell value of a in ary1 is 0
 * while the cell value of a in ary2 is 0
 * that means we found a pair of unused characters, that's good, we can store the index there
 * we will update arrays:
 * ary1 = [ 1, 0]
 * ary2 = [ 0, 1]
 * 
 * When we scan index 1,
 * we found cell value of a in ary1 is 1
 * while the cell value of a in ary2 is 0
 * That means, we have found a new pair that map used "a" in string s to a unused "a" in string t
 * we will return false
 * 
 * In sol2, we use two arrays to store the relationship in two directions. If we found two indexes stored in arrays are not same,
 * that means we have used such character in another relationship (we don't need to know which direction got corrupted, as long as 
 * they are not same, we will return false)
 * 
 * This algorithm also uses O(n) time, but it uses less space as it only uses two array of length 256
 * 
 * So sol2 is more recommended
 * @author hpPlayer
 * @date Sep 1, 2015 2:12:21 PM
 */
public class p205_sol2 {
	   public boolean isIsomorphic(String s, String t) {
	        //the num of total units in ASCII is 256
	        int[] ary1 = new int[256];
	        int[] ary2 = new int[256];
	        for(int i = 0; i < s.length(); i++){
	            if(ary1[s.charAt(i)] != ary2[t.charAt(i)]) return false;
	            
	            //why i + 1? since the inital value in array is 0
	            //if we look at the beginning index of two chars, they will also be 0
	            //to avoid such mix, we let i + 1, so we can distinguish index 0 and inital value 0
	            ary1[s.charAt(i)] = ary2[t.charAt(i)] = i + 1;
	        }
	        
	        return true;
	    }
}
