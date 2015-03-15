/**
 * DFS solution, but not a combined DFS solution
 * The Basic idea is:
 * 1) we look two string char by char
 * 2) we only need to look different cases in P, and s is a regular string
 * 3) P may have following cases: 1. next char is not"*", 2. next char is "*"
 * 4) we need a isValid() to help us check if current char is a valid match, valid match includes:
 *  1. non of char is empty(boundary case)
 *  2. two chars are exactly same 
 *  3. char from P is "."
 * 5) for P case 1, we just need to check if isValid(), if not return false
 *    for P case 2, we have two cases, 1. current chars are not matched, so we just skip char in P and do DFS in P[i+2], 2. current
 *    chars are matched, then we need try different index in S, to find the next matched pairs(the char in S that matched P[i+2])
 *    if found, do DFS on next matched pairs..if following DFS return true, then return true
 * 6) The end of DFS means we have matched all chars in S and in P, so two pointers should all points to the end
 * 7) more detailed explanation please see code below
 * 
 * Remark:
 * 1) In isValid(), be careful about the case that we have reached the end of string, so the char is empty
 * 2) The boundary case of DFS is we have matched all chars in S and P
 * 3) Don't forget the case that we can use 0 element in a*, which we can skip isValid() condition for current pair, otherwise we 
 * have to check different numbers of a in P and found the next valid pair
 * 
 * @author hpPlayer
 * @date Mar 15, 2015 12:36:18 AM
 */
public class p010_sol2 {
	public static void main(String[] args) {
		String a = "aab";
		String b = "c*a*b";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a, b));
	}

	public static boolean isMatch(String s, String p) {
		return DFS(s, p, 0, 0);
	}

	public static boolean DFS(String s, String p, int IndexS, int IndexP) {
		if (IndexP >= p.length()) {
			// System.out.println("im here");
			//we can't directly return true, since we may have case S: aa, P: a, We are able to reach the end of P, but S not end..
			//also the condition must be IndexP, since we are manipulating IndexP, and we can return inappropriate case in isValid,
			//but if we judge IndexS, then it may happen  S: aa, P:a, then we will have 
			// S:a, P:a and IndexP = 2...then it will give error when read p.charAt(2);
			//return true;
			return IndexS == s.length();
		}
		
		//we must consider the case that IndexP == p.length()-1, so we will check the case that indexP +1 is out of boundary
		//we can treat this case as normal case which means we don't have "*" after it.
		if (IndexP == p.length() - 1 || p.charAt(IndexP + 1) != '*') {
			if (isValid(s, p, IndexS, IndexP)) {
				return DFS(s, p, IndexS + 1, IndexP + 1);
			} else {
				return false;
			}
		} else {
			// boolean notUsed = DFS(s, p, IndexS, IndexP+2);
			// if(notUsed){
			// return DFS(s, p, IndexS, IndexP+2);//we can't return here, since
			// IndexS and IndexP +2 are not necessarily the only choice
			if (DFS(s, p, IndexS, IndexP + 2)) {//if we use 0 element of a*
				return true;
			} else {
				//if current arrangement is valid
				while (isValid(s, p, IndexS, IndexP)) {
					/*then we try different numbers of a*
					 * the logic likes:
					 * S: aaab
					 * P: a*b
					 * current S[1] == P[1], then we can try P[1+2] =? S[1], P[1+2] =? S[2], P[1+2] =? S[3], P[1+2] =? S[4]
					 * which means, how many bits do we need to skip to find the next valid pair?
					 */
					if (DFS(s, p, IndexS + 1, IndexP + 2)) {
						return true;
					}
					IndexS++;//if current arrangement is valid, but not global solution
				}
			}

		}
		return false;
	}

	public static boolean isValid(String s, String p, int IndexS, int IndexP) {
		if (IndexS >= s.length() || IndexP >= p.length()) {
			return false;
		}
		return (s.charAt(IndexS) == p.charAt(IndexP) || p.charAt(IndexP) == '.');
	}
}
