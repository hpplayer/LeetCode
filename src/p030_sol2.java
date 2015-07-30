import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * BEST SOLUTION TO THIS PROBLEM!
 * This is a clever algorithm that uses window moving algorithm
 * Basically, we use left pointer to indicate where does the substring start, and use the right pointer to look for match
 * If we found an unexpected word, then move left pointer until skip this word
 * if we found an extra matching word that has exceeded the limit of matching word (like s:barfoobarthe, words:{bar, foo, the},
 * then we should skip the previous appearance of this word, which is differed from unexptected word that we need to skip all words
 * before it.
 * if we found an expected word, we simply add it into our collection and move windows len(word) away
 * In order to include all cases, here we need move window char by char, i.e. the window can be start anywhere from 0->len(word)
 * positions. By this way, we will include all cases of window. No need to consider case that start index after (len(word) + 1), 
 * because it will be taken care when we move window in previous step
 * 
 * Thus we will have O(len(word) * len(str)/len(word)) = O(len(str)) time complexity which will be much faster than sol1
 * 
 * @author hpPlayer
 * @date Jul 29, 2015 11:12:23 PM
 */

public class p030_sol2 {
	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = { "bar", "foo" };
		//String s = "wordgoodgoodgoodbestword";
		//String[] words = {"word","good","best","good"};
		//String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
		//String[] words = {"fooo","barr","wing","ding","wing"};
		
		
		//String s1 = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa";
		//String s2 = "bababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
		//String s3 = "ababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
		//String[] words = {"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
		
		System.out.println(new p030_sol2().findSubstring(s, words));
	}
	
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> result = new ArrayList<Integer>();
    	int len = words[0].length();
    	HashMap<String, Integer> map_expect = new HashMap<String, Integer>();
    	
    	//initialization
    	for(String word : words){
    		if(!map_expect.containsKey(word)){
    			map_expect.put(word, 1);
    		}else{
    			map_expect.put(word, map_expect.get(word) + 1);
    		}
    	}
    	
    	//we will move the window through string s
    	//the outer loop is moving windws cell by cell since the windwow size is large but we want check each possible case
    	//ex: s:catisgood, words = {ati, sgo}, without the outer loop, since inner loop only moves window by len(word) step,
    	//we will miss the match ati which starts from the second index
    	//if the problem is changed from matching string to matching char, then we dont need consider the outer loop
    	for(int i = 0; i < len; i++){
    		HashMap<String, Integer> map_actual = new HashMap<String, Integer>();
    		int count = 0;//it can be used ton count the total num of words we have included
    		int winleft = i;//record the index of windows left side
    		//the inner loop is moving the right side (i.e. j) of the window, though we may move leftwin in some special cases
    		//don't forget end the inner loop when remaing length in s is less then our expect string's length, which will help
    		//us pass the large test case
    		for(int winRight = i; winRight + len <= s.length(); winRight+= len){//move win, the index of cell is reflected in int j = i
    			String word = s.substring(winRight, winRight+len);
    			//if current word is not in words, then move left side of windows over this word
    			if(!map_expect.containsKey(word)){
    				map_actual.clear();//reset map_actual
    				count = 0;//reset count
    				winleft = winRight + len;//move winleft over this word, the new index of left side would be j + len
    				//restart loop, since the inner loop is for each cell, we dont need to change current index in window
    				continue;
    			}
    			
    			if (!map_actual.containsKey(word)){
    				map_actual.put(word, 1);
    			}else{
    				map_actual.put(word, map_actual.get(word) + 1);
    			}
    			
    			count ++;//we have included word into our collection
    			
    			//if current word makes the appeance of this word appears more than expected, then move window until we skip previous this word
    			//during the move, we have to drop some of previous words, even if they are legal
    			while(map_actual.get(word) > map_expect.get(word)){
    					String temp = s.substring(winleft, winleft+ len);
    					count --;
    					map_actual.put(temp, map_actual.get(temp) - 1);
    					winleft += len;
    			}
    			
    			/*
    			 * we can also use code below to replace if condition, either way, it means our window size has reached the target substring size
    			 * if(winRight - winleft + len== (words.length)*len){//we need add length of word to include the word starts from winRight
    			 */
    			if (count == words.length){//found a complete match
    				result.add(winleft);
    				//move window one word futher
    				String temp = s.substring(winleft, winleft + len);
    				map_actual.put(temp, map_actual.get(temp) - 1);
    				winleft += len;
    				count --;
    			}
    		}
    	}
    	
    	return result;
    }
}
