import java.util.HashMap;
import java.util.Map;
/**
 * This is the official solution.
 * It observe that we can remove 4, 9, 40, 90, 400, 900 from the table
 * and put them all in the hashmap
 * But how we deal with those number then?
 * We observe that if curr number is larger than prev number like IX, IV, etc.
 * Those are 4, 9,.., that we removed from table.
 * So we can simply subtract them from the total then plus (curr value - prev value)
 * 
 * This approach is more simple and smooth than my solution, so more recommended!
 * @author hpPlayer
 * @date Jul 23, 2015 2:07:45 PM
 */

public class p013_sol2 {
    private Map<Character, Integer> hs = new HashMap<Character, Integer>(){
    	{put('I', 1); put('V', 5); put('X', 10); put('L', 50); put('C', 100); put('D', 500); put('M', 1000);}
    };
    public int romanToInt(String s) {
    	int prev = 0, total = 0;//this method needs record prev value, so we can deal with 4 and 9 cases
        for(char c: s.toCharArray()){
        	int curr = hs.get(c);
        	//since we have added prev to total, we need subtract it from total if curr > prev, we also need subtract it from cur
        	//to make it correct (ex: IV = 5-1, where we have added 1 to total, so we need have 1 = 1 + 5 - 1*2 = 4)
        	total += curr > prev? curr - 2* prev : curr; 
        	prev = curr;
        }

        return total;

    }
}
