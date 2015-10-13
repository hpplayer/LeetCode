/*
Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "*") ¡ú true
isMatch("aa", "a*") ¡ú true
isMatch("ab", "?*") ¡ú true
isMatch("aab", "c*a*b") ¡ú false

*/

/**
 * I firstly used recursive approach to do this problem, but got stack overflow error in running test code. 
 * I remember a problem before(p130) that also get the similar problem(it is LTE), that time I used stack instead to do the problem 
 * This time, we can't use stack since we need to know all following strings after and can't just compare current bit
 * We know recursion and iteration can be converted to each other, so this time we do with iteration. 
 * Basic idea is similar to previous question(p10), we try use * 0 time, if not matched, then try it 1 time, until the bit after * in
 * P can find a matching bit in S, due to iteration, we can't simply check if(DFS) false and then offset++. This time, we use a trick
 * i.e. use two variables backS and backP to record the position of the * case, if we found an unmatched pair, then we firstly check
 * if we have any value stored in backP, if it is, then we set current index to backP, and let offset to backS ++. This is nice, and 
 * we would not have case that a position of * replaced previous position of * and cause error, since we only can visit the second *
 * if its previous bit is matched(unmatched case will reset the loop). 
 * How many offset can we add to backS? the offset will never let the IndexS larger than S.length()
 * 
 * If we jump out of the loop, it either means we have tried add all possible offset and still cannot find matched offset, or 
 * because we have matched all pairs in S and P, so we need to check if the remaining P is 0, if it is, then we are done, if not 
 * we will have two cases: p is ending with "*" or p is ending with non-* bit, the first case should return true, the second case
 * should return false; So we can use a while loop to check this condition.
 * 
 * 
 * @author hpPlayer
 * @date Mar 16, 2015 1:03:26 AM
 */
public class p044_sol1 {
	public static void main(String[] args) {
		String a = "aa";
		String b = "a";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a, b));
	}

	public static boolean isMatch(String s, String p) {
		return DFS(s, p);
	}

	public static boolean DFS(String s, String p) {
		// if(p.length() == 0){
		// return s.length() == 0;
		// }
		int PointerP = 0, PointerS = 0, BackupP = -1, BackupS = -1;
		while (PointerS < s.length()) {

			if (PointerP < p.length()
					&& (p.charAt(PointerP) == '?' || p.charAt(PointerP) == s
							.charAt(PointerS))) {
				PointerP++;
				PointerS++;
			} else if (PointerP < p.length() && p.charAt(PointerP) == '*') {
				BackupP = PointerP + 1;// store the next bit place, it equals the case that current pair is matched, we want to find next
				// match case
				BackupS = PointerS;

				PointerP++;// get next bit,we firstly try case that * is 0 length
			} else {// if our strings are not matched..then we need go back and reset
				if (BackupP == -1) {// if we have not encountered * before
					return false;// no previous star, definitely error
				} else {
//if we encountered * before, then we try use * with length  +1 each time, until we reach the offset that larger than  the size of s
					BackupS++;// try 0, 1, ,2, 3 ,4 offset
					PointerS = BackupS;
					PointerP = BackupP;
				}
			}

		}

		while (PointerP < p.length() && p.charAt(PointerP) == '*') {
			PointerP++;
		}

		return PointerP == p.length();
	}

}
