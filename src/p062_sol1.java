import java.util.ArrayList;
import java.util.HashMap;
/**
 * Recursive way ... should work, but takes long time
 * Basic idea: if found target, then return, count +1
 * But it is too slow, we can use DP to save time
 * @author hpPlayer
 * @date Mar 16, 2015 5:24:28 PM
 */
public class p062_sol1 {
	public static void main(String[] args) {
		p062_sol1 temp = new p062_sol1();
		System.out.println(temp.uniquePaths(8, 10));
	}

	public class Pointer {
		int x;
		int y;

		public Pointer(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int uniquePaths(int m, int n) {
		ArrayList<Pointer> Path = new ArrayList<Pointer>();
		Pointer ori = new Pointer(0, 0);
		Path.add(ori);
		HashMap<Pointer, Integer> hs = new HashMap<Pointer, Integer>();
		//result.add(Path);
		int result = DP(ori, m, n, hs);
		return result;
	}

	public int DP(Pointer p, int m, int n, HashMap<Pointer, Integer> hs) {
		if(hs.containsKey(p)){
			return hs.get(p);
		}
		int count = 0;
		if (p.x == m-1 && p.y == n-1) {
			return 1;
		}

		if (p.x < m-1) {
			//System.out.println("i, here");
			Pointer right = new Pointer(p.x + 1, p.y);
			//Path.add(right);
			count += DP(right, m, n, hs);
			//Path.remove(right);
		
		}
		if(p.y < n-1){
			Pointer botm = new Pointer(p.x, p.y + 1);
			//Path.add(botm);
			count += DP(botm, m, n, hs);
			//Path.remove(botm);
		}
		hs.put(p, count);
		return count;
	}
}
