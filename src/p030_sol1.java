import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*   Substring with Concatenation of All Words 
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * 
 * 
 */

/**
 * This is my own solution nearly without help
 * Though the time complexity is still O(m*n) or more specifically O((n-k*m)*m),
 * where m is the length of each word in words, k is number of word in words and n is the total length of string s
 * This solution is using two pointers and brute force algorithm
 * Some notes when I am solving this problem:
 * 1) pointerB always needs to jump with k steps like k-> 2k-> 3k...
 * 2) we need a data structure to record the appearance of each word, we can either use hashmap or array. (here, hashmap is better, as it 
 * provides constant lookup time)
 * 3) one word can be used in two substring like words = {a,b,c}, string s = abca, so we got two substrings abc and bca
 * 4) Same word can appear multi-times in words like words = {a, a} (I didn't observe this until get error result)
 * 5) total length may not be t times of each word's length like string s = abccc (length 5), while each word's length = 3
 * 6)** which proven to be incorrect: we can jump the word if it is not in words. Why it is incorrect? Because we may have extra char
 * that causes all following chars one char behind, we can not safely pass this word. Example: s = catisgood, words = {ati, sgo}. cat is not 
 * in words, if we pass it, then we will never get correct answer atisgo
 * 7) THE MOST IMPORTANT NOTE I MISS:
 * We can safely stop matching if remaining substring has length less then words's total length.
 * This is very critical when we deal with large test case, imagining if the word's total length is very long, then this tip will help us 
 * save a lot of time  
 * 
 * A much faster solution can be found in sol2
 * An alternative solution that uses counter to find match can be found in sol3
 * @author hpPlayer
 * @date Jul 29, 2015 8:14:23 PM
 */
public class p030_sol1 {
	public static void main(String[] args) {
		//String s = "barfoofoobarthefoobarman";
		//String[] words = { "bar", "foo", "the" };
		String s = "wordgoodgoodgoodbestword";
		String[] words = {"word","good","best","good"};
		//String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
		//String[] words = {"fooo","barr","wing","ding","wing"};
		
		
		//String s1 = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa";
		//String s2 = "bababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
		//String s3 = "ababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
		//String[] words = {"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
		
		System.out.println(new p030_sol1().findSubstring(s, words));
	}

	public List<Integer> findSubstring(String s, String[] words) {
		if (words == null || words.length == 0)
			return new ArrayList<Integer>();
		int len = words[0].length() * words.length; //our ideal substring's length
		//System.out.println(len);
		int wordLen = words[0].length();//each word's length
		
		//two hashmaps can help us compare substring more easily. Keep one map fixed, filling and resetting another one 
		HashMap<String, Integer> hs = resetHS(words);//expect hashmap
		HashMap<String, Integer> hs2 = new HashMap<String, Integer>();//actual hashmap
		
		List<Integer> result = new ArrayList<Integer>();
		int pointerA = 0, pointerB = 0;//two pointers, a for start index, b for index where we are
		// System.out.println(hs);
		
		//my original code is "while (pointerA + len <= s.length())", it is correct, but does not consider note 7, so will not pass large case
		while (pointerA + len <= s.length()) {//pointerA + len <= s.length(), this step is very important to pass large test case
			//System.out.println("B: " + pointerB);
			//System.out.println("A: " +  pointerA);
			//System.out.println();
			if (pointerB - pointerA == len) {// if found match, move pointerA one char forward, reset hashmap2 and pointerB
				//System.out.println("im here");
				result.add(pointerA);
				pointerA ++;
				pointerB = pointerA;
				hs2.clear();
			}
			pointerB += wordLen;//pointerB will move len(word) each step
			if (pointerB <= s.length()) {//in case we reach boundary case
				String str = s.substring(pointerB - wordLen, pointerB);
				//System.out.println(str);
				//System.out.println(hs);
				// System.out.println(str);
				if (hs.containsKey(str)) {//if we got this word in words
					if (hs2.containsKey(str)){
						hs2.put(str, hs2.get(str) + 1);
						//if this word has appeared more than expected times, then move pointerA one char forward, reset hashmap2 and pointerB
						if(hs2.get(str) > hs.get(str)){
							pointerA ++;
							pointerB = pointerA;
							hs2.clear();
						}
					}else{//legal word in words but have not put in hs2 yet
						hs2.put(str, 1);
					}
				}else {//not in words, move pointerA one char forward, reset hashmap2 and pointerB
					pointerA ++;
					pointerB = pointerA;
					hs2.clear();
				}
			} else {
				break;// not enough space left
			}
		}

		return result;
	}

	public HashMap<String, Integer> resetHS(String[] words) {
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		for (String str : words) {
			if (!hs.containsKey(str)) {
				hs.put(str, 1);
			} else {
				hs.put(str, hs.get(str) + 1);
			}
		}
		return hs;
	}

}
