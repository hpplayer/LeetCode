/**
 * clean and short version of KMP(using for loop to search match)
 * @author hpPlayer
 * @date Mar 31, 2015 3:40:52 PM
 */
public class p028_sol5 {
	public int[] buildNext(String needle) {
		int[] next = new int[needle.length()];
		next[0] = 0;
		int prev = -1;// we set it to -1 to distinguish it with other unmatched
						// match, -1 is reset to head and fill 0 to table
		int post = 0;
		while (post < needle.length() - 1) {// post is the moving index, we have
											// set next[0] = 0, so we need loop
											// needle.length -1 loop
			if (prev == -1 || needle.charAt(prev) == needle.charAt(post)) {
				prev++;
				post++;
				next[post] = prev;
			} else {
				prev = -1;// unmatched, reset to -1
			}
		}
		return next;
	}

	public int strStr(String haystack, String needle) {
		if (needle == null || haystack == null
				|| needle.length() > haystack.length())
			return -1;
		if (needle.isEmpty())
			return 0;
		int next[] = buildNext(needle);
		int j = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				j++;
				if (j == needle.length())
					return i + 1 - j;
				/*
				 * we must put the check statement here, otherwise we may get error in boundary
				 * case (like our loop only loops one time, we have found the
				 * index, but we have to report it in next loop, which may let
				 * us miss this match and report -1, ex: "a", "a" )
				 */
			} else if (j > 0) {//must >0, otherwise, if first char is unmatched, we will loop forever
				j = next[j];
				i--;// move j steps and recompare current i and j, if not i--,
					// then our loop will proceed and we may lose this potential
					// index
			}
		}
		return -1;
	}
}
