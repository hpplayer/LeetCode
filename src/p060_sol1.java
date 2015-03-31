import java.util.ArrayList;
import java.util.HashSet;
/**
 * Purely recursive search, generate from 1 to kth number, the solution is supposed to be right, but will cause TLE error
 * @author hpPlayer
 * @date Mar 30, 2015 11:52:56 PM
 */
public class p060_sol1 {
	public static void main(String[] args) {
		p060_sol1 test = new p060_sol1();

		System.out.println(test.getPermutation(9, 171669));
	}

	String s = null;

	public String getPermutation(int n, int k) {
		if (k < 1)
			return null;
		int a = 1 << n;
		ArrayList<String> ary = new ArrayList<String>();
		for (int i = 1; i <= n; i++) {
			ary.add(i + "");
		}
		DFS(null, k, n, ary, new HashSet<String>());
		return s;
	}

	int count = 0;

	public void DFS(StringBuffer currS, int k, int n, ArrayList<String> hs, HashSet<String> used) {
		if (currS != null && currS.length() == n) {
			// System.out.println(currS.length());
			count++;
			if (count == k)
				s = currS.toString();
			return;
		}

		for (int i = 0; i < hs.size(); i++) {
			if (used.contains(hs.get(i)))
				continue;
			used.add(hs.get(i));
			if (currS == null)
				currS = new StringBuffer();
			currS.append(hs.get(i));
			// System.out.println(currS);
			HashSet<String> temp = new HashSet<String>(used);
			DFS(currS, k, n, hs, temp);
			currS.deleteCharAt(currS.length() - 1);
			used.remove(hs.get(i));
		}
	}

}
